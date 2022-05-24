package ru.tinkoff.edu;

public class WriteRle {
    public static void main(String[] args) {
        String rle = "5.1^4.5_1I3#1_5_1I2#2_5_1I3#1_5_1I4_1\\8=1I1_1\\7=1I2_1\\6=1I9~";
        int rowBreak = 0;
        int count = 0;
        for (int i = 0; i < rle.length(); i++) {
            var ch = rle.charAt(i);
            if (ch >= '0' && ch <= '9') {
                count = Integer.parseInt(String.valueOf(ch));
                continue;
            } else {
                for (int j = 0; j < count; j++) {
                    if (rowBreak == 10) {
                        System.out.println("");
                        rowBreak = 0;
                    }
                    rowBreak++;
                    System.out.print(ch);
                }
            }
        }
    }
}
