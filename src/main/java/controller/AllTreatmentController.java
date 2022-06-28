package controller;

import datastorage.CaregiverDAO;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Caregiver;
import model.Patient;
import model.Treatment;
import datastorage.DAOFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AllTreatmentController {
    @FXML
    private TableView<Treatment> tableView;
    @FXML
    private TableColumn<Treatment, Integer> colID;
    @FXML
    private TableColumn<Treatment, Integer> colPid;
    @FXML
    private TableColumn<Treatment, String> colDate;
    @FXML
    private TableColumn<Treatment, String> colBegin;
    @FXML
    private TableColumn<Treatment, String> colEnd;
    @FXML
    private TableColumn<Treatment, String> colDescription;
    @FXML
    private TableColumn<Treatment, Integer> colCid;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ComboBox<String> comboBoxCaregiver;
    @FXML
    private Button btnNewTreatment;
    @FXML
    private Button btnDelete;

    private ObservableList<Treatment> tableviewContent = FXCollections.observableArrayList();
    private TreatmentDAO Tdao;
    private CaregiverDAO Cdao;
    private ObservableList<String> myComboBoxDataPatient = FXCollections.observableArrayList();
    private ObservableList<String> myComboBoxDataCaregiver = FXCollections.observableArrayList();
    private ArrayList<Patient> patientList;
    private ArrayList<Caregiver> caregiverList;
    private Main main;

    public void initialize() {
        readAllAndShowInTableView();
        comboBox.setItems(myComboBoxDataPatient);
        comboBox.getSelectionModel().select(1);
        //comboBoxCaregiver.setItems(myComboBoxDataCaregiver);
        //comboBoxCaregiver.getSelectionModel().select(2);
        this.main = main;


        this.colID.setCellValueFactory(new PropertyValueFactory<Treatment, Integer>("tid"));
        this.colPid.setCellValueFactory(new PropertyValueFactory<Treatment, Integer>("pid"));
        this.colCid.setCellValueFactory(new PropertyValueFactory<Treatment, Integer>("cid"));
        this.colDate.setCellValueFactory(new PropertyValueFactory<Treatment, String>("date"));
        this.colBegin.setCellValueFactory(new PropertyValueFactory<Treatment, String>("begin"));
        this.colEnd.setCellValueFactory(new PropertyValueFactory<Treatment, String>("end"));
        this.colDescription.setCellValueFactory(new PropertyValueFactory<Treatment, String>("description"));
        this.tableView.setItems(this.tableviewContent);
        createComboBoxData();
    }

    public void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        comboBox.getSelectionModel().select(0);
        //comboBoxCaregiver.getSelectionModel().select(0);
        this.Tdao = DAOFactory.getDAOFactory().createTreatmentDAO();
        List<Treatment> allTreatments;
        try {
            allTreatments = Tdao.readAll();
            for (Treatment treatment : allTreatments) {
                this.tableviewContent.add(treatment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createComboBoxData() {
        PatientDAO pdao = DAOFactory.getDAOFactory().createPatientDAO();
        //CaregiverDAO cdao = DAOFactory.getDAOFactory().createCaregiverDAO();
        try {
            patientList = (ArrayList<Patient>) pdao.readAll();
          //  caregiverList = (ArrayList<Caregiver>) cdao.readAll();
            this.myComboBoxDataPatient.add("alle");
            //this.myComboBoxDataCaregiver.add("alle");
            for (Patient patient : patientList) {
                this.myComboBoxDataPatient.add(patient.getSurname());
            }
          //  for (Caregiver caregiver : caregiverList) {
            //    this.myComboBoxDataCaregiver.add(caregiver.getSurname());
            //}
        } catch (SQLException e) {
              e.printStackTrace();
        }
    }


    @FXML
    public void handleComboBoxPatient() {
        String p = this.comboBox.getSelectionModel().getSelectedItem();
        //String c = this.comboBoxCaregiver.getSelectionModel().getSelectedItem();
        this.tableviewContent.clear();
        this.Tdao = DAOFactory.getDAOFactory().createTreatmentDAO();
        List<Treatment> allTreatments;
        if (p.equals("alle")) {
            try {
                allTreatments = this.Tdao.readAll();
                for (Treatment treatment : allTreatments) {
                    this.tableviewContent.add(treatment);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Patient patient = searchInListPatient(p);
        if (patient != null) {
            try {
                    allTreatments = Tdao.readTreatmentsByPid(patient.getPid());


                for (Treatment treatment : allTreatments) {
                    this.tableviewContent.add(treatment);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
/*
    public void handleComboBoxCaregiver(){
        String c = this.comboBoxCaregiver.getSelectionModel().getSelectedItem();
        this.tableviewContent.clear();
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
    */


    private Patient searchInListPatient(String surname) {
        for (int i = 0; i < this.patientList.size(); i++) {
            if (this.patientList.get(i).getSurname().equals(surname)) {
                return this.patientList.get(i);
            }
        }
        return null;
    }
/*
    private Caregiver searchInListCaregiver(String Surname) {
        for (int i = 0; i < this.caregiverList.size(); i++) {
            if (this.caregiverList.get(i).getSurname().equals(Surname)) {
                return this.caregiverList.get(i);
            }
        }
        return null;
    }

 */


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

    @FXML
    public void handleNewTreatment() {
        try {
            String p = this.comboBox.getSelectionModel().getSelectedItem();
            Patient patient = searchInListPatient(p);
            newTreatmentWindow(patient);
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Patient/Pfleger für die Behandlung fehlt !");
            alert.setContentText("Wählen Sie über die Combobox einen Patienten/Pfleger aus!");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleMouseClick() {
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (tableView.getSelectionModel().getSelectedItem() != null)) {
                int index = this.tableView.getSelectionModel().getSelectedIndex();
                Treatment treatment = this.tableviewContent.get(index);

                treatmentWindow(treatment);
            }
        });
    }

    public void newTreatmentWindow(Patient patient) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/NewTreatmentView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            //da die primaryStage noch im Hintergrund bleiben soll
            Stage stage = new Stage();

            NewTreatmentController controller = loader.getController();
            controller.initialize(this, stage, patient);


            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void treatmentWindow(Treatment treatment) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/TreatmentView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            //da die primaryStage noch im Hintergrund bleiben soll
            Stage stage = new Stage();
            TreatmentController controller = loader.getController();

            controller.initializeController(this, stage, treatment);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}