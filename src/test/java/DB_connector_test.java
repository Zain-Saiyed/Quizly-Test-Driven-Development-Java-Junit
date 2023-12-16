import org.example.DB_connector;
import org.example.Question_answer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DB_connector_test {

    @Test
    public void open_db_connection_test() {
        // Opening Connection
        DB_connector db_connector = null;
        try {
            db_connector = new DB_connector();
            boolean connection_status = db_connector.open_db_connection();
            assertTrue(connection_status);
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error while connecting to DB. Connection Failed.");
        }
        System.out.println("------");
    }
    @Test
    public void close_db_connection_test() {
        DB_connector db_connector = null;
        // Opening Connection
        try {
            db_connector = new DB_connector();
            boolean connection_status = db_connector.open_db_connection();
            assertTrue(connection_status);
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error while connecting to DB. Connection Failed.");
        }
        // Closing Connection
        try {
            boolean connection_status = db_connector.close_db_connection();
            assertTrue(connection_status);
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error unable to close the DB connection.");
        }
        System.out.println("------");
    }
    @Test
    public void execute_insert_test() {
        DB_connector db_connector = null;
        // Opening Connection
        try {
            db_connector = new DB_connector();
            boolean connection_status = db_connector.open_db_connection();
            assertTrue(connection_status);
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error while connecting to DB. Connection Failed.");
        }
        // Initialising quiz
        String quiz_name = "Chapter 5 Periodic Classification of Elements";
        int total_questions = 2;
        Question_answer [] quiz_q_a = new Question_answer[total_questions];
        quiz_q_a[0] = new Question_answer();
        quiz_q_a[0].set_question_answer("Which one of the following salts does not contain water of crystallisation?", 2,4,new String[] {"Blue vitriol","Baking soda","Washing soda","Gypsum"},5,"chemistry",1);
        quiz_q_a[1] = new Question_answer();
        quiz_q_a[1].set_question_answer("Tomato is a natural source of which acid?", 4,4,new String[] {"Acetic acid","Citric acid","Tartaric acid","Oxalic acid"},5,"chemistry",1);

        // Inserting into DB
        try {
            boolean connection_status = db_connector.execute_insert(quiz_name,total_questions,quiz_q_a);
            assertTrue(connection_status);
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error unable to insert into  DB table.");
        }
        // Closing Connection
        try {
            boolean connection_status = db_connector.close_db_connection();
            assertTrue(connection_status);
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error unable to close the DB connection.");
        }
        System.out.println("------");
    }
    @Test
    public void execute_select_test() {
        DB_connector db_connector = null;
        // Opening Connection
        try {
            db_connector = new DB_connector();
            boolean connection_status = db_connector.open_db_connection();
            assertTrue(connection_status);
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error while connecting to DB. Connection Failed.");
        }

        // Selecting Data from DB
        try {
            String result = db_connector.execute_select("SELECT USER_TYPE FROM USER_DETAILS WHERE EMAIL_ID=test_user;");
            assertEquals(result,"true");
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error unable to insert into  DB table.");
        }
        // Closing Connection
        try {
            boolean connection_status = db_connector.close_db_connection();
            assertTrue(connection_status);
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error unable to close the DB connection.");
        }
        System.out.println("------");
    }
    @Test
    public void execute_select_login_test() {
        DB_connector db_connector = null;
        // Opening Connection
        try {
            db_connector = new DB_connector();
            boolean connection_status = db_connector.open_db_connection();
            assertTrue(connection_status);
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error while connecting to DB. Connection Failed.");
        }

        // Selecting Data from DB
        try {
            boolean connection_status = db_connector.execute_select_login("test_user@dal.ca","test_user123");
            assertTrue(connection_status);
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error unable to insert into  DB table.");
        }
        // Closing Connection
        try {
            boolean connection_status = db_connector.close_db_connection();
            assertTrue(connection_status);
        }
        catch (Exception DB_connector_exception) {
            System.out.println("[*] Error unable to close the DB connection.");
        }
        System.out.println("------");
    }
}
