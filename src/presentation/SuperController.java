package presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class SuperController implements Initializable {
    @FXML
    BorderPane superBorderPane;

    protected void btnBackAction(){
        System.out.println("super Back pressed");
        goToLoginPage();
        closeWindow();

    }
    protected void goToLoginPage(){

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
        Stage stage = (Stage) superBorderPane.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
