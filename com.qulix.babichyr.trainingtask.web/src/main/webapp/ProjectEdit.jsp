<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Project Edit</title>
</head>
<body class="body" bgcolor="#f0f0f0">
<h2 class="h2">Project Edit</h2>

<form action="ServletController" method="GET">
    ID: <input class="input" type="number" name="projectId" required readonly value="${id}" autocomplete="off"/>
    <br/>
    Title: <input class="input" type="text" name="title" required value="${title}" pattern="[a-zA-Z ]{1,30}"
                  placeholder="max length 20" autocomplete="off"/>
    <br/>
    Short Title: <input class="input" type="text" name="shortTitle" required value="${shortTitle}" pattern="[a-zA-Z ]{1,15}"
                        placeholder="max length 15" autocomplete="off"/>
    <br/>
    Description: <input class="input" type="text" name="description" required value="${description}" pattern="[a-zA-Z ]{1,50}"
                        placeholder="max length 50" autocomplete="off"/>
    <br/>
    <input class="button" type="submit" name="project" value="Edit"/>
</form>
<br>
    <h2 class="h2">Appointed Tasks</h2>
    <form method="GET"><input type="hidden" name="projectId" value="${id}">
        <button class="button" style="height:25px;width:80px" title="Add New" name="task" value="createForm"
                formaction="ServletController">Add New
        </button>
    </form>
    <table class="table">
        <c:forEach items="${tasks}" var="task">
            <c:if test="${id==task.projectId}">
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
                            <input type="hidden" name="projectEdit" value="${id}"/>
                            <input type="hidden" name="fromProject" value="delete"/>
                            <button class="button" name="taskDelete" value="${task.id}"
                                    formaction="ServletController">Delete
                            </button>
                        </form>
                    </td>
                    <td>
                        <form method="GET">
                            <button class="button" name="taskFromProject" value="${task.id}"
                                    formaction="ServletController">Edit
                            </button>
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
<br>
<form method="get">
    <button class="button" name="project" value="list"
            formaction="ServletController">Cancel
    </button>
</form>
</body>
</html>