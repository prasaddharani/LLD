package org.example.snakeAndFoodGame.movementStrategy.concrentStategies;

import org.example.snakeAndFoodGame.movementStrategy.MovementStrategy;
import org.example.snakeAndFoodGame.utilityClasses.Pair;

public class HumanPlacementStrategy implements MovementStrategy {
    @Override
    public Pair getNextPosition(Pair currentHead, String direction) {
        int row = currentHead.getRow();
        int col = currentHead.getCol();
        return switch (direction) {
            case "U" -> new Pair(row - 1, col);
            case "D" -> new Pair(row + 1, col);
            case "L" -> new Pair(row, col - 1);
            case "R" -> new Pair(row, col + 1);
            default -> currentHead;
        };
    }
}
