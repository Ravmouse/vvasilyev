package ru.job4j.h7testtask.t1yandex;
import java.util.List;
import java.util.ArrayList;

/**
 * A container for storing a collection of Events.
 */
public class EventContainer {

    /**
     * A list inside the container.
     */
    private List<Event> events;

    /**
     * The constructor.
     */
    public EventContainer() {
        events = new ArrayList<>();
    }

    /**
     * @param id is the id of Event.
     * @param title is the title of Event.
     */
    public void addEvent(int id, String title) {
        if ((id >= 0) && (title != null)) {
            events.add(new Event(id, title));
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
     * Uses linear or binary search.
     * @param value is the time gap in milliseconds.
     * @return the amount of Events according to the time gap.
     */
    private int getAmount(long value) {
        // Linear search.
//        long start, finish;
//        int rsl = 0;
//        Iterator<Event> it = events.iterator();
//        start = System.currentTimeMillis();
//        System.out.println("start: " + start);
//        while ((it.hasNext()) && (it.next().getTimestamp() < value)) {
//            rsl++;
//        }
//        finish = System.currentTimeMillis();
//        System.out.println("finish: " + finish);
//        double res = (double) (finish - start) / 1000;
//        System.out.println("The time of linear searching: " + res);
//        return (events.size() - rsl);
//-----------------------------------------------------------------------
        // Binary search.
        long start, finish;
        int lower = 0;
        int upper = events.size() - 1;
        int curr, rsl;
        if (events.get(lower).getTimestamp() >= value) { //If the first event starts before the value,
            return events.size();                        //then need to return a size of the events.
        }
        if (events.get(upper).getTimestamp() < value) { //If the last event was added a long before the value,
            return 0;                                   //then need to return zero because no events are contained.
        }
        start = System.currentTimeMillis();
        System.out.println("start: " + start);
        while (true) {
            curr = (lower + upper) / 2;
            if (events.get(curr).getTimestamp() == value) {
                rsl =  events.size() - curr;
                break;
            }
            //If the (curr-1) and (curr+1) are greater (>) then the value, then the upper-value should be changed
            //to the curr's previous element -> curr-1.
            if ((events.get(curr - 1).getTimestamp() > value) && (events.get(curr + 1).getTimestamp() > value)) {
                upper = curr - 1;
            }
            //If the (curr-1) and (curr+1) are less (<) then the value, then the lower-value should be changed
            //to the curr's next element -> curr+1.
            if ((events.get(curr - 1).getTimestamp() < value) && (events.get(curr + 1).getTimestamp() < value)) {
                lower = curr + 1;
            }
            //If the value is equal to (curr-1)
            if (events.get(curr - 1).getTimestamp() == value) {
                rsl =  events.size() - curr + 1;
                break;
            }
            //If the value is equal to (curr+1)
            if (events.get(curr + 1).getTimestamp() == value) {
                rsl =  events.size() - curr - 1;
                break;
            }
            //If the value is inside the curr's (curr-1) and (curr+1), then we need to find out whether the curr is
            //greater or less the value.
            if ((events.get(curr - 1).getTimestamp() < value) && (events.get(curr + 1).getTimestamp() > value)) {
                rsl = (curr > value ? (events.size() - curr) : (events.size() - curr - 1));
                break;
            }
        }
        finish = System.currentTimeMillis();
        System.out.println("finish: " + finish);
        double res = (double) (finish - start) / 1000;
        System.out.println("The time of binary searching: " + res);
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
     * @param args args.
     * @throws InterruptedException is exception.
     */
    public static void main(String[] args) throws InterruptedException {
        EventContainer ev = new EventContainer();
        long start, finish;
        start = System.currentTimeMillis();
        System.out.println("start: " + start);
        for (int i = 0; i < 30_000_000; i++) {
            ev.addEvent(i, "123");
            if (i == 5_000_000) {
                Thread.sleep(10000);
            }
            if (i == 10_000_000) {
                Thread.sleep(10000);
            }
            if (i == 15_000_000) {
                Thread.sleep(10000);
            }
            if (i == 20_000_000) {
                Thread.sleep(10000);
            }
            if (i == 25_000_000) {
                Thread.sleep(10000);
            }
            if (i == 27_000_000) {
                Thread.sleep(10000);
            }
            if (i == 29_000_000) {
                Thread.sleep(10000);
            }
        }
        finish = System.currentTimeMillis();
        System.out.println("finish: " + finish);
        double res = (double) (finish - start) / 1000;
        System.out.println("The time of adding: " + res);
        System.out.println(ev.getLastMinuteAmountOfEvents());
    }
}