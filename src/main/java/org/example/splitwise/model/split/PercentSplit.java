package org.example.splitwise.model.split;

import org.example.splitwise.model.User;

public class PercentSplit extends Split {
    private double percent;


    public PercentSplit(User user, double percent) {
        super(user);
        this.percent = percent;
    }
}
