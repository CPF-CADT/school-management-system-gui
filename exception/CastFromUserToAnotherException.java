package exception;

import user.Admin;
import user.Student;
import user.Teacher;
// import user.User;

public class CastFromUserToAnotherException extends ClassCastException {
    CastFromUserToAnotherException(String message){
        super(message);
    }
    public CastFromUserToAnotherException(Object source,Object destination) throws CastFromUserToAnotherException{
        boolean status = true;
        if(source != null){
            if(!((source instanceof Teacher) && (destination instanceof Teacher))){
                status = false;
            }else if (!((source instanceof Admin) && (destination instanceof Admin))){
                status =false;
            }else if(!((source instanceof Student) && (destination instanceof Student))){
                status =false;
            }
        }else{
            status = false;
        }
        if(!status){
            throw new CastFromUserToAnotherException("Can not be cast Class is in difference type");
        }
    }
}
