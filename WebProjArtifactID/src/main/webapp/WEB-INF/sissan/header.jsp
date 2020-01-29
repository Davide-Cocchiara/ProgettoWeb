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


<nav class="navbar navbar-dark navbar-expand bg-info shadow mb-4 topbar static-top"
     style="background-color: #1cc88a!important;">
    <div class="container-fluid">
        <button class="btn btn-link d-md-none rounded-circle mr-3" id="sidebarToggleTop" type="button"><i class="fas fa-bars"></i></button>

        <div
                class="dropdown"><a class="dropdown-toggle bg-info border rounded border-success" data-toggle="dropdown"
                                    aria-expanded="false" href="#"
                                    style="color: rgb(255,255,255);margin: 0px;padding: 5px;">Funzioni
            Amministrative</a>
            <div class="dropdown-menu"
                 role="menu" style="background-color: rgb(54,185,204);"><a class="dropdown-item" role="presentation"
                                                                           href="<%=request.getContextPath()%>/sissan/reportprestazioni">Report
                Prestazioni Erogate</a>


                <a
                    class="dropdown-item" role="presentation"
                    href="<%=request.getContextPath()%>/sissan/prestazionilista">Info Prestazioni</a>
                <a
                        class="dropdown-item" role="presentation"
                        href="<%=request.getContextPath()%>/sissan/pazientilista">Lista Pazienti</a>
            </div>

        </div>


        <ul class="nav navbar-nav flex-nowrap ml-auto">
            <li class="nav-item dropdown no-arrow" role="presentation">
                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown"
                                                           aria-expanded="false" href="#"><span
                        class="d-none d-lg-inline mr-2 text-gray-600 small"
                        style="text-align: right;filter: brightness(200%) saturate(200%);">${sessionScope.utente.getPaziente().getNome()} ${sessionScope.utente.getPaziente().getCognome()}<br>Provincia di <%=request.getSession().getAttribute("provinciastampabile")%></span><img
                        class="border rounded-circle img-profile" src="<%=request.getContextPath()%>/avatar"></a>
                    <div
                            class="dropdown-menu shadow dropdown-menu-right animated--grow-in" role="menu"><a
                            class="dropdown-item" role="presentation"
                            href="<%=request.getContextPath()%>/sissan/profiloadmin"><i
                            class="fas fa-persona fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Profile</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" role="presentation" href="<%=request.getContextPath()%>/servizi"><i
                                class="fas fa-persona fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Servizi</a>

                        <a class="dropdown-item" role="presentation" href="<%=request.getContextPath()%>/logout"><i
                                class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Logout</a></div>
                </div>
            </li>
        </ul>
    </div>
</nav>

</body>
</html>



