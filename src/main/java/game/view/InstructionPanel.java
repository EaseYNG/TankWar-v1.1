package main.java.game.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InstructionPanel extends APanel {
    private ArrayList<JLabel> labels = new ArrayList<JLabel>();
    private BoxLayout layout;
    private Font f = new Font("Impact", Font.PLAIN, 22);
    private JButton bottomButton = ButtonFactory.createButton("Menu");

    public InstructionPanel() {
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        add(Box.createVerticalGlue());

        labels.add(new JLabel("W A S D - UP LEFT DOWN RIGHT"));
        labels.add(new JLabel("SPACE - shoot"));
        labels.add(new JLabel("R - reload"));
        labels.add(new JLabel("There are three tank types: Heavy, Medium, Light"));
        labels.add(new JLabel("Different types means different health, attack, speed"));
        labels.add(new JLabel("There are three types of difficulties, means three different enemy quantities"));

        for(JLabel label : labels) {
            label.setFont(f);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(label);
            add(Box.createVerticalStrut(20));
        }

        bottomButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(bottomButton);
        add(Box.createVerticalGlue());

    }


}
