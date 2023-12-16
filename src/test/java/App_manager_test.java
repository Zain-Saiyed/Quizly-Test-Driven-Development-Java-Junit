import org.example.App_manager;
import org.example.Question_answer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class App_manager_test {

    @Test
    public void create_single_quiz_question_answer_test(){
        String[] options = {"Toronto","Montreal","Ottawa","Halifax"};
        // Mock Question_answer object to compare
        Question_answer expected_question_answer = new Question_answer();
        expected_question_answer.set_question_answer("What is the capital of Canada?",
                3,4,options,2,"geography",1);

        // appManager object
        App_manager appManager = new App_manager();
        Question_answer actual_question_answer = appManager.create_single_quiz_question_answer("What is the capital of Canada?",
                3,4,options,2,"geography",1);

        // Check if both objects are equal and contain same data
        boolean result = expected_question_answer.equals(actual_question_answer);
        assertTrue(result);
        System.out.println("------");
    }
    @Test
    public void save_quiz_in_db_test() {
        //TODO Replace to another question and answers
        String quiz_name = "Test Quiz";
        int total_questions = 2;
        Question_answer [] quiz_q_a = new Question_answer[total_questions];
        quiz_q_a[0] = new Question_answer();
        quiz_q_a[0].set_question_answer("Which one of the following salts does not contain water of crystallisation?", 2,4,new String[] {"Blue vitriol","Baking soda","Washing soda","Gypsum"},5,"chemistry",1);
        quiz_q_a[1] = new Question_answer();
        quiz_q_a[1].set_question_answer("Tomato is a natural source of which acid?", 4,4,new String[] {"Acetic acid","Citric acid","Tartaric acid","Oxalic acid"},5,"chemistry",1);

        App_manager appManager = new App_manager();
        boolean result = appManager.save_quiz_in_db(quiz_name, total_questions, quiz_q_a);
        assertTrue(result);
        System.out.println("------");
    }

    @Test
    public void view_all_quiz_test(){
        App_manager appManager = new App_manager();
        assertTrue(appManager.view_all_quiz());
    }

}
