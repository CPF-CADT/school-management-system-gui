package progress;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import core.Form;
import user.Student;
import user.Teacher;
import user.User;

public class SaveToCSVFIle {
    public static void main(String[] args) {
        // Teacher t2 = new Teacher(
        //             "Jak",
        //             "Jony",
        //             "456 Elm St, CA",
        //             "9876543210",
        //             "Mathematics");    
        // Student s1=new Student("kon","khmer","82","123");
        while (true) {     
            System.out.println("1 . Save");
            System.out.println("2 . Read");
            System.out.println("3 . Create many acoount");
            switch (Form.inputInteger()) {
                case 1:
                    try{
                        FileWriter file = new FileWriter("./Data/User.csv");
                        // create FileWriter object with file as parameter 
                        BufferedWriter outputfile = new BufferedWriter(file); 
                        
                        // create CSVWriter object filewriter object as parameter 
                        CSVWriter writer = new CSVWriter(outputfile); 
                        String[] csvHeader = {"id", "firstName", "lastName", "dob", "address" ,"phoneNumber", "email", "password"};
                        for(User p : User.listUser.values()){
                            writer.writeNext(p.toCSVFormat()); 
                        }
                        User.listUser.clear();
                        writer.close(); 
                    }catch(Exception e){
    
                    }
                    break;
                case 2:
                    try (FileReader fileReader = new FileReader("./Data/User.csv");
                        CSVReader csvReader = new CSVReader(fileReader)) {
                        List<String[]> records = csvReader.readAll();
                        for (String[] record : records) {
                            if(record[0].contains("S")){
                                Student s = new Student(record[0], record[1], record[2], record[3], record[4], record[5], record[6], Boolean.valueOf(record[7]));
                            }else if(record[0].contains("T")){
                                Teacher t = new Teacher(record[0], record[1], record[2], record[3], record[4], record[5], record[6],record[7] ,Boolean.valueOf(record[8]));
                            }
                        }
                        System.out.println(User.listUser.values());
                    } catch (IOException | CsvException e) {
                        e.printStackTrace(); // Properly log the exception
                    } catch (NullPointerException e){
                        System.out.println("Cannot Read "+e.getMessage());
                    }
                    break;
                case 3:
                    try (FileReader fileReader = new FileReader("./Data/list_student.csv");
                        CSVReader csvReader = new CSVReader(fileReader)) {
                        List<String[]> records = csvReader.readAll();
                        for (String[] record : records) {
                            Student s = new Student(record[0], record[1], record[3], record[4]); // dont contain phone number
                        }
                        System.out.println(User.listUser.values());
                    } catch (IOException | CsvException e) {
                        e.printStackTrace(); // Properly log the exception
                    } catch (NullPointerException e){
                        System.out.println("Cannot Read "+e.getMessage());
                    }
                    break;
            
                default:
                    break;
            }
        }
    }
}
