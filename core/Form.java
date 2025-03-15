package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import exception.StringFilterException;
import exception.NumberRangeExceptionHandling;
import user.Admin;
import user.Student;
import user.Teacher;
import user.User;

public class Form implements Authentication{
    static Scanner input = new Scanner(System.in);
    Feature f = new Feature();
    public int TYPE_OF_USER = 3;
    @Override
    public User login() {

        // System.out.print("Email address : ");
        // String email = input.next();
        // System.out.print("Password      : ");
        // String passsword = input.next();

        //TEST LOGIN
        String email = "admin@adm.kdc.edu";
        String passsword = "kdc2025";

        
        User user = null;   
        if (loadData(email,passsword)!=null) {
            if(email.endsWith("@tch.kdc.edu")) {
                user = new Teacher(email, passsword);
            }else if(email.endsWith("@stu.kdc.edu")){
                user = new Student(email, passsword);
            }else if(email.endsWith("@adm.kdc.edu")){
                user = new Admin(email, passsword);
            }
            return User.login(user);
        }
        // System.out.println("Fail");
        return null;
    }
    @SuppressWarnings("unused")
    @Override
    public boolean register(){
        String lastName, firstName, address, phoneNumber, role_major;
        boolean inputStats = true;
        int typeOfAccount =0;
        do{
            System.out.println("====================================");
            System.out.println("      USER REGISTRATION FORM       ");
            System.out.println("====================================");
            System.out.println("Choose Type User :");
            System.out.println("1 . Teacher");
            System.out.println("2 . Student");
            System.out.print("Choose : ");
            try{
                typeOfAccount = inputInteger();
                NumberRangeExceptionHandling userType = new NumberRangeExceptionHandling(1,TYPE_OF_USER,typeOfAccount);
                
                System.out.print("First Name   : ");
                firstName = input.next();
                StringFilterException f_name = new StringFilterException(firstName,"^[A-Za-z]+$","Alphabet Only");             
                System.out.print("Last Name    : ");

                lastName = input.next();
                StringFilterException l_name = new StringFilterException(lastName,"^[A-Za-z]+$","Alphabet Only");             
                input.nextLine();
        
                System.out.print("Address      : ");
                address = input.nextLine(); 
        
                System.out.print("Phone Number : ");
                phoneNumber = input.next();
                StringFilterException phone = new StringFilterException(phoneNumber,"^0[1-9]\\d{6,12}$","Invalid Phone Numebr Format");

                switch (typeOfAccount) {
                    case 1:
                        // Teacher
                        System.out.print("Major        : ");
                        role_major = input.next();
                        StringFilterException major = new StringFilterException(role_major, "^[A-Za-z]+([ -&][A-Za-z]+)*$", "Spcial character is not Allowed");
                        Teacher teach = new Teacher(firstName, lastName, address, phoneNumber, role_major);
                        break;
                    case 2:
                        // Student
                        Student stu = new Student(firstName, lastName, address, phoneNumber);
                        System.out.println("Student registered successfully!");
                        break;
                    default:
                        System.out.println("Invalid user type! Registration failed.");
                        return false;
                }
                return true;
            }catch(IllegalArgumentException registerCheck){
                f.clearScreen();
                System.out.println(registerCheck.getMessage());
                inputStats = false;
            }
        }while (!inputStats) ;
        return false;
    }
    public static int inputInteger(){
        int number = 0;
        while (true) {
            try{
                number = input.nextInt();
                return number;
            }catch(InputMismatchException e){
                System.out.println("Input Must be a Integer 0,1,2... ");
                System.out.print("Input : ");
            }
            input.nextLine();
        }
    }

    public static double inputNumber() throws IllegalArgumentException{
        double number = 0;
        while (true) {
            try{
                number = input.nextDouble();
                return number;
            }catch(InputMismatchException e){
                System.out.println("Input Must be a Number ");
                System.out.print("Input : ");
            }
            input.nextLine();
        }
    }

    @SuppressWarnings("unused")
    private static boolean isValidEmailFormat(String input) {
        return input.endsWith("@adm.kdc.edu") ||
                input.endsWith("@tch.kdc.edu") ||
                input.endsWith("@stu.kdc.edu");
    }
    public User loadData(String userID,User user){
        String query = " ";
        if(user instanceof Admin){
            if(userID.contains("S")){
                query = "SELECT u.id, u.first_name, u.last_name, u.dob, u.address, u.email, u.phone_number, u.password, s.status FROM User AS u JOIN Students AS s ON u.id = s.user_id WHERE u.id = '"+userID+"';"; //NOT READY 
            }else if(userID.contains("T")){
                query = "SELECT u.id, u.first_name, u.last_name, u.dob, u.address, u.email, u.phone_number, u.password, t.role_major,t.status FROM User AS u JOIN Teachers AS t ON u.id = t.user_id WHERE u.id = '"+userID+"';"; //NOT READY 
            }
            return getUsr(query);
        }
        return null;
    }
    public User loadData(String email,String password){
        String query = " ";
        if (email.endsWith("@stu.kdc.edu")){
            query = "SELECT u.id,u.first_name,u.last_name,u.dob,u.address,u.email,u.phone_number,u.password,s.status FROM User AS u JOIN Students AS s ON u.id = s.user_id WHERE email = '"+email+"' AND password = '"+password+"';";
        }else if(email.endsWith("@tch.kdc.edu")){
            query = "SELECT u.id, u.first_name, u.last_name, u.dob, u.address, u.email, u.phone_number, u.password, t.role_major,t.status FROM User AS u JOIN Teachers AS t ON u.id = t.user_id WHERE u.email = '"+email+"' AND u.password = '"+password+"';";
        }else if(email.endsWith("@adm.kdc.edu")) {
            query = "SELECT u.id, u.first_name, u.last_name, u.dob, u.address, u.email, u.phone_number, u.password FROM User AS u WHERE u.email = '"+email+"' AND u.password = '"+password+"';"; //NOT READY 
        }
        return getUsr(query);
    }
    private User getUsr(String query){
        ResultSet result = MySQLConnection.executeQuery(query);
        if(result!=null){
            try{
                if (result.next()) { 
                    String userId = result.getString("id"); 
                    String userEmail = result.getString("email");
                    String phone = result.getString("phone_number"); 
                    String userPassword = result.getString("password");
                    String firstName = result.getString("first_name");
                    String lastName = result.getString("last_name");
                    String address = result.getString("address");
                    if (userEmail.endsWith("@stu.kdc.edu")){
                        boolean status = result.getBoolean("status");
                        Student user = new Student(userId, firstName,lastName, address, phone, userEmail, userPassword,status);
                        return user;
                    }else if(userEmail.endsWith("@tch.kdc.edu")){
                        boolean status = result.getBoolean("status");
                        String major = result.getString("role_major");
                        Teacher user = new Teacher( userId, firstName,lastName,  address,  phone, userEmail, userPassword, major,status);
                        return user;
                    }else if(userEmail.endsWith("@adm.kdc.edu")){
                        Admin adm = new Admin(userId,firstName,lastName,address,phone,userEmail,userPassword);
                        return adm;
                    }
                } else {
                    System.out.println("Invalid email or password.");
                    return null;
                }
            }catch(SQLException s){
                
            }
        }else{
            System.out.println("Fail");
            return null;
        }
        return null;
    }
}
