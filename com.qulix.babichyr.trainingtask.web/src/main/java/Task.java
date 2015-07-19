package main.java;


/**
 * Container for Task entity data, getter and setter methods
 *
 * @author Yaraslau Babich
 * @version 1.0
 */
public class Task {
    private int id;
    private int employeeId;
    /**
     * Contains {@link Employee#lastname} of {@link Employee} appointed to this task
     */
    private String employeeName;
    private int projectId;
    /**
     * Contains {@link Project#shortTitle} of {@link Employee} appointed to this task
     */
    private String projectShortTitle;
    private String title;
    private int duration;
    private String startDate;
    private String endDate;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getProjectShortTitle() {
        return projectShortTitle;
    }

    public void setProjectShortTitle(String projectShortTitle) {
        this.projectShortTitle = projectShortTitle;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
