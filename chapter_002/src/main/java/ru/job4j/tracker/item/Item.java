package ru.job4j.tracker.item;

/**
 * Class Item.
 */
public class Item {
    /**
     * The id of the Item.
     */
    private String id;
    /**
     * The name of the Item.
     */
    private String name;
    /**
     * The description of the Item.
     */
    private String description;
    /**
     * The date.
     */
    private long created;
    /**
     * The comments of the Item.
     */
    private String[] comments = new String[10];
    /**
     * The counter of the comments in the Item.
     */
    private int commCount;


    /**
     * The default constructor.
     */
    public Item() {

    }

    /**
     * The constructor.
     * @param name The name value.
     * @param description The description value.
     * @param created The created value.
     */
    public Item(String name, String description, long created) {
        this.name = name;
        this.description = description;
        this.created = created;
    }

    /**
     * The constructor.
     * @param id The id value.
     * @param name The name value.
     * @param description The description value.
     * @param created The created value.
     */
    public Item(String id, String name, String description, long created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
    }

    /**
     * The name getter.
     * @return The name of the Item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * The description getter.
     * @return The description of the Item.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * The created getter.
     * @return The created of the Item.
     */
    public long getCreated() {
        return this.created;
    }

    /**
     * The Id getter.
     * @return The Id of the Item.
     */
    public String getId() {
        return this.id;
    }

    /**
     * The Id setter.
     * @param id The String.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * The method places the String variable into comments array.
     * @param comment The String variable.
     */
    public void addComment(String comment) {
        if (commCount == comments.length) {
            System.out.println(" The array of comments is full!");
            return;
        }
        comments[commCount++] = comment;
    }

    /**
     * The method gets all the comments related to the Item.
     * @return The array of String.
     */
    public String[] getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return String.format("Id: %s, user name: %s, description: %s", id, name, description);
    }
}