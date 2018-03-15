package ru.job4j.h7testtask.t3stock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Here the Issuer is created.
 */
public class Issuer {
    /**
     * The title of the issuer.
     */
    private final String title;
    /**
     * The List of ASK Orders.
     */
    private final List<Order> askList;
    /**
     * The List of BID Orders.
     */
    private final List<Order> bidList;

    /**
     * @param title of the Issuer.
     */
    public Issuer(String title) {
        this.title = title;
        this.askList = new ArrayList<>();
        this.bidList = new ArrayList<>();
    }

    /**
     * Forms the Order by adding or deleting it.
     * @param type is 0 for adding and 1 is for deleting existed Order.
     * @param action is 0 for ASK and is 1 for BID.
     * @param price of the Order.
     * @param volume of the Order.
     */
    public void formOrder(int type, int action, double price, int volume) {
        if (type == 0) {
            this.add(action, price, volume);
        } else {
            this.delete(action, price);
        }
    }

    /**
     * First the binary search is invoked to find out whether there is such Order with the entered price or not.
     * Then the Order should be created or updated.
     * After adding the Order the checkPricesAndVolumes() method is invoked.
     * @param action is 0 for ASK and is 1 for BID.
     * @param price of the Order.
     * @param volume of the Order.
     */
    public void add(int action, double price, int volume) {
        int tmp;
        if (action == 0) {                   //Если action == ASK...
            tmp = bSearch(askList, price);   //то выполнить bSearch(): есть ли уже эл-т с такой ценой среди добавленных.
            if (tmp != -1) {                 //Если bSearch() НЕ возвращ. -1, значит объект с такой ценой в ASK существует...
                askList.get(tmp).setVolume(askList.get(tmp).getVolume() + volume); //И нужно просто изменить цену этого объекта
            } else {                         //Если bSearch() возвращ. -1, значит либо объекта нет, либо список пуст.
                askList.add(new Order(0, action, price, volume));
                Collections.sort(askList, Collections.reverseOrder()); //Каждый раз после добавления - сортировка по убыванию цены.
                if (bidList.size() != 0) { //Если противоположный список не пуст, то нужно посмотреть, нет ли встречного предложения.
                    checkPricesAndVolumes(); //Выполняться должен только тогда, когда в BID есть хоть 1 элемент.
                }
            }
        } else {
            tmp = bSearch(bidList, price);
            if (tmp != -1) {
                bidList.get(tmp).setVolume(bidList.get(tmp).getVolume() + volume);
            } else {
                bidList.add(new Order(0, action, price, volume));
                Collections.sort(bidList, Collections.reverseOrder());
                if (askList.size() != 0) {
                    checkPricesAndVolumes(); //Выполняться должен только тогда, когда в ASK есть хоть 1 элемент.
                }
            }
        }

    }

    /**
     * Checks if the BID-Orders are greater or equal to Orders from ASK. If so, continues checking their volumes.
     * Проверяет на наличие заявок BID, которые должны быть больше или равны заявкам из ASK. После этого проверяет объемы.
     */
    private void checkPricesAndVolumes() {
        int i;
        int j;
        for (j = bidList.size() - 1; j > -1; j--) {       //От последнего эл-та в BID до первого...
            for (i = askList.size() - 1; i > -1; i--) {   //От последнего эл-та в ASK до первого...
                if (bidList.get(j).compareTo(askList.get(i)) >= 0) { //Если эл-т в BID больше или равен эл-ту в ASK,...
                    Order a = askList.get(i);
                    Order b = bidList.get(j);
                    if (b.getVolume() == a.getVolume()) { //То перейти к сравнению объемов. Если объемы равны...
                        bidList.remove(b);                //то удалить эл-ты.
                        askList.remove(a);
                        break;                            //И выйти из внутр.цикла, чтобы перейти к след.эл-ту в BID.
                    }
                    if (b.getVolume() > a.getVolume()) {  //Если объем в BID больше...
                        b.setVolume(b.getVolume() - a.getVolume()); //То вычесть из большего меньший
                        askList.remove(a); //Удалить эл-т в ASK. Далее - continue, т.е. продолжить сравнивать эл-т
                                           //из BID со след.эл-том из ASK.
                        continue; //Без continue в этом цикле продолжается работа с ссылками a и b, не смотря на то, что
                    }             //объект по ссылке a удален.
                    if (b.getVolume() < a.getVolume()) {
                        a.setVolume(a.getVolume() - b.getVolume());
                        bidList.remove(b);
                        break; //После удаления эл-та только из BID перейти к след.эл-ту из BID, чтобы проверить
                    }          //оставшийся эл-т из ASK.
                }
            }
        }
    }

    /**
     * @param action is 0 for ASK and is 1 for BID.
     * @param price of the Order.
     */
    public void delete(int action, double price) {
        int tmp;
        if (action == 0) {
            tmp = bSearch(askList, price);
            askList.remove(tmp);
        } else {
            tmp = bSearch(bidList, price);
            bidList.remove(tmp);
        }
    }

    /**
     * @param list that should be sorted.
     * @param price of the Order.
     * @return the position of found element or -1.
     */
    private int bSearch(List<Order> list, double price) {
        if (list.size() == 0) { //Это делается, если askList или bidList пустые, чтобы добавить объект без
            return -1;          //осуществления поиска.
        }
        int low, up, cur;
        low = 0;
        up = list.size() - 1;
        while (true) {
            cur = (low + up) >>> 1;
            final Order o = list.get(cur);
            if (o.getPrice() == price) {
                return cur;
            }  else if (low > up) {
                return -1;
            } else {
                if (o.getPrice() < price) {
                    low = cur + 1;
                } else {
                    up = cur - 1;
                }
            }
        }
    }

    /**
     * Prints out both of the Lists using merge.
     */
    public void print() {
        int i = 0, j = 0;
        System.out.println("\n" + title + "\nASK\tPRICE\tBID");
        while ((i < askList.size()) && (j < bidList.size())) {
            if (askList.get(i).compareTo(bidList.get(j)) > 0) {
                System.out.println(askList.get(i).getVolume() + "\t" + askList.get(i).getPrice());
                i++;
            } else if (askList.get(i).compareTo(bidList.get(j)) < 0) {
                System.out.println("\t" + bidList.get(j).getPrice() + "\t" + bidList.get(j).getVolume());
                j++;
            } else {
                System.out.println(askList.get(i).getVolume() + "\t" + askList.get(i).getPrice()
                        + "\t" + bidList.get(j).getVolume());
                i++;
                j++;
            }
        }
        while (i < askList.size()) {
            System.out.println(askList.get(i).getVolume() + "\t" + askList.get(i).getPrice());
            i++;
        }
        while (j < bidList.size()) {
            System.out.println("\t" + bidList.get(j).getPrice() + "\t" + bidList.get(j).getVolume());
            j++;
        }
    }


    /**
     * @param args args.
     */
    public static void main(String[] args) {
        Order ord = null;
        Random rnd = new Random();
        Issuer is = new Issuer("test");
        int amount = 20_000_000;

        long start = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            is.askList.add(new Order(0, 0, rnd.nextDouble(), rnd.nextInt(100)));
            if (i == 19_999_000) {
                ord = new Order(0, 0, 0.455, rnd.nextInt(100));
                is.askList.add(ord);
            }
        }
        System.out.println("ADD: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        Collections.sort(is.askList);
        System.out.println("SORT: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
//        boolean boo = is.askList.contains(ord);
        int c = is.bSearch(is.askList, 0.455);
//        int c = Collections.binarySearch(is.askList, ord);
//        if (boo) {
            System.out.println("SEARCH: " + (System.currentTimeMillis() - start));
//        }
        System.out.println(c);
    }
}

//180315.
//TreeSet = добавление элементов = 20 сек (10 млн), 53 сек (20 млн). Сортировать не нужно. Использовать бинарный поиск
//напрямую не нужно, т.к. есть метод contains(), который обеспечивает время O(log(n)) при поиске искомого элемента.
//Метод contains() -> 0 сек.

//HashSet = добавление элементов = 10 сек (10 млн), 32 сек (20 млн). Метод contains() -> 0 сек.

//ArrayList = добавление элементов = 2,3 сек (20 млн). Сортировка qsort() = 14 сек. Метод contains() -> 0,2 сек.
//Если использовать мой метод bSearch(), то время вообще 0 сек. Метод Collections.binarySearch() -> 0 сек.

//LinkedList = добавление элементов = 4,2 сек (20 млн). Сортировка qsort() = 19 сек.

//Почему мой метод bSearch() для LL выполянется 3,8 сек, в то время как Collections.binarySearch() выполняется 0,3 сек.
//В методе bSearch() было 2 вызова list.get(index). Как говорит Хорстманн, в LL не буферизуется индекс.
//Заменил на 1 вызов и присвоение Order o, а дальше использование этой переменной вместо постоянного вызова list.get(index).
//Время снизилось до 1,8 сек.