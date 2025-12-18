package org.example.splitwise.service;

import org.example.splitwise.enums.ExpenseType;
import org.example.splitwise.model.User;
import org.example.splitwise.model.expense.*;
import org.example.splitwise.model.split.PercentSplit;
import org.example.splitwise.model.split.Split;

import java.util.List;

public class ExpenseService {
    public static Expense createExpense(ExpenseType expenseType, double amount, User paidBy, List<Split> splits, ExpenseMetaData metaData) {
        return switch (expenseType) {
            case EXACT -> new ExactExpense(amount, paidBy, splits, metaData);
            case EQUAL -> {
                int totalSplits = splits.size();
                double splitAmount = (Math.round(amount*100/totalSplits)/100.0);
                for (Split split: splits) {
                    split.setAmount(splitAmount);
                }
                splits.getFirst().setAmount(splitAmount + (amount - splitAmount*totalSplits));
                yield new EqualExpense(amount, paidBy, splits, metaData);
            }
            case PERCENT -> {
                for (Split split: splits) {
                    PercentSplit percentSplit = (PercentSplit) split;
                    percentSplit.setAmount((amount*percentSplit.getPercent())/100.0);
                }
                yield new PercentExpense(amount, paidBy, splits, metaData);
            }
        };
    }
}
