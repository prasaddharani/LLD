package org.example.fileSystem;

import org.example.fileSystem.node.DirectoryNode;
import org.example.fileSystem.node.FileNode;
import org.example.fileSystem.node.FileSystemNode;

public class FileSystem {
    private FileSystemNode root;

    public FileSystem() {
        this.root = new DirectoryNode("/");
    }

    private boolean isValidPath(String path) {
        return path != null && path.startsWith("/") && path.length() > 0;
    }

    public boolean createPath(String path) {
        if (!isValidPath(path)) {
            return false;
        }
        String[] parts = path.split("/");
        FileSystemNode current = root;

        for (int i = 1; i < parts.length - 1; i++) {
            String part = parts[i];
            if (path.isEmpty()) {
                continue;
            }
            if (!current.hasChild(part)) {
                current.addChild(part, new DirectoryNode(part));
            }
            current = current.getChild(part);
            if (current.isFile()) {
                return false;
            }
        }

        String last = parts[parts.length - 1];
        if (last.isEmpty() || current.hasChild(last)) {
            return false;
        }
        FileSystemNode node = last.contains(".") ? new FileNode(last): new DirectoryNode(last);
        current.addChild(last, node);
        return true;
    }

    public FileSystemNode getNode(String path) {
        if (!isValidPath(path)) {
            return null;
        }
        if (path.equals("/")) {
            return root;
        }
        FileSystemNode current = root;
        String[] parts = path.split("/");
        for (int i = 1; i < parts.length; i++) {
            String part = parts[i];
            if (part.isEmpty()) {
                continue;
            }
            if (!current.hasChild(part)) {
                return null;
            }
            current = current.getChild(part);
        }
        return current;
    }

    public boolean deletePath(String path) {
        if (!isValidPath(path) || path.equals("/")) {
            return false;
        }

        int idx = path.lastIndexOf("/");
        String parentPath = idx == 0? "/": path.substring(0, idx);
        String name = path.substring(idx + 1);
        FileSystemNode parent = getNode(parentPath);
        if (parent == null || parent.isFile()) {
            return false;
        }
        return parent.removeChild(name);
    }

    public boolean setFileContent(String path, String content) {
        FileSystemNode node = getNode(path);
        if (node == null || !node.isFile()) {
            return false;
        }
        ((FileNode) node).setContent(content);
        return true;
    }

    public String getFileContent(String path) {
        FileSystemNode node = getNode(path);
        if (node == null || !node.isFile()) {
            return null;
        }
        return ((FileNode) node).getContent();
    }

    public void display() {
         root.display(0);
    }
}
