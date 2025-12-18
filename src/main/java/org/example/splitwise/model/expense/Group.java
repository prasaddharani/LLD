package org.example.splitwise.model.expense;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class Group {
    private String groupId;
    private String name;
    private Set<String> members;

    // Each group has its own balance sheet
    private Map<String, Map<String, Double>> balanceSheet;

    public Group(String groupId, String name) {
        this.groupId = groupId;
        this.name = name;
        this.members = new HashSet<>();
        this.balanceSheet = new HashMap<>();
    }

    public String getGroupId() {
        return groupId;
    }

    public Set<String> getMembers() {
        return members;
    }

    public Map<String, Map<String, Double>> getBalanceSheet() {
        return balanceSheet;
    }

    public void addMember(String userId) {
        members.add(userId);
        balanceSheet.putIfAbsent(userId, new HashMap<>());
    }
}
