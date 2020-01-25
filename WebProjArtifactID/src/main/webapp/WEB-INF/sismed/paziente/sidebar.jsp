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
<nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0" style="background-color: rgb(109,32,32);background-image: linear-gradient(180deg, rgba(109,32,32,1) 10%, rgba(60,18,18,1) 100%);border-width: 2px!important;border-right-color: rgb(99,22,22);border-right-style: inset;">
    <div class="container-fluid d-flex flex-column p-0">
        <a class="navbar-brand border-danger d-flex justify-content-center align-items-center sidebar-brand m-0" href="#" style="padding-right: 6px;border-color: rgb(99,22,22)!important;">
            <div class="sidebar-brand-icon rotate-n-15"><i class="fa fa-plus-square"></i></div>
            <div class="sidebar-brand-text mx-3"><span>Servizi Medico<br></span></div>
        </a>
        <hr class="sidebar-divider my-0">
        <ul class="nav navbar-nav text-light" id="accordionSidebar">
            <li class="nav-item" role="presentation"><a class="nav-link text-center active" href="/SisMed/paziente/info-paziente.html"><i class="fas fa-persona" style="font-size: 20px;"></i><strong>DAADEA80C02A475A</strong><br><strong>Ada Eda</strong></a></li>
            <li class="nav-item" role="presentation"><a class="nav-link text-center" href="/SisMed/paziente/storico-referti.html"><i class="fas fa-book-medical" style="font-size: 20px;"></i><span style="font-size: 20px;">Storico Referti</span></a></li>
            <li class="nav-item" role="presentation"><a class="nav-link text-center" href="/SisMed/paziente/storico-prescrizioni.html"><i class="fas fa-notes-medical" style="font-size: 20px;"></i><span style="font-size: 20px;">Storico Prescrizioni</span></a></li>
            <li class="nav-item"
                role="presentation"><a class="nav-link text-center" href="/SisMed/paziente/nuova-ricetta.html"><i class="fas fa-file-medical" style="font-size: 20px;"></i><span style="font-size: 20px;">Nuova Ricetta</span></a></li>
            <li class="nav-item" role="presentation"><a class="nav-link text-center" href="/SisMed/paziente/nuova-visita.html"><i class="fas fa-diagnoses" style="font-size: 20px;"></i><span style="font-size: 20px;">Nuova Visita</span></a></li>
        </ul>
        <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
    </div>
</nav>
</body>
</html>
