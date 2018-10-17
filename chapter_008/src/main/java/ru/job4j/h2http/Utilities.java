package ru.job4j.h2http;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vitaly Vasilyev, date: 16.10.2018, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Utilities {

    /**
     * @param encodedString ..
     * @return ..
     */
    private static Map<String, String> parseURL(String encodedString) { //action=add&id=1&name=Mike&login=mister&email=bla@mail.ru&createDate=2018-10-15
        Map<String, String> result = new HashMap<>();
        String[] arr = encodedString.split("&");
        System.out.println(Arrays.toString(arr));
        for (String s : arr) {
            result.put(s.substring(0, s.indexOf("=")), s.substring(s.indexOf("=") + 1, s.length()));
        }
        result.remove("action");
        return result;
    }
}