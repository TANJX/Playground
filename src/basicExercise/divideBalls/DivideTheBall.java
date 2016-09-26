package basicExercise.divideBalls;
/*
 * Created by Mars on 7/3/2016.
 * Copyright ISOTOPE Studio
 */
public class DivideTheBall {

    public static void main(String args[]) {
        System.out.println("Start");
        double result = 0;
        for (double black1 = 0; black1 <= 50; black1++) {
            for (double white1 = 0; white1 <= 50; white1++) {
                double black2 = 50 - black1;
                double white2 = 50 - white1;
                if (black1 + white1 == 0 || black2 + white2 == 0) continue;
                double p1 = black1 / (black1 + white1);
                double p2 = black2 / (black2 + white2);
                double temp = (p1 + p2) / 2.0;
                if (temp > result)
                    result = temp;
                System.out.println(white1 + " " + black1 + " " + white2 + " " + black2 + " " + temp);
            }
        }
        System.out.print(result);
    }
}
