package main.java.game.model;

public interface Attackable {
    void shot();
    void crashed();
    int getX();
    int getY();
    int getCurrentHealth();
}
