package advance.lambda;
/*
 * Created by david on 2017/3/25.
 * Copyright ISOTOPE Studio
 */

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveStream {

    public static void main(String[] args) {
        IntStream.range(1, 5).forEach(n -> System.out.print(n + " "));
        System.out.println();
        Arrays.stream(IntStream.range(1, 5).toArray()).map(n -> n * n).average().ifPresent(System.out::println);
        Stream.of(1.5, 2.3, 3.7).mapToInt(Double::intValue).forEach(System.out::println);
    }

}
