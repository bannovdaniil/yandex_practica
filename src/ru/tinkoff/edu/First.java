package ru.tinkoff.edu;

import java.util.Scanner;

public class First {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        StringBuilder out = new StringBuilder();
        int max = n;
        String first = "X";
        String second = "Y";
        if (n < m) {
            max = n;
            n = m;
            m = max;
            first = "Y";
            second = "X";
        }
        max = n / m;
        for (int i = 0; i < n; i++) {
            out.append(first);
            if (i % max == 0) {
                out.append(second);
            }
        }
        System.out.println(out);

    }
}
