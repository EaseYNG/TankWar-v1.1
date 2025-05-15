package main.java.game.manager;

import main.java.game.model.Direction;
import main.java.game.model.TankType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpritesManager {
    public static SpritesManager spritesManager = new SpritesManager();
    public BufferedImage img = null;
    private File f = new File("src/main/resources/Sprites/insect_sprite.png");

    private SpritesManager() {}

    public static SpritesManager getInstance() {
        return spritesManager;
    }

    // 读取子弹
    public BufferedImage loadBullet() {
        try {
            img = ImageIO.read(f);
            img = img.getSubimage(5*34, 6*34, 34, 34);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage bulletImage = img;
        return bulletImage;
    }

    // 读取按钮样式
    public ImageIcon loadPause() {
        try {
            img = ImageIO.read(f);
            img = img.getSubimage(12*34, 4*34, 34, 34);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icon = new ImageIcon(img);
        return icon;
    }

    public ImageIcon loadVolumeDown() {
        try {
            img = ImageIO.read(f);
            img = img.getSubimage(13*34, 4*34, 34, 34);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icon = new ImageIcon(img);
        return icon;
    }

    public ImageIcon loadVolumeUp() {
        try {
            img = ImageIO.read(f);
            img = img.getSubimage(14*34, 4*34, 34, 34);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icon = new ImageIcon(img);
        return icon;
    }
}
