<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Project List</title>
</head>
<body class="body" bgcolor="#f0f0f0">
<h2 class="h2">Projects</h2>

<form method="GET">
    <button class="button" title="Add New" name="project" value="createForm"
            formaction="ServletController">Add New
    </button>
</form>
<table class="table" border="1">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Short Title</th>
        <th>Description</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>
    <c:forEach items="${projects}" var="project">
        <tr>
            <td>${project.id}</td>
            <td>${project.title}</td>
            <td>${project.shortTitle}</td>
            <td>${project.description}</td>
            <td>
                <c:if test="${project.isRemovable==true}">
                    <form method="GET">
                        <button style="height:25px;width:60px" name="projectDelete" value="${project.id}"
                                formaction="ServletController">Delete
                        </button>
                    </form>
                </c:if>
                <c:if test="${project.isRemovable==false}">
                    Project is used in Task
                </c:if>
            </td>
            <td>
                <form method="GET">
                    <button style="height:25px;width:60px" name="projectEdit" value="${project.id}"
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
