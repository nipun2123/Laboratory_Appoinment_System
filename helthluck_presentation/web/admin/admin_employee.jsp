<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="emp" uri="/WEB-INF/tlds/test" %>
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
            <jsp:param name="employee" value="w3-blue" />
        </jsp:include>
        <jsp:include page="../topbar.jsp"/>



        <div class="w3-main" style="margin-left:300px; margin-top:43px;">


            <button type="button" id="registerBtn" class="btn btn-primary" data-toggle="modal" data-target="#empAddModel">
                Register employee
            </button>
            <button type="button" id="updateBtn" class="btn btn-primary" data-toggle="modal" data-target="#empUpdateModel">
                Update employee

            </button>
            <div class="table-responsive">
                <table id="empTable" class="table table-striped table-bordered container table-hover" style="width:100%;">
                    <thead class="thead-light">
                        <tr>
                            <th>Nic</th>
                            <th>Name</th>
                            <th>Gender</th>
                            <th>Contact numbers</th>
                            <th>Address</th>
                            <th>Role</th>
                            <th>Avalability</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${emp:getAllEmp()}">
                            <tr>
                                <td> ${item.nic}</td>
                                <td> ${item.name}</td>
                                <td> ${item.gender}</td>
                                <td> ${item.tp}</td>
                                <td> ${item.address}</td>
                                <td> ${item.role}</td>
                                <td> ${item.status}</td>

                            </tr>
                        </c:forEach>
                    </tbody> 
                </table>

            </div>
            
            <div class="modal fade" id="empAddModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">

                        <div class="modal-body">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <form action="../EmployeeServlet?f=add" method="POST" id="addForm">

                                <div class="form-group">
                                    <label for="nic">Nic</label>
                                    <input type="text" class="form-control" id="nic" required name="nic" pattern="^([0-9]{9}[x|X|v|V]|[0-9]{12})$" >
                                    <label id="nicResult" style="color: red"></label>
                                </div>

                                <div class="form-group">
                                    <label for="firstname">First name</label>
                                    <input type="text" class="form-control" maxlength="45" id="firstname"  name="firstname" required pattern="[A-Za-z]{1,45}"  >
                                </div>
                                <div class="form-group">
                                    <label for="middelname">Middel name</label>
                                    <input type="text" class="form-control" id="middelname" name="middelname" maxlength="45" pattern="[A-Za-z]{1,45}" >
                                </div>
                                <div class="form-group">
                                    <label for="lastname">Last name</label>
                                    <input type="text" class="form-control" id="lastname" required name="lastname" maxlength="45" pattern="[A-Za-z]{1,45}" >
                                </div>

                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" checked value="m" name="gender">Male
                                    </label>
                                </div>
                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" value="f" name="gender">Female
                                    </label>
                                </div>

                                <div class="form-group">
                                    <label for="tp1">Telephone number 1</label>
                                    <input type="tel" class="form-control" id="tp1" required name="tp1" pattern="^(?:0|94|\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|5|6|7|8)\d)\d{6}$">
                                </div>

                                <div class="form-group">
                                    <label for="tp2">Telephone number 2</label>
                                    <input type="tel" class="form-control" id="tp2" name="tp2"  pattern="^(?:0|94|\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|5|6|7|8)\d)\d{6}$" >
                                </div>

                                <div class="form-group">
                                    <label for="no">Address no</label>
                                    <input type="text" class="form-control" id="no" maxlength="15" required name="no" >
                                </div>

                                <div class="form-group">
                                    <label for="street1">Street 1</label>
                                    <input type="text" class="form-control" id="street1" maxlength="45"  required name="street1"  >
                                </div>

                                <div class="form-group">
                                    <label for="street2">Street 2</label>
                                    <input type="text" class="form-control" id="street2" name="street2" maxlength="45" >
                                </div>

                                <div class="form-group">
                                    <label for="city">City</label>
                                    <input type="text" class="form-control" id="city" required name="city" maxlength="45" >
                                </div>

                                <div class="form-group">
                                    <label for="sel1">Job role</label>
                                    <select class="form-control browser-default custom-select" id="role" name="role">
                                        <option value="a">Admin</option>
                                        <option value="r">Receptionist</option>
                                        <option value="t">Techniciant</option>
                                    </select>
                                </div>

                                <button type="submit" class="btn btn-primary">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="empUpdateModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">

                        <div class="modal-body">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <form action="../EmployeeServlet?f=up" method="POST" id="updForm">

                                <div class="form-group">
                                    <input type="text" id="pastNic" class="form-control" name="pastNic" style="display: none"  >
                                    <label for="nic">Nic</label>
                                    <input type="text" id="updNic" class="form-control" required name="nic" pattern="^([0-9]{9}[x|X|v|V]|[0-9]{12})$"  >
                                    <label id="updNicResult" style="color: red"></label>
                                </div>

                                <div class="form-group">
                                    <label for="firstname">First name</label>
                                    <input type="text" id="updFname" class="form-control" required name="firstname" maxlength="45" pattern="[A-Za-z]{1,45}" >
                                </div>
                                <div class="form-group">
                                    <label for="middelname">Middel name</label>
                                    <input type="text" id="updMname" class="form-control" name="middelname" maxlength="45" pattern="[A-Za-z]{1,45}" >
                                </div>
                                <div class="form-group">
                                    <label for="lastname">Last name</label>
                                    <input type="text" id="updLname" class="form-control" required name="lastname" maxlength="45" pattern="[A-Za-z]{1,45}" >
                                </div>

                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <input type="radio" id="updMale" class="form-check-input" checked value="m" name="gender">Male
                                    </label>
                                </div>
                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <input type="radio" id="updFemale" class="form-check-input" value="f" name="gender">Female
                                    </label>
                                </div>

                                <div class="form-group">
                                    <label for="tp1">Telephone number 1</label>
                                    <input type="tel" id="updTp1" class="form-control" required name="tp1" pattern="^(?:0|94|\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|5|6|7|8)\d)\d{6}$">
                                </div>

                                <div class="form-group">
                                    <label for="tp2">Telephone number 2</label>
                                    <input type="tel" id="updTp2" class="form-control"name="tp2"  pattern="^(?:0|94|\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|5|6|7|8)\d)\d{6}$" >
                                </div>

                                <div class="form-group">
                                    <label for="no">Address no</label>
                                    <input type="text" id="updNo" class="form-control" maxlength="15" required name="no" >
                                </div>

                                <div class="form-group">
                                    <label for="street1">Street 1</label>
                                    <input type="text" id="updStreet1" class="form-control" required name="street1" maxlength="45"  >
                                </div>

                                <div class="form-group">
                                    <label for="street2">Street 2</label>
                                    <input type="text" id="updStreet2" class="form-control"  name="street2" maxlength="45" >
                                </div>

                                <div class="form-group">
                                    <label for="city">City</label>
                                    <input type="text" id="updCity" class="form-control" required name="city" maxlength="45"  >
                                </div>

                                <div class="form-group">
                                    <label for="sel1">Job role</label>
                                    <select class="form-control browser-default custom-select" id="updRole" name="role">
                                        <option value="a">Admin</option>
                                        <option value="r">Receptionist</option>
                                        <option value="t">Techniciant</option>
                                    </select>
                                </div>


                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <input type="radio" id="updOn" class="form-check-input" checked value="1" name="availibility">Active
                                    </label>
                                </div>
                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <input type="radio" id="updOff" class="form-check-input" value="0" name="availibility">Deactive
                                    </label>
                                </div>

                                <button type="submit" class="btn btn-primary" onclick="cl">Submit</button>
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
                                    var events = $('#events');
                                    $(document).ready(function () {
                                        var table = $('#empTable').DataTable({
                                            lengthChange: false,
                                            select: true,
                                            "paging": false
                                        });




                                        $('#empTable tbody').on('click', 'tr', function () {
                                            var selectRow = table.rows(this).data()[0];
                                            clearFields();
                                            $("#updNic").val(selectRow[0]);
                                            $("#pastNic").val(selectRow[0]);

                                            var name = selectRow[1].split(" ");

                                            if (name.length === 3) {
                                                $("#updFname").val(name[0]);
                                                $("#updMname").val(name[1]);
                                                $("#updLname").val(name[2]);
                                            } else {
                                                $("#updFname").val(name[0]);
                                                $("#updLname").val(name[1]);
                                            }

                                            var gender = selectRow[2];

                                            if (gender === "Male") {
                                                $("#updMale").prop("checked", true);
                                            } else {
                                                $("#updFemale").prop("checked", true);
                                            }

                                            var tp = selectRow[3].split("/");

                                            if (tp.length === 2) {
                                                $("#updTp1").val(tp[0]);
                                                $("#updTp2").val(tp[1]);
                                            } else {
                                                $("#updTp1").val(selectRow[3]);
                                            }

                                            var address = selectRow[4].split(",");

                                            if (address.length === 4) {
                                                $("#updNo").val(address[0]);
                                                $("#updStreet1").val(address[1]);
                                                $("#updStreet2").val(address[2]);
                                                $("#updCity").val(address[3]);
                                            } else {
                                                $("#updNo").val(address[0]);
                                                $("#updStreet1").val(address[1]);
                                                $("#updCity").val(address[2]);
                                            }

                                            var role = selectRow[5];

                                            if (role === "Admin") {
                                                $("#updRole").val("a");
                                            } else if (role === "Technician") {
                                                $("#updRole").val("t");
                                            } else {
                                                $("#updRole").val("r");
                                            }

                                            var availibility = selectRow[6];

                                            if (availibility === "Active") {
                                                $("#updOn").prop("checked", true);
                                            } else {
                                                $("#updOff").prop("checked", true);
                                            }

                                        });
                                    });


                                    function clearFields() {
                                        $("#updNic").val("");
                                        $("#pastNic").val("");
                                        $("#updFname").val("");
                                        $("#updMname").val("");
                                        $("#updLname").val("");
                                        $("#updMale").prop("checked", true);
                                        $("#updTp1").val("");
                                        $("#updTp2").val("");
                                        $("#updNo").val("");
                                        $("#updStreet1").val("");
                                        $("#updStreet2").val("");
                                        $("#updCity").val("");
                                        $("#updOn").prop("checked", true);
                                    }



                                    $(document).ready(function () {

                                        $('#nic').keyup(function () {
                                            var enic = $('#nic').val();
                                            $.ajax({
                                                type: 'GET',
                                                data: {nic: enic},
                                                url: '../EmployeeServlet',
                                                success: function (result) {
                                                    $('#nicResult').html(result);
                                                    $('#nicResult').val(result);
                                                }
                                            });
                                        });

                                    });

                                    $(document).ready(function () {

                                        $('#updNic').keyup(function () {
                                            var enic = $('#updNic').val();
                                            var pnic = $('#pastNic').val();
                                            $.ajax({
                                                type: 'GET',
                                                data: {nic: enic, pnic: pnic},
                                                url: '../EmployeeServlet',
                                                success: function (result) {
                                                    $('#updNicResult').html(result);
                                                    $('#updNicResult').val(result);
                                                }
                                            });
                                        });

                                    });

                                    $(document).ready(function () {
                                        $("#addForm").submit(function (e) {
                                            var message = $.trim($("#nicResult").val());

                                            if (message === "Already exists") {
                                                e.preventDefault();
                                            }

                                        });
                                    });

                                    $(document).ready(function () {
                                        $("#updForm").submit(function (e) {
                                            var message = $.trim($("#updNicResult").val());

                                            if (message === "Already exists") {
                                                e.preventDefault();
                                            }


                                        });

                                    });

                                    $('#empAddModel').on('shown.bs.modal', function () {
                                        $('#myInput').trigger('focus');
                                    });
                                    $('#empUpdateModel').on('shown.bs.modal', function () {
                                        $('#myInput').trigger('focus');
                                    });


        </script>
    </body>
</html>
