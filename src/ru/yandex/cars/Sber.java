package ru.yandex.cars;

import java.util.ArrayList;
import java.util.List;

public class Sber {

    public static boolean getResult(List<Integer> fences) {
        // найти элементы сумма которых будет равна size()-1
        // сегодня хороший день сделать брутфорс без рекурсии
        // и без повторяющихся символов... погнали!
        // выкинуть все 0 т.к. процесс ускорится и толку с них ноль
        int sizeFence = fences.size();
        List<Integer> nonZerro = new ArrayList<>(); // это позиция каждой цифры в строке
        for (var i : fences) {
            if(i != 0){
                nonZerro.add(i);
            }
        }
        int nonZerroSize = nonZerro.size();
        boolean result = false;
        // begin Shuffle
        int t = 1; // допустим это длина нового массива с каждым разом он будет увеличиваться до size.
        List<Integer> pos = new ArrayList<>(); // это позиция каждой цифры в строке
        pos.add(0);

        while (!result) {
            List<Integer> array = new ArrayList<>();
            if (!result) {
                // формируем лист в соответствии с массивом pos
                for (int i = 0; i < t; i++) {
                    // тут просто перебираем все по порядку если нет такой позиции то добавим
                    if (array.size() == t) {
                        array.set(i, nonZerro.get(pos.get(i)));
                    } else {
                        array.add(nonZerro.get(pos.get(i)));
                    }
                }
                // убрать комбинации повторов? это когда позиции в pos не могут быть ==
                boolean dobl = true;
                while (dobl) {
                    // теперь надо массив pos увеличить на +1 прокрутить счетчик
                    for (int i = 0; i < t; i++) {
                        if (pos.get(i) != nonZerroSize - 1) {
                            pos.set(i,pos.get(i)+1);
                            break;
                        } else {
                            pos.set(i,0);
                            if (i == t - 1) {
                                pos.add(0);
                                t++;
                                break;
                            }
                        }
                    }
                    // чекаем на дубли если найдены совпадения прокручиваем счетчик
                    dobl = false;
                    if (t < 2)
                        break;
                    for (int i = 0; i < t && !dobl; i++) {
                        for (int j = i + 1; j < t; j++) {
                            if (pos.get(i) == pos.get(j)) {
                                dobl = true;
                                break;
                            }
                        }
                    }
                    if (t > nonZerroSize) {
                        break;
                    }
                }
            }
            // перебрали все возможные комбинации на выход
            int size = array.size();
            if (array.size() > nonZerroSize) {
                break;
            }
            // end Shuffle
            // check kombination;
            int sum = 0;
            for (int i = 0; i < size; i++) {
                sum += array.get(i);
                // улетел за забор
                if(sum < 0){
                    break;
                }
                if (sum == sizeFence - 1 ) {
                    result = true;
                    break;
                } else if (sum > sizeFence - 1) {
                    break;
                }
            }
            // end check kombination;
            System.out.println(array);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getResult(List.of(2,  1, 0,  0,  0,-4, -2, -1, 1, 1, -1)));
    }
}
