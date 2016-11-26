package basic.xml;/*
 * Created by Mars Tan on 11/26/2016.
 * Copyright ISOTOPE Studio
 */

import java.util.Scanner;

public class Main {
    public static void main(String useless[]) {
        DOMXML xml = new DOMXML();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
//            String[] args = line.split(" ");
//            xml.addRecord(args[0], Integer.parseInt(args[1]));
//            System.out.println("added");
            System.out.println(xml.getAgeByName(line));
        }
    }
}
