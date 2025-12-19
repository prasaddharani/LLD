package org.example.fileSystem.node;

public class DirectoryNode extends FileSystemNode{

    public DirectoryNode(String name) {
        super(name);
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public void display(int depth) {
        String indent = " ".repeat(depth * 2);
        System.out.println(indent + "📁 " + name + " (" + children.size() + " items)");
        for (FileSystemNode child : children.values()) {
            child.display(depth + 1);
        }
    }
}
