package main.java.game.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuPanel extends APanel {
    private List<JButton> buttons = new ArrayList<>();
    private File f;
    private BufferedImage background;

    public MenuPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        f = new File("src/main/resources/background/menu.png");
        try {
            background = ImageIO.read(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        buttons.add(ButtonFactory.createButton("Start"));
        buttons.add(ButtonFactory.createButton("Instructions"));
        buttons.add(ButtonFactory.createButton("Exit"));

        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        for (JButton button : buttons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT); // 居中
            add(button);
            add(Box.createVerticalStrut(50)); // 按钮间间距
        }
        add(Box.createVerticalGlue());

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Painting " + getClass().getSimpleName());
        if(this.isShowing()) {
            if (background != null) {
                // 绘制图片
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            } else {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        }
    }
}
