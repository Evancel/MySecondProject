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

import java.util.*;
import java.util.stream.Stream;

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
        mathQuestionService.add(new Question("Question1?", "Answer1."));
        mathQuestionService.add(new Question("Question2?", "Answer2."));
        mathQuestionService.add(new Question("Question3?", "Answer3."));
        mathQuestionService.add(new Question("Question4?", "Answer4."));
        mathQuestionService.add(new Question("Question5?", "Answer5."));
        mathQuestionService.add(new Question("Question6?", "Answer6."));
        mathQuestionService.add(new Question("Question7?", "Answer7."));
        mathQuestionService.add(new Question("Question8?", "Answer8."));
        mathQuestionService.add(new Question("Question9?", "Answer9."));
        mathQuestionService.add(new Question("Question10?", "Answer10."));
    }

    public Collection<Question> addQuestionsToExpected(){
        Set<Question> questionSet = new HashSet<>();

        questionSet.add(new Question("Question1?", "Answer1."));
        questionSet.add(new Question("Question2?", "Answer2."));
        questionSet.add(new Question("Question3?", "Answer3."));
        questionSet.add(new Question("Question4?", "Answer4."));
        questionSet.add(new Question("Question5?", "Answer5."));
        questionSet.add(new Question("Question6?", "Answer6."));
        questionSet.add(new Question("Question7?", "Answer7."));
        questionSet.add(new Question("Question8?", "Answer8."));
        questionSet.add(new Question("Question9?", "Answer9."));
        questionSet.add(new Question("Question10?", "Answer10."));

        return questionSet;
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
