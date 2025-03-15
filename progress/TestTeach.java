package progress;

import core.*;
import user.*;

public class TestTeach {
    public static void main(String[] args) {
        User teahcer = new Teacher(
            "Smith",
            "Alice",
            "456 Elm St, CA",
            "9876543210",
            "Mathematics");
        User admin =  new Admin(
            "John",
            "Doe",
            "123 Main St, NY",
            "1234567890");
        User tea = new Teacher("smithalice1@tch.kdc.edu", "kdc2025");
        Student s3=new Student("kon","chin","82","123");
        Student s4 =new Student("konjam3@std.kdc.edu", "yes");
        System.out.println(User.listUser.values());
        Form form = new Form();
        User user = form.login();
        if (user != null){
            System.out.println("true");
        }else
        System.out.println(".(false)");
    }
}
