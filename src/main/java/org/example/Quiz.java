package org.example;

import java.util.Arrays;

public class Quiz {
    String quiz_name;
    int total_questions;
    Question_answer[] quiz_questions_answers;

    public Quiz() {
        this.quiz_name = null;
        this.total_questions = -999;
        this.quiz_questions_answers = null;
    }

    public Quiz(String quiz_name, int total_questions, Question_answer[] quiz_questions_answers) {
        this.quiz_name = quiz_name;
        this.quiz_questions_answers = quiz_questions_answers;
        this.total_questions = total_questions;
    }

    public String getQuiz_name() {
        return quiz_name;
    }

    public int getTotal_questions() {
        return total_questions;
    }

    public Question_answer[] getQuiz_questions_answers() {
        return quiz_questions_answers;
    }

    @Override
    public String toString() {
        return "-------------------------------------------------------------------------------------------\n" +
                "Quiz Name ------: " + quiz_name +
                "\n-------------------------------------------------------------------------------------------\n" +
                "Total number of questions :" + total_questions +
                "\nQuestions are --:" + Arrays.toString(quiz_questions_answers);
    }
    @Override
    public boolean equals(Object expected_obj) {
        if (expected_obj == null)
            return false;
        else if (expected_obj == this)
            return true;
        else {
            Quiz qa_obj = (Quiz) expected_obj;
            return this.quiz_name.equals(qa_obj.quiz_name)
                    && this.total_questions==qa_obj.total_questions
                    && this.quiz_questions_answers.equals(qa_obj.quiz_questions_answers)
                    ;
        }
    }
}
