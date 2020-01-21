<%@ page import="unitn.progweb.cocchiara.model.Persona" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome User!</title>
</head>
<body>
<%
    Persona persona = (Persona)session.getAttribute("Persona");
    out.println(persona.getCodicefiscale()+ " " + persona.getDatanascita().toString());
%>
<a href="logout">Logout</a>
</body>
</html>
