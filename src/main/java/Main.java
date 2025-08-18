import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();

        while (true) {
            printMenu(); // display main menu
            System.out.print("Choose an option: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": // add task
                    String task = readNonEmptyString(sc, "Enter a new task: ");
                    tasks.add(task);
                    System.out.println("Added.");
                    break;

                case "2": // show tasks
                    printTasks(tasks);
                    break;

                case "3": // mark task as done
                    printTasks(tasks);
                    if (tasks.isEmpty()) break;
                    int idxDone = readIndex(sc, tasks.size(), "Task number to mark as done");
                    if (idxDone == -1) break;
                    String original = tasks.get(idxDone);
                    if (!original.endsWith(" (done)")) {
                        tasks.set(idxDone, original + " (done)");
                        System.out.println("Marked as done.");
                    } else {
                        System.out.println("Already marked as done.");
                    }
                    break;

                case "4": // delete task
                    printTasks(tasks);
                    if (tasks.isEmpty()) break;
                    int idxDel = readIndex(sc, tasks.size(), "Task number to delete");
                    if (idxDel == -1) break;
                    tasks.remove(idxDel);
                    System.out.println("Deleted.");
                    break;

                case "0": // exit application
                    System.out.println("Goodbye! Have a nice day!");
                    return;

                default: // invalid choice
                    System.out.println("Invalid choice.");
            }
            System.out.println();
        }
    }

    // print menu
    private static void printMenu() {
        System.out.println("=== TODO MENU ===");
        System.out.println("1) Add task");
        System.out.println("2) Show tasks");
        System.out.println("3) Mark as done");
        System.out.println("4) Delete task");
        System.out.println("0) Exit");
    }

    // print list of tasks with numbering
    private static void printTasks(List<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("(No tasks)");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, tasks.get(i));
        }
    }

    // read valid task index or -1 to cancel
    private static int readIndex(Scanner sc, int max, String prompt) {
        while (true) {
            System.out.print(prompt + " (1-" + max + ", Enter = back): ");
            String s = sc.nextLine().trim();
            if (s.isEmpty()) return -1; // cancel selection
            try {
                int n = Integer.parseInt(s);
                if (n >= 1 && n <= max) return n - 1;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Enter a number in the range 1-" + max + " or press Enter.");
        }
    }

    // safe input of non-empty text (won’t crash, keeps asking until something is entered)
    private static String readNonEmptyString(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Empty text is not allowed.");
        }
    }
}