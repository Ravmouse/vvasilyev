package ru.job4j.h2ocp;

import ru.job4j.h1srp.MenuAction;
import ru.job4j.h1srp.action.Exit;
import ru.job4j.h1srp.action.Result;
import ru.job4j.h2ocp.action.Cos;
import ru.job4j.h2ocp.action.Ctg;
import ru.job4j.h2ocp.action.Sin;
import ru.job4j.h2ocp.action.Tg;

/**
 * @author Vitaly Vasilyev, date: 16.04.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class TrigMenuAction extends  MenuAction {
    /**
     * Конструктор.
     */
    public TrigMenuAction() {
        actions.put(5, new Cos(" 5. Косинус", calc));
        actions.put(6, new Sin(" 6. Синус", calc));
        actions.put(7, new Tg(" 7. Тангенс", calc));
        actions.put(8, new Ctg(" 8. Котангенс", calc));
        actions.put(9, new Result(" 9. Пред.результат: "));
        actions.put(10, new Exit("10. Выход"));
    }
}