<%-- 
    Document   : dashboard
    Created on : Oct 30, 2020, 4:58:22 PM
    Author     : nipun
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="a" uri="/WEB-INF/tlds/test" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HealthLuck</title>

        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.6.5/css/buttons.bootstrap4.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.3.1/css/select.bootstrap4.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://editor.datatables.net/extensions/Editor/css/editor.bootstrap4.min.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <style>
            html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
        </style>
    </head>
    <body class="w3-light-grey">

        <%
            HttpSession ses = request.getSession(false);
            String name = (String) ses.getAttribute("name");
        %>

        <div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
            <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
            <span class="w3-bar-item w3-right">Helthluck</span>
        </div>


        <div class="w3-main" style="margin-top:53px;">
            <div class="pull-right">
                <span >Welcome,     <strong><%= name%></strong></span>

                &nbsp;&nbsp;&nbsp;


                <a type="button" class="btn btn-default btn-sm" href="LogoutServlet" style="margin-bottom:10px;">
                    <span class="glyphicon glyphicon-log-out"></span> Log out
                </a>
            </div>


            <a type="button" id="viewBtn" target="_blank" class="btn btn-primary" style="margin-left: 10px;" >
                View Report
            </a>



            <div class="table-responsive">

                <table id="appointmentTable" class="table table-striped container table-hover" style="width:100%;">
                    <thead class="thead-light">
                        <tr>
                            <th>Appointment Id</th>
                            <th>Test</th>
                            <th>Appointed date</th>
                            <th>Appointment date</th>
                            <th>Appointment time</th>
                            <th>Number</th>
                            <th>Status</th>
                            <th>Nettotal</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${a:getAllAppointment()}">
                            <c:if test = "${item.patientId == userid}">
                                <c:if test = "${item.status == 'Completed'}">
                                    <tr>
                                        <td> ${item.appointmentId}</td>
                                        <td> ${item.testName}</td>
                                        <td> ${item.appointedDate}</td>
                                        <td> ${item.appointmentDate}</td>
                                        <td> ${item.appointmentTime}</td>
                                        <td> ${item.currentCount}</td>
                                        <td> ${item.status}</td>
                                        <td> ${item.nettotal}</td>
                                    </tr>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </tbody> 
                </table>
            </div> 

        </div>

        <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js|https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.5/js/dataTables.buttons.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.5/js/dataTables.buttons.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js"></script>
        <script type="text/javascript" src="https://editor.datatables.net/extensions/Editor/js/dataTables.editor.min.js"></script>
        <script type="text/javascript" src="https://editor.datatables.net/extensions/Editor/js/editor.bootstrap4.min.js"></script>

        <script>

                $(document).ready(function () {
                    var table = $('#appointmentTable').DataTable({
                        lengthChange: false,
                        "paging": false,
                        "info": false,
                        select: true
                    });

                    $('#appointmentTable tbody').on('click', 'tr', function () {
                        var selectRow = table.rows(this).data()[0];
                        $("#viewBtn").attr("href", "DashboardServlet?appointmentId=" + selectRow[0] + "");
                    });

                });
        </script>
    </body>
</html>
