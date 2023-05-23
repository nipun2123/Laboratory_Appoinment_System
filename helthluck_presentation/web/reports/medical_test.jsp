<%-- 
    Document   : appointment
    Created on : Oct 30, 2020, 7:55:13 PM
    Author     : nipun
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="medicaltest" uri="/WEB-INF/tlds/test" %>
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
                                <table id="testTable" class="table table-striped table-bordered container table-hover " style="width:100%;">
                                    <thead class="thead-light">
                                        <tr>
                                            <th>Test name</th>
                                            <th>Test category</th>
                                            <th>Price</th>
                                            <th>Max count</th>
                                            <th>Lab no</th>
                                            <th>Technician</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach var="item" items="${medicaltest:getAllTest()}">
                                            <tr>
                                                <td> ${item.testName}</td>
                                                <td> ${item.testCategory}</td>
                                                <td> ${item.price}</td>
                                                <td> ${item.maxCount}</td>
                                                <td> ${item.labNo}</td>
                                                <td> ${item.empName}</td>

                                            </tr>
                                        </c:forEach>
                                    </tbody>  
                                </table>
                            </div>    
    </body>
</html>
