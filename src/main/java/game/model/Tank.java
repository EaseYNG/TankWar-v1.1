package main.java.game.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.imageio.ImageIO;

public class Tank implements AMove {
    // 坦克基本属性
    public int ax;
    public int ay; // 坐标
    public TankType atype;
    public Direction adir;
    public int speed;
    public int health;
    public int attack;

    public final int AMMO = 5; // 默认弹药5
    public int currentAmmo; // 目前弹药
    public AtomicBoolean isEmpty = new AtomicBoolean(false); // 弹夹是否为空

    public static int WIDTH = 17, HEIGHT = 17;


    // constructor
    public Tank(int x, int y) {
        this.ax = x;
        this.ay = y;
        this.atype = getType();
        this.speed = atype.getSpeed();
        this.health = atype.getHealth();
        this.attack = atype.getAttack();
        this.adir = Direction.UP; // 初始方向
    }

    
    public void setAdir(Direction dir) {
        this.adir = dir;
    }
    public Direction getDir() {
        return adir;
    }
    public void setType(TankType t) {
        this.atype = t;
    }
    public TankType getType() {
        return atype;
    }

    public void setX(int x) {
        this.ax = x;
    }
    public void setY(int y) {
        this.ay = y;
    }
    public int getX() {
        return ax;
    }
    public int getY() {
        return ay;
    }

    @Override
    public void move() {
        switch (this.adir) {
            case Direction.UP -> this.ay -= this.speed;
            case Direction.RIGHT -> this.ax += this.speed;
            case Direction.DOWN -> this.ay += this.speed;
            case Direction.LEFT -> this.ax -= this.speed;
        }
        // 碰到则静止
        this.ax = Math.max(WIDTH, Math.min(this.ax, 800-WIDTH));
        this.ay = Math.max(HEIGHT, Math.min(this.ay, 600-HEIGHT));

    }
}
