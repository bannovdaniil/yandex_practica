package ru.tinkoff.edu;

public class TestPrint {
    public static void main(String[] args) {
        print(null);
        print(1);
        print("string");
        print(0.2d);
        print(false);
        print('A');
    }

    static void print(int i) {
        System.out.println("i." + i);
    }


    static void print(boolean b) {
        System.out.println("b." + b);
    }

    static void print(Object o) {
        System.out.println("o." + o);
    }

    static void print(String s) {
        System.out.println("s." + s);
    }

}
