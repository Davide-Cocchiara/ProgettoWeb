<%--
  Created by IntelliJ IDEA.
  User: burge
  Date: 22/01/2020
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
    <div class="container-fluid d-flex flex-column p-0">
        <a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="<%=request.getContextPath()%>/sispaz/profilocittadino">
            <div class="sidebar-brand-icon rotate-n-15"><i class="fa fa-plus-square"></i></div>
            <div class="sidebar-brand-text mx-3"><span>Servizi Sanitari<br>per il cittadino</span></div>
        </a>
        <hr class="sidebar-divider my-0">
        <ul class="nav navbar-nav text-light" id="accordionSidebar">
            <li class="nav-item" role="presentation"><a class="nav-link text-center" href="<%=request.getContextPath()%>/sispaz/refertilista"><i class="fas fa-file-medical-alt" style="font-size: 20px;"></i><span style="font-size: 20px;">Referti</span></a><a class="nav-link text-center" href="<%=request.getContextPath()%>/sispaz/prescrizionilista"><i class="fas fa-notes-medical" style="font-size: 20px;"></i><span style="font-size: 20px;">Prescrizioni</span></a>
                <a
                        class="nav-link text-center" href="<%=request.getContextPath()%>/sispaz/pagamentilista"><i class="fas fa-money-bill-wave" style="font-size: 20px;"></i><span style="font-size: 20px;">Pagamenti</span></a><a class="nav-link text-center" href="<%=request.getContextPath()%>/sispaz/infomedico"><i class="fas fa-book-medical" style="font-size: 20px;"></i><span style="font-size: 20px;">Info Medico</span></a></li>
            <li
                    class="nav-item" role="presentation"></li>
        </ul>
        <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
    </div>
</nav>
</body>
</html>
