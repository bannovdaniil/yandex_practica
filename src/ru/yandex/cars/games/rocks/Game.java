package ru.yandex.cars.games.rocks;

import java.util.List;
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
        computer.doChoice();
        man.doChoice();
        showMessageEnd();
        showWinner(computer, man);
    }

    /**
     * Оглашаем результат
     */
    private void showWinner(Player computer, Player man) {
        int userSelect = man.getSelect();
        int computerSelect = computer.getSelect();
        StringBuilder message = new StringBuilder("Победил ");
        switch (getWin(computerSelect, userSelect)) {
            case (1):
                message.append(computer.name + ": ");
                message.append(getWinMessage(computerSelect, userSelect));
                break;
            case (2):
                message.append(man.name + ": ");
                message.append(getWinMessage(userSelect, computerSelect));
                break;
            default:
                message = new StringBuilder("Ничья");
        }
        message.insert(0, System.lineSeparator());
        System.out.println(message);
    }

    /**
     * если true тогда выиграл компьютер = 1, иначе человек =2
     *
     * @param sComputer - выбор компьютера
     * @param uSelect   - выбор человека
     * @return - 0- ничья, 1- победил компьютер, 0 - победил человек
     */
    private int getWin(int sComputer, int uSelect) {
        if (sComputer == uSelect)
            return 0;
        int result = (sComputer == 2 && uSelect == 0)
                || (sComputer == 0 && uSelect == 1)
                || (sComputer == 1 && uSelect == 2)
                ? 1 : 2;
        return result;
    }

    /**
     * надо же объяснить, почему произошла победа.
     *
     * @param sComputer - первый игрок
     * @param uSelect   - второй игрок
     * @return - сообщение с раскладом
     */
    private String getWinMessage(int sComputer, int uSelect) {
        String message = "";
        if (sComputer == 2 && uSelect == 0) {
            message = "Бумага побеждает камень («бумага обёртывает камень»).";
        } else if (sComputer == 0 && uSelect == 1) {
            message = "Камень побеждает ножницы («камень затупляет или ломает ножницы»).";
        } else if (sComputer == 1 && uSelect == 2) {
            message = "Ножницы побеждают бумагу («ножницы разрезают бумагу»).";
        }
        return message;
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
