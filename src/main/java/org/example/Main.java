package org.example;

import java.util.Scanner;

import static org.example.Auth_verification.*;

public class Main {
    static Scanner get_input = new Scanner(System.in);

    private static String get_user_type(String query) {
        DB_connector db_connector=null;
        boolean connection_status=false;
        String result_set=null;

        // Opening DB connection
        try {
            db_connector = new DB_connector();
            connection_status = db_connector.open_db_connection();
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error while connecting to DB. Connection Failed.");
            return "false";
        }
        // Inserting into DB
        try {
            if (connection_status) {
                result_set = db_connector.execute_select(query);
            } else {
                return "false";
            }
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error while inserting quiz into DB table.");
            return "false";
        }
        // Closing DB connection
        try {
            connection_status = db_connector.close_db_connection();
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error while connecting to DB. Connection Failed.");
            return "false";
        }
        return result_set;
    }

    public static void main(String[] args) {
        boolean debug_mode=false;
        // main driver flow
        while (true) {
            boolean input_flag = false;
            String input_field = null;
            // Welcome message
            String welcome_msg = """
                    ===============================================================================================
                    Welcome to Quizly!
                    -----------------------------------------------------------------------------------------------
                    Single destination for professors to create Quizzes for students. Students can attempt quizzes,
                    view instant graded results on the quiz.
                    ===============================================================================================
                    """;
            System.out.println(welcome_msg);
            // Login User
            String email_id = null, password_ = null;
            if (!debug_mode)
            while (!input_flag) {
                System.out.print("Please enter below credentials to log into Quizly:\n\n");
                email_id  = get_field_input("EMAIL ID = ");
                password_ = get_field_input("PASSWORD = ");

                input_flag = verify_input(email_id, "email_id");
                if (!input_flag) {
                    System.out.println("[error] Incorrect Email ID or password entered. Please enter a valid email ID and password.\n---------");
                    continue;
                }
                input_flag = false;
                // DB for Authentication
                Auth_verification auth_verification = new Auth_verification();
                input_flag = auth_verification.login_authenticate(email_id, password_);

                // Check UserType
                // check_user_type(email_id) -> return Student/Professor
                // String user_type = get_user_type("SELECT USER_TYPE FROM USER_DETAILS WHERE EMAIL_ID=" + email_id + ";");
            }
            if(debug_mode) {
                email_id = "test_user@dal.ca";
                password_ = "test_user123";
                Auth_verification auth_verification = new Auth_verification();
                input_flag = auth_verification.login_authenticate(email_id, password_);
            }
            System.out.println("[success] Successfully logged into Quizly!");
            input_flag = false;
            String user_type=null;
            if(!debug_mode) {
                while (!input_flag) {
                    input_field = get_field_input("\nPlease select a User Type: \n[1] Professor\n[2] Student\n[9] Logout\nChoose 1 or 2 or 9 : ");
                    input_flag = verify_input(input_field);
                    if ( !input_field.equals("1") && !input_field.equals("2") && !input_field.equals("9")) {
                        System.out.println("Invalid Input! Please select [1] or [2] or [9]. Please try again.");input_flag=false;}
                }
                    user_type = input_field;
            }
            if (debug_mode)
                user_type = "2";

            App_manager quiz_app_manager = new App_manager();
            while (true) {
                // User Type = Professor
                if (user_type.equals("1")) {
                    String menu_options = """
                            -------------------------------------------------------------------------------------------
                            Menu Options
                            -------------------------------------------------------------------------------------------
                            [1] Create a new Quiz.
                            [2] View existing Quizzes.
                            [9] Logout
                            ===========================================================================================
                            """;
                    System.out.print(menu_options);
                    input_flag = false;
                    while (!input_flag) {
                        input_field = get_field_input("Choice = ");
                        input_flag = verify_input(input_field);
                    }
                    String menu_selection = input_field;
                    input_flag = false;
//                    String menu_selection = "1";
                    if (menu_selection.equals("1")) {

                        System.out.println("\n** You selected '[1] Create a new Quiz.'");
                        quiz_app_manager.create_new_quiz();
                        input_field = get_field_input("Please press ENTER to continue...");
                        System.out.println("\n** Quiz created successfully!");

                    } else if (menu_selection.equals("2")) {
                        System.out.println("\n** You selected '[2] View existing Quizzes.'");
                        quiz_app_manager.view_all_quiz();
                        input_field = get_field_input("Please press ENTER to continue...");

                    } else if (menu_selection.equals("9")) {
                        email_id  = null;
                        password_ = null;
                        user_type = null;
                        System.out.println("[status] User Logged out successfully.");
                        System.out.println("===========================================================================================\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX---XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        break;
                    } else {
                        System.out.println("Invalid selection. Please Try again!");
                        input_flag=false;
                    }
                }
                // User Type = Student
                else if (user_type.equals("2")) {
                    String menu_options = """
                            -------------------------------------------------------------------------------------------
                            Menu Options
                            -------------------------------------------------------------------------------------------
                            [1] Attempt a Quiz.
                            [2] View existing Quizzes.
                            [9] Logout.
                            ===========================================================================================
                            """;
                    System.out.print(menu_options);
                    input_flag = false;
                    while (!input_flag) {
                        input_field = get_field_input("Choice = ");
                        input_flag = verify_input(input_field);
                    }
                    String menu_selection = input_field;
                    input_flag = false;
                    if (menu_selection.equals("1")) {

                        System.out.println("\n** You selected '[1] Attempt a Quiz.'");
                        quiz_app_manager.attempt_quiz();

                    } else if (menu_selection.equals("2")) {
                        System.out.println("\n** You selected '[2] View existing Quizzes.'");
                        quiz_app_manager.view_all_quiz();
                        input_field = get_field_input("Please press ENTER to continue...");

                    } else if (menu_selection.equals("9")) {
                        email_id  = null;
                        password_ = null;
                        user_type = null;
                        System.out.println("[status] User Logged out successfully.");
                        System.out.println("===========================================================================================\n===========================================================================================");
                        break;
                    } else {
                        System.out.println("Invalid selection. Please Try again!");
                        input_flag=false;
                    }
                }
                // Log Out User
                else if (user_type.equals("9")) {
                    System.out.println("[status] User Logged out successfully.");
                    email_id  = password_ = user_type = null;
                    System.out.println("===========================================================================================\n===========================================================================================");
                    break;
                } else {
                    System.out.println("Invalid selection. Please Try again!");
                    input_flag=false;
                }
            }
        }
    }
}

