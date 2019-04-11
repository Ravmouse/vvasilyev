package ru.job4j.h1srp;

import java.io.IOException;
import ru.job4j.h1srp.input.ValidateInput;

/**
 * @author Vitaly Vasilyev, date: 06.04.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class InteractCalc {
    /**
     * Чтобы получить доступ ко всем экз.класса Action.
     */
    private final MenuAction menu = new MenuAction();
    /**
     * Доступ к экз.классов пользовательского ввода.
     */
    private ValidateInput input = new ValidateInput(menu);

    /**
     * @throws IOException искл.
     */
    public void show() throws IOException {
        int ans;
        double one, two, rsl;
        do {
            System.out.println("** Calculator **");
            System.out.println("Чтобы воспользоваться результатом пред.вычисления введите 'z' после выбора ариф.операции"
                    + " во время ввода значений 'Операнд #1' или 'Операнд #2'");
            menu.actions().forEach((key, value) -> value.info());
            ans = input.ask("Выберите операцию: ", 6);
            if (ans == 5) {
                System.out.println("Пред.результат: " + menu.actions().get(ans).getResult() + System.lineSeparator());
            } else if (ans != 6) {
                one = input.ask("Операнд #1: ");
                two = input.ask("Операнд #2: ");
                rsl = menu.actions().get(ans).operation(one, two);
                System.out.println("Результат: " + rsl + System.lineSeparator());
                menu.actions().get(5).setResult(rsl);
            }
        } while (ans != 6);
        input.close();
    }

    /**
     * @param args аргс.
     * @throws IOException искл.
     */
    public static void main(String[] args) throws IOException {
        InteractCalc inter = new InteractCalc();
        inter.show();
    }
}