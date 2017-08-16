package ru.job4j.profession;

/**
 * Class Engineer.
 */
public class Engineer extends Profession {
    /**
     * Does the person work in office?
     */
    private boolean worksAtOffice;

    /**
     * The constructor of the class.
     * @param name The name of the person.
     * @param amount The amount of money.
     * @param currencyType The currency type of the money.
     * @param degree The degree of university.
     * @param vName The name of the vehicle.
     * @param vSpeed The speed of the vehicle.
     */
    public Engineer(String name, int amount, String currencyType, String degree, String vName, int vSpeed) {
        super(name, amount, currencyType, degree, vName, vSpeed);
        worksAtOffice = true;
    }

    /**
     * The method that returns Result type.
     * @param project Project type.
     * @return The Result type.
     */
    public Result designProject(Project project) {
        return new Result(project.getName(), this);
    }
}

/**
 * Class Project.
 */
class Project {
    /**
     * The name of the project.
     */
    private String name;

    /**
     * The constructor.
     * @param name name.
     */
    Project(String name) {
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