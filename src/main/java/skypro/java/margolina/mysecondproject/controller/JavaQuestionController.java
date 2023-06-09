package skypro.java.margolina.mysecondproject.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import skypro.java.margolina.mysecondproject.model.Question;
import skypro.java.margolina.mysecondproject.service.QuestionService;
import skypro.java.margolina.mysecondproject.service.implementations.JavaQuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/{subject}")
public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(@Qualifier ("javaQuestionService")
                                  QuestionService questionService){
        this.questionService = questionService;
    }

    @GetMapping("/add")
    public Question addQuestion(//@PathVariable
            @RequestParam (name ="question")  String question,
            @RequestParam(name="answer") String answer){
        return questionService.add(question, answer);
    }

    @GetMapping()
    public Collection<Question> getQuestions(){
        return questionService.getAll();
    }

    @GetMapping("/remove")
    public Question removeQuestion(
            @RequestParam (name ="question")  String question,
            @RequestParam(name="answer") String answer){
        return questionService.remove(new Question(question, answer));
    }
}
