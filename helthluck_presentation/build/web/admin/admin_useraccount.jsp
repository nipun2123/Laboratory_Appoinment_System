<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="useracc" uri="/WEB-INF/tlds/test" %>
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

        <jsp:include page="admin_navbar.jsp">
            <jsp:param name="useraccount" value="w3-blue" />
        </jsp:include>
        <jsp:include page="../topbar.jsp"/>


        <div class="w3-main" style="margin-left:300px; margin-top:43px;">

            <button type="button" class="btn btn-primary" id="addUserBtn" data-toggle="modal" data-target="#accModel">
                Create new account
            </button>
            <button type="button" class="btn btn-primary" id="editUserBtn" data-toggle="modal" data-target="#accModel">
                Update account
            </button>

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

            <div class="modal fade" id="accModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">

                        <div class="modal-body">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <form action="../UserAccountServlet" id="userAccForm" method="POST">
                                <div class="form-group">
                                    <label for="employee">Employee</label>
                                    <select class="form-control browser-default custom-select" id="employee" name="employee">
                                        <c:forEach  var="emp" items="${useracc:getAllEmpNoAcc()}" >
                                            <option value="${emp}">${emp}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="username">Username</label>
                                    <input type="text" class="form-control" id="username" name="username"  onkeyup="this.value = this.value.toLowerCase();" title="Must contain minimum 8 characters with at least one letter and one number"  maxlength="45" pattern="^(?=.*?[a-z])(?=.*?[0-9]).{8,45}$" required  >
                                    <label id="resultusername" style="color: red"></label>
                                    <input type="text" id="pastusername" class="form-control" name="pastusername" style="display: none"  >
                                </div>

                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" required  >
                                </div>

                                <div class="form-group">
                                    <label for="newPassword">New password</label>
                                    <input type="password" class="form-control"  id="new_password" name="newpassword" title="Must contain minimum 8 characters with at least one letter and one number"  required pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$" placeholder="New Password">
                                </div>
                                <div class="form-group">
                                    <label for="confirmPassword">Confirm password</label>
                                    <input type="password" class="form-control" id="confirm_password" required placeholder="Confirm Password">
                                </div>

                                <span id='result_password'></span>
                                <br/>

                                <div id="status">
                                    <div class="form-check-inline">
                                        <label class="form-check-label">
                                            <input type="radio" class="form-check-input" id="active" value="1" checked name="availibility">Active
                                        </label>
                                    </div>
                                    <div class="form-check-inline">
                                        <label class="form-check-label">
                                            <input type="radio" class="form-check-input" id="deactive" value="0" name="availibility">Deactive
                                        </label>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary">Submit</button>
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
                                            var table = $('#accTable').DataTable({
                                                lengthChange: false,
                                                select: true,
                                                "paging": false

                                            });

                                            $('#accTable tbody').on('click', 'tr', function () {
                                                var selectRow = table.rows(this).data()[0];
                                                clearFields();
                                                $("#username").val(selectRow[0]);
                                                $("#pastusername").val(selectRow[0]);
                                                $("#employee").val(selectRow[1]);
                                                $("#email").val(selectRow[2]);
                                                var availibility = selectRow[3];

                                                if (availibility === "Active") {
                                                    $("#active").prop("checked", true);
                                                } else {
                                                    $("#deactive").prop("checked", true);
                                                }
                                            });

                                        });

                                        $('#addUserBtn').on('click', function () {
                                            clearFields();
                                            $("#status").css('display', 'none');
                                        });

                                        $('#editUserBtn').on('click', function () {
                                            $("#status").css('display', 'inline');
                                        });

                                        function clearFields() {
                                            $("#employee").val($("#employee option:first").val());
                                            $("#username").val("");
                                            $("#pastusername").val("");
                                            $("#email").val("");
                                            $("#active").prop("checked", true);
                                            $("#resultusername").val("");
                                            $("#resultusername").html("");
                                            $("#result_password").val("");
                                            $("#new_password").val("");
                                            $("#confirm_password").val("");
                                            $("#result_password").html("");
                                        }


                                        $(document).ready(function () {

                                            $('#username').keyup(function () {
                                                var user = $('#username').val();
                                                var puser = $('#pastusername').val();
                                                $.ajax({
                                                    type: 'GET',
                                                    data: {user: user, puser: puser},
                                                    url: '../UserAccountServlet',
                                                    success: function (result) {
                                                        $('#resultusername').html(result);
                                                        $('#resultusername').val(result);
                                                    }
                                                });
                                            });

                                        });


                                        $(document).ready(function () {
                                            $("#userAccForm").submit(function (e) {
                                                var usernamemessage = $.trim($("#resultusername").val());
                                                var passwordmessage = $.trim($("#result_password").html());
                                                if (usernamemessage === "Already exists" || passwordmessage === "Not Matching") {
                                                    e.preventDefault();
                                                }

                                            });
                                        });


                                        $('#accModel').on('shown.bs.modal', function () {
                                            $('#myInput').trigger('focus');
                                        });



                                        $('#new_password, #confirm_password').on('keyup', function () {

                                            if ($('#new_password').val() === $('#confirm_password').val() && $('#new_password').val() !== '') {
                                                $('#result_password').html('Matching').css('color', 'green');
                                            } else {
                                                $('#result_password').html('Not Matching').css('color', 'red');
                                            }
                                        });


                                        $('#new_password, #confirm_password, #username').on('keypress', function (event) {
                                            var key = event.keyCode;
                                            if (key === 32) {
                                                event.preventDefault();
                                            }
                                        });





        </script>
    </body>
</html>
