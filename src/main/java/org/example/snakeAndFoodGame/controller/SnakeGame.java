package org.example.snakeAndFoodGame.controller;

import org.example.snakeAndFoodGame.movementStrategy.MovementStrategy;
import org.example.snakeAndFoodGame.movementStrategy.concrentStategies.HumanPlacementStrategy;
import org.example.snakeAndFoodGame.utilityClasses.GameBoard;
import org.example.snakeAndFoodGame.utilityClasses.Pair;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SnakeGame {
    private GameBoard board;
    private Deque<Pair> snake;
    private Map<Pair, Boolean> snakeMap;
    private int[][] food;
    private int foodIndex;
    private MovementStrategy movementStrategy;

    public SnakeGame(int width, int height, int[][] food) {
        this.board = GameBoard.getInstance(width, height);
        this.food = food;
        this.foodIndex = 0;

        this.snake = new LinkedList<>();
        this.snakeMap = new HashMap<>();
        Pair initialPos = new Pair(0, 0);
        this.snake.offerFirst(initialPos);
        this.snakeMap.put(initialPos, true);

        this.movementStrategy = new HumanPlacementStrategy();
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public int move(String direction) {
        int score = -1;

        Pair currentHead = this.snake.peekFirst();
        Pair newHead = this.movementStrategy.getNextPosition(currentHead, direction);

        int newHeadRow = newHead.getRow();
        int newHeadCol = newHead.getCol();


        boolean crossBoundary = newHeadRow < 0 || newHeadRow >= this.board.getHeight() ||
                newHeadCol < 0 || newHeadCol >= this.board.getWidth();

        Pair tail = this.snake.peekLast();

        boolean bitItself = this.snakeMap.containsKey(newHead)
                && !(tail.getRow() == newHeadRow && tail.getCol() == newHeadCol);

        if (crossBoundary || bitItself) {
            return score;
        }

        boolean ateFood =
                this.foodIndex < this.food.length && this.food[this.foodIndex][0] == newHeadRow
                        && this.food[this.foodIndex][1] == newHeadCol;

        if (ateFood) {
            this.foodIndex++;
        } else {
            this.snake.pollLast();
            this.snakeMap.remove(tail);
        }
        this.snake.offerFirst(newHead);
        this.snakeMap.put(newHead, true);
        return this.snake.size() - 1;
    }

    public Deque<Pair> getSnake() {
        return snake;
    }
}
