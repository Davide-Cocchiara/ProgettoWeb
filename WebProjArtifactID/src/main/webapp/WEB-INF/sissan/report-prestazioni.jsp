<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- TODO BRO: Aggiungi i due tipi di lista in tables.js. Esempio applicato in lista pazienti medico  Copia le librerie src giuste-->
<!-- TODO BRO: Come punti per ottenere i dati /sissan/getesamierogati e /sissan/getfarmacierogati -->

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Report Prestazioni - Servizi Sanitari per il cittadino</title>
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
                    <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#tab-1">Farmaci Erogati</a></li>
                    <li class="nav-item"><a class="nav-link active" role="tab" data-toggle="tab" href="#tab-2">Esami Erogati</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane" role="tabpanel" id="tab-1">
                        <div class="card shadow">
                            <div class="card-body" style="padding: 20px;padding-top: 10px;padding-bottom: 10px;"><span class="d-inline-flex d-lg-inline mr-2 text-gray-600 small" style="font-size: 20px;"><strong>Data</strong></span><a class="card-link" href="#"><i class="fa fa-search border rounded d-inline-flex float-right align-content-center align-self-center m-auto justify-content-xl-center align-items-xl-center" style="font-size: 20px;padding: 3px;"></i></a>
                                <input
                                    class="border rounded d-inline-flex float-right" type="date" value="0"></div>
                            <div class="card-body border rounded" style="padding-top: 10px;">
                                <div class="row">
                                    <div class="col-md-6 text-nowrap">
                                        <div id="dataTable_length" class="dataTables_length" aria-controls="dataTable"><label>Show&nbsp;<select class="form-control form-control-sm custom-select custom-select-sm"><option value="10" selected="">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select>&nbsp;</label></div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="text-md-right dataTables_filter" id="dataTable_filter"><label><input type="search" class="form-control form-control-sm" aria-controls="dataTable" placeholder="Search"></label></div>
                                    </div>
                                </div>
                                <div class="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">
                                    <table class="table dataTable my-0" id="dataTable">
                                        <thead>
                                            <tr>
                                                <th style="width: 144px;">Data</th>
                                                <th>Farmaco</th>
                                                <th>Medico</th>
                                                <th>Paziente</th>
                                                <th>Ticket</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>02/02/03 22:33</td>
                                                <td>Buffetto</td>
                                                <td>Dr. Eco Ive</td>
                                                <td>NNGGG21B30R213N<br></td>
                                                <td>123,1€</td>
                                            </tr>
                                            <tr></tr>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <td><strong>Data</strong><br></td>
                                                <td><strong>Farmaco</strong></td>
                                                <td><strong>Medico</strong></td>
                                                <td><strong>Paziente</strong></td>
                                                <td><strong>Ticket</strong><br></td>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 align-self-center">
                                        <p id="dataTable_info" class="dataTables_info" role="status" aria-live="polite">Showing 1 to 1 of 1</p>
                                    </div>
                                    <div class="col-md-6">
                                        <nav class="d-lg-flex justify-content-lg-end dataTables_paginate paging_simple_numbers">
                                            <ul class="pagination">
                                                <li class="page-item disabled"><a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
                                                <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                                <li class="page-item"><a class="page-link" href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer"><button class="btn btn-primary text-right border rounded float-right d-xl-flex" type="button">Scarica Report .XLS</button></div>
                        </div>
                    </div>
                    <div class="tab-pane active" role="tabpanel" id="tab-2">
                        <div class="card shadow">
                            <div class="card-body" style="padding: 20px;padding-top: 10px;padding-bottom: 10px;"><span class="d-inline-flex d-lg-inline mr-2 text-gray-600 small" style="font-size: 20px;"><strong>Data</strong></span><a class="card-link" href="#"><i class="fa fa-search border rounded d-inline-flex float-right align-content-center align-self-center m-auto justify-content-xl-center align-items-xl-center" style="font-size: 20px;padding: 3px;"></i></a>
                                <input
                                    class="border rounded d-inline-flex float-right" type="date" value="0"></div>
                            <div class="card-body border rounded" style="padding-top: 10px;">
                                <div class="row">
                                    <div class="col-md-6 text-nowrap">
                                        <div id="dataTable_length" class="dataTables_length" aria-controls="dataTable"><label>Show&nbsp;<select class="form-control form-control-sm custom-select custom-select-sm"><option value="10" selected="">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select>&nbsp;</label></div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="text-md-right dataTables_filter" id="dataTable_filter"><label><input type="search" class="form-control form-control-sm" aria-controls="dataTable" placeholder="Search"></label></div>
                                    </div>
                                </div>
                                <div class="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">
                                    <table class="table dataTable my-0" id="dataTable">
                                        <thead>
                                            <tr>
                                                <th style="width: 144px;">Data</th>
                                                <th>Esame</th>
                                                <th>Medico</th>
                                                <th>Paziente</th>
                                                <th>Ticket</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>02/02/03 22:33</td>
                                                <td>Buffetto</td>
                                                <td>Dr. Eco Ive</td>
                                                <td>NNGGG21B30R213N<br></td>
                                                <td>123,1€</td>
                                            </tr>
                                            <tr></tr>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <td><strong>Data</strong><br></td>
                                                <td><strong>Esame</strong></td>
                                                <td><strong>Medico</strong></td>
                                                <td><strong>Paziente</strong></td>
                                                <td><strong>Ticket</strong><br></td>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 align-self-center">
                                        <p id="dataTable_info" class="dataTables_info" role="status" aria-live="polite">Showing 1 to 1 of 1</p>
                                    </div>
                                    <div class="col-md-6">
                                        <nav class="d-lg-flex justify-content-lg-end dataTables_paginate paging_simple_numbers">
                                            <ul class="pagination">
                                                <li class="page-item disabled"><a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
                                                <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                                <li class="page-item"><a class="page-link" href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer"><button class="btn btn-primary text-right border rounded float-right d-xl-flex" type="button">Scarica Report .XLS</button></div>
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