<%--
  Created by IntelliJ IDEA.
  User: dimon
  Date: 17.01.2016
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


    <h3>Hello ${user.name}</h3>
    <h4>You password is ${user.password}</h4>
    <h5>Is administrator ${user.administrator}</h5>
    <h5>${locale}</h5>

    <form action="logout" method="get">
        <input type="submit">
    </form>

</body>
</html>
