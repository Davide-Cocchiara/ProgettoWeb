<%--
  Created by IntelliJ IDEA.
  User: burge
  Date: 25/01/2020
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
</head>
<body>
<nav class="navbar navbar-dark navbar-expand bg-info shadow mb-4 topbar static-top" style="background-image: linear-gradient(90deg, rgba(109,32,32,1) 10%, rgba(60,18,18,1) 100%);">
    <div class="container-fluid"><button class="btn btn-link d-md-none rounded-circle mr-3" id="sidebarToggleTop" type="button"><i class="fas fa-bars"></i></button><a class="bg-secondary border rounded shadow" href="<%=request.getContextPath()%>/sismed/pazientilista" style="color: rgb(255,255,255);padding: 4px;padding-right: 6px;background-color: #1e2767!important;"><i class="fas fa-file-medical-alt" style="font-size: 20px;"></i><span style="font-size: 20px;margin-left: 4px;">Pazienti</span></a>
        <ul class="nav navbar-nav flex-nowrap ml-auto">
            <li class="nav-item dropdown no-arrow" role="presentation">
                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#"><span class="d-none d-lg-inline mr-2 text-gray-600 small" style="text-align: right;filter: brightness(200%) saturate(200%);">${sessionScope.utente.getPaziente().getNome()} ${sessionScope.utente.getPaziente().getCognome()}<br>Provincia di <%=request.getSession().getAttribute("provinciastampabile")%></span><img class="border rounded-circle img-profile" src="<%=request.getContextPath()%>/avatar"></a>
                    <div
                            class="dropdown-menu shadow dropdown-menu-right animated--grow-in" role="menu"><a class="dropdown-item" role="presentation" href="<%=request.getContextPath()%>/sismed/profilomedico"><i class="fas fa-persona fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Profile</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" role="presentation" href="<%=request.getContextPath()%>/servizi"><i class="fas fa-persona fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Servizi</a>

                        <a class="dropdown-item" role="presentation" href="<%=request.getContextPath()%>/logout"><i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Logout</a></div>
                </div>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>
