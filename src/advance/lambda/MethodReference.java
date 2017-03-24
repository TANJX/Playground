package advance.lambda;
/*
 * Created by Mars Tan on 3/24/2017.
 * Copyright ISOTOPE Studio
 */

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;

public class MethodReference {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        IntFunction<String> intToString1 = num -> {
            num++;
            return Integer.toString(num);
        };
        IntFunction<String> intToString2 = num -> Integer.toString(num);
        IntFunction<String> intToString3 = Integer::toString;
        Function<String, Integer> stringToInt = Integer::parseInt;
        Consumer<Object> print = System.out::println;
        print.accept(intToString3.apply(100));
        UnaryOperator<String> makeGreeting = "Hello, "::concat;
        UnaryOperator<String> makeGreeting2 = s -> "Hello, ".concat(s);
        print.accept(makeGreeting.apply("Mars"));
    }

}
