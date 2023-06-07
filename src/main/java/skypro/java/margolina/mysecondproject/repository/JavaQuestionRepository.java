package skypro.java.margolina.mysecondproject.repository;

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
