package ru.tinkoff.edu;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Fifth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line1 = scanner.nextLine().split(" ");
        int row = Integer.parseInt(line1[0]);
        int col = Integer.parseInt(line1[1]);
        int num = Integer.parseInt(line1[2]);
        int[] samplesLengths = new int[num];
        for (int i = 0; i < num; i++) {
            samplesLengths[i] = Integer.parseInt(scanner.nextLine());
        }
        int[] disk = new int[row];
        for (int i = 0; i < row; i++) {
            disk[i] = col;
        }
        int[][] status = new int[row][col];
        Map<Integer, Integer> tracks = new TreeMap<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < num; j++) {
                if (!tracks.containsKey(j) && samplesLengths[j] <= disk[i]) {
                    disk[i] -= samplesLengths[j];
                    tracks.put(j, i + 1);
                }
            }
        }
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < num; i++) {
            if (!tracks.containsKey(i)) {
                out.append(-1).append(System.lineSeparator());
            } else {
                out.append(tracks.get(i)).append(System.lineSeparator());
            }
        }
        System.out.print(out);
    }
}
