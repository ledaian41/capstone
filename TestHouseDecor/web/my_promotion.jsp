<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/list.js/1.5.0/list.min.js"></script>
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
                                 src="https://st.hzcdn.com/res/2551/pic/user_3.png?v=2551"
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
                                    <a style="font-size: 25px;line-height: 1.1;text-decoration: none" href="#"><c:out value="${userDTO.firstname}"/> <c:out value="${userDTO.lastname}"/></a>
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
                                    <a class="sidebar-item-label-2" href="ProfileServlet" >
                                        <span class="option-text">Hồ Sơ</span>
                                    </a>
                                </li>

                                <li class="sidebar-item">			
                                    <a class="sidebar-item-label-2" href="MainProfessionalServlet?btnAction=load&txtUserId=${sessionScope.user.userId}" >
                                        <span class="option-text">Dự Án</span>
                                    </a>
                                </li>
                                <li class="sidebar-item">			
                                    <a class="sidebar-item-label-2" href="IdeaBooksServlet?txtUserID=${user.userId}">
                                        <span class="option-text">Ý Tưởng</span>
                                    </a>
                                </li

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
                                        <a class="sidebar-item-label-2" href="ViewOrderServlet" >
                                            <span class="option-text">Đơn Hàng</span>
                                        </a>
                                    </li>
                                </c:if>                                               
                            </ul>
                        </div>                        
                </div>

            </div>
        </div>
        <div class="container">
            <div class="container" style="background: #ffffff">
                <div id="coPage">
                    <h1 class="header-1 top">Promotion</h1>
                    <button data-toggle="modal" data-target="#myModal" style="margin-top: 6px; margin-bottom: 10px">
                        Tạo Promotion
                    </button>
                    <div class="coBox shoppingCart">
                        <div class="coBoxHead">
                            <div class="itemImage"><b>Tên promotion</b></div>
                            <div class="itemDetail">&nbsp;</div>
                            <div class="itemPrice"><b>Ngày bắt đầu</b></div>
                            <div class="itemQuantity"><b>Ngày kết thúc</b></div>
                        </div>                                
                    </div>

                    <c:forEach var="dto" items="${listDTO}">                       
                        <div class="coBox shoppingCart">
                            <div class="coBoxHead" style="background-color: #ffffff;">
                                <div class="itemImage"><b>${dto.name}</b></div>
                                <div class="itemDetail">${dto.description}</div>
                                <div class="itemPrice"><b><fmt:formatDate pattern = "dd-MM-yyyy" value = "${dto.startDate}" /></b></div>
                                <div class="itemQuantity"><b><fmt:formatDate pattern = "dd-MM-yyyy" value = "${dto.endDate}" /></b></div>
                                <div class="itemXoa">
                                    <a href="PromotionServlet?action=delete&txtPromotionId=${dto.id}"> Xóa</a>
                                </div>
                            </div>                                
                        </div>                       
                    </c:forEach>
                </div>
            </div>
        </div>
        <form action="PromotionServlet?action=create" method="POST">
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title" id="myModalLabel">Thêm Promotion</h4>
                        </div>
                        <div class="modal-body">
                            <div class="newGalleryFormBody">
                                <div class="row">
                                    <span class="dateNgoc" style="width: 29%; margin-left: 15px;"></span>
                                    <span class="dateNgoc">Ngày bắt đầu</span>
                                    <span class="dateNgoc">Ngày kết thúc</span>
                                </div>
                                <div class="row">                                   
                                    <input class="form-control-3" style="width: 29%; margin-left: 15px;" type="text" id="newGalleryName" placeholder="Tên promotion" name="promotion"  required=""/>   
                                    <input class="form-control-3" style="width: 29%" type="date" id="newGalleryName" placeholder="Ngày bắt đầu" name="startDate" required=""/>
                                    <input class="form-control-3" style="width: 29%" type="date" id="newGalleryName" placeholder="Ngày kết thúc" name="endDate"  required=""/>                           
                                </div>
                                <input type="hidden" id="allowDuplicate" name="allowDuplicate" value="false" >
                                    <textarea rows="5" class="form-control" name="description"></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Hủy</button>
                            <button class="btn btn-primary">Tạo Promotion</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <!--                        end modal-->
        <!--END PORTFOLIO SECTION-->
        <!--FOOTER-->
        <%@include file="footer.jsp" %>
        <!--END FOOTER-->
        <!-- JAVASCRIPT FILES PLACED AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
        <!-- CORE JQUERY  -->

    </body>
</html>
