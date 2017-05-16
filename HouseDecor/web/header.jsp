<%-- 
    Document   : header
    Created on : May 16, 2017, 4:06:41 PM
    Author     : Lê Đại An
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <!--[if IE]>
            <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
            <![endif]-->
        <title>Header</title>
        <!-- BOOTSTRAP CORE STYLE CSS -->
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FONT AWESOME STYLE CSS -->
        <link href="assets/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" />
        <!-- CUSTOM STYLE CSS -->
        <link href="assets/css/style.css" rel="stylesheet" />    
        <!-- GOOGLE FONT -->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    </head>
    <body>
        <!--NAVBAR SECTION-->
        <div class="navbar navbar-inverse navbar-fixed-top scrollclass" >
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">YOUR LOGO</a>
                </div>
                <div class="navbar-collapse collapse">
                    <div class="navbar-brand">
                        <form name="Search" action="Search">
                            <div>
                                <input type="text" name="txtSearch" value="" class="input-search"/>
                            </div>
                        </form>
                    </div>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#home">HOME</a></li>
                        <li>
                            <a href="#portfolio">
                                <i class="fa fa-bell-o"></i> 
                            </a>
                        </li>
                        <li>
                            <a href="">
                                <i class="fa fa-shopping-cart"></i>    
                            </a>
                        </li>
                        <li><a href="#contact">CONTACT</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <!--END NAVBAR SECTION-->
    </body>
</html>
