package main.java;

/**
 * Container for Project entity data, getter and setter methods
 *
 * @author Yaraslau Babich
 * @version 1.0
 */
public class Project {
    private int id;
    private String title;
    private String shortTitle;
    private String description;

    /**
     * Whether this {@link Project} violates constraints in database or not.
     */
    private boolean isRemovable;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsRemovable() {
        return isRemovable;
    }

    public void setIsRemovable(boolean isRemovable) {
        this.isRemovable = isRemovable;
    }
}
