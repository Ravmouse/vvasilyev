package ru.job4j.tracker;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.action.UserAction;
import ru.job4j.tracker.action.BaseAction;
import ru.job4j.tracker.item.Item;

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
     * The index of the array's element.
     */
    private int position;

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
        this.actions[position++] = new MenuTracker.AddItem();
        this.actions[position++] = new MenuTracker.EditItem();
        this.actions[position++] = new MenuTracker.DeleteItem();
        this.actions[position++] = new MenuTracker.FindAllItems();
        this.actions[position++] = new MenuTracker.FindByName();
        this.actions[position++] = new MenuTracker.FindById();
        this.actions[position++] = new MenuTracker.ExitProgram();
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
     * The getter.
     * @return The ref. to the array of UserAction.
     */
    public UserAction[] getActions() {
        return actions;
    }

    //-------------------------------------------------------------------------------------------------------------

    /**
     * Private static inner class AddItem.
     */
    private static class AddItem extends BaseAction {
        /**
         * The constructor.
         */
        AddItem() {
            super(0, "Add new Item (Добавить новую заявку);");
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
    }

    /**
     * Private static inner class EditItem.
     */
    private static class EditItem extends BaseAction {
        /**
         * The constructor.
         */
        EditItem() {
            super(1, "Edit Item (Редактировать заявку);");
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
    }

    /**
     * Private static inner class DeleteItem.
     */
    private static class DeleteItem extends BaseAction {
        /**
         * The constructor.
         */
        DeleteItem() {
            super(2, "Delete Item (Удалить заявку);");
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
    }

    /**
     * Private static inner class FindAllItems.
     */
    private static class FindAllItems extends BaseAction {
        /**
         * The constructor.
         */
        FindAllItems() {
            super(3, "Find All Items (Найти все заявки);");
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
    }

    /**
     * Private static inner class FindByName.
     */
    private static class FindByName extends BaseAction {
        /**
         * The constructor.
         */
        FindByName() {
            super(4, "Find Item by name (Найти заявку по имени пользователя);");
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
    }

    /**
     * Private static inner class FindById.
     */
    private static class FindById extends BaseAction {
        /**
         * The constructor.
         */
        FindById() {
            super(5, "Find Item by id (Найти заявку по номеру);");
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
    }

    /**
     * Private static inner class ExitProgram.
     */
    private static class ExitProgram extends BaseAction {
        /**
         * The constructor.
         */
        ExitProgram() {
            super(6, "Exit (Выход);\n");
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
    }
}