package org.example.fileSystem;

import java.util.Scanner;

public class FileSystemClient {

    public static void main(String[] args) {

        FileSystem fs = new FileSystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Commands:");
        System.out.println("create <path>");
        System.out.println("write <path> <content>");
        System.out.println("read <path>");
        System.out.println("delete <path>");
        System.out.println("display");
        System.out.println("exit");

        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split("\\s+", 3);

            try {
                switch (parts[0].toLowerCase()) {
                    case "create":
                        System.out.println(fs.createPath(parts[1]) ? "Created" : "Failed");
                        break;

                    case "write":
                        System.out.println(fs.setFileContent(parts[1], parts[2]) ? "Written" : "Failed");
                        break;

                    case "read":
                        System.out.println(fs.getFileContent(parts[1]));
                        break;

                    case "delete":
                        System.out.println(fs.deletePath(parts[1]) ? "Deleted" : "Failed");
                        break;

                    case "display":
                        fs.display();
                        break;

                    case "exit":
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid command");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
