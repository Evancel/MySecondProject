package skypro.java.margolina.mysecondproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skypro.java.margolina.mysecondproject.repository.Question;
import skypro.java.margolina.mysecondproject.service.QuestionService;

import java.util.Collection;

@RestController
public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @GetMapping("/java/add")
    public Question addQuestion(
            @RequestParam (name ="question")  String question,
            @RequestParam(name="answer") String answer){
        return questionService.add(question, answer);
    }

    @GetMapping("/java")
    public Collection<Question> getQuestions(){
        return questionService.getAll();
    }

    @GetMapping("/java/remove")
    public Question removeQuestion(
            @RequestParam (name ="question")  String question,
            @RequestParam(name="answer") String answer){
        return questionService.remove(new Question(question, answer));
    }
}
