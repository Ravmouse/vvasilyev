package ru.job4j.h6testtask.t3switcher;
/**
 * Класс Switcher.
 */
public class Switcher {
    /**
     * Строка, куда добавляются новые строки в виде наборов чисел.
     */
    private final StringBuilder string = new StringBuilder("");
    /**
     * Переменная для контроля работы потоков в определенном порядке.
     */
    private int number = 1;

    /**
     * @param value значение для добавления в строку в количестве 10 раз.
     * @throws InterruptedException исключение.
     */
    private synchronized void addValue(int value) throws InterruptedException {
        while (value != number) {
            this.wait();
        }
        for (int i = 0; i < 10; i++) {
            this.string.append(String.valueOf(value));
        }
        this.string.append("\n");
        number = number == 1 ? 2 : 1;
        this.notifyAll();
    }

    /**
     * @param args .
     * @throws InterruptedException .
     */
    public static void main(String[] args) throws InterruptedException {
        Switcher switcher = new Switcher();
        Thread t1 = new Thread(() -> {
            int i = 0;
            while (i < 10) {
                try {
                    switcher.addValue(1);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            int i = 0;
            while (i < 10) {
                try {
                    switcher.addValue(2);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(switcher.string);
    }
}