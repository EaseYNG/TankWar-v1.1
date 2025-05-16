package main.java.game.model;

import main.java.game.controller.GameController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
定义坦克类，包含坐标、类型、生命值、速度、方向、伤害等属性
构造器参数为坐标，并对方向进行初始化

 */
public class Tank implements Attackable {
    // 坦克基本属性
    public static final int SIZE = 34;
    public int ax;
    public int ay; // 坐标
    private TankType atype;
    private Direction adir;
    private int speed;
    private int health;
    private int currentHealth;
    private final int MAX_AMMO = 5; // 默认弹药5
    private int currentAmmo = 0; // 目前弹药 - index = 0
    private boolean isEmpty = false; // 弹夹是否为空
    private boolean isFirst = true; // 绘制检测


    private List<BufferedImage> spritesF;
    private List<BufferedImage> spritesS;

    public static int WIDTH = 17, HEIGHT = 17;


    // constructor
    public Tank(int x, int y, TankType atype) {
        this.ax = x;
        this.ay = y;
        this.adir = Direction.UP; // 初始方向
        setType(atype); // 设置初始类型 及类型属性
        currentHealth = this.health;
        // 加载图片
        spritesF = loadTankF(this.atype);
        spritesS = loadTankS(this.atype);
    }


    public void setAdir(Direction dir) {
        this.adir = dir;
    }
    public Direction getDir() {
        return adir;
    }
    public void setType(TankType t) { // 工厂方法调用设置基础属性
        if(t!=null){
            this.atype = t;
            this.health = t.getHealth();
            this.speed = t.getSpeed();
        }
    }

    public int getHealth() {
        return this.health;
    }
    public int getCurrentHealth() {return this.currentHealth;}
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
    public boolean isFirst() {
        return isFirst;
    }
    public boolean isEmpty() {
        return isEmpty;
    }
    public void setEmpty(boolean b) {
        this.isEmpty = b;
    }

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

    // 绘制
    public void draw(Graphics g) {
        if(isFirst()) {
            switch (this.getDir()) {
                case Direction.UP -> g.drawImage(this.getSpritesF().get(0), this.getX()-SIZE/2, this.getY()-SIZE/2, null);
                case Direction.RIGHT -> g.drawImage(this.getSpritesF().get(1), this.getX()-SIZE/2, this.getY()-SIZE/2, null);
                case Direction.DOWN -> g.drawImage(this.getSpritesF().get(2), this.getX()-SIZE/2, this.getY()-SIZE/2, null);
                case Direction.LEFT -> g.drawImage(this.getSpritesF().get(3), this.getX()-SIZE/2, this.getY()-SIZE/2, null);
            }
        } else {
            switch (this.getDir()) {
                case Direction.UP -> g.drawImage(this.getSpritesS().get(0), this.getX()-SIZE/2, this.getY()-SIZE/2, null);
                case Direction.RIGHT -> g.drawImage(this.getSpritesS().get(1), this.getX()-SIZE/2, this.getY()-SIZE/2, null);
                case Direction.DOWN -> g.drawImage(this.getSpritesS().get(2), this.getX()-SIZE/2, this.getY()-SIZE/2, null);
                case Direction.LEFT -> g.drawImage(this.getSpritesS().get(3), this.getX()-SIZE/2, this.getY()-SIZE/2, null);
            }
        }
        isFirst = !isFirst;
    }

    // 射击
    public void shoot(GameController controller) {
        if (!isEmpty) {
            Bullet bullet = new Bullet(this.ax, this.ay, this.adir);
            controller.addBullet(bullet);

            // 更新弹药逻辑
            currentAmmo++;
            if (currentAmmo == MAX_AMMO) {
                isEmpty = true;
            }
        }
    }

    public int getCurrentAmmo() {
        return this.currentAmmo;
    }

    public List<BufferedImage> getSpritesF() {
        return spritesF;
    }

    public List<BufferedImage> getSpritesS() {
        return spritesS;
    }

    // 读取图片
    // 读取第一帧
    public List<BufferedImage> loadTankF(TankType t) {
        List<BufferedImage> spritesF = new ArrayList<>();

        if(t != null) {
            switch (t) {
                case HEAVY:
                    for(int i=0;i<4;i++) {
                        String input = String.format("src/main/resources/Sprites/FH_%d.png", i);
                        File f = new File(input);
                        try {
                            spritesF.add(ImageIO.read(f));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case MEDIUM:
                    for(int i=0;i<4;i++) {
                        String input = String.format("src/main/resources/Sprites/FM_%d.png", i);
                        File f = new File(input);
                        try {
                            spritesF.add(ImageIO.read(f));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case LIGHT:
                    for(int i=0;i<4;i++) {
                        String input = String.format("src/main/resources/Sprites/FL_%d.png", i);
                        File f = new File(input);
                        try {
                            spritesF.add(ImageIO.read(f));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
        }
        return spritesF;
    }

    // 读取第二帧
    public List<BufferedImage> loadTankS(TankType t) {
        List<BufferedImage> spritesS = new ArrayList<>(4);
        if(t != null) {
            switch (t) {
                case HEAVY:
                    for (int i = 0; i < 4; i++) {
                        String input = String.format("src/main/resources/Sprites/SH_%d.png", i);
                        File f = new File(input);
                        try {
                            spritesS.add(ImageIO.read(f));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case MEDIUM:
                    for (int i = 0; i < 4; i++) {
                        String input = String.format("src/main/resources/Sprites/SM_%d.png", i);
                        File f = new File(input);
                        try {
                            spritesS.add(ImageIO.read(f));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case LIGHT:
                    for (int i = 0; i < 4; i++) {
                        String input = String.format("src/main/resources/Sprites/SL_%d.png", i);
                        File f = new File(input);
                        try {
                            spritesS.add(ImageIO.read(f));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    break;
            }
        }
        return spritesS;
    }

    @Override
    public void shot() {
        this.currentHealth -= 50;
    }

    @Override
    public void crashed() {
        this.currentHealth -= 25;
    }
}