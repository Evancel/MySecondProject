package skypro.java.margolina.mysecondproject.service.implementations;

import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import skypro.java.margolina.mysecondproject.exceptions.RepositoryNotExistsException;
import skypro.java.margolina.mysecondproject.model.Question;
import skypro.java.margolina.mysecondproject.repository.MathQuestionRepository;
import skypro.java.margolina.mysecondproject.repository.QuestionRepository;
import skypro.java.margolina.mysecondproject.service.QuestionService;

import java.util.*;

@Service
//@Scope(value= ConfigurablePropertyAccessor)
//@ConditionalOnMissingBean(MathQuestionRepository.class)
public class MathQuestionService implements QuestionService {

    private final MathQuestionRepository mathQuestionRepository;
    private final RandomService random;

    public MathQuestionService(
  //          @Qualifier("mathQuestionRepository")
            MathQuestionRepository mathRepository,
                               RandomService randomService){

        this.mathQuestionRepository = mathRepository;
        this.random = randomService;
    }

    @Override
    public Question add(String question, String answer) {

        return mathQuestionRepository.add(question, answer);
    }

    @Override
    public Question add(Question question) {
        return mathQuestionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return mathQuestionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return mathQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        //приводим Set к List, т.к. нужно обратиться к Question по index
        List<Question> listOfQuestions = new ArrayList<>(mathQuestionRepository.getAll());
        return listOfQuestions.get(random.getRandomInt(listOfQuestions.size()));
    }
}
