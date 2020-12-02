package emailChecker;

public class EmailFactory {

    public Email getTest(String passType){
        if(passType==null){
            return null;
        }else if(passType.equalsIgnoreCase("EMAILCHK")){
            return new CheckEmail("Invalid Email");
        }
        return null;
    }
}
