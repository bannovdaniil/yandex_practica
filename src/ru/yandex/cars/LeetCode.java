package ru.yandex.cars;

public class LeetCode {
    public static int strStr(String haystack, String needle) {
        if(haystack.equals(needle)) return 0;
        int size = haystack.length();
        int size2 = needle.length();
        if (size2 <= size && size2 != 0) {
            int j;
            for (int i = 0; i <= size - needle.length(); i++) {
                if (haystack.charAt(i) == needle.charAt(0)) {
                    int pos = i;
                    for (j = 1; j < size2; j++) {
                        if (haystack.charAt(++pos) != needle.charAt(j)) {
                            break;
                        }
                    }
                    if (j == size2) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr("", ""));
    }
}
