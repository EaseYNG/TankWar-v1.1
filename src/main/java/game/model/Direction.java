package main.java.game.model;

public enum Direction {
    UP(0), DOWN(2), LEFT(3), RIGHT(1); // UP - 0 DOWN - 1 LEFT - 2 RIGHT - 3

    public int dir;

    Direction(int dir) {
        this.dir = dir;
    }
    public Direction getDir() {
        switch (dir) {
            case 0:
                return UP;
            case 1:
                return RIGHT;
            case 2:
                return DOWN;
            case 3:
                return LEFT;
            default:
                break;
        }
        return null;
    }
}
