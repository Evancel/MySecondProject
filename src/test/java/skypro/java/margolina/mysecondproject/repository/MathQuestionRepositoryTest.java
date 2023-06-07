package skypro.java.margolina.mysecondproject.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import skypro.java.margolina.mysecondproject.exceptions.EmptyAnswerException;
import skypro.java.margolina.mysecondproject.exceptions.EmptyQuestionException;
import skypro.java.margolina.mysecondproject.exceptions.QuestionNotFoundException;
import skypro.java.margolina.mysecondproject.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MathQuestionRepositoryTest {
    private final MathQuestionRepository mathQuestionRepository = new MathQuestionRepository();

    @BeforeEach
    public void setup(){
        mathQuestionRepository.init();
    }
    @Test
    public Question checkAddMathQuestionAnswer(){
        String question = "Question1?";
        String answer = "Answer1.";
        Question q = new Question(question,answer);

        assertEquals(q,mathQuestionRepository.add(question,answer));
        return q;
    }

    @Test
    public void checkAddMathQuestion(){
        String question = "Question1?";
        String answer = "Answer1.";
        Question q = new Question(question,answer);

        assertEquals(q,mathQuestionRepository.add(q));
    }

    @Test
    public void checkRemoveMathQuestion(){
        String question = "Question1?";
        String answer = "Answer1.";
        Question q = new Question(question,answer);

        mathQuestionRepository.add(q);
        mathQuestionRepository.add("Question2?","Answer2.");
        mathQuestionRepository.add("Question3?","Answer3.");

        assertEquals(q,mathQuestionRepository.remove(q));
    }

    @Test
    public void checkGetAll(){
        String question1 = "Question1?";
        String answer1 = "Answer1.";
        Question q1 = new Question(question1,answer1);

        String question2 = "Question1?";
        String answer2 = "Answer1.";
        Question q2 = new Question(question2,answer2);

        String question3 = "Question1?";
        String answer3 = "Answer1.";
        Question q3 = new Question(question3,answer3);

        String question4 = "Question1?";
        String answer4 = "Answer1.";
        Question q4 = new Question(question4,answer4);

        String question5 = "Question1?";
        String answer5 = "Answer1.";
        Question q5 = new Question(question5,answer5);

        mathQuestionRepository.add(q1);
        mathQuestionRepository.add(q2);
        mathQuestionRepository.add(q3);
        mathQuestionRepository.add(q4);
        mathQuestionRepository.add(q5);

        Collection<Question> exp = new HashSet<>();
        exp.add(q1);
        exp.add(q2);
        exp.add(q3);
        exp.add(q4);
        exp.add(q5);

        assertEquals(exp, mathQuestionRepository.getAll());
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
        assertThrows(EmptyQuestionException.class,()-> mathQuestionRepository.add(question, answer));
    }

    @ParameterizedTest
    @MethodSource("checkInvalidAnswer")
    public void checkNullAnswerAdd(String question, String answer){
        assertThrows(EmptyAnswerException.class,()-> mathQuestionRepository.add(question, answer));
    }

    @Test
    public void checkRemoveQuestionNotFound(){
        String question = "Question1?";
        String answer = "Answer1.";
        Question q = new Question(question,answer);
        Question q99 = new Question("Question99?","Answer99.");

        mathQuestionRepository.add(q);
        mathQuestionRepository.add("Question2?","Answer2.");
        mathQuestionRepository.add("Question3?","Answer3.");

        assertThrows(QuestionNotFoundException.class,()->mathQuestionRepository.remove(q99));
    }

    @Test
    public void checkGetAllIfEmpty(){
        Collection<Question> exp = new HashSet<>();
        assertEquals(exp, mathQuestionRepository.getAll());
    }
}
