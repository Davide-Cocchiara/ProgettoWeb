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
        <jsp:include page="<%=request.getContextPath()%>/sismed/paziente/sidebar.jsp" />
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
                <jsp:include page="<%=request.getContextPath()%>/sismed/header.jsp" />
            <div class="container-fluid">
                <h3 class="text-dark mb-4">Info Paziente</h3>
                <div class="row mb-3">
                    <div class="col-lg-4">
                        <div class="card mb-3">
                            <div class="card-body text-center shadow"><img class="rounded-circle mb-3 mt-4" src="<%=request.getContextPath()%>/assets/img/3.jpg" width="160" height="160">
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
                        <div class="card shadow mb-3">
                            <div class="card-header py-3">
                                <p class="text-primary m-0 font-weight-bold">Dati</p>
                            </div>
                            <div class="card-body">
                                <form>
                                    <div class="form-row">
                                        <div class="col">
                                            <div class="form-group"><label for="username"><strong>Nome</strong></label><input class="form-control" type="text" name="name" placeholder="nome" readonly="" value="${sessionScope.selectedpaziente.getNome()}"></div>
                                        </div>
                                        <div class="col">
                                            <div class="form-group"><label for="email"><strong>Cognome</strong></label><input class="form-control" type="email" placeholder="cognome" name="surname" readonly="" value="${sessionScope.selectedpaziente.getCognome()}"></div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col">
                                            <div class="form-group"><label for="first_name"><strong>Data di nascita</strong></label><input class="form-control" type="text" placeholder="0/0/0000" name="nascita" value="${sessionScope.selectedpaziente.etDatanascita()}" readonly=""></div>
                                        </div>
                                        <div class="col">
                                            <div class="form-group"><label for="last_name"><strong>Luogo di nascita</strong></label><input class="form-control" type="text" placeholder="Gubbio, PG" name="luogonascita" readonly="" value="${sessionScope.selectedpaziente.getLuogonascita()}"></div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col">
                                            <div class="form-group"><label for="first_name"><strong>Sesso</strong></label><input class="form-control" type="text" placeholder="Mischio" name="sesso" readonly="" value="${sessionScope.selectedpaziente.getSesso()}"></div>
                                        </div>
                                        <div class="col">
                                            <div class="form-group"><label for="first_name"><strong>Codice Fiscale</strong></label><input class="form-control" type="text" placeholder="CGNNMO00A01E256D" name="codicefiscale" readonly="" value="${sessionScope.selectedpaziente.getCodicefiscale()}"></div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="card shadow">
                            <div class="card-header py-3">
                                <p class="text-primary m-0 font-weight-bold">Contatti</p>
                            </div>
                            <div class="card-body">
                                <form>
                                    <div class="form-group"><label for="address"><strong>Mail</strong></label><input class="form-control" type="text" placeholder="example@address.com" name="email" readonly=""  value="${sessionScope.selectedpaziente.getEmail()}"></div>
                                    <div class="form-row">
                                        <div class="col">
                                            <div class="form-group"><label for="city"><strong>Indirizzo</strong></label><input class="form-control" type="text" placeholder="Via Gnocchi, 00" name="indirizzo" readonly="" value="${sessionScope.selectedpaziente.getIndirizzo()}"></div>
                                        </div>
                                        <div class="col">
                                            <div class="form-group"><label for="country"><strong>Provincia</strong></label><input class="form-control" type="text" placeholder="Gubbio, GB" name="indirizzo" readonly="" value="${sessionScope.selectedpaziente.getProvincia()}"></div>
                                        </div>
                                    </div>
                                </form>
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