# Quizly - Quiz application system for education

<hr>

[![Java - JDK 17.0.7 LTS](https://img.shields.io/static/v1?label=Java&message=JDK+17.0.7+LTS&color=%23007396)]()
[![Test Driven Development - true](https://img.shields.io/badge/Test_Driven_Development-true-2ea44f)]()
[![JUnit Jupiter version](https://img.shields.io/maven-central/v/org.junit.jupiter/junit-jupiter/5.9.1.svg?color=25a162&label=Jupiter)]()


## Overview:
Single destination for professors to create Quizzes for students. Students can attempt quizzes,
view instant graded results on the quiz.

## Functionalities:
This app holds features which allows Professors to create quizzes which would be then inserted into the Database, while allowing  Students to view available quizzes and then attempt them. Moreover, after the student completes the quiz the applciation automatically gradest he quiz and dispalys the result on how many questions did the stduent get right(correct) and how many questions were incorrect, Percentage scored and the Letter Grade for the submitted quiz.
In short, the functionalities are :
1. **Professor** : View Quizzes, Create Quizzes
2. **Student**   : View Quizzes, Attempt Quizzes (and get instant graded report result)

* Additionally, the application is built such that all error cases are handled carefully, while prompting the user and guiding teh user towards entering a correct input. And, all the logical functions are tested using junit in the test folder. Lastly, sample quizzes are present in the csv files present in the resources directory.

#### Java JDK used for building this application: JDK 17.0.7 LTS _[reference](https://www.oracle.com/ca-en/java/technologies/downloads/#java17)_
#### Tested using Junit5 : 5.9.1

## Functionalities:
#### I. Professor:
1. Create a new Quiz.
2. Manage existing created Quiz. (View, Edit, Delete, and Add Questions in existing Quiz)
3. View Student Performances.

#### II. Student:
1. Submit a Quiz.
2. View past quiz performance and results.

## Classes:
#### Classes used in application:
1. **Main** - Contains the main driver function which runs the quiz application. 
2. **Auth_verification** - Contains logic for logging user in and verifying user input.
3. **Question_answer** - A Question Answer Class which holds information about a question of a quiz, like Question, Answer, options [1,2.3.4], number of points worth of the question, maximum time limit for this question, topic tag of this question.  
4. **App_manager** - The class which has logics for the quiz app functionalities, like creating a quiz, creating a question answer section of a quiz. 
5. **DB_connector** - Mock class which handles the Database connection, fetching and inserting operation. 
6. **DB_connector_exception** - Custom Exception class to simulate exception similarity as the JDBC drivers. 
7. **Quiz** - This is the Class which simulates a single entity called 'Quiz' which would contain quiz details (like quiz name and total number of questions in a quiz). Moreover it has a list of Question Answer pairs inside it.
8. **Quiz_data** - This is a class similar to mocking Database but which reads quiz data from csv files, then loads this data into Quiz and Question_answer object. This can further be used to simulate the DB whcih already contains quiz data.
9. **Grader** - This class file contains implementation for an interface named "IGrader" which has abstract methods to view quiz result, and compute percentage and get lette grade.  

#### Test Classes:
1. **App_manager_test** - To test the logical methods in _App_manager_ class.
2. **Auth_verification_test** - To test the logical methods in _Auth_verification_ class.
3. **DB_connector_test** - To test the logical methods in _DB_connector_ class.
4. **Quiz_data_test** - To test if the method which returns the mock quiz values are consistent.
## Screenshots- Code Output:
![1](/images/quizly1.jpg?raw=true "1")
![2](/images/quizly2.jpg?raw=true "2")
![3](/images/quizly3.jpg?raw=true "3")
![4](/images/quizly4.jpg?raw=true "4")
![5](/images/student1.PNG?raw=true "5")
![6](/images/student2.PNG?raw=true "6")
![7](/images/student3.PNG?raw=true "7")
![8](/images/student4.PNG?raw=true "8")

<hr>
