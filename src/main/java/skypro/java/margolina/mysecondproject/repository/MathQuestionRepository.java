package skypro.java.margolina.mysecondproject.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import skypro.java.margolina.mysecondproject.exceptions.EmptyAnswerException;
import skypro.java.margolina.mysecondproject.exceptions.EmptyQuestionException;
import skypro.java.margolina.mysecondproject.exceptions.QuestionNotFoundException;
import skypro.java.margolina.mysecondproject.model.Question;
import skypro.java.margolina.mysecondproject.service.implementations.MathQuestionService;

import javax.annotation.PostConstruct;
import java.util.*;
@Repository
public class MathQuestionRepository implements QuestionRepository
{
    private Set<Question> questions;

    public MathQuestionRepository() {
    }

    @PostConstruct
    public void init(){
        questions = new HashSet<>();
        add("MathQuestion1","MathAnswer1");
        add("MathQuestion2","MathAnswer2");
        add("MathQuestion3","MathAnswer3");
        add("MathQuestion4","MathAnswer4");
        add("MathQuestion5","MathAnswer5");
        add("MathQuestion6","MathAnswer6");
        add("MathQuestion7","MathAnswer7");
        add("MathQuestion8","MathAnswer8");
        add("MathQuestion9","MathAnswer9");
        add("MathQuestion10","MathAnswer10");
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
