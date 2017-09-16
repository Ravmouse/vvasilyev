package ru.job4j.tracker;
import java.util.ArrayList;
import java.util.List;

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
    private List<String> comments = new ArrayList<>();


    /**
     * The default constructor.
     */
    public Item() {

    }

    /**
     * The constructor.
     * @param name of the Item.
     * @param description of the Item.
     * @param created of the Item.
     */
    public Item(String name, String description, long created) {
        this.name = name;
        this.description = description;
        this.created = created;
    }

    /**
     * The constructor.
     * @param id of the Item.
     * @param name of the Item.
     * @param description of the Item.
     * @param created of the Item.
     */
    public Item(String id, String name, String description, long created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
    }

    /**
     * The constructor.
     * @param id of the Item.
     * @param name of the Item.
     * @param description of the Item.
     * @param created of the Item.
     * @param comment of the Item.
     */
    public Item(String id, String name, String description, long created, String comment) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        comments.add(comment);
    }

    /**
     * The name getter.
     * @return The name of the Item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * The name setter.
     * @param name to replace.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The description getter.
     * @return The description of the Item.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * The description setter.
     * @param description to replace.
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @param id to replace.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Adds comment in the List of Strings.
     * @param comment to add in the List.
     */
    public void addComment(String comment) {
        comments.add(comment);
    }

    /**
     * The comments getter.
     * @return The List of String.
     */
    public List<String> getComments() {
        return comments;
    }

    /**
     * String representation of the Item.
     * @return to display at the console.
     */
    @Override
    public String toString() {
        return String.format("Id: %s, name: %s, description: %s, comments: %s", id, name, description, comments);
    }
}