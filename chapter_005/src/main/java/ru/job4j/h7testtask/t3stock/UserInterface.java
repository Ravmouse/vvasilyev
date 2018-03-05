package ru.job4j.h7testtask.t3stock;

/**
 * The main class for launching the app.
 */
public class UserInterface {
    /**
     * The ref. to StockMarket object.
     */
    private StockMarket stock;
    /**
     * The ref. to the SimpleInput interface type.
     */
    private SimpleInput input;

    /**
     * @param input could be the ConsoleInput or the StubInput.
     */
    public UserInterface(SimpleInput input) {
        stock = new StockMarket(input);
        this.input = input;
    }

    /**
     * Starts showing the user interface and waiting for the user's response.
     */
    public void init() {
        int i;
        stock.createAndFill();
        do {
            System.out.println("\n*** Stock market ***");
            i = input.ask("Please, select the operation:\n0. Form the order;\n1. Show the Stock Market;\n2. Exit.\n");
            stock.select(i);
        } while (i != 2);
    }

    /**
     * @param args args.
     */
    public static void main(String[] args) {
        new UserInterface(new ConsoleInput()).init();
//        new UserInterface(new StubInput(new String[] {"0", "0", "0", "0", "10", "10", "1"
//                                                    , "0", "0", "0", "0", "11", "11", "1"
//                                                    , "0", "0", "0", "1", "12", "10", "1"
//                                                    , "2"})).init();
    }
}