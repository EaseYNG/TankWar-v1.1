package main.java.game.controller;

import java.util.Random;

import main.java.game.model.GameConfig;
import main.java.game.model.Tank;
import main.java.game.model.TankType;

public class TankFactory {

    public static Tank createCustomTank() {
        Tank customTank = new Tank();
        customTank.ax = 17;
        customTank.ay = 17; // 用户坦克初始坐标
        customTank.setType(GameConfig.getInstance().getCustomTankType());
        return customTank;
    }

    public static Tank createEnemyTank() {
        Tank enemyTank = new Tank();
        Random rnd = new Random();
        TankType t = null;
        enemyTank.ax = rnd.nextInt(17, 800-17+1);
        enemyTank.ay = rnd.nextInt(17, 600-17+1);
        switch (rnd.nextInt(0,3)) {
            case 0 -> t = TankType.HEAVY;
            case 1 -> t = TankType.MEDIUM;
            case 2 -> t = TankType.LIGHT;
        }
        enemyTank.setType(t);
        return enemyTank;
    }
}
