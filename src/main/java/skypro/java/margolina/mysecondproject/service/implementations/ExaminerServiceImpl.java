package skypro.java.margolina.mysecondproject.service.implementations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import skypro.java.margolina.mysecondproject.exceptions.EmptyCollectionException;
import skypro.java.margolina.mysecondproject.exceptions.NotEnoughQuestionsException;
import skypro.java.margolina.mysecondproject.model.Question;
import skypro.java.margolina.mysecondproject.service.ExaminerService;
import skypro.java.margolina.mysecondproject.service.QuestionService;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;
    private final RandomService rand;

    public ExaminerServiceImpl(@Qualifier("javaQuestionService")
                               QuestionService questionService1,
                               @Qualifier ("mathQuestionService")
                               QuestionService questionService2,
                               RandomService randomService

    ){
        this.javaQuestionService = questionService1;
        this.mathQuestionService = questionService2;
        this.rand = randomService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        validateJavaQuestionCollection();
        if((javaQuestionService.getAll().size() + mathQuestionService.getAll().size()) < amount){
            throw new NotEnoughQuestionsException();
        }

        //проверяем, что вопросов и по Java, и по математике хватает для выбора
        int javaAmount = 0;

        do{
            javaAmount = rand.getRandomInt(amount);
        }while(javaQuestionService.getAll().size()<javaAmount
                || mathQuestionService.getAll().size()<(amount - javaAmount));

        Set<Question> examQuestions = new HashSet<>();

        do{
            examQuestions.add(javaQuestionService.getRandomQuestion());
        }while(examQuestions.size()<javaAmount);

        do{
            examQuestions.add(mathQuestionService.getRandomQuestion());
        }while(examQuestions.size()<amount);

        return examQuestions.stream().collect(Collectors.toUnmodifiableSet());
    }

    private void validateJavaQuestionCollection(){
        if(javaQuestionService.getAll().size()==0 || mathQuestionService.getAll().size()==0){
            throw new EmptyCollectionException();
        }
    }
}
