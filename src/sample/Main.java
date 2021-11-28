package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(DateFormatter.stringConverter.toString(LocalDate.of(1999, 5,22)));
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        primaryStage.setTitle("VIVO TV");
        primaryStage.setScene(new Scene(root, 890, 630));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}