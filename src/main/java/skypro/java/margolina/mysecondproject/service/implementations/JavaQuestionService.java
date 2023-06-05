package skypro.java.margolina.mysecondproject.service.implementations;

import org.springframework.stereotype.Service;
import skypro.java.margolina.mysecondproject.exceptions.EmptyAnswerException;
import skypro.java.margolina.mysecondproject.exceptions.EmptyQuestionException;
import skypro.java.margolina.mysecondproject.exceptions.QuestionNotFoundException;
import skypro.java.margolina.mysecondproject.repository.Question;
import skypro.java.margolina.mysecondproject.service.QuestionService;

import java.util.*;

@Service

public class JavaQuestionService implements QuestionService {

    Set<Question> questions;
    private final int MAX_NUMBER = 10;

    public JavaQuestionService() {
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

    @Override
    public Question getRandomQuestion() {
        Random rand = new Random();
        //приводим Set к List, т.к. нужно обратиться к Question по index
        List<Question> listOfQuestions = new ArrayList<>(questions);
        return listOfQuestions.get(rand.nextInt(listOfQuestions.size()));
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
