package org.example;

import static org.example.Auth_verification.*;
import static org.example.Auth_verification.get_field_input;

public class App_manager {

    Quiz[] quiz_data_db = new Quiz_data(true).getQuiz_db();
    Grader quiz_grader = null;
    public void create_new_quiz(){
        boolean input_flag=false;
        String quiz_name = null,input_field;
        int total_questions=-999;

        String menu_options = """
                -------------------------------------------------------------------------------------------
                Create a new Quiz
                -------------------------------------------------------------------------------------------
                Please enter the quiz details below:
                """;
        System.out.print(menu_options);
        // get input: quiz_name
        while (!input_flag) {
            quiz_name = get_field_input("Q1. What is the name of this Quiz: ");
            input_flag = verify_input(quiz_name);
            if (!input_flag) {
                System.out.println("[error] Incorrect input, please enter a valid input!");
            }
        }
        input_flag = false;

        // get input: total_questions
        while (!input_flag) {
            input_field = get_field_input("Q2. Total number of questions in this quiz (Integer minimum 2 or more): ");
            input_flag = verify_input(input_field);
            if (verify_input_dtype(input_field, "int")){
                total_questions = Integer.parseInt(input_field);
                if (total_questions<2) {
                    input_flag = false;
                    System.out.println("[error] A quiz should have a minimum of 2 questions. Please try again.");
                } else {input_flag = true;}
            } else {
                System.out.println("[error] Incorrect input, please enter a valid input!"); input_flag=false;
            }
        }

        System.out.print("\nNow, Please Enter each of the below details: \n");
        // Create an array of question_answers to hold each of the Question answers in the quiz
        Question_answer [] quiz_q_a = new Question_answer[total_questions];
        // get input: get all the question ans  wer details
        for (int q_num = 1; q_num <= total_questions; q_num++) {
            quiz_q_a[q_num-1] = create_question(q_num);
        }

        Quiz quiz_obj = new Quiz(quiz_name,total_questions,quiz_q_a);

        System.out.println("\n---- ALL QUESTIONS ANSWERED SUCCESSFULLY! -------------------------------------------------");
        input_field = get_field_input("Please press ENTER to continue...");

        // print the newly created quiz to view
        System.out.println("\nHere is the newly created quiz:\n"
                +"-------------------------------------------------------------------------------------------\n"
                +"Quiz name :"+quiz_name
                +"\n-------------------------------------------------------------------------------------------"
                +"\nTotal number of Questions: "+total_questions+"\n");
        total_questions=1;
        for (Question_answer qa_obj : quiz_q_a) {
            System.out.println("--- Q"+total_questions+": ---");
            System.out.println(qa_obj);
            total_questions+=1;
        }
        System.out.println("-----------");
        input_field = get_field_input("Please press ENTER to continue...");

        // Save the newly created quiz into database
        System.out.println("-------------------------------------------------------------------------------------------");
        save_quiz_in_db(quiz_name,total_questions,quiz_q_a);
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    private Question_answer create_question (int q_num){
        String question,question_topic_tag,input_field = null;
        int answer,question_time,points,num_options;
        // default number of choices for a question = 2
        num_options=2;
        boolean input_flag = false;
        question=question_topic_tag=null;
        answer=question_time=points=-999;
        // Get question
        System.out.println("-------------------------------------------------------------------------------------------");
        while (!input_flag) {
            question = get_field_input("Serial Number :" + q_num + " \nQuestion : ");
            input_flag = verify_input(question);
            if (input_flag == false) {
                System.out.println("[error] Incorrect input, please enter a valid question!");
            }
        }
        // get total num_options
        input_flag = false;
        while (!input_flag) {
            input_field = get_field_input("\nHow many options does this Question have [2/3/4]? : ");
            input_flag = verify_input(input_field);
            if (verify_input_dtype(input_field, "int")) {
                num_options = Integer.parseInt(input_field);
                if (num_options<2 || num_options>4) {
                    input_flag = false;
                    System.out.println("[error] A quiz should have a minimum of 2 options and maximum 4 options.");
                }
            }
            else { System.out.println("Invalid input! Please Try again."); input_flag=false;}

        }
        // Get each option of question
        String[] options = new String[num_options];

        for (int j = 0; j < num_options; j++) {
            input_flag = false;
            while (!input_flag) {
                input_field = get_field_input("option " + (j + 1) + " : ");
                input_flag = verify_input(input_field);
                if (input_flag == false) {
                    System.out.println("[error] Incorrect input, please enter a valid option!");
                }
            }
            options[j] = input_field;
        }
        // Get question topic category tag
        input_flag = false;
        while (!input_flag) {
            input_field = get_field_input("question topic tag    : ");
            input_flag = verify_input(input_field);
            if (input_flag == false) {
                System.out.println("[error] Incorrect input, please enter a valid option!");
            }
        }
        question_topic_tag = input_field;
        // answer option
        String possible_options = num_options == 4 ? "[1/2/3/4]" : (num_options) == 3 ? "[1/2/3]" : (num_options) == 2 ? "[1/2]" : "";
        input_flag = false;
        while (!input_flag) {
            input_field = get_field_input("answer (which option number is correct? " + possible_options + ")    : ");
            input_flag = verify_input(input_field);
            if (verify_input_dtype(input_field, "int")) {
                answer = Integer.parseInt(input_field);
                if (answer<1 || answer>4 || answer>num_options) {
                    input_flag = false;
                    System.out.println("[error] Please choose a answer value from "+possible_options+".");
                }
            } else {
                input_flag = false;
                System.out.println("[error] Incorrect input, please enter a valid option!");
            }
        }
        // Get marks or points of the question
        input_flag = false;
        while (!input_flag) {
            input_field = get_field_input("points (integer)  : ");
            input_flag = verify_input(input_field);
            if (verify_input_dtype(input_field, "int")) {
                points = Integer.parseInt(input_field);
            } else {
                input_flag = false;
                System.out.println("[error] Incorrect input, please enter a valid option!");
            }
        }
        // Get the possible time taken to solve this question
        input_flag = false;
        while (!input_flag) {
            input_field = get_field_input("time on question (in minutes) : ");
            input_flag = verify_input(input_field);
            if (verify_input_dtype(input_field, "int")) {
                question_time = Integer.parseInt(input_field);
            } else {
                input_flag = false;
                System.out.println("[error] Incorrect input, please enter a valid option!");
            }
        }
        // Get Question_answer object with all the question answer details
        Question_answer questionAnswer = create_single_quiz_question_answer(question,answer,num_options,options,points,question_topic_tag,question_time);
//        System.out.println("-------------------------------------------------------------------------------------------");
//        System.out.println(questionAnswer.toString());
        return questionAnswer;
    }

    public Question_answer create_single_quiz_question_answer(String question, int answer, int num_options, String[] options, int points, String question_topic_tag, int question_time){
        Question_answer questionAnswer = new Question_answer();
        questionAnswer.set_question_answer(question,answer,num_options,options,points,question_topic_tag,question_time);
        return questionAnswer;
    }

    public boolean save_quiz_in_db(String quiz_name, int total_questions, Question_answer[] quiz_q_a) {
        boolean connection_status=false;
        DB_connector db_connector = null;
        // Opening DB connection
        try {
            db_connector = new DB_connector();
            connection_status = db_connector.open_db_connection();
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[* db_error] Error while connecting to DB. Connection Failed.");
            return false;
        }
        // Inserting quiz into DB
        try {
            if (connection_status) {
                connection_status = db_connector.execute_insert(quiz_name,total_questions,quiz_q_a);
            } else {
                return false;
            }
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[* db_error] Error while inserting quiz into DB table.");
            return false;
        }
        // Closing DB connection
        try {
            connection_status = db_connector.close_db_connection();
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[* db_error] Error while connecting to DB. Connection Failed.");
            return false;
        }
        return true;
    }
    public void attempt_quiz() {
        boolean input_flag = false;
        int quiz_option=-999;
        // Print all quiz option to user for selection
        this.view_all_quiz();
        // get quiz choice input
        while (!input_flag) {
            // Display all the available quizzes that can be attempted.
            // Get user choice on quiz selection
            String input_field = get_field_input("------\nWhich Quiz would you like to attempt?\nChoice = ");
            input_flag = verify_input(input_field);
            if (input_field.equals("#")) {return ;}
            else if (verify_input_dtype(input_field, "int")) {
                quiz_option = Integer.parseInt(input_field);
                if (quiz_option>quiz_data_db.length) {
                    input_flag = false;
                    System.out.println("[error] Invalid Quiz selection. Please select a Quiz option between [1 to "+quiz_data_db.length+"]. Please try again.");
                }
            }
            else { System.out.println("[error] Invalid Quiz selection. Please select a Quiz option between [1 to "+quiz_data_db.length+"]. Please try again."); input_flag=false;}
        }

        quiz_option-=1;
        System.out.println("\n** You are now attempting Quiz : "+quiz_data_db[quiz_option].getQuiz_name());
        String input_field = get_field_input("Please press ENTER to start Quiz...");

        int num_correct_answers=0,num_wrong_answers=0,score=0,total_points=0;
        // Iterate over all quiz questions
        for (int index = 0; index < quiz_data_db[quiz_option].total_questions; index++) {
            Question_answer question = quiz_data_db[quiz_option].getQuiz_questions_answers()[index];
            total_points += question.get_points();
            System.out.println(question.display_question(index));
            int answer_option=-999;
            input_flag=false;
            // get user's answer choice selection
            while (!input_flag) {
                input_field = get_field_input("Option Choice (Integer) = ");
                input_flag = verify_input(input_field);
                if (verify_input_dtype(input_field, "int")) {
                    answer_option = Integer.parseInt(input_field);
                    if (answer_option>4 || answer_option<0 || answer_option>question.get_num_options()) {
                        input_flag = false;
                        System.out.println("[error] Invalid option selection. Please try again.");
                    }
                } else { System.out.println("[error] Invalid option selection. Please try again."); input_flag=false;}
            }
            if (question.get_answer()==answer_option) {
                num_correct_answers+=1;
                score += question.get_points();
            } else {
                num_wrong_answers+=1;
            }
        }
        input_field = get_field_input("\nYou have completed the Quiz successfully. The grader will grade your quiz. \nPlease press ENTER to continue...\n");
        // Display Grader result to user
        System.out.println("-------------------------");
        System.out.println("===== GRADER RESULT =====");
        System.out.println("-------------------------");
        quiz_grader = new Grader(quiz_data_db[quiz_option].total_questions,num_correct_answers,num_wrong_answers,quiz_data_db[quiz_option].getQuiz_name(),score,total_points);

        quiz_grader.view_results();
        input_field = get_field_input("\nPlease press ENTER to continue...");

    }
    // Display all available quizzes that the user can attempt
    public boolean view_all_quiz(){
        try {
            System.out.println("-------------------------------------------------------------------------------------------\n" +
                    "All Available Quizzes :\n" +
                    "-------------------------------------------------------------------------------------------");
            for (int index = 0; index < quiz_data_db.length; index++) {
                System.out.println("[" + (index + 1) + "] " + quiz_data_db[index].getQuiz_name());
            }
            System.out.println("[#] Exit.");
        } catch (NullPointerException err) {
            System.out.println("[error] Issue in retrieving Quiz present DB.");
            return false;
        }
        return true;
    }
    public void fetch_quiz(){
    }
    public void display_question(){
    }
    private void edit_response(){
    }
}
