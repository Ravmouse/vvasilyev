package ru.job4j.profession;

/**
 * Class Profession.
 */
public class Profession {

    /**
     * The name of the person.
     */
    private String name;

    /**
     * The amount of money that the person earns.
     */
    private Money salary;

    /**
     * The certificate of the person.
     */
    private Certificate certificate;

    /**
     * What vehicle does the person have?
     */
    private Vehicle vehicle;

    /**
     * The constructor of the class.
     * @param name The name of the person.
     * @param amount The amount of money.
     * @param currencyType The currency type of the money.
     * @param degree The degree of university.
     * @param vName The name of the vehicle.
     * @param vSpeed The speed of the vehicle.
     */
    public Profession(String name, int amount, String currencyType, String degree, String vName, int vSpeed) {
        this.name = name;
        salary = new Money(amount, currencyType);
        certificate = new Certificate(degree);
        vehicle = new Vehicle(vName, vSpeed);
    }

    /**
     * The method shows tne name, the vehicle and the speed.
     * @return The String.
     */
    public String goToWork() {
        return name + " добирается до работы на т/с " + vehicle.getTypeOfVehicle() + " со скоростью " + vehicle.getSpeed() + " км/ч.";
    }

    /**
     * The setter.
     * @param name The String.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The getter.
     * @return The String.
     */
    public String getName() {
        return name;
    }

    /**
     * The getter.
     * @return The Money type.
     */
    public Money getSalary() {
        return salary;
    }

    /**
     * The getter.
     * @return The Certificate type.
     */
    public Certificate getCertificate() {
        return certificate;
    }
}