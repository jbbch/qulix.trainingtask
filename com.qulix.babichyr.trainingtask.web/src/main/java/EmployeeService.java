package main.java;

import java.sql.SQLException;
import java.util.List;

/**
 * Provides the methods to interact with Employee entities
 *
 * @author Yaraslau Babich
 * @version 1.0
 */
public class EmployeeService {

    DatabaseService databaseService;

    /**
     * Creates DatabaseService object to invoke methods on.
     */
    public EmployeeService() {
        databaseService = new DatabaseService();
    }

    /**
     * Obtains {@link Employee} objects collection from data source
     * @return Employee object collection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Employee> getEmployeeList() throws ClassNotFoundException, SQLException {
        return databaseService.getEmployeeList();
    }

    /**
     * Obtains {@link Employee} object with data for Employee specified by ID
     *
     * @param id ID of specified Employee
     * @return Employee object
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Employee getEmployee(int id) throws ClassNotFoundException, SQLException {
        return databaseService.getEmployee(id);
    }

    /**
     * Updates specified Employee entity
     *
     * @param employee {@link Employee} object with updated data and ID to update data to
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateEmployee(Employee employee) throws ClassNotFoundException, SQLException {
        databaseService.updateEmployee(employee);

    }

    /**
     * Adds new Employee entity
     *
     * @param employee {@link Employee} object with new data and ID to put data to
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addEmployee(Employee employee) throws ClassNotFoundException, SQLException {
        databaseService.addEmployee(employee);
    }

    /**
     * Deletes specified Employee entity
     *
     * @param id ID of entry to look for and delete
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void deleteEmployee(int id) throws ClassNotFoundException, SQLException {
        databaseService.deleteEmployee(id);
    }

    /**
     * Finds the index of next {@link Employee} entity to be inserted.
     *
     * @return ID of next inserted entity
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getNextId() throws ClassNotFoundException, SQLException {
        return databaseService.getNextIdEmployee();
    }

}
