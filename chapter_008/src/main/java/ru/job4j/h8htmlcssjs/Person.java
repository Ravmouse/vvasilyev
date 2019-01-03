package ru.job4j.h8htmlcssjs;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Vitaly Vasilyev, date: 29.12.2018, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Person {
    /**
     * id.
     */
    private final int id;
    /**
     * Имя.
     */
    private String name;
    /**
     * Фамилия.
     */
    private String surname;
    /**
     * Пол.
     */
    private SEX sex;
    /**
     * Описание.
     */
    private String description;
    /**
     * Счетчик.
     */
    private static AtomicInteger count = new AtomicInteger();

    /**
     * Конструктор.
     */
    public Person() {
        this.id = count.incrementAndGet();
    }

    /**
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name имя.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return фамилия.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname фамилия.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return пол.
     */
    public SEX getSex() {
        return sex;
    }

    /**
     * @param sex пол.
     */
    public void setSex(String sex) {
        this.sex = sex.equals("Male") ? SEX.Male : SEX.Female;
    }

    /**
     * @return описание.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description описание.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return строковое представление.
     */
    @Override
    public String toString() {
        return String.format("name = %s, surname = %s, sex = %s, description = %s, id = %d", name, surname, sex, description, id);
    }
}

/**
 * Перечисление.
 */
enum SEX {
    /**
     * МУЖ.
     */
    Male,
    /**
     * ЖЕН.
     */
    Female
}