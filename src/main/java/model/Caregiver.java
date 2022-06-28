package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Patients live in a NURSING home and are treated by nurses.
 */
public class Caregiver {
    private long cid;
    private String Firstname;
    private String Surname;
    private String Telephone;
    private List<Caregiver> allCaregivers = new ArrayList<Caregiver>();

    /**
     * constructs a patient from the given params.
     * @param Firstname
     * @param Surname
     * @param Telephone
     */
    public Caregiver(String Firstname, String Surname, String Telephone) {
        this.Firstname = Firstname;
        this.Surname = Surname;
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
        this.Surname = Surname;
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
        return Surname;
    }

    public void setSurname(String Surname) {

        this.Surname = Surname;
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