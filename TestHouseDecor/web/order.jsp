<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>House Decor: Profile </title>
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FONT AWESOME STYLE CSS -->
        <link href="assets/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" />
        <!-- CUSTOM STYLE CSS -->
        <link href="assets/css/style.css" rel="stylesheet" />    
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
      
        <link href="assets/css/datepicker.css" rel="stylesheet" />
        <script src="assets/plugins/jquery-1.10.2.js"></script> 
<!--        <script src="assets/plugins/bootstrap.js"></script>-->
        <!-- EASING SCROLL SCRIPTS PLUGIN  -->
<!--        <script src="assets/plugins/jquery.easing.min.js"></script>-->
        <!-- CUSTOM SCRIPTS   -->
        <script src="assets/js/custom.js"></script>
<!--        <script src='https://code.jquery.com/jquery-1.10.0.min.js'></script>-->
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/bootstrap-datepicker.js"></script>
<!--        <script src='assets/js/jquery-2.1.1.js'></script>-->
        <script src='assets/js/jquery.validate.js'></script>
        <script src='assets/js/signup-form.js'></script>
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
                        <div class="profile-pic-border" >
                            <img class="profile-pic" width="148" height="165" id="mainUserProfilePic" 
                                 src="assets/icon/common-avatar.png"
                                 oncontextmenu="return false;" onmousedown="preventImageDrag(event);" 
                                 ondragstart="return false;" onselectstart="return false;">
                        </div>
                    </div>
                    <img id="coverImage" class="cover-image " src="assets/img/home-design.jpg" 
                         oncontextmenu="return false;" onmousedown="preventImageDrag(event);" 
                         ondragstart="return false;" onselectstart="return false;">
                        <div class="profile-info">
                            <div class="profile-title">
                                <h1>
                                    <a style="font-size: 25px;line-height: 1.1;text-decoration: none" href="#"><c:out value="${sessionScope.user.firstname}"/> <c:out value="${sessionScope.user.lastname}"/></a>
                                </h1>   
                            </div>
                        </div>
<!--                        <div class="change-cover-photo">
                            <a class="hzBtn" id="changeCoverPhoto" style="text-decoration: none" href="#">
                                <i class="fa fa-camera fa"></i>
                                Change Cover Photo
                            </a>
                        </div>-->
                            <div class="side-bar">

                                    <ul class="list-inline" style="margin-top: 11px;">
                                        <li class=""><div class="profile-pic-placeholder"></div></li>
                                        <li class="sidebar-item">			
                                            <a class="sidebar-item-label-2" href="ProfileServlet">
                                                <span class="option-text">Hồ Sơ</span>
                                            </a>
                                        </li>

                                        <li class="sidebar-item">			
                                            <a class="sidebar-item-label-2" href="MainProfessionalServlet?btnAction=load&txtUserId=${sessionScope.user.userId}">
                                                <span class="option-text">Dự Án</span>
                                            </a>
                                        </li>
                                        <li class="sidebar-item">			
                                            <a class="sidebar-item-label-2" href="IdeaBooksServlet?txtUserID=${user.userId}">
                                                <span class="option-text">Ý Tưởng</span>
                                            </a>
                                        </li>
                                        <li class="sidebar-item">			
                                            <a class="sidebar-item-label-2" href="ShoppingCartServlet?method=purchase" >
                                                <span class="option-text">Mua Hàng</span>
                                            </a>
                                        </li>
                                        
                                        <c:if test="${user.roleId.roleId == 3}">
                                            <li class="sidebar-item">			
                                            <a class="sidebar-item-label-2" href="MyProductServlet?action=show&txtUserID=${user.userId}" >
                                                <span class="option-text">Cửa Hàng</span>
                                            </a>
                                            </li>
                                            <li class="sidebar-item">			
                                                <a class="sidebar-item-label" href="ViewOrderServlet">
                                                    <span class="option-text" style="color: #3d8901">Đơn Hàng</span>
                                                </a>
                                            </li>
                                            
                                        </c:if>
                                    </ul>

                                </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="container" style="background: #ffffff; padding-left: 20px ">
            <div id="coPage">
                <h1 class="header-1 top">Đơn Hàng </h1>
                
                <div class="row" style="padding-bottom: 10px; background-color: #f5f5f5;">
                    
                    <div class="col-lg-2"><b>Ngày đặt</b></div>
                    <div class="col-lg-2">Mã sản phẩm</div>
                    <div class="col-lg-2"><b>Số Lượng</b></div>
                    <div class="col-lg-2"><b>   </b></div>
                    <div class="col-lg-2"><b>Khách Hàng</b></div>
                    <div class="col-lg-2"><b>Điện thoại</b></div>
                    </div>
                <c:forEach var="orderDetail" items="${listDTO}">
                    <div class="row" style="padding-bottom: 10px">        
                        <div class="col-lg-2" style="padding-top: 20px"><b><fmt:formatDate value="${orderDetail.orderId.createdTime}" pattern="dd-MM-yyyy" /></b></div>
                        <div class="col-lg-2" style="padding-top: 20px">${fn:split(orderDetail.productId.productId,'_')[0]}</div>
                        <div class="col-lg-2" style="padding-top: 20px"><b>${orderDetail.quantity}</b></div>
                        <div class="col-lg-2"> <img src="${orderDetail.productId.productPhotoCollection[0].url}" width="100" height="100"></div>
                        <div class="col-lg-2" style="padding-top: 20px"><b>${orderDetail.orderId.userId.email}</b></div>
                        <div class="col-lg-2" style="padding-top: 20px"><b>${orderDetail.orderId.userId.phoneNumber}</b></div>
                    </div>
                </c:forEach>
            </div>
                
            </div>
        </div>
        <!--END PORTFOLIO SECTION-->
        <!--FOOTER-->
        <%@include file="footer.jsp" %>
        <!--END FOOTER-->
        <!-- JAVASCRIPT FILES PLACED AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
        <!-- CORE JQUERY  -->
       
    </body>
</html>
