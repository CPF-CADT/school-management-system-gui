package user;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

import core.MySQLConnection;
import exception.CastToUserHandleException;

public abstract class User {
    static Scanner input = new Scanner(System.in);
    static int numberOfUser = 0;
    public String lastName;
    public String firstName;

    protected LocalDate dob;
    protected String address;

    private String email;
    private String phoneNumber;
    private String password;
    protected String id;

    public static HashMap<String, User> listUser = new HashMap<String, User>();

    // login
    public User(String firstName, String lastName, String address, String phoneNumber, String email, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dob = LocalDate.now(); // need to input later
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // register
    public User(String firstName, String lastName, String address, String phoneNumber, String emailFormat) {
        ++numberOfUser;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dob = LocalDate.now(); // need to input later
        this.email = generateEmail(emailFormat).toLowerCase();
        this.password = "kdc2025"; // defult password
    }

    @Override
    public String toString() {
        String userInfo = "Name        : " + lastName + " " + firstName + "\n"
                + "Address     : " + address + "\n"
                + "DOB         : " + dob.toString() + "\n"
                + "Phone Number: " + phoneNumber + "\n"
                + "Email       : " + email + "\n";

        if (password.equals("kdc2025")) {
            userInfo += "Password (Default): " + password + "\n";
        }
        return userInfo;
    }

    // login
    public static User login(User log) {
        for (User user : User.listUser.values()) {
            if (log.equals(user)) {
                return user;
            }
        }
        System.out.println("Fail");
        return null;
    }

    @SuppressWarnings("unused")
    @Override
    public boolean equals(Object obj){
        try {
            CastToUserHandleException c = new CastToUserHandleException(obj);
            User log = (User) obj;
            if (this.email.equals(log.getEmail())) {
                if ((log.checkPassword(this.password))) {
                    return true;
                }
            }
        } catch (ClassCastException c) {
            System.out.println(c.getMessage());
        }
        return false;
    }

    public void setPassword(String newPassword, String curPassword) {
        if (curPassword.equals(this.password)) {
            this.password = newPassword;
        } else {
            System.out.println("Password Invalid");
        }
    }

    public boolean checkPassword(String password) {
        if (this.password.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email, String conPassword) {
        if (conPassword.equals(this.password)) {
            this.email = email;
        } else {
            System.out.println("Password Invalid");
        }
    }

    public String getId() {
        return id;
    }

    public void setPhoneNumber(String phoneNumber, String conPassword) {
        if (conPassword.equals(this.password)) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Password Invalid");
        }
    }

    private String generateEmail(String format) {
        return firstName + "." + lastName + numberOfUser + format;
    }
    public int registerToMySQL() throws SQLException {
        String userQuery = "INSERT INTO User (id, first_name, last_name, dob, address, email, phone_number, password) "
                + "VALUES ('" + id + "', '" + firstName + "', '" + lastName + "', '" + dob.toString() + "', '"
                + address + "', '" + email + "', '" + phoneNumber + "', '" + password + "');";
        int row = MySQLConnection.executeUpdate(userQuery);
        MySQLConnection.closeConnection();
        return row;
    }
    public String[] toCSVFormat(){
        return new String[]{id,firstName,lastName,dob.toString(),address,email,phoneNumber,password};
    } 

    public boolean updateData(){
        String query = "UPDATE User SET first_name = '" + firstName + "', last_name = '" + lastName + "', dob = '" + dob.toString() + "', address = '" + address + "', email = '" + email + "', phone_number = '" + phoneNumber + "', password = '" + password + "' WHERE id = '" + id + "';";
        MySQLConnection.executeUpdate(query);
        return true;
    }

    public boolean updateFirstName(User user) {
        System.out.println("Enter the Password to comfirm: ");
        String Cfpassword = input.nextLine();
        if (checkPassword(Cfpassword)) {
            System.out.println("Enter the new first name: ");
            String newFirstName = input.nextLine();
            user.firstName = newFirstName;
            System.out.println(user);
            return user.updateData();
        } else {
            System.out.println("Password Invalid");
            return false;
        }
    }

    public boolean updateLastName(User user) {
        System.out.println("Enter the Password to comfirm: ");
        String Cfpassword = input.nextLine();
        if (checkPassword(Cfpassword)) {
            System.out.println("Enter the new last name: ");
            String newLastName = input.nextLine();
            user.lastName = newLastName;
            System.out.println(user);
            return user.updateData();
        } else {
            System.out.println("Password Invalid");
            return false;
        }

    }

    public boolean updateAddress(User user) {
        System.out.println("Enter the Password to comfirm: ");
        String Cfpassword = input.nextLine();
        if (checkPassword(Cfpassword)) {
            System.out.println("Enter the new Address: ");
            String newAddress = input.nextLine();
            user.address = newAddress;
            System.out.println(user);
            return user.updateData();
        } else {
            System.out.println("Password Invalid");
            return false;
        }

    }

    public boolean updatePassword(User user) {
        System.out.println("Enter the Password to comfirm: ");
        String Cfpassword = input.nextLine();
        if (checkPassword(Cfpassword)) {

            System.out.println("Enter the new Password: ");
            String newPassword = input.nextLine();
            user.password = newPassword;
            System.out.println(user);
            return user.updateData();
        } else {
            System.out.println("Password Invalid");
            return false;
        }

    }

    public boolean updatePhoneNumber() {

        System.out.println("Enter the Password to comfirm: ");
        String Cfpassword = input.nextLine();
        if (checkPassword(Cfpassword)) {
            System.out.println("Enter the new PhoneNumber: ");
            String newPhoneNumber = input.nextLine();
            this.phoneNumber = newPhoneNumber;
            return updateData();
        } else {
            System.out.println("Password Invalid");
            return false;
        }

    }

    public void resetPassword(User user){
        if(user instanceof Admin){
            this.password = "kdc2025";
        }else{
            System.out.println("Permission Denie");
        }
    }
}
