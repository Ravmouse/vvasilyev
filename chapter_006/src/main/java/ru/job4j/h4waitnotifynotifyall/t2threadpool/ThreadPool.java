package ru.job4j.h4waitnotifynotifyall.t2threadpool;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Rav, date: 07.04.2018
 * @version 1.0
 */
public class ThreadPool {
    /**
     * Лист потоков.
     */
    private final List<Thread> threadList;
    /**
     * Блокирующая очередь с работами.
     */
    private SBQ<Work> workQueue;

    /**
     * Конструктор.
     */
    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        threadList = new ArrayList<>();
        workQueue = new SBQ<>(size * 3);
        fillThreadList(size);
    }

    /**
     * @param size - кол-во потоков, которое нужно создать.
     */
    private void fillThreadList(int size) {
        for (int i = 0; i < size; i++) {
            threadList.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            workQueue.poll().begin();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }));
        }
    }

    /**
     * @param work - работа для добавления в очередь.
     */
    public void add(Work work) {
        if (work != null) {
            try {
                this.workQueue.offer(work);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Работы добавляются разом в цикле.
     */
    public void addWorkToQueue() {
        for (int i = 0; i < workQueue.getSize(); i++) {
            add(new Work(i));
        }
    }

    /**
     * Запустить на выполнение все потоки.
     */
    public void execute() {
        for (Thread t : threadList) {
            t.start();
        }
    }
}