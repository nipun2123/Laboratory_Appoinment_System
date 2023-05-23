<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="medicaltest" uri="/WEB-INF/tlds/test" %>
<!DOCTYPE html>
<html>
    <head>
        <title>HealthLuck</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.6.5/css/buttons.bootstrap4.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.3.1/css/select.bootstrap4.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://editor.datatables.net/extensions/Editor/css/editor.bootstrap4.min.css"/>


        <style>
            html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
        </style>

    </head>
    <body class="w3-light-grey">

        <jsp:include page="receptionist_navbar.jsp">
            <jsp:param name="overview" value="w3-blue" />
        </jsp:include>
        <jsp:include page="../topbar.jsp"/>


        <div class="w3-main" style="margin-left:300px;margin-top:43px;">

            <header class="w3-container" style="padding-top:22px">
                <h5><b><i class="fa fa-dashboard"></i> My Dashboard</b></h5>
            </header>

            <div class="w3-row-padding w3-margin-bottom">
                <div class="w3-quarter">
                    <div class="w3-container w3-red w3-padding-16 w3-card-4">
                        <div class="w3-center">
                            <h3>80</h3>
                        </div>
                        <div class="w3-clear"></div>
                        <h5 class="w3-center">Patient count</h5>
                    </div>
                </div>
                <div class="w3-quarter">
                    <div class="w3-container w3-blue w3-padding-16">
                        <div class="w3-center">
                            <h3>30</h3>
                        </div>
                        <div class="w3-clear"></div>
                        <h5 class="w3-center">Appointment count</h5>
                    </div>
                </div>
                <div class="w3-quarter">
                    <div class="w3-container w3-teal w3-padding-16">
                        <div class="w3-center">
                            <h3>15</h3>
                        </div>
                        <div class="w3-clear"></div>
                        <h5 class="w3-center">Completed test count</h5>
                    </div>
                </div>
                <div class="w3-quarter">
                    <div class="w3-container w3-orange w3-text-white w3-padding-16">
                        <div class="w3-center">
                            <h3>15</h3>
                        </div>
                        <div class="w3-clear"></div>
                        <h5 class="w3-center">Upcoming appointment</h5>
                    </div>
                </div>
            </div>

            <hr>

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
        </div>

        <!--<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>-->
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
                $('#testTable').DataTable({
                    lengthChange: false,
                    "paging": false,
                    "info": false,
                    select: true

                });
            });
        </script>


    </body>
</html>
