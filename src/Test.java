
/*
 * Created by david on 4/3/2018.
 * Copyright ISOTOPE Studio
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

    public static void main(String args[]) {
        for (int i = 17; i <= 32; i++) {
            for (int j = 1; j <= 3; j++) {
                System.out.println(i + "-" + j);
            }
            if (i % 4 == 0)
                System.out.println("Test " + (i / 4));
        }
    }

}
