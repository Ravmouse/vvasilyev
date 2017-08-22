package ru.job4j.tracker;

/**
 * Class MenuTracker.
 */
public class MenuTracker {
    /**
     * Input.
     */
    private Input input;
    /**
     * Tracker.
     */
    private Tracker tracker;
    /**
     * The array of object's references that implement UserAction interface.
     */
    private UserAction[] actions = new UserAction[7];

    /**
     * The constructor.
     * @param input input.
     * @param tracker tracker.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Creates for each array's element the object of the inner class with reference.
     */
    public void fillActions() {
        this.actions[0] = new MenuTracker.AddItem();
        this.actions[1] = new MenuTracker.EditItem();
        this.actions[2] = new MenuTracker.DeleteItem();
        this.actions[3] = new MenuTracker.FindAllItems();
        this.actions[4] = new MenuTracker.FindByName();
        this.actions[5] = new MenuTracker.FindById();
        this.actions[6] = new MenuTracker.ExitProgram();
    }

    /**
     * Calls the execute() method of any UserAction interface object according to the key parameter.
     * @param key key.
     */
    public void select(int key) {
        this.actions[key].execute(input, tracker);
    }

    /**
     * Calls the info() method for all UserAction interface objects.
     */
    public void show() {
        System.out.println("\n*** Tracker Program ***\n");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Private static inner class AddItem.
     */
    private static class AddItem implements UserAction {
        /**
         * Returns 0.
         * @return int.
         */
        public int key() {
            return 0;
        }

        /**
         * Calls the Tracker class method add() with creation a new Item.
         * @param input input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("\n--- Adding a new Item: ---");
            String name = input.ask("Please, enter the name of the user: ");
            String desc = input.ask("Please, enter the description of the Item: ");
            tracker.add(new Item(name, desc, System.currentTimeMillis()));
            System.out.println("--- The Item has been created. ---\n");
        }

        /**
         * Shows a String line with a menu option.
         * @return String.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Add new Item (Добавить новую заявку);");
        }
    }

    /**
     * Private static inner class EditItem.
     */
    private static class EditItem implements UserAction {
        /**
         * Returns 1.
         * @return int.
         */
        public int key() {
            return 1;
        }

        /**
         * Calls the Tracker class method update() with creation a new Item.
         * @param input input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("\n--- Editing the Item: ---");
            String id = input.ask("Please, enter the id of the Item that should be edited: ");
            System.out.println("\nNow you need to input new parameters for the Item.");
            String name = input.ask("Please, enter a new name of the user: ");
            String desc = input.ask("Please, enter a new description of the Item: ");
            tracker.update(new Item(id, name, desc, System.currentTimeMillis()));
            System.out.println("--- The Item has been edited. ---\n");
        }

        /**
         * Shows a String line with a menu option.
         * @return String.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Edit Item (Редактировать заявку);");
        }
    }

    /**
     * Private static inner class DeleteItem.
     */
    private static class DeleteItem implements UserAction {
        /**
         * Returns 2.
         * @return int.
         */
        public int key() {
            return 2;
        }

        /**
         * Calls the Tracker class method delete().
         * @param input input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("\n--- Deleting the Item: ---");
            String id = input.ask("Please, enter the id of the Item that should be deleted: ");
            Item tmpItem = tracker.findById(id);
            tracker.delete(tmpItem);
            System.out.println("--- The Item has been deleted. ---\n");
        }

        /**
         * Shows a String line with a menu option.
         * @return String.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Delete Item (Удалить заявку);");
        }
    }

    /**
     * Private static inner class FindAllItems.
     */
    private static class FindAllItems implements UserAction {
        /**
         * Returns 3.
         * @return int.
         */
        public int key() {
            return 3;
        }

        /**
         * Calls the Tracker class method findAll().
         * @param input input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("\n--- All Items in the Tracker: ---");
            for (Item item : tracker.findAll()) {
                System.out.println(item);
            }
            System.out.println();
        }

        /**
         * Shows a String line with a menu option.
         * @return String.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find All Items (Найти все заявки);");
        }
    }

    /**
     * Private static inner class FindByName.
     */
    private static class FindByName implements UserAction {
        /**
         * Returns 4.
         * @return int.
         */
        public int key() {
            return 4;
        }

        /**
         * Calls the Tracker class method findByName().
         * @param input input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
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
         * Shows a String line with a menu option.
         * @return String.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find Item by name (Найти заявку по имени пользователя);");
        }
    }

    /**
     * Private static inner class FindById.
     */
    private static class FindById implements UserAction {
        /**
         * Returns 5.
         * @return int.
         */
        public int key() {
            return 5;
        }

        /**
         * Calls the Tracker class method findById().
         * @param input input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("\n--- Finding the Item by id: ---");
            String id = input.ask("Please, enter the id of the Item: ");
            Item item = tracker.findById(id);
            System.out.println("\nThe following Item has been found: ");
            System.out.println(item);
            System.out.println();
        }

        /**
         * Shows a String line with a menu option.
         * @return String.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find Item by id (Найти заявку по номеру);");
        }
    }

    /**
     * Private static inner class ExitProgram.
     */
    private static class ExitProgram implements UserAction {
        /**
         * Returns 6.
         * @return int.
         */
        public int key() {
            return 6;
        }

        /**
         * Returns of the method w/out doing anything.
         * @param input input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("\n--- Exiting program ---");
            return;
        }

        /**
         * Shows a String line with a menu option.
         * @return String.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Exit (Выход);\n");
        }
    }
}