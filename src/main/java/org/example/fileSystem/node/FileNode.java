package org.example.fileSystem.node;

public class FileNode extends FileSystemNode{
    private String content;
    private String extension;

    public FileNode(String name) {
        super(name);
        this.extension = extractExtension(name);
    }

    private String extractExtension(String name) {
        int dot = name.lastIndexOf(".");
        return dot > 0 ? name.substring(dot + 1): "";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public void display(int depth) {
        String indent = " ".repeat(depth * 2);
        System.out.println(indent + "📄 " + name);
    }
}
