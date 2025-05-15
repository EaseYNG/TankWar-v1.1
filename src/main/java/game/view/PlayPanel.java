package main.java.game.view;

import main.java.game.controller.GameController;
import main.java.game.manager.MapManager;
import main.java.game.manager.SpritesManager;
import main.java.game.model.Direction;
import main.java.game.model.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class PlayPanel extends APanel { // 控制HUD和地图等组件绘制
    private HUD top = new HUD();
    private HUD bottom = new HUD();
    private MapPanel mapPanel = new MapPanel();
    private JButton pause = new JButton();
    private JButton volumeUp = new JButton();
    private JButton volumeDown = new JButton();
    private JLabel hp = new JLabel("Health:");
    private JLabel currentHp = new JLabel();


    public PlayPanel() {
        setPreferredSize(new Dimension(800, 660));
        setLayout(new BorderLayout());

        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.SOUTH);
        add(mapPanel, BorderLayout.CENTER);

        mapPanel.requestFocusInWindow();

        // HUD设置
        pause.setIcon(SpritesManager.getInstance().loadPause());
        volumeUp.setIcon(SpritesManager.getInstance().loadVolumeUp());
        volumeDown.setIcon(SpritesManager.getInstance().loadVolumeDown());
        pause.setSize(new Dimension(30, 30));
        volumeUp.setSize(new Dimension(30, 30));
        volumeDown.setSize(new Dimension(30, 30));

        top.add(pause);
        top.add(volumeUp);
        top.add(volumeDown);

        pause.setLocation(770,0);
        volumeUp.setLocation(740,0);
        volumeDown.setLocation(710,0);

        hp.setSize(new Dimension(100,30));
        currentHp.setSize(new Dimension(30,30));

        bottom.add(hp);
        bottom.add(currentHp);

        hp.setLocation(0,0);
        currentHp.setLocation(140,0);

        top.setVisible(true);
        bottom.setVisible(true);

        // 注册组件监听器，当面板切换时延迟初始化
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                mapPanel.initialGame();
            }
        });
    }

    public APanel getMapPanel() {
        return mapPanel;
    }

    public void updateCurrentHP(Tank tank) {
        currentHp.setText(String.valueOf(tank.getCurrentHealth()));
    }
}

class MapPanel extends APanel {
    private final int GRID_WIDTH = 40;
    private GameController gameController;
    private MapManager mapManager;
    private Timer gameTimer;

    public MapPanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setVisible(true);

        // 初始化地图元素
        //switch (GameConfig.getInstance().getSelectedMap()) {
            //case Maps.DUST_3 -> mapManager.dust_3();
        //}

    }

    /*
    延迟初始化gameController，gameTimer，使坦克类型、选择难度正确传递
    同时游戏线程和键盘监听也将在此调用
     */
    public void initialGame() {
        if (gameController == null) {
            gameController = new GameController();
        }
        if (gameTimer != null && gameTimer.isRunning()) {
            gameTimer.stop();
        }
        // 游戏线程
        gameTimer = new Timer(32, e -> {
            setFocusable(true);
            requestFocusInWindow();
            repaint();

            if (gameController != null) {
                gameController.getCustomTank().move();
                gameController.updateBullets();

                for(int i=0;i<gameController.getEnemyTanks().size();i++) {
                    gameController.getEnemyTanks().get(i).move();
                    handleEnemyTankBehaviour(gameController.getEnemyTanks().get(i));
                }
            }
        });
        gameTimer.start();
        // 键盘事件处理
        this.setupKeyBindings();
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

        if(gameController != null) {
            gameController.drawComponents(g);
        }
    }
}

class HUD extends APanel {
    public HUD() {
        setPreferredSize(new Dimension(800, 30));
        setBackground(Color.BLACK);
    }
}
