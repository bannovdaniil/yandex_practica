package ru.yandex.cars;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sber {

    public static int getResult(List<String> scheme) {
        //       return left * 1000 + right * 100 + top * 10 + down;
        Map<Integer, Integer> varios = new HashMap<>();
        varios.put(0, 0);
        varios.put(1, 20);
        varios.put(10, 20);
        varios.put(11, 20);
        varios.put(100, 21);
        varios.put(101, 17);
        varios.put(110, 15);
        varios.put(111, 40);
        varios.put(1000, 21);
        varios.put(1001, 10);
        varios.put(1010, 13);
        varios.put(1011, 31);
        varios.put(1100, 21);
        varios.put(1101, 32);
        varios.put(1110, 29);
        varios.put(1111, 63);

        int result = 0;
        char[][] array = new char[scheme.size()][];
        for (int i = 0; i < scheme.size(); i++) {
            String[] koleno = scheme.get(i).split("-");
            array[i] = new char[koleno.length];
            for (int j = 0; j < koleno.length; j++) {
                array[i][j] = koleno[j].charAt(0);
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
   //             System.out.println(array[i][j]);
                int count = countAll(i, j, array);
                if (count > 0) {
//                    System.out.println(count);
//                    count= countAll(i, j, array);
                    result += varios.get(count);
                }
            }
        }
        return result;
    }

    private static int countAll(int x, int y, char[][] array) {
        if (array[x][y] == '0') {
            return -1;
        }
        int left = 1;
        int right = 1;
        int top = 1;
        int down = 1;
        if (x != 0) {
            top = array[x - 1][y] == '1' ? 1:0;
        }
        if (x != array.length - 1) {
            down = array[x + 1][y] == '1' ? 1:0;
        }
        if (y != 0) {
            left = array[x][y - 1] == '1' ? 1:0;
        }
        if (y != array[x].length - 1) {
            right = array[x][y + 1] == '1' ? 1:0;
        }

        return left * 1000 + right * 100 + top * 10 + down;
    }

    public static void main(String[] args) {
        System.out.println(getResult(List.of(
                "1-0-0-0-0-0-0-0",
                "0-0-0-0-0-0-0-0",
                "0-0-0-0-0-0-0-0",
                "0-0-0-0-0-0-0-0",
                "0-0-0-0-0-0-0-0",
                "0-0-0-0-0-0-0-0",
                "0-0-0-0-0-0-0-0"
        )));
    }
}
