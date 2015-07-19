<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Employee List</title>
</head>
<body class="body" bgcolor="#f0f0f0">
<h2 class="h2">Employees</h2>

<form method="GET">
    <button class="button" title="Add New" name="employee" value="createForm"
            formaction="ServletController">Add New
    </button>
</form>
<table border="1" cellspacing="1" style="table-layout: auto" class="table">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Patronymic</th>
        <th>Position</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>
    <c:forEach items="${employees}" var="employee">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.firstname}</td>
            <td>${employee.lastname}</td>
            <td>${employee.patronymic}</td>
            <td>${employee.position}</td>
            <td>
                <c:if test="${employee.isRemovable==true}">
                    <form method="GET">
                        <button style="height:25px;width:60px" name="employeeDelete" value="${employee.id}"
                                formaction="ServletController">Delete
                        </button>
                    </form>
                </c:if>
                <c:if test="${employee.isRemovable==false}">
                    Employee is used in Task<br>
                    Unable to delete
                </c:if>
            </td>
            <td>
                <form method="GET">
                    <button style="height:25px;width:60px" name="employeeEdit" value="${employee.id}"
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
