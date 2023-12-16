package org.example;

public class DB_connector {

    public DB_connector(){
    }
    public boolean open_db_connection() throws DB_connector_exception {
        System.out.println("[* db_ops] DB Connection opened successfully.");
        return true;
    }
    public boolean close_db_connection() throws DB_connector_exception {
        System.out.println("[* db_ops] DB Connection closed successfully.");
        return true;
    }

    public boolean execute_insert(String quiz_name, int total_questions, Question_answer[] quiz_q_a) throws DB_connector_exception {
        System.out.println("[* db_ops] Successfully inserted quiz into database table.");
        return true;
    }

    public boolean execute_select_login(String email_id, String password_) throws DB_connector_exception {
        System.out.println("[* db_ops] Successfully fetched result-set executed from database query.");
        return true;
    }
    public String execute_select(String query) throws DB_connector_exception {
        // db_connection = Driver_manager.getConnection(url);
        // db_query_statement = db_connection.createStatement();
        // result = db_query_statement.executeQuery(query);
        System.out.println("[* db_ops] Successfully fetched result-set executed from database query.");
        return "true";
    }
}