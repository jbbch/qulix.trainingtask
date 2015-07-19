package main.java;

import java.sql.SQLException;
import java.util.List;

/**
 * Provides the methods to interact with Task entities
 *
 * @author Yaraslau Babich
 * @version 1.0
 */
public class TaskService extends Task {

    DatabaseService databaseService;

    /**
     * Creates DatabaseService object to invoke methods on.
     */
    public TaskService() {
        databaseService = new DatabaseService();
    }

    /**
     * Obtains {@link Task} objects collection from data source
     * @return Task object collection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Task> getTaskList() throws ClassNotFoundException, SQLException{
        return databaseService.getTaskList();
    }

    /**
     * Obtains {@link Task} object with data for Task specified by ID
     *
     * @param id ID of specified Task
     * @return Task object
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Task getTask(int id) throws ClassNotFoundException, SQLException {
        return databaseService.getTask(id);
    }

    /**
     * Updates specified Task entity
     *
     * @param task {@link Task} object with updated data and ID to update data to
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateTask(Task task) throws ClassNotFoundException, SQLException{
        databaseService.updateTask(task);

    }

    /**
     * Adds new Task entity
     *
     * @param task {@link Task} object with new data and ID to put data to
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addTask(Task task) throws ClassNotFoundException, SQLException{
        databaseService.addTask(task);
    }

    /**
     * Deletes specified Task entity
     *
     * @param id ID of entry to look for and delete
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void deleteTask(int id) throws  ClassNotFoundException, SQLException{
        databaseService.deleteTask(id);
    }

    /**
     * Finds the index of next {@link Task} entity to be inserted.
     *
     * @return ID of next inserted entity
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getNextId() throws ClassNotFoundException, SQLException{
        return databaseService.getNextIdTask();
    }
}
