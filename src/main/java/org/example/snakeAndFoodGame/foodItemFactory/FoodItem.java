package org.example.snakeAndFoodGame.foodItemFactory;

public abstract class FoodItem {
    protected int row, col;
    protected int points;

    public FoodItem(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getPoints() {
        return points;
    }

    public int getCol() {
        return col;
    }
}
