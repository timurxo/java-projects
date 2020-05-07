package sample.model;

/*
    INTERACTING WITH TXT FILE (SAVING & RETRIEVING DATA)
 */


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class TodoData {

    private static String filename = "listOfTasks.txt";

    // create observable list -> to make data binding work
    private ObservableList<TodoTask> todoTasks;
    // manipulate date
    private DateTimeFormatter formatter;

    // ---- create singleton -> to access 1 instance of the class at the time ----
    private static TodoData instance = new TodoData();

    public static TodoData getInstance() {
        return instance;
    }

    // private -> can't instantiate new object of this class
    private TodoData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }
    // ------------------------------------------------------------------


    public ObservableList<TodoTask> getTodoTasks() {
        return todoTasks;
    }

    public void addTodoTask(TodoTask task) {
        todoTasks.add(task);
    }

    // ----------------------------- save to txt file -------------------------------
    public void storeTodoTasks() throws IOException {

        Path path = Paths.get(filename);
        // point to the location where the file is
        BufferedWriter bw = Files.newBufferedWriter(path);

        try {
            // iterate through list of tasks
            Iterator<TodoTask> iterator = todoTasks.iterator();

            while(iterator.hasNext()) {
                TodoTask task = iterator.next();
                bw.write(String.format("%s\t%s\t%s",
                        task.getNameOfTodoTask(),
                        task.getDetails(),
                        task.getDeadline().format(formatter)));

                bw.newLine();
            }

        } finally { // close
            if(bw != null) {
                bw.close();
            }
        }

    }



    // ----------------------------- load from txt file -------------------------------
    public void loadTodoTasks() throws IOException {

        // observable -> help automatically handle changes
        todoTasks = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);
        String input;

        try {
            // loop that goes through and retrieves the data
            while((input = br.readLine()) != null) {
                String[] taskInfo = input.split("\t");

                // convert
                String taskTitle = taskInfo[0];
                String details = taskInfo[1];
                String dateString = taskInfo[2];

                // convert date so we can read it
                LocalDate date = LocalDate.parse(dateString, formatter);

                // add to do item
                TodoTask task = new TodoTask(taskTitle, details, date);
                todoTasks.add(task);

            }


        } finally {
            // make sure we have valid object before we try to close it
            if(br != null) {
                br.close();
            }
        }

    }


    // ----------------------------- delete item -------------------------------
    public void deleteTodoTask(TodoTask task) {
        todoTasks.remove(task);
    }

}
