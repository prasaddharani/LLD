package org.example.snakeAndFoodGame.utilityClasses;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Snake {
    private Deque<Pair> body;
    private Map<Pair, Boolean> positionMap;

    public Snake() {
        this.body = new LinkedList<>();
        this.positionMap = new HashMap<>();

        Pair initialPos = new Pair(0, 0);
        this.body.offerFirst(initialPos);
        this.positionMap.put(initialPos, true);
    }
}
