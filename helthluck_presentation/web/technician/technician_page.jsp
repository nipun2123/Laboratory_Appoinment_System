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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
            String emp = (String) ses.getAttribute("emp");

            String role = (String) ses.getAttribute("role");
         
                if (role != null) {
                if (!role.equals("t")) {
                    response.sendRedirect("../login.jsp");
                }
                }else{
                    response.sendRedirect("../login.jsp");
                }
            
        %>

        <%@ include file="../topbar.jsp" %>

        <div class="w3-main container" style="margin-top:53px;">

            <div class="pull-right">
                <span >Welcome,     <strong><%= emp%></strong></span>

                &nbsp;&nbsp;&nbsp;


                <button data-toggle="modal" data-target="#changePasswordModel" type="button" class="btn btn-default btn-sm" style="margin-bottom:10px;">
                    Change Password
                </button>

                <a type="button" class="btn btn-default btn-sm" href="../LogoutServlet" style="margin-bottom:10px;">
                    <span class="glyphicon glyphicon-log-out"></span> Log out
                </a>
            </div>

            <div class="w3-row-padding w3-margin-bottom"> 
                <div class="w3-quarter">
                    <div class="w3-container w3-red w3-padding-16 w3-card-4">
                        <div class="w3-left"><i class="fa fa-comment w3-xxxlarge"></i></div>
                        <div class="w3-right">
                            <h3>52</h3>
                        </div>
                        <div class="w3-clear"></div>
                        <h5>Today appointment count</h5>
                    </div>
                </div>
                <div class="w3-quarter">
                    <div class="w3-container w3-blue w3-padding-16">
                        <div class="w3-left"><i class="fa fa-eye w3-xxxlarge"></i></div>
                        <div class="w3-right">
                            <h3>99</h3>
                        </div>
                        <div class="w3-clear"></div>
                        <h5>Today completed appointment count</h5>
                    </div>
                </div>
                <div class="w3-quarter">
                    <div class="w3-container w3-teal w3-padding-16">
                        <div class="w3-left"><i class="fa fa-share-alt w3-xxxlarge"></i></div>
                        <div class="w3-right">
                            <h3>23</h3>
                        </div>
                        <div class="w3-clear"></div>
                        <h5>All appointments</h5>
                    </div>
                </div>
                <div class="w3-quarter">
                    <div class="w3-container w3-orange w3-text-white w3-padding-16">
                        <div class="w3-left"><i class="fa fa-users w3-xxxlarge"></i></div>
                        <div class="w3-right">
                            <h3>50</h3>
                        </div>
                        <div class="w3-clear"></div>
                        <h5>Upcoming appointment</h5>
                    </div>
                </div>
            </div>

            <hr>

            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#uploadFileModel">
                Upload Report
            </button>

            <div class="table-responsive">
                <table id="appointmentTable" class="table table-striped table-bordered container table-hover " style="width:100%;">
                    <thead class="thead-light">
                        <tr>
                            <th>Appointment Id</th>
                            <th>Patient nic</th>
                            <th>Test</th>
                            <th>Appointment date</th>
                            <th>Appointment time</th>
                            <th>No</th>
                            <th>Status</th>
                            <th>Appointed by</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="item" items="${a:getAllAppointment()}">

                            <c:if test = "${item.technician == emp}">
                                <c:if test = "${item.status != 'Cancel'}">
                                    <tr>
                                        <td> ${item.appointmentId}</td>
                                        <td> ${item.nic}</td>
                                        <td> ${item.testName}</td>
                                        <td> ${item.appointmentDate}</td>
                                        <td> ${item.appointmentTime}</td>
                                        <td> ${item.currentCount}</td>
                                        <td> ${item.status}</td>
                                        <td> ${item.appointedBy}</td>

                                    </tr>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </tbody> 
                </table>

            </div>

            <div class="modal fade" id="uploadFileModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">

                        <div class="modal-body">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <form action="../TechnicianServlet" method="POST" enctype="multipart/form-data">

                                <input type="text" id="appointmentId" name="appointmentId" style="display: none;">
                                <input type="text" id="nic" name="nic" style="display: none;">
                                <input type="file" accept="application/pdf" name="uploadReport" required>
                                <br/>
                                <br/>
                                <button type="submit" class="btn btn-primary">Upload</button>

                            </form>

                        </div>
                    </div>
                </div>
            </div>


            <div class="modal fade" id="changePasswordModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Change password</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="../ChangePasswordServlet" id="changePasswordForm" method="POST">
                                <div class="form-group">
                                    <label for="oldPassword">Old password</label>
                                    <input type="password" class="form-control" id="oldPassword"  required placeholder="Old Password">
                                    <label id="resultOldPassword" style="color: red"></label>
                                </div>
                                <div class="form-group">
                                    <label for="newPassword">New password</label>
                                    <input type="password" class="form-control" id="newpassword" name="newpassword" title="Must contain minimum 8 characters with at least one letter and one number"  required pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$" placeholder="New Password">
                                </div>
                                <div class="form-group">
                                    <label for="confirmpassword">Confirm password</label>
                                    <input type="password" class="form-control" id="confirmpassword" required placeholder="Confirm Password">
                                    <span id='resultpassword'></span>
                                </div>

                                <button type="submit" class="btn btn-primary" >Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
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
                    $("#appointmentId").val(selectRow[0]);
                    $("#nic").val(selectRow[1]);
                });
            });

            $('#uploadFileModel').on('shown.bs.modal', function () {
                $('#myInput').trigger('focus');
            });

            $('#changePasswordModel').on('shown.bs.modal', function () {
                $('#myInput').trigger('focus');
            });

            $('#newpassword, #confirmpassword').on('keyup', function () {

                if ($('#newpassword').val() === $('#confirmpassword').val() && $('#newpassword').val() !== '') {
                    $('#resultpassword').html('Matching').css('color', 'green');
                } else {
                    $('#resultpassword').html('Not Matching').css('color', 'red');
                }
            });

            $('#newpassword, #confirmpassword, #oldpassword').on('keypress', function (event) {
                var key = event.keyCode;
                if (key === 32) {
                    event.preventDefault();
                }
            });

            $(document).ready(function () {
                $("#changePasswordForm").submit(function (e) {
                    var passwordmessage = $.trim($("#resultpassword").html());
                    var oldpasswordmessage = $.trim($("#resultOldPassword").html());
                    if (passwordmessage === "Not Matching" || oldpasswordmessage === "Wrong password") {
                        e.preventDefault();
                    }

                });
            });

            $(document).ready(function () {

                $('#oldPassword').keyup(function () {
                    var oldPassword = $('#oldPassword').val();
                    $.ajax({
                        type: 'GET',
                        data: {oldPassword: oldPassword},
                        url: '../ChangePasswordServlet',
                        success: function (result) {
                            $('#resultOldPassword').html(result);
                            $('#resultOldPassword').val(result);
                        }
                    });
                });

            });
        </script>
    </body>
</html>
