<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Profile - Servizi Sanitari per il cittadino</title>
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
        <nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0" style="background-color: rgb(109,32,32);background-image: linear-gradient(180deg, rgba(109,32,32,1) 10%, rgba(60,18,18,1) 100%);">
            <div class="container-fluid d-flex flex-column p-0">
                <a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="#">
                    <div class="sidebar-brand-icon rotate-n-15"><i class="fa fa-plus-square"></i></div>
                    <div class="sidebar-brand-text mx-3"><span>Servizi Medico<br></span></div>
                </a>
                <hr class="sidebar-divider my-0">
                <ul class="nav navbar-nav text-light" id="accordionSidebar">
                    <li class="nav-item" role="presentation"></li>
                </ul>
                <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
            </div>
        </nav>
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
                <jsp:include page="header.jsp" />
            <div class="container-fluid">
                <h3 class="text-dark mb-4">Profilo Medico</h3>
                <div class="row mb-3">
                    <div class="col-lg-4">
                        <div class="card mb-3">
                            <div class="card-body text-center shadow"><img class="rounded-circle mb-3 mt-4" src="<%=request.getContextPath()%>/avatar" width="160" height="160">
                                <div class="mb-3"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <div class="row">
                            <div class="col">
                                <div class="card shadow mb-3">
                                    <div class="card-header py-3">
                                        <p class="text-primary m-0 font-weight-bold">Dati Anagrafici</p>
                                    </div>
                                    <div class="card-body">
                                        <form>
                                            <div class="form-row">
                                                <div class="col">
                                                    <div class="form-group"><label for="username"><strong>Nome</strong></label><input class="form-control" value="${sessionScope.utente.getPaziente().getNome()}" type="text" name="name" placeholder="nome" readonly=""></div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-group"><label for="email"><strong>Cognome</strong></label><input class="form-control" value="${sessionScope.utente.getPaziente().getCognome()}" type="email" placeholder="cognome" name="surname" readonly=""></div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="card shadow">
                                    <div class="card-header py-3">
                                        <p class="text-primary m-0 font-weight-bold">Informazioni Clinica</p>
                                    </div>
                                    <div class="card-body">
                                        <form method="post" action="<%=request.getContextPath()%>/sismed/changeinformazioniclinica">
                                            <div class="form-group"><label for="address"><strong>Indirizzo Clinica</strong></label><input value="${sessionScope.utente.getMedico().getClinica()}" class="form-control" type="text" placeholder="Via Lello 33a, CN " name="indirizzo"></div>
                                            <div class="form-group"><label for="address"><strong>Telefono Clinica</strong></label><input value="${sessionScope.utente.getMedico().getTelefonoclinica()}" class="form-control" type="text" placeholder="092799923312" name="telefono"></div>
                                            <div class="form-group"><button class="btn btn-primary btn-sm" type="submit">Salva Informazioni Clinica</button></div>
                                        </form>
                                        <%
                                            if (request.getParameter("changedinfo") != null && request.getParameter("changedinfo").equals("true")) {
                                                out.println("<label for=\"signature\" style=\"color: rgb(0,220,0);\"><strong>Successo: </strong>Informazioni cambiate con successo!<br></label>\n");
                                            }
                                            if (request.getParameter("changedinfo") != null && request.getParameter("changedinfo").equals("false")) {
                                                out.println("<label for=\"signature\" style=\"color: rgb(255,0,0);\"><strong>Errore: </strong>Informazioni non cambiate. Verificare che l'indirizzo e/o il telefono inserito sia valido.<br></label>\n");
                                            }
                                        %>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer class="bg-white sticky-footer">
            <div class="container my-auto">
                <div class="text-center my-auto copyright"><span>Copyright © Servizi Sanitari per il cittadino 2020</span></div>
            </div>
        </footer>
    </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a></div>
    <script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/Header-Blue--Sticky-Header--Smooth-Scroll-1.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/Header-Blue--Sticky-Header--Smooth-Scroll.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/theme.js"></script>
</body>

</html>