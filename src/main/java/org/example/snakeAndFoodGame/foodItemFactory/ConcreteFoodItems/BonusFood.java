package org.example.snakeAndFoodGame.foodItemFactory.ConcreteFoodItems;

import org.example.snakeAndFoodGame.foodItemFactory.FoodItem;

public class BonusFood extends FoodItem {
    public BonusFood(int row, int col) {
        super(row, col);
        this.points = 3;
    }
}
