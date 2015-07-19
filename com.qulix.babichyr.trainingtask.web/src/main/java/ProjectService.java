package main.java;

import java.sql.SQLException;
import java.util.List;

/**
 * Provides the methods to interact with Project entities
 *
 * @author Yaraslau Babich
 * @version 1.0
 */
public class ProjectService extends Project {

    DatabaseService databaseService;

    /**
     * Creates DatabaseService object to invoke methods on.
     */
    public ProjectService() {
        databaseService = new DatabaseService();
    }

    /**
     * Obtains {@link Project} objects collection from data source
     * @return Project object collection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Project> getProjectList() throws ClassNotFoundException, SQLException {
        return databaseService.getProjectList();
    }

    /**
     * Obtains {@link Project} object with data of specified Project from database by its ID.
     *
     * @param id ID of specified Project
     * @return Project object
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Project getProject(int id) throws ClassNotFoundException, SQLException{
        return databaseService.getProject(id);
    }

    /**
     * Updates specified Project entity
     *
     * @param project {@link Project} object with updated data and ID to update data to
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateProject(Project project) throws ClassNotFoundException, SQLException{
        databaseService.updateProject(project);
    }

    /**
     * Adds new Project entity
     *
     * @param project {@link Project} object with new data and ID to put data to
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addProject(Project project) throws ClassNotFoundException, SQLException{
        databaseService.addProject(project);
    }

    /**
     * Deletes specified Project entity
     *
     * @param id ID of entity to look for and delete
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void deleteProject(int id) throws ClassNotFoundException, SQLException {
        databaseService.deleteProject(id);
    }

    /**
     * Finds the index of next {@link Project} entity to be inserted.
     *
     * @return ID of next inserted entity
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getNextId() throws ClassNotFoundException, SQLException {
        return databaseService.getNextIdProject();
    }
}
