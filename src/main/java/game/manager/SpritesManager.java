package main.java.game.manager;

import main.java.game.model.Direction;
import main.java.game.model.TankType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpritesManager {
    public static SpritesManager spritesManager = new SpritesManager();

    private SpritesManager() {}

    public static SpritesManager getInstance() {
        return spritesManager;
    }

    // 读取子弹
    public BufferedImage loadBullet() {
        BufferedImage img = null;
        File f = new File("src/main/resources/Sprites/insect_sprite.png");
        try {
            img = ImageIO.read(f);
            img = img.getSubimage(5*34, 6*34, 34, 34);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage bulletImage = img;
        return bulletImage;
    }
}
