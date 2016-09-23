package basicExercise.array;/*
 * Created by Mars on 9/22/2016.
 * Copyright ISOTOPE Studio
 */

public class Remove {

    private static void print(int[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("]");
    }

    public static void main(String args[]) {
        int a[] = {1, 1, 1, 1, 1, 1, 1};
        int n = 0;
        int count = 0;
        while (count < a.length - 1) {
            int i = 0;
            while (i < 2) {
                n++;
                n %= a.length;
                if (a[n] == 1) {
                    i++;
                }
            }
            a[n] = 0;
            count++;
            print(a);
        }
    }
}
