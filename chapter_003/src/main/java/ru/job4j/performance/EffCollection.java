package ru.job4j.performance;

import java.util.Collection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Random;

/**
 * There is adding & deleting strings in objects implementing Collection interface.
 * В классе происходит добавление строк в коллекции и удаление из них.
 */
public class EffCollection {

    /**
     * An object's instance of Random class.
     */
    private final Random rnd = new Random();

    /**
     * Creates string literals containing a random set of symbols.
     * Генерирует строковые литералы, состоящие из произвольного набора символов.
     * @return a string object.
     */
    public String generateString() {
        final int wordLen = rnd.nextInt(10) + 10; //Сгенерировать длину слова
        String str = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        char ch;
        int tmp;
        for (int i = 0; i < wordLen; i++) {
            tmp = rnd.nextInt(str.length()); //Сгенерировать число от 0 до 26
            ch = str.charAt(tmp);
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * Adds string objects into the collection.
     * Добавляет в коллекцию строковые литералы.
     * @param collection to add string literals into.
     * @param amount - how many string literals must be generated to add.
     * @return the size of the collection.
     */
    public long add(Collection<String> collection, int amount) {
        for (int i = 0; i < amount; i++) {
            collection.add(generateString());
        }
        return collection.size();
    }

    /**
     * Deletes first n elements from the collection.
     * Удаляет из коллекции первые n элементов.
     * @param collection to delete string literals from.
     * @param amount - how many string literals must be deleted.
     * @return the size of the collection.
     */
    public long delete(Collection<String> collection, int amount) {
        final Iterator<String> iterator = collection.iterator();
        for (int i = 0; i < amount; i++) {
            iterator.next();
            iterator.remove();
        }
        return collection.size();
    }

    /**
     * main method.
     * @param args array of strings.
     */
    public static void main(String[] args) {
//        System.out.println(new EffCollection().generateString());
        EffCollection ef = new EffCollection();
        long startTime;
        ArrayList<String> arrayList = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();
        TreeSet<String> treeSet = new TreeSet<>();

        startTime = System.nanoTime();
        ef.add(arrayList, 1000000);
        System.out.println(String.format("AL add = %,d", System.nanoTime() - startTime));
        startTime = System.nanoTime();
        ef.delete(arrayList, 10000);
        System.out.println(String.format("AL delete = %,d", System.nanoTime() - startTime));
        System.out.println();


        startTime = System.nanoTime();
        ef.add(linkedList, 1000000);
        System.out.println(String.format("LL add = %,d", System.nanoTime() - startTime));
        startTime = System.nanoTime();
        ef.delete(linkedList, 10000);
        System.out.println(String.format("LL delete = %,d", System.nanoTime() - startTime));
        System.out.println();


        startTime = System.nanoTime();
        ef.add(treeSet, 1000000);
        System.out.println(String.format("TS add = %,d", System.nanoTime() - startTime));
        startTime = System.nanoTime();
        ef.delete(treeSet, 10000);
        System.out.println(String.format("TS delete = %,d", System.nanoTime() - startTime));
    }
}