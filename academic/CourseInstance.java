package academic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import core.Form;
import core.MySQLConnection;
import user.Admin;
import user.Student;
import user.Teacher;
import user.User;

public class CourseInstance {
    static Scanner input = new Scanner(System.in);
    public static HashMap<String, CourseInstance> listCourseInstace = new HashMap<String, CourseInstance>();
    protected String teacherID;
    public Course course;
    public int year;
    public int term;
    public String group;
    protected ArrayList<String> listStudent = new ArrayList<>(30);
    protected HashMap<String,String> listStudentName = new HashMap<>(30); // ID and name
    // private ArrayList<Quizz> quizzes = new ArrayList<Quizz>();
    // private ArrayList<Assignment> assignments = new ArrayList<>();
    public HashMap<String,ArrayList<Attendent>> attendentStu = new HashMap<>();
    private String keyIdentical;    
    private HashMap<String,ArrayList<Grading>> activityScore =  new HashMap<String,ArrayList<Grading>>();

    public CourseInstance(Course course, String teacherID, int year, int term, String group,ArrayList<String> listStudent) {
        this.teacherID = teacherID;
        this.course = course;
        this.year = year;
        this.term = term;
        this.group = group;
        keyIdentical = generatePrimaryKey(year, term, group ,course.getShortName());
        listCourseInstace.put(keyIdentical, this);
    }
    public CourseInstance(Course course, Teacher teacher, int year, int term, String group) {
        this.teacherID = teacher.getId();
        this.course = course;
        this.year = year;
        this.term = term;
        this.group = group;
        keyIdentical = generatePrimaryKey(year, term, group ,course.getShortName());
        teacher.addTeachingCourse(keyIdentical);
        listCourseInstace.put(keyIdentical, this);
    }


    // Generate key in format Year term course_code GROUP le 2021-T1-GDS-G1
    private static String generatePrimaryKey(int year, int term,String group,String shortName) {
        return String.format("%d-%d-%s-%s", year, term,group,shortName);
    }

    // Find instance by primary key
    public static CourseInstance findCourseInstance() {
        System.out.print("Year              : ");
        int year = Form.inputInteger();
        System.out.print("Term              : ");
        int term = Form.inputInteger();
        System.out.print("Course Short Name : ");
        String shortName = input.next();
        System.out.print("Group             : ");
        String group = input.next();
        String key = generatePrimaryKey(year, term, group, shortName);
        for (CourseInstance instance : CourseInstance.listCourseInstace.values()) {
            if (instance.getKeyIdentical().equals(key)) {
                return instance;
            }
        }
        return null;
    }

    public ArrayList<String> getlistStudent() {
        return listStudent;
    }

    public boolean studentEnrollment(User user) {
        if (!(user instanceof Admin)) {
            System.out.println("Permission Denied: Only Admins");
            return false;
        } else {
            System.out.println("Student Enrollment ");
            System.out.print("Student ID : ");
            String stuID = input.next();
            //Need add trycatch
            Student stu = (Student) User.listUser.get(stuID);
            if (stu != null) {
                listStudent.add(stuID);
                listStudentName.put(stuID,stu.firstName + " " + stu.lastName );
                Student s = (Student)User.listUser.get(stuID);
                s.addCourseStudy(user,this.keyIdentical);

                ArrayList<Grading> g = new ArrayList<Grading>();
                activityScore.put(stuID,g);

                ArrayList<Attendent> a = new ArrayList<Attendent>();
                attendentStu.put(stuID, a);

                return true;
            }
        }
        return false;
    }

    // private void allocateScoreForStudnet( HashMap<String, Float> listStu){
    //     for(String id : this.listStudent){
    //         listStu.put(id, 0.0f);
    //     }
    // }

    // public void setQuizzes(Object user, Quizz quizzes) {
    //     if (user instanceof Teacher) {
    //         this.quizzes.add(quizzes);
    //         allocateScoreForStudnet(quizzes.studentScore);
    //     } else {
    //         System.out.println("Access denied: You don't have permission!");
    //     }
    // }

    // public Assignment getAssignments(int index) {
    //     return assignments.get(index);
    // }
    
    // public void setAssignments(Object user, Assignment ass) {
    //     if (user instanceof Teacher) {
    //         this.assignments.add(ass);
    //         allocateScoreForStudnet(ass.studentScore);
    //     } else {
    //         System.out.println("Access denied: You don't have permission!");
    //     }
    // }
    public String getKeyIdentical() {
        return keyIdentical;
    }

    public void setStuGrade(User user,String stuID, String assessmentType,int ses_number,float socre) {
        if(user instanceof Teacher){
            Grading grade = new Grading(assessmentType, ses_number, socre);
            activityScore.get(stuID).add(grade);
        }else{
            System.out.println("Access Denied : teacherID Only");
        }
    }
    private void gradeHeader(){
        System.out.printf("%-5s ", "Score : ");
        for(int i=1;i<=activityScore.get(listStudent.get(0)).size();i++){
            System.out.printf("%-10s ",activityScore.get(listStudent.get(0)).get(i-1).assesmentType);
        }   
    }
    public void myAttendace(String id){
        attendaceHeader();
        System.out.println(); 
        System.out.printf("%-5s ",id);
        ArrayList<Attendent> attendents = attendentStu.get(id);
        if (attendents != null) {
            for (Attendent attendent : attendents) {
                System.out.printf("%-14s ",attendent);
            }
        } else {
            System.out.print("No attendents found");
        }
        System.out.println(); 
    }
    public void myGeade(String id){
        gradeHeader();
        System.out.println(); 
        System.out.printf("%-5s    ",id);
        ArrayList<Grading> grade = activityScore.get(id);
        for (Grading g : grade) {
            System.out.printf("%-10.2f ",g.score);
        }
        System.out.println(); 
    }
    public void showStuGrade(){
        gradeHeader();
        System.out.println(); 
        for (String id : listStudent) {
            System.out.printf("%-5s      ",id);
            ArrayList<Grading> grade = activityScore.get(id);
            for (Grading g : grade) {
                System.out.printf("%-10.2f ",g.score);
            }
            System.out.println(); 
        }
    }
    @SuppressWarnings("unused")
    public static void syncCourseInstance(ArrayList<String> instanceID){
        //check instanceID is null or not
        for(String classID : instanceID){
            String query = "SELECT * FROM Course_instance AS c WHERE c.course_instance_id = '"+classID+"';";
            ResultSet result = MySQLConnection.executeQuery(query);
            if(result!=null){
                try{
                    while(result.next()){
                        int year = result.getInt("year");
                        int term = result.getInt("term");
                        String group = result.getString("group_s");
                        String short_name = result.getString("short_name");
                        String teacher_id = result.getString("teacher_id");
                        Course.syncCourse(short_name);
                        try{
                            Course course = Course.findCourse(short_name);
                            ArrayList<String> listStu = getStudentList(year,term,group,short_name);
                            CourseInstance c = new CourseInstance(course, teacher_id, year, term, group,listStu);
                        }catch(NullPointerException nu){
                            System.out.println("Cannot Sync" + nu.getMessage());
                        }
                    }
                }catch(SQLException e){
                    System.out.println("Cannot Sync"+e.getMessage());
                }
            }
        }
    }
    private static ArrayList<String> getStudentList(int y,int t,String g,String short_name){
        String query = "SELECT student_id FROM Enrollment WHERE course_instance_id = (SELECT course_instance_id FROM Course_instance WHERE year = "+y+" AND term = "+t+" AND group_s = '"+g+"' AND short_name = '"+short_name+"');";
        ArrayList<String> list = new ArrayList<String>();
        ResultSet result =  MySQLConnection.executeQuery(query);
        if(result!=null){
            try{
                while (result.next()) {
                    list.add(result.getString("student_id"));
                }
            }catch(SQLException sql){
                System.out.println("No Student In Class "+ sql.getMessage());
            }
        }

        return list;
    }

    public void checkAttendance(User user){
        if(user instanceof Teacher){
            System.out.println("Check Attendance");
            for(String stu : listStudent ){
                System.out.println("ID : "+ stu +" - "+listStudentName.get(stu));
                Attendent atten = new Attendent(term);
                atten.tick();
                attendentStu.get(stu).add(atten);
            }
        }else{
            System.out.println("Permission Denie");
        }
    }

    private void attendaceHeader(){
        System.out.printf("%-5s ", "Session");
        for(int i=1;i<=attendentStu.get(listStudent.get(0)).size();i++){
            System.out.printf("%-10s ",String.valueOf(i));
        }

    }
    public void listAttendance(){
        attendaceHeader();
        System.out.println(); 
        for (String id : listStudent) {
            System.out.printf("%-5s ",id);
            ArrayList<Attendent> attendents = attendentStu.get(id);
            if (attendents != null) {
                for (Attendent attendent : attendents) {
                    System.out.printf("%-10s ",attendent);
                }
            } else {
                System.out.print("No attendents found");
            }
            System.out.println(); 
        }
    }
    // public void attempQuizz(Quizz q,User user){
    //     if(user instanceof Student){
    //         if(LocalDate.now().isBefore(q.getDue())){
    //             float score = q.attemp(user.getId());
    //             q.addStudentScore(user.getId(), score);
    //         }
    //     }else{
    //         System.out.println("Permission denie : ");
    //     }
    // }
    public void stuReport(String stuId){
        System.out.println("------------ Academic Report ------------");

        System.out.println("Grade      : " );
        System.out.println("Average    : " );

        // System.out.print("\nQuizz         : " ); 
        // stuQuizzScore(stuId);

        // System.out.print("\nAssignment     : " );
        // stuAssScore(stuId);

        System.out.print("\nActivity Score : " );

        System.out.println("Total Attendance : ");
    }
    // private String stuQuizzScore(String stuId){
    //     String score = " ";
    //     for(Quizz q : quizzes){
    //         score +=(q.studentScore.get(stuId)+" ,");
    //     }
    //     return score;
    // }
    // private String stuAssScore(String stuId){
    //     String score = " ";
    //     for(Assessment ass : assignments){
    //         score +=(ass.studentScore.get(stuId)+" ,");
    //     }
    //     return score;
    // }

    // private String stuActivityScore(String stuId){
    //     String score = " ";
    //     for(Assessment ass : assignments){
    //         score +=(ass.studentScore.get(stuId)+" ,");
    //     }
    //     return score;
    // }

    public void getResultForStudent(){

    }
}
