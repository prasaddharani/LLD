package org.example.snakeAndFoodGame;

import org.example.snakeAndFoodGame.controller.SnakeGame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int width = 20, height = 15;

        int[][] foodPositions = {
                {0, 1},
                {10, 8},
                {12, 13},
                {13, 14}
        };

        SnakeGame game = new SnakeGame(width, height, foodPositions);
        boolean hasRunning = true;
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        while (hasRunning) {
            displayGameState(game);
            System.out.print("Enter move (W/A/S/D) or Q to quit: ");
            String input = scanner.nextLine();

            if ("Q".equals(input)) {
                System.out.println("Game ended by player. Final score: " + score);
                hasRunning = false;
                continue;
            }

            String direction = convertInput(input);

            if (direction.isEmpty()) {
                System.out.println("Invalid input: Use W/S/A/D to move or Q to Quit");
                continue;
            }
            score = game.move(input);
            if (score == -1) {
                System.out.println("Game Over! You hit a wall or bit yourself");
                System.out.println("Final Score: " + score);
                hasRunning = false;
            } else {
                System.out.println("Score: " + score);
            }


        }
    }

    private static String convertInput(String input) {
        return switch (input) {
            case "W" -> "U";
            case "S" -> "D";
            case "A" -> "L";
            case "D" -> "R";
            default -> "";
        };
    }

    private static void displayGameState(SnakeGame game) {
        System.out.println("Current snake length: "+ game.getSnake().size());
    }
}
