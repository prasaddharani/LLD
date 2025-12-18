package org.example.splitwise.controller;

import org.example.splitwise.enums.ExpenseType;
import org.example.splitwise.model.User;
import org.example.splitwise.model.expense.Expense;
import org.example.splitwise.model.expense.ExpenseMetaData;
import org.example.splitwise.model.expense.Group;
import org.example.splitwise.model.split.Split;
import org.example.splitwise.service.ExpenseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.*;

public class ExpenseGroupManager {

    private final Map<String, User> userMap;
    private final Map<String, Group> groupMap;
    private final List<Expense> expenses;

    public ExpenseGroupManager() {
        this.userMap = new HashMap<>();
        this.groupMap = new HashMap<>();
        this.expenses = new ArrayList<>();
    }

    /* ---------------- USER ---------------- */

    public void addUser(User user) {
        userMap.put(user.getId(), user);
    }

    /* ---------------- GROUP ---------------- */

    public void createGroup(String groupId, String name, List<String> members) {
        Group group = new Group(groupId, name);
        for (String userId : members) {
            group.addMember(userId);
        }
        groupMap.put(groupId, group);
    }

    /* ---------------- EXPENSE ---------------- */

    public void addExpense(
            String groupId,
            ExpenseType expenseType,
            double amount,
            String paidBy,
            List<Split> splits,
            ExpenseMetaData metadata
    ) {

        Group group = groupMap.get(groupId);
        Map<String, Map<String, Double>> balanceSheet = group.getBalanceSheet();

        Expense expense = ExpenseService.createExpense(
                expenseType,
                amount,
                userMap.get(paidBy),
                splits,
                metadata
        );

        if (!expense.validate()) {
            throw new IllegalArgumentException("Invalid Expense");
        }

        expenses.add(expense);

        for (Split split : expense.getSplits()) {
            String paidTo = split.getUser().getId();

            balanceSheet.putIfAbsent(paidBy, new HashMap<>());
            balanceSheet.putIfAbsent(paidTo, new HashMap<>());

            // paidBy is owed money
            balanceSheet.get(paidBy).put(
                    paidTo,
                    balanceSheet.get(paidBy).getOrDefault(paidTo, 0.0)
                            + split.getAmount()
            );

            // paidTo owes money
            balanceSheet.get(paidTo).put(
                    paidBy,
                    balanceSheet.get(paidTo).getOrDefault(paidBy, 0.0)
                            - split.getAmount()
            );
        }
    }

    /* ---------------- SHOW ---------------- */

    public void showGroupBalances(String groupId) {
        Group group = groupMap.get(groupId);
        Map<String, Map<String, Double>> balanceSheet = group.getBalanceSheet();

        boolean empty = true;

        for (String u1 : balanceSheet.keySet()) {
            for (Map.Entry<String, Double> entry : balanceSheet.get(u1).entrySet()) {
                if (entry.getValue() > 0) {
                    empty = false;
                    printBalance(u1, entry.getKey(), entry.getValue());
                }
            }
        }

        if (empty) {
            System.out.println("No balances");
        }
    }

    public void showUserBalance(String groupId, String userId) {
        Group group = groupMap.get(groupId);
        Map<String, Double> balances = group.getBalanceSheet().get(userId);

        boolean empty = true;

        for (Map.Entry<String, Double> entry : balances.entrySet()) {
            if (entry.getValue() != 0) {
                empty = false;
                printBalance(userId, entry.getKey(), entry.getValue());
            }
        }

        if (empty) {
            System.out.println("No balances");
        }
    }

    /* ---------------- MANUAL SETTLEMENT ---------------- */

    public void settle(
            String groupId,
            String fromUser,
            String toUser,
            double amount
    ) {
        Group group = groupMap.get(groupId);
        Map<String, Map<String, Double>> balanceSheet = group.getBalanceSheet();

        balanceSheet.get(fromUser).put(
                toUser,
                balanceSheet.get(fromUser).getOrDefault(toUser, 0.0) + amount
        );

        balanceSheet.get(toUser).put(
                fromUser,
                balanceSheet.get(toUser).getOrDefault(fromUser, 0.0) - amount
        );
    }

    /* ---------------- AUTO SIMPLIFY (LEAST TRANSACTIONS) ---------------- */

    public void simplifyGroupBalances(String groupId) {
        Group group = groupMap.get(groupId);
        Map<String, Map<String, Double>> balanceSheet = group.getBalanceSheet();

        Map<String, Double> netBalance = new HashMap<>();

        for (String u1 : balanceSheet.keySet()) {
            for (Map.Entry<String, Double> entry : balanceSheet.get(u1).entrySet()) {
                netBalance.put(
                        u1,
                        netBalance.getOrDefault(u1, 0.0) + entry.getValue()
                );
            }
        }

        Queue<String> debtors = new LinkedList<>();
        Queue<String> creditors = new LinkedList<>();

        for (String user : netBalance.keySet()) {
            if (netBalance.get(user) < 0) debtors.add(user);
            else if (netBalance.get(user) > 0) creditors.add(user);
        }

        while (!debtors.isEmpty() && !creditors.isEmpty()) {
            String debtor = debtors.poll();
            String creditor = creditors.poll();

            double amount = Math.min(
                    -netBalance.get(debtor),
                    netBalance.get(creditor)
            );

            System.out.println(
                    userMap.get(debtor).getName() +
                            " pays " +
                            userMap.get(creditor).getName() +
                            ": " + amount
            );

            netBalance.put(debtor, netBalance.get(debtor) + amount);
            netBalance.put(creditor, netBalance.get(creditor) - amount);

            if (netBalance.get(debtor) < 0) debtors.add(debtor);
            if (netBalance.get(creditor) > 0) creditors.add(creditor);
        }
    }

    /* ---------------- UTIL ---------------- */

    private void printBalance(String u1, String u2, double amount) {
        String name1 = userMap.get(u1).getName();
        String name2 = userMap.get(u2).getName();

        if (amount > 0) {
            System.out.println(name2 + " owes " + name1 + ": " + amount);
        } else if (amount < 0) {
            System.out.println(name1 + " owes " + name2 + ": " + Math.abs(amount));
        }
    }

    public User getUser(String userId) {
        return userMap.get(userId);
    }
}