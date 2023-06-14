package skypro.java.margolina.mysecondproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skypro.java.margolina.mysecondproject.model.Question;
import skypro.java.margolina.mysecondproject.service.QuestionService;
import skypro.java.margolina.mysecondproject.service.implementations.ExaminerServiceImpl;
import skypro.java.margolina.mysecondproject.service.implementations.JavaQuestionService;
import skypro.java.margolina.mysecondproject.service.implementations.MathQuestionService;
import skypro.java.margolina.mysecondproject.service.implementations.RandomService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private QuestionService javaQuestionServiceMock;
    @Mock
    private QuestionService mathQuestionServiceMock;
    @Mock
    private RandomService rand;
  //  @InjectMocks
    private ExaminerServiceImpl examinerServiceTest;

    @Test
    public void checkGetQuestionsNew(){
        int maxAmount=5;
        ExaminerServiceImpl examinerServiceTest = new ExaminerServiceImpl(javaQuestionServiceMock,
                mathQuestionServiceMock,rand);


        String q1 = "Question1?";
        String a1 = "Answer1.";
        Question curr1 = new Question(q1,a1);

        String q2 = "Question2?";
        String a2 = "Answer2.";
        Question curr2 = new Question(q2,a2);

        String q3 = "Question3?";
        String a3 = "Answer3.";
        Question curr3 = new Question(q3,a3);

        String q4 = "Question4?";
        String a4 = "Answer4.";
        Question curr4 = new Question(q4,a4);

        String q5 = "Question5?";
        String a5 = "Answer5.";
        Question curr5 = new Question(q5,a5);

        Set<Question> expected = new HashSet<>();
        expected.add(curr1);
        expected.add(curr2);
        expected.add(curr3);
        expected.add(curr4);
        expected.add(curr5);

        given(javaQuestionServiceMock.getAll()).willReturn(Set.of(curr1,curr3,curr5));
        assertEquals(3,javaQuestionServiceMock.getAll().size());
        given(mathQuestionServiceMock.getAll()).willReturn(Set.of(curr2, curr4));
        assertEquals(2,mathQuestionServiceMock.getAll().size());
        given(rand.getRandomInt(maxAmount)).willReturn(3);
        assertEquals(3,rand.getRandomInt(maxAmount));
        given(javaQuestionServiceMock.getRandomQuestion()).willReturn(curr1,curr3,curr5);
//        assertTrue(expected.add(javaQuestionServiceMock.getRandomQuestion()));
//        assertTrue(expected.add(javaQuestionServiceMock.getRandomQuestion()));
//        assertTrue(expected.add(javaQuestionServiceMock.getRandomQuestion()));
        given(mathQuestionServiceMock.getRandomQuestion()).willReturn(curr2,curr4);
//        assertTrue(expected.add(mathQuestionServiceMock.getRandomQuestion()));
//        assertTrue(expected.add(mathQuestionServiceMock.getRandomQuestion()));
//        System.out.println(expected);
        assertEquals(expected, examinerServiceTest.getQuestions(maxAmount));
        verify(javaQuestionServiceMock,times(3)).getRandomQuestion();
        verify(mathQuestionServiceMock,times(2)).getRandomQuestion();
    }
}
