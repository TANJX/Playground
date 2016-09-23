package basicExercise.dateCalculation;/*
 * Created by Mars on 9/23/2016.
 * Copyright ISOTOPE Studio
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入年");
        int year = sc.nextInt();
        System.out.println("输入月");
        int month = sc.nextInt();
        System.out.println("输入日");
        int day = sc.nextInt();
        Age Simon = new Age();
        Simon.Calculuateage(year, month, day);
    }
}
