package academic;

import java.time.LocalDate;

import user.Teacher;
import user.User;

public class Grading {
    protected String assesmentType;
    protected LocalDate date;
    protected  int sessionNumber;
    protected float score;

    public Grading(){

    }

    public Grading(String assesmentType, int sessionNumber, float score) {
        this.assesmentType = assesmentType;
        this.sessionNumber = sessionNumber;
        this.score = score;
        date = LocalDate.now();
    }

    public void setScore(User user,float score) {
        if(user instanceof Teacher){
            this.score = score;
        }else{
            System.out.println("Access denied : Only Teacher");
        }
    }

    public String getAssesmentType() {
        return assesmentType;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public float getScore() {
        return score;
    }
    
}
