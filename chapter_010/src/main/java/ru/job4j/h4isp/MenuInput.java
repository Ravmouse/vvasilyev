package ru.job4j.h4isp;

import ru.job4j.h1srp.input.ConsoleInput;

/**
 * @author Vitaly Vasilyev, date: 14.06.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class MenuInput extends ConsoleInput {
    /**
     * @param in строка с предложением.
     * @return результат вызова родительского метода ConsoleInput.
     */
    @Override
    public String askS(String in) {
        return super.askS(in);
    }

    /**
     * @param in строка с предложением.
     * @return результат вызова родительского метода ConsoleInput.
     */
    @Override
    public int askI(String in) {
        return super.askI(in);
    }
}