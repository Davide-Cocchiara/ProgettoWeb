<%@ page import="unitn.progweb.cocchiara.model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Utente utente = (Utente)session.getAttribute("utente");

%>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Servizi - Servizi Sanitari per il cittadino</title>
    <meta name="description" content="Sistema di Servizi Sanitari per il cittadino, 2020, ProgWeb">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bungee">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lora">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/fonts/fontawesome5-overrides.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Article-Clean.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Features-Clean.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Footer-Basic.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Footer-Clean.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Header-Blue--Sticky-Header--Smooth-Scroll.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Header-Blue.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Header-Dark.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Highlight-Phone.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Team-Boxed.css">
</head>

<body id="page-top">
<div id="wrapper">
    <div class="d-flex flex-column" id="content-wrapper">
        <div id="content">
            <nav class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
                <div class="container-fluid"><button class="btn btn-link d-md-none rounded-circle mr-3" id="sidebarToggleTop" type="button"><i class="fas fa-bars"></i></button>
                    <ul class="nav navbar-nav flex-nowrap ml-auto">
                        <li class="nav-item dropdown d-sm-none no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#"><i class="fas fa-search"></i></a>
                            <div class="dropdown-menu dropdown-menu-right p-3 animated--grow-in" role="menu" aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto navbar-search w-100">
                                    <div class="input-group"><input class="bg-light form-control border-0 small" type="text" placeholder="Search for ...">
                                        <div class="input-group-append"><button class="btn btn-primary py-0" type="button"><i class="fas fa-search"></i></button></div>
                                    </div>
                                </form>
                            </div>
                        </li>
                        <li class="nav-item dropdown no-arrow" role="presentation">
                            <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#"><span class="d-none d-lg-inline mr-2 text-gray-600 small"><%=utente.getPaziente().getNome()%> <%=utente.getPaziente().getCognome()%></span><img class="border rounded-circle img-profile" src="avatar"></a>
                                <div
                                        class="dropdown-menu shadow dropdown-menu-right animated--grow-in" role="menu"><a class="dropdown-item" role="presentation" href="logout"><i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Logout</a></div>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="container-fluid">
                <h3 class="text-center text-dark mb-1">Servizi Disponibili</h3>
            </div>
        </div>
    </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a></div>
<div class="team-boxed">
    <div class="container">
        <div class="intro"></div>
        <div class="row people">
            <div class="col-md-6 col-lg-4 item">
                <div class="box" style="background-color: rgb(199,228,255);"><img class="rounded-circle" src="avatar">
                    <h3 class="name">Cittadino</h3>
                    <p class="description">Servizi per il cittadino</p><a href="sispaz/profilocittadino" class="btn btn-primary">Entra</a></div>
            </div>
 <% if (utente.isMedico()){
     out.println("<div class=\"col-md-6 col-lg-4 item\">\n" +
             "                <div class=\"box\" style=\"background-color: rgb(255,214,214);\"><img class=\"rounded-circle\" src=\"assets/img/medico.jpg\">\n" +
             "                    <h3 class=\"name\">Medico</h3>\n" +
             "                    <p class=\"description\">Servizi per il medico di base</p><a href=\"sismed/profilomedico\" style=\"background-color: rgb(223,104,78);\" class=\"btn btn-primary\">Entra</a></div>\n"+
             "            </div>");
 }%>
<% if (utente.isAdmin()) {
out.println("            <div class=\"col-md-6 col-lg-4 item\">\n" +
        "                <div class=\"box\" style=\"background-color: rgb(224,255,205);\"><img class=\"rounded-circle\" src=\"assets/img/admin.jpg\">\n" +
        "                    <h3 class=\"name\">Amministratore</h3>\n" +
        "                    <p class=\"description\">Servizi amministrativi</p><a href=\"sissan/profiloadmin\" style=\"background-color: rgb(116,223,78);\" class=\"btn btn-primary\">Entra</a></div>\n"+
            "            </div>");
}
%>
        </div>
    </div>
</div>
<div class="footer-clean">
    <footer>
        <div class="container">
            <div class="row justify-content-center"><span>Copyright © Servizi Sanitari per il cittadino 2020</span></div>
        </div>
    </footer>
</div>
<script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/Header-Blue--Sticky-Header--Smooth-Scroll-1.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/Header-Blue--Sticky-Header--Smooth-Scroll.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/theme.js"></script>
</body>

</html>