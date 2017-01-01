/**
 *
 */
package app.percentAnalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Mars
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] types = {"A", "B", "C", "D", "E", "F"};
        List<String> senior1 = new ArrayList<>();
        List<String> senior2 = new ArrayList<>();
        List<String> senior3 = new ArrayList<>();
        System.out.println("INPUT SENIOR I");
        String temp = sc.next();
        while (!temp.equals("end")) {
            senior1.add(temp);
            temp = sc.next();
        }
        System.out.println("INPUT SENIOR II");
        temp = sc.next();
        while (!temp.equals("end")) {
            senior2.add(temp);
            temp = sc.next();
        }
        System.out.println("INPUT SENIOR III");
        temp = sc.next();
        while (!temp.equals("end")) {
            senior3.add(temp);
            temp = sc.next();
        }
        for (String type : types) {
            System.out.print("," + type);
        }
        System.out.println();
        System.out.print("Senior 1");
        for (String type : types) {
            double count = 0;
            for (String data : senior1) {
                if (type.equals(data))
                    count++;
            }
            System.out.print("," + count / senior1.size());
        }
        System.out.println();
        System.out.print("Senior 2");
        for (String type : types) {
            double count = 0;
            for (String data : senior2) {
                if (type.equals(data))
                    count++;
            }
            System.out.print("," + count / senior2.size());
        }
        System.out.println();
        System.out.print("Senior 3");
        for (String type : types) {
            double count = 0;
            for (String data : senior3) {
                if (type.equals(data))
                    count++;
            }
            System.out.print("," + count / senior3.size());
        }
    }

}
