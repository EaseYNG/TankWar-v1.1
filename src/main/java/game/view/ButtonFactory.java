package main.java.game.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ButtonFactory extends JButton {

    public static JButton createButton(String label) {
        Font buttonFont = new Font("Impact", Font.PLAIN, 22);
        JButton button = new JButton(label);

        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setFont(buttonFont);
        button.setFocusPainted(false);

        Insets margin = new Insets(10, 20, 10, 20);
        button.setMargin(margin);

        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);

        Border raisedBorder = BorderFactory.createRaisedBevelBorder();
        Border paddingBorder = new EmptyBorder(5, 15, 5, 15); // 上下左右内边距
        button.setBorder(new CompoundBorder(raisedBorder, paddingBorder));

        // 确保按钮有合适的最小尺寸
        button.setMinimumSize(new Dimension(120, 50));

        return button;
    }
}