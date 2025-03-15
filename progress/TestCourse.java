package progress;

import academic.Course;
import academic.CourseInstance;
import core.AcademicControl;
import java.util.Scanner;
import user.Admin;
import user.Student;
import user.Teacher;
import user.User;

public class TestCourse {
    public static void main(String[] args) {
        User admin = new Admin("adminUser", "adminPass"); // Assuming Admin has a constructor (username, password)
         Teacher t2 = new Teacher(
                    "Jak",
                    "Jony",
                    "456 Elm St, CA",
                    "9876543210",
                    "Mathematics");
                    
                    
        Student s1=new Student("kon","khmer","82","123");

        while (true) {
            System.out.println("\n===== Course Management System =====");
            System.out.println("1 Create Course");
            System.out.println("1 Create Class");
            System.out.println("0 Exit");
            System.out.print("Select an option: ");

            Scanner sc = new Scanner(System.in);
            AcademicControl ac = new AcademicControl();
            Course c = new Course("C++ And OOP", "CPP", 60.0f, "Basic Level");
            CourseInstance clas = new CourseInstance(c, t2, 2022, 2, "1");
            // System.out.println(s1);
            // clas.StudentEnrollment(admin);
            // System.out.println(clas.getlistStudent());
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    // ac.createCourse(admin);
                    break;
                case 2:
                    // ac.createClass(admin);
                    break;
                case 3:
                    try{
                        CourseInstance stuClass = CourseInstance.findCourseInstance();
                        stuClass.studentEnrollment(admin);
                    }catch(NullPointerException n){
                        System.out.println("Class not Found");
                    }
                    break;
                case 0:
                    System.out.println(" Exiting the system. Goodbye!");
                    return;  // Exit the program
                default:
                    System.out.println(" Invalid option. Please try again.");
            }
        }
    }
}
