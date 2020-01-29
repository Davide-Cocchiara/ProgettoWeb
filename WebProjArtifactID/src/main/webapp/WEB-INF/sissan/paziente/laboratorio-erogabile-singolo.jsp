<%@ page import="unitn.progweb.cocchiara.model.Prescrizione" %>
<%@ page import="unitn.progweb.cocchiara.model.Pagamento" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Prescrizione Erogabile - Servizi Sanitari per il cittadino</title>
    <meta name="description" content="Sistema di Servizi Sanitari per il cittadino, 2020, ProgWeb">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
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
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/css/Header-Blue--Sticky-Header--Smooth-Scroll.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Header-Blue.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Header-Dark.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Highlight-Phone.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Team-Boxed.css">
    <script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/Header-Blue--Sticky-Header--Smooth-Scroll-1.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/Header-Blue--Sticky-Header--Smooth-Scroll.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/theme.js"></script>
</head>

<body id="page-top">
<div id="wrapper">
    <jsp:include page="/WEB-INF/sissan/paziente/sidebar.jsp"/>
    <div class="d-flex flex-column" id="content-wrapper">
        <div id="content">
            <jsp:include page="/WEB-INF/sissan/header.jsp"/>
            <div class="container-fluid">

                <div class="card shadow" id="toprint">
                    <div class="card-header d-inline-flex" style="height: 55px;">
                        <p class="text-center d-xl-flex justify-content-center align-items-center align-content-center my-auto justify-content-xl-center align-items-xl-center dataTables_info"
                           id="dataTable_info" role="status" aria-live="polite" style="font-size: 24px;"><strong>Prescrizione
                            Erogabile</strong></p>
                        <a id="stampabutton"
                           class="d-inline-flex float-right d-xl-flex flex-row-reverse justify-content-center align-items-center align-self-center ml-auto justify-content-lg-center align-items-lg-center justify-content-xl-center align-items-xl-center"
                           href="#" style="width: 40;height: 23px;">Stampa<i
                                class="fas fa-print border rounded d-inline-flex align-items-lg-center justify-content-xl-center align-items-xl-center"
                                style="height: 0px;"></i></a></div>
                    <div class="card-body">
                        <span class="d-inline-flex d-lg-inline mr-2 text-gray-600 small"
                              style="font-size: 20px;"><strong>Data Rilascio</strong></span><span
                            class="border rounded d-inline-flex float-right mr-2 text-gray-600 small"
                            style="font-size: 20px;">${requestScope.prescrizioneerogabile.getDatarilascio()}</span>
                    </div>
                    <div
                            class="card-body"><span class="d-inline-flex d-lg-inline mr-2 text-gray-600 small"
                                                    style="font-size: 20px;"><strong>Data Evasione</strong></span><span
                            class="border rounded d-inline-flex float-right mr-2 text-gray-600 small"
                            style="font-size: 20px;">${requestScope.prescrizioneerogabile.getDataevasione()}</span>
                    </div>
                    <div
                            class="card-body"><span class="d-inline-flex d-lg-inline mr-2 text-gray-600 small"
                                                    style="font-size: 20px;"><strong>Denominazione</strong></span><span
                            class="border rounded d-inline-flex float-right d-lg-inline mr-2 text-gray-600 small"
                            style="font-size: 20px;">${requestScope.prescrizioneerogabile.getPrestazione()}</span></div>
                    <div
                            class="card-body"><span class="d-inline-flex d-lg-inline mr-2 text-gray-600 small"
                                                    style="font-size: 20px;"><strong>Medico</strong></span><span
                            class="border rounded d-inline-flex float-right d-lg-inline mr-2 text-gray-600 small"
                            style="font-size: 20px;">${requestScope.prescrizioneerogabile.getMedico()}</span></div>
                    <div
                            class="card-body"><span class="d-inline-flex d-lg-inline mr-2 text-gray-600 small"
                                                    style="font-size: 20px;"><strong>Provincia</strong></span><span
                            class="border rounded d-inline-flex float-right d-lg-inline mr-2 text-gray-600 small"
                            style="font-size: 20px;">${requestScope.prescrizioneerogabile.getProvincia()}</span></div>
                    <div
                            class="card-body"><span class="d-inline-flex d-lg-inline mr-2 text-gray-600 small"
                                                    style="font-size: 20px;"><strong>Costo</strong></span><span
                            class="border rounded d-inline-flex float-right d-lg-inline mr-2 text-gray-600 small"
                            style="font-size: 20px;">${requestScope.prescrizioneerogabile.getCosto()}</span></div>

                    <div class="card-body"><span class="d-inline-flex d-lg-inline mr-2 text-gray-600 small"
                                                 style="font-size: 20px;margin-right: 2px;"><strong>Relazione</strong></span>
                    </div>
                    <form id="formrelazione" style="margin:0px;">
                        <div class="card-body d-inline-flex flex-grow-1 flex-shrink-1"
                             style="height: auto;width:100%;padding-top: 10px;pa"><textarea
                                class="border rounded flex-grow-1" style="width: 100%;height: 200px;" wrap="hard"
                                name="relazione" id="inputarea"></textarea></div>
                        <div class="card-footer" style="padding-bottom:50px;">

                                <button type="submit" id="completabtn"
                                        class="btn btn-primary text-right border rounded float-right d-xl-flex">
                                    Completa Referto&nbsp;
                                </button>

                            <div id="requestsuccess">
                            </div>
                        </div>
                    </form>
                </div>

            </div>
        </div>
        <footer class="bg-white sticky-footer">
            <div class="container my-auto">
                <div class="text-center my-auto copyright">
                    <span>Copyright © Servizi Sanitari per il cittadino 2020</span></div>
            </div>
        </footer>
    </div>
    <a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a></div>

<script>

    var pagaurl = "<% if (request.getAttribute("prescrizioneerogabile") != null ) {
                                    Prescrizione pr = (Prescrizione) request.getAttribute("prescrizioneerogabile");
                                    out.print(request.getContextPath()+ "/sissan/paziente/erogalaboratorio?idprescrizione=" + pr.getIdprescrizione());
                                }
                            %>";

    $(document).ready(function () {
        $("#formrelazione").submit(function (e) {
            e.preventDefault();

            $.ajax({
                type:"POST",
                url: pagaurl,
                data: $(this).serialize(),
                success: function (result) {
                    if (result === "true") {

                        $("#requestsuccess").html("<label for=\"signature\" style=\"color: rgb(0,220,0);\"><strong>Successo: </strong>Prestazione erogata con successo!<br></label>\n");
                        $("#completabtn").remove();
                        $("#inputarea").prop('readonly',true);
                    } else {
                        $("#requestsuccess").html("<label for=\"signature\" style=\"color: rgb(255,0,0);\"><strong>Errore: </strong>Prestazione non erogata!<br></label>\n");
                    }
                }
            });
        });
    });
</script>
<script src="<%=request.getContextPath()%>/js/printThis.js"></script>
<script>$('#stampabutton').click(function () {
    $('#toprint').printThis({});
});</script>
</body>

</html>