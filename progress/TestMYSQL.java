package progress;
import java.sql.SQLException;
import user.Student;
import user.Teacher;

public class TestMYSQL {
    public static void main(String[] args) throws SQLException {
        // Form f = new Form();
        // f.register();
        Teacher.syncNumberOfUser();
        Teacher t2 = new Teacher(
                    "Jak",
                    "Jny",
                    "456 Elm St, CA",
                    "98724309210",
                    "Mathematics");
                    System.out.println(t2);
        Student.syncNumberOfUser();
        Student s1=new Student("kon","khmer","82","123");
        Student s2=new Student("kon","alien","70","9283");
        try {
            s2.registerToMySQL();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
