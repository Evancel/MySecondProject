package skypro.java.margolina.mysecondproject;

//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import skypro.java.margolina.mysecondproject.exceptions.EmptyAnswerException;
import skypro.java.margolina.mysecondproject.exceptions.EmptyQuestionException;
import skypro.java.margolina.mysecondproject.exceptions.QuestionNotFoundException;
import skypro.java.margolina.mysecondproject.repository.Question;
import skypro.java.margolina.mysecondproject.service.QuestionService;
import skypro.java.margolina.mysecondproject.service.implementations.JavaQuestionService;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {

    private QuestionService out = new JavaQuestionService();

    public void addQuestionsToOut(){
        String q1 = "Вопрос1?";
        String a1 = "Ответ1.";
        Question curr1 = new Question(q1,a1);
        out.add(curr1);

        String q2 = "Вопрос2?";
        String a2 = "Ответ2.";
        Question curr2 = new Question(q2,a2);
        out.add(curr2);

        String q3 = "Вопрос3?";
        String a3 = "Ответ3.";
        Question curr3 = new Question(q3,a3);
        out.add(curr3);

        String q4 = "Вопрос4?";
        String a4 = "Ответ4.";
        Question curr4 = new Question(q4,a4);
        out.add(curr4);

        String q5 = "Вопрос5?";
        String a5 = "Ответ5.";
        Question curr5 = new Question(q5,a5);
        out.add(curr5);

        String q6 = "Вопрос6?";
        String a6 = "Ответ6.";
        Question curr6 = new Question(q6,a6);
        out.add(curr6);

        String q7 = "Вопрос7?";
        String a7 = "Ответ7.";
        Question curr7 = new Question(q7,a7);
        out.add(curr7);

        String q8 = "Вопрос8?";
        String a8 = "Ответ8.";
        Question curr8 = new Question(q8,a8);
        out.add(curr8);

        String q9 = "Вопрос9?";
        String a9 = "Ответ9.";
        Question curr9 = new Question(q9,a9);
        out.add(curr9);

        String q10 = "Вопрос10?";
        String a10 = "Ответ10.";
        Question curr10 = new Question(q10,a10);
        out.add(curr10);
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
        assertEquals(q,out.add(question, answer));
    }

    @Test
    public void checkAddQuestion(){
        String question = "Что такое «переменная»?";
        String answer = "Переменная — это ячейка в памяти компьютера, которой можно присвоить имя и " +
                "в которой можно хранить данные.";
        Question q = new Question(question, answer);
        assertEquals(q,out.add(q));
    }

    @Test
    public void checkRemoveQuestion(){
        addQuestionsToOut();
        Question q=new Question("Вопрос1?","Ответ1.");
        assertEquals(q, out.remove(q));
    }

    @Test
    public void checkGetAllQuestions(){
        addQuestionsToOut();
        assertEquals(addQuestionsToExpected(),out.getAll());
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
        assertThrows(EmptyQuestionException.class,()-> out.add(null, answer));
    }

    @ParameterizedTest
    @MethodSource("checkInvalidAnswer")
    public void checkNullAnswerAdd(String question, String answer){
        assertThrows(EmptyAnswerException.class,()-> out.add(question, answer));
    }

    @Test
    public void checkRemoveQuestionNotFound(){
        addQuestionsToOut();
        Question q=new Question("Вопрос11?","Ответ11.");
        assertThrows(QuestionNotFoundException.class,()->out.remove(q));
    }

}
