package register;

public class PasswordLength extends Password{

    public PasswordLength(String message) {
        super(message);
    }

    @Override
    public boolean checkPassword(String pass1, String pass2) {

        return !(pass1.length()>=6);
    }
}
