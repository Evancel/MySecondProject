package skypro.java.margolina.mysecondproject.service.implementations;

import org.springframework.stereotype.Service;
import skypro.java.margolina.mysecondproject.exceptions.NotEnoughQuestionsException;
import skypro.java.margolina.mysecondproject.repository.Question;
import skypro.java.margolina.mysecondproject.service.ExaminerService;
import skypro.java.margolina.mysecondproject.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private Random random;
    private QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService){
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if(questionService.getAll().size()<amount){
            throw new NotEnoughQuestionsException();
        }
        Set<Question> examQuestions = new HashSet<>();
        while(examQuestions.size()<amount){
            examQuestions.add(questionService.getRandomQuestion());
        }
        return examQuestions.stream().collect(Collectors.toUnmodifiableSet());
    }
}
