package ru.yandex.autogen;

public class Ghost {
    private double age;
    private boolean isFly;

    public Ghost(double age, boolean isFly) {
        this.age = age;
        this.isFly = isFly;
    }

    public Ghost() {
    }

    public String say(){
        return "Бу!";
    }
}

class GhostShip extends Ghost {

}