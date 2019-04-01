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
    private final List<String> answers = new ArrayList<>();
    /**
     * Логгер.
     */
    private static final Logger LOG = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     * @throws IOException искл.
     */
    public Chat() throws IOException {
        PropertyConfigurator.configure(Utils.getResourcePath("ru/job4j/h1io/t5consolechat/log4j.properties"));
        Path path = Paths.get(Utils.getResourcePath("ru/job4j/h1io/t5consolechat/answers.txt"));
        Files.lines(path).forEach(answers::add);
    }

    /**
     * @throws IOException искл.
     */
    public void start() throws IOException {
        System.out.println("*** Консольный чат! ***");
        LOG.info("*** Консольный чат! ***");
        int index;
        final Random rnd = new Random();
        boolean enabled = true;
        String input = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (!"закончить".equals(input.toLowerCase())) {
                if (enabled) {
                    index = rnd.nextInt(answers.size());
                    System.out.println(String.format("AI: %s", answers.get(index)));
                    LOG.info(String.format("AI: %s", answers.get(index)));
                }
                input = br.readLine();
                LOG.info(String.format("User: %s", input));
                if (input.equals("стоп")) {
                    enabled = false;
                } else if (input.equals("продолжить")) {
                    enabled = true;
                }
            }
        }
    }

    /**
     * @param args аргументы.
     * @throws IOException искл.
     */
    public static void main(String[] args) throws IOException {
        new Chat().start();
    }
}