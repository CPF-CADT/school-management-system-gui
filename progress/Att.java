package progress;

import java.util.Scanner;
import academic.Course;
import academic.CourseInstance;
import core.AcademicControl;
import user.Admin;
import user.Student;
import user.Teacher;
import user.User;

public class Att {
    public static void main(String[] args) {
        
        User admin = new Admin("adminUser", "adminPass"); // Assuming Admin has a constructor (username, password)
        Teacher t2 = new Teacher(
                "Jak",
                "Jony",
                "456 Elm St, CA",
                "9876543210",
                "Mathematics");
    
        Student s1=new Student("kon","khmer","82","123");
        Student s2=new Student("sfs","affd","82","131");

        Scanner sc = new Scanner(System.in);
        AcademicControl ac = new AcademicControl();
        Course c = new Course("C++ And OOP", "CPP", 60.0f, "Basic Level");
        CourseInstance clas = new CourseInstance(c, t2, 2022, 2, "1");
        System.out.println(s1);
        System.out.println(s2);
        
        clas.studentEnrollment(admin);
        clas.studentEnrollment(admin);
        System.out.println(clas.getlistStudent());
        
        while(true){
            System.out.println("\n===== Course Management System =====");
            System.out.println("1 Create Course");
            System.out.println("1 Create Class");
            System.out.println("0 Exit");
            System.out.print("Select an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    // ac.createCourse(admin);
                    clas.checkAttendance(t2);
                    break;
                case 2:
                    // ac.createClass(admin);
                    clas.listAttendance();
                    break;
                case 3:
                    try {
                        CourseInstance stuClass = CourseInstance.findCourseInstance();
                        stuClass.studentEnrollment(admin);
                    } catch (NullPointerException n) {
                        System.out.println("Class not Found");
                    }
                    break;
                case 4:
                
                    break;
                case 0:
                    System.out.println(" Exiting the system. Goodbye!");
                    return; // Exit the program
                default:
                    System.out.println(" Invalid option. Please try again.");
            }
        }
    }
}
