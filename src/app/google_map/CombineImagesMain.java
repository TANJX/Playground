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

public class CombineImagesMain {

    public static void main(String[] args) throws IOException {
        // create the new image, canvas size is the max. of both image sizes
        int w = 18 * 1280;
        int h = 10 * 1200;
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics g = combined.getGraphics();

        File path = new File("C:\\Users\\david\\Desktop\\Google Maps\\zoom 14");
        File[] listOfFiles = path.listFiles();

        assert listOfFiles != null;
        for (File image_file : listOfFiles) {
            BufferedImage image = ImageIO.read(image_file);
            String[] split = image_file.getName().split("\\.")[0].split("-");
            int x = Integer.parseInt(split[1]) - 1;
            int y = Integer.parseInt(split[0]) - 1;
            System.out.println(x + " " + y);

            g.drawImage(image, x * 1280, y * 1200, null);
        }

        // Save as new image
        ImageIO.write(combined, "PNG", new File(path, "combined.png"));
    }
}
