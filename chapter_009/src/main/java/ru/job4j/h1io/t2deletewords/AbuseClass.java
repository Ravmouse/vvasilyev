package ru.job4j.h1io.t2deletewords;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * @author Vitaly Vasilyev, date: 27.02.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class AbuseClass {
    /**
     * @param in байтовый поток чтения.
     * @param out байтовый поток записи.
     * @param abuse массив с запрещенными словами.
     * @throws IOException искл.
     */
    public static void dropAbuses(InputStream in, OutputStream out, String[] abuse) throws IOException {
        try (BufferedReader buffIn = new BufferedReader(new InputStreamReader(in));
             BufferedWriter buffOut = new BufferedWriter(new OutputStreamWriter(out))) {
            String line;
            while ((line = buffIn.readLine()) != null) {
                for (String s : abuse) {
                    line = findAndReplace(line, s);
                }
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

    /**
     * @param sentence строка, которую нужно проверить на наличие запрещенного слова.
     * @param word запрещенное слово.
     * @return проверенную строку.
     */
    public static String findAndReplace(String sentence, String word) {
        if (sentence.contains(word)) {
            sentence = sentence.replaceAll(word, "");
            sentence = sentence.trim();
            if (sentence.contains("  ")) {
                sentence = sentence.replace("  ", " ");
            }
        }
        return sentence;
    }
}