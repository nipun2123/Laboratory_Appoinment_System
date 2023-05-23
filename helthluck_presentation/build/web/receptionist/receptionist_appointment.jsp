<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="a" uri="/WEB-INF/tlds/test" %>
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.12.6/js/standalone/selectize.min.js" integrity="sha256-+C0A5Ilqmu4QcSPxrlGpaZxJ04VjsRjKu+G82kl5UJk=" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.12.6/css/selectize.bootstrap3.min.css" integrity="sha256-ze/OEYGcFbPRmvCnrSeKbRTtjG4vGLHXgOqsyLFTRjg=" crossorigin="anonymous" />


        <style>
            html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
        </style>

    </head>
    <body class="w3-light-grey">

        <jsp:include page="receptionist_navbar.jsp">
            <jsp:param name="appointment" value="w3-blue" />
        </jsp:include>
        <jsp:include page="../topbar.jsp"/>

        <div class="w3-main" style="margin-left:300px; margin-top:43px;">


            <div class="container">
                <form action="../AppointmentServlet?type=add" method="POST" id="appointmentForm">

                    <div class="form-row">
                        <div class="form-group col-md-3">
                            <label for="patient">Patient</label>
                            <select class="form-control browser-default custom-select" id="patient" name="patient" required>
                                <c:forEach  var="patient" items="${a:getAllPatients()}" >
                                    <option value="${patient.nic}">${patient.nic}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group col-md-3">
                            <label for="test">Medical test</label>
                            <select class="form-control browser-default custom-select" id="test" name="test" required>
                                <c:forEach  var="test" items="${a:getAllTest()}" >
                                    <option value="${test.testName}">${test.testName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group col-md-3">
                            <label for="date">Appointment Date</label>
                            <input type="date" class="form-control" data-date-format="YYYY MM DD" id="appointmentDate" name="appointmentDate" required>
                            <span id="appointmentDateResult" style="color: red;"></span>
                        </div>

                        <div class="form-group col-md-3">
                            <label for="date">Appointment time</label>
                            <input type="time" class="form-control"  id="appointmentTime" name="appointmentTime" value="13:00"  required>
                        </div>
                        
                        <div class="form-group col-md-3">
                            <label for="doctor">Doctor who recommend</label>
                            <input type="text" class="form-control" id="doctor" name="doctor">
                        </div>
                    </div>

                    <div class="form-row justify-content-center">
                        <div class="form-group">
                            <label for="doctor">Amount</label>
                            <input type="text" class="form-control" id="amount" name="amount" readonly>
                        </div>
                    </div>

                    <div class="form-row justify-content-center">
                        <div class="form-check-inline">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" checked value="Cash" name="payment">Cash
                            </label>
                        </div>
                        <div class="form-check-inline">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" value="Card" name="payment">Card
                            </label>
                        </div>
                    </div>

                    <br/>
                    <div class="form-row justify-content-center">
                        <button type="submit" class="btn btn-primary float-right">Submit</button>

                    </div>

                </form>
            </div>

            <hr/>
            &nbsp;&nbsp;&nbsp;
            <button type="button" id="updateBtn" class="btn btn-primary" data-toggle="modal" data-target="#cancelModel">
                Cancel Appointment
            </button>

            <button type="button" id="updateBtn" class="btn btn-primary" data-toggle="modal" data-target="#empUpdateModel">
                Print invoice
            </button>

            <div class="table-responsive">

                <table id="appointmentTable" class="table table-striped container table-hover" style="width:100%;">
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
        </div>


        <div class="modal fade" id="cancelModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content" >

                    <div class="modal-body">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>

                        <form action="../AppointmentServlet?type=cancel" method="POST">

                            <input type="text" id="appointmentId" name="appointmentId" style="display: none" >
                            <input type="text" id="clickStatus" name="clickStatus" style="display: none" >
                            <label >Are You sure to cancel this appointment?</label>
                            <br/>
                            <div class="d-flex align-items-center justify-content-center">
                                <button type="submit" class="btn btn-primary" >Yes</button> 
                                &nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-secondary"  class="close" data-dismiss="modal" aria-label="Close">No</button> 
                            </div>
                        </form>

                    </div>
                </div>
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
                $('select').selectize({
                    sortField: 'text'
                });
            });
            
            $(document).ready(function () {
                var table = $('#appointmentTable').DataTable({
                    lengthChange: false,
                    "paging": false,
                    "info": false,
                    select: true
                });

                $('#appointmentTable tbody').on('click', 'tr', function () {
                    var selectRow = table.rows(this).data()[0];
                    $("#appointmentId").val(selectRow[0]);
                    $("#clickStatus").val(selectRow[8]);
                });
            });


            $(document).ready(function () {
                var formSubmit = $("#appointmentForm").submit(function (e) {


                    var date = $('#appointmentDate').val();
                    var test = $('#test').val();
                    e.preventDefault();
                    $.ajax({
                        type: 'GET',
                        data: {type: "count", date: date, test: test},
                        url: '../AppointmentServlet',
                        success: function (result) {
                            $('#appointmentDateResult').html(result);
                            $('#appointmentDateResult').val(result);
                            if ($.trim(result) === "No more appointments can be taken!") {
                                e.preventDefault();
                            } else {
                                e.currentTarget.submit();
                            }
                        }
                    });
                });
            });
            $("#test").change(function () {
                var test = $('#test').val();
                $.ajax({
                    type: 'GET',
                    data: {type: "price", test: test},
                    url: '../AppointmentServlet',
                    success: function (result) {

                        $('#amount').val(result);
                    }
                });
            });
            $(document).ready(function () {
                var test = $('#test').val();
                $.ajax({
                    type: 'GET',
                    data: {type: "price", test: test},
                    url: '../AppointmentServlet',
                    success: function (result) {

                        $('#amount').val(result);
                    }
                });
            });

            $('#cancelModel').on('shown.bs.modal', function () {
                $('#myInput').trigger('focus');
            });


        </script>
    </body>
</html>
