# ToDo App (Java)

A simple console-based task management application.

## ✨ Features
- **Add a task** – the user enters text and it is saved to the list.
- **View tasks** – displays all current tasks with numbering.
- **Mark a task as done** – the selected task is marked with `(done)`.
- **Delete a task** – removes the selected task from the list.
- **Exit the application** – option to quit the program.

## 🖥 Usage
1. Run the application.
2. Choose an option from the menu (**1 – 4** or **0** to exit).
3. Follow the instructions in the console.
```text
=== TODO MENU ===
1) Add task
2) Show tasks
3) Mark as done
4) Delete task
0) Exit
Choose an option: 1
Enter a new task: Buy groceries
Added.

=== TODO MENU ===
1) Add task
2) Show tasks
3) Mark as done
4) Delete task
0) Exit
Choose an option: 2
1) Buy groceries

=== TODO MENU ===
1) Add task
2) Show tasks
3) Mark as done
4) Delete task
0) Exit
Choose an option:
```
## 🛠 Technologies
- **Java 23**
- **Collections** (`List`, `ArrayList`)
- **Scanner** for input reading
- **switch-case** for menu handling

## 📝 Notes
- Includes **input validation** for menu options and task selection.
- The application runs entirely in the console.
