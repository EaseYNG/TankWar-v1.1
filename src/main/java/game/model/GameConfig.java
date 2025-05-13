package main.java.game.model;

public class GameConfig {
    // 单例模式
    public static final GameConfig gameConfig = new GameConfig();
    private static TankType customTankType = TankType.MEDIUM; // 默认类型
    private static Maps selectedMap = Maps.DUST_3;       // 默认地图
    private static Difficulty selectedDifficulty = Difficulty.MEDIUM;

    private GameConfig() {}

    public static GameConfig getInstance() {
        return gameConfig;
    }

    public void setCustomTankType(TankType t) {
        if (t != null) {
            customTankType = t;
        } else {
            customTankType = TankType.MEDIUM; // 可选：允许通过 null 重置为默认值
        }
    }

    public TankType getCustomTankType() {
        return customTankType;
    }

    public void setSelectedMap(Maps map) {
        if (map != null) {
            selectedMap = map;
        }
    }

    public Maps getSelectedMap() {
        return selectedMap;
    }

    public void setSelectedDifficulty(Difficulty d) {
        if (d != null) {
            selectedDifficulty = d;
        }
    }

    public Difficulty getSelectedDifficulty() {
        return selectedDifficulty;
    }
}