package ru.job4j.h1io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Vitaly Vasilyev, date: 27.02.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class CheckByteStream {
    /**
     * @param in байтовый поток.
     * @return true, если число в этом потоке четное и false, если - нет.
     * @throws IOException искл.
     */
    public boolean isNumber(InputStream in) throws IOException {
        boolean result = true;
        StringBuilder sb = new StringBuilder();
        int k;
        while ((k = in.read()) != -1) {
            sb.append(k);
        }
        if (Integer.parseInt(sb.toString()) % 2 != 0) {
            result = false;
        }
        return result;
    }
}