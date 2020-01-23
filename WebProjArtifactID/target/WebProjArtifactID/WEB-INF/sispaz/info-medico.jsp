<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Info Medico - Servizi Sanitari per il cittadino</title>
    <meta name="description" content="Sistema di Servizi Sanitari per il cittadino, 2020, ProgWeb">
    <link rel="stylesheet" href="../assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bungee">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lora">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="../assets/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="../assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="../assets/fonts/fontawesome5-overrides.min.css">
    <link rel="stylesheet" href="../assets/css/Article-Clean.css">
    <link rel="stylesheet" href="../assets/css/Features-Clean.css">
    <link rel="stylesheet" href="../assets/css/Footer-Basic.css">
    <link rel="stylesheet" href="../assets/css/Footer-Clean.css">
    <link rel="stylesheet" href="../assets/css/Header-Blue--Sticky-Header--Smooth-Scroll.css">
    <link rel="stylesheet" href="../assets/css/Header-Blue.css">
    <link rel="stylesheet" href="../assets/css/Header-Dark.css">
    <link rel="stylesheet" href="../assets/css/Highlight-Phone.css">
    <link rel="stylesheet" href="../assets/css/Team-Boxed.css">
</head>

<body id="page-top">
    <div id="wrapper">
        <jsp:include page="sidebar.jsp" />
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
                <jsp:include page="header.jsp" />
            <div class="container-fluid">
                <h3 class="text-dark mb-4">Info Medico</h3>
                <div class="row mb-3">
                    <div class="col-lg-4">
                        <div class="card mb-3">
                            <div class="card-body text-center shadow"><img class="rounded-circle mb-3 mt-4" src="../assets/img/medico.jpg" width="160" height="160">
                                <div class="mb-3"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <div class="row mb-3 d-none">
                            <div class="col">
                                <div class="card text-white bg-primary shadow">
                                    <div class="card-body">
                                        <div class="row mb-2">
                                            <div class="col">
                                                <p class="m-0">Peformance</p>
                                                <p class="m-0"><strong>65.2%</strong></p>
                                            </div>
                                            <div class="col-auto"><i class="fas fa-rocket fa-2x"></i></div>
                                        </div>
                                        <p class="text-white-50 small m-0"><i class="fas fa-arrow-up"></i>&nbsp;5% since last month</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="card text-white bg-success shadow">
                                    <div class="card-body">
                                        <div class="row mb-2">
                                            <div class="col">
                                                <p class="m-0">Peformance</p>
                                                <p class="m-0"><strong>65.2%</strong></p>
                                            </div>
                                            <div class="col-auto"><i class="fas fa-rocket fa-2x"></i></div>
                                        </div>
                                        <p class="text-white-50 small m-0"><i class="fas fa-arrow-up"></i>&nbsp;5% since last month</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="card shadow mb-3">
                                    <div class="card-header py-3">
                                        <p class="text-primary m-0 font-weight-bold">Dati</p>
                                    </div>
                                    <div class="card-body">
                                        <form>
                                            <div class="form-row">
                                                <div class="col">
                                                    <div class="form-group"><label for="username"><strong>Nome</strong></label><input value="${requestScope.medicoassegnato.getPaziente().getNome()}" class="form-control" type="text" name="name" placeholder="NESSUN MEDICO ASSEGNATO" readonly=""></div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-group"><label for="email"><strong>Cognome</strong></label><input value="${requestScope.medicoassegnato.getPaziente().getCognome()}"class="form-control" type="email" placeholder="NESSUN MEDICO ASSEGNATO" name="surname" readonly=""></div>
                                                </div>
                                            </div>
                                            <div class="form-group"><label for="address"><strong>Indirizzo Clinica</strong></label><input value="${requestScope.medicoassegnato.getMedico().getClinica()}" class="form-control" type="text" placeholder="NESSUN MEDICO ASSEGNATO" name="indirizzoClinica" readonly=""></div>
                                            <div class="form-group"><label for="address"><strong>Telefono Clinica</strong></label><input value="${requestScope.medicoassegnato.getMedico().getTelefonoclinica()}" class="form-control" type="text" placeholder="NESSUN MEDICO ASSEGNATO" name="telefonoclinica" readonly=""></div>
                                        </form>
                                        <% if(request.getAttribute("medicoassegnato")==null) {
                                            out.println("<label for=\"signature\" style=\"color: rgb(255,0,0);\"><strong>Attenzione: </strong>Nessun medico assegnato!<br></label>\n");
                                        } %>
                                    </div>
                                </div>
                                <div class="card shadow">
                                    <div class="card-header py-3">
                                        <p class="text-primary m-0 font-weight-bold">Cambio Medico di Base</p>
                                    </div>
                                    <div class="card-body">
                                        <form method="post" action="sispaz/changemedico">
                                            <div class="form-group"><label for="address"><strong>Medici Disponibili nella provincia di residenza</strong></label><select class="form-control"><optgroup label="Medici"><option value="12" selected="">Dr. Bob Bobo</option><option value="13">Dr. Lella Lul</option><option value="14">Dr. Kaka Keke</option></optgroup></select></div>
                                            <div
                                                class="form-group"><button class="btn btn-primary btn-sm" type="submit">Cambia Medico di Base</button></div>
                                    </form>
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
    <script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="../assets/js/Header-Blue--Sticky-Header--Smooth-Scroll-1.js"></script>
    <script src="../assets/js/Header-Blue--Sticky-Header--Smooth-Scroll.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
    <script src="../assets/js/theme.js"></script>
</body>

</html>