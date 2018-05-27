package ru.job4j.h6testtask.t1bomberman;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс Base.
 */
public class Base {
    /**
     * Массив локов.
     */
    private final ReentrantLock[][] board;
    /**
     * Ссылка на класс Рандом.
     */
    private final Random rnd = new Random();
    /**
     * Рзмер поля.
     */
    private final int size;

    /**
     * @param size размер поля.
     */
    public Base(int size) {
        this.board = new ReentrantLock[size][size];
        this.size = size;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

    /**
     * @return массив со значениями для создания экземпляра класса Hero.
     */
    private int[] createFirstPosition() {
        int[] result = new int[2];
        result[0] = rnd.nextInt(this.size);
        result[1] = rnd.nextInt(this.size);
        return result;
    }

    /**
     * Рисует положения героев на поле.
     * @param h1 ссылка №1 на экземпляр класса Hero.
     * @param h2 ссылка №2 на экземпляр класса Hero.
     */
    private void draw(Hero h1, Hero h2) {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if ((i == h1.getX()) && (j == h1.getY())) {
                    System.out.print(" 1 ");
                } else if ((i == h2.getX()) && (j == h2.getY())) {
                    System.out.print(" 2 ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * @param h ссылка на экземпляр класса Hero.
     * @throws InterruptedException в случае возникновения исключения.
     */
    private void startMovement(Hero h) throws InterruptedException {
        int[] pAr;
        board[h.getX()][h.getY()].lock(); //Сделать лок позиции при создании героя (экземпляра класса Hero).
        int xPos;
        int yPos;
        while (true) {
            pAr = nextPosition(h); //Получить координаты, на которые можно "пойти".
            if (board[pAr[0]][pAr[1]].tryLock(500, TimeUnit.MILLISECONDS)) { //Проверка,а нет ли там лока?Получить лок.
                xPos = h.getX(); //Присвоить положение по оси X там, где расположен герой и где еще есть лок.
                yPos = h.getY(); //Присвоить положение по оси Y там, где расположен герой и где еще есть лок.
                try {
                    h.setX(pAr[0]); //Передвинуть героя на новую позицию.
                    h.setY(pAr[1]);
                } finally {
                    board[xPos][yPos].unlock(); //Освобождение лока там, где раньше стоял герой.
                }
                Thread.sleep(1000);
            }
        }
    }

    /**
     * У ссылки на героя получаются значения X и Y. Рандомно получается 0 (для изменения по оси X)
     * или 1 (для измненеия по оси Y). Запускается метод changeArray().
     * @param h ссылка на экземпляр объекта класса Hero.
     * @return массив со значениями.
     */
    private int[] nextPosition(Hero h) {
        int[] rsl = new int[2];
        rsl[0] = h.getX();
        rsl[1] = h.getY();
        if (rnd.nextInt(2) == 0) {
            changeArray(rsl, 0);
        } else {
            changeArray(rsl, 1);
        }
        return rsl;
    }

    /**
     * В зависимости от индекса меняется значение одного из элементов массива.
     * @param ar ссылка на массив.
     * @param i индекс массива.
     */
    private void changeArray(int[] ar, int i) {
        if (System.currentTimeMillis() % 2 == 0) {
            if (ar[i] == size - 1) {
                ar[i]--;
            } else {
                ar[i]++; //+1
            }
        } else {
            if (ar[i] == 0) {
                ar[i]++;
            } else {
                ar[i]--; //-1
            }
        }
    }

    /**
     * @param args args.
     * @throws InterruptedException .
     */
    public static void main(String[] args) throws InterruptedException {
        Base base = new Base(3);
        int[] posOne = base.createFirstPosition();
        int[] posTwo = base.createFirstPosition();
        while (ArraysCompare.compareTwoArrays(posOne, posTwo)) {
            posTwo = base.createFirstPosition();
        }
        Hero h1 = new Hero(posOne, "hero#1");
        Hero h2 = new Hero(posTwo, "hero#2");

        System.out.println(h1);
        System.out.println(h2);
        base.draw(h1, h2);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    base.startMovement(h1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    base.startMovement(h2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        while (true) {
            base.draw(h1, h2);
            Thread.sleep(1000);
        }
    }
}