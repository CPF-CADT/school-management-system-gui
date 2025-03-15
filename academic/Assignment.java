package academic;

import core.MySQLConnection;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import user.Teacher;

public class Assignment extends Assessment {
    //fields
    public String instruction; 
    Scanner scanner = new Scanner(System.in);
    
    public Assignment(int courseId, String title, Teacher teacher, float score,String description) {
        super(courseId, title, teacher,score,description,LocalDate.now());
        this.description = description;
    }

    @Override
    public void addStudentScore(String studentId, float score) {
        try {
            if (score < 0 || score > totalScore) {
                throw new IllegalArgumentException("Score must be between 0 and " + totalScore + ".");
            }
            System.out.println("Score of " + score + " added for student ID: " + studentId);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // @Override
    // public void displayAssessment() {
    //     super.displayAssessment();
    //     System.out.println("Description: " + description);
    // }

    //for teacher  to create an assignment

    public void createAssignment(Object user) {
        if (user instanceof Teacher) {
            try {
                System.out.println("Enter Course ID: ");
                this.courseId = scanner.nextInt();
                scanner.nextLine(); // Consume leftover newline

                System.out.println("Enter Title: ");
                this.title = scanner.nextLine();

                System.out.println("Enter Description: ");
                this.description = scanner.nextLine();

                System.out.println("Enter Score: ");
                this.totalScore = scanner.nextFloat();
                
                if (this.totalScore < 0) {
                    throw new IllegalArgumentException("Score cannot be negative.");
                }

                System.out.println("Assignment created successfully!");

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter the correct data type.");
                scanner.nextLine(); // Clear invalid input
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Access denied: You don't have permission!");
        }
    }

    //displlay the assignment
    public void displayAssignment() {
        System.out.println("Course ID: " + courseId);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Score: " + totalScore);
        System.out.println("Assigned by: " + (teacher != null ? teacher.toString() : "Annonyomous")); //
    }

    //submit assignment
    public void submitAssignment(String studentName) {
        System.out.println(studentName + " has submitted the assignment: " + title); 
        
    }

    public boolean updateAssignmentData() {
        String query = "UPDATE assignment SET description = '" + description + "' , title = '"+title+"' ,  instruction = '"+ instruction+ "'WHERE assignment_id = " + no;
        MySQLConnection.executeUpdate(query);
        return true;
    }

    public boolean updateDescriptionInAssignment(Assignment assignment) {
        System.out.println("Enter the new description: ");
        String newDescription = scanner.nextLine();
        description = newDescription;
        System.out.println(assignment);
        return assignment.updateAssignmentData();
    }

    public boolean updateTitleInAssignment(Assignment assignment) {
        System.out.println("Enter the new title: ");
        String newTitle = scanner.nextLine();
        title = newTitle;
        System.out.println(assignment);
        return assignment.updateAssignmentData();
    }

    public boolean updateInstructionInAssignment(Assignment assignment) {
        System.out.println("Enter the new instruction: ");
        String newInstruction = scanner.nextLine();
        instruction = newInstruction;
        System.out.println(assignment);
        return assignment.updateAssignmentData();
    }

}