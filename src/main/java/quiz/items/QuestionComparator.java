package quiz.items;

import java.util.Comparator;

public class QuestionComparator implements Comparator<Question> {
    @Override
    public int compare(Question o1, Question o2) {
        return Integer.compare(o1.getQuestionNumber(), o2.getQuestionNumber());
    }
}
