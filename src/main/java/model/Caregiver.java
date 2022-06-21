package model;

import utils.DateConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Patients live in a NURSING home and are treated by nurses.
 */
public class Caregiver extends Person {
    private long pid;
    private String phonenumber;
    private List<Caregiver> allCaregivers = new ArrayList<Caregiver>();

    /**
     * constructs a patient from the given params.
     * @param firstName
     * @param surname
     * @param phonenumber
     */
    public Caregiver(String firstName, String surname, String phonenumber) {
        super(firstName, surname);
        this.phonenumber = phonenumber;
    }

    /**
     * constructs a patient from the given params.
     * @param pid
     * @param firstName
     * @param surname
     * @param phonenumber
     */
    public Caregiver(long pid, String firstName, String surname, String phonenumber) {
        super(firstName, surname);
        this.pid = pid;
        this.phonenumber = phonenumber;
    }

    /**
     *
     * @return patient id
     */
    public long getPid() {
        return pid;
    }


    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }


    /**
     * adds a treatment to the treatment-list, if it does not already contain it.
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
     *
     * @return string-representation of the caregiver
     */
    public String toString() {
        return "Caregiver" + "\nMNID: " + this.pid +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nPhonenumber:" + this.phonenumber +
                "\n";
    }
}