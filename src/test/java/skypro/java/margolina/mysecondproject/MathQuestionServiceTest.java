package skypro.java.margolina.mysecondproject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skypro.java.margolina.mysecondproject.exceptions.EmptyAnswerException;
import skypro.java.margolina.mysecondproject.exceptions.EmptyQuestionException;
import skypro.java.margolina.mysecondproject.exceptions.QuestionNotFoundException;
import skypro.java.margolina.mysecondproject.model.Question;
import skypro.java.margolina.mysecondproject.repository.MathQuestionRepository;
import skypro.java.margolina.mysecondproject.service.implementations.MathQuestionService;
import skypro.java.margolina.mysecondproject.service.implementations.RandomService;

import java.util.stream.Stream;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class MathQuestionServiceTest {
    @Mock
    private MathQuestionRepository mathQuestionRepositoryMock;
    @Mock
    //  @Qualifier("random")
    private RandomService randomServiceMock;
    @InjectMocks
    private MathQuestionService mathQuestionService;

    public void addQuestionsToOut(){
        String q1 = "Вопрос1?";
        String a1 = "Ответ1.";
        Question curr1 = new Question(q1,a1);
        mathQuestionService.add(curr1);

        String q2 = "Вопрос2?";
        String a2 = "Ответ2.";
        Question curr2 = new Question(q2,a2);
        mathQuestionService.add(curr2);

        String q3 = "Вопрос3?";
        String a3 = "Ответ3.";
        Question curr3 = new Question(q3,a3);
        mathQuestionService.add(curr3);

        String q4 = "Вопрос4?";
        String a4 = "Ответ4.";
        Question curr4 = new Question(q4,a4);
        mathQuestionService.add(curr4);

        String q5 = "Вопрос5?";
        String a5 = "Ответ5.";
        Question curr5 = new Question(q5,a5);
        mathQuestionService.add(curr5);

        String q6 = "Вопрос6?";
        String a6 = "Ответ6.";
        Question curr6 = new Question(q6,a6);
        mathQuestionService.add(curr6);

        String q7 = "Вопрос7?";
        String a7 = "Ответ7.";
        Question curr7 = new Question(q7,a7);
        mathQuestionService.add(curr7);

        String q8 = "Вопрос8?";
        String a8 = "Ответ8.";
        Question curr8 = new Question(q8,a8);
        mathQuestionService.add(curr8);

        String q9 = "Вопрос9?";
        String a9 = "Ответ9.";
        Question curr9 = new Question(q9,a9);
        mathQuestionService.add(curr9);

        String q10 = "Вопрос10?";
        String a10 = "Ответ10.";
        Question curr10 = new Question(q10,a10);
        mathQuestionService.add(curr10);
    }

    public Collection<Question> addQuestionsToExpected(){
        Set<Question> questionSet = new HashSet<>();
        String q1 = "Вопрос1?";
        String a1 = "Ответ1.";
        Question curr1 = new Question(q1,a1);
        questionSet.add(curr1);

        String q2 = "Вопрос2?";
        String a2 = "Ответ2.";
        Question curr2 = new Question(q2,a2);
        questionSet.add(curr2);

        String q3 = "Вопрос3?";
        String a3 = "Ответ3.";
        Question curr3 = new Question(q3,a3);
        questionSet.add(curr3);

        String q4 = "Вопрос4?";
        String a4 = "Ответ4.";
        Question curr4 = new Question(q4,a4);
        questionSet.add(curr4);

        String q5 = "Вопрос5?";
        String a5 = "Ответ5.";
        Question curr5 = new Question(q5,a5);
        questionSet.add(curr5);

        String q6 = "Вопрос6?";
        String a6 = "Ответ6.";
        Question curr6 = new Question(q6,a6);
        questionSet.add(curr6);

        String q7 = "Вопрос7?";
        String a7 = "Ответ7.";
        Question curr7 = new Question(q7,a7);
        questionSet.add(curr7);

        String q8 = "Вопрос8?";
        String a8 = "Ответ8.";
        Question curr8 = new Question(q8,a8);
        questionSet.add(curr8);

        String q9 = "Вопрос9?";
        String a9 = "Ответ9.";
        Question curr9 = new Question(q9,a9);
        questionSet.add(curr9);

        String q10 = "Вопрос10?";
        String a10 = "Ответ10.";
        Question curr10 = new Question(q10,a10);
        questionSet.add(curr10);

        return Collections.unmodifiableSet(questionSet);
    }

    @Test
    public void checkAddQuestionAnswer(){
        String question = "Что такое «переменная»?";
        String answer = "Переменная — это ячейка в памяти компьютера, которой можно присвоить имя и " +
                "в которой можно хранить данные.";
        Question q = new Question(question, answer);
        when(mathQuestionRepositoryMock.add(question, answer)).thenReturn(q);
        assertEquals(q,mathQuestionService.add(question, answer));
    }

    @Test
    public void checkAddQuestion(){
        String question = "Что такое «переменная»?";
        String answer = "Переменная — это ячейка в памяти компьютера, которой можно присвоить имя и " +
                "в которой можно хранить данные.";
        Question q = new Question(question, answer);
        when(mathQuestionRepositoryMock.add(q)).thenReturn(q);
        assertEquals(q,mathQuestionService.add(q));
    }

    @Test
    public void checkRemoveQuestion(){
        Collection<Question> exp = addQuestionsToExpected();
        addQuestionsToOut();
        Question q=new Question("Вопрос1?","Ответ1.");
        when(mathQuestionRepositoryMock.remove(q)).thenReturn(q);
        assertEquals(q, mathQuestionService.remove(q));
    }

    @Test
    public void checkGetAllQuestions(){
        addQuestionsToOut();
        when(mathQuestionRepositoryMock.getAll()).thenReturn(addQuestionsToExpected());
        assertEquals(addQuestionsToExpected(),mathQuestionService.getAll());
    }

    @Test
    public void checkGetNoneQuestions(){
        Collection<Question> exp = new HashSet<>();
        when(mathQuestionRepositoryMock.getAll()).thenReturn(exp);
        assertEquals(exp,mathQuestionService.getAll());
    }

    @Test
    public void checkGetRandomQuestion(){
        int index = 5;
        addQuestionsToOut();
        when(mathQuestionRepositoryMock.getAll()).thenReturn(addQuestionsToExpected());
        List<Question> listOfQuestions = new ArrayList<>(mathQuestionRepositoryMock.getAll());
        when(randomServiceMock.getRandomInt(listOfQuestions.size())).thenReturn(index);
        assertEquals(listOfQuestions.get(index),mathQuestionService.getRandomQuestion());
        verify(randomServiceMock,times(1)).getRandomInt(listOfQuestions.size());

        index=2;
        when(randomServiceMock.getRandomInt(listOfQuestions.size())).thenReturn(index);
        assertEquals(listOfQuestions.get(index),mathQuestionService.getRandomQuestion());
        verify(randomServiceMock,times(2)).getRandomInt(listOfQuestions.size());
    }


    //NegativeTests

    public static Stream<Arguments> checkInvalidQuestion(){
        return Stream.of(
                Arguments.of(null,"answer"),
                Arguments.of("","answer"),
                Arguments.of(" ","answer"),
                Arguments.of(null,null),
                Arguments.of("",""),
                Arguments.of(" "," ")
        );
    }

    public static Stream<Arguments> checkInvalidAnswer() {
        return Stream.of(
                Arguments.of("question", null),
                Arguments.of("question", ""),
                Arguments.of("question", " ")
        );
    }

    @ParameterizedTest
    @MethodSource("checkInvalidQuestion")
    public void checkNullQuestionAdd(String question, String answer){
        when(mathQuestionRepositoryMock.add(question, answer)).thenThrow(EmptyQuestionException.class);
        assertThrows(EmptyQuestionException.class,()-> mathQuestionService.add(question, answer));
    }

    @ParameterizedTest
    @MethodSource("checkInvalidAnswer")
    public void checkNullAnswerAdd(String question, String answer){
        when(mathQuestionRepositoryMock.add(question, answer)).thenThrow(EmptyAnswerException.class);
        assertThrows(EmptyAnswerException.class,()-> mathQuestionService.add(question, answer));
    }

    @Test
    public void checkRemoveQuestionNotFound(){
        addQuestionsToOut();
        Question q=new Question("Вопрос11?","Ответ11.");
        when(mathQuestionRepositoryMock.remove(q)).thenThrow(QuestionNotFoundException.class);
        assertThrows(QuestionNotFoundException.class,()->mathQuestionService.remove(q));
    }
}
