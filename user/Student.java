package user;
// import academic.Course;
import academic.CourseInstance;
import core.Form;
import core.MySQLConnection;
import exception.NumberRangeExceptionHandling;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.stream.IntStream;
import java.util.stream.Stream;
public class Student  extends User{

    // public Date dob;
    final static String EMAIL_FORMAT="@stu.kdc.edu";
    static int numberOfStudents = 0;
    boolean status;
    private ArrayList<String> studyCourseID = new ArrayList<String>();
    //login fir compare
    public Student(String email, String password) {
        super(email, password);
    }

    public Student(String id,String firstName, String lastName, String address, String phoneNumber,String email,String password,boolean status) {
        super(firstName,lastName, address, phoneNumber,email,password);
        this.id = id;
        if(MySQLConnection.testConnection()){
            syncCourse();
        }
        this.status = status;
        User.listUser.put(this.id, this);
    }
    
    //register
    public Student(String firstName, String lastName, String address, String phoneNumber) {
        super(firstName,lastName, address, phoneNumber,EMAIL_FORMAT);
        super.id="S";
        super.id +=(String.valueOf(++numberOfStudents));
        status = true;
        User.listUser.put(this.id, this);
    }
    private String isActive(boolean status){
        if(status){
            return "Active";
        }else{
            return "non-active";
        }
    }
    @Override
    public String toString() {
        String userInfo = super.toString();
        userInfo += "ID            : " + id + "\n"
                + "Status        : " + isActive(status) + "\n"
                 + "Course         : " + studyCourseID + "\n"
                 + "====================================\n";
        return userInfo;
    }

    // public static Student FindStudentById(int id){
    //     for(int i :listOfStudent.keySet()){
    //         if(i== id){
    //             return listOfStudent.get(i);
    //         }
    //     }
    //     return null;
    // }
    //     public static Student login(User std) {
    //     for (User student : User.listUser.values()) {
    //         if (std.equals(student)) {
    //             return (Student) student;
    //         }
    //     }
    //     return null;
    // }
    
    public CourseInstance selectCourseStudy(){ 
        try{
            for(int i=0;i<studyCourseID.size();i++){
                System.out.println((i+1) + " . "+ CourseInstance.listCourseInstace.get(studyCourseID.get(i)).course.name );
            }
            System.out.print("Choose Course : ");
            int choose = Form.inputInteger();
            @SuppressWarnings("unused")
            NumberRangeExceptionHandling c = new NumberRangeExceptionHandling(1,studyCourseID.size(),choose);
            CourseInstance cStudy = CourseInstance.listCourseInstace.get(studyCourseID.get(choose-1));
            return cStudy;
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return null;
        }catch(NullPointerException n){
            System.out.println("You Dont have courses");
            return null;
        }

    }

    public String getId() {
        return id;
    }

    public  Student selectStudent() {
        if (!User.listUser.isEmpty()) {
            System.out.println("List Student ");
            for (User std : User.listUser.values()) {
                System.out.println(std);
            }
            System.out.print("Enter ID : ");
            String select = input.nextLine();
            User findUser = User.listUser.get(select);
            if(findUser instanceof Student student){
                return student;
            }else {
                System.out.println("ot kert");
                return null;
            }
        } else {
            System.out.println("No Student in list");
            return null;
        }
    }
    
    public boolean addCourseStudy(User user,String c_id){
        if(user instanceof Admin){
            //add validation
            studyCourseID.add(c_id);
            return true;
        }
        return false;
    }
    
    public void remove() {
        if (!User.listUser.isEmpty()) {
            System.out.println("List Student ");
            for (User std : User.listUser.values()) {
                System.out.println(std);
            }
            System.out.print("Enter ID : ");
            String select = input.nextLine();
             User.listUser.remove(select);
            System.out.println("Remove successfully");
        } else {
            System.out.println("No Student in list");
            
        }
    }


    public static void  syncNumberOfUser() throws SQLException {
        ResultSet r = MySQLConnection.executeQuery("SELECT COUNT(id) AS n FROM User WHERE id LIKE 'S%';");
        if (r!=null){
            if(r.next()){
                numberOfStudents = r.getInt("n");
            }
        }
        MySQLConnection.closeConnection();
    }
    @Override
    public int registerToMySQL() throws SQLException {        
        int row = super.registerToMySQL();
        String query = "INSERT INTO Students (user_id,status) "
        + "VALUES ('" + id + "','"+status+"');";    
        // + "VALUES ('" + id + "', '" + major + "');";    
        row += MySQLConnection.executeUpdate(query);
        MySQLConnection.closeConnection();
        return row ;
    }
    private void syncCourse(){
        String query = "SELECT e.course_instance_id AS c FROM  Enrollment e WHERE  e.student_id = '"+this.id+"';";
        ResultSet result = MySQLConnection.executeQuery(query);
        if(result!=null){
            try{
                while(result.next()){
                    String courseId = result.getString("c");
                    studyCourseID.add(courseId);
                }
                CourseInstance.syncCourseInstance(studyCourseID);
            }catch(Exception e){
                System.out.println("You Dont Have class ");
            }
        }
    }
    @Override
    public String[] toCSVFormat() {
    return Stream.concat(Arrays.stream(super.toCSVFormat()), Stream.of(String.valueOf(status)))
                 .toArray(String[]::new);
    }

    static public void loadAllStudent(){
        String query = "SELECT * FROM User AS u JOIN Students AS s ON u.id = s.user_id; ";
        ResultSet result = MySQLConnection.executeQuery(query);
        if(result!=null){
            try{
                while (result.next()) { 
                    String userId = result.getString("id"); 
                    String userEmail = result.getString("email");
                    String phone = result.getString("phone_number"); 
                    String userPassword = result.getString("password");
                    String firstName = result.getString("first_name");
                    String lastName = result.getString("last_name");
                    String address = result.getString("address");
                    boolean status = result.getBoolean("status");
                    @SuppressWarnings("unused")
                    Student user = new Student(userId, firstName,lastName, address, phone, userEmail, userPassword,status);
                }   
            }catch(SQLException s){
                
            }
        }else{
            System.out.println("Fail to Load Data ");
        }
    }
}
