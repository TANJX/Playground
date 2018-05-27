package io.image;
/*
 * Created by david on 5/21/2018.
 * Copyright ISOTOPE Studio
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CompleteBlackAndWhite {

    public static void main(String[] args) {

        // For storing image in RAM
        BufferedImage image = null;

        // READ IMAGE
        File folder = new File("C:\\Users\\david\\Desktop\\sheet\\");
        for (File input_file : folder.listFiles()) {

            try {
            /* create an object of BufferedImage type and pass
               as parameter the width,  height and image int
               type.TYPE_INT_ARGB means that we are representing
               the Alpha, Red, Green and Blue component of the
               image pixel using 8 bit integer value. */

                // Reading input file
                image = ImageIO.read(input_file);

                System.out.println("Reading complete.");
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
            assert image != null;
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int rgb = image.getRGB(x, y);
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = (rgb >> 0) & 0xFF;
                    if (r > 100 && g > 100 && b > 100) {
                        image.setRGB(x, y, getIntFromColor(255, 255, 255));
                    } else {
                        image.setRGB(x, y, getIntFromColor(0, 0, 0));
                    }
                }
            }

            // WRITE IMAGE
            try {
                // Output file path
                File output_file = new File("C:\\Users\\david\\Desktop\\out\\" + input_file.getName());

                // Writing to file taking type and path as
                ImageIO.write(image, "jpg", output_file);

                System.out.println("Writing complete.");
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }
    }

    private static int getIntFromColor(int Red, int Green, int Blue) {
        Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        Blue = Blue & 0x000000FF; //Mask out anything not blue.

        return 0xFF000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
    }
}
