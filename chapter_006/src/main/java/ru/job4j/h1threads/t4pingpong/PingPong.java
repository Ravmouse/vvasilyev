package ru.job4j.h1threads.t4pingpong;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Создается форма JavaFX приложения с черным квадратиком.
 */
public class PingPong extends Application {
    /**
     * Название формы.
     */
    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    /**
     * @param stage - контейнер приложения.
     * @throws Exception в случае возникновения исключения.
     */
    @Override
    public void start(Stage stage) throws Exception {
        int limitX = 500;
        int limitY = 500;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rect);
        Scene scene = new Scene(group, limitX, limitY);
        stage.setScene(scene);
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
        RectangleMove rectMove = new RectangleMove(rect, scene);
        new Thread(rectMove).start();
        stage.setOnCloseRequest(event -> rectMove.setStop(true));
    }
}