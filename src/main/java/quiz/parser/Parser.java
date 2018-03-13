package quiz.parser;

import com.google.common.io.Files;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import quiz.items.Question;
import quiz.items.QuestionComparator;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private List<Question> questionList = null;
    private final String[] graphicExtensions = {
            "bmp", "png", "jpeg", "gif", "jpg"
    };

    public void initialize(File textFile) throws IOException {
        parseQuestions(textFile);
        setImages(textFile.getParentFile());
    }

    public ObservableList<Question> getQuestionList() {
        return new ObservableListWrapper<>(questionList);
    }

    public void parseQuestions(File file) throws IOException {
        if(!Files.getFileExtension(file.getAbsolutePath()).equals("txt")){
            throw new IOException("Wrong file type");
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        String question = "";
        String answer;
        questionList = new ArrayList<>();
        int counter = 0;
        try {
            while ((line = br.readLine()) != null) {
                if (question.equals("")) {
                    question = line;
                } else {
                    answer = line;
                    questionList.add(new Question(counter++, question, answer));
                    question = "";
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        questionList.sort(new QuestionComparator());
    }

    public void setImages(File directory) throws IOException {
        if (!directory.isDirectory()) {
            throw new IOException("Incorrect directory");
        }
        for (File file : directory.listFiles()) {
            String[] fileName = file.getName().split("\\.");
            if (Arrays.asList(graphicExtensions).contains(Files.getFileExtension(file.getAbsolutePath()))) {
                int imageNumber;
                try {
                    imageNumber = Integer.parseInt(fileName[0].substring(0, fileName[0].length() - 2));
                } catch (NumberFormatException nfe) {
                    continue;
                }
                if (imageNumber < questionList.size() && imageNumber >= 0) {
                    if (fileName[0].charAt(fileName[0].length() - 1) == 'q') {
                        questionList.get(imageNumber).setQuestionImage(new Image(file.getAbsolutePath()));
                    } else if (fileName[0].charAt(fileName[0].length() - 1) == 'a') {
                        questionList.get(imageNumber).setAnswerImage(new Image(file.getAbsolutePath()));
                    }
                }
            }
        }


    }


}
