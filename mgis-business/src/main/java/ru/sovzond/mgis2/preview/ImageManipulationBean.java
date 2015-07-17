package ru.sovzond.mgis2.preview;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

/**
 * Created by Alexander Arakelyan on 16.07.15.
 */
@Service
public class ImageManipulationBean {

	public static final int MAX_DIMENSION = 100;

	public byte[] createThumbnailFromImage(byte[] bytes) throws IOException {
		BufferedImage read = ImageIO.read(new ByteArrayInputStream(bytes));
		Image image = read.getScaledInstance(MAX_DIMENSION, MAX_DIMENSION * read.getHeight() / read.getWidth(), BufferedImage.SCALE_SMOOTH);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(toBufferedImage(image, BufferedImage.TYPE_INT_ARGB), "png", byteArrayOutputStream);
		return byteArrayOutputStream.toByteArray();
	}

	public BufferedImage toBufferedImage(final Image image, final int type) {
		if (image instanceof BufferedImage) { return (BufferedImage) image; }
		if (image instanceof VolatileImage) { return ((VolatileImage) image).getSnapshot(); }
		loadImage(image);
		final BufferedImage buffImg = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		final Graphics2D g2 = buffImg.createGraphics();
		g2.drawImage(image, null, null);
		g2.dispose();
		return buffImg;
	}

	private void loadImage(final Image image) {
		class StatusObserver implements ImageObserver {
			boolean imageLoaded = false;

			@Override
			public boolean imageUpdate(final Image img, final int infoflags, final int x, final int y, final int width, final int height) {
				if (infoflags == ALLBITS) {
					synchronized (this) {
						imageLoaded = true;
						notify();
					}
					return true;
				}
				return false;
			}
		}
		final StatusObserver imageStatus = new StatusObserver();
		synchronized (imageStatus) {
			if (image.getWidth(imageStatus) == -1 || image.getHeight(imageStatus) == -1) {
				while (!imageStatus.imageLoaded) {
					try {
						imageStatus.wait();
					} catch (InterruptedException ex) {
					}
				}
			}
		}
	}

	/*
	 * public byte[] createThumbnailFromDoc(byte[] bytes) throws ConnectException { ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
	 * ByteArrayOutputStream baos = new ByteArrayOutputStream(); // File inputFile = new File("document.doc"); // File outputFile = new
	 * File("document.pdf"); // connect to an OpenOffice.org instance running on port 8100 OpenOfficeConnection connection = new
	 * SocketOpenOfficeConnection(8100); connection.connect(); // convert DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
	 * // converter.convert(inputFile, outputFile); converter.convert(bais, DocumentFormatRegistry., outputStream, outputFormat); // close the
	 * connection connection.disconnect(); return new byte[0]; }
	 */

	public byte[] createDocThumbnail() throws IOException {
		try (InputStream is = ImageManipulationBean.class.getResourceAsStream("document-48x48.png")) {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[16384];
			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
			return buffer.toByteArray();
		}
	}
}
