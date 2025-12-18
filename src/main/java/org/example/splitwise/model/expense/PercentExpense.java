package org.example.splitwise.model.expense;

import org.example.splitwise.model.User;
import org.example.splitwise.model.split.PercentSplit;
import org.example.splitwise.model.split.Split;

import java.util.List;

public class PercentExpense extends Expense{
    public PercentExpense(double amount, User paidBy, List<Split> splits, ExpenseMetaData metaData) {
        super(amount, paidBy, splits, metaData);
    }

    @Override
    public boolean validate() {
        for (Split split: getSplits()) {
            if (!(split instanceof PercentSplit)) {
                return false;
            }
        }

        double totalPercent = 100;
        double sumSplitPercent = 0;
        for (Split split: getSplits()) {
            PercentSplit percentSplit = (PercentSplit) split;
            sumSplitPercent += percentSplit.getPercent();
        }
        return totalPercent == sumSplitPercent;
    }
}
