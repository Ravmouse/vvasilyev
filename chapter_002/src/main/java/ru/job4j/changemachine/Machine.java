package ru.job4j.changemachine;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Machine.
 */
public class Machine {
    /**
     * String literal 10.
     * Строковый литерал 10.
     */
    private final String s1;
    /**
     * Two string literals 5 and 5.
     * Два строковых литерала 5 и 5.
     */
    private final String s2;
    /**
     * Two string literals 5 and 11111.
     * Два строковых литерала 5 и 11111.
     */
    private final String s3;
    /**
     * Two string literals 11111 and 11111.
     * Два строковых литерала 11111 и 11111.
     */
    private final String s4;
    /**
     * A value that should be added to the res List's size after returning from recursion call.
     * Переменная, на значение которой увеличивается величина листа res после возврата из вызова рекурсивного
     * метода exchangeTen().
     */
    private int outer;
    /**
     * A value that should be subtracted from the res List's size after returning from recursion call.
     * Переменная, которая вычитается из кол-ва элементов res, и на каждом шаге вызова рекурсивного метода
     * exchangeTen() увеличивается на 2.
     */
    private int inner;
    /**
     * Переменная для хранения кол-ва единиц.
     */
    private int one;
    /**
     * Переменная для хранения кол-ва пятерок.
     */
    private int five;
    /**
     * Переменная для хранения величины, кратной десяти.
     */
    private int ten;


    /**
     * The constructor.
     */
    public Machine() {
        s1 = String.format("%6d", 10);
        s2 = String.format("%6d%6d", 5, 5);
        s3 = String.format("%6d%6d", 5, 11111);
        s4 = String.format("%6d%6d", 11111, 11111);
        outer = 5;
        inner = 3;
    }

    /**
     * @param value that is multiple to 10.
     * @return list containing the set of ten-value exchanged number.
     */
    private List<String> exchangeTen(int value) {
        List<String> res;
        if (value != 10) {
            res = exchangeTen(value - 10);
        } else {
            res = new ArrayList<>();
            res.add(s1);
            res.add(s2);
            res.add(s3);
            res.add(s4);
            return res;
        }
        List<String> tmp = new ArrayList<>();
        int i = 0;
        int k = res.size() - inner;
        for (; i < res.size(); i++) {
            tmp.add(s1 + res.get(i));
        }
        for (; i < res.size() + outer - 2; i++) {
            tmp.add(s2 + res.get(k++));
        }
        tmp.add(s3 + res.get(res.size() - 1));
        tmp.add(s4 + res.get(res.size() - 1));
        outer += 2;
        inner += 2;
        return tmp;
    }

    /**
     * @return list containing the set of five-value exchanged number.
     */
    private List<String> exchangeFive() {
        List<String> list = new ArrayList<>();
        list.add(String.format("%6d", 5));
        list.add(String.format("%6d", 11111));
        return list;
    }

    /**
     * @return list containing the set of one-value exchanged number.
     */
    private List<String> exchangeOne() {
        List<String> list = new ArrayList<>();
        list.add(String.format("%6d", 1));
        for (int i = 1; i < one; i++) {
            list.set(0, list.get(0) + "1");
        }
        return list;
    }

    /**
     * @return list containing the set of ten-value & five-value exchanged numbers.
     */
    private List<String> exchangeFifteen() {
        List<String> ten = exchangeTen(this.ten);
        List<String> five = exchangeFive();
        List<String> fifteen = new ArrayList<>();
        int i = 0;
        for (; i < ten.size(); i++) {
            fifteen.add(String.format("%s%s", five.get(0), ten.get(i)));
        }
        fifteen.add(String.format("%s%s", ten.get(0), five.get(1)));
        fifteen.add(String.format("%s%s", five.get(1), ten.get(ten.size() - 1)));
        return  fifteen;
    }

    /**
     * Counts the amount of ones, fives and tens in the passed parameter.
     * Подсчитывает количество единиц, пятерок и десяток во входном параметре.
     * @param value that must be split.
     */
    public void decompose(int value) {
        while (value % 10 != 0) {
            if (value % 5 == 0) {
                value = value - 5;
                five++;
            } else {
                while (value % 5 != 0) {
                    value = value - 1;
                    one++;
                }
            }
        }
        ten = value;
        System.out.println(String.format("Кол-во ед.: %d, кол-во пят.: %d, кол-во дес.: %d", one, five, ten));
    }

    /**
     * @return list of string elements
     */
    public List<String> exchangeAll() {
        if (ten == 0) {
            if (five == 0) {
                //Вернуть только единицы
                return exchangeOne();
            } else {
                if (one == 0) {
                    //Вернуть только пять
                    return exchangeFive();
                } else {
                    //Вернуть пять и единицы
                    List<String> forFive = exchangeFive();
                    List<String> forOne = exchangeOne();
                    for (int i = 0; i < forFive.size(); i++) {
                        forFive.set(i, String.format("%s%s", forFive.get(i), forOne.get(0)));
                    }
                    return forFive;
                }
            }
        } else {
            if (five == 0) {
                if (one == 0) {
                    //Вернуть только десять
                    return exchangeTen(ten);
                } else {
                    //Вернуть десять и единицы
                    List<String> forTen = exchangeTen(ten);
                    List<String> forOne = exchangeOne();
                    for (int i = 0; i < forTen.size(); i++) {
                        forTen.set(i, String.format("%s%s", forTen.get(i), forOne.get(0)));
                    }
                    return forTen;
                }
            } else {
                if (one == 0) {
                    //Вернуть десять и пять
                    return exchangeFifteen();
                } else {
                    //Вернуть десять, пять и единицы
                    List<String> forFifteen = exchangeFifteen();
                    List<String> forOne = exchangeOne();
                    for (int i = 0; i < forFifteen.size(); i++) {
                        forFifteen.set(i, String.format("%s%s", forFifteen.get(i), forOne.get(0)));
                    }
                    return forFifteen;
                }
            }
        }
    }

    /**
     * @param args string array.
     */
    public static void main(String[] args) {
        Machine m = new Machine();
        m.decompose(5);
        for (String s : m.exchangeAll()) {
            System.out.println(s);
        }
    }
}