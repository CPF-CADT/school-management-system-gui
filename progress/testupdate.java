package progress;

import java.sql.SQLException;
import java.util.Scanner;
import user.Teacher;
import user.User;


public class testupdate {
    Scanner input = new Scanner(System.in);



    // public boolean updateFirstName(User user)  {

    //     System.out.println("Enter the new first name: ");
    //     String firstName = input.nextLine();
    //     user.firstName = firstName;
    //     System.out.println(user);
    //     return user.updateData();
    // }
    public boolean updateLastName(User user)  {

        System.out.println("Enter the new last name: ");
        String lastName = input.nextLine();
        user.lastName = lastName;
        System.out.println(user);
        return user.updateData();
        
    }

    // public int updateAddress(User user)  {
    //     System.out.println("Enter the New Address: ");
    //     String address = input.nextLine();
    //     user.seta
    // }

    // public int updatePassword() throws SQLException {
    //     System.out.println("Enter the user id: ");
    //     String id = input.nextLine();

    //     if (userExists(id)) {
    //         System.out.println("User exists");
    //         System.out.println("Enter the new password: ");
    //         String password = input.nextLine();

    //         String userQuery = "UPDATE User u SET u.password = ? WHERE u.id = ?";
    //         try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //             stmt.setString(1, password);
    //             stmt.setString(2, id);
    //             int row = stmt.executeUpdate();
    //             MySQLConnection.closeConnection();
    //             return row;
    //         } catch (SQLException e) {
    //             System.out.println("Database error: " + e.getMessage());
    //             return 0;
    //         }
    //     } else {
    //         System.out.println("User does not exist");
    //         return 0;
    //     }
    // }

    // public int updatePhoneNumber() throws SQLException {
    //     System.out.println("Enter the user id: ");
    //     String id = input.nextLine();

    //     if (userExists(id)) {
    //         System.out.println("User exists");
    //         System.out.println("Enter the new phone number: ");
    //         String phoneNumber = input.nextLine();

    //         String userQuery = "UPDATE User u SET u.phone_number = ? WHERE u.id = ?";
    //         try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //             stmt.setString(1, phoneNumber);
    //             stmt.setString(2, id);
    //             int row = stmt.executeUpdate();
    //             MySQLConnection.closeConnection();
    //             return row;
    //         } catch (SQLException e) {
    //             System.out.println("Database error: " + e.getMessage());
    //             return 0;
    //         }
    //     } else {
    //         System.out.println("User does not exist");
    //         return 0;
    //     }
    // }

    // public int updateClassForAllStudent() throws SQLException {
    //     System.out.println("Enter the instance id: ");
    //     String instanceId = input.nextLine();

    //     if (instanceExists(instanceId)) {
    //         System.out.println("Instance exists");
    //         System.out.println("Enter the new class id: ");
    //         String classId = input.nextLine();

    //         String userQuery = "UPDATE Enrollment e SET e.class_id = ? WHERE e.instance_id = ?";
    //         try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //             stmt.setString(1, classId);
    //             stmt.setString(2, instanceId);
    //             int row = stmt.executeUpdate();
    //             MySQLConnection.closeConnection();
    //             return row;
    //         } catch (SQLException e) {
    //             System.out.println("Database error: " + e.getMessage());
    //             return 0;
    //         }
    //     } else {
    //         System.out.println("Instance does not exist");
    //         return 0;
    //     }
    // }

    // public int updateAStudenttoDifferentClass() throws SQLException {
    //     System.out.println("Enter the student id: ");
    //     String studentId = input.nextLine();

    //     System.out.println("Enter the new class id: ");
    //     String classId = input.nextLine();

    //     String userQuery = "UPDATE Enrollment e SET e.class_id = ? WHERE e.student_id = ?";
    //     try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //         stmt.setString(1, classId);
    //         stmt.setString(2, studentId);
    //         int row = stmt.executeUpdate();
    //         MySQLConnection.closeConnection();
    //         return row;
    //     } catch (SQLException e) {
    //         System.out.println("Database error: " + e.getMessage());
    //         return 0;
    //     }
    // }

    // public int updateFeeInCourse() throws SQLException {
    //     System.out.println("Enter the course short name: ");
    //     String shortName = input.nextLine();

    //     if (courseExists(shortName)) {
    //         System.out.println("Course exists");
    //         System.out.println("Enter the new fee: ");
    //         int fee = Integer.parseInt(input.nextLine());

    //         String userQuery = "UPDATE Course c SET c.fee = ? WHERE c.short_name = ?";
    //         try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //             stmt.setInt(1, fee);
    //             stmt.setString(2, shortName);
    //             int row = stmt.executeUpdate();
    //             MySQLConnection.closeConnection();
    //             return row;
    //         } catch (SQLException e) {
    //             System.out.println("Database error: " + e.getMessage());
    //             return 0;
    //         }
    //     } else {
    //         System.out.println("Course does not exist");
    //         return 0;
    //     }
    // }

    // public int updateDescriptionInCourse() throws SQLException {
    //     System.out.println("Enter the course short name: ");
    //     String shortName = input.nextLine();

    //     if (courseExists(shortName)) {
    //         System.out.println("Course exists");
    //         System.out.println("Enter the new description: ");
    //         String description = input.nextLine();

    //         String userQuery = "UPDATE Course c SET c.description = ? WHERE c.short_name = ?";
    //         try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //             stmt.setString(1, description);
    //             stmt.setString(2, shortName);
    //             int row = stmt.executeUpdate();
    //             MySQLConnection.closeConnection();
    //             return row;
    //         } catch (SQLException e) {
    //             System.out.println("Database error: " + e.getMessage());
    //             return 0;
    //         }
    //     } else {
    //         System.out.println("Course does not exist");
    //         return 0;
    //     }
    // }

    // public int updateTimeLimitInQuizz() throws SQLException {
    //     System.out.println("Enter the quiz id: ");
    //     int quizId = Integer.parseInt(input.nextLine());

    //     if (quizExists(quizId)) {
    //         System.out.println("Quiz exists");
    //         System.out.println("Enter the new time limit (HH:MM:SS): ");
    //         String timeLimitStr = input.nextLine();
    //         Time timeLimit = Time.valueOf(timeLimitStr);

    //         String userQuery = "UPDATE Quizz q SET q.time_limit = ? WHERE q.id = ?";
    //         try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //             stmt.setTime(1, timeLimit);
    //             stmt.setInt(2, quizId);
    //             int row = stmt.executeUpdate();
    //             MySQLConnection.closeConnection();
    //             return row;
    //         } catch (SQLException e) {
    //             System.out.println("Database error: " + e.getMessage());
    //             return 0;
    //         }
    //     } else {
    //         System.out.println("Quiz does not exist");
    //         return 0;
    //     }
    // }

    // public int updateDescriptionInQuizz() throws SQLException {
    //     System.out.println("Enter the quiz id: ");
    //     int quizId = Integer.parseInt(input.nextLine());

    //     if (quizExists(quizId)) {
    //         System.out.println("Quiz exists");
    //         System.out.println("Enter the new description: ");
    //         String description = input.nextLine();

    //         String userQuery = "UPDATE Quizz q SET q.description = ? WHERE q.id = ?";
    //         try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //             stmt.setString(1, description);
    //             stmt.setInt(2, quizId);
    //             int row = stmt.executeUpdate();
    //             MySQLConnection.closeConnection();
    //             return row;
    //         } catch (SQLException e) {
    //             System.out.println("Database error: " + e.getMessage());
    //             return 0;
    //         }
    //     } else {
    //         System.out.println("Quiz does not exist");
    //         return 0;
    //     }
    // }

    // public int updateTitleInQuizz() throws SQLException {
    //     System.out.println("Enter the quiz id: ");
    //     int quizId = Integer.parseInt(input.nextLine());

    //     if (quizExists(quizId)) {
    //         System.out.println("Quiz exists");
    //         System.out.println("Enter the new title: ");
    //         String title = input.nextLine();

    //         String userQuery = "UPDATE Quizz q SET q.title = ? WHERE q.id = ?";
    //         try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //             stmt.setString(1, title);
    //             stmt.setInt(2, quizId);
    //             int row = stmt.executeUpdate();
    //             MySQLConnection.closeConnection();
    //             return row;
    //         } catch (SQLException e) {
    //             System.out.println("Database error: " + e.getMessage());
    //             return 0;
    //         }
    //     } else {
    //         System.out.println("Quiz does not exist");
    //         return 0;
    //     }
    // }

    // public int updateDueDateInQuizz() throws SQLException {
    //     System.out.println("Enter the quiz id: ");
    //     int quizId = Integer.parseInt(input.nextLine());

    //     if (quizExists(quizId)) {
    //         System.out.println("Quiz exists");
    //         System.out.println("Enter the new due date (YYYY-MM-DD): ");
    //         String dueDateStr = input.nextLine();
    //         Date dueDate = Date.valueOf(dueDateStr);

    //         String userQuery = "UPDATE Quizz q SET q.due = ? WHERE q.id = ?";
    //         try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //             stmt.setDate(1, dueDate);
    //             stmt.setInt(2, quizId);
    //             int row = stmt.executeUpdate();
    //             MySQLConnection.closeConnection();
    //             return row;
    //         } catch (SQLException e) {
    //             System.out.println("Database error: " + e.getMessage());
    //             return 0;
    //         }
    //     } else {
    //         System.out.println("Quiz does not exist");
    //         return 0;
    //     }
    // }

    // public int updateInstructionInAssignment() throws SQLException {
    //     System.out.println("Enter the assignment id: ");
    //     int assignmentId = Integer.parseInt(input.nextLine());

    //     if (assignmentExists(assignmentId)) {
    //         System.out.println("Assignment exists");
    //         System.out.println("Enter the new instruction: ");
    //         String instruction = input.nextLine();

    //         String userQuery = "UPDATE Assignment a SET a.instruction = ? WHERE a.id = ?";
    //         try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //             stmt.setString(1, instruction);
    //             stmt.setInt(2, assignmentId);
    //             int row = stmt.executeUpdate();
    //             MySQLConnection.closeConnection();
    //             return row;
    //         } catch (SQLException e) {
    //             System.out.println("Database error: " + e.getMessage());
    //             return 0;
    //         }
    //     } else {
    //         System.out.println("Assignment does not exist");
    //         return 0;
    //     }
    // }

    // public int updateTitleInAssignment() throws SQLException {
    //     System.out.println("Enter the assignment id: ");
    //     int assignmentId = Integer.parseInt(input.nextLine());

    //     if (assignmentExists(assignmentId)) {
    //         System.out.println("Assignment exists");
    //         System.out.println("Enter the new title: ");
    //         String title = input.nextLine();

    //         String userQuery = "UPDATE Assignment a SET a.title = ? WHERE a.id = ?";
    //         try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //             stmt.setString(1, title);
    //             stmt.setInt(2, assignmentId);
    //             int row = stmt.executeUpdate();
    //             MySQLConnection.closeConnection();
    //             return row;
    //         } catch (SQLException e) {
    //             System.out.println("Database error: " + e.getMessage());
    //             return 0;
    //         }
    //     } else {
    //         System.out.println("Assignment does not exist");
    //         return 0;
    //     }
    // }

    // public int updateDescriptionInAssignment() throws SQLException {
    //     System.out.println("Enter the assignment id: ");
    //     int assignmentId = Integer.parseInt(input.nextLine());

    //     if (assignmentExists(assignmentId)) {
    //         System.out.println("Assignment exists");
    //         System.out.println("Enter the new description: ");
    //         String description = input.nextLine();

    //         String userQuery = "UPDATE Assignment a SET a.description = ? WHERE a.id = ?";
    //         try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //             stmt.setString(1, description);
    //             stmt.setInt(2, assignmentId);
    //             int row = stmt.executeUpdate();
    //             MySQLConnection.closeConnection();
    //             return row;
    //         } catch (SQLException e) {
    //             System.out.println("Database error: " + e.getMessage());
    //             return 0;
    //         }
    //     } else {
    //         System.out.println("Assignment does not exist");
    //         return 0;
    //     }
    // }

    // public int updateDueDateInAssignment() throws SQLException {
    //     System.out.println("Enter the assignment id: ");
    //     int assignmentId = Integer.parseInt(input.nextLine());

    //     if (assignmentExists(assignmentId)) {
    //         System.out.println("Assignment exists");
    //         System.out.println("Enter the new due date (YYYY-MM-DD): ");
    //         String dueDateStr = input.nextLine();
    //         Date dueDate = Date.valueOf(dueDateStr);

    //         String userQuery = "UPDATE Assignment a SET a.due = ? WHERE a.id = ?";
    //         try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //             stmt.setDate(1, dueDate);
    //             stmt.setInt(2, assignmentId);
    //             int row = stmt.executeUpdate();
    //             MySQLConnection.closeConnection();
    //             return row;
    //         } catch (SQLException e) {
    //             System.out.println("Database error: " + e.getMessage());
    //             return 0;
    //         }
    //     } else {
    //         System.out.println("Assignment does not exist");
    //         return 0;
    //     }
    // }

    // public int updateQuestion() throws SQLException {
    //     System.out.println("Enter the quiz id: ");
    //     int quizzId = Integer.parseInt(input.nextLine());

    //     System.out.println("Enter the question id: ");
    //     int questionId = Integer.parseInt(input.nextLine());

    //     if (questionExists(questionId, quizzId)) {
    //         System.out.println("Question exists");
    //         System.out.println("Enter the new question: ");
    //         String question = input.nextLine();

    //         String userQuery = "UPDATE Question q SET q.question = ? WHERE q.id = ? AND q.quizz_id = ?";
    //         try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //             stmt.setString(1, question);
    //             stmt.setInt(2, questionId);
    //             stmt.setInt(3, quizzId);
    //             int row = stmt.executeUpdate();
    //             MySQLConnection.closeConnection();
    //             return row;
    //         } catch (SQLException e) {
    //             System.out.println("Database error: " + e.getMessage());
    //             return 0;
    //         }
    //     } else {
    //         System.out.println("Question does not exist");
    //         return 0;
    //     }
    // }

    // public int updateMarkInQuestion() throws SQLException {
    //     System.out.println("Enter the quiz id: ");
    //     int quizzId = Integer.parseInt(input.nextLine());

    //     System.out.println("Enter the question id: ");
    //     int questionId = Integer.parseInt(input.nextLine());

    //     if (questionExists(questionId, quizzId)) {
    //         System.out.println("Question exists");
    //         System.out.println("Enter the new mark: ");
    //         int mark = Integer.parseInt(input.nextLine());

    //         String userQuery = "UPDATE Question q SET q.mark = ? WHERE q.id = ? AND q.quizz_id = ?";
    //         try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //             stmt.setInt(1, mark);
    //             stmt.setInt(2, questionId);
    //             stmt.setInt(3, quizzId);
    //             int row = stmt.executeUpdate();
    //             MySQLConnection.closeConnection();
    //             return row;
    //         } catch (SQLException e) {
    //             System.out.println("Database error: " + e.getMessage());
    //             return 0;
    //         }
    //     } else {
    //         System.out.println("Question does not exist");
    //         return 0;
    //     }
    // }

    // public int updateCorrectAnswerInQuestion() throws SQLException {
    //     System.out.println("Enter the quiz id: ");
    //     int quizzId = Integer.parseInt(input.nextLine());

    //     System.out.println("Enter the question id: ");
    //     int questionId = Integer.parseInt(input.nextLine());

    //     if (questionExists(questionId, quizzId)) {
    //         System.out.println("Question exists");
    //         System.out.println("Enter the new correct answer: ");
    //         String correctAnswer = input.nextLine();

    //         String userQuery = "UPDATE Question q SET q.correct_ans = ? WHERE q.id = ? AND q.quizz_id = ?";
    //         try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //             stmt.setString(1, correctAnswer);
    //             stmt.setInt(2, questionId);
    //             stmt.setInt(3, quizzId);
    //             int row = stmt.executeUpdate();
    //             MySQLConnection.closeConnection();
    //             return row;
    //         } catch (SQLException e) {
    //             System.out.println("Database error: " + e.getMessage());
    //             return 0;
    //         }
    //     } else {
    //         System.out.println("Question does not exist");
    //         return 0;
    //     }
    // }

    // public int updateOptionsInQuestion() throws SQLException {
    //     System.out.println("Enter the quiz id: ");
    //     int quizzId = Integer.parseInt(input.nextLine());

    //     System.out.println("Enter the question id: ");
    //     int questionId = Integer.parseInt(input.nextLine());

    //     if (questionExists(questionId, quizzId)) {
    //         System.out.println("Question exists");
    //         System.out.println("Enter the new options (as a JSON array): ");
    //         String options = input.nextLine();

    //         String userQuery = "UPDATE Question q SET q.options = ? WHERE q.id = ? AND q.quizz_id = ?";
    //         try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(userQuery)) {
    //             stmt.setString(1, options);
    //             stmt.setInt(2, questionId);
    //             stmt.setInt(3, quizzId);
    //             int row = stmt.executeUpdate();
    //             MySQLConnection.closeConnection();
    //             return row;
    //         } catch (SQLException e) {
    //             System.out.println("Database error: " + e.getMessage());
    //             return 0;
    //         }
    //     } else {
    //         System.out.println("Question does not exist");
    //         return 0;
    //     }
    // }
    public static void main(String[] args) throws SQLException {
      
            testupdate test = new testupdate();
            User user = new Teacher(
                "God",
                "joe",
                "456 Elm St, TP",
                "9812542210",
                "Mathematics");
            user.registerToMySQL();
            test.updateLastName(user);
            System.out.println(user);
        
    }
}
