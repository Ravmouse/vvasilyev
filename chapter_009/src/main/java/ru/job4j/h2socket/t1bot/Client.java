package ru.job4j.h2socket.t1bot;

import org.apache.log4j.Logger;
import ru.job4j.utils.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Vitaly Vasilyev, date: 20.03.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Client {
    /**
     * Сокет.
     */
    private final Socket socket;
    /**
     * Список предложений для общения с сервером.
     */
    public static final List<String> WORDS = new ArrayList<>();
    /**
     * Логгер.
     */
    private static final Logger LOGGER = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     * @param socket сокет.
     * @throws IOException искл.
     */
    public Client(Socket socket) throws IOException {
        this.socket = socket;
        final Path path = Paths.get(Utils.getResourcePath("ru/job4j/h2socket/t1bot/answers.txt"));
        Files.lines(path).forEach(s -> WORDS.add(s.substring(0, s.indexOf("-"))));
    }

    /**
     * @throws IOException искл.
     * @throws InterruptedException искл.
     */
    public void start() throws IOException, InterruptedException {
        final Random rnd = new Random();
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String str;
            out.println("hello");
            do {
                Thread.sleep(5000);
                while (!(str = in.readLine()).isEmpty()) {
                    LOGGER.info(String.format("Server: %s", str));
                }
                str = WORDS.get(rnd.nextInt(WORDS.size()));
//                str = "exit"; //Для теста метода из класса ClientTest.
                out.println(str);
            } while (!"exit".equals(str));
        }
    }

    /**
     * @param args .
     * @throws IOException искл.
     * @throws InterruptedException искл.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 5050)) {
            new Client(socket).start();
        }
    }
}