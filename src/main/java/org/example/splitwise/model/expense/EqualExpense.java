package org.example.splitwise.model.expense;

import org.example.splitwise.model.User;
import org.example.splitwise.model.split.EqualSplit;
import org.example.splitwise.model.split.Split;

import java.util.List;

public class EqualExpense extends Expense{

    public EqualExpense(double amount, User paidBy, List<Split> splits, ExpenseMetaData metaData) {
        super(amount, paidBy, splits, metaData);
    }

    @Override
    public boolean validate() {
        for (Split split: getSplits()) {
            if (!(split instanceof EqualSplit)) {
                return false;
            }
        }
        return false;
    }
}
