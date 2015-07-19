<%--
  Created by IntelliJ IDEA.
  User: TS
  Date: 16.07.2015
  Time: 0:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title></title>
</head>
<body class="body" bgcolor="#f0f0f0">
<h2 class="h2">Employee Create</h2>

<form action="ServletController" method="GET">
    ID: <input class="input" type="number" name="employeeId" required readonly value="${index}" autocomplete="off"/>
    <br/>
    Name: <input class="input" type="text" name="firstname" required pattern="[a-zA-Z ]{1,20}" placeholder="maximum length is 20"
                 autocomplete="off"/>
    <br/>
    Surname: <input  class="input" type="text" name="lastname" required pattern="[a-zA-Z ]{1,20}" placeholder="maximum length is 20"
                    autocomplete="off"/>
    <br/>
    Patronymic: <input  class="input" type="text" name="patronymic" required pattern="[a-zA-Z ]{1,20}" placeholder="maximum length is 20"
                       autocomplete="off"/>
    <br/>
    Position: <input  class="input" type="text" name="position" required pattern="[a-zA-Z ]{1,20}" placeholder="maximum length is 20"
                     autocomplete="off"/>
    <br>
    <input class="button" type="submit" name="employee" value="Create"/>
</form>
<form method="get">
    <button class="button" name="employee" value="list"
            formaction="ServletController">Cancel
    </button>
</form>
</body>
</html>
