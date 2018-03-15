package ru.job4j.h7testtask.t3stock;

/**
 * The Order class.
 */
public class Order implements Comparable<Order> {
    /**
     * The type of the Order: ADD or DELETE.
     */
    private final Type type;
    /**
     * The action of the Order: ASK or BID.
     */
    private final Action act;
    /**
     * The price of the Order.
     */
    private final double price;
    /**
     * The volume.
     */
    private int volume;

    /**
     * @param type type.
     * @param act act.
     * @param price price.
     * @param volume volume.
     */
    public Order(int type, int act, double price, int volume) {
        this.type = (type == 0) ? Type.ADD : Type.DELETE;
        this.act = (act == 0) ? Action.ASK : Action.BID;
        this.price = price;
        this.volume = volume;
    }

    /**
     * @param order that should be compared.
     * @return -1, 0 or 1.
     */
    @Override
    public int compareTo(Order order) {
        return Double.compare(this.price, order.price);
    }

    /**
     * @return the type.
     */
    public Type getType() {
        return type;
    }

    /**
     * @return the price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param volume to be assigned.
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * @return the volume.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * @return the string representation of the Order object.
     */
    public String toString() {
        return String.format("Volume = %d; price = %f", this.volume, this.price);
    }

    /**
     * @param o is the Object to be compared.
     * @return true if two objects are equal and false, if they're not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        if (Double.compare(order.price, price) != 0) {
            return false;
        }
        return volume == order.volume;
    }

    /**
     * @return the hashcode of the instance of the class.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(price);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + volume;
        return result;
    }
}

/**
 * Action enum.
 */
enum Action {
    /**
     *
     */
    BID,
    /**
     *
     */
    ASK
}

/**
 * Type enum.
 */
enum Type {
    /**
     *
     */
    ADD,
    /**
     *
     */
    DELETE
}