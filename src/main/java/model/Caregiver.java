package model;

import utils.DateConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Patients live in a NURSING home and are treated by nurses.
 */
public class Caregiver {
    private long cid;         /**Caregiver ID für Pfleger Liste*/
    private String Firstname; /**Vorname für Pfleger Liste*/
    private String Surname;   /**Nachname für Pfleger Liste*/
    private String Telephone; /**Telefon nummer für Pfleger Liste*/
    private List<Caregiver> allCaregivers = new ArrayList<Caregiver>(); /**CaregiverListe erstellt*/

    /**
     * constructs a patient from the given params.
     *
     * @param Firstname
     * @param Surname
     * @param Telephone
     */
    public Caregiver(String Firstname, String Surname, String Telephone) {
        this.Firstname = Firstname;
        this.Surname = Surname;
        this.Telephone = Telephone;
        /**Vor, nachname und Telfonummer zu Caregiver hinzugefügt*/
    }

    /**
     * constructs a patient from the given params.
     *
     * @param cid
     * @param Firstname
     * @param Surname
     * @param Telephone
     */
    public Caregiver(long cid, String Firstname, String Surname, String Telephone) {
        this.cid = cid;
        this.Firstname = Firstname;
        this.Surname = Surname;
        this.Telephone = Telephone;
        /**ID, Vorname, Nachname und Telefon nummer werden zu den variablen hinzugefügt*/
    }

    /**
     * @return patient id
     */
    public long getCid() { /**Gettet und returnt die Caregiver ID*/
        return cid;
    }
    public String getFirstname() { /**Gettet returnt den Caregiver Vornamen*/
        return Firstname;
    }

    public void setFirstname(String Firstname) { /**Setzt den Vornamen in die Caregiver Liste*/

        this.Firstname = Firstname;
    }

    public String getSurname() {/**Gettet returnt den Caregiver Nachnamen*/
        return Surname;
    }

    public void setSurname(String Surname) {/**Setzt den Nachnamen in die Caregiver Liste*/

        this.Surname = Surname;
    }

    public String getTelephone() {/**Gettet returnt den Caregiver Telefonnummer*/
        return Telephone;
    }

    public void setTelephone(String phonenumber) { /**Setzt die Telefonnummer in die Caregiver Liste*/
        this.Telephone = Telephone;
    }


    /**
     * adds a treatment to the treatment-list, if it does not already contain it.
     *
     * @param m Treatment
     * @return true if the treatment was not already part of the list. otherwise false
     */
    public boolean add(Caregiver m) {
        if (!this.allCaregivers.contains(m)) {
            this.allCaregivers.add(m);
            return true;
        }
        return false;
    }

    /**
     * @return string-representation of the caregiver
     */
    public String toString() {
        return "Caregiver" + "\nMNID: " + this.cid +
                "\nFirstname: " + this.Firstname +
                "\nSurname: " + this.Surname +
                "\nTelephone:" + this.Telephone +
                "\n";
    }
}