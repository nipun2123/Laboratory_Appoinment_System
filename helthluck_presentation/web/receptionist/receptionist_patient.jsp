<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" uri="/WEB-INF/tlds/test" %>
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


        <style>
            html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
        </style>

    </head>
    <body class="w3-light-grey">

        <jsp:include page="receptionist_navbar.jsp">
            <jsp:param name="patient" value="w3-blue" />
        </jsp:include>
        <jsp:include page="../topbar.jsp"/>

        <div class="w3-main" style="margin-left:300px; margin-top:43px;">

            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#patientModel">
                Register patient
            </button>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#patientModel">
                Update patient
            </button>


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
            
            <div class="modal fade" id="patientModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">

                        <div class="modal-body">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <form action="../PatientServlet" method="POST" id="patientForm">

                                <div class="form-group">
                                    <input type="text" id="pastNic" class="form-control" name="pastNic" style="display: none"  >
                                    <label for="nic">Nic</label>
                                    <input type="text" class="form-control" id="nic" name="nic" required name="nic" pattern="^([0-9]{9}[x|X|v|V]|[0-9]{12})$" >
                                    <label id="nicResult" style="color: red"></label>
                                </div>


                                <div class="form-group">
                                    <label for="fname">First name</label>
                                    <input type="text" class="form-control" id="fname" name="fname" maxlength="45" required pattern="[A-Za-z]{1,45}" >
                                </div>

                                <div class="form-group">
                                    <label for="lastname">Last name</label>
                                    <input type="text" class="form-control" id="lname" name="lname" maxlength="45" required pattern="[A-Za-z]{1,45}">
                                </div>

                                <div class="form-group">
                                    <label for="tp">Telephone number</label>
                                    <input type="tel" class="form-control" id="tp" name="tp" required pattern="^(?:0|94|\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|5|6|7|8)\d)\d{6}$">
                                </div>

                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" id="male" name="gender" value="m" checked>Male
                                    </label>
                                </div>
                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" id="female" name="gender" value="f">Female
                                    </label>
                                </div>

                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control"  id="email" name="email" >
                                </div>

                                <button type="submit" class="btn btn-primary">Submit</button>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> 
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
                var table = $('#patientTable').DataTable({
                    lengthChange: false,
                    select: true,
                    "paging": false
                });

                $('#patientTable tbody').on('click', 'tr', function () {
                    var selectRow = table.rows(this).data()[0];
                    clearFields();
                    $("#nic").val(selectRow[0]);
                    $("#pastNic").val(selectRow[0]);

                    var name = selectRow[1].split(" ");

                    $("#fname").val(name[0]);
                    $("#lname").val(name[1]);

                    var gender = selectRow[2];

                    if (gender === "Male") {
                        $("#male").prop("checked", true);
                    } else {
                        $("#female").prop("checked", true);
                    }

                    $("#tp").val(selectRow[3]);
                    $("#email").val(selectRow[4]);



                });


            });

            function clearFields() {
                $("#nic").val("");
                $("#pastNic").val("");
                $("#nicResult").val("");
                $("#nicResult").html("");
                $("#fname").val("");
                $("#lname").val("");
                $("#male").prop("checked", true);
                $("#tp").val("");
                $("#email").val("");
            }


            $(document).ready(function () {

                $('#nic').keyup(function () {
                    var enic = $('#nic').val();
                    var pnic = $('#pastNic').val();
                    $.ajax({
                        type: 'GET',
                        data: {nic: enic, pnic: pnic},
                        url: '../PatientServlet',
                        success: function (result) {
                            $('#nicResult').html(result);
                            $('#nicResult').val(result);
                        }
                    });
                });

            });

            $(document).ready(function () {
                $("#patientForm").submit(function (e) {
                    var message = $.trim($("#nicResult").val());

                    if (message === "Already exists") {
                        e.preventDefault();
                    }

                });
            });

            $('#patientModel').on('shown.bs.modal', function () {
                $('#myInput').trigger('focus');
            });


        </script>
    </body>
</html>
