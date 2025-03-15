package academic;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import user.Teacher;

public abstract class Assessment{
    Scanner input = new Scanner(System.in);
    public static int numberAssessments = 0;
    public int no;
    public int courseId;
    public String title;
    public float totalScore; 
    public Teacher teacher;
    public String description;
    private LocalDate due;

    //id key and value score
    public HashMap<String, Float> studentScore = new HashMap<String, Float>();

    // Constructor
    public Assessment(int courseId, String title, Teacher teacher,float score, String description,LocalDate deu) {
        numberAssessments+=1;
        this.no = numberAssessments;
        this.courseId = courseId;
        this.title = title;
        this.teacher = teacher;
        this.totalScore =  score;
        this.description = description;
        this.due = deu;
    }

      public LocalDate getDue() {
        return due;
    }

    //add student score
    public abstract void addStudentScore(String studentId, float score);

    //

    // Display assessment details
    // public void displayAssessment() {
    //     System.out.println("Course ID: " + courseId);
    //     System.out.println("Title: " + title);
    //     System.out.println("Score: " + totalScore);
    //     System.out.println("Assigned by: " + (teacher != null ? teacher.toString() : "Anonymous"));
    // }




    




}

