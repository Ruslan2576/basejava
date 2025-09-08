package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TwoMethods {
    public static void main(String[] args) {
        System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 3}));
        System.out.println(oddOrEven(new ArrayList<>(Arrays.asList(2, 3, 3, 2, 3))));
    }

    static int minValue(int[] values) {
        return IntStream.of(values)
                .distinct()
                .sorted()
                .reduce(0, (acc, x) -> acc * 10 + x);
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
//        boolean isOdd = integers.stream()
//                .mapToInt(Integer::intValue)
//                .sum() % 2 != 0;
//
//        return integers.stream()
//              .filter(num -> isOdd == (num % 2 != 0))
        //      .collect(Collectors.toList());

        return integers.stream()
                .collect(Collectors.collectingAndThen(Collectors.summingInt(Integer::intValue),
                        sum -> integers.stream()
                                .filter(num -> (sum % 2 != 0) == (num % 2 != 0))
                                .collect(Collectors.toList())));
    }
}
