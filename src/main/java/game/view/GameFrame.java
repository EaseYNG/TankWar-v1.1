package main.java.game.view;

import main.java.game.model.Difficulty;
import main.java.game.model.GameConfig;
import main.java.game.model.Maps;
import main.java.game.model.TankType;
import main.java.game.view.APanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class GameFrame extends JFrame {
    public static final GameFrame gameFrame = new GameFrame();
    private JPanel container = new JPanel();
    private CardLayout cardLayout = new CardLayout();
    private Map<String, APanel> panels = new HashMap<>(); // 哈希表存储各面板

    private GameFrame() {
        super("TankWar-v1.1");

        // 创建各面板
        APanel menuPanel = new MenuPanel();
        APanel difficultyPanel = new DifficultyPanel();
        APanel chooseTankPanel = new ChooseTankPanel();
        APanel chooseMapPanel = new ChooseMapPanel();
        APanel playPanel = new PlayPanel();
        APanel instructionPanel = new InstructionPanel();

        // 注册面板
        registerPanels("MENU", menuPanel); // 默认初始显示
        registerPanels("DIFFICULTY", difficultyPanel);
        registerPanels("CHOOSETANK", chooseTankPanel);
        registerPanels("CHOOSEMAP", chooseMapPanel);
        registerPanels("PLAY", playPanel);
        registerPanels("INSTRUCTION", instructionPanel);

        // 设置容器布局和显示初始面板
        container.setLayout(cardLayout);
        container.setBackground(Color.WHITE); // 设置默认背景色
        add(container, BorderLayout.CENTER);


        // 设置窗体属性
        pack();  // 使窗体自适应
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void registerPanels(String panelName, APanel panel) {
        panels.put(panelName, panel);
        container.add(panel, panelName);
        addListeners(panel);
    }

    private void addListeners(APanel panel) {
        for (Component c : panel.getComponents()) {
            if (c instanceof JButton) {
                ((JButton) c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String command = ((JButton) c).getText();
                        switch (command) {
                            case "Start" -> switchPanelTo("DIFFICULTY");
                            case "Instructions" -> switchPanelTo("INSTRUCTION");
                            case "Exit" -> System.exit(0);
                            case "Menu" -> switchPanelTo("MENU");
                            case "Easy" -> {
                                switchPanelTo("CHOOSETANK");
                                GameConfig.getInstance().setSelectedDifficuly(Difficulty.EASY);
                            }
                            case "Medium" -> {
                                switchPanelTo("CHOOSETANK");
                                GameConfig.getInstance().setSelectedDifficuly(Difficulty.MEDIUM);
                            }
                            case "Hard" -> {
                                switchPanelTo("CHOOSETANK");
                                GameConfig.getInstance().setSelectedDifficuly(Difficulty.HARD);
                            }
                            case "HEAVY" -> {
                                switchPanelTo("CHOOSEMAP");
                                GameConfig.getInstance().setCustomTankType(TankType.HEAVY);
                            }
                            case "MEDIUM" -> {
                                switchPanelTo("CHOOSEMAP");
                                GameConfig.getInstance().setCustomTankType(TankType.MEDIUM);
                            }
                            case "LIGHT" -> {
                                switchPanelTo("CHOOSEMAP");
                                GameConfig.getInstance().setCustomTankType(TankType.LIGHT);
                            }
                            case "Difficulty" -> switchPanelTo("DIFFICULTY");
                            case "Tank" -> switchPanelTo("CHOOSETANK");
                            case "dust_3" -> {
                                switchPanelTo("PLAY");
                                GameConfig.getInstance().setSelectedMap(Maps.DUST_3);
                            }
                        }
                    }
                });
            }
        }
    }

    public void switchPanelTo(String panelName) {
        System.out.println("Switching to: " + panelName);

        // 设置其他面板可见性
        for (String panelKey : panels.keySet()) {
            if (!panelKey.equals(panelName)) { // 其他未被显示的面板
                panels.get(panelKey).setVisible(false);
            }
        }

        // 显示目标面板
        panels.get(panelName).setVisible(true);
        cardLayout.show(container, panelName);
        container.repaint();  // 强制重新绘制
    }


    public static GameFrame getInstance() {
        return gameFrame;
    }
}
