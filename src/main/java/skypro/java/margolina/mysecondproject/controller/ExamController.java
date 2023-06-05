package skypro.java.margolina.mysecondproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skypro.java.margolina.mysecondproject.repository.Question;
import skypro.java.margolina.mysecondproject.service.ExaminerService;

import java.util.Collection;

@RestController
public class ExamController {
    private ExaminerService examinerService;
    public ExamController(ExaminerService examinerService){
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable ("amount") int amount){
        return examinerService.getQuestions(amount);
    }

    /*
    @GetMapping("/get/{amount}")
    public String getQuestions(@PathVariable ("amount") int amount){
        return "Amount" + amount;
    }
     */
}