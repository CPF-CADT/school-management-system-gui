package academic;

import exception.NumberRangeExceptionHandling;
import java.time.LocalDate;
import java.util.Scanner;

public class Attendent {
    Scanner input = new Scanner(System.in);
    protected boolean present;
    protected boolean late ;
    protected boolean absent;
    protected boolean permission;
    protected LocalDate date;

    public Attendent(int session) {
        this.date = LocalDate.now();
    }

    public Attendent(){
        this.date = LocalDate.now();
    }

    public void tick(){
        System.out.println("1 . Presnt");	
        System.out.println("2 . Late");	
        System.out.println("3 . Absent");	
        System.out.println("4 . Permission");	
        System.out.print("Choose : ");
        int choice = input.nextInt();
        // Add excepttion from 1 to 4
        try {
            @SuppressWarnings("unused")
            NumberRangeExceptionHandling stuOption = new NumberRangeExceptionHandling(1,4,choice);
            switch(choice){
                case 1:
                    present = true;
                    late = absent = permission = false;   
                    break;
                case 2:
                    late = true;
                    present = absent = permission = false;
                    break;
                case 3:
                    absent = true;
                    present = late = permission = false;
                    break;
                case 4:
                    permission = true;
                    present = late = absent = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }}

        @Override
        public String toString() {
            if(present){
                return "present";
            }else if(late){
                return "Late";
            }else if(absent){
                return "absent";
            }else{
                return "permission";
            }
        }
}
