import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Simple console TODO application.
 *
 * <p>The application allows the user to:
 * <ul>
 *     <li>add tasks,</li>
 *     <li>display all tasks,</li>
 *     <li>mark tasks as completed,</li>
 *     <li>delete tasks,</li>
 *     <li>exit the application.</li>
 * </ul>
 *
 * <p>The program runs in a loop until the user chooses to exit.
 */
public class Main {

    private static final String OPTION_EXIT = "0";
    private static final String OPTION_ADD = "1";
    private static final String OPTION_SHOW = "2";
    private static final String OPTION_MARK_DONE = "3";
    private static final String OPTION_DELETE = "4";

    private static final String DONE_SUFFIX = " (done)";

    private static final String MESSAGE_ADDED = "Added.";
    private static final String MESSAGE_DELETED = "Deleted.";
    private static final String MESSAGE_INVALID_CHOICE = "Invalid choice.";
    private static final String MESSAGE_MARKED_DONE = "Marked as done.";
    private static final String MESSAGE_ALREADY_DONE = "Already marked as done.";
    private static final String MESSAGE_NO_TASKS = "(No tasks)";
    private static final String MESSAGE_EMPTY_TEXT = "Empty text is not allowed.";

    /**
     * Entry point of the application.
     *
     * <p>Handles user input, menu navigation,
     * and task management operations.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();

        while (true) {
            printMenu(); // display main menu
            System.out.print("Choose an option: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case OPTION_ADD: // add task
                    addTask(sc, tasks);
                    break;

                case OPTION_SHOW: // show tasks
                    printTasks(tasks);
                    break;

                case OPTION_MARK_DONE: // mark task as done
                    printTasks(tasks);

                    if (tasks.isEmpty()) {
                        break;
                    }

                    int idxDone = readIndex(sc, tasks.size(), "Task number to mark as done");

                    if (idxDone == -1) {
                        break;
                    }

                    markTaskAsDone(tasks, idxDone);
                    break;

                case OPTION_DELETE: // delete task
                    printTasks(tasks);

                    if (tasks.isEmpty()) {
                        break;
                    }

                    int idxDel = readIndex(sc, tasks.size(), "Task number to delete");

                    if (idxDel == -1) {
                        break;
                    }

                    deleteTask(tasks, idxDel);
                    break;

                case OPTION_EXIT: // exit application
                    System.out.println("Goodbye! Have a nice day!");
                    return;

                default: // invalid choice
                    System.out.println(MESSAGE_INVALID_CHOICE);
            }

            System.out.println();
        }
    }

    /**
     * Adds a new task to the task list.
     *
     * @param sc scanner used for reading input
     * @param tasks list of tasks
     */
    private static void addTask(Scanner sc, List<String> tasks) {
        String task = readNonEmptyString(sc, "Enter a new task: ");
        tasks.add(task);
        System.out.println(MESSAGE_ADDED);
    }

    /**
     * Marks the selected task as completed.
     *
     * @param tasks list of tasks
     * @param index index of the selected task
     */
    private static void markTaskAsDone(List<String> tasks, int index) {
        String original = tasks.get(index);

        if (!original.endsWith(DONE_SUFFIX)) {
            tasks.set(index, original + DONE_SUFFIX);
            System.out.println(MESSAGE_MARKED_DONE);
        } else {
            System.out.println(MESSAGE_ALREADY_DONE);
        }
    }

    /**
     * Deletes the selected task.
     *
     * @param tasks list of tasks
     * @param index index of the selected task
     */
    private static void deleteTask(List<String> tasks, int index) {
        tasks.remove(index);
        System.out.println(MESSAGE_DELETED);
    }

    /**
     * Prints the main application menu.
     */
    private static void printMenu() {
        System.out.println("=== TODO MENU ===");
        System.out.println(OPTION_ADD + ") Add task");
        System.out.println(OPTION_SHOW + ") Show tasks");
        System.out.println(OPTION_MARK_DONE + ") Mark as done");
        System.out.println(OPTION_DELETE + ") Delete task");
        System.out.println(OPTION_EXIT + ") Exit");
    }

    /**
     * Prints all tasks with numbering.
     *
     * @param tasks list of tasks to display
     */
    private static void printTasks(List<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(MESSAGE_NO_TASKS);
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, tasks.get(i));
        }
    }

    /**
     * Reads and validates a task index from user input.
     *
     * <p>The method keeps asking until the user enters:
     * <ul>
     *     <li>a valid number within range, or</li>
     *     <li>an empty input to cancel.</li>
     * </ul>
     *
     * @param sc scanner used for reading input
     * @param max maximum allowed index value
     * @param prompt text displayed to the user
     * @return zero-based index of the selected task,
     *         or -1 if the user cancels
     */
    private static int readIndex(Scanner sc, int max, String prompt) {
        while (true) {
            System.out.print(prompt + " (1-" + max + ", Enter = back): ");
            String s = sc.nextLine().trim();

            if (s.isEmpty()) {
                return -1;
            }

            try {
                int n = Integer.parseInt(s);

                if (n >= 1 && n <= max) {
                    return n - 1;
                }

            } catch (NumberFormatException ignored) {
            }

            System.out.println("Enter a number in the range 1-" + max + " or press Enter.");
        }
    }

    /**
     * Reads a non-empty string from user input.
     *
     * <p>The method prevents empty input
     * and keeps asking until valid text is entered.
     *
     * @param sc scanner used for reading input
     * @param prompt text displayed to the user
     * @return validated non-empty text
     */
    private static String readNonEmptyString(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();

            if (!s.isEmpty()) {
                return s;
            }

            System.out.println(MESSAGE_EMPTY_TEXT);
        }
    }
}