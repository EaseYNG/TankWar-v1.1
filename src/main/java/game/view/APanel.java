package main.java.game.view;

import javax.swing.*;
import java.awt.*;

public abstract class APanel extends JPanel {

    public APanel() {
        setVisible(true);
        setPreferredSize(new Dimension(800, 600));
        setLayout(null); // 子类手动设置布局管理器

    }
}
