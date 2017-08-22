package ru.job4j.tracker;

/**
 * Class StartUI.
 */
public class StartUI {
    /**
     * The reference to the Tracker object with the array of Item elements.
     */
    private Tracker tracker = new Tracker();
    /**
     * The reference to the Input interface.
     */
    private Input input;
    /**
     * The ADD const.
     */
    private static final String ADD = "0";
    /**
     * The UPDATE const.
     */
    private static final String UPDATE = "1";
    /**
     * The DELETE const.
     */
    private static final String DELETE = "2";
    /**
     * The FIND_ALL const.
     */
    private static final String FIND_ALL = "3";
    /**
     * The FIND_NAME const.
     */
    private static final String FIND_NAME = "4";
    /**
     * The FIND_ID const.
     */
    private static final String FIND_ID = "5";
    /**
     * The EXIT const.
     */
    private static final String EXIT = "6";


    /**
     * The constructor.
     * @param input The reference to Input object.
     */
    public StartUI(Input input) {
        this.input = input;
    }

    /**
     * Initializes input and tracker fields.
     * @param input of Input type.
     * @param tracker of Tracker type.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * The entry point of the program.
     * @param args The array of String variables.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput()).init();
    }

    /**
     * The method creates the UI and displays it on a screen.
     */
    public void init() {
        String answer;
        for (;;) {
            do {
                System.out.println("\n*** Tracker Program ***\n");
                System.out.println("0. Add new Item (Добавить новую заявку);");
                System.out.println("1. Update Item (Заменить заявку);");
                System.out.println("2. Delete Item (Удалить заявку);");
                System.out.println("3. Find All Items (Найти все заявки);");
                System.out.println("4. Find Item by name (Найти заявку по имени пользователя);");
                System.out.println("5. Find Item by id (Найти заявку по номеру);");
                System.out.println("6. Exit (Выход);\n");

                answer = input.ask("Please, choose an operation number: ");

            } while (Integer.parseInt(answer) < 0 || Integer.parseInt(answer) > 6);

            if (EXIT.equals(answer)) {
                break;
            }
            switch (answer) {
                case ADD :
                    addItem();
                    break;
                case UPDATE :
                    updateItem();
                    break;
                case DELETE :
                    deleteItem();
                    break;
                case FIND_ALL :
                    showAllItems();
                    break;
                case FIND_NAME :
                    findItemByName();
                    break;
                case FIND_ID :
                    findItemById();
                default:
            }
        }
    }

    /**
     * The method calls the Tracker class's method add().
     * @return The reference to Item object.
     */
    public Item addItem() {
        String[] data = new String[2];
        System.out.println("\n--- Adding a new Item: ---");
        data[0] = input.ask("Please, enter the name of the user: ");
        data[1] = input.ask("Please, enter the description of the Item: ");
        System.out.println("--- The Item has been created. ---\n");
        return tracker.add(new Item(data[0], data[1], System.currentTimeMillis()));
    }

    /**
     * The method calls the Tracker class's method update().
     */
    public void updateItem() {
        String[] data = new String[3];
        System.out.println("\n--- Updating the Item: ---");
        data[0] = input.ask("Please, enter the id of the Item that should be replaced: ");
        System.out.println("\nNow you need to input two parameters for a new Item.");
        data[1] = input.ask("Please, enter the name of the user: ");
        data[2] = input.ask("Please, enter the description of the Item: ");
        tracker.update(new Item(data[0], data[1], data[2], System.currentTimeMillis()));
        System.out.println("--- The Item has been updated. ---\n");
    }

    /**
     * The method calls the Tracker class's method delete().
     */
    public void deleteItem() {
        System.out.println("\n--- Deleting the Item: ---");
        String id = input.ask("Please, enter the id of the Item that should be deleted: ");
        Item item = tracker.findById(id);
        tracker.delete(item);
        System.out.println("--- The Item has been deleted. ---\n");
    }

    /**
     * The method calls the Tracker class's method findAll() and displays the results on a screen.
     */
    public void showAllItems() {
        System.out.println("\n--- All Items in the Tracker: ---");
        for (Item item : tracker.findAll()) {
            System.out.println(item);
        }
        System.out.println();
    }

    /**
     * The method calls the Tracker class's method findByName() and displays the results on a screen.
     */
    public void findItemByName() {
        System.out.println("\n--- Finding the Item(-s) by name: ---");
        String name = input.ask("Please, enter the name of the User: ");
        Item[] items = tracker.findByName(name);
        System.out.println("\nThe following Item(-s) has(-ve) been found: ");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println();
    }

    /**
     * The method calls the Tracker class's method findById() and displays the results on a screen.
     */
    public void findItemById() {
        System.out.println("\n--- Finding the Item by id: ---");
        String id = input.ask("Please, enter the id of the Item: ");
        Item item = tracker.findById(id);
        System.out.println("\nThe following Item has been found: ");
        System.out.println(item);
        System.out.println();
    }
}