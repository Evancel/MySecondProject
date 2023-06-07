package skypro.java.margolina.mysecondproject.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skypro.java.margolina.mysecondproject.model.Question;
import skypro.java.margolina.mysecondproject.service.QuestionService;

import java.util.Collection;
@RestController

public class MathQuestionController {
    private final QuestionService questionService;

    public MathQuestionController(@Qualifier("mathQuestionService") QuestionService questionService){
        this.questionService = questionService;
    }

    @GetMapping("/math/add")
    public Question addQuestion(
            @RequestParam(name ="question")  String question,
            @RequestParam(name="answer") String answer){
        return questionService.add(question, answer);
    }

    @GetMapping("/math")
    public Collection<Question> getQuestions(){
        return questionService.getAll();
    }

    @GetMapping("/math/remove")
    public Question removeQuestion(
            @RequestParam (name ="question")  String question,
            @RequestParam(name="answer") String answer){
        return questionService.remove(new Question(question, answer));
    }

    @GetMapping("/math/test")
    public String helloWorld(){
        return "HelloWorld";
    }
}
