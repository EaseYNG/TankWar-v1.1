package main.java.game.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tank {
    public int ax;
    public int ay;
    public TankType atype;
    public Direction adir;
    public int speed;
    public int health;
    public int attack;
    public final int WIDTH = 17, HEIGHT = 17;
    public BufferedImage[] spritesF;
    public BufferedImage[] spritesS;

    // constuctor
    public Tank() {
        this.atype = getType();
        this.speed = atype.getSpeed();
        this.health = atype.getHealth();
        this.attack = atype.getAttack();
        this.adir = Direction.UP; // 初始方向
        loadImageF(atype);
        loadImageS(atype);
    }

    // 读取第一帧
    public void loadImageF(TankType t) {
        spritesF = new BufferedImage[4];
        if(t != null) {
            switch (atype) {
                case HEAVY:
                    for(int i=0;i<4;i++) {
                        String input = String.format("src/main/java/game/resources/FH_%d.png", i);
                        File f = new File(input);
                        try {
                            spritesF[i] = ImageIO.read(f);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case MEDIUM:
                    for(int i=0;i<4;i++) {
                        String input = String.format("src/main/java/game/resources/FM_%d.png", i);
                        File f = new File(input);
                        try {
                            spritesF[i] = ImageIO.read(f);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case LIGHT:
                    for(int i=0;i<4;i++) {
                        String input = String.format("src/main/java/game/resources/FL_%d.png", i);
                        File f = new File(input);
                        try {
                            spritesF[i] = ImageIO.read(f);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
        }
    }

    // 读取第二帧
    public void loadImageS(TankType t) {
        spritesS = new BufferedImage[4];
        if(t != null) {
            switch (atype) {
                case HEAVY:
                    for (int i = 0; i < 4; i++) {
                        String input = String.format("src/main/java/game/resources/SH_%d.png", i);
                        File f = new File(input);
                        try {
                            spritesS[i] = ImageIO.read(f);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case MEDIUM:
                    for (int i = 0; i < 4; i++) {
                        String input = String.format("src/main/java/game/resources/SM_%d.png", i);
                        File f = new File(input);
                        try {
                            spritesS[i] = ImageIO.read(f);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case LIGHT:
                    for (int i = 0; i < 4; i++) {
                        String input = String.format("src/main/java/game/resources/SL_%d.png", i);
                        File f = new File(input);
                        try {
                            spritesS[i] = ImageIO.read(f);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    break;
            }
        }

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
}
