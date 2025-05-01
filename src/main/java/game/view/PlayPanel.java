package main.java.game.view;

import main.java.game.controller.GameController;

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
    private GameController gameController = new GameController();


    public MapPanel() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        gameController.drawComponents(g);
    }
}

class HUD extends APanel {

    public HUD() {
        setPreferredSize(new Dimension(800, 50));
        setBackground(Color.GRAY);
    }
}



