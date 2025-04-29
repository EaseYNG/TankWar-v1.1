package main.java.game.model;

public class Bullet implements AMove {
    private int bx, by, attack;
    private Direction bdir;
    private int bspeed = 8;


    public Bullet(int x, int y) {
        this.bx = x;
        this.by = y;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setBdir(Direction dir) {
        this.bdir = dir;
    }

    public int getBx() {
        return this.bx;
    }

    public int getBy() {
        return this.by;
    }



    @Override
    public void move() {
        switch (bdir) {
            case Direction.UP -> by -= bspeed;
            case Direction.RIGHT -> bx += bspeed;
            case Direction.DOWN -> by += bspeed;
            case Direction.LEFT -> bx -= bspeed;
        }

    }
}
