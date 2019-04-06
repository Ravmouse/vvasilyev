package ru.job4j.h1io.t7analysis;

import ru.job4j.utils.Utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Vitaly Vasilyev, date: 04.04.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Analysis {
    /**
     * Сигнализирует, когда начался период и когда закончился.
     */
    private boolean period;
    /**
     * Для формирования строки.
     */
    private StringBuilder sb = new StringBuilder("");

    /**
     * @param source источник, откуда считывать.
     * @param target куда записывать.
     * @throws IOException искл.
     */
    public void unavailable(String source, String target) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(Utils.getResourcePath(source)));
             BufferedWriter bw = new BufferedWriter(new FileWriter(target))) {
            br.lines()
                    .filter(s -> !s.isEmpty())
                    .forEach(s -> {
                        if ((s.startsWith("400") || s.startsWith("500")) && !this.period) {
                            sb.append(s.substring(s.indexOf(" ") + 1));
                            this.period = true;
                        } else if ((!s.startsWith("400") && !s.startsWith("500")) && this.period) {
                            sb.append(";").append(s.substring(s.indexOf(" ") + 1)).append(System.lineSeparator());
                            this.period = false;
                        }
                    }
            );
            bw.write(sb.toString());
            bw.flush();
        }
    }

    /**
     * @return строковое представление.
     */
    public String read() {
        return sb.toString();
    }
}