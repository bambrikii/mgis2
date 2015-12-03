package ru.sovzond.mgis2.common.exceptions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;

/**
 * Created by Alexander Arakelyan on 30.11.15.
 */
public class StackTraceFactory {
	public static void printStackTrace(StringBuilder result, Exception ex) {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 PrintStream ps = new PrintStream(baos)) {
			ex.printStackTrace(ps);
			result.append(new String(baos.toByteArray(), Charset.forName("utf-8")));
		} catch (IOException ex2) {
			result.append(ex2.getMessage());
		}
	}

	public static String stackTraceToString(Exception ex) {
		StringBuilder sb = new StringBuilder();
		printStackTrace(sb, ex);
		return sb.toString();
	}
}
