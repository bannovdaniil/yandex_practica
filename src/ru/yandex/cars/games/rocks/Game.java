package ru.yandex.cars.games.rocks;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.SplittableRandom;

/**
 * игра Камень, Ножницы, Бумага,
 * Карандаш, огонь, вода,
 * и бутылка лимонада и железная рука
 * цуефа, цуефа...
 * Самогон, Нога, Трусы
 * <p>
 * Правила:
 * Победитель определяется по следующим правилам:
 * <p>
 * 2 - Бумага побеждает камень («бумага обёртывает камень»).
 * 0 - Камень побеждает ножницы («камень затупляет или ломает ножницы»).
 * 1 - Ножницы побеждают бумагу («ножницы разрезают бумагу»).
 * ITEMS - набор принадлежностей
 * WAIT - задержка при показе слов.
 */
public class Game {
    private static final List<String> ITEMS = List.of("Камень @", "Ножницы 8<", "Бумага []");
    private static int WAIT = 20;

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    /**
     * тут происходит вся логика игры
     */
    public void play() {
        showMessageStart();
        Player computer = new Player("Random", "AI");
        Player man = new Player("Человек", "human");
        Round round = new Round(computer, man);
        showMessageEnd();
        round.getWinner();
        System.out.println(round.message);
    }

    /**
     * Детская, Присказка, до выбора
     */
    private void showMessageStart() {
        showMessageWithTimer(
                String.format("Камень, Ножницы, Бумага, ...%n "
                        + "Карандаш, огонь, вода, ...%n "
                        + "и бутылка лимонада и железная рука ...%n%n "));
    }

    /**
     * после выбора...
     */
    private void showMessageEnd() {
        showMessageWithTimer(
                String.format("цу -ефа, цу -ефа...%n%n"));
    }

    /**
     * небольшой экшен, вывод слов с задержкой, эмитация жизни
     *
     * @param message - сообщение
     */
    private void showMessageWithTimer(String message) {
        try {
            for (String word : message.split(" ")) {
                System.out.print(word);
                Thread.sleep(word.length() * WAIT);
                if (word.contains("...")) {
                    Thread.sleep(WAIT * 10);
                } else {
                    System.out.print(" ");
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    class Round {
        private Player p1;
        private Player p2;
        private String message;

        public Round(Player p1, Player p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        /**
         * Вернуть победителя.
         *
         * @return - победитель
         */
        public Player getWinner() {
            p1.doChoice();
            p2.doChoice();
            int p1Select = p1.getSelect();
            int p2Select = p2.getSelect();
            if (p2Select == p1Select) {
                this.message = "Ничья";
                return null;
            }
            Player winner = (p2Select == 2 && p1Select == 0)
                    || (p2Select == 0 && p1Select == 1)
                    || (p2Select == 1 && p1Select == 2)
                    ? p1 : p2;
            StringBuilder message = new StringBuilder("Победил ");
            message.append(winner.name + ": ");
            switch (winner.getSelect()) {
                case (0):
                    message.append("Камень побеждает ножницы («камень затупляет или ломает ножницы»).");
                    break;
                case (1):
                    message.append("Ножницы побеждают бумагу («ножницы разрезают бумагу»).");
                    break;
                default:
                    message.append("Бумага побеждает камень («бумага обёртывает камень»).");
            }
            this.message = message.toString();
            return winner;
        }

    }

    /**
     * Модель игрок
     */
    class Player {
        private int select;
        private String name;
        private String[] ROBOT_NAME = new String[]{"Петсон", "Зока", "Коломбо", "Рембо", "Алиса"};
        private boolean type;

        /**
         * @param name - имя
         * @param seed - AI или human
         */
        public Player(String name, String seed) {
            this.name = name;
            if ("AI".equals(seed)) {
                this.type = true;
                if ("Random".equals(name)) {
                    Random random = new Random();
                    this.name = ROBOT_NAME[random.nextInt(ROBOT_NAME.length - 1)];
                }
            } else {
                this.type = false;
            }
        }

        /**
         * Игрок делает выбор
         */
        public void doChoice() {
            if (type) {
                Random random = new Random();
                this.select = random.nextInt(2);
            } else {
                this.select = getUserSelect();
            }
        }

        /**
         * пользователь делает выбор
         *
         * @return - 0 | 1 | 2
         */
        private int getUserSelect() {
            List<String> num = List.of("1", "2", "3");
            Scanner scanner = new Scanner(System.in);
            String select;
            do {
                for (int i = 0; i < ITEMS.size(); i++) {
                    System.out.println(String.format("%d - %s", i + 1, ITEMS.get(i)));
                }
                System.out.println("Делайте свой выбор(1-"
                        + ITEMS.size() + "): ");
                select = scanner.next();
            } while (!num.contains(select));
            return Integer.parseInt(select) - 1;
        }

        /**
         * показать, что выбрали.
         *
         * @return - номер вещи
         */
        public int getSelect() {
            System.out.println(name + " выбрал: " + getItem(select));
            return select;
        }

        /**
         * показать выбранную вещь.
         *
         * @param i - цифра
         * @return - вещь
         */
        private String getItem(int i) {
            if (i >= 0 && i < ITEMS.size()) {
                return ITEMS.get(i);
            }
            return "";
        }
    }
}
