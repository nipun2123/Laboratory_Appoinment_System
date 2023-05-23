<%-- 
    Document   : admin_navbar
    Created on : Oct 15, 2020, 12:59:35 PM
    Author     : nipun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>HealthLuck</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <style>
            html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
        </style>

    </head>
    <body class="w3-light-grey">
        <%
            HttpSession ses = request.getSession(false);
            String empName = (String) ses.getAttribute("emp");
            String role = (String) ses.getAttribute("role");
            if (role != null) {
            if (!role.equals("r")) {
                response.sendRedirect("../login.jsp");
            }
            }else{
                response.sendRedirect("../login.jsp");
            }
        %>


        <nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
            <div class="w3-container">

                <span>Welcome,     <strong><%= empName%></strong></span><br>
            </div>
            <hr>
            <div class="w3-container">
                <h5>Dashboard</h5>
            </div>
            <div class="w3-bar-block" >
                <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" style="text-decoration: none" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
                <a href="receptionist_overview.jsp" class="w3-bar-item w3-button w3-padding ${param.overview}" style="text-decoration: none" id="item"><i class="fa fa-tachometer fa-fw"></i>  Overview</a>
                <a href="receptionist_patient.jsp" class="w3-bar-item w3-button w3-padding ${param.patient}" style="text-decoration: none"><i class="fa fa-user fa-fw"></i>  Patient</a>
                <a href="receptionist_appointment.jsp" class="w3-bar-item w3-button w3-padding ${param.appointment}" style="text-decoration: none" ><i class="fa fa-calendar-plus-o fa-fw"></i>  Appointment</a>
                <a href="#" data-toggle="modal" data-target="#changePasswordModel" class="w3-bar-item w3-button w3-padding mynavitem" onclick="changeColor()" style="text-decoration: none" ><i class="fa fa-cog fa-fw"></i>  Change Password</a>
                <a href="../LogoutServlet" class="w3-bar-item w3-button w3-padding" style="text-decoration: none" ><i class="fa fa-power-off fa-fw"></i>  Logout</a>
            </div>
        </nav>

        <div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

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

        <script>

            $('#changePasswordModel').on('shown.bs.modal', function () {
                $('#myInput').trigger('focus');
            });

            var mySidebar = document.getElementById("mySidebar");

            var overlayBg = document.getElementById("myOverlay");

            function w3_open() {
                if (mySidebar.style.display === 'block') {
                    mySidebar.style.display = 'none';
                    overlayBg.style.display = "none";
                } else {
                    mySidebar.style.display = 'block';
                    overlayBg.style.display = "block";
                }
            }
            
            function w3_close() {
                mySidebar.style.display = "none";
                overlayBg.style.display = "none";
            }


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
