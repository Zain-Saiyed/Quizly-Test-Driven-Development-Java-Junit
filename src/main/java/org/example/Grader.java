package org.example;

interface IGrader
{
    void view_results();
    double get_percentage();
    String get_grade();
}

class Grader implements IGrader{

    int total_questions,num_correct_answers,num_wrong_answers,score,total_points;
    String quiz_name;
    public Grader(){
    }
    public Grader(int total_questions, int num_correct_answers, int num_wrong_answers,String quiz_name,int score,int total_points) {
        this.total_questions = total_questions;
        this.num_correct_answers = num_correct_answers;
        this.num_wrong_answers = num_wrong_answers;
        this.quiz_name= quiz_name;
        this.score=score;
        this.total_points = total_points;
    }

    @Override
    public void view_results() {
        // Print Quiz results
        System.out.println("Please find your results for this Quiz :"+"\n------------------------------------------------------- "+
                "\nQuiz Name ------------------: "+this.quiz_name+
                "\n------------------------------------------------------- "+
                "\nTotal Correct Answers ------: "+this.num_correct_answers+
                "\nTotal Incorrect Answers ----: "+this.num_wrong_answers+
                "\nTotal number of Questions --: "+this.total_questions+
                "\nTotal Score obtained -------: "+this.score+ " (Total Score of Quiz = "+this.total_points+")"+
                "\nTotal Percentage obtained --: "+this.get_percentage()+" %"+
                "\nLetter Grade ---------------: "+this.get_grade()
        );
    }

    @Override
    public double get_percentage() {
        // Calculate percentage obtained for attempted quiz
        return ((double) this.num_correct_answers / (double) this.total_questions) * 100.0;
    }

    @Override
    public String get_grade() {
        // Get grade corresponding to percentage obtained
        double percentage_score= this.get_percentage();
        if (percentage_score >= 90) {
            return "A+";
        } else if (percentage_score >= 85) {
            return "A";
        } else if (percentage_score >= 80) {
            return "A-";
        } else if (percentage_score >= 77) {
            return "B+";
        } else if (percentage_score >= 73) {
            return "B";
        } else if (percentage_score >= 70) {
            return "B-";
        } else if (percentage_score >= 65) {
            return "C+";
        } else if (percentage_score >= 60) {
            return "C";
        } else if (percentage_score >= 55) {
            return "C-";
        } else if (percentage_score >= 50) {
            return "D";
        } else if (percentage_score >= 0) {
            return "F";
        } else {
            return null;
        }
    }
}
