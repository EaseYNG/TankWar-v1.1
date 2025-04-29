package main.java.game.controller;

import java.util.Random;

import main.java.game.model.GameConfig;
import main.java.game.model.Tank;
import main.java.game.model.TankType;

public class TankFactory {
    private static final int FX = 17;
    private static final int FY = 17;

    public static Tank createCustomTank() {
        Tank customTank = new Tank(FX, FY);
        customTank.setType(GameConfig.getInstance().getCustomTankType());
        return customTank;
    }

    public static Tank createEnemyTank() {
        int ex,ey;
        Random rnd = new Random();
        TankType t = null;
        ex = rnd.nextInt(17, 800-17+1);
        ey = rnd.nextInt(17, 600-17+1);
        Tank enemyTank = new Tank(ex, ey);
        switch (rnd.nextInt(0,3)) {
            case 0 -> t = TankType.HEAVY;
            case 1 -> t = TankType.MEDIUM;
            case 2 -> t = TankType.LIGHT;
        }
        enemyTank.setType(t);
        return enemyTank;
    }
}
