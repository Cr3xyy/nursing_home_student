package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Patients live in a NURSING home and are treated by nurses.
 */
public class Caregiver {
    private long cid;
    private String Firstname;
    private String surname;
    private String Telephone;
    private List<Treatment> allTreatments = new ArrayList<Treatment>();

    /**
     * constructs a patient from the given params.
     * @param Firstname
     * @param Surname
     * @param Telephone
     */
    public Caregiver(String Firstname, String Surname, String Telephone) {
        this.Firstname = Firstname;
        this.surname = Surname;
        this.Telephone = Telephone;
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
        this.surname = Surname;
        this.Telephone = Telephone;
    }

    /**
     * @return patient id
     */
    public long getCid() {
        return cid;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String Firstname) {

        this.Firstname = Firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {

        this.surname = surname;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }


    /**
     * adds a treatment to the treatment-list, if it does not already contain it.
     *
     * @param m Treatment
     * @return true if the treatment was not already part of the list. otherwise false
     */
    public boolean add(Treatment m) {
        if (!this.allTreatments.contains(m)) {
            this.allTreatments.add(m);
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
                "\nPfleger: " + this.surname +
                "\nTelephone:" + this.Telephone +
                "\n";
    }
}