package app.google_map;
/*
 * Created by david on 2019/01/26.
 * Copyright ISOTOPE Studio
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GetImagesMain {

    public static void main(String[] args) {
        List<String> list = GenerateCoords.generateCoords(22.8864101, 113.6922456, 22.4119839, 114.6425105, 14);

        final String outputPath = "C:\\Users\\david\\Desktop\\Google Maps\\zoom 14\\";
        for (String line : list) {
            String[] split = line.split(",");
            if (split.length < 3) continue;
            double lat = Double.parseDouble(split[1]);
            double lon = Double.parseDouble(split[2]);
            String url = StaticAPISign.getUrl(lat, lon, 14);
            OpenImage.saveImageOnline(url, outputPath + split[0] + ".png");
            System.out.println(split[0] + " done!");
        }
    }

    private static List<String> readFile(String path) {
        List<String> result = new ArrayList<>();
        // Open the file
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream(path);

            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;

            while ((strLine = br.readLine()) != null) {
                result.add(strLine);
            }

            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
