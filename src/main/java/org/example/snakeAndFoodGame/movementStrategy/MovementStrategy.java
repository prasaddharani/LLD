package org.example.snakeAndFoodGame.movementStrategy;

import org.example.snakeAndFoodGame.utilityClasses.Pair;

public interface MovementStrategy {
    Pair getNextPosition(Pair currentHead, String direction);
}
