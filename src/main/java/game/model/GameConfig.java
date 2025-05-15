package main.java.game.model;

import java.util.Objects;

public class GameConfig {
    // 单例模式
    public static final GameConfig gameConfig = new GameConfig();
    private TankType customTankType;
    private Maps selectedMap;
    private Difficulty selectedDifficulty;

    private GameConfig() {}

    public static GameConfig getInstance() {
        return gameConfig;
    }

    public void setCustomTankType(TankType t) {
        customTankType = Objects.requireNonNullElse(t, TankType.MEDIUM);
    }

    public TankType getCustomTankType() {
        System.out.println(customTankType);
        return customTankType;
    }

    public void setSelectedMap(Maps map) {
        selectedMap = Objects.requireNonNullElse(map, Maps.DUST_3);
    }

    public Maps getSelectedMap() {
        return selectedMap;
    }

    public void setSelectedDifficulty(Difficulty d) {
        selectedDifficulty = Objects.requireNonNullElse(d, Difficulty.MEDIUM);
    }

    public Difficulty getSelectedDifficulty() {
        return selectedDifficulty;
    }
}