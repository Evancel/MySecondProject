package skypro.java.margolina.mysecondproject.service;

import skypro.java.margolina.mysecondproject.model.Question;

import java.util.Collection;

public interface ExaminerService {
    public Collection<Question> getQuestions(int amount);
 //   public Collection<Question> getQuestions1(int amount);
}
