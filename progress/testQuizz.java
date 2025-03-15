package progress;

import academic.*;
import java.util.HashMap;

import user.Student;
import user.Teacher;

public class testQuizz {
    public static void main(String[] args) {
        Teacher t2 = new Teacher(
                    "Jak",
                    "Jony",
                    "456 Elm St, CA",
                    "9876543210",
                    "Mathematics");
        // Create a quiz 
        Quizz quiz = new Quizz(1, "Java Basics", null, 10, "A quiz on Java basics");
        Student s3=new Student("kon","chin","82","123");
        Student s4=new Student("kon","khmer","82","321");
        HashMap<Character,String> a = new HashMap<>();
        a.put('A',"2");
        a.put('B',"3");
        a.put('C',"4");
        a.put('D',"5");

        Question q1 = new Question("What is the value associated with the key 'B'?", 10.0f, 'B', a);
        Question q2 = new Question("What is the key for the value '5'?", 10.0f, 'D', a);
        // Question q3 = new Question("In the HashMap, which value is associated with the key 'C'?", 10.0f, 'C', a);
        // Question q4 = new Question("Which of the following statements is true about the HashMap after adding the entries?", 10.0f, 'C', a);
        // Question q5 = new Question("If the value associated with 'B' were changed to '6', what would the new mapping be?", 10.0f, 'B', a);
        // q.createQuestion(t2);
        quiz.addTask(t2, q1);
        quiz.addTask(t2, q2);
        // quiz.addTask(t2, q3);
        // quiz.addTask(t2, q4);
        // quiz.addTask(t2, q5);
        quiz.addStudentScore(s3.getId(), quiz.attemp(s3.getId()));
        quiz.addStudentScore(s4.getId(), quiz.attemp(s4.getId()));

        for(float f :quiz.studentScore.values()){
            System.out.println("Score - "+f);
        }

}}
