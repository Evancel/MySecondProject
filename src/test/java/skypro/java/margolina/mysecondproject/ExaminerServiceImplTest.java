package skypro.java.margolina.mysecondproject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skypro.java.margolina.mysecondproject.repository.Question;
import skypro.java.margolina.mysecondproject.service.implementations.ExaminerServiceImpl;
import skypro.java.margolina.mysecondproject.service.implementations.JavaQuestionService;

import java.util.Collection;
import java.util.HashSet;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    public void checkGetQuestions(){
        String q1 = "Вопрос1?";
        String a1 = "Ответ1.";
        Question curr1 = new Question(q1,a1);
        questionService.add(curr1);

        Collection<Question> expected = new HashSet<>();
        expected.add(curr1);

        when(questionService.getAll()).thenReturn(expected);
        when(questionService.getRandomQuestion()).thenReturn(curr1);

        Assertions.assertEquals(expected,examinerService.getQuestions(1));
        verify(questionService, atLeastOnce()).getRandomQuestion();
    }
}
