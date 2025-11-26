package org.example.snakeAndFoodGame.utilityClasses;

public class GameBoard {
    public static GameBoard instance = null;

    private final int width;
    private final int height;

    GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static GameBoard getInstance(int width, int height) {
        if (instance == null) {
            instance = new GameBoard(width, height);
        }
        return instance;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
