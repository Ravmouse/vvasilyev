package ru.job4j.h7testtask.t3stock;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Класс с хеш-отображениями для ASK и BID.
 */
public class TradeIssuer {
    /**
     * Название.
     */
    private final String title;
    /**
     * Хеш-карта для ASK.
     */
    private final Map<Double, Order> askPriceStock = new HashMap<>();
    /**
     * Хеш-карта для BID.
     */
    private final Map<Double, Order> bidPriceStock = new HashMap<>();

    /**
     * @param title Название.
     */
    public TradeIssuer(String title) {
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
        Map<Double, Order> currentMap = (action == 0 ? askPriceStock : bidPriceStock);
        currentMap.computeIfPresent(price, (aDouble, order) -> new Order(0, action, price, order.getVolume() + volume));
        currentMap.putIfAbsent(price, new Order(0, action, price, volume));
        if (askPriceStock.size() != 0 && bidPriceStock.size() != 0) {
            checkOtherMap();
        }
    }

    /**
     * Внутри создаются две TreeMap, чтобы элементы из HashMap были отсортированы. Из левой TreeMap(ASK) получается
     * самый последний ключ, и по нему ищется в правой TreeMap(BID) среди ключей, которые больше или равны, самый
     * меньший по значению ключ.
     */
    private void checkOtherMap() {
        TreeMap<Double, Order> askTreeMap = new TreeMap<>((object_1, object_2) -> Double.compare(object_2, object_1));
        TreeMap<Double, Order> bidTreeMap = new TreeMap<>((object_1, object_2) -> Double.compare(object_2, object_1));
        askTreeMap.putAll(askPriceStock);
        bidTreeMap.putAll(bidPriceStock);
        Double askKey;
        Double bidKey;
        while ((!askTreeMap.isEmpty()) && (!bidTreeMap.isEmpty()) && (bidTreeMap.floorKey(askTreeMap.lastKey()) != null)) {
            askKey = askTreeMap.lastKey();
            bidKey = bidTreeMap.floorKey(askKey);
            Order askOrder  = askPriceStock.get(askKey);
            Order bidOrder  = bidPriceStock.get(bidKey);
            if (askOrder.getVolume() > bidOrder.getVolume()) {
                askOrder.setVolume(askOrder.getVolume() - bidOrder.getVolume());
                bidTreeMap.remove(bidKey);
                bidPriceStock.remove(bidKey);
            } else if (askOrder.getVolume() < bidOrder.getVolume()) {
                bidOrder.setVolume(bidOrder.getVolume() - askOrder.getVolume());
                askTreeMap.remove(askKey);
                askPriceStock.remove(askKey);
            } else {
                askTreeMap.remove(askKey);
                bidTreeMap.remove(bidKey);
                askPriceStock.remove(askKey);
                bidPriceStock.remove(bidKey);
            }
        }
    }

    /**
     * @param action параметр, который указывает откуда удалять Order: из ASK или из BID.
     * @param price значение, по которому будет найден Order.
     */
    public void delete(int action, double price) {
        Map<Double, Order> currentMap = (action == 0 ? askPriceStock : bidPriceStock);
        Order order = currentMap.get(price);
        if (order != null) {
            currentMap.remove(price);
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
        final TreeSet<Double> askTree = new TreeSet<>((object_1, object_2) -> Double.compare(object_2, object_1));
        final TreeSet<Double> bidTree = new TreeSet<>((object_1, object_2) -> Double.compare(object_2, object_1));
        askTree.addAll(askPriceStock.keySet());
        bidTree.addAll(bidPriceStock.keySet());
        while (!askTree.isEmpty() && !bidTree.isEmpty()) {
            final Double askElement = askTree.first();
            final Double bidElement = bidTree.first();
            if (askElement > bidElement) {
                System.out.println(askPriceStock.get(askElement).getVolume() + "\t" + askPriceStock.get(askElement).getPrice());
                askTree.pollFirst();
            } else if (askElement < bidElement) {
                System.out.println("\t" + bidPriceStock.get(bidElement).getPrice() + "\t" + bidPriceStock.get(bidElement).getVolume());
                bidTree.pollFirst();
            } else {
                System.out.println(askPriceStock.get(askElement).getVolume() + "\t" + askPriceStock.get(askElement).getPrice()
                        + "\t" + bidPriceStock.get(bidElement).getVolume());
                askTree.pollFirst();
                bidTree.pollFirst();
            }
        }
        while (!askTree.isEmpty()) {
            System.out.println(askPriceStock.get(askTree.first()).getVolume() + "\t" + askPriceStock.get(askTree.first()).getPrice());
            askTree.pollFirst();
        }
        while (!bidTree.isEmpty()) {
            System.out.println("\t" + bidPriceStock.get(bidTree.first()).getPrice() + "\t" + bidPriceStock.get(bidTree.first()).getVolume());
            bidTree.pollFirst();
        }
    }
}