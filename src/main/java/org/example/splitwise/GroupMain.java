package org.example.splitwise;

import org.example.splitwise.controller.ExpenseGroupManager;
import org.example.splitwise.enums.ExpenseType;
import org.example.splitwise.model.User;
import org.example.splitwise.model.split.EqualSplit;
import org.example.splitwise.model.split.ExactSplit;
import org.example.splitwise.model.split.PercentSplit;
import org.example.splitwise.model.split.Split;

import java.util.*;

public class GroupMain {

    public static void main(String[] args) {

        ExpenseGroupManager manager = new ExpenseGroupManager();

        // Users
        manager.addUser(new User("u1", "User1", "gaurav@workat.tech", "9876543210"));
        manager.addUser(new User("u2", "User2", "sagar@workat.tech", "9876543210"));
        manager.addUser(new User("u3", "User3", "hi@workat.tech", "9876543210"));
        manager.addUser(new User("u4", "User4", "mock-interviews@workat.tech", "9876543210"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            String[] commands = input.split(" ");
            String commandType = commands[0];

            switch (commandType) {

                /* ---------- CREATE GROUP ---------- */
                case "GROUP": {
                    String groupId = commands[1];
                    String groupName = commands[2];

                    List<String> members = new ArrayList<>();
                    for (int i = 3; i < commands.length; i++) {
                        members.add(commands[i]);
                    }

                    manager.createGroup(groupId, groupName, members);
                    break;
                }

                /* ---------- ADD EXPENSE ---------- */
                case "EXPENSE": {
                    String groupId = commands[1];
                    String paidBy = commands[2];
                    double amount = Double.parseDouble(commands[3]);
                    int noOfUsers = Integer.parseInt(commands[4]);

                    List<Split> splits = new ArrayList<>();

                    for (int i = 0; i < noOfUsers; i++) {
                        String userId = commands[5 + i];
                        // placeholder, actual amount set later
                        splits.add(new EqualSplit(manager.getUser(userId)));
                    }

                    String expenseType = commands[5 + noOfUsers];

                    switch (expenseType) {

                        case "EQUAL":
                            splits.clear();
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(
                                        new EqualSplit(
                                                manager.getUser(commands[5 + i])
                                        )
                                );
                            }
                            manager.addExpense(groupId, ExpenseType.EQUAL, amount, paidBy, splits, null);
                            break;

                        case "EXACT":
                            splits.clear();
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(
                                        new ExactSplit(
                                                manager.getUser(commands[5 + i]),
                                                Double.parseDouble(commands[6 + noOfUsers + i])
                                        )
                                );
                            }
                            manager.addExpense(groupId, ExpenseType.EXACT, amount, paidBy, splits, null);
                            break;

                        case "PERCENT":
                            splits.clear();
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(
                                        new PercentSplit(
                                                manager.getUser(commands[5 + i]),
                                                Double.parseDouble(commands[6 + noOfUsers + i])
                                        )
                                );
                            }
                            manager.addExpense(groupId, ExpenseType.PERCENT, amount, paidBy, splits, null);
                            break;
                    }
                    break;
                }

                /* ---------- SHOW ---------- */
                case "SHOW": {
                    if (commands.length == 2) {
                        manager.showGroupBalances(commands[1]);
                    } else {
                        manager.showUserBalance(commands[1], commands[2]);
                    }
                    break;
                }

                /* ---------- SETTLE ---------- */
                case "SETTLE": {
                    manager.settle(
                            commands[1],
                            commands[2],
                            commands[3],
                            Double.parseDouble(commands[4])
                    );
                    break;
                }

                /* ---------- SIMPLIFY ---------- */
                case "SIMPLIFY": {
                    manager.simplifyGroupBalances(commands[1]);
                    break;
                }
            }
        }
    }
}
