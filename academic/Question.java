package academic;
import java.util.HashMap;
import java.util.Scanner;

import exception.StringFilterException;
import user.Teacher;

public class Question {
    Scanner input = new Scanner(System.in);
    private String question;
    private float  marks;
    private char correctAnswer;
    private HashMap<Character,String> choices = new HashMap<>();
    private final char[] option =  new char[]{'A','B','C','D'};

    public Question() {
    }
    
    public Question(String question,float  marks,char correctAnswer,HashMap<Character,String>choices){
        this.question = question;
        this.marks = marks;
        this.correctAnswer = correctAnswer;
        this.choices=choices;
    }
    public String getQuestion() {
        return question;
    }
    public double getMarks() {
        return marks;
    }
    public char getCorrectAnswer() {
        return correctAnswer;
    }
    

    // @Override
    // public String toString() {
    //     return "Question [ question=" + question + ", marks=" + marks + ", correctAnswer="
    //             + correctAnswer + ", choices=" + Arrays.toString(choices) + "]";
    // }

    public void createQuestion(Object user){
        if(user instanceof Teacher){
            System.out.print("Enter Question : ");
            question = input.nextLine();
            System.out.println("Enter Answer");
            for(int i=0;i<4;i++){
                System.out.print((option[i])+". ");
                String answer = input.nextLine();
                choices.put(option[i], answer);
            }
            System.out.print("Set Correct Answer (A-D) : ");
            correctAnswer = input.next().charAt(0);
            System.out.print("Set Mark : ");
            marks = input.nextFloat();
        }else{
            System.out.println("Access denied: You Dont have permission!");
        }
    }
    @SuppressWarnings("unused")
    public float answerQuestion(){
        System.out.println("Question : "+question);
        for(int i=0;i<4;i++){
            System.out.print(option[i]+" . "+ choices.get(option[i])+"\n");
        }
        try{
            char ans = ' ';
            while (true) {
                System.out.print("Enter : ");
                ans = input.next().charAt(0);
                StringFilterException answ = new StringFilterException(String.valueOf(ans), "^[A-D]$", "Answer A to D");
                break;
            }
            if(correctAnswer == ans ){
                System.out.println("Yeyy you're right !"+ans);
                return marks;
            }else{
                System.out.println("Nope try again it's wrong !");
            }
        }catch (IllegalArgumentException a){
            System.out.println(a.getMessage());
        }
        return 0.0f;
    }
    // public boolean updateQuestionData(){
    //     String query = "UPDATE question SET question = '" + question + "' , marks = '"+marks+"' WHERE question_id = " + no;
    //     MySQLConnection.executeUpdate(query);
    //     return true;
    // }
    // public boolean updateQuestion(Question question) {
    //     System.out.println("Enter the new question: ");
    //     String newQuestion = input.nextLine();
    //     this.question = newQuestion;
    //     System.out.println(question);
    //     return question.updateQuestionData();
    // }
    // public boolean updateMarks(Question question) {
    //     System.out.println("Enter the new marks: ");
    //     float newMarks = input.nextFloat();
    //     this.marks = newMarks;
    //     System.out.println(question);
    //     return question.updateQuestionData();
    // }

}
