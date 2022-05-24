package ru.tinkoff.edu;

import java.util.Scanner;

public class Third {

    static void result(String s1, String s2, String yes) {
        String[] line1 = s1.split(" ");
        String line2 = s2;
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String line1 = scanner.nextLine();
//        int num = Integer.parseInt(line1);
//        String[] stack = new String[num];
//        for (int i = 0; i < num; i++) {
//            stack[i] = scanner.nextLine();
//        }

        int num = Integer.parseInt("5");
        String[] stack = new String[num];
        stack[0] = "5 10 15";
        stack[1] = "2 10 15";
        stack[2] = "5 5 5";
        stack[3] = "20 20 1";
        stack[4] = "20 1 1";

//        stack[0] = "10 15 14";
//        stack[1] = "5 1 1";
//        stack[2] = "1 1 1";
        //////////////////////
        int[] a = new int[num];
        int[] b = new int[num];
        int[] c = new int[num];
        for (int i = 0; i < num; i++) {
            String[] abc = stack[i].split(" ");
            a[i] = Integer.parseInt(abc[0]);
            b[i] = Integer.parseInt(abc[1]);
            c[i] = Integer.parseInt(abc[2]);
        }

                int minTime = a[0];
                int[] time = new int[num * 2];
                time[0] = 0;
                time[1] = a[0];
                if (num > 1) {
                    time[2] = Math.min(a[0] + a[1], b[0]);
                    minTime = time[2];
                }
                if (num > 2) {
                    time[3] = Math.min(Math.min(time[2] + a[2], time[1] + b[1]), c[0]);
                    minTime = time[3];
                }

                for (int i = 3; i < num; i++) {
                    time[i] = Math.min(
                            Math.min(time[i - 1] + a[i - 1], time[i - 2] + b[i - 2]),
                            time[i - 3] + c[i - 3]);
                    minTime = time[i];
                }
                System.out.print(minTime);

    }
}
