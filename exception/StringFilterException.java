package exception;

public class StringFilterException extends IllegalArgumentException{
    public StringFilterException(String message){
        super(message);
    }
    public StringFilterException(String text,String format,String formatMeaning) throws StringFilterException{
        if(!text.matches(format)){
            throw new StringFilterException("The String format is Wrong must be "+formatMeaning);
        }
    }
}   
