package quiz.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import quiz.Main;

import java.io.IOException;

public class AppController {
    private Stage primaryStage;

    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initRootLayout() {
        try {
            this.primaryStage.setTitle("QUIZ");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/quiz/view/MainWindow.fxml"));
            Pane rootLayout = loader.load();

            MainWindowController mainWindowController= loader.getController();
            mainWindowController.setAppController(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit(){
        primaryStage.close();
    }
}
