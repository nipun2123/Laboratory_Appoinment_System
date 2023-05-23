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
                            <h3>52</h3>
                        </div>
                        <div class="w3-clear"></div>
                        <h5 class="w3-center">Patient count</h5>
                    </div>
                </div>
                <div class="w3-quarter">
                    <div class="w3-container w3-blue w3-padding-16">
                        <div class="w3-center">
                            <h3>99</h3>
                        </div>
                        <div class="w3-clear"></div>
                        <h5 class="w3-center">Appointment count</h5>
                    </div>
                </div>
                <div class="w3-quarter">
                    <div class="w3-container w3-teal w3-padding-16">
                        <div class="w3-center">
                            <h3>23</h3>
                        </div>
                        <div class="w3-clear"></div>
                        <h5 class="w3-center">Completed test count</h5>
                    </div>
                </div>
                <div class="w3-quarter">
                    <div class="w3-container w3-orange w3-text-white w3-padding-16">
                        <div class="w3-center">
                            <h3>50000</h3>
                        </div>
                        <div class="w3-clear"></div>
                        <h5 class="w3-center">Income</h5>
                    </div>
                </div>
            </div>

            <hr>
            <div class="w3-container" style="margin-top: 50px">
                
                <p>Appointment count</p>
                <div class="w3-grey">
                    <div class="w3-container w3-center w3-padding w3-green" style="width:80%">+80%</div>
                </div>

                <p>Completed test count</p>
                <div class="w3-grey">
                    <div class="w3-container w3-center w3-padding w3-orange" style="width:25%">+25%</div>
                </div>

            </div>
        </div>

    </body>
</html>
