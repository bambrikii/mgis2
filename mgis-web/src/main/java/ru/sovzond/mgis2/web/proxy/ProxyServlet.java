package ru.sovzond.mgis2.web.proxy;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.AbortableHttpRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.HeaderGroup;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.net.HttpCookie;
import java.net.URI;
import java.util.Enumeration;
import java.util.List;

/**
 * https://github.com/mitre/HTTP-Proxy-Servlet/blob/master/src/main/java/org/mitre/dsmiley/httpproxy/ProxyServlet.java
 * <p>
 * Created by Alexander Arakelyan on 20/08/15.
 */
public class ProxyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HttpClient proxyClient;

	protected static final HeaderGroup hopByHopHeaders;

	static {
		hopByHopHeaders = new HeaderGroup();
		String[] headers = new String[]{"Connection", "Keep-Alive", "Proxy-Authenticate", "Proxy-Authorization", "TE", "Trailers", "Transfer-Encoding", "Upgrade"};
		for (String header : headers) {
			hopByHopHeaders.addHeader(new BasicHeader(header, null));
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		HttpParams hcParams = new BasicHttpParams();
		hcParams.setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.IGNORE_COOKIES);
		proxyClient = createHttpClient(hcParams);
	}

	@SuppressWarnings({"unchecked", "deprecation"})
	protected HttpClient createHttpClient(HttpParams hcParams) {
		try {
			Class clientClazz = Class.forName("org.apache.http.impl.client.SystemDefaultHttpClient");
			Constructor constructor = clientClazz.getConstructor(HttpParams.class);
			return (HttpClient) constructor.newInstance(hcParams);
		} catch (ClassNotFoundException e) {
			//no problem; use v4.1 below
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return new DefaultHttpClient(new ThreadSafeClientConnManager(), hcParams);
	}

	@Override
	public void destroy() {
		if (proxyClient instanceof Closeable) {//TODO AutoCloseable in Java 1.6
			try {
				((Closeable) proxyClient).close();
			} catch (IOException e) {
				log("While destroying servlet, shutting down HttpClient: " + e, e);
			}
		} else {
			if (proxyClient != null)
				proxyClient.getConnectionManager().shutdown();
		}
		super.destroy();
	}

	@Override
	protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
		String method = servletRequest.getMethod();
		String proxyRequestUri = servletRequest.getQueryString();
		HttpRequest proxyRequest;
		if (servletRequest.getHeader(HttpHeaders.CONTENT_LENGTH) != null || servletRequest.getHeader(HttpHeaders.TRANSFER_ENCODING) != null) {
			HttpEntityEnclosingRequest eProxyRequest = new BasicHttpEntityEnclosingRequest(method, proxyRequestUri);
			eProxyRequest.setEntity(new InputStreamEntity(servletRequest.getInputStream(), servletRequest.getContentLength()));
			proxyRequest = eProxyRequest;
		} else
			proxyRequest = new BasicHttpRequest(method, proxyRequestUri);

		copyRequestHeaders(servletRequest, proxyRequest);

		HttpResponse proxyResponse = null;
		try {
			URI uri = new URI(proxyRequestUri);
			proxyResponse = proxyClient.execute(new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme()), proxyRequest);
			int statusCode = proxyResponse.getStatusLine().getStatusCode();
			copyResponseHeaders(proxyResponse, servletRequest, servletResponse);
			servletResponse.setStatus(statusCode, proxyResponse.getStatusLine().getReasonPhrase());
			copyResponseEntity(proxyResponse, servletResponse);

		} catch (Exception e) {
			if (proxyRequest instanceof AbortableHttpRequest) {
				AbortableHttpRequest abortableHttpRequest = (AbortableHttpRequest) proxyRequest;
				abortableHttpRequest.abort();
			}
			if (e instanceof RuntimeException)
				throw (RuntimeException) e;
			if (e instanceof ServletException)
				throw (ServletException) e;
			if (e instanceof IOException)
				throw (IOException) e;
			throw new RuntimeException(e);

		} finally {
			if (proxyResponse != null)
				consumeQuietly(proxyResponse.getEntity());
		}
	}


	/**
	 * Copy proxied response headers back to the servlet client.
	 */
	protected void copyResponseHeaders(HttpResponse proxyResponse, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		for (Header header : proxyResponse.getAllHeaders()) {
			if (hopByHopHeaders.containsHeader(header.getName()))
				continue;
			if (header.getName().equalsIgnoreCase(org.apache.http.cookie.SM.SET_COOKIE) || header.getName().equalsIgnoreCase(org.apache.http.cookie.SM.SET_COOKIE2)) {
				copyProxyCookie(servletRequest, servletResponse, header);
			} else {
				servletResponse.addHeader(header.getName(), header.getValue());
			}
		}
	}

	/**
	 * Copy cookie from the proxy to the servlet client.
	 * Replaces cookie path to local path and renames cookie to avoid collisions.
	 */
	protected void copyProxyCookie(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Header header) {
		List<HttpCookie> cookies = HttpCookie.parse(header.getValue());
		String path = servletRequest.getContextPath(); // path starts with / or is empty string
		path += servletRequest.getServletPath(); // servlet path starts with / or is empty string

		for (HttpCookie cookie : cookies) {
			//set cookie name prefixed w/ a proxy value so it won't collide w/ other cookies
			String proxyCookieName = getCookieNamePrefix() + cookie.getName();
			Cookie servletCookie = new Cookie(proxyCookieName, cookie.getValue());
			servletCookie.setComment(cookie.getComment());
			servletCookie.setMaxAge((int) cookie.getMaxAge());
			servletCookie.setPath(path); //set to the path of the proxy servlet
			// don't set cookie domain
			servletCookie.setSecure(cookie.getSecure());
			servletCookie.setVersion(cookie.getVersion());
			servletResponse.addCookie(servletCookie);
		}
	}

	/**
	 * Copy response body data (the entity) from the proxy to the servlet client.
	 */
	protected void copyResponseEntity(HttpResponse proxyResponse, HttpServletResponse servletResponse) throws IOException {
		HttpEntity entity = proxyResponse.getEntity();
		if (entity != null) {
			OutputStream servletOutputStream = servletResponse.getOutputStream();
			entity.writeTo(servletOutputStream);
		}
	}

	/**
	 * HttpClient v4.1 doesn't have the
	 * {@link org.apache.http.util.EntityUtils#consumeQuietly(org.apache.http.HttpEntity)} method.
	 */
	protected void consumeQuietly(HttpEntity entity) {
		try {
			EntityUtils.consume(entity);
		} catch (IOException e) {//ignore
			log(e.getMessage(), e);
		}
	}

	/**
	 * Copy request headers from the servlet client to the proxy request.
	 */
	protected void copyRequestHeaders(HttpServletRequest servletRequest, HttpRequest proxyRequest) {
		// Get an Enumeration of all of the header names sent by the client
		Enumeration enumerationOfHeaderNames = servletRequest.getHeaderNames();
		while (enumerationOfHeaderNames.hasMoreElements()) {
			String headerName = (String) enumerationOfHeaderNames.nextElement();
			//Instead the content-length is effectively set via InputStreamEntity
			if (headerName.equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH))
				continue;
			if (hopByHopHeaders.containsHeader(headerName))
				continue;

			Enumeration headers = servletRequest.getHeaders(headerName);
			while (headers.hasMoreElements()) {//sometimes more than one value
				String headerValue = (String) headers.nextElement();
				// In case the proxy host is running multiple virtual servers,
				// rewrite the Host header to ensure that we get content from
				// the correct virtual server
				if (headerName.equalsIgnoreCase(HttpHeaders.HOST)) {
				} else if (headerName.equalsIgnoreCase(org.apache.http.cookie.SM.COOKIE)) {
					headerValue = getRealCookie(headerValue);
				}
				proxyRequest.addHeader(headerName, headerValue);
			}
		}
	}

	/**
	 * Take any client cookies that were originally from the proxy and prepare them to send to the
	 * proxy.  This relies on cookie headers being set correctly according to RFC 6265 Sec 5.4.
	 * This also blocks any local cookies from being sent to the proxy.
	 */
	protected String getRealCookie(String cookieValue) {
		StringBuilder escapedCookie = new StringBuilder();
		String cookies[] = cookieValue.split("; ");
		for (String cookie : cookies) {
			String cookieSplit[] = cookie.split("=");
			if (cookieSplit.length == 2) {
				String cookieName = cookieSplit[0];
				if (cookieName.startsWith(getCookieNamePrefix())) {
					cookieName = cookieName.substring(getCookieNamePrefix().length());
					if (escapedCookie.length() > 0) {
						escapedCookie.append("; ");
					}
					escapedCookie.append(cookieName).append("=").append(cookieSplit[1]);
				}
			}

			cookieValue = escapedCookie.toString();
		}
		return cookieValue;
	}

	/**
	 * The string prefixing rewritten cookies.
	 */
	protected String getCookieNamePrefix() {
		return "!Proxy!" + getServletConfig().getServletName();
	}

}
