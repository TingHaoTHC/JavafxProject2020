package emailChecker;

public abstract class Email {
    private String message;

    private Email(){
        message="";
    }
    public Email(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
    public abstract boolean checkEmailChar(String email);
}
