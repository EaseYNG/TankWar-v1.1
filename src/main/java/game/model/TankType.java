package main.java.game.model;

public enum TankType {
    HEAVY(300,  1), // 0
    MEDIUM(225,  2), // 1
    LIGHT(150,  3); // 2

    private final int health;
    private final int speed;

    TankType(int health, int speed) {
        this.health = health;
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }
}
