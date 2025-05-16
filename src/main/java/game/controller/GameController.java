package main.java.game.controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.java.game.model.Attackable;
import main.java.game.model.Bullet;
import main.java.game.model.GameConfig;
import main.java.game.model.Tank;

public class GameController {
    private Tank customTank;
    private List<Tank> enemyTanks;
    public boolean isPaused = false;
    private TankFactory tankFactory = new TankFactory();
    private List<Bullet> bullets = new ArrayList<>();
    private ArrayList<Attackable> allTanks = new ArrayList<>(); // 定义列表检测碰撞

    public GameController() { // 控制坦克和炮弹绘制

        customTank = tankFactory.createCustomTank();
        enemyTanks = new ArrayList<>(GameConfig.getInstance().getSelectedDifficulty().getEnemyNum());

        for(int i=0;i<GameConfig.getInstance().getSelectedDifficulty().getEnemyNum();i++) {
            enemyTanks.add(tankFactory.createEnemyTank());
        }

        // 将所有坦克加入Attackable列表
        allTanks.add(getCustomTank());
        allTanks.addAll(getEnemyTanks());
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
            if (bullet.getBx() <= bullet.SIZE || bullet.getBx() >= (800-bullet.SIZE) ||
                    bullet.getBy() <= bullet.SIZE || bullet.getBy() >= (600-bullet.SIZE)) {
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

    public void shotListen() {
        int delta = Tank.SIZE/2 + Bullet.SIZE/2;
        for(int i=0;i<allTanks.size();i++) {
            for(int j=0;j<bullets.size();j++) {
                int deltaX = Math.abs(allTanks.get(i).getX() - bullets.get(j).getBx());
                int deltaY = Math.abs(allTanks.get(i).getY() - bullets.get(j).getBy());
                if(deltaX < delta && deltaY < delta) {
                    allTanks.get(i).shot();
                    bullets.remove(j);
                }
            }
        }
    }

    public void crashListen() {
        int delta = Tank.SIZE;
        for(int i=0;i<allTanks.size();i++) {
            for(int j=i+1;j<allTanks.size();j++) {
                int deltaX = Math.abs(allTanks.get(i).getX() - allTanks.get(j).getX());
                int deltaY = Math.abs(allTanks.get(i).getY() - allTanks.get(j).getX());
                if(deltaX < delta && deltaY < delta) {
                    allTanks.get(i).crashed();
                }
            }
        }
    }

    public void hpListen() {
        for(int i=0;i<allTanks.size();i++) {
            if(allTanks.get(i).getCurrentHealth()<=0) {
                allTanks.remove(i);
                if(allTanks.get(i).equals(customTank)) {
                    customTank = null;
                }
                else {
                    enemyTanks.remove(allTanks.get(i));
                }
            }
        }
    }
}


