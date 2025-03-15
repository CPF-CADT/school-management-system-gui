import java.sql.SQLException;
import java.util.Scanner;

import academic.CourseInstance;
import core.*;
import user.*;

public class Main {
    public static void main(String[] args) {
        headerProgram();
        Scanner input = new Scanner(System.in);
        int incorrectLoginCount = 3;
        Form form = new Form();
        System.out.println("No Internet Connection ");
        while (!MySQLConnection.testConnection()) {
            try{
                Thread.sleep(100);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        Feature.clearScreen();
        headerProgram();
        do {
            System.out.println("Login\n");
            User user = form.login();
            if (user != null) {
                if (user instanceof Admin) {
                    System.out.println("Admin Interface");
                    Admin admin = (Admin) user;
                    int option = 0 ;
                    do{
                        switch (Feature.admin()) {
                            case 1:
                                System.out.println("------------------ Create User Account ------------------ ");
                                try{
                                    Student.syncNumberOfUser();
                                    Teacher.syncNumberOfUser();
                                }catch(SQLException sql){
                                    System.out.println(sql.getMessage());
                                }
                                form.register();
                                break;
                            case 2:
                                System.out.println("------------------ Create Course ------------------ ");
                                AcademicControl.createCourse(admin);
                                break;
                            case 3:
                                System.out.println("------------------ Create Course Insatnce  ------------------ ");
                                AcademicControl.createClass(admin);
                                break;
                            case 4:
                                System.out.println("------------------ Assign Student ------------------ ");
                                try{
                                    CourseInstance stuClass = CourseInstance.findCourseInstance();
                                    stuClass.studentEnrollment(admin);
                                }catch(NullPointerException n){
                                    System.out.println("Class not Found");
                                }
                                break;
                            case 5:
                                System.out.println("------------------ Save to Cloud ------------------ ");
                                for(User u : User.listUser.values()){
                                    if (!(u instanceof Admin)){ //dont have table admin
                                        try{
                                            u.registerToMySQL();
                                            System.out.println(u.firstName + u.lastName + " Save.");
                                        }catch(SQLException e){
                                           System.out.println("data is already save");
                                        }
                                    }
                                }
                                break;
                            case 6:
                                System.out.println("------------------ Reset User Password ------------------ ");
                                System.out.print("User ID : ");
                                String userID = input.next();
                                try{
                                    User usr = form.loadData(userID, admin);
                                    System.out.println(usr);
                                    System.out.println("Pleace Check");
                                    System.out.println("1 . Reset Password");
                                    System.out.println("0 . Exit");
                                    System.out.print("Choise : ");
                                    int op = Form.inputInteger();
                                    if(op==1){
                                        usr.resetPassword(admin);
                                        usr.updateData();
                                    };
                                }catch(NullPointerException e){
                                    System.out.println("User Not found");
                                }   
                                break;
                            case 7:
                                System.out.println("------------------ Show All User ------------------ ");
                                System.out.println("");
                                Student.loadAllStudent();
                                Teacher.loadAllTeacher();
                                for(User u:User.listUser.values()){
                                    System.out.println(u.getEmail());
                                }
                                break;
                            case 0:
                                System.exit(1);;
                                break;
                            default:
                                break;
                        }
                    }while(option==0);
                } else if (user instanceof Teacher) {
                    System.out.println("Teacher Interface");
                    Teacher teacher = (Teacher) user; //casting
                    System.out.println(teacher);
                    switch (Feature.teacher()) {
                        case 1:
                            System.out.println( "-------------- Teaching Course -------------- ");
                            CourseInstance teachingClass =  teacher.selectCourseTeaching();
                            AcademicControl.courseInterfacceForTeacher(teacher, teachingClass);
                            break;
                        case 2:
                            System.out.println( "-------------- Update Information -------------- ");
                        case 3:
                            System.out.println( "-------------- Show Information -------------- ");
                            System.out.println(teacher);
                        case 0:
                            System.exit(1);;
                            break;
                        default:
                            break;
                    }
                } else if (user instanceof Student) {
                    System.out.println("Student Interface");
                    System.out.println(CourseInstance.listCourseInstace);
                    Student studentLogin = (Student) user;
                    System.out.println(studentLogin);
                    switch (Feature.student()) {
                        case 1:
                            System.out.println(" - Your Course ");
                            CourseInstance c =studentLogin.selectCourseStudy();
                            AcademicControl.courseInterfacceForStudent(studentLogin, c);
                            break;
                        case 2:
                            System.out.println( "-------------- Update Information -------------- ");
                        case 3:
                            System.out.println( "-------------- Show Information -------------- ");
                        case 0:
                            System.out.println(studentLogin);
                            System.exit(1);;
                            break;
                        default:
                            break;
                    }
                }
            } else {
                incorrectLoginCount-=1;
                System.out.println("Incorrect User account");
                if(incorrectLoginCount==0) break;
            }
        } while (true);
        input.close();
    }
    static void headerProgram(){
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println( "+             KHMER DEGITAL CENTER           +");
        System.out.println(" ++++++++++++++++++++++++++++++++++++++++++++++");
    }
}