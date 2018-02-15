package ru.job4j.h7testtask.t1yandex;

/**
 * A class for events.
 */
public class Event {
    /**
     * ID of an event.
     */
    private int id;
    /**
     * A title of an event.
     */
    private String title;
    /**
     * The time of creating an event.
     */
    private long timestamp;

    /**
     * @param id id.
     * @param title title.
     */
    public Event(int id, String title) {
        this.id = id;
        this.title = title;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * @return the time in milliseconds.
     */
    public long getTimestamp() {
        return this.timestamp;
    }

    /**
     * @return the string representation.
     */
    @Override
    public String toString() {
        return String.format("[id: %d, title: %s]", id, title);
    }
}