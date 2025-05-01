package main.java.game.model;

import main.java.game.manager.SpritesManager;

import java.awt.image.BufferedImage;
import java.util.List;

public class Bullet implements AMove {
    private int bx, by, attack;
    private Direction bdir;
    private int bspeed = 8;
    private List<BufferedImage> sprites;


    public Bullet(int x, int y) {
        this.bx = x;
        this.by = y;
        sprites = SpritesManager.getInstance().loadBullet(this.getBdir());
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setBdir(Direction dir) {
        this.bdir = dir;
    }

    public Direction getBdir() {
        return this.bdir;
    }

    public void setBx(int x) {
        this.bx = x;
    }

    public void setBy(int y) {
        this.by = y;
    }

    public int getBx() {
        return this.bx;
    }

    public int getBy() {
        return this.by;
    }

    public List<BufferedImage> getSprites() {
        return sprites;
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
