package ru.job4j.profession;

/**
 * Class Teacher.
 */
public class Teacher extends Profession {
    /**
     * Does the person work in school?
     */
    private boolean worksAtSchool;

    /**
     * The constructor of the class.
     * @param name The name of the person.
     * @param amount The amount of money.
     * @param currencyType The currency type of the money.
     * @param degree The degree of university.
     * @param vName The name of the vehicle.
     * @param vSpeed The speed of the vehicle.
     */
    public Teacher(String name, int amount, String currencyType, String degree, String vName, int vSpeed) {
        super(name, amount, currencyType, degree, vName, vSpeed);
        worksAtSchool = true;
    }

    /**
     * The method that returns Result type.
     * @param student Project type.
     * @return The Result type.
     */
    public Result teachStudent(Student student) {
        return new Result(student.getName(), this);
    }
}

/**
 * Class Student.
 */
class Student {
    /**
     * The name of the student.
     */
    private String name;

    /**
     * The constructor.
     * @param name name.
     */
    Student(String name) {
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