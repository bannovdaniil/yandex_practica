package ru.yandex.cars;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sber {

    public static int getResult(List<Integer> passersby) {
// понял что ничего не понял,
// примерно понял так, сортируем, после чего
// массив делим пополам, первая половина в плюс (богатые), вторая в минус(бедные).
// если число элементов нечетное тогда центрального не трогаем
        List<Integer> coins = new ArrayList<>(passersby);
        coins.sort(Comparator.reverseOrder());
        int right = coins.size()-1;
        int left = 0;
        int loot = 0;
        while (left < right) {
            loot += coins.get(left++);
            loot -= coins.get(right--);
        }
        return loot;
    }

    public static void main(String[] args) {
        System.out.println(getResult(List.of(3, 10, 6, 4, 8)));
    }
}
