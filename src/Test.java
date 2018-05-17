
/*
 * Created by david on 4/3/2018.
 * Copyright ISOTOPE Studio
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        String s = in.nextLine();
        while (!" ".equals(s)) {
            list.add(s);
            s = in.nextLine();
        }
        list.stream()
                .map(s1 -> s1.indexOf("/") > 0 ? s1.substring(0, s1.indexOf("/")) : s1)
                .forEach(System.out::println);
    }

}
