package org.example.splitwise.model.expense;

import org.example.splitwise.model.User;
import org.example.splitwise.model.split.ExactSplit;
import org.example.splitwise.model.split.Split;

import java.util.List;

public class ExactExpense extends Expense{
    public ExactExpense(double amount, User paidBy, List<Split> splits, ExpenseMetaData metaData) {
        super(amount, paidBy, splits, metaData);
    }

    @Override
    public boolean validate() {
        for (Split split: getSplits()) {
            if (!(split instanceof ExactSplit)) {
                return false;
            }
        }

        double totalAmount = getAmount();
        double sumSplitAmount = 0;
        for (Split split: getSplits()) {
            ExactSplit exactSplit = (ExactSplit) split;
            sumSplitAmount += exactSplit.getAmount();
        }
        return sumSplitAmount ==  totalAmount;
    }
}
