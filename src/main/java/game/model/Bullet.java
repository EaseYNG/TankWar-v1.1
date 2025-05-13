package main.java.game.model;

import main.java.game.manager.SpritesManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Bullet implements AMove {
    private int bx, by, attack;
    private Direction bdir; // 默认初始化
    private int bspeed = 4;
    private BufferedImage sprite;
    public boolean go = false;


    public Bullet(int x, int y, Direction dir) {
        this.bx = x;
        this.by = y;
        this.bdir = dir;
        sprite = SpritesManager.getInstance().loadBullet(); // 加载子弹图像
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

    public BufferedImage getSprites() {
        return sprite;
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

    public void draw(Graphics g) {
        switch (bdir) {
            case UP -> g.drawImage(sprite, this.bx, this.by-34, 34, 34, null);
            case RIGHT -> g.drawImage(sprite, this.bx+34, this.by, 34, 34, null);
            case DOWN -> g.drawImage(sprite, this.bx, this.by+34, 34, 34, null);
            case LEFT -> g.drawImage(sprite, this.bx-34, this.by, 34, 34, null);
        }
    }
}
