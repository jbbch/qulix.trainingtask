<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Task List</title>
</head>
<body class="body" bgcolor="#f0f0f0">
<h2 class="h2">Tasks</h2>

<form method="GET">
    <button class="button" title="Add New" name="task" value="createForm"
            formaction="ServletController">Add New
    </button>
</form>
<table class="table">
    <tr>
        <th>ID</th>
        <th>Project</th>
        <th>Employee</th>
        <th>Title</th>
        <th>Duration</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Status</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>
    <c:forEach items="${tasks}" var="task">
        <tr>
            <td>${task.id}</td>
            <td>${task.projectShortTitle}</td>
            <td>${task.employeeName}</td>
            <td>${task.title}</td>
            <td>${task.duration}</td>
            <td>${task.startDate}</td>
            <td>${task.endDate}</td>
            <td>${task.status}</td>
            <td>
                <form method="GET">
                    <button class="button" name="taskDelete" value="${task.id}"
                            formaction="ServletController">Delete
                    </button>
                </form>
            </td>
            <td>
                <form method="GET">
                    <button  class="button" name="taskEdit" value="${task.id}"
                            formaction="ServletController">Edit
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<form method="GET">
    <button class="button" formaction="index.jsp">Main Menu
    </button>
</form>
</body>
</html>
