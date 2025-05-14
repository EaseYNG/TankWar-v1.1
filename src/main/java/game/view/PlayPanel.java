package main.java.game.view;

import main.java.game.controller.GameController;
import main.java.game.manager.MapManager;
import main.java.game.model.Direction;
import main.java.game.model.GameConfig;
import main.java.game.model.Tank;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class PlayPanel extends APanel { // 控制HUD和地图等组件绘制
    private HUD top = new HUD();
    private HUD bottom = new HUD();
    private MapPanel mapPanel = new MapPanel();
    private JButton pause = ButtonFactory.createButton("||");


    public PlayPanel() {
        setPreferredSize(new Dimension(800, 660));
        setLayout(new BorderLayout());

        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.SOUTH);
        add(mapPanel, BorderLayout.CENTER);
        mapPanel.requestFocusInWindow();

        top.add(pause);

    }
}

class MapPanel extends APanel {
    private final int GRID_WIDTH = 40;
    private GameController gameController = new GameController();
    private MapManager mapManager;

    public MapPanel() {
        setPreferredSize(new Dimension(800, 600));
        mapManager = new MapManager(600/GRID_WIDTH, 800/GRID_WIDTH); // 15 20
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);

        // 初始化地图元素
        //switch (GameConfig.getInstance().getSelectedMap()) {
            //case Maps.DUST_3 -> mapManager.dust_3();
        //}

        // 键盘事件处理
        setupKeyBindings();

        Timer timer = new Timer(32, e -> {
            repaint();
            gameController.getCustomTank().move();
            gameController.updateBullets(); // 更新子弹位置
            for(int i=0;i<gameController.getEnemyTanks().size();i++) {
                gameController.getEnemyTanks().get(i).move();
                handleEnemyTankBehaviour(gameController.getEnemyTanks().get(i)); // 随机移动和射击
            }
        });
        timer.start();
    }

    // 处理键位绑定
    private void setupKeyBindings() {
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        // 绑定方向键
        bindKey(inputMap, actionMap, KeyEvent.VK_UP, "UP", () -> handleCustomTurning(KeyEvent.VK_UP));
        bindKey(inputMap, actionMap, KeyEvent.VK_RIGHT, "RIGHT", () -> handleCustomTurning(KeyEvent.VK_RIGHT));
        bindKey(inputMap, actionMap, KeyEvent.VK_DOWN, "DOWN", () -> handleCustomTurning(KeyEvent.VK_DOWN));
        bindKey(inputMap, actionMap, KeyEvent.VK_LEFT, "LEFT", () -> handleCustomTurning(KeyEvent.VK_LEFT));

        // 绑定空格射击和R换弹
        bindKey(inputMap, actionMap, KeyEvent.VK_SPACE, "SHOOT", () -> handleCustomAmmo(KeyEvent.VK_SPACE));
        bindKey(inputMap, actionMap, KeyEvent.VK_R, "RELOAD", () -> handleCustomAmmo(KeyEvent.VK_R));
    }

    private void bindKey(InputMap inputMap, ActionMap actionMap, int keyCode, String actionKey, Runnable action) {
        inputMap.put(KeyStroke.getKeyStroke(keyCode, 0), actionKey);
        actionMap.put(actionKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
    }

    private void handleCustomTurning(int command) {
        switch (command) {
            case KeyEvent.VK_UP -> {
                gameController.getCustomTank().setAdir(Direction.UP);
                System.out.println("UP");
            }
            case KeyEvent.VK_RIGHT -> {
                gameController.getCustomTank().setAdir(Direction.RIGHT);
                System.out.println("RIGHT");
            }
            case KeyEvent.VK_DOWN -> {
                gameController.getCustomTank().setAdir(Direction.DOWN);
                System.out.println("DOWN");
            }
            case KeyEvent.VK_LEFT -> {
                gameController.getCustomTank().setAdir(Direction.LEFT);
                System.out.println("LEFT");
            }
        }
    }

    private void handleCustomAmmo(int command) {
        switch (command) {
            case KeyEvent.VK_R -> {
                gameController.getCustomTank().reload();
                System.out.println("RELOADING");
            }
            case KeyEvent.VK_SPACE -> {
                gameController.getCustomTank().shoot(gameController);
                System.out.println("SHOOTING");
            }
        }
    }

    private void handleEnemyTankBehaviour(Tank enemy) { // 随机变向和射击
        Random rnd = new Random();
        if(rnd.nextInt(0,101)<=2) {
            switch (rnd.nextInt(0,4)) {
                case 0 -> enemy.setAdir(Direction.UP);
                case 1 -> enemy.setAdir(Direction.RIGHT);
                case 2 -> enemy.setAdir(Direction.DOWN);
                case 3 -> enemy.setAdir(Direction.LEFT);
            }
        }

        if(rnd.nextDouble(0,1)<=0.01) {
            enemy.shoot(gameController);
            enemy.isEmpty = false; // 敌人无空弹匣逻辑
        }
    }

    @Override
    protected void paintComponent(Graphics g) { // 绘制地图及坦克等组件
        super.paintComponent(g);
        // 绘制地图
        for(int i=0;i<mapManager.getCols();i++) {
            for (int j=0;j<mapManager.getCols();j++) {
                switch (mapManager.getGrid()[i][j]) {
                    case EMPTY -> {
                        g.setColor(Color.WHITE);
                        g.fillRect(j*GRID_WIDTH, i*GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
                    }
                    case BRICK -> {
                        g.setColor(Color.RED);
                        g.fillRect(j*GRID_WIDTH, i*GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
                    }
                    case SPAWN -> {
                        g.setColor(Color.GREEN);
                        g.fillRect(j*GRID_WIDTH, i*GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
                    }
                }
            }
        }
        gameController.drawComponents(g);
    }
}

class HUD extends APanel {
    public HUD() {
        setPreferredSize(new Dimension(800, 30));
        setBackground(Color.GRAY);
        setVisible(true);
    }
}
