package main.java.game.model;

public enum Difficulty {
    EASY(5), MEDIUM(7), HARD(9);

    private int enemyNum;
    Difficulty(int enemyNum) {
        this.enemyNum = enemyNum;
    }

    public int getEnemyNum() {
        return enemyNum;
    }
}
