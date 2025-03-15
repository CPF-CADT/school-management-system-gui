// package progress;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Scanner;

// class TimerThread extends Thread {
//     private int timeLimit;  // Time limit in seconds
//     private int remainingTime;  // To store the remaining time
//     private boolean timeUp = false;
    
//     public TimerThread(int timeLimit) {
//         this.timeLimit = timeLimit;
//         this.remainingTime = timeLimit;

//     }
//     @Override
//     public void run() {
//         try {
//             for (int i = timeLimit; i > 0; i--) {
//                 remainingTime = i;  // Update remaining time
//                 System.out.print("\rRemaining Time: " + remainingTime+" "); // Print on same line (no refresh)
//                 Thread.sleep(1000);  // Sleep for 1 second
//             }
//             timeUp = true;
//             System.out.println("\nTime's up!");
//         } catch (InterruptedException e) {
//             System.out.println(e);
//         }
//     }

//     public boolean isTimeUp() {
//         return timeUp;
//     }

//     public int getTimeRemain() {
//         return remainingTime;
//     }


// }

// class Questiosn {
//     private String question;
//     private String[] choices;
//     private char correctAnswer;

//     public Question(String question, String[] choices, char correctAnswer) {
//         this.question = question;
//         this.choices = choices;
//         this.correctAnswer = correctAnswer;
//     }
//     public Question() {

//     }
//     public String getQuestion() {
//         return question;
//     }

//     public String[] getChoices() {
//         return choices;
//     }

//     public char getCorrectAnswer() {
//         return correctAnswer;
//     }
// }

// class LQuiz {
//     private ArrayList<Question> questions = new ArrayList<Question>();

//     public LQuiz() {
//         questions = new ArrayList<>();
//         loadQuestions();
//     }

//     private void loadQuestions() {
//         // Example questions
//         questions.add(new Question("What is the capital of France?", new String[]{"a) Berlin", "b) Madrid", "c) Paris", "d) Rome"}, 'c'));
//         questions.add(new Question("What is 2 + 2?", new String[]{"a) 3", "b) 4", "c) 5", "d) 6"}, 'b'));
//         questions.add(new Question("What is the color of the sky?", new String[]{"a) Blue", "b) Green", "c) Red", "d) Yellow"}, 'a'));
//         questions.add(new Question("What is the largest planet in our solar system?", new String[]{"a) Earth", "b) Mars", "c) Jupiter", "d) Saturn"}, 'c'));
//     }

//     public void startQuiz() {
//         Scanner scanner = new Scanner(System.in);
//         int score = 0;

//         for (Question question : questions) {
//             System.out.println(question.getQuestion());
//             for (String choice : question.getChoices()) {
//                 System.out.println(choice);
//             }

//             System.out.print("Your answer (a/b/c/d): ");
//             char userAnswer = scanner.next().charAt(0);

//             if (userAnswer == question.getCorrectAnswer()) {
//                 System.out.println("Correct!");
//                 score++;
//             } else {
//                 System.out.println("Wrong! The correct answer is " + question.getCorrectAnswer() + ")");
//             }
//             System.out.println();
//         }

//         System.out.println("Your total score: " + score + "/" + questions.size());
//         scanner.close();
//     }}

// public class QuizSystem {
//     public static void main(String[] args) {
//           Scanner input = new Scanner(System.in);
//         TimerThread timer = new TimerThread(5000);  // 10-second timer for each question
//         timer.start();
//          // Start the timer thread
        
//         // Simulate quiz question and user interaction
//         // System.out.println("Question 1: What is 2 + 2?");
//         LQuiz quiz = new LQuiz();
        
//         // Continuously display the remaining time without refreshing
//         while (!timer.isTimeUp()) {
//             try {
//                 quiz.startQuiz();
//                 Thread.sleep(500);  // Sleep for half a second to avoid excessive CPU usage
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
//         }
//         System.out.println("\nProceed to next question...");
//     }
// }
