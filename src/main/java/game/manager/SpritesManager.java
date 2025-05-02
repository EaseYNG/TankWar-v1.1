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

    // 读取坦克四方向图，游戏面板绘制时调用
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

    public List<BufferedImage> loadBullet(Direction dir) {
        List<BufferedImage> bullets = new ArrayList<>(4);
        if(dir != null) {
            switch (dir) {
                case UP -> {

                }
            }
        }

        return bullets;
    }
}
