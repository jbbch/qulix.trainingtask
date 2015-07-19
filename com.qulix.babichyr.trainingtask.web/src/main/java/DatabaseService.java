package main.java;

import java.lang.Class;
import java.lang.String;
import java.lang.System;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Carries connections to data source and data processing service.
 *
 * @author Yaraslau Babich
 * @version 1.0
 */
public class DatabaseService {


    /**
     * Environmental variable to Catalina home directory.
     * Used to locate Tomcat {@code webapps} directory for database deploying.
     */
    final String CATALINA = System.getenv("CATALINA_HOME");
    final String DB_URL = "jdbc:hsqldb:file:" + CATALINA + "/webapps/trainingtask/database/database";
    final String JDBC_DRIVER = "org.hsqldb.jdbc.JDBCDriver";
    final String USER = "sa";
    final String PASS = "";


    final String SELECT_ALL_EMPLOYEES = "SELECT * FROM Employee";
    final String DELETE_CHECK_EMPLOYEES = "SELECT employeeId, title FROM employee JOIN task ON employee.employeeId=task.employeeId";
    final String SELECT_EMPLOYEE = "SELECT * FROM Employee WHERE employeeId=?";
    final String UPDATE_EMPLOYEE = "UPDATE Employee SET firstname=?, lastname=?, patronymic=?, position=? WHERE employeeId=?";
    final String INSERT_EMPLOYEE = "INSERT INTO Employee VALUES (?,?,?,?,?)";
    final String DELETE_EMPLOYEE = "DELETE FROM Employee WHERE employeeId=?";
    final String GET_NEXT_ID_EMPLOYEE = "SELECT * FROM Employee ORDER BY employeeid DESC";
    final String GET_NEXT_ID_PROJECT = "SELECT * FROM Project ORDER BY projectid DESC";
    final String GET_NEXT_ID_TASK = "SELECT * FROM Task ORDER BY taskid DESC";
    final String SELECT_ALL_PROJECTS = "SELECT * FROM Project";
    final String DELETE_CHECK_PROJECTS = "SELECT projectId, title FROM project JOIN task ON project.projectId=task.projectId";
    final String SELECT_PROJECT = "SELECT * FROM Project WHERE projectId=?";
    final String UPDATE_PROJECT = "UPDATE Project SET title=?, shortTitle=?, description=?  WHERE projectId=?";
    final String INSERT_PROJECT = "INSERT INTO Project VALUES (?,?,?,?)";
    final String DELETE_PROJECT = "DELETE FROM Project WHERE projectId=?";
    final String SELECT_ALL_TASKS = "SELECT * FROM Task";
    final String SELECT_EMPLOYEE_USED_IN_TASK = "SELECT employee.lastname FROM task JOIN employee ON employee.employeeId=task.employeeId";
    final String SELECT_PROJECT_USED_IN_TASK = "SELECT project.shortTitle FROM task JOIN project ON project.projectId=task.projectId";
    final String SELECT_TASK = "SELECT * FROM Task WHERE taskId=?";
    final String UPDATE_TASK = "UPDATE Task SET employeeId=?, projectId=?, title=?, duration=?, startDate=?, endDate=?, status=? WHERE taskId=?";
    final String INSERT_TASK = "INSERT INTO Task values (?,?,?,?,?,?,?,?)";
    final String DELETE_TASK = "DELETE FROM Task WHERE taskId=?";

    Connection connection = null;
    PreparedStatement preparedStatement = null;


    /**
     * This method used to establish connection to data storage.
     *
     * @throws ClassNotFoundException if unable to register JDBC driver
     * @throws SQLException           if unable to establish connection
     */

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER); //register JDBC driver
        connection = DriverManager.getConnection(DB_URL, USER, PASS); //open connection to database
    }


    /**
     * Obtains and instance of {@code PreparedStatement} from query
     *
     * @param query {@code String} contains SQL query to be prepared
     * @return {@code PreparedStatement} object
     * @throws ClassNotFoundException if unable to invoke {@code connect()} method
     * @throws SQLException           if unable to process SQL query
     */
    public PreparedStatement getPreparedStatement(String query) throws ClassNotFoundException, SQLException {
        try {
            connect();
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException | ClassNotFoundException c) {
            c.printStackTrace();
        }
        return preparedStatement;
    }

    /*public void execute() throws Exception {
        try {
            connect();
            preparedStatement.executeQuery();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }*/

    //close all connections

    /*public void close() throws Exception {
        try {
            preparedStatement.close();
            connection.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }*/

    /**
     * Obtains collection of {@link Employee} objects with data from database.
     * Creates new {@link Employee} object, stores Employee data there and puts the object in collection.
     * Checks any constraints in Employee table.
     *
     * @return collection of Employee objects
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Employee> getEmployeeList() throws ClassNotFoundException, SQLException {
        ResultSet rsDeleteCheck = getPreparedStatement(DELETE_CHECK_EMPLOYEES).executeQuery();
        ResultSet rsList = getPreparedStatement(SELECT_ALL_EMPLOYEES).executeQuery();
        List<Employee> employees = new ArrayList<>();
        while (rsList.next()) {
            Employee employee = new Employee();
            employee.setIsRemovable(true);

            while (rsDeleteCheck.next()) {
                if (rsDeleteCheck.getString(ServletController.EMPLOYEE_ID).equals(rsList.getString(ServletController.EMPLOYEE_ID))) {
                    employee.setIsRemovable(false);
                    break;
                }
            }

            employee.setId(rsList.getInt(ServletController.EMPLOYEE_ID));
            employee.setFirstname(rsList.getString(ServletController.NAME));
            employee.setLastname(rsList.getString(ServletController.SURNAME));
            employee.setPatronymic(rsList.getString(ServletController.PATRONYMIC));
            employee.setPosition(rsList.getString(ServletController.POSITION));
            employees.add(employee);
        }
        rsList.close();
        rsDeleteCheck.close();
        return employees;
    }

    /**
     * Obtains {@link Employee} object with data of specified Employee from database by its ID.
     *
     * @param id ID of specified Employee
     * @return Employee object
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Employee getEmployee(int id) throws ClassNotFoundException, SQLException {
        Employee employee = new Employee();
        getPreparedStatement(SELECT_EMPLOYEE);
        preparedStatement.setInt(1, id);
        ResultSet rsEmployee = preparedStatement.executeQuery();
        rsEmployee.next();
        employee.setId(rsEmployee.getInt(ServletController.EMPLOYEE_ID));
        employee.setFirstname(rsEmployee.getString(ServletController.NAME));
        employee.setLastname(rsEmployee.getString(ServletController.SURNAME));
        employee.setPatronymic(rsEmployee.getString(ServletController.PATRONYMIC));
        employee.setPosition(rsEmployee.getString(ServletController.POSITION));
        rsEmployee.close();
        return employee;
    }

    /**
     * Updates specified Employee entry
     *
     * @param employee {@link Employee} object with updated data and ID to update data to
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateEmployee(Employee employee) throws ClassNotFoundException, SQLException {
        getPreparedStatement(UPDATE_EMPLOYEE);
        preparedStatement.setString(1, employee.getFirstname());
        preparedStatement.setString(2, employee.getLastname());
        preparedStatement.setString(3, employee.getPatronymic());
        preparedStatement.setString(4, employee.getPosition());
        preparedStatement.setInt(5, employee.getId());
        preparedStatement.executeUpdate();

    }

    /**
     * Adds new entry to Employee table
     *
     * @param employee {@link Employee} object with new data and ID to put data to
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addEmployee(Employee employee) throws ClassNotFoundException, SQLException {
        getPreparedStatement(INSERT_EMPLOYEE);
        preparedStatement.setInt(1, employee.getId());
        preparedStatement.setString(2, employee.getFirstname());
        preparedStatement.setString(3, employee.getLastname());
        preparedStatement.setString(4, employee.getPatronymic());
        preparedStatement.setString(5, employee.getPosition());
        preparedStatement.executeUpdate();

    }

    /**
     * Deletes specified Employee entry
     *
     * @param id ID of entry to look for and delete
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void deleteEmployee(int id) throws ClassNotFoundException, SQLException {
        getPreparedStatement(DELETE_EMPLOYEE);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

    /**
     * Finds the index of next {@link Employee} entry to be inserted.
     * For task requirement "»дентификатор: пор€дковый номер; формируетс€ автоматически; недоступно дл€ изменени€".
     * <p>
     * Checks if there is any entries already.
     * Returns 0 if none. Returns incremented index if any.
     * </p>
     *
     * @return ID of next inserted entry
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getNextIdEmployee() throws ClassNotFoundException, SQLException {
        int nextIndex;
        getPreparedStatement(GET_NEXT_ID_EMPLOYEE);
        ResultSet rsIndex = preparedStatement.executeQuery();
        if (!rsIndex.next()) {
            nextIndex = 1;
        } else {
            nextIndex = rsIndex.getInt(1) + 1;
        }
        rsIndex.close();
        return nextIndex;
    }

    /**
     * Finds the index of next {@link Project} entry to be inserted.
     * <p>
     * Checks if there is any entries already.
     * Returns 0 if none. Returns incremented index if any.
     * </p>
     *
     * @return ID of next inserted entry
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getNextIdProject() throws ClassNotFoundException, SQLException {
        int nextIndex;
        getPreparedStatement(GET_NEXT_ID_PROJECT);
        ResultSet rsIndex = preparedStatement.executeQuery();
        if (!rsIndex.next()) {
            nextIndex = 1;
        } else {
            nextIndex = rsIndex.getInt(1) + 1;
        }
        rsIndex.close();
        return nextIndex;
    }

    /**
     * Finds the index of next {@link Task} entry to be inserted.
     * <p>
     * Checks if there is any entries already.
     * Returns 0 if none. Returns incremented index if any.
     *
     * @return ID of next inserted entry
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getNextIdTask() throws ClassNotFoundException, SQLException {
        int nextIndex;
        getPreparedStatement(GET_NEXT_ID_TASK);
        ResultSet rsIndex = preparedStatement.executeQuery();
        if (!rsIndex.next()) {
            nextIndex = 1;
        } else {
            nextIndex = rsIndex.getInt(1) + 1;
        }
        rsIndex.close();
        return nextIndex;
    }

    /**
     * Obtains collection of {@link Project} objects with data from database.
     * Creates new {@link Project} object, stores Project data there and puts the object in collection.
     * Checks any constraints in Project table.
     *
     * @return collection of Project objects
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Project> getProjectList() throws ClassNotFoundException, SQLException {
        ResultSet rsList = getPreparedStatement(SELECT_ALL_PROJECTS).executeQuery();
        ResultSet rsDeleteCheck = getPreparedStatement(DELETE_CHECK_PROJECTS).executeQuery();
        List<Project> projects = new ArrayList<>();
        while (rsList.next()) {
            Project project = new Project();
            project.setId(rsList.getInt(ServletController.PROJECT_ID));
            project.setTitle(rsList.getString(ServletController.TITLE));
            project.setShortTitle(rsList.getString(ServletController.SHORT_TITLE));
            project.setDescription(rsList.getString(ServletController.DESCRIPTION));
            project.setIsRemovable(true);
            while (rsDeleteCheck.next()) {
                if (rsDeleteCheck.getString(ServletController.PROJECT_ID).equals(rsList.getString(ServletController.PROJECT_ID))) {
                    project.setIsRemovable(false);
                    break;
                }
            }
            projects.add(project);
        }
        rsList.close();
        rsDeleteCheck.close();
        return projects;
    }

    /**
     * Obtains {@link Employee} object with data for Project specified by ID
     *
     * @return Employee object
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Project getProject(int id) throws ClassNotFoundException, SQLException {
        Project project = new Project();
        getPreparedStatement(SELECT_PROJECT);
        preparedStatement.setInt(1, id);
        ResultSet rsProject = preparedStatement.executeQuery();
        rsProject.next();
        project.setId(rsProject.getInt(ServletController.PROJECT_ID));
        project.setTitle(rsProject.getString(ServletController.TITLE));
        project.setShortTitle(rsProject.getString(ServletController.SHORT_TITLE));
        project.setDescription(rsProject.getString(ServletController.DESCRIPTION));
        rsProject.close();
        return project;
    }

    /**
     * Updates specified Project entry
     *
     * @param project {@link Project} object with updated data and ID to update data to
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateProject(Project project) throws ClassNotFoundException, SQLException {
        getPreparedStatement(UPDATE_PROJECT);
        preparedStatement.setString(1, project.getTitle());
        preparedStatement.setString(2, project.getShortTitle());
        preparedStatement.setString(3, project.getDescription());
        preparedStatement.setInt(4, project.getId());
        preparedStatement.executeUpdate();
    }

    /**
     * Adds new entry to Project table
     *
     * @param project {@link Project} object with new data and ID to put data to
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addProject(Project project) throws ClassNotFoundException, SQLException {
        getPreparedStatement(INSERT_PROJECT);
        preparedStatement.setInt(1, project.getId());
        preparedStatement.setString(2, project.getTitle());
        preparedStatement.setString(3, project.getShortTitle());
        preparedStatement.setString(4, project.getDescription());
        preparedStatement.executeUpdate();
    }

    /**
     * Deletes specified Project entry
     *
     * @param id ID of entry to look for and delete
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void deleteProject(int id) throws ClassNotFoundException, SQLException {
        getPreparedStatement(DELETE_PROJECT);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

    /**
     * Obtains collection of {@link Task} objects with data from database.
     * Obtains {@link Employee#lastname} and {@link Project#shortTitle} linked with each Task
     * Creates new {@link Task} object, stores Task data there and puts the object in collection.
     *
     * @return collection of Project objects
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Task> getTaskList() throws ClassNotFoundException, SQLException {
        getPreparedStatement(SELECT_ALL_TASKS);
        ResultSet rsList = preparedStatement.executeQuery();

        getPreparedStatement(SELECT_EMPLOYEE_USED_IN_TASK);
        ResultSet rsEmployee = preparedStatement.executeQuery();

        getPreparedStatement(SELECT_PROJECT_USED_IN_TASK);
        ResultSet rsProject = preparedStatement.executeQuery();

        rsEmployee.next();
        rsProject.next();
        List<Task> tasks = new ArrayList<>();
        while (rsList.next()) {
            Task task = new Task();
            task.setId(rsList.getInt(ServletController.TASK_ID));
            task.setEmployeeId(rsList.getInt(ServletController.EMPLOYEE_ID));
            task.setEmployeeName(rsEmployee.getString(ServletController.SURNAME));
            task.setProjectId(rsList.getInt(ServletController.PROJECT_ID));
            task.setProjectShortTitle(rsProject.getString(ServletController.SHORT_TITLE));
            task.setTitle(rsList.getString(ServletController.TITLE));
            task.setDuration(rsList.getInt(ServletController.DURATION));
            task.setStartDate(rsList.getString(ServletController.START_DATE));
            task.setEndDate(rsList.getString(ServletController.END_DATE));
            task.setStatus(rsList.getString(ServletController.STATUS));
            rsEmployee.next();
            rsProject.next();
            tasks.add(task);
        }
        rsList.close();
        rsEmployee.close();
        rsProject.close();
        return tasks;
    }

    /**
     * Obtains {@link Task} object with data of specified Task from database by its ID.
     *
     * @param id ID of specified Task
     * @return Task object
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Task getTask(int id) throws ClassNotFoundException, SQLException {
        Task task = new Task();
        getPreparedStatement(SELECT_TASK);
        preparedStatement.setInt(1, id);
        ResultSet rsTask = preparedStatement.executeQuery();
        rsTask.next();
        task.setId(rsTask.getInt(ServletController.TASK_ID));
        task.setEmployeeId(rsTask.getInt(ServletController.EMPLOYEE_ID));
        task.setProjectId(rsTask.getInt(ServletController.PROJECT_ID));
        task.setTitle(rsTask.getString(ServletController.TITLE));
        task.setDuration(rsTask.getInt(ServletController.DURATION));
        task.setStartDate(rsTask.getString(ServletController.START_DATE));
        task.setEndDate(rsTask.getString(ServletController.END_DATE));
        task.setStatus(rsTask.getString(ServletController.STATUS));
        rsTask.close();

        return task;
    }

    /**
     * Updates specified Task entry
     *
     * @param task {@link Task} object with updated data and ID to update data to
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateTask(Task task) throws ClassNotFoundException, SQLException {
        getPreparedStatement(UPDATE_TASK);
        preparedStatement.setInt(1, task.getEmployeeId());
        preparedStatement.setInt(2, task.getProjectId());
        preparedStatement.setString(3, task.getTitle());
        preparedStatement.setInt(4, task.getDuration());
        preparedStatement.setString(5, task.getStartDate());
        preparedStatement.setString(6, task.getEndDate());
        preparedStatement.setString(7, task.getStatus());
        preparedStatement.setInt(8, task.getId());
        preparedStatement.executeUpdate();

    }

    /**
     * Adds new entry to Task table
     *
     * @param task {@link Task} object with new data and ID to put data to
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addTask(Task task) throws ClassNotFoundException, SQLException {
        getPreparedStatement(INSERT_TASK);
        preparedStatement.setInt(1, task.getId());
        preparedStatement.setInt(2, task.getEmployeeId());
        preparedStatement.setInt(3, task.getProjectId());
        preparedStatement.setString(4, task.getTitle());
        preparedStatement.setInt(5, task.getDuration());
        preparedStatement.setString(6, task.getStartDate());
        preparedStatement.setString(7, task.getEndDate());
        preparedStatement.setString(8, task.getStatus());

        preparedStatement.executeUpdate();

    }

    /**
     * Deletes specified Task entry
     *
     * @param id ID of entry to look for and delete
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void deleteTask(int id) throws ClassNotFoundException, SQLException {
        getPreparedStatement(DELETE_TASK);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }


}
