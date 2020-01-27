<%--
  Created by IntelliJ IDEA.
  User: burge
  Date: 25/01/2020
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sidebar</title>
</head>
<body>
<nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0" style="background-color: rgb(224,255,205);background-image: linear-gradient(180deg, rgba(164,205,155,1) 10%, rgba(100,170,120,1) 100%);">
    <div class="container-fluid d-flex flex-column p-0">
        <a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="#">
            <div class="sidebar-brand-icon rotate-n-15"><i class="fa fa-plus-square"></i></div>
            <div class="sidebar-brand-text mx-3"><span class="text-center">Servizi <br>Amministrativi<br></span></div>
        </a>
        <hr class="sidebar-divider my-0">
        <ul class="nav navbar-nav text-light" id="accordionSidebar">
            <li class="nav-item" role="presentation"><a class="nav-link text-center active" href="<%=request.getContextPath()%>/sissan/paziente/infopaziente"><i class="fas fa-persona" style="font-size: 20px;"></i><strong>${sessionScope.selectedprovinciapaziente.getCodicefiscale()}</strong><br><strong>${sessionScope.selectedprovinciapaziente.getNome()} ${sessionScope.selectedprovinciapaziente.getCognome()}</strong></a></li>
            <li class="nav-item" role="presentation"><a class="nav-link text-center" href="<%=request.getContextPath()%>/sissan/paziente/erogaservizio"><i class="fas fa-file-medical-alt" style="font-size: 20px;"></i><span style="font-size: 20px;">Eroga Farmaco / Esame</span></a></li>
        </ul>
        <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
    </div>
</nav>
</body>
</html>
