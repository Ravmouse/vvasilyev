package ru.job4j.h7testtask.t1yandex;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A container for storing a collection of Events.
 */
public class EventContainer {

    /**
     * A linked list inside the container.
     */
    private LinkedList<Event> events;

    /**
     * The constructor.
     */
    public EventContainer() {
        events = new LinkedList<>();
    }

    /**
     * @param id is the id of Event.
     * @param title is the title of Event.
     */
    public void addEvent(int id, String title) {
        if ((id > 0) && (title != null)) {
            events.addLast(new Event(id, title));
        }
    }

    /**
     * @param val is the symbol to choose the way of calculation.
     * @return a time gap btw now and 1 min/hour/day.
     */
    private long getTimeGap(char val) {
        long res;
        switch (val) {
            case 'S':
                System.out.println("curr: " + System.currentTimeMillis());
                res = System.currentTimeMillis() - 60 * 1000;
                System.out.println("curr: " + System.currentTimeMillis());
                break;
            case 'M':
                res = System.currentTimeMillis() - 60 * 1000 * 3600;
                break;
            default:
                res = System.currentTimeMillis() - (60L * 1000L * 3600L * 24L);
        }
        System.out.println("res: " + res);
        return res;
    }

    /**
     * @return the amount of Events per 1 min.
     */
    public int getLastMinuteAmountOfEvents() {
        return getAmount(getTimeGap('S'));
    }

    /**
     * @return the amount of Events per 1 hour.
     */
    public long getLastHourAmountOfEvents() {
        return getAmount(getTimeGap('M'));
    }

    /**
     * @return the amount of Events per 1 day.
     */
    public long getLastDayAmountOfEvents() {
        return getAmount(getTimeGap('H'));
    }

    /**
     * @param value is the time gap in milliseconds
     * @return the amount of Events according to the time gap.
     */
    private int getAmount(long value) {
        int rsl = 0;
        Iterator<Event> it = events.descendingIterator();
        while ((it.hasNext()) && (it.next().getTimestamp() >= value)) {
            rsl++;
        }
        return rsl;
    }

    /**
     * Shows every Event in the container.
     */
    public void println() {
        for (Event e : events) {
            System.out.println(e);
        }
    }

    /**
     * @param args is the argument.
     */
    public static void main(String[] args) {
        EventContainer ev = new EventContainer();
        try {
            ev.addEvent(1, "first");
            System.out.println("event added: " + ev.events.getLast().getTimestamp());
            Thread.sleep(10000);
            ev.addEvent(2, "second");
            System.out.println("event added: " + ev.events.getLast().getTimestamp());
            Thread.sleep(15000);
            ev.addEvent(3, "third");
            System.out.println("event added: " + ev.events.getLast().getTimestamp());
            Thread.sleep(20000);
            ev.addEvent(4, "cola");
            System.out.println("event added: " + ev.events.getLast().getTimestamp());
            Thread.sleep(25000);
            ev.addEvent(5, "whiskey");
            System.out.println("event added: " + ev.events.getLast().getTimestamp());
            Thread.sleep(30000);
            ev.addEvent(6, "tequila");
            System.out.println("event added: " + ev.events.getLast().getTimestamp());
        } catch (InterruptedException e) {
            System.err.println("error");
        }
        System.out.println(ev.getLastMinuteAmountOfEvents());
    }
}