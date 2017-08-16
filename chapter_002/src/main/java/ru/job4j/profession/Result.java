package ru.job4j.profession;

/**
 * Class Result.
 */
public class Result {
    /**
     * The name of the result.
     */
    private String outputString;
    /**
     * The reference to the Profession type.
     */
    private Profession profession;

    /**
     * The constructor.
     * @param title title.
     * @param profession profession.
     */
    public Result(String title, Profession profession) {
        outputString = title;
        this.profession = profession;
    }

    /**
     * The toString method.
     * @return String.
     */
    @Override
    public String toString() {
        String result = null;
        if (profession instanceof Engineer) {
            result = "Результат достигнут, проект " + outputString + " реализован инженером " + profession.getName()
                     + ", у которого оклад " + profession.getSalary() + " и диплом " + profession.getCertificate();
        } else if (profession instanceof Doctor) {
            result = "Результат достигнут, пациент " + outputString + " вылечен доктором " + profession.getName()
                     + ", у которого оклад " + profession.getSalary() + " и диплом " + profession.getCertificate();
        } else {
            result = "Результат достигнут, студент " + outputString + " обучен учителем " + profession.getName()
                     + ", у которого оклад " + profession.getSalary() + " и диплом " + profession.getCertificate();
        }
        return result;
    }
}
