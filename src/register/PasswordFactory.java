package register;

public class PasswordFactory {

    public Password getTest(String passType){
        if(passType==null){
            return null;
        }else if(passType.equalsIgnoreCase("PWDSAME")){
           // System.out.println("Check same");
            return new PasswordSame("Pasword do not Match");
        }else if(passType.equalsIgnoreCase("PWDLENGTH")){
           // System.out.println("Password is not long enough");
            return new PasswordLength("Password is not long enough");
        }else if(passType.equalsIgnoreCase("PWDDIGIT")){
            //System.out.println("Password should contain a Number");
            return new PasswordCase("Password must contain Capital Letter");
        }else if(passType.equalsIgnoreCase("PWDCASE")){
            //System.out.println("Password should contain a Captial");
            return new PasswordDigit("Password does not contain a number");
        }else if(passType.equalsIgnoreCase("PWDCOMPROMISED")){
            //System.out.println("Check Compromised");
            return new PasswordCompromised("Password has been compromised");
        }
        return null;
    }
}
