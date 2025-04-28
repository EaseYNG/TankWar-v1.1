package main.java.game.model;

public class GameConfig {
    // 单例模式 全游戏配置
    public static final GameConfig gameConfig = new GameConfig();
    public static TankType customTankType = TankType.MEDIUM; // 默认类型
    public static Maps selectedMap = Maps.DUST_3; // 默认地图
    public static Difficulty selectedDifficuly = Difficulty.MEDIUM;

    private GameConfig() {}

    public static GameConfig getInstance() {
        return gameConfig;
    }

    public void setCustomTankType(TankType t) {
        customTankType = t;
    }

    public TankType getCustomTankType() {
        return customTankType;
    }

    public void setSelectedMap(Maps map) {
        selectedMap = map;
    }

    public Maps getSelectedMap() {
        return selectedMap;
    }

    public void setSelectedDifficuly(Difficulty d) {
        selectedDifficuly = d;
    }

    public Difficulty getSelectedDifficuly() {
        return selectedDifficuly;
    }
}
