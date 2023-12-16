package org.example;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Auth_verification {
    static Scanner get_input = new Scanner(System.in);

    public boolean login_authenticate(String email_id, String password_) {
        DB_connector db_connector = null;
        // Opening Connection
        try {
            db_connector = new DB_connector();
            boolean connection_status = db_connector.open_db_connection();
            if (connection_status==false) {
                return false;
            }
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error while connecting to DB. Connection Failed.");
            return false;
        }
        // Logging user using email and password
        try {
            boolean connection_status = db_connector.execute_select_login(email_id,password_);
            if (connection_status==false) {
                return false;
            }
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error unable to fetch user details.");
            return false;
        }
        // Closing Connection
        try {
            boolean connection_status = db_connector.close_db_connection();
            if (connection_status==false) {
                return false;
            }
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error unable to close the DB connection.");
            return false;
        }
        return true;
    }

    public static boolean verify_input(String input_value) {
        // Check if input string value is null or a space or a new line character or empty string
        if (input_value == null || input_value.strip() == "" || input_value.strip() == "\n" || input_value.isEmpty()) {
            System.out.println("[error] Incorrect input, please enter a valid input!");
            return false;
        }
        else {
            return true;
        }
    }
    public static boolean verify_input(String input_value,String condition_flag) {
        if (input_value == null || input_value.strip() == "" || input_value.strip() == "\n" || input_value.isEmpty()) {
            System.out.println("[error] Incorrect input, please enter a valid input!");
            return false;
        } else {
            if (condition_flag=="email_id"){
                Matcher email_matcher = Pattern.compile("(.+)@(.+)\\.(.+)").matcher(input_value.strip());
                return email_matcher.matches();
            }
            return true;
        }
    }
    public static boolean verify_input_dtype(String input_value, String dtype) {
        // Check if value has a datatype as dtype
        if (dtype.equals("int")) {
            try {
                Integer.parseInt(input_value);
                return true;
            } catch(NumberFormatException err) {
                return false;
            }
        } else{
            return false;
        }
    }
    public static String get_field_input(String message) {
        // Print message and get input from user
        System.out.print(message);
        String input_field = get_input.nextLine();
        return input_field.strip();
    }
}
