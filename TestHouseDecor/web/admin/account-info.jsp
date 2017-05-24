<%-- 
    Document   : admin
    Created on : May 23, 2017, 12:58:28 PM
    Author     : Lê Đại An
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" sizes="16x16" href="admin/plugins/images/favicon.png">
        <title>House Decor</title>
        <!-- Bootstrap Core CSS -->
        <link href="admin/html/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Menu CSS -->
        <link href="admin/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
        <!-- toast CSS -->
        <link href="admin/plugins/bower_components/toast-master/css/jquery.toast.css" rel="stylesheet">
        <!-- morris CSS -->
        <link href="admin/plugins/bower_components/morrisjs/morris.css" rel="stylesheet">
        <!-- chartist CSS -->
        <link href="admin/plugins/bower_components/chartist-js/dist/chartist.min.css" rel="stylesheet">
        <link href="admin/plugins/bower_components/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.css" rel="stylesheet">
        <!-- animation CSS -->
        <link href="admin/html/css/animate.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="admin/html/css/style.css" rel="stylesheet">
        <link href="admin/style.css" rel="stylesheet" type="text/css"/>
        <!-- color CSS -->
        <link href="admin/html/css/colors/default.css" id="theme" rel="stylesheet">
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    </head>
    <body class="fix-header">
        <!-- ============================================================== -->
        <!-- Preloader -->
        <!-- ============================================================== -->
        <div class="preloader">
            <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" />
            </svg>
        </div>
        <!-- ============================================================== -->
        <!-- Wrapper -->
        <!-- ============================================================== -->
        <div id="wrapper">
            <!-- ============================================================== -->
            <!-- Topbar header - style you can find in pages.scss -->
            <!-- ============================================================== -->
            <nav class="navbar navbar-default navbar-static-top m-b-0">
                <div class="navbar-header">
                    <div class="top-left-part">
                        <!-- Logo -->
                        <a href="#" style="color: black; padding-left: 32px; font-size: 24px"><strong>House Decor</strong></a>
                    </div>
                    <!-- /Logo -->
                    <ul class="nav navbar-top-links navbar-right pull-right">
                        <li>
                            <a class="profile-pic" href="LogoutAdminServlet"> <img src="admin/plugins/images/users/varun.jpg" alt="user-img" width="36" class="img-circle"><b class="hidden-xs">Log Out</b></a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-header -->
                <!-- /.navbar-top-links -->
                <!-- /.navbar-static-side -->
            </nav>
            <!-- End Top Navigation -->
            <!-- ============================================================== -->
            <!-- Left Sidebar - style you can find in sidebar.scss  -->
            <!-- ============================================================== -->
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav slimscrollsidebar">
                    <div class="sidebar-head">
                        <h3><span class="fa-fw open-close"><i class="ti-close ti-menu"></i></span> <span class="hide-menu">Navigation</span></h3>
                    </div>
                    <ul class="nav" id="side-menu">
                        <li style="padding: 70px 0 0;">
                            <a href="LoadAccountsServlet?status=1" class="waves-effect"><i class="fa fa-user fa-fw" aria-hidden="true"></i>Manage Accounts</a>
                        </li>
                        <li>
                            <a href="#" class="waves-effect"><i class="fa fa-font fa-fw" aria-hidden="true"></i>Manage Photos</a>
                        </li>
                        <li>
                            <a href="#" class="waves-effect"><i class="fa fa-globe fa-fw" aria-hidden="true"></i>Manage Products</a>
                        </li>
                        <li>
                            <a href="#" class="waves-effect"><i class="fa fa-columns fa-fw" aria-hidden="true"></i>Manage Projects</a>
                        </li>
                        <li>
                            <a href="LoadAccountsServlet?status=0" class="waves-effect"><i class="fa fa-info-circle fa-fw" aria-hidden="true"></i>Ban Accounts</a>
                        </li>
                    </ul>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End Left Sidebar -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Page Content -->
            <!-- ============================================================== -->
            <c:set var="dto" value="${requestScope.DETAIL}"/>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-8 col-xs-12">
                            <div class="white-box">
                                <form class="form-horizontal form-material" action="MainAdminServlet" method="POST">
                                    <div class="form-group">
                                        <label class="col-md-12">Full Name</label>
                                        <div class="col-md-12">
                                            <input type="text" value="${dto.lastname} ${dto.firstname}" class="form-control form-control-line" readonly="readonly"> 
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="example-email" class="col-md-12">Birth date</label>
                                        <div class="col-md-12">
                                            <input type="text" value="${dto.dateOfBirth}" class="form-control form-control-line" readonly="readonly"> 
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="example-email" class="col-md-12">Email</label>
                                        <div class="col-md-12">
                                            <input type="email" value="${dto.email}" class="form-control form-control-line" name="example-email" id="example-email"> 
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">Phone No</label>
                                        <div class="col-md-12">
                                            <input type="text" value="${dto.phoneNumber}" class="form-control form-control-line" readonly="readonly">  
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">Address 1</label>
                                        <div class="col-md-12">
                                            <input type="text" value="${dto.primayryAddress}" class="form-control form-control-line" readonly="readonly"> 
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">Address 2</label>
                                        <div class="col-md-12">
                                            <input type="text" value="${dto.sencondAddress}" class="form-control form-control-line" readonly="readonly"> 
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">Message</label>
                                        <div class="col-md-12">
                                            <textarea rows="5" class="form-control form-control-line"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-12">Select Country</label>
                                        <div class="col-sm-12">
                                            <select class="form-control form-control-line">
                                                <option>London</option>
                                                <option>India</option>
                                                <option>Usa</option>
                                                <option>Canada</option>
                                                <option>Thailand</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-12">
                                            <input type="hidden" name="txtUserId" value="${dto.userID}" />
                                            <c:if test="${dto.status==0}">
                                                <button class="btn btn-account" name="btnAction" value="unbanaccount">Unban Account</button>
                                            </c:if>
                                            <c:if test="${dto.status==1}">
                                                <button class="btn btn-account" name="btnAction" value="banaccount">Ban Account</button>
                                            </c:if>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->
                <footer class="footer text-center"> 2017 &copy; Ample Admin brought to you by wrappixel.com </footer>
            </div>
            <!-- ============================================================== -->
            <!-- End Page Content -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- End Wrapper -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- All Jquery -->
        <!-- ============================================================== -->
        <script src="admin/plugins/bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="admin/html/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- Menu Plugin JavaScript -->
        <script src="admin/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
        <!--slimscroll JavaScript -->
        <script src="admin/html/js/jquery.slimscroll.js"></script>
        <!--Wave Effects -->
        <script src="admin/html/js/waves.js"></script>
        <!--Counter js -->
        <script src="admin/plugins/bower_components/waypoints/lib/jquery.waypoints.js"></script>
        <script src="admin/plugins/bower_components/counterup/jquery.counterup.min.js"></script>
        <!-- chartist chart -->
        <script src="admin/plugins/bower_components/chartist-js/dist/chartist.min.js"></script>
        <script src="admin/plugins/bower_components/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.min.js"></script>
        <!-- Sparkline chart JavaScript -->
        <script src="admin/plugins/bower_components/jquery-sparkline/jquery.sparkline.min.js"></script>
        <!-- Custom Theme JavaScript -->
        <script src="admin/html/js/custom.min.js"></script>
        <script src="admin/html/js/dashboard1.js"></script>
        <script src="admin/plugins/bower_components/toast-master/js/jquery.toast.js"></script>
        <script>
            function myFunction() {
                var input, filter, table, tr, td, i;
                input = document.getElementById("myInput");
                filter = input.value.toUpperCase();
                table = document.getElementById("myTable");
                tr = table.getElementsByTagName("tr");
                for (i = 0; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[1];
                    td1 = tr[i].getElementsByTagName("td")[3];
                    if (td || td1) {
                        if (td.innerHTML.toUpperCase().indexOf(filter) > -1 || td1.innerHTML.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                    }
                }
            }
        </script>
    </body>
</html>