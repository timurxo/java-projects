package sample;

/*
    HANDLE INTERACTION BETWEEN UI AND DATA MODEL
 */


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import sample.model.TodoData;
import sample.model.TodoTask;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Controller {

    private List<TodoTask> todoTasks;

    @FXML
    private ListView<TodoTask> todoListView;
    @FXML
    private TextArea taskDetailsTextArea;
    @FXML
    private BorderPane todoMainWindow;
    @FXML
    private ContextMenu listContextMenu;
    @FXML
    private Label deadlineLabel;

    // to sort tasks by deadline
    private FilteredList<TodoTask> filteredList;

    public void initialize() {

        // ---------------- when user right clicks task ----------------
        // create pop up that appears in response to the right click (content menu)
        listContextMenu = new ContextMenu();
        MenuItem deleteCurrentTask = new MenuItem("Delete task");

        // deal with right click action
        deleteCurrentTask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // task - currently selected task
                TodoTask task = todoListView.getSelectionModel().getSelectedItem();
                deleteTask(task);
            }
        });
        // add delete option to context menu
        listContextMenu.getItems().addAll(deleteCurrentTask);
        // --------------------------------------------------------------


        // -------------------------- EVERY TIME VALUE CHANGES --------------------------
        // handle event any time the value changes
        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoTask>() {
            @Override
            public void changed(ObservableValue<? extends TodoTask> observableValue, TodoTask oldValue, TodoTask newValue) {
                // show details of currently selected task on the text area
                if(newValue != null) {
                    TodoTask task = todoListView.getSelectionModel().getSelectedItem();
                    taskDetailsTextArea.setText(task.getDetails());

                    // show date
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                    deadlineLabel.setText(df.format(task.getDeadline()));
                }
            }
        });

        filteredList = new FilteredList<TodoTask>(TodoData.getInstance().getTodoTasks());

        // ---------------------------- SORT TASKS BY DEADLINE -------------------------------
        SortedList<TodoTask> sortedList = new SortedList<>(filteredList, new Comparator<TodoTask>() {
            // compare by their deadlines
            @Override
            public int compare(TodoTask o1, TodoTask o2) {
                return o1.getDeadline().compareTo(o2.getDeadline());
            }
        });

        // ---------------------------- POPULATE LIST VIEW with list -------------------------------
        todoListView.setItems(sortedList);
        // make possible to select only 1 task at a time
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        // auto select 1st item in the list
        todoListView.getSelectionModel().selectFirst();

    }


    // ------------------- dialog event (show dialog to enter new task) -------------
    @FXML
    public void newTaskDialog() {

        Dialog<ButtonType> dialog = new Dialog<>();

        // get stage of main window
        dialog.initOwner(todoMainWindow.getScene().getWindow());
        dialog.setTitle("Add new task");
        dialog.setHeaderText("Here you can create new task");

        // load fxml file for dialog
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("tasksDialog.fxml"));

        try {
            // get controller from fxml loader instance
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e) {
            return;
        }

        // add buttons
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        // block main window
        Optional<ButtonType> result = dialog.showAndWait(); // showAndWait() - method if you need to block the caller until the modal stage is hidden (closed)

        // if user pressed "apply" - add new task to the list; else do nothing
        if(result.isPresent() && result.get() == ButtonType.OK) {

            TasksDialogController controller = fxmlLoader.getController();
            TodoTask newItem = controller.processResults();

            // auto select new item
            todoListView.getSelectionModel().select(newItem);
        }

    }

    @FXML
    public void exitApp() {
        Platform.exit();
    }

    // ------------------- delete event (when "delete" pressed on keyboard) -------------
    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        TodoTask selectedTask = todoListView.getSelectionModel().getSelectedItem();

        // check if key was pressed
        if(selectedTask != null) {
            // check if key was "delete"
            if(keyEvent.getCode().equals(KeyCode.DELETE)) {
                deleteTask(selectedTask);
            }
        }
    }


    // ------------------- delete task -------------
    public void deleteTask(TodoTask task) {

        // alert user before deleting task
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete task?");
        alert.setHeaderText("Deleting task: " + task.getNameOfTodoTask());
        alert.setContentText("Are you sure?");

        // actually show the dialog
        Optional<ButtonType> result = alert.showAndWait();

        // check what button was pressed
        if(result.isPresent() && (result.get() == ButtonType.OK)) {
            TodoData.getInstance().deleteTodoTask(task);
        }

    }

}
