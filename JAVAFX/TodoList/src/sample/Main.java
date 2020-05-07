package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.TodoData;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        primaryStage.setTitle("My Todo List");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    // --------------------  WHEN USER EXISTS APPLICATION --------------------
    // we will store items
    @Override
    public void stop() throws Exception {
        try {
            // access singleton -> save it to txt file
            TodoData.getInstance().storeTodoTasks();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    // -------------------- LOAD ITEMS FROM TXT FILE --------------------
    @Override
    public void init() throws Exception {
        try {
            // access singleton -> save it to txt file
            TodoData.getInstance().loadTodoTasks();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
