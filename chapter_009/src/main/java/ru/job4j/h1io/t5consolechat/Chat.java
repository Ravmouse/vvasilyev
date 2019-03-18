package ru.job4j.h1io.t5consolechat;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ru.job4j.utils.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Vitaly Vasilyev, date: 11.03.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Chat {
    /**
     * Список строк из текстового файла.
     */
    private final List<String> compAnswers = new ArrayList<>();
    /**
     * Логгер.
     */
    private static final Logger LOGGER = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     * @throws IOException искл.
     */
    public Chat() throws IOException{
        PropertyConfigurator.configure(Utils.getResourcePath("ru/job4j/h1io/t5consolechat/log4j.properties"));
        Path path = Paths.get(Utils.getResourcePath("ru/job4j/h1io/t5consolechat/answers.txt"));
        Files.lines(path).forEach(compAnswers::add);
    }

    /**
     * @throws IOException искл.
     */
    public void start() throws IOException {
        System.out.println("*** Консольный чат! ***");
        LOGGER.info("*** Консольный чат! ***");
        int index;
        Random rnd = new Random();
        boolean enabled = true;
        String userAnswer = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (!userAnswer.toLowerCase().equals("закончить")) {
                if (enabled) {
                    index = rnd.nextInt(compAnswers.size());
                    System.out.println(String.format("AI: %s", compAnswers.get(index)));
                    LOGGER.info(String.format("AI: %s", compAnswers.get(index)));
                }
                userAnswer = br.readLine();
                LOGGER.info(String.format("User: %s", userAnswer));
                if (userAnswer.equals("стоп")) {
                    enabled = false;
                } else if (userAnswer.equals("продолжить")) {
                    enabled = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Chat().start();
    }
}