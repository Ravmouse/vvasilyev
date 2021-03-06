package ru.job4j.h1threads.t4pingpong;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Rectangle2D;

/**
 * В классе задается движение черного квадартика.
 */
public class RectangleMove extends Thread {
    /**
     * Черный квадратик.
     */
    private final Rectangle rect;
    /**
     * Сцена.
     */
    private final Scene scene;

    /**
     * @param rect - черный квадратик на форме.
     * @param scene - сцена; нужны ее высота и ширина.
     */
    RectangleMove(Rectangle rect, Scene scene) {
        this.rect = rect;
        this.scene = scene;
    }

    /**
     * Создает лок.переменную Rectangle2D, которая служит границей для движения черного квадратика.
     * Квадратик перемещается в цикле, отталкиваясь от краев окна.
     * При выходе из приложения текущий поток прерывается.
     */
    @Override
    public void run() {
        Rectangle2D rect2D = new Rectangle2D(0, 0, scene.getWidth(), scene.getHeight());
        double dx = 1;
        double dy = 1;
        while (!Thread.currentThread().isInterrupted()) {
            double x = this.rect.getX();
            double y = this.rect.getY();
            double width = x + rect.getWidth();
            double height = y + rect.getHeight();
            if ((width >= rect2D.getWidth()) || (x <= 0)) {
                dx = -dx;
            }
            if ((height >= rect2D.getHeight()) || (y <= 0)) {
                dy = -dy;
            }
            this.rect.setX(x + dx);
            this.rect.setY(y + dy);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}