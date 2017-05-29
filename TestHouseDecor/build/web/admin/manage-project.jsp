<%-- 
    Document   : manage-member
    Created on : May 25, 2017, 3:21:16 PM
    Author     : Lê Đại An
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Member</title>
        <!-- Bootstrap Core CSS -->
        <link href="admin/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="admin/css/sb-admin.css" rel="stylesheet">
        <!-- Custom Fonts -->
        <link href="admin/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <script src="admin/js/filter.js" type="text/javascript"></script>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <div id="wrapper">
            <!-- Navigation -->
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.html">House Decor</a>
                </div>
                <!-- Top Menu Items -->
                <ul class="nav navbar-right top-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
                        <ul class="dropdown-menu message-dropdown">
                            <li class="message-preview">
                                <a href="#">
                                    <div class="media">
                                        <span class="pull-left">
                                            <img class="media-object" src="http://placehold.it/50x50" alt="">
                                        </span>
                                        <div class="media-body">
                                            <h5 class="media-heading">
                                                <strong>John Smith</strong>
                                            </h5>
                                            <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                            <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="message-preview">
                                <a href="#">
                                    <div class="media">
                                        <span class="pull-left">
                                            <img class="media-object" src="http://placehold.it/50x50" alt="">
                                        </span>
                                        <div class="media-body">
                                            <h5 class="media-heading">
                                                <strong>John Smith</strong>
                                            </h5>
                                            <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                            <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="message-preview">
                                <a href="#">
                                    <div class="media">
                                        <span class="pull-left">
                                            <img class="media-object" src="http://placehold.it/50x50" alt="">
                                        </span>
                                        <div class="media-body">
                                            <h5 class="media-heading">
                                                <strong>John Smith</strong>
                                            </h5>
                                            <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                            <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="message-footer">
                                <a href="#">Read All New Messages</a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b class="caret"></b></a>
                        <ul class="dropdown-menu alert-dropdown">
                            <li>
                                <a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
                            </li>
                            <li>
                                <a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
                            </li>
                            <li>
                                <a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
                            </li>
                            <li>
                                <a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
                            </li>
                            <li>
                                <a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
                            </li>
                            <li>
                                <a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">View All</a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="MainAdminServlet?btnAction=logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav side-nav">
                        <li>
                            <a href="javascript:;" data-toggle="collapse" data-target="#manage-account"><i class="fa fa-fw fa-arrows-v"></i> Manage Accounts <i class="fa fa-fw fa-caret-down"></i></a>
                            <ul id="manage-account" class="collapse">
                                <li>
                                    <a href="MainAdminServlet?btnAction=load&role=1">Member</a>
                                </li>
                                <li>
                                    <a href="MainAdminServlet?btnAction=load&role=2">Professional</a>
                                </li>
                                <li>
                                    <a href="LoadAccountsServlet?status=0">Block List</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript:;" data-toggle="collapse" data-target="#manage-product"><i class="fa fa-fw fa-arrows-v"></i> Manage Products <i class="fa fa-fw fa-caret-down"></i></a>
                            <ul id="manage-product" class="collapse">
                                <li>
                                    <a href="#">Product</a>
                                </li>
                                <li>
                                    <a href="#">Block Product</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript:;" data-toggle="collapse" data-target="#manage-photo"><i class="fa fa-fw fa-arrows-v"></i> Manage Photos <i class="fa fa-fw fa-caret-down"></i></a>
                            <ul id="manage-photo" class="collapse">
                                <li>
                                    <a href="#">Photo</a>
                                </li>
                                <li>
                                    <a href="#">Block Photo</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript:;" data-toggle="collapse" data-target="#manage-project"><i class="fa fa-fw fa-arrows-v"></i> Manage Projects <i class="fa fa-fw fa-caret-down"></i></a>
                            <ul id="manage-project" class="collapse">
                                <li>
                                    <a href="MainAdminServlet?btnAction=loadprojects">Projects</a>
                                </li>
                                <li>
                                    <a href="#">Block Projects</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </nav>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <!-- table -->
                            <!-- ============================================================== -->
                            <div class="row">
                                <div class="col-md-12 col-lg-12 col-sm-12">
                                    <div class="white-box">
                                        <form role="search" class="app-search hidden-sm hidden-xs m-r-10">
                                            <input type="text" placeholder="Search..." class="form-control" style="background-color: #e0f1ff" id="myInput" onkeyup="search()">
                                        </form>
                                        <div class="table-responsive">
                                            <table class="table sortable" id="myTable">
                                                <thead>
                                                    <tr>
                                                        <th>NAME</th>
                                                        <th>PHONE</th>
                                                        <th>EMAIL</th>
                                                        <th>Register Date</th>
                                                        <!--<th>INFO</th>-->
                                                        <!--<th>ACTION</th>-->
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${sessionScope.ACCOUNTLIST}" var="dto" varStatus="count">
                                                        <tr>
                                                            <td class="txt-oflo">${dto.lastname} ${dto.firstname}</td>
                                                            <td class="txt-oflo">${dto.phoneNumber}</td>
                                                            <td class="txt-oflo">${dto.email}</td>
                                                            <td class="txt-oflo"><fmt:formatDate type="date" value = "${dto.registerDate}" /></td>
                                                            <td>
                                                                <form action="MainAdminServlet" method="POST">
                                                                    <input type="hidden" name="txtUserId" value="${dto.userID}" />
                                                                    <button class="button btn-view" name="btnAction" value="ViewDetail">Detail</button>
                                                                </form>
                                                            </td>
                                                            <td>
                                                                <form action="MainAdminServlet" method="POST">
                                                                    <input type="hidden" name="txtUserId" value="${dto.userID}" />
                                                                    <button class="button btn-account" name="btnAction" value="BanAccount">Ban Account</button>
                                                                </form>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <script src="admin/js/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="admin/js/bootstrap.min.js"></script>
    <script>
                                                function search() {
                                                    var input, filter, table, tr, td, i;
                                                    input = document.getElementById("myInput");
                                                    filter = input.value.toUpperCase();
                                                    table = document.getElementById("myTable");
                                                    tr = table.getElementsByTagName("tr");
                                                    for (i = 0; i < tr.length; i++) {
                                                        td = tr[i].getElementsByTagName("td")[0];
                                                        td1 = tr[i].getElementsByTagName("td")[2];
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
