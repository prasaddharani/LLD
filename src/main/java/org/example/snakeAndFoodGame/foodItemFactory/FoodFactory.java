package org.example.snakeAndFoodGame.foodItemFactory;

import org.example.snakeAndFoodGame.foodItemFactory.ConcreteFoodItems.BonusFood;
import org.example.snakeAndFoodGame.foodItemFactory.ConcreteFoodItems.NormalFood;

public class FoodFactory {

    public static FoodItem createFood(int[] pos, String type) {
        if ("bonus".equalsIgnoreCase(type)) {
            return new BonusFood(pos[0], pos[1]);
        }
        return new NormalFood(pos[0], pos[1]);
    }
}
