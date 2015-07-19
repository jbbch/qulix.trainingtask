<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title></title>
</head>
<body class="body" bgcolor="#f0f0f0">
<h2 class="h2">Create Project</h2>
<form action="ServletController" method="GET">
    ID: <input class="input" type="number"  name="projectId" required readonly value="${index}" autocomplete="off"/>
    <br />
    Title: <input class="input" type="text" name="title" required pattern="[a-zA-Z ]{1,20}" placeholder="maximum length is 20" autocomplete="off"/>
    <br />
    Short Title: <input class="input" type="text" name="shortTitle" required pattern="[a-zA-Z ]{1,15}" placeholder="max length is 15" autocomplete="off"/>
    <br />
    Description: <input class="input" type="text" name="description" required pattern="[a-zA-Z ]{1,50}" placeholder="maximum length is 50" autocomplete="off"/>
    <br />
    <input class="button" type="submit" name="project" value="Create" />
</form>
<form method="get">
    <button class="button" name="project" value="list"
            formaction="ServletController">Cancel
    </button>
</form>
</body>
</html>