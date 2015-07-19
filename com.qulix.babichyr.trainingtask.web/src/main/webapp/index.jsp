<%--
  Created by IntelliJ IDEA.
  User: TS
  Date: 15.07.2015
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Task Management Application</title>
</head>
<body class="body" bgcolor="#f0f0f0">
<h2 class="h2">Main menu</h2>

<form method="get">
    <button class="buttonindex" name="employee" value="list"
            formaction="ServletController">Employee List
    </button>
</form>
<form method="get">
    <button class="buttonindex" name="project" value="list"
            formaction="ServletController">Project List
    </button>
</form>
<form method="get">
    <button class="buttonindex" name="task" value="list"
            formaction="ServletController">Task List
    </button>
</form>
</body>
</html>
