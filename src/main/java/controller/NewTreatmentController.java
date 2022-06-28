package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Caregiver;
import model.Patient;
import model.Treatment;
import utils.DateConverter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class NewTreatmentController {

    @FXML
    private TableView<Treatment> tableView;
    @FXML
    private Label lblSurnamePatient;
    @FXML
    private Label LblCaregiver;
    @FXML
    private Label lblFirstnamePatient;
    @FXML
    private TextField txtBegin;
    @FXML
    private TextField txtEnd;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextArea taRemarks;
    @FXML
    private DatePicker datepicker;
    @FXML
    private ComboBox<String> comboBoxCaregiver;

    private TreatmentDAO dao;
    private AllTreatmentController controller;
    private Patient patient;
    private Caregiver caregiver;
    private Stage stage;
    private ArrayList<Caregiver> caregiverList;
    private ObservableList<Treatment> tableviewContent = FXCollections.observableArrayList();
    private ObservableList<String> myComboBoxDataCaregiver = FXCollections.observableArrayList();

    public void initialize(AllTreatmentController controller, Stage stage, Patient patient) {
        this.controller= controller;
        this.patient = patient;
        this.stage = stage;
        comboBoxCaregiver.setItems(myComboBoxDataCaregiver);
        showPatientData();
        createComboBoxData();
        //showCaregiverData();
    }

    private void showPatientData(){
        this.lblFirstnamePatient.setText(patient.getFirstName());
        this.lblSurnamePatient.setText(patient.getSurname());
    }
    private void showCaregiverData(){
        this.LblCaregiver.setText(caregiver.getSurname());
    }

    @FXML
    public void handleAdd(){
        LocalDate date = this.datepicker.getValue();
        String s_begin = txtBegin.getText();
        LocalTime begin = DateConverter.convertStringToLocalTime(txtBegin.getText());
        LocalTime end = DateConverter.convertStringToLocalTime(txtEnd.getText());
        String description = txtDescription.getText();
        String remarks = taRemarks.getText();
        Treatment treatmentP = new Treatment(patient.getPid(), caregiver.getCid(), date,
                begin, end, description, remarks);
        createTreatment(treatmentP);
        controller.readAllAndShowInTableView();
        stage.close();
    }

    private void createComboBoxData() {
        CaregiverDAO cdao = DAOFactory.getDAOFactory().createCaregiverDAO();
        try {
            caregiverList = (ArrayList<Caregiver>) cdao.readAll();
            for (Caregiver caregiver : caregiverList) {
                this.myComboBoxDataCaregiver.add(caregiver.getSurname());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleComboBoxCaregiver(){
        String c = this.comboBoxCaregiver.getSelectionModel().getSelectedItem();
        this.dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        List<Treatment> allTreatments;
        if (c.equals("alle")) {
            try {
                allTreatments = this.dao.readAll();
                for (Treatment treatment : allTreatments) {
                    this.tableviewContent.add(treatment);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Caregiver caregiver = searchInListCaregiver(c);
        if (caregiver != null) {
            try {
                allTreatments = dao.readTreatmentsByPid(caregiver.getCid());

                for (Treatment treatment : allTreatments) {
                    this.tableviewContent.add(treatment);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private Caregiver searchInListCaregiver(String Surname) {
        for (int i = 0; i < this.caregiverList.size(); i++) {
            if (this.caregiverList.get(i).getSurname().equals(Surname)) {
                return this.caregiverList.get(i);
            }
        }
        return null;
    }


    @FXML
    public void handleDelete() {
        int index = this.tableView.getSelectionModel().getSelectedIndex();
        Treatment t = this.tableviewContent.remove(index);
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.deleteById(t.getTid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTreatment(Treatment treatmentP) {
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.create(treatmentP);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCancel(){
        stage.close();
    }
}