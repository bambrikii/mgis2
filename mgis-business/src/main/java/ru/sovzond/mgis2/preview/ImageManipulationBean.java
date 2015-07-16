package ru.sovzond.mgis2.preview;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Alexander Arakelyan on 16.07.15.
 */
@Service
public class ImageManipulationBean {


    public static final int MAX_DIMENSION = 100;

    public byte[] createThumbnail(byte[] bytes) throws IOException {
        BufferedImage read = ImageIO.read(new ByteArrayInputStream(bytes));
        Image image = read.getScaledInstance(MAX_DIMENSION, MAX_DIMENSION * read.getHeight() / read.getWidth(), BufferedImage.SCALE_SMOOTH);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(toBufferedImage(image, BufferedImage.TYPE_INT_ARGB), "png", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public BufferedImage toBufferedImage(final Image image, final int type) {
        if (image instanceof BufferedImage)
            return (BufferedImage) image;
        if (image instanceof VolatileImage)
            return ((VolatileImage) image).getSnapshot();
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

            public boolean imageUpdate(final Image img, final int infoflags,
                                       final int x, final int y, final int width, final int height) {
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
}
