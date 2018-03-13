package quiz.items;

import javafx.scene.image.Image;

public class Question {
    private final int questionNumber;
    private Image questionImage = null;
    private Image answerImage = null;
    private final String questionText;
    private final String answerText;
    private boolean read;

    public Question(int questionNumber, String questionText, String answerText) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.answerText = answerText;
        read = false;
    }

    public void setQuestionImage(Image questionImage) {
        this.questionImage = questionImage;
    }

    public void setAnswerImage(Image answerImage) {
        this.answerImage = answerImage;
    }


    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public Image getQuestionImage() {
        return questionImage;
    }

    public Image getAnswerImage() {
        return answerImage;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
