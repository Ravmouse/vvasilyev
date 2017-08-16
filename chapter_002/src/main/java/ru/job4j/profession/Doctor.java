package ru.job4j.profession;

/**
 * Class Doctor.
 */
public class Doctor extends Profession {
    /**
     * Does the person work in hospital?
     */
    private boolean worksAtHospital;

    /**
     * The constructor of the class.
     * @param name The name of the person.
     * @param amount The amount of money.
     * @param currencyType The currency type of the money.
     * @param degree The degree of university.
     * @param vName The name of the vehicle.
     * @param vSpeed The speed of the vehicle.
     */
    public Doctor(String name, int amount, String currencyType, String degree, String vName, int vSpeed) {
        super(name, amount, currencyType, degree, vName, vSpeed);
        worksAtHospital = true;
    }

    /**
     * The method that returns Result type.
     * @param patient Project type.
     * @return The Result type.
     */
    public Result treatPatient(Patient patient) {
        return new Result(patient.getName(), this);
    }
}

/**
 * Class Patient.
 */
class Patient {
    /**
     * The name of the patient.
     */
    private String name;

    /**
     * The constructor.
     * @param name name.
     */
    Patient(String name) {
        this.name = name;
    }

    /**
     * The getter.
     * @return String type.
     */
    public String getName() {
        return name;
    }
}