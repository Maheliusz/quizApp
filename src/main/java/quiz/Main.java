package quiz;

import javafx.application.Application;
import javafx.stage.Stage;
import quiz.controller.AppController;

public class Main extends Application {
    private Stage primaryStage;
    private AppController appController;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Quiz");

        this.appController = new AppController(primaryStage);
        this.appController.initRootLayout();
    }

    /*public static void main(String[] args){
//        String s = "dupa.avi";
//        System.out.print(s.split("\\.")[0]);
        Parser parser = new Parser();
        try {
            parser.getQuestionList("C:\\Documents\\Moje\\");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
