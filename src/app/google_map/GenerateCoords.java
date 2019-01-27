package app.google_map;
/*
 * Created by david on 2019/01/26.
 * Copyright ISOTOPE Studio
 */

import java.util.ArrayList;
import java.util.List;

public class GenerateCoords {

    public static void main(String[] args) {
        List<String> list = generateCoords(22.8864101, 113.6922456, 22.4119839, 114.6425105, 14);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println(list.size());
    }

    static List<String> generateCoords(double x1, double y1, double x2, double y2, int zoom) {
        scale = 1 << zoom;

        List<String> result = new ArrayList<>();
        Coord top_left = new Coord(x1, y1);
        Coord bottom_right = new Coord(x2, y2);
        Coord current = new Coord(x1, y1);

        int x = 1, y = 1;
        while (current.x > bottom_right.x) {
            result.add(y + "-" + x + "," + current.x + "," + current.y);
            while (current.y < bottom_right.y) {
                Coord world_coord = getWorldCoord(current.x, current.y);
                Coord pixel_coord = getPixelCoord(world_coord.x, world_coord.y);

                pixel_coord.x += 640;
                world_coord.x = pixelToWorld((int) pixel_coord.x);
                current.y = worldXToLng(world_coord.x);

                x++;
                result.add(y + "-" + x + "," + current.x + "," + current.y);
            }
            Coord world_coord = getWorldCoord(current.x, current.y);
            Coord pixel_coord = getPixelCoord(world_coord.x, world_coord.y);
            pixel_coord.y += 600;
            world_coord.y = pixelToWorld((int) pixel_coord.y);
            current.x = worldYToLong(world_coord.y);
            current.y = top_left.y;
            y++;
            x = 1;
        }

        return result;
    }

    private static final int TILE_SIZE = 256;
    private static int scale;

    private static Coord getWorldCoord(double lat, double lng) {
        double siny = Math.sin(lat * Math.PI / 180);

        // Truncating to 0.9999 effectively limits latitude to 89.189. This is
        // about a third of a tile past the edge of the world tile.
        siny = Math.min(Math.max(siny, -0.9999), 0.9999);

        return new Coord
                (TILE_SIZE * (0.5 + lng / 360),
                        TILE_SIZE * (0.5 - Math.log((1 + siny) / (1 - siny)) / (4 * Math.PI)));
    }


    private static Coord getPixelCoord(double x, double y) {
        return new Coord(Math.floor(x * scale), Math.floor(y * scale));
    }


    private static double pixelToWorld(int coord) {
        return coord * 1.0 / scale;
    }


    private static double worldXToLng(double x) {
        return (x / 256 - 0.5) * 360;
    }

    private static double worldYToLong(double y) {
        int count = 0;
        double left = -90, right = 90;
        while (count < 100) {
            count++;
            double middle = (left + right) / 2.0;
            double result = getWorldCoord(middle, 0).y;
            if (result < y) {
                right = middle;
            } else {
                left = middle;
            }
        }
        return (left + right) / 2.0;
    }


    static class Coord {
        double x;
        double y;

        Coord(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

}
