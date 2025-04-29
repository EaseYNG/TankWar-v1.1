package main.java.game.controller;

import java.util.ArrayList;
import java.util.List;

import main.java.game.model.GameConfig;
import main.java.game.model.Maps;
import main.java.game.model.Tank;

public class GameController {
    private Tank customTank;
    private List<Tank> enemyTanks;
    private Maps nowMap;
    private GameThread gameThread = new GameThread();
    
    

    public GameController() {
        nowMap = GameConfig.getInstance().getSelectedMap();
        customTank = TankFactory.createCustomTank();
        enemyTanks = new ArrayList<Tank>();

        for(int i=0;i<GameConfig.getInstance().getSelectedDifficuly().getEnemyNum();i++) {
            enemyTanks.add(TankFactory.createEnemyTank());
        }

    }
    
    
}

class GameThread implements Runnable {
    @Override
    public void run() {
        
    }
}
