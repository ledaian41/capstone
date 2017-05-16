<%-- 
    Document   : header
    Created on : May 16, 2017, 4:06:41 PM
    Author     : Lê Đại An
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
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
            <div class="houzz-header-secondary">
                <ul class="header-nav-rooms">
                    <li class="header-nav-room">
                        <a class="menu-title" href="">
                            <span class="fa fa-picture-o fa">Photos</span>
                        </a>
                    </li>
                    <li class="header-nav-room">
                        <a class="menu-title" href="">
                            <span class="fa fa-archive fa">Shop By Department</span>
                        </a>
                    </li>
                    <li class="header-nav-room">
                        <a class="menu-title" href="">
                            <span class="fa fa-user fa">Find Professional</span>
                        </a>
                    </li>
                    <li class="header-nav-room">
                        <a class="menu-title" href="" style="color: #4cae4c">
                            <span class="fa fa-gift fa">Sale</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <!--END NAVBAR SECTION-->
    </body>
</html>
