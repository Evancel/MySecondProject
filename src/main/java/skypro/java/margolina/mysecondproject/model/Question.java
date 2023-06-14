package skypro.java.margolina.mysecondproject.model;

import java.util.Objects;

public class Question {
    private String questionText;
    private String answerText;

    public Question(String question, String answer){
        this.questionText = question;
        this.answerText = answer;
    }

    public String getQuestion() {
        return questionText;
    }

    public String getAnswer() {
        return answerText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(questionText, question1.questionText) && Objects.equals(answerText, question1.answerText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionText, answerText);
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + questionText + '\'' +
                ", answer='" + answerText + '\'' +
                '}';
    }

}
