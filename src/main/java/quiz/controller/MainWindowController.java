package quiz.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import quiz.Main;
import quiz.items.Question;
import quiz.parser.Parser;

import java.io.File;
import java.io.IOException;

public class MainWindowController {
    @FXML
    private ListView questionList;
    @FXML
    private BorderPane mainWindow;


    private Parser parser;

    private AppController appController;

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void handleStartNewQuizAction(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        File selectedFile = chooser.showOpenDialog(null);
        if (selectedFile != null) {
            parser = new Parser();
            try {
                parser.initialize(selectedFile);

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait().filter(response -> response == ButtonType.OK);
                return;
            }
            refreshList();
        }
    }

    public void handleExitAction(ActionEvent actionEvent) {
        appController.exit();
    }

    public void handleQuestionAction(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() < 2) return;
        ((Question) questionList.getSelectionModel().getSelectedItem()).setRead(true);
        refreshList();
        System.out.println("Kliknięto " + ((Question) questionList.getSelectionModel().getSelectedItem()).getAnswerText());
        questionList.getSelectionModel().clearSelection();
    }

    private void refreshList() {
        questionList.setCellFactory(lv -> new ListCell<Question>() {
            @Override
            public void updateItem(Question question, boolean empty) {
                super.updateItem(question, empty);
                getStylesheets().clear();
                if (empty) {
                    setText("");
                } else {
                    setText("" + question.getQuestionNumber());
                    if (question.isRead()) {
                        getStylesheets().add(Main.class.getResource("/quiz/styles/readQuestionStyle.css").toExternalForm());
                    } else {
                        getStylesheets().add(Main.class.getResource("/quiz/styles/normalQuestionStyle.css").toExternalForm());
                    }
                }
            }
        });
        questionList.setItems(parser.getQuestionList());
    }

    public void handleClickBarAction(MouseEvent mouseEvent) {
        questionList.getSelectionModel().clearSelection();
    }
}
