package ru.job4j.h2socket.t1bot;

import org.apache.log4j.Logger;
import ru.job4j.utils.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vitaly Vasilyev, date: 20.03.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Server {
    /**
     * Сокет.
     */
    private final Socket socket;
    /**
     * Отображение: вопросы - ответы.
     */
    private final Map<String, String> answers = new HashMap<>();
    /**
     * Логгер.
     */
    private static final Logger LOGGER = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     * @param socket сокет.
     * @throws IOException искл.
     */
    public Server(Socket socket) throws IOException {
        this.socket = socket;
        final Path path = Paths.get(Utils.getResourcePath("ru/job4j/h2socket/t1bot/answers.txt"));
        Files.lines(path).forEach(s -> answers.put(s.substring(0, s.indexOf("-")), s.substring(s.indexOf("-") + 1)));
    }

    /**
     * @throws IOException искл.
     */
    public void start() throws IOException {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            String ask;
            do {
                LOGGER.info("Waiting for a command...");
                ask = in.readLine();
                LOGGER.info(String.format("Client: %s", ask));
                out.println(answers.get(ask));
                out.println();
            } while (!"exit".equals(ask));
        }
    }

    /**
     * @param args .
     * @throws IOException искл.
     */
    public static void main(String[] args) throws IOException {
        try (Socket socket = new ServerSocket(5050).accept()) {
            new Server(socket).start();
        }
    }
}