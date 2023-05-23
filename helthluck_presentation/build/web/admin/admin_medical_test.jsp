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

        <jsp:include page="admin_navbar.jsp">
            <jsp:param name="medicaltest" value="w3-blue" />
        </jsp:include>
        <jsp:include page="../topbar.jsp"/>


        <div class="w3-main" style="margin-left:300px;margin-top:43px;">


            <div id="accordion">
                <div class="card">
                    <div class="card-header" id="headingOne">
                        <h5 class="mb-0">
                            <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                Test registration
                            </button>
                        </h5>
                    </div>

                    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                        <div class="card-body">
                            <button type="button" id="addTestBtn" class="btn btn-primary" data-toggle="modal" data-target="#testModel">
                                Register test
                            </button>
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#testModel">
                                Update test
                            </button>

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
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headingTwo">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                Test category registration
                            </button>
                        </h5>
                    </div>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                        <div class="card-body">
                            <button type="button" class="btn btn-primary" id="addCategoryBtn" data-toggle="modal" data-target="#testCategoryModel">
                                Register test category
                            </button>
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#testCategoryModel">
                                Update test category
                            </button>

                            <div class="table-responsive">
                                <table id="testCategoryTable" class="table table-striped table-bordered container table-hover " style="width:100%;">
                                    <thead class="thead-light">
                                        <tr>
                                            <th>Test category</th>
                                            <th>Lab no</th>
                                            <th>Maximum count</th>
                                            <th>Technician</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach var="item" items="${medicaltest:getAllTestCategory()}">
                                            <tr>
                                                <td> ${item.testCategory}</td>
                                                <td> ${item.labNo}</td>
                                                <td> ${item.maxCount}</td>
                                                <td> ${item.empName}</td>

                                            </tr>
                                        </c:forEach>
                                    </tbody>   
                                </table>
                            </div>

                        </div>

                    </div>
                </div>

            </div>

            <div class="modal fade" id="testCategoryModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">

                        <div class="modal-body">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <form action="../MedicalTestServlet?type=category" id="categoryForm" method="POST">
                                <div class="form-group">
                                    <label for="testcategory">Test category</label>
                                    <input type="text" class="form-control" maxlength="35" id="testcategory" required name="testcategory" >
                                    <label id="resultTestCategory" style="color: red"></label>
                                    <input type="text" id="pastTestCategory" class="form-control" name="pastTestCategory" style="display: none"  >
                                </div>


                                <div class="form-group">
                                    <label for="labno">Lab no</label>
                                    <input type="text" class="form-control" id="labno" name="labno" required maxlength="10">
                                </div>

                                <div class="form-group">
                                    <label for="maxcount">Maximum patient count</label>
                                    <input type="number" class="form-control" id="maxcount" required name="maxcount">
                                </div>

                                <div class="form-group">
                                    <label for="techniciant">Techniciant</label>
                                    <select class="form-control browser-default custom-select"  id="technician" name="techniciant">
                                        <c:forEach  var="technician" items="${medicaltest:getAllTechnicians()}" >
                                            <option value="${technician}">${technician}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <button type="submit" class="btn btn-primary">Submit</button>

                            </form>

                        </div>
                    </div>
                </div>

            </div>
            
            <div class="modal fade" id="testModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">

                        <div class="modal-body">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <form action="../MedicalTestServlet?type=test" id="testForm" method="POST">

                                <div class="form-group">
                                    <label for="testname">Test name</label>
                                    <input type="text" class="form-control" maxlength="45" required id="testname" name="testname">
                                    <label id="resultTest" style="color: red"></label>
                                    <input type="text" id="pastTest" class="form-control" name="pastTest" style="display: none"  >
                                </div>

                                <div class="form-group">
                                    <label for="testcat">Test category</label>
                                    <select class="form-control browser-default custom-select" id="testcat" name="testcat">
                                        <c:forEach  var="category" items="${medicaltest:getAllCategory()}" >
                                            <option value="${category.testCategory}">${category.testCategory}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="price">Price</label>
                                    <input type="number" class="form-control" required id="price" name="price">
                                </div>


                                <button type="submit" class="btn btn-primary">Submit</button>
                            </form>
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
                    var table = $('#testCategoryTable').DataTable({
                        lengthChange: false,
                        select: true,
                        "paging": false

                    });


                    $('#testCategoryTable tbody').on('click', 'tr', function () {
                        var selectRow = table.rows(this).data()[0];
                        clearFieldsCategory();
                        $("#testcategory").val(selectRow[0]);
                        $("#pastTestCategory").val(selectRow[0]);
                        $("#labno").val(selectRow[1]);
                        $("#maxcount").val(selectRow[2]);
                        $("#technician").val(selectRow[3]);
                    });

                });

                $('#addCategoryBtn').on('click', function () {
                    clearFieldsCategory();
                });

                function clearFieldsCategory() {
                    $("#testcategory").val("");
                    $("#resultTestCategory").val("");
                    $("#resultTestCategory").html("");
                    $("#pastTestCategory").val("");
                    $("#labno").val("");
                    $("#maxcount").val("");
                    $("#technician").val($("#technician option:first").val());
                }

                $(document).ready(function () {

                    $('#testcategory').keyup(function () {
                        var testCat = $('#testcategory').val();
                        var pTestCat = $('#pastTestCategory').val();
                        $.ajax({
                            type: 'GET',
                            data: {testCat: testCat, pTestCat: pTestCat, type: "category"},
                            url: '../MedicalTestServlet',
                            success: function (result) {
                                $('#resultTestCategory').html(result);
                                $('#resultTestCategory').val(result);
                            }
                        });
                    });

                });

                $(document).ready(function () {
                    $("#testForm").submit(function (e) {
                        var message = $.trim($("#resultTest").val());

                        if (message === "Already exists") {
                            e.preventDefault();
                        }

                    });
                });

                $(document).ready(function () {
                    var table = $('#testTable').DataTable({
                        lengthChange: false,
                        select: true,
                        "paging": false

                    });

                    $('#testTable tbody').on('click', 'tr', function () {
                        var selectRow = table.rows(this).data()[0];
                        clearFieldsTest();
                        $("#testname").val(selectRow[0]);
                        $("#pastTest").val(selectRow[0]);
                        $("#testcat").val(selectRow[1]);
                        $("#price").val(selectRow[2]);
                    });
                });

                $('#addTestBtn').on('click', function () {
                    clearFieldsTest();
                });

                function clearFieldsTest() {
                    $("#testname").val("");
                    $("#price").val("");
                    $("#testcat").val($("#testcat option:first").val());
                    $("#resultTest").val("");
                    $("#resultTest").html("");
                    $("#pastTest").val("");
                }

                $(document).ready(function () {

                    $('#testname').keyup(function () {
                        var test = $('#testname').val();
                        var pTest = $('#pastTest').val();
                        $.ajax({
                            type: 'GET',
                            data: {test: test, pTest: pTest, type: "test"},
                            url: '../MedicalTestServlet',
                            success: function (result) {
                                $('#resultTest').html(result);
                                $('#resultTest').val(result);
                            }
                        });
                    });

                });

                $(document).ready(function () {
                    $("#categoryForm").submit(function (e) {
                        var message = $.trim($("#resultTestCategory").val());

                        if (message === "Already exists") {
                            e.preventDefault();
                        }

                    });
                });


                $('#testCategoryModel').on('shown.bs.modal', function () {
                    $('#myInput').trigger('focus');
                });

                $('#testModel').on('shown.bs.modal', function () {
                    $('#myInput').trigger('focus');
                });




            </script>
    </body>
</html>
