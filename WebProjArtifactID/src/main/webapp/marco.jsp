<%@ page import="unitn.progweb.cocchiara.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    User marco = (User)request.getAttribute("marco");
    out.println(marco.getUsername() + " " + marco.getPassword());
%>
</body>
</html>
