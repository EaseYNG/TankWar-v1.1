package main.java.game.model;

public enum Direction {
    UP(0), DOWN(2), LEFT(3), RIGHT(1); // UP - 0 DOWN - 1 LEFT - 2 RIGHT - 3

    public int dir;

    Direction(int dir) {
        this.dir = dir;
    }

}
