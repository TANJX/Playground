
/*
 * Created by david on 5/7/2018.
 * Copyright ISOTOPE Studio
 */

import java.util.Random;

public class RandomGrass {

    private static final long TRAILS = 100000000;
    private static int TIMES = 1;
    private static double sum = 0;
    private static int i = 0;
    private static boolean complete = false;

    public static void main(String[] args) throws InterruptedException {
        Runnable run = () -> {
            Random r = new Random();
            for (i = 0; i < TRAILS; i++) {
                int max = 0;
                for (int j = 0; j < TIMES; j++) {
                    int temp = r.nextInt((30 - 5) + 1) + 5;
                    if (temp > max) max = temp;
                }
                sum += max;
            }
            complete = true;
        };
        for (int i1 = 0; i1 < 20; i1++) {
            Thread thread = new Thread(run);
            thread.start();
            while (!complete) {
                Thread.sleep(1000);
//                System.out.print(100.0 * i / TRAILS);
//                System.out.println("%");
            }
            System.out.print(TIMES + ": ");
            System.out.println(sum / TRAILS);
            complete = false;
            TIMES++;
            sum = 0;
        }

    }


}
