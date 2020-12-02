package emailChecker;
import emailChecker.Email;
import java.util.regex.Pattern;
public class CheckEmail extends Email {

    protected CheckEmail(String email){
        super(email);
    }

    @Override
    public boolean checkEmailChar(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@"+"(?:[a-zA-Z0-9-]+\\.)+[a-z" +"A-Z]{​​​​​​​2,7}​​​​​​​$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null){
            return true;
        }
        return !pat.matcher(email).matches();//the checks are found then F= NO error T= error that why i negated
    }

}