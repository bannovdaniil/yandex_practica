package ru.yandex.cars.games.rocks;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

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
    private static final List<String> ITEMS_NAME = List.of("Камень @", "Ножницы 8<", "Бумага []");
    private static final int WAIT = 1;
    private static final Map<Integer, String> HELP_MESSAGE = Map.of(
            0, "Камень побеждает ножницы («камень затупляет или ломает ножницы»).",
            1, "Ножницы побеждают бумагу («ножницы разрезают бумагу»).",
            2, "Бумага побеждает камень («бумага обёртывает камень»)."
    );

    private enum ITEMS {STONE, PAPER, SCISSOR}

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    /**
     * тут происходит вся логика игры
     */
    public void play() {
        Player computer = new Player("Random", "AI");
        //        Player man = new Player("Человек", "human");
        Player man = new Player("Random", "AI");
        Round round = new Round(man, computer);
        String nextRound = "y";
        try (Scanner scanner = new Scanner(System.in)) {
            while ("y".equalsIgnoreCase(nextRound)) {
                showMessageStart();
                round.getWinner();
                System.out.println(round.message);
                round.showStatistic();
                nextRound = "";
                while (!List.of("y", "n").contains(nextRound.toLowerCase())) {
                    System.out.println("-".repeat(17) + System.lineSeparator() + "Еще разок? (y/n):");
                    nextRound = scanner.next();
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    /**
     * Детская, Присказка, до выбора
     */
    private void showMessageStart() {
        showMessageWithTimer(
                String.format("Камень, Ножницы, Бумага, ...%n "
                        + "Карандаш, огонь, вода, ...%n "
                        + "и бутылка лимонада и железная рука ...%n "
                        + "цу -ефа, цу -ефа...%n%n"));
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

    /**
     * каждый раунд
     */
    private class Round {
        private final Player p1;
        private final Player p2;
        private String message;
        private int roundCount;

        public Round(Player p1, Player p2) {
            this.roundCount = 0;
            this.p1 = p1;
            this.p2 = p2;
        }

        /**
         * Выбрать победителя.
         */
        public void getWinner() {
            roundCount++;
            p1.doChoice();
            p2.doChoice();
            var p1Select = ITEMS.values()[p1.getSelect()];
            var p2Select = ITEMS.values()[p2.getSelect()];
            if (p2Select == p1Select) {
                this.message = "> Ничья <";
                return;
            }

            Player winner = (p2Select == ITEMS.SCISSOR && p1Select == ITEMS.STONE)
                    || (p2Select == ITEMS.STONE && p1Select == ITEMS.PAPER)
                    || (p2Select == ITEMS.PAPER && p1Select == ITEMS.SCISSOR)
                    ? p2 : p1;
            winner.winCount++;
            this.message = String.format("%n> Победитель: %s, %s",
                    winner.name,
                    HELP_MESSAGE.get(winner.getSelect()));
        }

        /**
         * Статистика по играм
         */
        public void showStatistic() {
            System.out.printf("%nСтатистика:%n Раунд: %d%n %s VS %s%n %d:%d%n%n",
                    roundCount,
                    p1.name,
                    p2.name,
                    p1.winCount,
                    p2.winCount);
        }

    }

    /**
     * Модель игрок
     */
    private class Player {
        private int winCount;
        private int select;
        private String name;
        private final String[] ROBOT_NAME = new String[]{"Петсон", "Зока", "Коломбо", "Рембо", "Алиса"};
        private final boolean type;

        /**
         * @param name - имя
         * @param seed - AI или human
         */
        public Player(String name, String seed) {
            this.winCount = 0;
            this.name = name;
            if ("AI".equals(seed)) {
                this.type = true;
                if ("Random".equals(name)) {
                    Random random = new Random();
                    this.name = ROBOT_NAME[random.nextInt(ROBOT_NAME.length)];
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
                this.select = random.nextInt(3);
            } else {
                this.select = getUserSelect();
            }
            showSelect();
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
                for (int i = 0; i < ITEMS_NAME.size(); i++) {
                    System.out.printf("%d - %s%n", i + 1, ITEMS_NAME.get(i));
                }
                System.out.println("Делайте свой выбор(1-"
                        + ITEMS_NAME.size() + "): ");
                select = scanner.next();
            } while (!num.contains(select));
            return Integer.parseInt(select) - 1;
        }

        /**
         * @return - номер вещи
         */
        public int getSelect() {
            return select;
        }

        /**
         * показать выбор.
         */
        public void showSelect() {
            System.out.println(name + " выбрал: " + getItem(select));
        }

        /**
         * показать выбранную вещь.
         *
         * @param i - цифра
         * @return - вещь
         */
        private String getItem(int i) {
            if (i >= 0 && i < ITEMS_NAME.size()) {
                return ITEMS_NAME.get(i);
            }
            return "";
        }
    }
}
