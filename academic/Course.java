package academic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import core.Form;
import core.MySQLConnection;
import exception.NumberRangeExceptionHandling;
import java.util.Scanner;
public class Course {
    Scanner input = new Scanner(System.in);
    static int totalCourse;
    private int courseId;
    
    public String name; //graphic design
    // public String level;
    public String description;

    public int getCourseId() {
        return courseId;
    }

    private float fee;
    private String shortName;
    public static HashMap<Integer, Course> listCourses = new HashMap<>();

    //constructor 
    public Course(String name, String shortName, float fee, String description) {
        totalCourse += 1;
        this.courseId = totalCourse;
        this.name = name;
        this.shortName = shortName;
        // this.level = level;
        this.fee = fee;
        this.description = description;
        listCourses.put(courseId, this);
    }
    
    // @Override
    // public String toString() {
    //     return "Course " + courseId + ", name=" + name + ", level=" + level + ", fee=" + fee + ", description="
    //             + description ;
    // }
    @Override
    public String toString() {
        return "Course ID: " + courseId + "\n" +
               "Name: " + name + "\n" +
               "Short Name: " + shortName + "\n" +
            //    "Level: " + level + "\n" +
               "Fee: $" + fee + "\n" +
               "Description: " + description;
    }

    // Method to find a course by short name and level
    // GDS, Beginner
    public static Course findCourse(String shortName) {
        for (Course course : Course.listCourses.values()) {
            if (course.shortName.equalsIgnoreCase(shortName)) {
                return course;
            }
        }
        return null;
    }
    public static void removeCourse(ArrayList<Course> courses, int inputId) {
        for (Course course : courses) {
            if (inputId == course.courseId) {
                courses.remove(course);
                System.out.println("Course removed successfully.");
                return;
            }
        }
    }
    @SuppressWarnings("unused")
    public static Course selectCourse(){
        displayCourses();
        System.out.print("Entetr Course ID : ");
        int courseID = 0;
        try{
            courseID = Form.inputInteger(); 
            NumberRangeExceptionHandling n = new NumberRangeExceptionHandling(1,Course.listCourses.size(),courseID);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return Course.listCourses.get(courseID);
    }
    // Method to display all courses
    public static void displayCourses() {
        for (Course course : Course.listCourses.values()) {
            System.out.println(course);
            System.out.println("------------------------------------------");

        }
    }  
    @SuppressWarnings("unused")
    public static void syncCourse(String shortName){
        String query = "SELECT * FROM Course WHERE short_name = '"+shortName+"';";
        ResultSet result = MySQLConnection.executeQuery(query);
        if(result!=null){
            try{
                if(result.next()){
                    Course c = new Course(result.getString("name"), result.getString("short_name"), result.getFloat("fee"), result.getString("description"));
                }
            }catch(SQLException sql){
                System.out.println("Course Not found" + sql.getMessage());
            }
        }
    }
    public float getFee() {
        return fee;
    }

    public String getShortName() {
        return shortName;
    } 
    public boolean updateCourseData(){
      String query = "UPDATE Course SET name = '"+name+"', fee = "+fee+", description = '"+description+"' WHERE short_name = '"+shortName+"';";
      MySQLConnection.executeUpdate(query);
        return true;
    }

    public boolean updateFeeCourse(Course course){
        System.out.println("Enter the new fee : ");
        float newFee = input.nextFloat();
        course.fee = newFee;
        System.out.println(course);
        return course.updateCourseData();
    }

    public boolean updateDescriptionInCourse(Course course){
        System.out.println("Enter the new Description : ");
        String newDescription = input.nextLine();
        course.description = newDescription;
        System.out.println(course);
        return course.updateCourseData();
    }



}