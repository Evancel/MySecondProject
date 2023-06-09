package skypro.java.margolina.mysecondproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import skypro.java.margolina.mysecondproject.model.Question;
import skypro.java.margolina.mysecondproject.service.implementations.ExaminerServiceImpl;
import skypro.java.margolina.mysecondproject.service.implementations.JavaQuestionService;
import skypro.java.margolina.mysecondproject.service.implementations.MathQuestionService;
import skypro.java.margolina.mysecondproject.service.implementations.RandomService;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionServiceMock;
    @Mock
    private MathQuestionService mathQuestionServiceMock;
    @Mock
    private RandomService rand;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

//    @BeforeEach
//    public void setup(){
//        examinerService.init();
//    }
    @Test
    public void checkGetQuestions(){
        int maxAmount=2;

        String q1 = "Вопрос1?";
        String a1 = "Ответ1.";
        Question curr1 = new Question(q1,a1);

        String q2 = "Вопрос2?";
        String a2 = "Ответ2.";
        Question curr2 = new Question(q2,a2);

        String q3 = "Вопрос3?";
        String a3 = "Ответ3.";
        Question curr3 = new Question(q3,a3);

        Collection<Question> examSet = new HashSet<>();
        Collection<Question> expected = new HashSet<>();
        expected.add(curr1);
        expected.add(curr2);
        expected.add(curr3);

        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(curr1);
        assertTrue(examSet.add(javaQuestionServiceMock.getRandomQuestion()));
        verify(javaQuestionServiceMock,times(examSet.size())).getRandomQuestion();

        when(mathQuestionServiceMock.getRandomQuestion()).thenReturn(curr2);
        assertTrue(examSet.add(mathQuestionServiceMock.getRandomQuestion()));
        verify(mathQuestionServiceMock,times(1)).getRandomQuestion();

        when(mathQuestionServiceMock.getRandomQuestion()).thenReturn(curr3);
        assertTrue(examSet.add(mathQuestionServiceMock.getRandomQuestion()));
        assertEquals(expected,examSet);
        verify(mathQuestionServiceMock,times(2)).getRandomQuestion();
    }
}
