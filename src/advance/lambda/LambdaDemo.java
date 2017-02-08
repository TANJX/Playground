package advance.lambda;
/*
 * Created by Mars Tan on 2/8/2017.
 * Copyright ISOTOPE Studio
 */

import java.util.Date;

public class LambdaDemo {

    public static void main(String[] args) {
        test1(s -> System.out.println(s + "!"));
        test1(s -> {
            System.out.println(s + "!");
            System.out.println("Great!");
        });
        test2(() -> {
            System.out.println(new Date().toString());
        });
    }

    private interface Action1 {
        void out(String s);
    }

    private interface Action2 {
        void out();
    }

    private static void test1(Action1 action) {
        action.out("WOW");
    }

    private static void test2(Action2 action) {
        action.out();
    }
}
