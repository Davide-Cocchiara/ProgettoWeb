<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Table - Servizi Sanitari per il cittadino</title>
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
        <jsp:include page="/WEB-INF/sismed/paziente/sidebar.jsp" />
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
                <jsp:include page="/WEB-INF/sismed/header.jsp" />
            <div class="container-fluid">
                <div>
                    <ul class="nav nav-tabs">
                        <li class="nav-item"><a class="nav-link active" role="tab" data-toggle="tab" href="#tab-1">Ricetta Esame</a></li>
                        <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#tab-2">Ricetta Farmaco</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" role="tabpanel" id="tab-1">
                            <div class="card shadow">
                                <div class="card-header d-inline-flex" style="height: 55px;">
                                    <p class="text-center d-xl-flex justify-content-center align-items-center align-content-center my-auto justify-content-xl-center align-items-xl-center dataTables_info" id="dataTable_info" role="status" aria-live="polite"
                                        style="font-size: 24px;"><strong>Prescrizione</strong></p>
                                </div>
                                <div class="card-body" style="padding: 1;padding-top: 5;padding-right: 5;padding-bottom: 5;padding-left: 5;height: 50px;"><span class="d-inline-flex d-lg-inline mr-2 text-gray-600 small" style="font-size: 20px;"><strong>Data Rilascio</strong></span><span class="border rounded d-inline-flex float-right mr-2 text-gray-600 small" style="font-size: 20px;">04/01/2020 (New)</span></div>
                                <div
                                    class="card-body" style="padding-bottom: 30px;height: 70px;"><span class="d-inline-flex d-lg-inline mr-2 text-gray-600 small" style="font-size: 20px;"><strong>Esame Prescritto</strong></span><select class="border rounded float-right" style="font-size: 20px;"><optgroup label="Esami"><option value="" selected="">---</option><option value="14">Controllo Naso</option><option value="2">Controllo Piedi</option></optgroup></select></div>
                            <div
                                class="card-footer"><button class="btn btn-primary text-right border rounded float-right d-xl-flex" type="button">Completa Prescrizione</button></div>
                    </div>
                </div>
                <div class="tab-pane" role="tabpanel" id="tab-2">
                    <div class="card shadow">
                        <div class="card-header d-inline-flex" style="height: 55px;">
                            <p class="text-center d-xl-flex justify-content-center align-items-center align-content-center my-auto justify-content-xl-center align-items-xl-center dataTables_info" id="dataTable_info" role="status" aria-live="polite" style="font-size: 24px;"><strong>Prescrizione</strong></p>
                        </div>
                        <div class="card-body" style="padding: 1;padding-top: 5;padding-right: 5;padding-bottom: 5;padding-left: 5;height: 50px;"><span class="d-inline-flex d-lg-inline mr-2 text-gray-600 small" style="font-size: 20px;"><strong>Data Rilascio</strong></span><span class="border rounded d-inline-flex float-right mr-2 text-gray-600 small" style="font-size: 20px;">04/01/2020 (New)</span></div>
                        <div
                            class="card-body" style="padding-bottom: 30px;height: 70px;"><span class="d-inline-flex d-lg-inline mr-2 text-gray-600 small" style="font-size: 20px;"><strong>Farmaco Prescritto</strong></span><select class="border rounded float-right" style="font-size: 20px;"><optgroup label="Farmaci"><option value="0">---</option><option value="12" selected="">Boccino 20mg</option><option value="13">Doccia 20ml</option></optgroup></select></div>
                    <div
                        class="card-footer"><button class="btn btn-primary text-right border rounded float-right d-xl-flex" type="button">Completa Prescrizione</button></div>
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