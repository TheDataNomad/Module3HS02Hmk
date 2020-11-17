package space.harbour.java.hm7;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Functional {

    private static final long MEASURE_COUNT = 10;

    public static void main(String[] args) {
        //// p to print String
        Consumer<String> p = System.out::println;

        IntToLongFunction sqr2 = x -> x * x;
        Function<Integer, Long> sqr = x -> Long.valueOf(x * x);

        p.accept(String.valueOf(sqr.apply(5)));

        Function<String, Integer> l = String::length;

        System.out.println(sqr.apply(l.apply("hello")));

        p.accept(String.valueOf(sqr.apply(l.apply("Hello"))));

        Function<Integer, String[]> stringArr = String[]::new;
        String[] s1 = stringArr.apply(7);
        System.out.println(s1.toString());

        "Hello world!".chars().forEach(x -> System.out.print((char) x));

        System.out.println("\n * ** * * ** map filter reduce  ** * * *  *");

        List<String> stringList = List.of("abc", "def", "ghi", "harbour");
        List<String> longStrings = stringList.stream()
                .filter(s -> s.length() > 3)
                .collect(Collectors.toList());

        longStrings.stream().forEach(p);

        // Returning a list of the lengths of each string
        List<Integer> lengths = stringList.stream()
                .map(s -> s.length())
                .collect(Collectors.toList());

        /// Print for priting Integers
        Consumer<Integer> printInt = x -> System.out.println(String.valueOf(x));
        lengths.stream().forEach(printInt);

        List<String> firstLetters = stringList.stream()
                .map(s -> s.substring(0, 1))
                .collect(Collectors.toList());

        firstLetters.stream().forEach(p);


        ////// PRACTICE STREAMS
        List<String> stringListHeat = List.of("The heat", "heatmap", "ahmed", "harbour");
        List<String> containsHeat = stringListHeat.stream()
                .filter(s -> s.contains("heat"))
                .collect(Collectors.toList());

        containsHeat.stream().forEach(p);

        List<String> stringList01 = List.of("The heat", "map", "ahmed", "harbaoeur");
        boolean containsAoeu = stringList01.stream()
                .anyMatch(s -> s.contains("aoeu"));

        p.accept(String.valueOf(containsAoeu));
        System.out.println(containsAoeu);

        calcTime(() -> {
            LongStream.range(0, Integer.MAX_VALUE).parallel().sum();
        });


        calcTime(() -> {
            long sumValues = 0;
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sumValues = sumValues + i;
            }
        });

    }

    public static void calcTime(Runnable r) {
        long startTime = System.nanoTime();
        r.run();
        for (int i = 0; i < MEASURE_COUNT; i++) {
            r.run();
        }
        long finishTime = System.nanoTime();
        long timeNano = finishTime - startTime;
        System.out.println(String.format("Time %d ns (%d ms)",
                timeNano / MEASURE_COUNT,
                timeNano / 1_000_000 / MEASURE_COUNT));
    }
}
