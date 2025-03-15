package core;
import academic.*;
import exception.CastFromUserToAnotherException;
import exception.NumberRangeExceptionHandling;
import java.util.Scanner;
import user.*;
public class AcademicControl {
    static Scanner input = new Scanner(System.in);
    @SuppressWarnings("unused")
    public static boolean createCourse(User user) {
        if (!(user instanceof Admin)) {
            System.out.println("Permission Denied: Only Admins can create courses.");
            return false;
        }else{
            System.out.print("Enter Course Name: ");
            String name = input.nextLine();
            System.out.print("Enter Short Name: ");
            String shortName = input.next();
            // System.out.print("Enter Level: ");
            // String level = input.next();
            System.out.print("Enter Fee: ");
            float fee = (float) Form.inputNumber();
            input.nextLine();
            System.out.print("Enter Description: ");
            String description = input.nextLine();    
            Course c = new Course(name, shortName, fee, description);
            return true;
        }
    }

    @SuppressWarnings("unused")
    public static boolean createClass(User user){
        if (!(user instanceof Admin)) {
            System.out.println("Permission Denied: Only Admins can create class.");
            return false;
        }else{
            System.out.println("Enter The Clss Information");
            System.out.print("Year     : ");
            int year = Form.inputInteger();
            System.out.print("Term     : ");
            int term = Form.inputInteger();
            System.out.print("Group    : ");
            String group = input.next();
            System.out.println(" - Select Course ");
            Course c = Course.selectCourse();
            System.out.print("Teacher ID  : ");
            String teacherId = input.next();
            User usr = User.listUser.get(teacherId);
            Teacher t = null;
            try{
                CastFromUserToAnotherException toTeacher = new CastFromUserToAnotherException(c, t);
            }catch(ClassCastException cast){
                System.out.println("Cannot Create a class "+cast.getMessage());
                return false;
            }
            CourseInstance course = new CourseInstance( c, t, year,term,group);
        }
        // CourseInstance course = new CourseInstance( null, teacher, year,term,group);
        return true;
    }
    @SuppressWarnings("unused")
    public static void courseInterfacceForTeacher(Teacher teach,CourseInstance classLearn){
        System.out.println("------------- "+ classLearn.course.name  +"------------- ");
        System.out.println(" -  "+classLearn.course.description);
        System.out.println(" 1 .  List Student ");
        System.out.println(" 2 .  Grading ");
        System.out.println(" 3 .  View Student Grade ");
        System.out.println(" 4 .  Create Assignment -NOT");
        System.out.println(" 5 .  Create Quizz - IN DECIDE");
        System.out.println(" 6 .  Attendance ");
        System.out.println(" 7 .  View Attendacne ");
        System.out.println(" 0 .  Exit ");
        int option = Form.inputInteger();
        switch (option) {
            case 1:
                System.out.println(" -----------  List of Student ----------- ");
                System.out.printf("%-5s %-10s %-15s %-15s %-25s %-15s%n", "No.", "ID", "First Name", "Last Name", "Email", "Phone Number");
                System.out.println("------------------------------------------------------------------------------------------");
                for (int i = 0; i < classLearn.getlistStudent().size(); i++) {
                    Student stu = (Student) User.listUser.get(classLearn.getlistStudent().get(i));
                    System.out.printf("%-5d %-10s %-15s %-15s %-25s %-15s%n",
                            i + 1, stu.getId(), stu.firstName, stu.lastName, stu.getEmail(), stu.getPhoneNumber());
                }
                break;
            case 2:
                System.out.println(" -----------  Grading for Student ----------- ");
                System.out.print("Assessment Type : ");
                String assType = input.nextLine();
                System.out.print("Sesseon No     : ");
                int sesNo =0;
                try{
                    sesNo = Form.inputInteger(); // add validation leter

                }catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
                System.out.printf("%-5s %-10s %-15s %-15s%n", "No.", "ID", "First Name", "Last Name ");
                for (int i = 0; i < classLearn.getlistStudent().size(); i++) {
                    Student stu = (Student) User.listUser.get(classLearn.getlistStudent().get(i));
                    System.out.printf("%-5d %-10s %-15s %-15s%n",i + 1, stu.getId(), stu.firstName, stu.lastName);
                    System.out.print("Enter Score : " );
                    try{
                        float score = (float) Form.inputNumber();
                        NumberRangeExceptionHandling sc = new NumberRangeExceptionHandling(0.0,100.0,score);
                        classLearn.setStuGrade(teach,stu.getId(),assType,sesNo,score);
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                }
                break;
            case 3:
                //show gradingNumberRangeExceptionHandling
                System.out.println("Student Grade ");
                classLearn.showStuGrade();
                break;
            case 4:
                //
                break;
            case 5:
                
                break;
            case 6: //attendance student
                System.out.println("Check Attendance");
                classLearn.checkAttendance(teach);
                break;
            case 7:
                System.out.println("View attendacne");
                classLearn.listAttendance();
                break;
            default:
                break;
        }
    }

    public static void courseInterfacceForStudent(Student stu,CourseInstance classLearn){
        System.out.println("------------- "+ classLearn.course.name  +"------------- ");
        System.out.println(" -  "+classLearn.course.description);
        System.out.println(" 1 .  View Grade ");
        System.out.println(" 2 .  View Attendance");
        System.out.println(" 0 .  Exit ");
        int option = Form.inputInteger();
        switch (option) {
            case 1:
                classLearn.myGeade(stu.getId());
            break;
            case 2:
                classLearn.myAttendace(stu.getId());
            break;
            case 0:
                System.out.println("Logout");
            break;
        }
    }

}
