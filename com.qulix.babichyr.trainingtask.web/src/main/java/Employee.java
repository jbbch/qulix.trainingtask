package main.java;

/**
 * Container for Employee entity data, getter and setter methods
 *
 * @author Yaraslau Babich
 * @version 1.0
 */
public class Employee {
    private int id;
    private String firstname;
    private String lastname;
    private String patronymic;
    private String position;

    /**
     * Whether this {@link Employee} violates constraints in database or not.
     */
    private boolean isRemovable;

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPosition() {
        return position;
    }

    public boolean getIsRemovable() {
        return isRemovable;
    }

    public void setIsRemovable(boolean isRemovable) {
        this.isRemovable = isRemovable;
    }
}
