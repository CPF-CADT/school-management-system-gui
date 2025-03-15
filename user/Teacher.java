package user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import academic.CourseInstance;
import core.Form;
import core.MySQLConnection;
import exception.NumberRangeExceptionHandling;

// import java.util.HashMap;

public class Teacher extends User {
    static int numberOfTeacher = 0;
    final static String EMAIL_FORMAT = "@tch.kdc.edu";
    public ArrayList<String> teachingCourseId = new ArrayList<String>();
    public String major;
    public boolean status;
    // login
    public Teacher(String email, String password) {
        super(email, password);
    }

    // Teacher (User user,String major,ArrayList<String> teaching){
    //     this = (Teacher) user;
    // }
    public Teacher(String id,String firstName, String lastName, String address, String phoneNumber,String email,String password,String major,boolean status) {
        super(firstName,lastName, address, phoneNumber,email,password);
        this.id = id;
        this.major = major;
        this.status = true;
        if(MySQLConnection.testConnection()){
            syncCourse();
        }
        User.listUser.put(this.id, this);
    }
    // register
    public Teacher(String firstName,String lastName, String address, String phoneNumber, String major) {
        super(firstName,lastName, address, phoneNumber,EMAIL_FORMAT);
        super.id="T";
        super.id +=(String.valueOf(++numberOfTeacher));
        this.major = major;
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
        userInfo += "ID           : " + id + "\n"
                 + "Major         : " + major + "\n"
                 + "Status        : " + isActive(status) + "\n"
                 + "Course         : " + teachingCourseId + "\n"
                 + "====================================\n";
        return userInfo;
    }

    public void addTeachingCourse(String c_id){
            //add validation
        teachingCourseId.add(c_id);
    }

    public static void syncNumberOfUser() throws SQLException {
        ResultSet r = MySQLConnection.executeQuery("SELECT COUNT(id) AS n FROM User WHERE id LIKE 'T%';");
        if (r!=null){
            if(r.next()){
                numberOfTeacher = r.getInt("n");
            }
        }
        MySQLConnection.closeConnection();
    }

    public CourseInstance selectCourseTeaching(){
        for(int i=0;i<teachingCourseId.size();i++){
            System.out.println((i+1) + " . "+ CourseInstance.listCourseInstace.get(teachingCourseId.get(i)).course.name );
        }
        System.out.print("Choose Course : ");
        try{
            int choose = Form.inputInteger();
            @SuppressWarnings("unused")
            NumberRangeExceptionHandling c = new NumberRangeExceptionHandling(1,teachingCourseId.size(),choose);
            CourseInstance cStudy = CourseInstance.listCourseInstace.get(teachingCourseId.get(choose-1));
            return cStudy;
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return null;
        }

    }
    public String getId() {
        return id;
    }

    public int registerToMySQL() throws SQLException {        
        int row = super.registerToMySQL();
        String teacherQuery = "INSERT INTO Teachers (user_id, role_major,status) "
        + "VALUES ('" + id + "', '" + major + "','"+status+"');";    
        row += MySQLConnection.executeUpdate(teacherQuery);
        MySQLConnection.closeConnection();
        return row;
    }
    private void syncCourse(){
        String query = "SELECT c.course_instance_id  FROM Course_instance AS c WHERE teacher_id = '"+this.id+"';";
        ResultSet result = MySQLConnection.executeQuery(query);
        if(result!=null){
            try{
                while(result.next()){
                    String courseId = result.getString("course_instance_id");
                    teachingCourseId.add(courseId);
                }
                CourseInstance.syncCourseInstance(teachingCourseId);
            }catch(NullPointerException e){
                System.out.println("You Dont Have class");
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    @Override
    public String[] toCSVFormat() {
        return Stream.concat(Arrays.stream(super.toCSVFormat()), Stream.of(new String[]{major,String.valueOf(status)})).toArray(String[]::new);
    }
    static public void loadAllTeacher(){
        String query = "SELECT * FROM User AS u JOIN Teachers AS t ON u.id = t.user_id;";
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
                    String major = result.getString("role_major");
                    @SuppressWarnings("unused")
                    Teacher user = new Teacher( userId, firstName,lastName,  address,  phone, userEmail, userPassword, major,status);              
                } 
            }catch(SQLException s){
                
            }
        }else{
            System.out.println("Fail to Load Data ");
        }
    }
}
