package skypro.java.margolina.mysecondproject.service.implementations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import skypro.java.margolina.mysecondproject.model.Question;
import skypro.java.margolina.mysecondproject.repository.JavaQuestionRepository;
import skypro.java.margolina.mysecondproject.repository.QuestionRepository;
import skypro.java.margolina.mysecondproject.service.QuestionService;

import java.util.*;

@Service
//@ConditionalOnProperty(value="setOfQuestions",havingValue = "javaSet",matchIfMissing = true)
public class JavaQuestionService implements QuestionService {

    private final JavaQuestionRepository javaQuestionRepository;
    private final RandomService random;

    public JavaQuestionService(@Qualifier("javaQuestionRepository")
                               JavaQuestionRepository javaRepository,
                               RandomService randomService) {
        this.javaQuestionRepository = javaRepository;
        this.random = randomService;
    }

    @Override
    public Question add(String question, String answer) {
        return javaQuestionRepository.add(question, answer);
    }

    @Override
    public Question add(Question question) {
        return javaQuestionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return javaQuestionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return javaQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        //приводим Set к List, т.к. нужно обратиться к Question по index
        List<Question> listOfQuestions = new ArrayList<>(javaQuestionRepository.getAll());
        return listOfQuestions.get(random.getRandomInt(listOfQuestions.size()));
    }
}
