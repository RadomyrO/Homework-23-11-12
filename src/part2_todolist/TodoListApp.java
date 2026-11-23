package part2_todolist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Part 2. Simple To-Do List with File Storage
public class TodoListApp {

    static final String FILE_NAME = "todo.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Part 2.1 Load tasks
        List<String> tasks = loadTasks();

        // Part 2.2 Menu loop
        while (true) {
            System.out.println("==== TODO MENU ====");
            System.out.println("1 - Show all tasks");
            System.out.println("2 - Add a new task");
            System.out.println("3 - Save and exit");
            System.out.print("Choose option: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    showTasks(tasks);
                    break;
                case "2":
                    addTask(tasks, sc);
                    break;
                case "3":
                    saveTasks(tasks);
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Wrong option, try again.");
            }
            System.out.println();
        }
    }

    // read todo.txt into list
    static List<String> loadTasks() {
        List<String> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);

        // no file - empty list
        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    tasks.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    // Part 2.3 Show all tasks
    static void showTasks(List<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    // Part 2.4 Add a new task (only in memory)
    static void addTask(List<String> tasks, Scanner sc) {
        System.out.print("Enter new task description: ");
        String task = sc.nextLine().trim();

        if (task.isEmpty()) {
            System.out.println("Empty task, skipped.");
            return;
        }
        tasks.add(task);
        System.out.println("Task added.");
    }

    // Part 2.5 Save and exit
    static void saveTasks(List<String> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String t : tasks) {
                bw.write(t);
                bw.newLine();
            }
            System.out.println("Saved " + tasks.size() + " task(s) to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
