package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import datastorage.LoginDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import model.Caregiver;
import model.Login;

import java.sql.SQLException;
import java.util.List;

public class LogInController {


    @FXML
    private TextField textfieldUsername;
    @FXML
    private PasswordField passwordfield;
    @FXML
    private Button btnLogin;

    private ObservableList<Login> tableviewContent = FXCollections.observableArrayList();
    private LoginDAO dao;


    public void initialize() {}



    @FXML
    public void handleAdd() {

    }
}



