package org.example.snakeAndFoodGame.foodItemFactory.ConcreteFoodItems;

import org.example.snakeAndFoodGame.foodItemFactory.FoodItem;

public class NormalFood extends FoodItem {
    public NormalFood(int row, int col) {
        super(row, col);
        this.points = 1;
    }
}
