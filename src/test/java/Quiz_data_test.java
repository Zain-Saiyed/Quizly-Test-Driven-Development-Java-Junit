import org.example.Question_answer;
import org.example.Quiz;
import org.example.Quiz_data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Quiz_data_test {
    @Test
    public void init_Quiz_data() {
        // Create the mock Test Quiz
        String quiz_name = "Test Quiz";
        int total_questions = 4;
        Question_answer[] q_a = new Question_answer[total_questions];
        q_a[0] = new Question_answer();
        q_a[0].set_question_answer("Which of the following is not a Java features?",
                3,4,new String[] {"Dynamic","Architecture Neutral","Use of pointers","Object-oriented"},2,"java",1);
        q_a[1] = new Question_answer();
        q_a[1].set_question_answer("_____ is used to find and fix bugs in the Java programs.",
                4,4,new String[] {"JVM","JRE","JDK","JDB"},2,"java",1);
        q_a[2] = new Question_answer();
        q_a[2].set_question_answer("Which of the following is a valid long literal?",
                2,2,new String[] {"ABH8097","0xnf029L"},2,"java",1);
        q_a[3] = new Question_answer();
        q_a[3].set_question_answer("Which of the following for loop declaration is not valid?",
                1,4,new String[] {"for ( int i = 99; i >= 0; i / 9 )","for ( int i = 7; i <= 77; i += 7 )","for ( int i = 20; i >= 2; - -i )","for ( int i = 2; i <= 20; i = 2* i )"},4,"java",2);

        Quiz expected_quiz = new Quiz(quiz_name,total_questions,q_a);

        // Initialise the Test Quiz using the init_Quiz_data() method in Quiz_data class.
        Quiz_data quiz_data_obj = new Quiz_data();
        Quiz actual_quiz = quiz_data_obj.init_Quiz_data("src/main/resources/test_quiz.csv",quiz_name,false);

        // Check if expected and the actual Quiz objects are equal and contains same data
        assertEquals(expected_quiz.getQuiz_name(),actual_quiz.getQuiz_name());
        assertEquals(expected_quiz.getTotal_questions(),actual_quiz.getTotal_questions());

        Question_answer[] expected_q_a = expected_quiz.getQuiz_questions_answers();
        Question_answer[] actual_q_a   = actual_quiz.getQuiz_questions_answers();
        for (int i = 0; i < total_questions; i++) {
            assertTrue(expected_q_a[i].equals(actual_q_a[i]));
        }
    }
}
