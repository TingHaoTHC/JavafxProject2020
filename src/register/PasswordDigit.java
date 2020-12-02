package register;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordDigit extends Password {

    public PasswordDigit(String message) {
        super(message);
    }

    @Override
    public boolean checkPassword(String pass1, String pass2) {

        Pattern digit = Pattern.compile("(.*[0-9])");
        Matcher hasDigit = digit.matcher(pass1);
        boolean res = hasDigit.find();

        return !res;
    }
}
