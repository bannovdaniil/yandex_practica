package ru.tinkoff.edu;
//

import java.util.Scanner;

public class Fourth {
    public static int gcd(int n1, int n2) {
        return n2 == 0 ? n1 : gcd(n2, n1 % n2);
    }

    public static void main(String[] args) {
        int count;
        Scanner scanner = new Scanner(System.in);
        count = Integer.parseInt(scanner.nextLine());
        StringBuilder out = new StringBuilder();
        while (count-- > 0) {
            int length = Integer.parseInt(scanner.nextLine());
            int[] stream = new int[length];
            int mi = 0;
            String[] lineNum = scanner.nextLine().split(" ");
            for (int i = 0; i < length; i++) {
                stream[i] = Integer.parseInt(lineNum[i]);
                mi = (stream[i] > stream[mi] ? i : mi);
            }
            int[] b = new int[length];
            int cg = stream[mi];
            b[0] = cg;
            out.append(b[0]).append(" ");
            stream[mi] = 0;
            for (int i = 1; i < length; i++) {
                int ci = 0;
                int cans = 0;
                for (int j = 0; j < length; j++) {
                    int dc = gcd(stream[j], cg);
                    if (stream[j] > 0 && dc > cans) {
                        cans = dc;
                        ci = j;
                    }
                }
                b[i] = stream[ci];
                out.append(b[i]);
                if (i != length - 1) {
                    out.append(" ");
                }
                cg = cans;
                stream[ci] = 0;
            }
            out.append(System.lineSeparator());
        }
        System.out.print(out.toString());
    }
}