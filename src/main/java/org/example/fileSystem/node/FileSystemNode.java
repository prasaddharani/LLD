package org.example.fileSystem.node;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class FileSystemNode {

    protected String name;
    protected Map<String, FileSystemNode> children;
    protected LocalDateTime createdAt;
    protected LocalDateTime modifiedAt;

    public FileSystemNode(String name) {
        this.name = name;
        this.children = new HashMap<>();
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public String getName() {
        return this.name;
    }

    public boolean hasChild(String name) {
        return children.containsKey(name);
    }

    public FileSystemNode getChild(String name) {
        return children.get(name);
    }

    public void addChild(String name, FileSystemNode node) {
        children.put(name, node);
        updateModifiedTime();
    }

    public boolean removeChild(String name) {
        if (!hasChild(name)) {
            return false;
        }
        this.children.remove(name);
        updateModifiedTime();
        return true;
    }

    public Collection<FileSystemNode> getChildren() {
        return children.values();
    }

    public void updateModifiedTime() {
        this.modifiedAt = LocalDateTime.now();
    }

    public abstract boolean isFile();

    public abstract void display(int depth);
}
