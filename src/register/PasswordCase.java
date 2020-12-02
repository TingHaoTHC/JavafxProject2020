package register;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCase extends Password {

    public PasswordCase(String message) {

        super(message);
    }

    @Override
    public boolean checkPassword(String pass1, String pass2) {
        char ch;
        boolean capitalFlag=false;
        for(int i=0;i < pass1.length();i++){
            ch = pass1.charAt(i);
            if (Character.isUpperCase(ch)){
                capitalFlag = true;
            }
        }
        return !capitalFlag;
    }

}
