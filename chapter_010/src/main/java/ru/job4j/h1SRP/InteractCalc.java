package ru.job4j.h1srp;

import java.io.IOException;
import ru.job4j.h1srp.input.ValidateInput;
import ru.job4j.h2ocp.TrigMenuAction;

/**
 * @author Vitaly Vasilyev, date: 06.04.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class InteractCalc {
    /**
     * Чтобы получить доступ ко всем экз.класса Action.
     */
//    private final MenuAction menu = new MenuAction();
    private final MenuAction menu = new TrigMenuAction();
    /**
     * Доступ к экз.классов пользовательского ввода.
     */
    private ValidateInput input = new ValidateInput(menu);
    /**
     * Счетчик кол-ва экземпляров абстрактного класса Action - количество пунктов в меню.
     */
    private int count;

    /**
     * @throws IOException искл.
     * @throws InterruptedException искл.
     */
    public void show() throws IOException, InterruptedException {
        int ans;
        double one, two, rsl;
        do {
            count = 0;
            Thread.sleep(2000);
            System.out.println("** Calculator **");
            System.out.println("Чтобы воспользоваться результатом пред.вычисления введите 'z' после выбора ариф.операции");
            menu.actions().forEach((key, value) -> {
                value.info();
                count++;
            });
            ans = input.ask("Выберите операцию: ", count);
            if (ans == count) {
                continue;
            }
            if (ans == count - 1) {
                System.out.println("Пред.результат: " + menu.actions().get(ans).getResult() + System.lineSeparator());
                continue;
            }
            if (menu.isTrigonom(ans)) {
                one = input.ask("Аргумент: ");
                two = 0;
            } else {
                one = input.ask("Операнд #1: ");
                two = input.ask("Операнд #2: ");
            }
            rsl = menu.actions().get(ans).operation(one, two);
            System.out.println("Результат: " + rsl + System.lineSeparator());
            menu.actions().get(count - 1).setResult(rsl);
        } while (ans != count);
        input.close();
    }

    /**
     * @param args аргс.
     * @throws IOException искл.
     * @throws InterruptedException искл.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        InteractCalc inter = new InteractCalc();
        inter.show();
    }
}