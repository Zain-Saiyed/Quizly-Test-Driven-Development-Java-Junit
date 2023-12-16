package org.example;

public class Question_answer {
    private String question,option_1,option_2,option_3,option_4,question_topic_tag,retrieve_flag;
    private int points,answer,question_time,num_options;

    public Question_answer() {
        question=option_1=option_2=option_3=option_4=question_topic_tag=retrieve_flag = null;
        answer=question_time=points=num_options=-999;
    }

    public String get_question() {
        return question;
    }
    public int get_points() { return points; }
    public int get_answer() {
        return answer;
    }
    public int get_num_options() { return num_options; }
    public void set_question_answer(String question, int answer, int num_options, String[] options, int points, String question_topic_tag, int question_time) {
        this.question = question.strip();
        this.answer = answer;
        this.option_1 = options[0].strip();
        this.option_2 = options[1].strip();
        this.option_3 = num_options >= 3 ? options[2].strip() : null;
        this.option_4 = num_options == 4 ? options[3].strip() : null;
        this.num_options = num_options;
        this.points = points;
        this.question_topic_tag = question_topic_tag.strip();
        this.question_time = question_time;
    }
    public String toString(){
        return  "\nQuestion --------- : "+ this.question+"\n"+
                "Answer ----------- : "+ this.answer+"\n"+
                "points ----------- : "+ this.points+"\n"+
                "num_options ------ : "+ this.num_options+"\n"+
                "Option 1 --------- : "+ this.option_1+"\n"+
                "Option 2 --------- : "+ this.option_2+"\n"+
                ((num_options>=3) ? ("Option 3 --------- : "+ this.option_3+"\n"):"") +
                ((num_options==4) ? ("Option 4 --------- : "+ this.option_4+"\n"):"") +
                "question topic tag : "+ this.question_topic_tag+"\n"+
                "time limit (in min): "+ this.question_time+"\n";
    }

    public String display_question(int q_no) {
        // Return String which contains Question's details
        return  "\nQuestion "+(q_no+1)+" : "+ this.question+"\n"+"----------\n"+
                "Option 1 --------- : "+ this.option_1+"\n"+
                "Option 2 --------- : "+ this.option_2+"\n"+
                ((num_options>=3) ? ("Option 3 --------- : "+ this.option_3+"\n"):"") +
                ((num_options==4) ? ("Option 4 --------- : "+ this.option_4+"\n"):"") +"----------\n\n"+
                "points ----------- : "+ this.points+"\n"+
                "time limit (in min): "+ this.question_time+"\n"+
                "question topic tag : "+ this.question_topic_tag+"\n";
    }

    @Override
    public boolean equals(Object expected_obj) {
        // Compare if Question's object are equal
        if (expected_obj == null)
            return false;
        else if (expected_obj == this)
            return true;
        else {
            Question_answer qa_obj = (Question_answer) expected_obj;
            return this.question.equals(qa_obj.question)
                && this.option_1.equals(qa_obj.option_1)
                && this.option_2.equals(qa_obj.option_2)
                && (num_options>=3 ? this.option_3.equals(qa_obj.option_3):true)
                && (num_options>=3 ? this.option_4.equals(qa_obj.option_4):true)
                && this.question_topic_tag.equals(qa_obj.question_topic_tag)
                && this.points==qa_obj.points
                && this.answer==qa_obj.answer
                && this.question_time==qa_obj.question_time
                && this.num_options==qa_obj.num_options
            ;
        }
    }
}