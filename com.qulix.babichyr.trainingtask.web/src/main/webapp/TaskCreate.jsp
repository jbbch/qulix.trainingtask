<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="Employee" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Task Create</title>
</head>
<body class="body" bgcolor="#f0f0f0">
<h2 class="h2">Task Create</h2>
<form action="ServletController" method="GET">
    ID:
    <input class="input" type="number"  name="taskId" required readonly value="${index}" autocomplete="off"/><br/>
    Project:
    <c:if test="${not empty param.taskFromProject}">
        <input type="hidden" name="projectEdit" value="${projectId}"/>
        <input type="hidden" name="fromProject" value="create"/>
        <input class="input" type="number" name="projectId" readonly value="${projectId}" autocomplete="off"/><br/>
    </c:if>
    <c:if test="${empty param.taskFromProject}">
        <%--<c:set var="taskFromProject" value="ServletController?task=list"/>--%>
        <select  class="input" required name="projectId">
            <c:forEach items="${projects}" var="project">
                <option value="${project.id}">${project.shortTitle}</option>
            </c:forEach>
        </select><br/>
    </c:if>
    Employee:
    <select class="input" required name="employeeId">
        <c:forEach items="${employees}" var="employee">
            <option value="${employee.id}">${employee.lastname}</option>
        </c:forEach>
    </select><br/>
    Title:
    <input class="input" type="text" name="title" required pattern="[a-zA-Z ]{1,20}" placeholder="max length 20" autocomplete="off"/><br />
    Duration:
    <input class="input" type="text" name="duration" required pattern="[1-9\d*]{1,3}" placeholder="1-999"  autocomplete="off"/><br/>
    Start Date:
    <input class="input" type="date" name="startDate" required placeholder="Start Date" autocomplete="off"/><br />
    End Date:
    <input class="input" type="date" name="endDate" required  placeholder="End Date" autocomplete="off"/><br />
    Status:
    <select class="input" required name="status">
        <option value="Not Started">Not Started</option>
        <option value="In Progress">In Progress</option>
        <option value="Finished">Finished</option>
        <option value="Delayed">Delayed</option>
    </select>
    <br>
    <input class="button" type="submit" name="task" value="Create" />
</form>
<br>
<a href="${taskFromProject}">Cancel</a>
</body>
</html>