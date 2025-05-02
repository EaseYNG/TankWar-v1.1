package main.java.game.view;

import main.java.game.controller.GameController;
import main.java.game.manager.MapManager;


import javax.swing.*;
import java.awt.*;


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

        top.add(pause);

        Timer timer = new Timer(16, e -> mapPanel.repaint());
        timer.start();
    }
}


class MapPanel extends APanel {
    private final int GRID_WIDTH = 40;
    private GameController gameController = new GameController();
    private MapManager mapManager;



    public MapPanel() {
        setPreferredSize(new Dimension(800, 600));
        mapManager = new MapManager(600/GRID_WIDTH, 800/GRID_WIDTH); // 15 20
        // 初始化地图元素
        //switch (GameConfig.getInstance().getSelectedMap()) {
            //case Maps.DUST_3 -> mapManager.dust_3();
        //}


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
        setVisible(true);
        setPreferredSize(new Dimension(800, 30));
        setBackground(Color.GRAY);
    }
}



