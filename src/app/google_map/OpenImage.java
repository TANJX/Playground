package app.google_map;
/*
 * Created by david on 2019/01/26.
 * Copyright ISOTOPE Studio
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class OpenImage {

    static void saveImageOnline(String url_str, String path) {
        Image image = null;
        try {
            URL url = new URL(url_str);
            image = ImageIO.read(url);
            File outputfile = new File(path);
            ImageIO.write(toBufferedImage(image), "png", outputfile);
        } catch (IOException e) {
        }
    }

    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
