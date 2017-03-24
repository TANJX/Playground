package advance.lambda;
/*
 * Created by Mars Tan on 2/8/2017.
 * Copyright ISOTOPE Studio
 */

import java.util.Date;

public class LambdaDemo {

    public static void main(String[] args) {
        Action1 a1 = s -> System.out.println(s + "!");
        Action1 directPrint = System.out::println;
        Action2 date = () -> System.out.println(new Date());
        a1.out("Mars");
        date.out();
    }

    @FunctionalInterface
    private interface Action1 {
        void out(String s);
    }

    @FunctionalInterface
    private interface Action2 {
        void out();
    }
}
