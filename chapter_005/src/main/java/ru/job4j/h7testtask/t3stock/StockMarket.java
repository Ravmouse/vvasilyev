package ru.job4j.h7testtask.t3stock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Here the HashMap is created for storing Issuers associated with Integer numbers. And also there are inner classes
 * each of them has the method called "execute" according to the user's choice.
 */
public class StockMarket {
    /**
     * Each Integer number is associated with the Issuer that could add or delete the Orders.
     */
    private final HashMap<Integer, TradeIssuer> market = new HashMap<>();
    /**
     * The ref. to the SimpleInput interface type.
     */
    private SimpleInput input;
    /**
     * It's the List of objects each of them has the method with the same name.
     */
    private final List<UserAction> actions = new ArrayList<>();

    /**
     * @param input that is passed from the UserInterface class.
     */
    public StockMarket(SimpleInput input) {
        this.input = input;
    }

    /**
     * Fills the HashMap with Integers and Issuers, and also adds into the List three objects.
     * Кладет в HashMap (market) ключ и значение. Ключ - это Integer, значение - это создание объекта типа Issuer.
     * Также создает и заполняет List (actions) объектами типа UserAction.
     */
    public void createAndFill() {
        int k = -1;
        for (String s : IssuerNames.list) {
            market.put(++k, new TradeIssuer(s)); //Filling the map with Integers and instances of Issuer.
        }
        actions.add(0, new StockMarket.FormOrder()); //Filling the ArrayList with instance of FormOrder class.
        actions.add(1, new StockMarket.ShowStockMarket()); //Filling the ArrayList with instance of ShowStockMarket class.
        actions.add(2, new StockMarket.ExitProgram()); //Filling the ArrayList with instance of ExitProgram class.

    }

    /**
     * @param key is the value chosen by the user.
     * В соответствии со значением key получает объект типа UserAction и выполняет его метод execute().
     */
    public void select(int key) {
        this.actions.get(key).execute(input, market);
    }

    /**
     * @param input that is passed from the UserInterface class.
     * @return the user's choice.
     * Печатает каждую строку из List (list) класса IssuerNames и ждет ответа пользователя.
     */
    private static int chooseIssuer(SimpleInput input) {
        int i = -1;
        System.out.println("Please, choose the issuer from the list below:");
        for (String s : IssuerNames.list) {
            System.out.println(++i + ". " + s + ";");
        }
        return input.ask();
    }

    /**
     * The inner static class that contains the List of Issuer's names.
     */
    private static final class IssuerNames {
        /**
         * The List of Issuer's names.
         */
        private static List<String> list = Arrays.asList("GazProm", "Norilsk Nickel", "SberBank");
    }

    /**
     * The inner static class.
     */
    private static final class FormOrder extends UserAction {
        /**
         * @param input input.
         * @param market is the HashMap.
         */
        @Override
        public void execute(SimpleInput input, HashMap<Integer, TradeIssuer> market) {
            int type, iNo, action, volume;
            double price;
            System.out.println("\n--- Forming the order: ---");
            type = input.ask("Please, enter the type of the order (0 - Add; 1 - Delete): ");
            iNo = chooseIssuer(input);
            action = input.ask("Please, enter an action of the order (0 - Ask, 1 - Bid): ");
            price = input.askD("Please, enter a price: ");
            if (type == 1) {
                market.get(iNo).delete(action, price);
            } else {
                volume = input.ask("Please, enter a volume: ");
                market.get(iNo).add(action, price, volume);
            }
            System.out.println("--- The order has been formed. ---\n");
        }
    }

    /**
     * The inner static class.
     */
    private static final class ShowStockMarket extends UserAction {
        /**
         * @param input input.
         * @param market is the HashMap.
         */
        @Override
        public void execute(SimpleInput input, HashMap<Integer, TradeIssuer> market) {
            Set<Map.Entry<Integer, TradeIssuer>> set = market.entrySet();
            for (Map.Entry<Integer, TradeIssuer> me : set) {
                me.getValue().print();
            }
        }
    }

    /**
     * The inner static class.
     */
    private static final class ExitProgram extends UserAction {
        /**
         * @param input input.
         * @param market is the HashMap.
         */
        @Override
        public void execute(SimpleInput input, HashMap<Integer, TradeIssuer> market) {
            System.out.println("--- Exiting program ---");
        }
    }
}