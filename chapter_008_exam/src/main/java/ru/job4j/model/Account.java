package ru.job4j.model;

/**
 * @author Vitaly Vasilyev, date: 17.01.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Account {
    /**
     * Имя.
     */
    private String name;
    /**
     * Фамилия.
     */
    private String surname;
    /**
     * Отчество.
     */
    private String patron;
    /**
     * Номер телефона.
     */
    private String mobile;
    /**
     * Место в зрительном зале.
     */
    private Seat seat;

    /**
     * @param name имя.
     * @param surname фамилия.
     * @param patron отчество.
     * @param mobile номер телефона.
     * @param seat место.
     */
    public Account(String name, String surname, String patron, String mobile, Seat seat) {
        this.name = name;
        this.surname = surname;
        this.patron = patron;
        this.mobile = mobile;
        this.seat = seat;
    }

    /**
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * @return фамилию.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @return отчество.
     */
    public String getPatron() {
        return patron;
    }

    /**
     * @return номер телефона.
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @return место.
     */
    public Seat getSeat() {
        return seat;
    }
}