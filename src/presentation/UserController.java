package presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import register.PinChecker;

import java.net.URL;
import java.util.ResourceBundle;

import static persistence.StaffDAO.validatePin;
import static register.PinChecker.checkPin;

public class UserController implements Initializable {
    @FXML
    BorderPane userBorderPane;

    @FXML
    TextField pinTextField;

    @FXML
    Label messageLabel;

    @FXML
    Button btnGoToSuperHomepage;

    protected void setBtnGoToSuperHomepage(ActionEvent event) {
        //TODO for Discretionary Access Control.

        System.out.println("Login pressed");
        int pin = Integer.parseInt(pinTextField.getText());

        if (checkPin(pin) == true) {
            System.out.println("value passed!");
            if (validatePin(pin)) {
                System.out.println("The Pin is correct");
                goToSuperPage();
                closeWindow();
            } else {
                System.out.println("Incorrect Pin");
            }
        } else {
            System.out.println("Pin must be 4 characters!");
        }

        //showMessage("Trying To Login");

    }
    protected void btnBackAction(ActionEvent event) {
        System.out.println("User Back pressed");
        goToLoginPage();
        closeWindow();
    }
    private void goToSuperPage(){

        try{
            Parent root = FXMLLoader.load(getClass().getResource("super.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root));
            registerStage.show();

        } catch(Exception ex){
            ex.printStackTrace();

        }

    }
    private void goToLoginPage(){

        try{
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root));
            registerStage.show();

        } catch(Exception ex){
            ex.printStackTrace();

        }

    }
    private void closeWindow(){
        Stage stage = (Stage) userBorderPane.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void showMessage(String msg) {
        messageLabel.setText(msg);
    }
}
