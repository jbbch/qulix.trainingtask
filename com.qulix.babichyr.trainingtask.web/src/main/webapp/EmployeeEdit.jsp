<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Employee Edit</title>
</head>
<body class="body" bgcolor="#f0f0f0">
<h2 class="h2">Employee Edit</h2>
<form action="ServletController" method="GET">
    ID: <input class="input" type="number"  name="employeeId" required readonly value="${id}" autocomplete="off"/>
    <br />
    Name: <input class="input" type="text" name="firstname" required value="${firstname}" pattern="[a-zA-Z ]{1,20}" placeholder="max length 20" autocomplete="off"/>
    <br />
    Surname: <input class="input" type="text" name="lastname" required value="${lastname}" pattern="[a-zA-Z ]{1,20}" placeholder="max length 20" autocomplete="off"/>
    <br />
    Patronymic: <input class="input" type="text" name="patronymic" required value="${patronymic}" pattern="[a-zA-Z ]{1,20}" placeholder="max length 20" autocomplete="off"/>
    <br />
    Position: <input class="input" type="text" name="position" required value="${position}" pattern="[a-zA-Z ]{1,20}" placeholder="max length 20"  autocomplete="off"/>
    <br>
    <input class="button" type="submit" name="employee" value="Edit">
</form>
<form method="get">
    <button class="button" name="employee" value="list"
            formaction="ServletController">Cancel
    </button>
</form>
</body>
</html>