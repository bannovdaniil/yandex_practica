package ru.tinkoff.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Tinkoff {
    public static void main(String[] args) {
        List<String> numbers = new ArrayList(Arrays.asList("first", "second", "third"));
        for (String number : numbers) {
            if ("third".equals(number)) {
                numbers.add("fourth");
            }
        }
        System.out.println(numbers.size());
    }
}
