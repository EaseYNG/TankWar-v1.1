package main.java.game.controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.java.game.model.Bullet;
import main.java.game.model.GameConfig;
import main.java.game.model.Tank;

public class GameController {
    private Tank customTank;
    private List<Tank> enemyTanks;
    public boolean isPaused = false;
    private List<Bullet> bullets = new ArrayList<>();

    public GameController() { // 控制坦克和炮弹绘制

        customTank = TankFactory.createCustomTank();
        enemyTanks = new ArrayList<>(GameConfig.getInstance().getSelectedDifficulty().getEnemyNum());

        for(int i=0;i<GameConfig.getInstance().getSelectedDifficulty().getEnemyNum();i++) {
            enemyTanks.add(TankFactory.createEnemyTank());
        }

    }

    // MapPanel调用
    public void drawComponents(Graphics g) {

        // draw tanks
        customTank.draw(g);
        for(Tank enemyTank : enemyTanks) {
            enemyTank.draw(g);
        }
        // draw ammos
        for(Bullet b : bullets) {
            b.draw(g);
        }
    }

    public void addBullet(Bullet b) {
        bullets.add(b);
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    // 更新子弹位置
    public void updateBullets() {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.move();
            // 移除超出屏幕的子弹
            if (bullet.getBx() < 0 || bullet.getBx() > 800 ||
                    bullet.getBy() < 0 || bullet.getBy() > 600) {
                iterator.remove();
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


