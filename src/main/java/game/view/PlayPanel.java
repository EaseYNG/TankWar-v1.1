package main.java.game.view;

import main.java.game.controller.GameController;
import main.java.game.manager.MapManager;
import main.java.game.model.GameConfig;
import main.java.game.model.Maps;

import java.awt.*;

public class PlayPanel extends APanel { // 控制HUD和地图等组件绘制
    private GridBagLayout layout;
    private HUD top = new HUD();
    private HUD bottom = new HUD();
    private MapPanel mapPanel = new MapPanel();



    public PlayPanel() {
        setPreferredSize(new Dimension(800, 700));
        setLayout(new BorderLayout());

        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.SOUTH);
        add(mapPanel, BorderLayout.CENTER);
    }
}


class MapPanel extends APanel {
    private final int GRID_WIDTH = 40;
    private GameController gameController = new GameController();
    private MapManager mapManager = new MapManager(600/GRID_WIDTH, 800/GRID_WIDTH);


    public MapPanel() {
        // 初始化地图元素
        switch (GameConfig.getInstance().getSelectedMap()) {
            case Maps.DUST_3 -> mapManager.dust_3();
        }

    }

    @Override
    protected void paintComponent(Graphics g) { // 绘制地图及坦克等组件
        super.paintComponent(g);

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



