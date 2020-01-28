<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- TODO BRO: Implement  -->

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Lista Prestazioni - Servizi Sanitari per il cittadino</title>
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
        <nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0" style="background-color: rgb(224,255,205);background-image: linear-gradient(180deg, rgba(164,205,155,1) 10%, rgba(100,170,120,1) 100%);">
            <div class="container-fluid d-flex flex-column p-0">
                <a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="#">
                    <div class="sidebar-brand-icon rotate-n-15"><i class="fa fa-plus-square"></i></div>
                    <div class="sidebar-brand-text mx-3"><span class="text-center">Servizi <br>Amministrativi<br></span></div>
                </a>
                <hr class="sidebar-divider my-0">
                <ul class="nav navbar-nav text-light" id="accordionSidebar">
                    <li class="nav-item" role="presentation"><a class="nav-link text-center" href="<%=request.getContextPath()%>/sissan/pazientilista"><i class="fas fa-personas" style="font-size: 20px;"></i><span style="font-size: 20px;">Pazienti</span></a></li>
                </ul>
                <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
            </div>
        </nav>
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
                <jsp:include page="header.jsp" />
                <div class="container-fluid">
                    <div>
                        <ul class="nav nav-tabs">
                            <li class="nav-item"><a class="nav-link active" role="tab" data-toggle="tab" href="#tab-1">Eroga
                                Farmaco</a></li>
                            <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#tab-2">Eroga
                                Esame</a></li>
                            <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#tab-3">Eroga
                                Esame</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" role="tabpanel" id="tab-1">
                                <div class="card shadow">
                                    <div class="card-header d-inline-flex" style="height: 55px;">
                                        <p class="text-center d-xl-flex justify-content-center align-items-center align-content-center my-auto justify-content-xl-center align-items-xl-center dataTables_info"
                                           id="dataTable_info" role="status" aria-live="polite" style="font-size: 24px;">
                                            <strong>Esami Specialistici Disponibili</strong></p>
                                        <a id="stampabutton_spec"
                                           class="d-inline-flex float-right d-xl-flex flex-row-reverse justify-content-center align-items-center align-self-center ml-auto justify-content-lg-center align-items-lg-center justify-content-xl-center align-items-xl-center"
                                           href="#" style="width: 40;height: 23px;">Stampa<i
                                                class="fas fa-print border rounded d-inline-flex align-items-lg-center justify-content-xl-center align-items-xl-center"
                                                style="height: 0px;"></i></a>
                                    </div>
                                    <div class="card-body">
                                        <table class="table dataTable my-0" id="specialistici" style="width: 100%;"></table>
                                    </div>

                                </div>
                            </div>
                            <div class="tab-pane " role="tabpanel" id="tab-2">
                                <div class="card shadow">
                                    <div class="card-header d-inline-flex" style="height: 55px;">
                                        <p class="text-center d-xl-flex justify-content-center align-items-center align-content-center my-auto justify-content-xl-center align-items-xl-center dataTables_info"
                                           id="dataTable_info" role="status" aria-live="polite" style="font-size: 24px;">
                                            <strong>Esami Laboratorio Disponibili</strong></p>
                                        <a id="stampabutton_lab"
                                           class="d-inline-flex float-right d-xl-flex flex-row-reverse justify-content-center align-items-center align-self-center ml-auto justify-content-lg-center align-items-lg-center justify-content-xl-center align-items-xl-center"
                                           href="#" style="width: 40;height: 23px;">Stampa<i
                                                class="fas fa-print border rounded d-inline-flex align-items-lg-center justify-content-xl-center align-items-xl-center"
                                                style="height: 0px;"></i></a>
                                    </div>
                                    <div class="card-body">
                                        <table class="table dataTable my-0" id="laboratorio" style="width: 100%;"></table>
                                    </div>

                                </div>
                            </div>
                            <div class="tab-pane " role="tabpanel" id="tab-3">
                                <div class="card shadow">
                                    <div class="card-header d-inline-flex" style="height: 55px;">
                                        <p class="text-center d-xl-flex justify-content-center align-items-center align-content-center my-auto justify-content-xl-center align-items-xl-center dataTables_info"
                                           id="dataTable_info" role="status" aria-live="polite" style="font-size: 24px;">
                                            <strong>Farmaci Disponibili</strong></p>
                                        <a id="stampabutton_farm"
                                           class="d-inline-flex float-right d-xl-flex flex-row-reverse justify-content-center align-items-center align-self-center ml-auto justify-content-lg-center align-items-lg-center justify-content-xl-center align-items-xl-center"
                                           href="#" style="width: 40;height: 23px;">Stampa<i
                                                class="fas fa-print border rounded d-inline-flex align-items-lg-center justify-content-xl-center align-items-xl-center"
                                                style="height: 0px;"></i></a>
                                    </div>
                                    <div class="card-body">
                                        <table class="table dataTable my-0" id="farmaco" style="width: 100%;"></table>
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

    <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>


    <script src="<%=request.getContextPath()%>/js/tables.js"></script>
    <script>
        $( document ).ready(function() {
            tables_listadisponibiliesami("#specialistici","<%=request.getContextPath()%>/sissan/getesamispecdisponibili");
            tables_listadisponibiliesami("#laboratorio","<%=request.getContextPath()%>/sissan/getesamilabdisponibili");
            tables_listadisponibiliesami("#farmaci","<%=request.getContextPath()%>/sissan/getfarmacilabdisponibili");
        });
    </script>
    <script src="<%=request.getContextPath()%>/js/printThis.js"></script>

    <script>
        $('#stampabutton_spec').click(function(){
            $('#specialistici').printThis({
            });
        });
        $('#stampabutton_lab').click(function(){
            $('#laboratorio').printThis({
            });
        });
        $('#stampabutton_farm').click(function(){
            $('#farmaci').printThis({
            });
        });

    </script>
</body>

</html>