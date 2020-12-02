package presentation;

import Model.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import persistence.StaffDAO;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private AnchorPane adminAncorPane;

    @FXML
    private Label msgLabel; // vulnerabilit. it assible to anything in that folder (private Label msgLabel

    @FXML
    private TableView<Staff> tableView;

    @FXML
    private TableColumn<Staff, String> userNameColumn;

    @FXML
    private TableColumn<Staff, String> roleColumn;

    @FXML
    private TableColumn<Staff, String> attemptsColumn;

    @FXML
    private TableColumn<Staff, Staff.LOCK> isLockedColumn;


    public void btnBackAction(ActionEvent event){
        System.out.println("Admin Back pressed");
        goToLoginPage();
        closeWindow();

    }
    public void goToLoginPage(){

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
    public void closeWindow(){
        Stage stage = (Stage) adminAncorPane.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        msgLabel.setText("");

        userNameColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("UserName"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("Role"));
        attemptsColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("LockAttempt"));
        isLockedColumn.setCellValueFactory(new PropertyValueFactory<>("lock"));

        tableView.setItems(StaffDAO.getAllStaff());
        tableView.setEditable(true);
        //userNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        isLockedColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Staff.LOCK.values()));
        isLockedColumn.setOnEditCommit(e -> {
            Staff s = e.getRowValue();
            e.getRowValue().setLock(e.getNewValue());
            msgLabel.setText(StaffDAO.updateLockedData(s.getUserName(), e.getNewValue().toString(), s.getRole()));
            System.out.println("UserName" + s.getUserName() + "Locked" + e.getNewValue() + " attempts " + s.getRole());
            //refresh TabelView data after update;
            tableView.setItems(StaffDAO.getAllStaff());
        });

        userNameColumn.setCellFactory(ComboBoxTableCell.forTableColumn("Deleted"));
        userNameColumn.setOnEditCommit(e -> {
            Staff s = e.getRowValue();
            e.getRowValue().setUserName(e.getNewValue());
            msgLabel.setText(String.valueOf(StaffDAO.deleteUser(s.getUserName())));
            System.out.println("UserName "+s.getUserName()+"Got deleted");
            //refresh TabelView data after update;
            tableView.setItems(StaffDAO.getAllStaff());
        });
    }
}
