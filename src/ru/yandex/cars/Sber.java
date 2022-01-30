package ru.yandex.cars;

import java.util.ArrayList;
import java.util.List;

public class Sber {

    static class Pair {
        String first;
        String second;

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }
    }

    public static int getResult(List<String> deal) {
// собираем пары
        ArrayList<Pair> links = new ArrayList<>();
        for (var first : deal) {
            if (first.contains("-")) {
                ArrayList<String> path = new ArrayList<>();
                String[] company = first.split("-");
                for (var second : company[1].split("")) {
                    links.add(new Pair(company[0], second));
                }
            } else {
                links.add(new Pair(first, ""));
            }
        }
//        for (var p : links) {
//            System.out.println(p.first + " => " + p.second);
//        }
        int countLinks = 0;
        ArrayList<String> phrases = new ArrayList<>();
        for (int i = 0; i < links.size(); i++) {
            String first = links.get(i).first;
            ArrayList<Integer> path = new ArrayList<>();
            path.add(i);
            countLinks += getPath(path, i, first, links.get(i), links, first, phrases);
            links.set(i, new Pair(first, ""));
        }
        //    System.out.print(countLinks + "- ");
        return countLinks;
    }

    private static int getPath(ArrayList<Integer> index, int start, String first,
                               Pair pair, ArrayList<Pair> links, String path, ArrayList<String> phrases) {
        int inner = 0;
        for (int j = start; j < links.size(); j++) {
            if (index.contains(j)) continue;
            if (links.get(j).first.equals(pair.second)) {
                //
                String phrase = path + links.get(j).first + links.get(j).second;
                if (links.get(j).second.equals(first)) {
                    //      index.add(j);
                    //     System.out.println(j + "=>" + path + links.get(j).first + links.get(j).second);
                    //             System.out.println(index);
                    // удаляем дубляж маршрутов, при этом, не трогаем крайние
                    for (int i = 1; i < phrase.length() - 1; i++) {
                        char ch = phrase.charAt(i);
                        int lastPos = phrase.lastIndexOf(ch);
                        if (phrase.indexOf(ch) != lastPos) {
                            // удаляем последний дубль
                            phrase = phrase.substring(0, lastPos)
                                    + phrase.substring(lastPos + 1);
                            i = 1;
                        }
                    }
                    if (!phrases.contains(phrase)) {
                        phrases.add(phrase);
                        inner++;
                    }
                    // break;
                } else {
                    if (phrase.length() < index.size()) {
                        ArrayList<Integer> cache = new ArrayList<>();
                        for (int i = 0; i < phrase.length() - 1; i++) {
                            cache.add(index.get(i));
                        }
                        index = cache;
                    }
                    index.add(j);
                    //   System.out.println(temp);
                    inner += getPath(index, start, first, links.get(j),
                            links, path + links.get(j).first, phrases);
                }
            }
        }
        return inner;
    }

    public static void main(String[] args) {
//       System.out.println(1 == getResult(List.of("a-b", "b-c", "c-a")));
        //     System.out.println(1 == getResult(List.of("a-b", "b-c", "c-ad", "d")));
        //    System.out.println(0 == getResult(List.of("a-b", "b", "c-b")));
        //       System.out.println(2 == getResult(List.of("a-b", "b-cd", "c-a", "d-c")));
        //     System.out.println(3 == getResult(List.of("a-b", "b-ce", "c-d", "d-e", "e-fc", "f-a")));
        System.out.println(5 == getResult(List.of("a-b", "b-c", "c-da", "d-ea", "e-fa", "f-ac")));
// abcde
// abcdefa
// abcdecd
// abefa
    }
}
