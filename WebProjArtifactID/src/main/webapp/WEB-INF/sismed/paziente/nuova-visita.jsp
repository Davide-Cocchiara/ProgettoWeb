
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Nuova Visita - Servizi Sanitari per il cittadino</title>
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
                <div class="card shadow">
                    <form method="post" action="<%=request.getContextPath()%>/sismed/paziente/eroganuovavisita">
                    <div class="card-header d-inline-flex" style="height: 55px;width:100%">
                        <p class="text-center d-xl-flex justify-content-center align-items-center align-content-center my-auto justify-content-xl-center align-items-xl-center dataTables_info" id="dataTable_info" role="status" aria-live="polite" style="font-size: 24px;"><strong>Referto</strong></p>
                    </div>
                    <div class="card-body" ><span class="d-inline-flex d-lg-inline mr-2 text-gray-600 small" style="font-size: 20px;"><strong>Data</strong></span><span class="border rounded d-inline-flex float-right d-lg-inline mr-2 text-gray-600 small" style="font-size: 20px;"> <%= LocalDate.now().toString()%></span></div>
                    <div
                        class="card-body"><span class="d-inline-flex d-lg-inline mr-2 text-gray-600 small" style="font-size: 20px;"><strong>Prestazione</strong></span>
                        <select class="form-control" name="selectedprestazione">
                            <optgroup label="Prestazione">
                        ${requestScope.prestazionedropdown}
                            </optgroup>
                        </select>
                    </div>
                <div class="card-body"><span class="d-inline-flex d-lg-inline mr-2 text-gray-600 small" style="font-size: 20px;margin-right: 2px;"><strong>Relazione</strong></span></div>
            <div class="card-body d-inline-flex flex-grow-1 flex-shrink-1" style="height: auto;width:100%;padding-top: 10px;"><textarea class="border rounded flex-grow-1" style="width: 100%;height: 200px;" wrap="hard" name="relazione"></textarea></div>
            <div class="card-body"><span class="d-inline-flex d-lg-inline mr-2 text-gray-600 small" style="font-size: 20px;margin-top: 8px;"><strong>Pagamento Effettuato</strong></span><select name="pagato" class="border rounded float-right" style="font-size: 20px;"><option value="0" selected="">No</option><option value="1">Sì</option></select></div>
        <div
            class="card-body"><button class="btn btn-primary text-right border rounded float-right d-xl-flex" type="submit">Completa referto</button>
            <%
                if (request.getParameter("erogata") != null && request.getParameter("erogata").equals("true")) {
                    out.println("<label for=\"signature\" style=\"color: rgb(0,220,0);\"><strong>Successo: </strong>Visita erogata con successo!<br></label>\n");
                }
                if (request.getParameter("erogata") != null && request.getParameter("erogata").equals("false")) {
                    out.println("<label for=\"signature\" style=\"color: rgb(255,0,0);\"><strong>Errore: </strong>Visita non erogata!<br></label>\n");
                }
                if (request.getParameter("erogata") != null && request.getParameter("erogata").equals("nopagamento")) {
                    out.println("<label for=\"signature\" style=\"color: rgb(255,255,0);\"><strong>Errore: </strong>Visita erogata ma errore nel pagamento.<br></label>\n");
                }
            %>
        </div>
                </form>

                    <div class="card-footer"></div>
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