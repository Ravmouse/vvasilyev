package ru.job4j.h6testtask.t2garantdeadlock;

/**
 * Класс DeadLock.
 */
public class DeadLock {
    /**
     * @param args .
     */
    public static void main(String[] args) {
        Person john = new Person("John");
        Person bill = new Person("Bill");

        Thread t1 = new Thread(() -> {
            john.sayHello(bill);
        });
        Thread t2 = new Thread(() -> {
            bill.sayHello(john);
        });
        t1.start();
        t2.start();
    }
}

/**
 * Класс Person.
 */
class Person {
    /**
     * Имя.
     */
    private String name;

    /**
     * @param name имя для инициализации.
     */
    Person(String name) {
        this.name = name;
    }

    /**
     * @return имя текущего экземпляра класса.
     */
    String getName() {
        return this.name;
    }

    /**
     * @param person человек.
     */
    synchronized void sayHello(Person person) {
        System.out.println(this.name + " is saying hello to " + person.getName());
        person.shakeHand(this);
    }

    /**
     * @param person человек.
     */
    synchronized void shakeHand(Person person) {
        System.out.println(this.name + " is saying good buy to " + person.getName());
    }
}