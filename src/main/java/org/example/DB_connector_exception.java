package org.example;

public class DB_connector_exception extends Exception {
    public DB_connector_exception(String err_message) {
        super(err_message);
    }
}