package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.model.TodoData;
import sample.model.TodoTask;

import java.time.LocalDate;

public class TasksDialogController {

    @FXML
    private TextField shortDescriptionField;
    @FXML
    private TextArea detailsArea;
    @FXML
    private DatePicker deadlinePicker;

    // AFTER "OK" IS PRESSED
    public TodoTask processResults() {

        String shortDescription = shortDescriptionField.getText().trim(); // trim() to get rid of extra spaces
        String details = detailsArea.getText().trim();
        LocalDate deadlineValue = deadlinePicker.getValue();

        TodoTask newItem = new TodoTask(shortDescription, details, deadlineValue);

        // get instance and add todo items
        TodoData.getInstance().addTodoTask(newItem);

        return newItem;

    }

}
