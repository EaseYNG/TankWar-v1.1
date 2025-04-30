package main.java.game.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChooseTankPanel extends APanel {

    private final List<JButton> buttons = new ArrayList<>();
    private BufferedImage background;
    private final List<ImageIcon> tanks = new ArrayList<>(3);
    private final JPanel imagePanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();

    public ChooseTankPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(true);

        // 加载背景图
        try {
            background = ImageIO.read(new File("src/main/resources/background/chooseTank.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 设置子面板透明
        imagePanel.setOpaque(false);
        buttonPanel.setOpaque(false);

        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.X_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // 加载坦克图标
        tanks.add(new ImageIcon("src/main/resources/Sprites/FH_0.PNG"));
        tanks.add(new ImageIcon("src/main/resources/Sprites/FM_0.PNG"));
        tanks.add(new ImageIcon("src/main/resources/Sprites/FL_0.PNG"));

        imagePanel.add(Box.createHorizontalGlue());
        for (ImageIcon icon : tanks) {
            JLabel label = new JLabel(icon);
            imagePanel.add(label);
            imagePanel.add(Box.createHorizontalGlue());
        }

        buttons.add(ButtonFactory.createButton("HEAVY"));
        buttons.add(ButtonFactory.createButton("MEDIUM"));
        buttons.add(ButtonFactory.createButton("LIGHT"));

        buttonPanel.add(Box.createHorizontalGlue());
        for (JButton button : buttons) {
            buttonPanel.add(button);
            buttonPanel.add(Box.createHorizontalGlue());
        }


        add(Box.createVerticalGlue());
        add(imagePanel);
        add(Box.createVerticalStrut(50));
        add(buttonPanel);
        add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
