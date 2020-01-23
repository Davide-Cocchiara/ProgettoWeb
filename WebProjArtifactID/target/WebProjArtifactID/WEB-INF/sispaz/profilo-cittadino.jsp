<%@ page import="java.util.Map" %>
<%@ page import="unitn.progweb.cocchiara.model.SistemaNazionale" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="unitn.progweb.cocchiara.model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Profilo Cittadino - Servizi Sanitari per il cittadino</title>
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
                <h3 class="text-dark mb-4">Profile</h3>
                <div class="row mb-3">
                    <div class="col-lg-4">
                        <div class="card mb-3">
                            <div class="card-body text-center shadow"><img class="rounded-circle mb-3 mt-4" src="<%=request.getContextPath()%>/avatar" width="160" height="160">
                                <form action="uploadavatar" method="post" enctype="multipart/form-data">
                                    <input class="btn btn-primary btn-sm" type="file" name="file" accept="image/x-png,image/gif,image/jpeg" />
                                    <input class="btn btn-primary btn-sm" type="submit" />
                                </form>
                                <%
                                    if (request.getParameter("failedupload") == null) {
                                    } else { // Errore generico
                                        out.println("<div style='color:red' class='text-center'>Upload fallito. Immagine non caricata.</div>");
                                    }
                                %>
                            </div>
                        </div>
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="text-primary font-weight-bold m-0">Password</h6>
                            </div>
                            <div class="card-body">
                                <form action = "<%=request.getContextPath()%>/sispaz/changepassword" method="post" oninput='newpassword.setCustomValidity(newpassword.value != pa2.value  ? "Le nuove password non corrispondono" : "")'>
                                <div class="form-group d-flex flex-column"><label for="address"><strong>Password&nbsp;Corrente</strong></label><input id="password" name="password" class="flex-column" type="password" required ></div>
                                <div class="form-group d-flex flex-column"><label for="address"><strong>Nuova Password</strong></label><input id="newpassword" name= "newpassword" type="password" required></div>
                                <div class="form-group d-flex flex-column"><label for="address"><strong>Ripeti Nuova Password</strong></label><input type="password" required name=pa2></div>
                                <div class="form-group"><button class="btn btn-primary btn-sm" type="submit">Aggiorna Password</button></div>
                                </form>
                                <%
                                    if (request.getParameter("newpassword") != null && request.getParameter("newpassword").equals("true")) {
                                    out.println("<label for=\"signature\" style=\"color: rgb(0,220,0);\"><strong>Successo: </strong>La password è stata cambiata!<br></label>\n");
                                    }
                                    if (request.getParameter("newpassword") != null && request.getParameter("newpassword").equals("false")) {
                                        out.println("<label for=\"signature\" style=\"color: rgb(255,0,0);\"><strong>Errore: </strong>Verificare di aver inserito la password corrente correttamente.\nLa nuova password deve essere lunga almeno 8 caratteri<br></label>\n");
                                    }
                                %>

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
                                        <p class="text-primary m-0 font-weight-bold">Dati Anagrafici</p>
                                    </div>
                                    <div class="card-body">
                                        <form>
                                            <div class="form-row">
                                                <div class="col">
                                                    <div class="form-group"><label for="username"><strong>Nome</strong></label><input class="form-control" type="text" name="name" placeholder="nome" readonly="" value="${sessionScope.utente.getPaziente().getNome()}"></div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-group"><label for="email"><strong>Cognome</strong></label><input class="form-control" type="email" placeholder="cognome" name="surname" readonly="" value="${sessionScope.utente.getPaziente().getCognome()}"></div>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="col">
                                                    <div class="form-group"><label for="first_name"><strong>Data di nascita</strong></label><input class="form-control" type="text" placeholder="0/0/0000" name="nascita" value="${sessionScope.utente.getPaziente().getDatanascita().toString()}"readonly=""></div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-group"><label for="last_name"><strong>Luogo di nascita</strong></label><input class="form-control" type="text" placeholder="Gubbio, PG" name="luogonascita" readonly="" value="${sessionScope.utente.getPaziente().getLuogonascita()}"></div>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="col">
                                                    <div class="form-group"><label for="first_name"><strong>Sesso</strong></label><input class="form-control" type="text" placeholder="Mischio" name="sesso" readonly="" value="${sessionScope.utente.getPaziente().getSesso()}"></div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-group"><label for="first_name"><strong>Codice Fiscale</strong></label><input class="form-control" type="text" placeholder="CGNNMO00A01E256D" name="codicefiscale" readonly="" value="${sessionScope.utente.getPaziente().getCodicefiscale()}"></div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="card shadow">
                                    <div class="card-header py-3">
                                        <p class="text-primary m-0 font-weight-bold">Informazioni</p>
                                    </div>
                                    <div class="card-body">
                                        <form action="<%=request.getContextPath()%>/sispaz/changeinformazioni" method="post">
                                            <div class="form-group"><label for="address"><strong>Email</strong></label><input class="form-control" type="email" placeholder="example@address.com" value="${sessionScope.utente.getPaziente().getEmail()}" name="email"></div>
                                            <div class="form-row">
                                                <div class="col">
                                                    <div class="form-group"><label for="country"><strong>Provincia</strong></label><select class="form-control" name="provincia">

                                                        <optgroup label="Provincia">
                                                            <%
                                                                SistemaNazionale sis  = (SistemaNazionale) getServletContext().getAttribute("sistemanazionale");
                                                                LinkedHashMap<String, String> listaProvince = sis.getListaProvince();
                                                                Utente utente = (Utente)session.getAttribute("utente");
                                                                for (Map.Entry<String,String> entry :listaProvince.entrySet()) {
                                                                    if (entry.getKey().equals(utente.getPaziente().getProvincia())) {
                                                                        out.print("<option selected value=\"");
                                                                    }
                                                                    else {
                                                                        out.print("<option value=\"");
                                                                    }
                                                                    out.print(entry.getKey());
                                                                    out.print("\">");
                                                                    out.print(entry.getValue());
                                                                    out.println("</option>");
                                                                }
                                                            %>


                                                        </optgroup>

                                                    </select></div>
                                                </div>
                                            </div>
                                            <div class="form-group"><button class="btn btn-primary btn-sm" type="submit">Salva Informazioni</button></div>
                                        </form>
                                        <%
                                            if (request.getParameter("changedinfo") != null && request.getParameter("changedinfo").equals("true")) {
                                                out.println("<label for=\"signature\" style=\"color: rgb(0,220,0);\"><strong>Successo: </strong>Informazioni cambiate con successo!<br></label>\n");
                                            }
                                            if (request.getParameter("changedinfo") != null && request.getParameter("changedinfo").equals("false")) {
                                                out.println("<label for=\"signature\" style=\"color: rgb(255,0,0);\"><strong>Errore: </strong>Informazioni non cambiate. Verificare che l'email inserita sia valida.<br></label>\n");
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
    <script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="../assets/js/Header-Blue--Sticky-Header--Smooth-Scroll-1.js"></script>
    <script src="../assets/js/Header-Blue--Sticky-Header--Smooth-Scroll.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
    <script src="../assets/js/theme.js"></script>
</body>

</html>