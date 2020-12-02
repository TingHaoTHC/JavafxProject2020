package register;

public abstract class Password {
    private String pass1;
    private String pass2;
    private String checkRequired;
    private String message;

    public Password() {
        this.message = "";
    }

    public Password(String message) {
        this.message = message;
    }

    public Password(String pass1, String pass2, String checkRequired) {
        this.pass1 = pass1;
        this.pass2 = pass2;
        this.checkRequired = checkRequired;
        this.message = "";
    }

    public String getMessage() {
        return message;
    }



    public abstract boolean checkPassword(String pass1, String pass2);
}
