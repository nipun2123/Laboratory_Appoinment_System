<%-- 
    Document   : appointment
    Created on : Oct 30, 2020, 7:55:13 PM
    Author     : nipun
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="useracc" uri="/WEB-INF/tlds/test" %>
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
                <table id="accTable" class="table table-striped table-bordered container table-hover" style="width:100%;">
                    <thead class="thead-light">
                        <tr>
                            <th>Username</th>
                            <th>Employee</th>
                            <th>Email</th>
                            <th>Availability</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${useracc:getAllUserAccounts()}">
                            <tr>
                                <td> ${item.username}</td>
                                <td> ${item.empName}</td>
                                <td> ${item.email}</td>
                                <td> ${item.status}</td>
                            </tr>
                        </c:forEach>
                    </tbody> 
                </table>
            </div>
    </body>
</html>
