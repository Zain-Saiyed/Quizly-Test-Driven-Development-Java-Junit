package org.example;

import java.io.*;
import java.util.ArrayList;

/**
 * Class for initializing and loading quizzes from file repository
 */
public class Quiz_data {
    Quiz[] quiz_db = new Quiz[3];
    public Quiz_data() {

    }
    public Quiz_data(boolean supress_log_flag){
        this.populate_quiz_db(supress_log_flag);
    }
    public Quiz[] getQuiz_db() {
        return quiz_db;
    }
    public void populate_quiz_db(boolean supress_log_flag){
        quiz_db[0] = this.init_Quiz_data("src/main/resources/quiz_science.csv","CBSE Class 10 Science Quiz",supress_log_flag);
        quiz_db[1] = this.init_Quiz_data("src/main/resources/quiz_history.csv","CBSE Class 10 History Quiz",supress_log_flag);
        quiz_db[2] = this.init_Quiz_data("src/main/resources/quiz_mathematics.csv","CBSE Class 10 Mathematics Quiz",supress_log_flag);
    }
    public Quiz init_Quiz_data(String base_file_path, String quiz_name, boolean supress_log_flag){
        if (!supress_log_flag)
            System.out.println("[*file_ops] Starting read "+base_file_path);
        ArrayList<String> file_lines = new ArrayList<>();
        File file = new File(base_file_path);

        // Read the file from base_path
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String f_line;
            // Fetch all the lines from the file
            while ((f_line = reader.readLine()) != null) {
                file_lines.add(f_line);
            }
            if (!supress_log_flag)
                System.out.println("[*file_ops] Total number of lines = " + file_lines.size());
        } catch (FileNotFoundException err) {
            err.printStackTrace();
            System.out.println("[error] Quiz Data File not found. Please verify if the csv files are present in the resources folder. If in case its not present then download files from git repository and place in resources directory.");
        } catch (IOException err) {
            err.printStackTrace();
            System.out.println("[error] Error while reading file. Please verify if the file is consistent, if not then download files from git repository and place in resources directory.");
        }
        // Creating an array to hold the Question_answer objects
        Question_answer[] q_a = new Question_answer[file_lines.size()];

        // Reading the question answer details from the file and storing in Question_answer objects
        for (int index = 0; index < file_lines.size(); index++) {
            try {
                String[] parts = file_lines.get(index).split(",");
            // // For Debugging purpose:
            //    if (parts.length == 9) for (int i = 0; i < parts.length; i++) { System.out.println(i + "  " + parts[i]); }
                q_a[index] = new Question_answer();
                q_a[index].set_question_answer(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[3]), new String[]{parts[4], parts[5], parts[6], parts[7]}, Integer.parseInt(parts[2]), parts[8], Integer.parseInt(parts[9]));
            }
            // Catch ArrayIndexOutOfBoundsException for missing columns in a row.
            catch(ArrayIndexOutOfBoundsException err){
                System.out.println("[error] Error while reading file row. Some column is missing in file row, Please check line number = "+(index+1)+", ");
                break;
            }
            // Catch NumberFormatException for columns with incorrect data types.
            catch (NumberFormatException err){
                System.out.println("[error] Error in datatype of a column in file row. Please check if each column has the correct datatype. Please check line number = "+(index+1)+", ");
                break;
            }
        }
        if (!supress_log_flag)
            System.out.println("[*file_ops] File read complete successfully.");
        return new Quiz(quiz_name, file_lines.size(), q_a);
    }
}

