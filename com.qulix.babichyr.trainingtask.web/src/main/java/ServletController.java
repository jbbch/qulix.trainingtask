package main.java;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.Exception;
import java.util.List;

/**
 * Front controller servlet dispatches all requests to specific JSP pages.
 * Servlet checks if request contains specific attributes and redirects request to
 * JSP page that meets conditions.
 *
 * @author Yaraslau Babich
 * @version 1.0
 */
@WebServlet(name = "ServletController")
public class ServletController extends HttpServlet {

    static final String ID = "id";
    //EMPLOYEE FIELDS
    static final String EMPLOYEE_ID = "employeeId";
    static final String NAME = "firstname";
    static final String SURNAME = "lastname";
    static final String PATRONYMIC = "patronymic";
    static final String POSITION = "position";
    //PROJECT FIELDS
    static final String PROJECT_ID = "projectId";
    static final String TITLE = "title";
    static final String SHORT_TITLE = "shortTitle";
    static final String DESCRIPTION = "description";
    //TASK FIELDS
    static final String TASK_ID = "taskId";
    static final String DURATION = "duration";
    static final String START_DATE = "startDate";
    static final String END_DATE = "endDate";
    static final String STATUS = "status";
    //REQUEST ATTRIBUTES
    static final String REFERER_HEADER = "referer";
    static final String EMPLOYEE = "employee";
    static final String PROJECT = "project";
    static final String TASK = "task";
    static final String EMPLOYEE_COLLECTION = "employees";
    static final String PROJECT_COLLECTION = "projects";
    static final String TASK_COLLECTION = "tasks";
    static final String INDEX = "index";
    static final String LIST = "list";
    static final String CREATE_FORM = "createForm";
    static final String CREATE = "Create";
    static final String EDIT = "Edit";
    static final String UPDATE = "update";
    static final String DELETE = "delete";
    static final String EMPLOYEE_DELETE = "employeeDelete";
    static final String EMPLOYEE_EDIT = "employeeEdit";
    static final String PROJECT_EDIT = "projectEdit";
    static final String PROJECT_DELETE = "projectDelete";
    static final String FROM_PROJECT_PAGE_OPERATION = "fromProject";
    static final String FROM_PROJECT_PAGE = "taskFromProject";
    static final String TASK_DELETE = "taskDelete";
    static final String TASK_EDIT = "taskEdit";

    //URI
    static final String URL_EMPLOYEE_CREATE = "EmployeeCreate.jsp";
    static final String URL_EMPLOYEE_EDIT = "EmployeeEdit.jsp";
    static final String URL_EMPLOYEE_LIST = "EmployeeList.jsp";
    static final String URL_PROJECT_CREATE = "ProjectCreate.jsp";
    static final String URL_PROJECT_EDIT = "ProjectEdit.jsp";
    static final String URL_PROJECT_LIST = "ProjectList.jsp";
    static final String URL_TASK_CREATE = "TaskCreate.jsp";
    static final String URL_TASK_EDIT = "TaskEdit.jsp";
    static final String URL_TASK_LIST = "TaskList.jsp";


    EmployeeService employeeService;
    ProjectService projectService;
    TaskService taskService;

    /**
     * Creates {@link EmployeeService, ProjectService, TaskService} objects to invoke methods on
     * @throws Exception
     */
    public ServletController() throws Exception {
        employeeService = new EmployeeService();
        projectService = new ProjectService();
        taskService = new TaskService();
    }

    /**
     * Sets {@link Employee} objects collection as request attribute.
     *
     * @param request HTTP request
     * @param response HTTP response
     * @throws Exception
     */
    public void employeeListPrint(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Employee> employees = employeeService.getEmployeeList();
        request.setAttribute(EMPLOYEE_COLLECTION, employees);
        RequestDispatcher view = request.getRequestDispatcher(URL_EMPLOYEE_LIST);
        view.forward(request, response);

    }

    /**
     * Sets {@link Project} objects collection as request attribute.
     *
     * @param request HTTP request
     * @param response HTTP response
     * @throws Exception
     */
    public void projectListPrint(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Project> projects = projectService.getProjectList();
        request.setAttribute(PROJECT_COLLECTION, projects);
        RequestDispatcher view = request.getRequestDispatcher(URL_PROJECT_LIST);
        view.forward(request, response);
    }

    /**
     * Sets {@link Task} objects collection as request attribute.
     *
     * @param request HTTP request
     * @param response HTTP response
     * @throws Exception
     */
    public void taskListPrint(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Task> tasks = taskService.getTaskList();
        request.setAttribute(TASK_COLLECTION, tasks);
        RequestDispatcher view = request.getRequestDispatcher(URL_TASK_LIST);
        view.forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            //EMPLOYEE CONTROLLER
            if (request.getParameterMap().containsKey(EMPLOYEE)) {

                if (request.getParameter(EMPLOYEE).equals(LIST)) {
                    employeeListPrint(request, response);
                }

                if (request.getParameter(EMPLOYEE).equals(CREATE_FORM)) {
                    int index = employeeService.getNextId();
                    request.setAttribute(INDEX, index);
                    request.getRequestDispatcher(URL_EMPLOYEE_CREATE).forward(request, response);
                }

                if (request.getParameter(EMPLOYEE).equals(CREATE)) {
                    employeeService.addEmployee(getEmployeeFromRequest(request));
                    employeeListPrint(request, response);
                }

                if (request.getParameter(EMPLOYEE).equals(EDIT)) {
                    employeeService.updateEmployee(getEmployeeFromRequest(request));
                    employeeListPrint(request, response);
                }

            }

            if (request.getParameterMap().containsKey(EMPLOYEE_DELETE)) {
                employeeService.deleteEmployee(Integer.parseInt(request.getParameter(EMPLOYEE_DELETE)));
                employeeListPrint(request, response);
            }
            if (request.getParameterMap().containsKey(EMPLOYEE_EDIT)) {
                passEmployeeToRequest(request);
                request.getRequestDispatcher(URL_EMPLOYEE_EDIT).forward(request, response);
            }


            //PROJECT CONTROLLER
            if (request.getParameterMap().containsKey(PROJECT)) {

                if (request.getParameter(PROJECT).equals(LIST)) {
                    projectListPrint(request, response);
                }
                if (request.getParameter(PROJECT).equals(CREATE_FORM)) {
                    int index = projectService.getNextId();
                    request.setAttribute(INDEX, index);
                    request.getRequestDispatcher(URL_PROJECT_CREATE).forward(request, response);
                }

                if (request.getParameter(PROJECT).equals(CREATE)) {
                    projectService.addProject(getProjectFromRequest(request));
                    projectListPrint(request, response);
                }

                if (request.getParameter(PROJECT).equals(EDIT)) {
                    projectService.updateProject(getProjectFromRequest(request));
                    projectListPrint(request, response);
                }

            }

            if (request.getParameterMap().containsKey(PROJECT_DELETE)) {
                projectService.deleteProject(Integer.parseInt(request.getParameter(PROJECT_DELETE)));
                projectListPrint(request, response);
            }
            if (request.getParameterMap().containsKey(PROJECT_EDIT)) {
                if (request.getParameterMap().containsKey(FROM_PROJECT_PAGE_OPERATION) && request.getParameter(FROM_PROJECT_PAGE_OPERATION).equals(UPDATE)) {
                    taskService.updateTask(getTaskFromRequest(request));
                }
                if (request.getParameterMap().containsKey(FROM_PROJECT_PAGE_OPERATION) && request.getParameter(FROM_PROJECT_PAGE_OPERATION).equals(CREATE)) {
                    taskService.addTask(getTaskFromRequest(request));
                }
                if (request.getParameterMap().containsKey(FROM_PROJECT_PAGE_OPERATION) && request.getParameter(FROM_PROJECT_PAGE_OPERATION).equals(DELETE)) {
                    taskService.deleteTask(Integer.parseInt(request.getParameter(TASK_DELETE)));
                }
                passProjectToRequest(request);
                request.getRequestDispatcher(URL_PROJECT_EDIT).forward(request, response);

            }

            //TASK CONTROLLER
            if (request.getParameterMap().containsKey(TASK)) {

                if (request.getParameter(TASK).equals(LIST)) {
                    taskListPrint(request, response);
                }
                if (request.getParameter(TASK).equals(CREATE_FORM)) {
                    int index = taskService.getNextId();
                    List<Project> projects = projectService.getProjectList();
                    List<Employee> employees = employeeService.getEmployeeList();
                    request.setAttribute(PROJECT_COLLECTION, projects);
                    request.setAttribute(EMPLOYEE_COLLECTION, employees);
                    request.setAttribute(INDEX, index);
                    request.setAttribute(FROM_PROJECT_PAGE, request.getHeader(REFERER_HEADER));
                    request.setAttribute(PROJECT_ID, request.getParameter(PROJECT_ID));
                    request.getRequestDispatcher(URL_TASK_CREATE).forward(request, response);
                }

                if (request.getParameter(TASK).equals(CREATE)) {
                    taskService.addTask(getTaskFromRequest(request));
                    taskListPrint(request, response);
                }

                if (request.getParameter(TASK).equals(EDIT)) {
                    taskService.updateTask(getTaskFromRequest(request));
                    taskListPrint(request, response);
                }
            }


            if (request.getParameterMap().containsKey(TASK_DELETE)) {
                taskService.deleteTask(Integer.parseInt(request.getParameter(TASK_DELETE)));
                taskListPrint(request, response);
            }


            if (request.getParameterMap().containsKey(TASK_EDIT)) {
                passTaskToRequest(request);
                request.getRequestDispatcher(URL_TASK_EDIT).forward(request, response);
            }

            if (request.getParameterMap().containsKey(FROM_PROJECT_PAGE)) {
                passTaskToRequest(request);
                request.setAttribute(FROM_PROJECT_PAGE, request.getHeader(REFERER_HEADER));
                request.getRequestDispatcher(URL_TASK_EDIT).forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Puts request parameters into {@link Task} object
     * @param request HTTP request
     * @return Employee object
     */
    public Task getTaskFromRequest(HttpServletRequest request) {
        Task task = new Task();
        task.setId(Integer.parseInt(request.getParameter(TASK_ID)));
        task.setEmployeeId(Integer.parseInt(request.getParameter(EMPLOYEE_ID)));
        task.setProjectId(Integer.parseInt(request.getParameter(PROJECT_ID)));
        task.setTitle(request.getParameter(TITLE));
        task.setDuration(Integer.parseInt(request.getParameter(DURATION)));
        task.setStartDate(request.getParameter(START_DATE));
        task.setEndDate(request.getParameter(END_DATE));
        task.setStatus(request.getParameter(STATUS));
        return task;
    }

    /**
     * Puts request parameters into {@link Employee} object
     * @param request HTTP request
     * @return Employee object
     */
    public Employee getEmployeeFromRequest(HttpServletRequest request) {
        Employee employee = new Employee();
        employee.setId(Integer.parseInt(request.getParameter(EMPLOYEE_ID)));
        employee.setFirstname(request.getParameter(NAME));
        employee.setLastname(request.getParameter(SURNAME));
        employee.setPatronymic(request.getParameter(PATRONYMIC));
        employee.setPosition(request.getParameter(POSITION));
        return employee;
    }

    /**
     * Puts request parameters into {@link Project} object
     * @param request HTTP request
     * @return Project object
     */
    public Project getProjectFromRequest(HttpServletRequest request) {
        Project project = new Project();
        project.setId(Integer.parseInt(request.getParameter(PROJECT_ID)));
        project.setTitle(request.getParameter(TITLE));
        project.setShortTitle(request.getParameter(SHORT_TITLE));
        project.setDescription(request.getParameter(DESCRIPTION));
        return project;
    }

    /**
     * Passes {@link Task} object fields as request attributes.
     * Gets specific Task ID from request parameter.
     * Checks if Task object was obtained from Task list page or Project edit page.
     *
     * @param request HTTP request
     * @return HTTP request with Task attributes
     * @throws Exception
     */
    public HttpServletRequest passTaskToRequest(HttpServletRequest request) throws Exception {
        Task task;
        if (request.getParameterMap().containsKey(FROM_PROJECT_PAGE)) {
            task = taskService.getTask(Integer.parseInt(request.getParameter(FROM_PROJECT_PAGE)));
        } else {
            task = taskService.getTask(Integer.parseInt(request.getParameter(TASK_EDIT)));
        }
        List<Project> projects = projectService.getProjectList();
        List<Employee> employees = employeeService.getEmployeeList();
        request.setAttribute(ID, task.getId());
        request.setAttribute(EMPLOYEE_ID, task.getEmployeeId());
        request.setAttribute(PROJECT_ID, task.getProjectId());
        request.setAttribute(TITLE, task.getTitle());
        request.setAttribute(DURATION, task.getDuration());
        request.setAttribute(START_DATE, task.getStartDate());
        request.setAttribute(END_DATE, task.getEndDate());
        request.setAttribute(STATUS, task.getStatus());
        request.setAttribute(PROJECT_COLLECTION, projects);
        request.setAttribute(EMPLOYEE_COLLECTION, employees);
        return request;
    }

    /**
     * Passes {@link Employee} object fields as request attributes.
     * Gets specific Employee ID from request parameter.
     *
     * @param request HTTP request
     * @return HTTP request with Employee attributes
     * @throws Exception
     */
    public HttpServletRequest passEmployeeToRequest(HttpServletRequest request) throws Exception {
        Employee employee = employeeService.getEmployee(Integer.parseInt(request.getParameter(EMPLOYEE_EDIT)));
        request.setAttribute(ID, employee.getId());
        request.setAttribute(NAME, employee.getFirstname());
        request.setAttribute(SURNAME, employee.getLastname());
        request.setAttribute(PATRONYMIC, employee.getPatronymic());
        request.setAttribute(POSITION, employee.getPosition());
        return request;
    }

    /**
     * Passes {@link Project} object fields as request attributes.
     * Gets specific Project ID from request parameter.
     * Obtains {@link Task} object collection.
     *
     * @param request HTTP request
     * @return HTTP request with Project attributes and appointed Task collection
     * @throws Exception
     */
    public HttpServletRequest passProjectToRequest(HttpServletRequest request) throws Exception {
        Project project = projectService.getProject(Integer.parseInt(request.getParameter(PROJECT_EDIT)));
        List<Task> tasks = taskService.getTaskList();
        request.setAttribute(TASK_COLLECTION, tasks);
        request.setAttribute(ID, project.getId());
        request.setAttribute(TITLE, project.getTitle());
        request.setAttribute(SHORT_TITLE, project.getShortTitle());
        request.setAttribute(DESCRIPTION, project.getDescription());
        return request;
    }
}
