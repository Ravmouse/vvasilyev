package ru.job4j.h1io.t2deletewords;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.stream.Stream;;

/**
 * @author Vitaly Vasilyev, date: 27.02.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class AbuseClass {
    /**
     * Строка для хранения данных из символьного потока ввода.
     */
    private String line;

    /**
     * @param in байтовый поток чтения.
     * @param out байтовый поток записи.
     * @param abuse массив с запрещенными словами.
     * @throws IOException искл.
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) throws IOException {
        try (BufferedReader buffIn = new BufferedReader(new InputStreamReader(in));
             BufferedWriter buffOut = new BufferedWriter(new OutputStreamWriter(out))) {
            while ((line = buffIn.readLine()) != null) {
                Stream.of(abuse).forEach(s -> {
                    line = line.replace(s, "");
                    line = line.trim();
                    line = line.replace("  ", " ");
                });
                buffOut.write(line);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}