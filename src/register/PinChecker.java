package register;

public class PinChecker {
    public static boolean checkPin (int Pin) {
        boolean status = false;
        boolean pinLength = (int) (Math.log10(Pin)+1) == 4;
        if (pinLength == true) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }
}
