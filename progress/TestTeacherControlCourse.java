package progress;

import java.util.Scanner;

import academic.Course;
import academic.CourseInstance;
import core.AcademicControl;
import user.Admin;
import user.Student;
import user.Teacher;
import user.User;

public class TestTeacherControlCourse {
    public static void main(String[] args) {
        User admin = new Admin("adminUser", "adminPass"); // Assuming Admin has a constructor (username, password)
         Teacher t2 = new Teacher(
        "Jak",
        "Jony",
        "456 Elm St, CA",
        "9876543210",
        "Mathematics");
        
        AcademicControl ac = new AcademicControl();
        Course c = new Course("C++ And OOP", "CPP", 60.0f, "Basic Level");
        CourseInstance clas = new CourseInstance(c, t2, 2022, 2, "1");
        System.out.println("\n===== Course Management System =====");
        
        Student s1=new Student("s","1","82","123");
        // Student s2=new Student("s","2","82","321");
        // Student s3=new Student("s","3","82","321");
        // Student s4=new Student("s","4","82","321");
        Scanner sc = new Scanner(System.in);
        // Assuming clas.studentEnrollment(admin) reads from System.in
        clas.studentEnrollment(admin);
        // clas.studentEnrollment(admin);
        // clas.studentEnrollment(admin);
        // clas.studentEnrollment(admin);
        System.out.println(clas.getlistStudent());
        CourseInstance cst = t2.selectCourseTeaching();
        for(int i=0;i<2;i++){
            AcademicControl.courseInterfacceForTeacher(t2,cst);
        }
        cst.myAttendace("S1");
        cst.myGeade("S1");
    }
}
