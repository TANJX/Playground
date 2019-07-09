package app.metroman;
/*
 * Created by marstanjx on 2019-07-09.
 * Copyright ISOTOPE Studio
 */

import app.google_map.OpenImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class combineAllImages {

    private static void step1() {

        for (int x = 0; x <= 27; x++) {
            for (int y = 0; y <= 26; y++) {
                System.out.println("Saving" + x + "-" + y);
                OpenImage.saveImageOnline(
                        "http://www.metroman.cn/image/rail/13/" + x + "_" + y + ".png",
                        "/Users/marstanjx/Desktop/images/" + x + "-" + y + ".png"
                );
            }
        }
    }

    public static void main(String[] args) throws IOException {


        // create the new image, canvas size is the max. of both image sizes
        int w = 27 * 257 + 89;
        int h = 26 * 257 + 245;
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics g = combined.getGraphics();

        File path = new File("/Users/marstanjx/Desktop/images/");
        File[] listOfFiles = path.listFiles();

        assert listOfFiles != null;
        for (File image_file : listOfFiles) {
            BufferedImage image = ImageIO.read(image_file);
            String[] split = image_file.getName().split("\\.")[0].split("-");
            int y = Integer.parseInt(split[1]);
            int x = Integer.parseInt(split[0]);
            System.out.println(x + " " + y);

            g.drawImage(image, x * 257, y * 257, null);
        }

        // Save as new image
        ImageIO.write(combined, "PNG", new File(path, "combined.png"));
    }
}
