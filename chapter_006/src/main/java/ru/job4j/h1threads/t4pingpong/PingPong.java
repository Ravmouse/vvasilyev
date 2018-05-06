package ru.job4j.h1threads.t4pingpong;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
        rectMove.start();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                rectMove.interrupt();
            }
        });
    }
}