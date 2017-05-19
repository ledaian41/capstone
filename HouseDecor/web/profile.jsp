<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <!--[if IE]>
            <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
            <![endif]-->
        <title>House Decor: Profile </title>
        <!-- BOOTSTRAP CORE STYLE CSS -->
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FONT AWESOME STYLE CSS -->
        <link href="assets/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" />
        <!-- CUSTOM STYLE CSS -->
        <link href="assets/css/style.css" rel="stylesheet" />    
        <!-- GOOGLE FONT -->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

    </head>
    <body data-spy="scroll" data-target=".navbar-fixed-top"style="background: #f4f4f4 !important;">
        <!-- HEADER -->        
        <%@include file="header.jsp" %>
        <!-- END HEADER -->
        <!--PORTFOLIO SECTION-->
        <div class="container" style="padding-top: 90px">
            <div class="profile-header">
                <div class="profile-cover">
                    <div class="profile-pic-container">
                        <a class="profile-pic-border" href="https://www.houzz.com/pro/cuk3t/manh-hung">
                            <img class="profile-pic" width="148" height="165" id="mainUserProfilePic" 
                                 src="https://st.hzcdn.com/res/2551/pic/user_3.png?v=2551"
                                 oncontextmenu="return false;" onmousedown="preventImageDrag(event);" 
                                 ondragstart="return false;" onselectstart="return false;">
                        </a>
                    </div>
                    <img id="coverImage" class="cover-image " src="assets/img/home-design.jpg" 
                         oncontextmenu="return false;" onmousedown="preventImageDrag(event);" 
                         ondragstart="return false;" onselectstart="return false;">
                    <div class="profile-info">
                        <div class="profile-title">
                            <h1>
                                <a style="font-size: 25px;line-height: 1.1;text-decoration: none" href="#"> Your Name</a>
                            </h1>   
                        </div>
                    </div>
                        <div class="change-cover-photo">
                            <a class="hzBtn" id="changeCoverPhoto" style="text-decoration: none" href="#">
                                <i class="fa fa-camera fa"></i>
                                Change Cover Photo
                            </a>
                        </div>
                        <div class="profile-pro-actions">
                            <a class="btn-white" style="text-decoration: none;" href="#">
                                <i class="fa fa-pencil fa" style="margin-right: 2px;"></i>Edit Profile
                            </a>
                        </div>
                        <div class="side-bar">
                            
                            <ul class="list-inline" style="margin-top: 11px;">
                                    <li class=""><div class="profile-pic-placeholder"></div></li>
                                    <li class="sidebar-item">			
                                        <a class="sidebar-item-label" href="https://www.houzz.com/pro/cuk3t/manh-hung" >
                                            <span class="option-text" style="color: #3d8901">Your Houzz</span>
                                        </a>
                                    </li>
                                    <li class="sidebar-item">			
                                        <a class="sidebar-item-label-2" href="https://www.houzz.com/pro/cuk3t/manh-hung" >
                                            <span class="option-text">Project</span>
                                        </a>
                                    </li>
                                    <li class="sidebar-item">			
                                        <a class="sidebar-item-label-2" href="https://www.houzz.com/pro/cuk3t/manh-hung" >
                                            <span class="option-text">Ideabooks</span>
                                        </a>
                                    </li>
                                    <li class="sidebar-item">			
                                        <a class="sidebar-item-label-2" href="https://www.houzz.com/pro/cuk3t/manh-hung" >
                                            <span class="option-text">Bookmarks</span>
                                        </a>
                                    </li>
                                    <li class="sidebar-item">			
                                        <a class="sidebar-item-label-2" href="https://www.houzz.com/pro/cuk3t/manh-hung" >
                                            <span class="option-text">Purchases</span>
                                        </a>
                                    </li>
                            </ul>
                            
                        </div>
                </div>
               
            </div>
        </div>
        <!--END PORTFOLIO SECTION-->
        <!--FOOTER-->
        <%@include file="footer.jsp" %>
        <!--END FOOTER-->
        <!-- JAVASCRIPT FILES PLACED AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
        <!-- CORE JQUERY  -->
        <script src="assets/plugins/jquery-1.10.2.js"></script>
        <!-- BOOTSTRAP SCRIPTS  -->
        <script src="assets/plugins/bootstrap.js"></script>
        <!-- EASING SCROLL SCRIPTS PLUGIN  -->
        <script src="assets/plugins/jquery.easing.min.js"></script>
        <!-- CUSTOM SCRIPTS   -->
        <script src="assets/js/custom.js"></script>
    </body>
</html>
