<%-- 
    Document   : appointment
    Created on : Oct 30, 2020, 7:55:13 PM
    Author     : nipun
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" uri="/WEB-INF/tlds/test" %>
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
                <table id="patientTable" class="table table-striped table-bordered container table-hover " style="width:100%;">
                    <thead class="thead-light">
                        <tr>
                            <th>Nic</th>
                            <th>Name</th>
                            <th>Gender</th>
                            <th>Telephone no</th>
                            <th>Email</th>
                            <th>Id</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="item" items="${p:getAllPatients()}">
                            <tr>
                                <td> ${item.nic}</td>
                                <td> ${item.name}</td>
                                <td> ${item.gender}</td>
                                <td> ${item.tp}</td>
                                <td> ${item.email}</td>
                                <td> ${item.id}</td>

                            </tr>
                        </c:forEach>
                    </tbody> 
                </table>

            </div> 
    </body>
</html>
