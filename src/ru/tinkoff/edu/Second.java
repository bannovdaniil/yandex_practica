package ru.tinkoff.edu;

import java.util.Scanner;

public class Second {
    static void result(String s1, String s2, String yes) {
        String[] line1 = s1.split(" ");
        String line2 = s2;
        int numServers = Integer.parseInt(line1[0]);
        int round = Integer.parseInt(line1[1]);
        boolean okServer = false;
        int j = 0;
        int testServer = 0;
        while (j < numServers) {
            okServer = false;
            for (int i = 0; i < round; i++) {
                testServer = j + i;
                if (testServer > numServers || line2.charAt(testServer) == '1') {
                    testServer = i;
                    okServer = true;
                    break;
                }
            }
            if (!okServer) {
                break;
            }
            j += testServer + 1;
        }
        if (okServer) {
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line1 = scanner.nextLine().split(" ");;
        String line2 = scanner.nextLine();

        result("2 1", "11", "YES");
        result("5 2", "10101", "YES");
        result("8 4", "11000011", "NO");

    }

}
