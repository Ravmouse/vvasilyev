package ru.job4j.profession;

/**
 * Class Vehicle.
 */
public class Vehicle {
    /**
     * The type of vehicle.
     */
    private String typeOfVehicle;
    /**
     * The speed of vehicle.
     */
    private int speed;

    /**
     * The constructor.
     * @param typeOfVehicle typeOfVehicle.
     * @param speed speed.
     */
    public Vehicle(String typeOfVehicle, int speed) {
        this.typeOfVehicle = typeOfVehicle;
        this.speed = speed;
    }

    /**
     * The getter.
     * @return String.
     */
    public String getTypeOfVehicle() {
        return typeOfVehicle;
    }

    /**
     * The getter.
     * @return int.
     */
    public int getSpeed() {
        return speed;
    }
}
