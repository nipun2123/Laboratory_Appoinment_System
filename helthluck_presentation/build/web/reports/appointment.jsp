<%-- 
    Document   : appointment
    Created on : Oct 30, 2020, 7:55:13 PM
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="table-responsive">

            <table class="table table-striped container table-hover" style="width:100%;">
                <thead class="thead-light">
                    <tr>
                        <th>Appointment Id</th>
                        <th>Patient nic</th>
                        <th>Test</th>
                        <th>Appointed date</th>
                        <th>Appointment date</th>
                        <th>Appointment time</th>
                        <th>Number</th>
                        <th>Doctor</th>
                        <th>Status</th>
                        <th>Nettotal</th>
                        <th>Payment Type</th>
                        <th>Appointed by</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${a:getAllAppointment()}">
                        <tr>
                            <td> ${item.appointmentId}</td>
                            <td> ${item.nic}</td>
                            <td> ${item.testName}</td>
                            <td> ${item.appointedDate}</td>
                            <td> ${item.appointmentDate}</td>
                            <td> ${item.appointmentTime}</td>
                            <td> ${item.currentCount}</td>
                            <td> ${item.doctor}</td>
                            <td> ${item.status}</td>
                            <td> ${item.nettotal}</td>
                            <td> ${item.payType}</td>
                            <td> ${item.appointedBy}</td>

                        </tr>
                    </c:forEach>
                </tbody> 
            </table>
        </div> 
    </body>
</html>
