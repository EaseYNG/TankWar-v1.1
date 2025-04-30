package main.java.game.view;

import javax.swing.*;

public class InstructionPanel extends APanel {
    private JLabel label1 = new JLabel("WASD - UP LEFT DOWN RIGHT");
    private JLabel label2 = new JLabel("SPACE - shoot");
    private JLabel label3 = new JLabel("R - reload");
    private JLabel label4 = new JLabel("There are three tank types: Heavy, Medium, Light");
    private JLabel label5 = new JLabel("Different types means different health, attack, speed");
    private JLabel label6 = new JLabel("There are three types of difficulties, means three different enemy quantities");

    private BoxLayout layout;

    public InstructionPanel() {
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);


    }
}
