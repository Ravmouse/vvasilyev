package ru.job4j.h7testtask.t3stock;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Класс с хеш-отображениями для ASK и BID.
 */
public class IssuerHash {
    /**
     * Название.
     */
    private final String title;
    /**
     * Хеш-карта для ASK.
     */
    private final Map<Double, Order> aMap = new HashMap<>();
    /**
     * Хеш-карта для BID.
     */
    private final Map<Double, Order> bMap = new HashMap<>();

    /**
     * @param title Название.
     */
    public IssuerHash(String title) {
        this.title = title;
    }

    /**
     * Используются computeIfPresent() и putIfAbsent().
     * Если по ключу есть значение, то создается новый Order. Если нет - то также создается новый Order.
     * @param action параметр, который указывает куда добавлять Order: в ASK или в BID.
     * @param price значение, по которому будет найден Order.
     * @param volume количество.
     */
    public void add(int action, double price, int volume) {
        Map<Double, Order> map = (action == 0 ? aMap : bMap);
        map.computeIfPresent(price, (aDouble, order) -> new Order(0, action, price, order.getVolume() + volume));
        map.putIfAbsent(price, new Order(0, action, price, volume));
        if (aMap.size() != 0 && bMap.size() != 0) {
            checkOtherMap();
        }
    }

    /**
     * Внутри создаются две TreeMap, чтобы элементы из HashMap были отсортированы. Из левой TreeMap(ASK) получается
     * самый последний ключ, и по нему ищется в правой TreeMap(BID) среди ключей, которые больше или равны, самый
     * меньший по значению ключ.
     */
    private void checkOtherMap() {
        TreeMap<Double, Order> askTreeMap = new TreeMap<>((o1, o2) -> Double.compare(o2, o1));
        TreeMap<Double, Order> bidTreeMap = new TreeMap<>((o1, o2) -> Double.compare(o2, o1));
        askTreeMap.putAll(aMap);
        bidTreeMap.putAll(bMap);
        Double askKey;
        Double bidKey;
        while ((!askTreeMap.isEmpty()) && (!bidTreeMap.isEmpty()) && (bidTreeMap.floorKey(askTreeMap.lastKey()) != null)) {
            askKey = askTreeMap.lastKey();
            bidKey = bidTreeMap.floorKey(askKey);
            Order a  = aMap.get(askKey);
            Order b  = bMap.get(bidKey);
            if (a.getVolume() > b.getVolume()) {
                a.setVolume(a.getVolume() - b.getVolume());
                bidTreeMap.remove(bidKey);
                bMap.remove(bidKey);
            } else if (a.getVolume() < b.getVolume()) {
                b.setVolume(b.getVolume() - a.getVolume());
                askTreeMap.remove(askKey);
                aMap.remove(askKey);
            } else {
                askTreeMap.remove(askKey);
                bidTreeMap.remove(bidKey);
                aMap.remove(askKey);
                bMap.remove(bidKey);
            }
        }
    }

    /**
     * @param action параметр, который указывает откуда удалять Order: из ASK или из BID.
     * @param price значение, по которому будет найден Order.
     */
    public void delete(int action, double price) {
        Map<Double, Order> map = (action == 0 ? aMap : bMap);
        Order order = map.get(price);
        if (order != null) {
            map.remove(price);
        } else {
            System.err.println("Such order doesn't exist or there are no orders left!");
        }
    }

    /**
     * Внутри создаются два TreeSet. В цикле вытаскиваются элементы из начала TreeSet. Потом один из них или оба
     * удаляются из начала TreeSet.
     */
    public void print() {
        System.out.println("\n" + title + "\nASK\tPRICE\tBID");
        final TreeSet<Double> askTree = new TreeSet<>((o1, o2) -> Double.compare(o2, o1));
        final TreeSet<Double> bidTree = new TreeSet<>((o1, o2) -> Double.compare(o2, o1));
        askTree.addAll(aMap.keySet());
        bidTree.addAll(bMap.keySet());
        while (!askTree.isEmpty() && !bidTree.isEmpty()) {
            final Double a = askTree.first();
            final Double b = bidTree.first();
            if (a > b) {
                System.out.println(aMap.get(a).getVolume() + "\t" + aMap.get(a).getPrice());
                askTree.pollFirst();
            } else if (a < b) {
                System.out.println("\t" + bMap.get(b).getPrice() + "\t" + bMap.get(b).getVolume());
                bidTree.pollFirst();
            } else {
                System.out.println(aMap.get(a).getVolume() + "\t" + aMap.get(a).getPrice()
                        + "\t" + bMap.get(b).getVolume());
                askTree.pollFirst();
                bidTree.pollFirst();
            }
        }
        while (!askTree.isEmpty()) {
            System.out.println(aMap.get(askTree.first()).getVolume() + "\t" + aMap.get(askTree.first()).getPrice());
            askTree.pollFirst();
        }
        while (!bidTree.isEmpty()) {
            System.out.println("\t" + bMap.get(bidTree.first()).getPrice() + "\t" + bMap.get(bidTree.first()).getVolume());
            bidTree.pollFirst();
        }
    }
}