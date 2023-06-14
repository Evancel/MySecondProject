package skypro.java.margolina.mysecondproject.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import skypro.java.margolina.mysecondproject.exceptions.EmptyAnswerException;
import skypro.java.margolina.mysecondproject.exceptions.EmptyQuestionException;
import skypro.java.margolina.mysecondproject.exceptions.QuestionNotFoundException;
import skypro.java.margolina.mysecondproject.model.Question;

import javax.annotation.PostConstruct;
import java.util.*;
@Repository

public class JavaQuestionRepository implements QuestionRepository {
    private Set<Question> questions;

    public JavaQuestionRepository() {
    }

    @PostConstruct
    public void init(){
        questions = new HashSet<>();
        add("JavaQuestion1","JavaAnswer1");
        add("JavaQuestion2","JavaAnswer2");
        add("JavaQuestion3","JavaAnswer3");
        add("JavaQuestion4","JavaAnswer4");
        add("JavaQuestion5","JavaAnswer5");
        add("JavaQuestion6","JavaAnswer6");
        add("JavaQuestion7","JavaAnswer7");
        add("JavaQuestion8","JavaAnswer8");
        add("JavaQuestion9","JavaAnswer9");
        add("JavaQuestion10","JavaAnswer10");
    }

    @Override
    public Question add(String question, String answer) {
        validateQuestion(question);
        validateAnswer(answer);
        Question q = new Question(question, answer);
        questions.add(q);
        return q;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {

        if (!questions.contains(question)) {
            throw new QuestionNotFoundException();
        }

        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    private void validateQuestion(String question) {
        if (question == null || question.isBlank()) {
            throw new EmptyQuestionException();
        }
    }

    private void validateAnswer(String answer) {
        if (answer == null || answer.isBlank()) {
            throw new EmptyAnswerException();
        }
    }
}
