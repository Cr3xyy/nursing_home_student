package controller;

import datastorage.DAOFactory;
import datastorage.TreatmentDAO;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Caregiver;
import model.Patient;
import model.Treatment;
import utils.DateConverter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class NewTreatmentController {
    @FXML
    private Label lblSurnamePatient;
    @FXML
    private Label LblSurnameCaregiver;
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

    private AllTreatmentController controller;
    private Patient patient;
    private Caregiver caregiver;
    private Stage stage;

    public void initialize(AllTreatmentController controller, Stage stage, Patient patient, Caregiver caregiver) {
        this.controller= controller;
        this.patient = patient;
        this.caregiver = caregiver;
        this.stage = stage;
        showPatientData();
    }

    private void showPatientData(){
        this.lblFirstnamePatient.setText(patient.getFirstName());
        this.lblSurnamePatient.setText(patient.getSurname());
    }
    private void showCaregiverData(){
        this.LblSurnameCaregiver.setText(caregiver.getSurname());
    }

    @FXML
    public void handleAdd(){
        LocalDate date = this.datepicker.getValue();
        String s_begin = txtBegin.getText();
        LocalTime begin = DateConverter.convertStringToLocalTime(txtBegin.getText());
        LocalTime end = DateConverter.convertStringToLocalTime(txtEnd.getText());
        String description = txtDescription.getText();
        String remarks = taRemarks.getText();
        Treatment treatmentP = new Treatment(patient.getPid(), date,
                begin, end, description, remarks);
        createTreatment(treatmentP);
        controller.readAllAndShowInTableView();
        stage.close();
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