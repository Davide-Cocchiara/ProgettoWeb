<%@ page import="unitn.progweb.cocchiara.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome User!</title>
</head>
<body>
<%
    User user = (User)session.getAttribute("user");
    out.println(user.getUsername() + " " + user.getType());
%>
</body>
</html>
