package basicExercise.pi;/*
 * Created by Mars Tan on 11/21/2016.
 * Copyright ISOTOPE Studio
 */

public class Main {

    private static double estimatePi(long sides) {
        double degree = 180.0 / sides;
        return sides * Math.sin(degree / 180 * Math.PI);
    }

    public static void main(String args[]) {
        for (int i = 100; i <= 10000; i+=100) {
            System.out.println(estimatePi(i));
        }
    }
}
