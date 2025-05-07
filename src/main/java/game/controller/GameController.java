package main.java.game.controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import main.java.game.model.Direction;
import main.java.game.model.GameConfig;
import main.java.game.model.Tank;

public class GameController {
    private Tank customTank;
    private List<Tank> enemyTanks;
    private boolean isPaused = false;
    private boolean isShooting = false;

    
    public GameController() { // 控制坦克和炮弹绘制

        customTank = TankFactory.createCustomTank();
        enemyTanks = new ArrayList<>(GameConfig.getInstance().getSelectedDifficuly().getEnemyNum());

        for(int i=0;i<GameConfig.getInstance().getSelectedDifficuly().getEnemyNum();i++) {
            enemyTanks.add(TankFactory.createEnemyTank());
        }

    }

    // MapPanel调用
    public void drawComponents(Graphics g) {

        // draw tanks
        switch (customTank.getDir()) {
            case Direction.UP -> g.drawImage(customTank.getSpritesF().get(0), customTank.getX(), customTank.getY(), null);
            case Direction.RIGHT -> g.drawImage(customTank.getSpritesF().get(1), customTank.getX(), customTank.getY(), null);
            case Direction.DOWN -> g.drawImage(customTank.getSpritesF().get(2), customTank.getX(), customTank.getY(), null);
            case Direction.LEFT -> g.drawImage(customTank.getSpritesF().get(3), customTank.getX(), customTank.getY(), null);
        }

        for(Tank enemyTank : enemyTanks) {
            switch (enemyTank.getDir()) {
                case Direction.UP -> g.drawImage(enemyTank.getSpritesF().get(0), enemyTank.getX(), enemyTank.getY(), null);
                case Direction.RIGHT -> g.drawImage(enemyTank.getSpritesF().get(1), enemyTank.getX(), enemyTank.getY(), null);
                case Direction.DOWN -> g.drawImage(enemyTank.getSpritesF().get(2), enemyTank.getX(), enemyTank.getY(), null);
                case Direction.LEFT -> g.drawImage(enemyTank.getSpritesF().get(3), enemyTank.getX(), enemyTank.getY(), null);
            }
        }

        // draw bullets
        if(isShooting && !customTank.isEmpty) {
            switch (customTank.ammos.get(customTank.currentAmmo).getBdir()) {
                case Direction.UP -> g.drawImage(customTank.ammos.get(customTank.currentAmmo).getSprites().get(0), customTank.getX(), customTank.getY(), null);
                case Direction.RIGHT -> g.drawImage(customTank.ammos.get(customTank.currentAmmo).getSprites().get(1), customTank.getX(), customTank.getY(), null);
                case Direction.DOWN -> g.drawImage(customTank.ammos.get(customTank.currentAmmo).getSprites().get(2), customTank.getX(), customTank.getY(), null);
                case Direction.LEFT -> g.drawImage(customTank.ammos.get(customTank.currentAmmo).getSprites().get(3), customTank.getX(), customTank.getY(), null);
            }
        }

    }

    public Tank getCustomTank() {
        return customTank;
    }

    public List<Tank> getEnemyTanks() {
        return enemyTanks;
    }
}


