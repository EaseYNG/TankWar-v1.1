package main.java.game.manager;

import main.java.game.model.MapComponents;

public class MapManager {
    private int cols;
    private int rows;
    private MapComponents[][] grid;

    public MapManager(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        grid = new MapComponents[cols][rows];
        initDefaultMap();
    }

    public MapComponents[][] getGrid() {
        return grid;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    // 空地图
    public void initDefaultMap() {
        for(int i=0;i<cols;i++) {
            for(int j=0;j<rows;j++) {
                grid[i][j] = MapComponents.EMPTY;
            }
        }
    }

    public void dust_3() {

    }
}
