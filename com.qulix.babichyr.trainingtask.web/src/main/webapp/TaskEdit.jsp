<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="Employee" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Task Edit</title>
</head>
<body class="body" bgcolor="#f0f0f0">
<h2 class="h2">Task Edit</h2>
<form action="ServletController" method="GET">
    ID:
    <input class="input" type="number"  name="taskId" required readonly value="${id}" autocomplete="off"/><br/>
    Project:
    <c:if test="${not empty taskFromProject}">
        <input type="hidden" name="projectEdit" value="${projectId}"/>
        <input type="hidden" name="fromProject" value="update"/>
        <select class="input" required name="projectId">
            <c:forEach items="${projects}" var="project">
                <c:set value="" var="selectCheck"/>
                <option <c:if test="${project.id==projectId}"> selected </c:if> value="${project.id}">${project.shortTitle}</option>
            </c:forEach>
        </select><br/>
    </c:if>
    <c:if test="${empty taskFromProject}">
        <c:set var="taskFromProject" value="ServletController?task=list"></c:set>
        <select class="input" required name="projectId">
            <c:forEach items="${projects}" var="project">
                <c:set value="" var="selectCheck"/>
                <option <c:if test="${project.id==projectId}"> selected </c:if> value="${project.id}">${project.shortTitle}</option>
            </c:forEach>
        </select><br/>
    </c:if>
    Employee:
    <select class="input" required name="employeeId">
        <c:forEach items="${employees}" var="employee">
            <c:set value="" var="selectCheck"/>
            <c:if test="${employee.id==employeeId}"><c:set value="selected" var="selectCheck"></c:set></c:if>
            <option ${selectCheck} value="${employee.id}">${employee.lastname}</option>
        </c:forEach>
    </select><br/>
    Title:
    <input class="input" type="text" name="title" required value="${title}" pattern="[a-zA-Z ]{1,30}" placeholder="maximum length is 20" autocomplete="off"/><br />
    Duration:
    <input class="input" type="text" name="duration" required value="${duration}" pattern="[1-9\d*]{1,3}" placeholder="from 1 to 999"  autocomplete="off"/><br/>
    Start Date:
    <input class="input" type="date" name="startDate" required value="${startDate}" placeholder="Start Date" autocomplete="off"/><br />
    End Date:
    <input class="input" type="date" name="endDate" required value="${endDate}"  placeholder="End Date" autocomplete="off"/><br />
    Status:
    <select class="input" required name="status">
        <option <c:if test="${status=='Not Started'}"> selected </c:if>  value="Not Started">Not Started</option>
        <option <c:if test="${status=='In Progress'}"> selected </c:if> value="In Progress">In Progress</option>
        <option <c:if test="${status=='Finished'}"> selected </c:if> value="Finished">Finished</option>
        <option <c:if test="${status=='Delayed'}"> selected </c:if> value="Delayed">Delayed</option>
    </select>
    <br>
    <input class="button" type="submit" name="task" value="Edit" />
</form>
<br>
<a href="${taskFromProject}">Cancel</a>
</body>
</html>