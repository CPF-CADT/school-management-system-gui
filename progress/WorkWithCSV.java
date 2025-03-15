package progress;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
class Person{
    public static ArrayList<Person> listPersons = new ArrayList<Person>();
    public String username;
    @Override
    public String toString() {
        return "Person [username=" + username + ", pass=" + pass + "]";
    }
    private String pass;
    public Person(String username,String pass){
        this.username = username;
        this.pass = pass;
        listPersons.add(this);
    }
    public String[] toCSVFormat(){
        return new String[] {username, pass};
    }

}
public class WorkWithCSV {
    public static void main(String[] args) { 
        try {
            FileWriter file = new FileWriter("./Data/listUser.csv");
            // create FileWriter object with file as parameter 
            BufferedWriter outputfile = new BufferedWriter(file); 
    
            // create CSVWriter object filewriter object as parameter 
            CSVWriter writer = new CSVWriter(outputfile); 
    
            // adding header to csv 
            Person p1 = new Person("nak", "123");
            Person p2 = new Person("Bob", "123");
            Person p3 = new Person("Jak", "123");
            for(Person p : Person.listPersons){
                writer.writeNext(p.toCSVFormat()); 

            }
            // closing writer connection 
            writer.close(); 
        } catch (IOException e) {
            e.printStackTrace(); // Properly log the exception
        }
            
            try (FileReader fileReader = new FileReader("./Data/listUser.csv");
             CSVReader csvReader = new CSVReader(fileReader)) {
            
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                Person p = new Person(record[0], record[1]);
            }
            System.out.println(Person.listPersons);
        } catch (IOException | CsvException e) {
            e.printStackTrace(); // Properly log the exception
        }
        
    }

}
