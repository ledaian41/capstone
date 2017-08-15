<%-- 
    Document   : login
    Created on : Jul 5, 2017, 10:23:11 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>House Decor</title>

        <!-- Bootstrap -->
        <link href="admin/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="admin/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <!-- NProgress -->
        <link href="admin/vendors/nprogress/nprogress.css" rel="stylesheet">
        <!-- Animate.css -->
        <link href="admin/vendors/animate.css/animate.min.css" rel="stylesheet">

        <!-- Custom Theme Style -->
        <link href="admin/build/css/custom.min.css" rel="stylesheet">
    </head>

    <body class="login">
        <div>
            <a class="hiddenanchor" id="signup"></a>
            <a class="hiddenanchor" id="signin"></a>

            <div class="login_wrapper">
                <div class="animate form login_form">
                    <section class="login_content">
                        <form action="MainAdminServlet" method="POST">
                            <h1>House Decor</h1>
                            <div>
                                <input type="text" class="form-control" placeholder="Username" required="" id="username" name="txtUsername" />
                            </div>
                            <div>
                                <input type="password" class="form-control" placeholder="Password" required="" id="password" name="txtPassword" />
                            </div>
                            <p style="color: red">${requestScope.ERROR}</p>
                            <div>
                                <button type="submit" class="btn btn-default" name="btnAction" value="Login">Đăng nhập</button>
                            </div>

                            <div class="clearfix"></div>
                        </form>
                    </section>
                </div>
            </div>
        </div>
    </body>
</html>
