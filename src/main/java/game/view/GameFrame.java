package main.java.game.view;

import main.java.game.model.Difficulty;
import main.java.game.model.GameConfig;
import main.java.game.model.Maps;
import main.java.game.model.TankType;

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
        APanel menuPanel = new MenuPanel();
        APanel difficultyPanel = new DifficultyPanel();
        APanel chooseTankPanel = new ChooseTankPanel();
        APanel chooseMapPanel = new ChooseMapPanel();
        APanel playPanel = new PlayPanel();
        APanel instructionPanel = new InstructionPanel();

        registerPanels("MENU", menuPanel);
        registerPanels("DIFFICULTY", difficultyPanel);
        registerPanels("CHOOSETANK", chooseTankPanel);
        registerPanels("CHOOSEMAP", chooseMapPanel);
        registerPanels("PLAY", playPanel);
        registerPanels("INSTRUCTION", instructionPanel);

        container.setLayout(cardLayout);
        add(container, BorderLayout.CENTER);
        cardLayout.show(container,"MENU");

        pack(); // 自适应大小
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static GameFrame getInstance() {
        return gameFrame;
    }

    private void registerPanels(String panelName, APanel panel) { // 注册面板，便于cardLayout使用
        panels.put(panelName, panel);
        container.add(panel, panelName);
        addListeners(panel);
    }

    private void addListeners(APanel panel) {
        for(Component c : panel.getComponents()) {
            if(c instanceof JButton) {
                ((JButton) c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String command = ((JButton) c).getText(); // 通过标签确定功能
                        switch (command) {
                            // 按钮逻辑
                            // menuPanel
                            case "Start" -> switchPanelTo("DIFFICULTY");
                            case "Instructions" -> switchPanelTo("INSTRUCTION");
                            case "Exit" -> System.exit(0);
                            // difficultyPanel
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
                            // chooseTankPanel
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
                            // chooseMapPanel
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

    private void switchPanelTo(String panelName) { // 切换面板
        cardLayout.show(container, panelName);
    }
}
