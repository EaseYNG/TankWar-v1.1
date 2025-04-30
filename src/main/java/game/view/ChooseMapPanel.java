package main.java.game.view;

import main.java.game.model.GameConfig;
import main.java.game.model.Maps;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChooseMapPanel extends APanel {
    private List<JButton> mapButtons = new ArrayList<>();
    private JPanel mapPanel = new JPanel();
    private JButton bottomButton = ButtonFactory.createButton("Tank");
    private File f;
    private BufferedImage background;

    public ChooseMapPanel() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        bottomButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        f = new File("src/main/resources/background/chooseMap.png");
        try {
            background = ImageIO.read(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mapButtons.add(ButtonFactory.createButton("Dust_3"));

        mapPanel.setOpaque(false);
        mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.X_AXIS));
        mapPanel.add(Box.createHorizontalGlue());
        for (JButton btn : mapButtons) {
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = btn.getText();
                    switch (command) {
                        case "Dust_3" -> {
                            GameConfig.getInstance().setSelectedMap(Maps.DUST_3);
                            GameFrame.getInstance().switchPanelTo("PLAY");
                        }
                    }
                }
            });
            mapPanel.add(btn);
            mapPanel.add(Box.createHorizontalStrut(50));
        }
        mapPanel.remove(mapPanel.getComponentCount() - 1); // 去掉多余的间隙
        mapPanel.add(Box.createHorizontalGlue());

        add(Box.createVerticalGlue());
        add(mapPanel);
        add(Box.createVerticalStrut(250));
        add(bottomButton);
        add(Box.createVerticalGlue());

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Painting " + getClass().getSimpleName());
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
