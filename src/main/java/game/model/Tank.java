package main.java.game.model;

import main.java.game.manager.SpritesManager;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Tank implements AMove {
    // 坦克基本属性
    public int ax;
    public int ay; // 坐标
    public TankType atype;
    public Direction adir;
    public int speed;
    public int health;
    public int attack;

    public final int MAX_AMMO = 5; // 默认弹药5
    public int currentAmmo = 0; // 目前弹药 - index = 0
    public List<Bullet> ammos = new ArrayList<>(4);
    public boolean isEmpty = false; // 弹夹是否为空

    private List<BufferedImage> spritesF;
    private List<BufferedImage> spritesS;

    public static int WIDTH = 17, HEIGHT = 17;


    // constructor
    public Tank(int x, int y) {
        this.ax = x;
        this.ay = y;
        this.adir = Direction.UP; // 初始方向

        // load
        for(int i = 0;i<MAX_AMMO;i++) {
            Bullet b = new Bullet(ax, ay);
            b.setAttack(this.attack);
            ammos.add(b);
        }

        // loadSprites
        spritesF = SpritesManager.getInstance().loadTankF(this.getType());
        spritesS = SpritesManager.getInstance().loadTankS(this.getType());
    }


    public void setAdir(Direction dir) {
        this.adir = dir;
    }
    public Direction getDir() {
        return adir;
    }
    public void setType(TankType t) { // 工厂方法调用设置基础属性
        this.atype = t;
        this.attack = t.getAttack();
        this.health = t.getHealth();
        this.speed = t.getSpeed();
    }

    public int getAttack() {
        return this.attack;
    }

    public int getHealth() {
        return this.health;
    }

    public int getSpeed() {
        return this.speed;
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

    // 换弹
    public void reload() {
        isEmpty = false;
        currentAmmo = 0;
    }

    // 射击
    public void shoot() {
        if(!isEmpty) {
            // 刷新弹药位置及方向
            ammos.get(currentAmmo).setBx(this.getX());
            ammos.get(currentAmmo).setBy(this.getY());
            ammos.get(currentAmmo).setBdir(this.getDir());
            // 射击
            ammos.get(currentAmmo).move();
            // 刷新弹药数
            currentAmmo++;
            if(currentAmmo == 4) isEmpty = true;
        }
    }

    public List<BufferedImage> getSpritesF() {
        return spritesF;
    }

    public List<BufferedImage> getSpritesS() {
        return spritesS;
    }
}