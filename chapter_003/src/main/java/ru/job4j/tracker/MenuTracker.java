package ru.job4j.tracker;
import java.util.ArrayList;
import java.util.List;

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
     * The ArrayList of UserActions.
     */
    private List<UserAction> actions = new ArrayList<>();


    /**
     * The constructor.
     * @param input object.
     * @param tracker object.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Creates instances of each inner class and adds them to the List of UserAction.
     */
    public void fillActions() {
        this.actions.add(new AddItem());
        this.actions.add(new EditItem());
        this.actions.add(new DeleteItem());
        this.actions.add(new FindAllItems());
        this.actions.add(new FindByName());
        this.actions.add(new FindById());
        this.actions.add(new ExitProgram());
    }

    /**
     * Calls the execute() method of any UserAction interface object according to the key parameter.
     * @param key to choose UserAction.
     */
    public void select(int key) {
        this.actions.get(key).execute(input, tracker);
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
     * The actions getter.
     * @return The List of UserAction.
     */
    public List<UserAction> getActions() {
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
            System.out.println("\n-- Adding a new Item: --");
            String name = input.ask("Please, enter the name of the user: ");
            String desc = input.ask("Please, enter the description of the Item: ");
            tracker.add(new Item(name, desc, System.currentTimeMillis()));
            System.out.println("-- The Item has been added. --\n");
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
         * Calls the Tracker class method replace().
         * @param input input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("\n-- Editing the Item: --");
            String id = input.ask("Please, enter the id of the Item that should be edited: ");
            Item foundItem = null;
            String answer;
            for (Item item : tracker.getItems()) {
                if (id != null && id.equals(item.getId())) {
                    foundItem = item;
                    break;
                }
            }
            System.out.println("\nWhat would you like to edit?");
            String question = input.ask("1 - The name of the Item;\n2 - The description of the Item;\n" +
                                        "3 - The comment of the Item;\n4 - All the parameters;\n");
            switch (question) {
                case "1": answer = input.ask("Please, enter a new name of the Item: ");
                          foundItem.setName(answer);
                          tracker.replace(foundItem);
                          break;
                case "2": answer = input.ask("Please, enter a new description of the Item: ");
                          foundItem.setDescription(answer);
                          tracker.replace(foundItem);
                          break;
                case "3": answer = input.ask("Please, enter a new comment of the Item: ");
                          foundItem.addComment(answer);
                          tracker.replace(foundItem);
                          break;
                case "4": String name = input.ask("Please, enter a new name of the Item: ");
                          String desc = input.ask("Please, enter a new description of the Item: ");
                          String comm = input.ask("Please, enter a new comment of the Item: ");
                          tracker.replace(new Item(id, name, desc, System.currentTimeMillis(), comm));
            }
            System.out.println("-- The Item has been edited. --\n");
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
            System.out.println("\n-- Deleting the Item: --");
            String id = input.ask("Please, enter the id of the Item that should be deleted: ");
            Item tmpItem = tracker.findById(id);
            tracker.delete(tmpItem);
            System.out.println("-- The Item has been deleted. --\n");
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
            System.out.println("\n-- All Items in the Tracker: --");
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
            System.out.println("\n-- Finding the Item(-s) by name: --");
            String name = input.ask("Please, enter the name of the User: ");
            List<Item> items = tracker.findByName(name);
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
            System.out.println("\n-- Finding the Item by id: --");
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
            System.out.println("\n-- Exiting program --");
            return;
        }
    }
}