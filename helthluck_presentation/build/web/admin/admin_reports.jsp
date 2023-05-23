<!DOCTYPE html>
<html>
    <head>
        <title>HealthLuck</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
        </style>

    </head>
    <body class="w3-light-grey">

        <jsp:include page="admin_navbar.jsp">
            <jsp:param name="reports" value="w3-blue" />
        </jsp:include>
        <jsp:include page="../topbar.jsp"/>


        <div class="w3-main" style="margin-left:300px; margin-top:43px;">

            <a type="button" id="registerBtn" class="btn btn-primary" href="../reports/appointment.jsp" target="_blank"  style="margin-top:33px; margin-left: 10px;">
                Appointment Report
            </a>
            <a type="button" id="registerBtn" class="btn btn-primary" href="../reports/patient.jsp" target="_blank"   style="margin-top:33px; margin-left: 10px;">
                Patient Report
            </a>

            <a type="button" id="registerBtn" class="btn btn-primary" href="../reports/medical_test.jsp" target="_blank"  style="margin-top:33px; margin-left: 10px;">
                Medical Test Report
            </a>
            <a type="button" id="registerBtn" class="btn btn-primary" href="../reports/employee.jsp" target="_blank"  style="margin-top:33px; margin-left: 10px;">
                Employee Report
            </a>
            <a type="button" id="registerBtn" class="btn btn-primary" href="../reports/useraccount.jsp" target="_blank"  style="margin-top:33px; margin-left: 10px;">
                User Account Report
            </a>
        </div>


    </body>
</html>
