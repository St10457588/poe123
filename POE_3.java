package poe._3;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class POE_3 {

    static String firstName;
    static String userName;
    static String surname;
    static String password;

    // Arrays to store task details
    static List<String> developers = new ArrayList<>();
    static List<String> taskNames = new ArrayList<>();
    static List<Integer> taskIDs = new ArrayList<>();
    static List<Integer> taskDurations = new ArrayList<>();
    static List<String> taskStatuses = new ArrayList<>();

    // Login functionality class
    static class Login {
        private boolean loggedIn = false;
        private String currentUser = "";

        public void registerUser(String fName, String lName, String uName, String pwd) {
            // Simulated registration logic; typically you'd store these details
            firstName = fName;
            surname = lName;
            userName = uName;
            password = pwd;
            System.out.println("User registered successfully.");
        }

        public void login(String username, String password) {
            
            if (username.equals(userName) && password.equals(password)) {
                loggedIn = true;
                currentUser = username;
                System.out.println("Welcome, " + username + ". You are now logged in.");
            } else {
                loggedIn = false;
                System.out.println("Login failed. Please check your username and password.");
            }
        }

        public boolean isLoggedIn() {
            return loggedIn;
        }

        public String getCurrentUser() {
            return currentUser;
        }
    }

    // UserInfo functionality class
    static class UserInfo {
        public void WelcomeNote(String fName, String lName) {
            System.out.println("Welcome to the application, " + fName + " " + lName + "!");
        }
    }

    // Task functionality class
    static class Task {
        private String taskName;
        private int taskID;
        private int taskDuration;
        private String taskStatus;
        private String developer;

        public Task(String taskName, int taskID, int taskDuration, String taskStatus, String developer) {
            this.taskName = taskName;
            this.taskID = taskID;
            this.taskDuration = taskDuration;
            this.taskStatus = taskStatus;
            this.developer = developer;
        }

        // Getters 
        public String getTaskName() {
            return taskName;
        }

        public int getTaskID() {
            return taskID;
        }

        public int getTaskDuration() {
            return taskDuration;
        }

        public String getTaskStatus() {
            return taskStatus;
        }

        public String getDeveloper() {
            return developer;
        }
    }

    // Method to prompt and collect registration details from user
    public static void userRegistration() {
        firstName = JOptionPane.showInputDialog("Please enter your first name");
        surname = JOptionPane.showInputDialog("Please enter your surname");
        userName = JOptionPane.showInputDialog("Please enter your username");
        password = JOptionPane.showInputDialog("Please enter your password");
    }

    // Method to add tasks
    public static void addTasks(int numTasks) {
        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter task name for task " + (i + 1) + ":");
            String taskDeveloper = JOptionPane.showInputDialog("Enter developer name for task " + (i + 1) + ":");
            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration in hours for task " + (i + 1) + ":"));

            // Prompting user to select task status
            String[] statusOptions = {"To Do", "Done", "Doing"};
            int statusChoice = JOptionPane.showOptionDialog(null, "Choose task status for task " + (i + 1) + ":",
                    "Task Status", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, statusOptions, statusOptions[0]);
            String taskStatus = statusOptions[statusChoice];

            int newTaskID = taskIDs.size() + 1;
            Task task = new Task(taskName, newTaskID, taskDuration, taskStatus, taskDeveloper);
            taskNames.add(task.getTaskName());
            taskIDs.add(task.getTaskID());
            taskDurations.add(task.getTaskDuration());
            taskStatuses.add(task.getTaskStatus());
            developers.add(task.getDeveloper());
            System.out.println("Task " + task.getTaskName() + " added successfully.");
        }
    }

    // Method that displays tasks with status 'done'
    public static void displayDoneTasks() {
        System.out.println("Tasks with status 'Done':");
        for (int i = 0; i < taskStatuses.size(); i++) {
            if ("Done".equalsIgnoreCase(taskStatuses.get(i))) {
                System.out.println("Developer: " + developers.get(i) +
                        ", Task Name: " + taskNames.get(i) +
                        ", Task Duration: " + taskDurations.get(i));
            }
        }
    }

    // Method that displays task with the longest duration
    public static void displayLongestTask() {
        int maxDuration = 0;
        int index = -1;
        for (int i = 0; i < taskDurations.size(); i++) {
            if (taskDurations.get(i) > maxDuration) {
                maxDuration = taskDurations.get(i);
                index = i;
            }
        }
        if (index != -1) {
            System.out.println("Task with the longest duration:");
            System.out.println("Developer: " + developers.get(index) +
                    ", Task Duration: " + taskDurations.get(index));
        }
    }

    // Method to search for a task by task name and display details
    public static void searchTaskByName(String taskName) {
        boolean found = false;
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskName)) {
                System.out.println("Task Name: " + taskNames.get(i) +
                        ", Developer: " + developers.get(i) +
                        ", Task Status: " + taskStatuses.get(i));
                found = true;
                break; // Stop searching after finding the task
            }
        }
        if (!found) {
            System.out.println("Task with name '" + taskName + "' not found.");
        }
    }

    // Method to search for tasks assigned to a specific developer and display details
    public static void searchTasksByDeveloper(String developerName) {
        boolean found = false;
        System.out.println("Tasks assigned to developer '" + developerName + "':");
        for (int i = 0; i < developers.size(); i++) {
            if (developers.get(i).equalsIgnoreCase(developerName)) {
                System.out.println("Task Name: " + taskNames.get(i) +
                        ", Task Status: " + taskStatuses.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks assigned to developer '" + developerName + "'.");
        }
    }

    // Method to delete a task by task name
    public static void deleteTaskByName(String taskName) {
        boolean found = false;
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskName)) {
                developers.remove(i);
                taskNames.remove(i);
                taskIDs.remove(i);
                taskDurations.remove(i);
                taskStatuses.remove(i);
                System.out.println("Task '" + taskName + "' deleted successfully.");
                found = true;
                break; // Exit loop after deleting the task
            }
        }
        if (!found) {
            System.out.println("Task with name '" + taskName + "' not found.");
        }
    }

    // Method to display a report of all tasks
    public static void displayTaskReport() {
        System.out.println("Task Report:");
        for (int i = 0; i < taskNames.size(); i++) {
            System.out.println("Task ID: " + taskIDs.get(i) +
                    ", Task Name: " + taskNames.get(i) +
                    ", Developer: " + developers.get(i) +
                    ", Task Duration: " + taskDurations.get(i) +
                    ", Task Status: " + taskStatuses.get(i));
        }
    }

    // Main method
    public static void main(String[] args) {
        Login logObject = new Login();

        // Initial prompt for registration or login
        String[] initialOptions = {"Register", "Login", "Exit"};
        int initialChoice = JOptionPane.showOptionDialog(null, "Choose an option", "Registration / Login",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, initialOptions, initialOptions[0]);

        if (initialChoice == 0) { // Register
            userRegistration(); // Collect user details
            logObject.registerUser(firstName, surname, userName, password); // Register user
            UserInfo info = new UserInfo(); // Create UserInfo instance
            info.WelcomeNote(firstName, surname); // Display welcome message
        } else if (initialChoice == 1) { // Login
            userName = JOptionPane.showInputDialog("Enter your username:");
            password = JOptionPane.showInputDialog("Enter your password:");
            logObject.login(userName, password); // Perform login
            if (logObject.isLoggedIn()) {
                UserInfo info = new UserInfo(); // Create UserInfo instance
                info.WelcomeNote(firstName, surname); // Display welcome message
            }
        } else { // Exit
            JOptionPane.showMessageDialog(null, "Exiting the application.");
            System.exit(0);
        }

        // Main menu loop
        while (true) {
            String[] options = {"Add Task", "Display Done Tasks", "Display Longest Task",
                    "Search Task by Name", "Search Tasks by Developer", "Delete Task", "Display Task Report", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Choose an option", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            if (choice == 0) { // Adds Tasks
                int numTasksToAdd = Integer.parseInt(JOptionPane.showInputDialog("How many tasks do you want to add?"));
                addTasks(numTasksToAdd);
            } else if (choice == 1) { // Display Done Tasks
                displayDoneTasks();
            } else if (choice == 2) { // Display Longest Task
                displayLongestTask();
            } else if (choice == 3) { // Search Task by Name
                String taskName = JOptionPane.showInputDialog("Enter the task name to search for the task:");
                searchTaskByName(taskName);
            } else if (choice == 4) { // Search Tasks by Developer name
                String developerName = JOptionPane.showInputDialog("Enter the developer name to search for the task:");
                searchTasksByDeveloper(developerName);
            } else if (choice == 5) { // Delete Task
                String taskName = JOptionPane.showInputDialog("Enter the task name to delete the task:");
                deleteTaskByName(taskName);
            } else if (choice == 6) { // show Task Report
                displayTaskReport();
            } else if (choice == 7) { // Exit
                JOptionPane.showMessageDialog(null, "Exiting the application.");
                System.exit(0);
            } else {
                break;
            }
        }
    }
}