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
import java.util.List;

public class GoogleMapGenMain {
    public static void main(String[] args) throws IOException {
        double x1 = 22.8864101, y1 = 113.6922456, x2 = 22.0119839, y2 = 114.2425105;
        final String rootPath = "C:\\Users\\david\\Desktop\\Google Maps\\";
        for (int zoom = 13; zoom < 14; zoom++) {
            System.out.println("Generating Zoom " + zoom);
            generate(x1, y1, x2, y2, zoom, rootPath);
        }
    }

    private static void generate(double x1, double y1, double x2, double y2, int zoom, String rootPath) {

        List<String> list = GenerateCoords.generateCoords(x1, y1, x2, y2, zoom);

        final String outputPath = rootPath + "zoom " + zoom + "\\";
        File dir = new File(outputPath);
        if (!dir.exists()) dir.mkdirs();


        for (String line : list) {
            String[] split = line.split(",");
            if (split.length < 3) continue;
            double lat = Double.parseDouble(split[1]);
            double lon = Double.parseDouble(split[2]);
            String url = StaticAPISign.getUrl(lat, lon, zoom);
            OpenImage.saveImageOnline(url, outputPath + split[0] + ".png");
            System.out.println("Saving " + split[0] + " done!");
        }
        int s1 = Integer.parseInt(list.get(list.size() - 1).split(",")[0].split("-")[0]);
        int s2 = Integer.parseInt(list.get(list.size() - 1).split(",")[0].split("-")[1]);

        System.out.println("Saving image...");
        // Save as new image
        try {
            // create the new image, canvas size is the max. of both image sizes
            int w = s2 * 1280;
            int h = s1 * 1200;
            BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

            Graphics g = combined.getGraphics();

            File path = new File(outputPath);
            File[] listOfFiles = path.listFiles();

            assert listOfFiles != null;
            for (File image_file : listOfFiles) {
                BufferedImage image = ImageIO.read(image_file);
                String[] split = image_file.getName().split("\\.")[0].split("-");
                int x = Integer.parseInt(split[1]) - 1;
                int y = Integer.parseInt(split[0]) - 1;
                System.out.println("Adding " + x + " " + y);

                g.drawImage(image, x * 1280, y * 1200, null);
            }
            ImageIO.write(combined, "PNG", new File(new File(rootPath), "combined zoom " + zoom + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Completed.");
    }
}
