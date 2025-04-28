package main.java.game.model;

public enum TankType {
    HEAVY(300, 100, 2), // 0
    MEDIUM(225, 75, 3), // 1
    LIGHT(150, 50, 4); // 2

    private final int health;
    private final int attack;
    private final int speed;

    TankType(int health, int attack, int speed) {
        this.health = health;
        this.attack = attack;
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }
    public int getAttack() {
        return attack;
    }
    public int getSpeed() {
        return speed;
    }
}
