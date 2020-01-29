<%@ page import="unitn.progweb.cocchiara.model.Utente" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: burge
  Date: 22/01/2020
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
</head>
<body>
<nav class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
    <div class="container-fluid">
        <button class="btn btn-link d-md-none rounded-circle mr-3" id="sidebarToggleTop" type="button"><i
                class="fas fa-bars"></i></button>
        <ul class="nav navbar-nav flex-nowrap ml-auto">
            <li class="nav-item dropdown d-sm-none no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown"
                                                                aria-expanded="false" href="#"><i
                    class="fas fa-search"></i></a>
                <div class="dropdown-menu dropdown-menu-right p-3 animated--grow-in" role="menu"
                     aria-labelledby="searchDropdown">
                    <form class="form-inline mr-auto navbar-search w-100">
                        <div class="input-group"><input class="bg-light form-control border-0 small" type="text"
                                                        placeholder="Search for ...">
                            <div class="input-group-append">
                                <button class="btn btn-primary py-0" type="button"><i class="fas fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </li>
            <li class="nav-item dropdown no-arrow mx-1" role="presentation">
                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown"
                                                           aria-expanded="false" href="#"><i
                        class="fas fa-envelope fa-fw"></i>
                        <%
                             Utente utente = (Utente) session.getAttribute("utente");
                             ArrayList<Utente.Notifica> notifiche = utente.getNotifiche();
                             if(notifiche.size() > 0)
                             out.println("<span id=\"notcounter\" class=\"badge badge-danger badge-counter\">"+notifiche.size()+"</span>\n");%>

                </a><div class="dropdown-menu dropdown-menu-right dropdown-list dropdown-menu-right animated--grow-in"
                                        role="menu">
                                        <h6 class="dropdown-header">Notifiche</h6>
                            <%
                                for (Utente.Notifica n : notifiche)
                                {
                                   out.println("<a class=\" notifica d-flex align-items-center dropdown-item\" value=\"" + n.getId() + "\">\n" +
                                           "<div class=\"font-weight-bold\">\n" +
                                           "<div class=\"text\"><i class=\"fa fa-info\" style=\"\"></i><span>" + n.getText() + "</span>\n" +
                                           "<button value=\"" + n.getId() + "\" class=\"removenotifica close justify-content-lg-end\"><span aria-hidden=\"true\">×</span></button>\n" +
                                           "</div>\n" +
                                           "</div>\n" +
                                           "</a>");

                                }
                            %>

                    </div>
                </div>
                <div class="shadow dropdown-list dropdown-menu dropdown-menu-right"
                     aria-labelledby="alertsDropdown"></div>
            </li>
            <div class="d-none d-sm-block topbar-divider"></div>
            <li class="nav-item dropdown no-arrow" role="presentation">
                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown"
                                                           aria-expanded="false" href="#"><span
                        class="d-none d-lg-inline mr-2 text-gray-600 small"
                        style="text-align: right;">${sessionScope.utente.getPaziente().getNome()} ${sessionScope.utente.getPaziente().getCognome()}<br>Provincia di <%=request.getSession().getAttribute("provinciastampabile")%></span><img
                        class="border rounded-circle img-profile" src="<%=request.getContextPath()%>/avatar"></a>
                    <div class="dropdown-menu shadow dropdown-menu-right animated--grow-in" role="menu"><a
                            class="dropdown-item" role="presentation"
                            href="<%=request.getContextPath()%>/sispaz/profilocittadino"><i
                            class="fas fa-persona fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Profile</a>
                        <div class="dropdown-divider"></div>
                        <%
                            if (utente.isMedico() || utente.isAdmin()) {
                                out.println("<a class=\"dropdown-item\" role=\"presentation\" href=\"");
                                out.println(request.getContextPath());
                                out.println("/servizi");
                                out.println("\"><i class=\"fas fa-persona fa-sm fa-fw mr-2 text-gray-400\"></i>&nbsp;Servizi</a>");

                            }
                        %>
                        <a class="dropdown-item" role="presentation" href="<%=request.getContextPath()%>/logout"><i
                                class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Logout</a>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</nav>

<script>

    var closeNotifica = "<%=request.getContextPath()+ "/sispaz/deletenotifica"%>";


    $(document).ready(function(){
        $(".removenotifica").click(function(){

            var vars = {}
            vars["value"] = $(this).attr("value");
            $.ajax({type:"POST",
                url: closeNotifica,
                data: vars,
                async:true,
                success: function(result){
                    if(result)
                    {
                        var notifiche = Array.from(document.getElementsByClassName("notifica"));
                        notifiche.some(function(item)
                        {
                            if(item.getAttribute("value") === result) {
                                item.remove();
                                if(notifiche.length > 1)
                                    notcounter.innerText = notifiche.length - 1;
                                else
                                    $("#notcounter").hide();
                                return true;
                            }
                        })
                    }
                }});
        });
    });
</script>

</body>
</html>
