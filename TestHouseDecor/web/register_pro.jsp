<%-- 
    Document   : register_pro
    Created on : Aug 14, 2017, 10:17:12 AM
    Author     : An Lee
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FONT AWESOME STYLE CSS -->
        <link href="assets/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" />
        <!-- CUSTOM STYLE CSS -->
        <link href="assets/css/style.css" rel="stylesheet" />   

        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
        <title>Register</title>

        <link href="assets/css/datepicker.css" rel="stylesheet" />
        <script src='https://code.jquery.com/jquery-1.10.0.min.js'></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/bootstrap-datepicker.js"></script>
        <script src='assets/js/jquery-2.1.1.min.js'></script>
        <script src='assets/js/jquery.validate.js'></script>
        <script src='assets/js/signup-form.js'></script>
    </head>
    <body style="background: #f4f4f4 !important;">
        <%@include file="header.jsp" %>
        <div id="mainArea" style="margin-top: 100px;">
            <div class="wellcome_container">                
                <div class="wellcome_header_container">
                    <div class="wellcome_tour_hearder">Introduce Your self</div>
                </div>
                <div class="Wellcome_body_container">

                    <form id="userDetailsForm" method="POST" action="RegisterServlet" class="form-horizontal">
                        <div class="projectDetails">
                            <div class="user-details-intro">Tell us a little about you</div>
                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center">
                                    Tên đăng nhập
                                </label> 
                                <input required type="email" class="inputReg" id="email" name="email"value="" placeholder="Email">
                                <span><b><c:if test="${emailIsExist!= null}" >
                                            <c:out value="${emailIsExist}"/>
                                        </c:if>
                                    </b></span>
                            </div>
                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center">
                                    Mật khẩu

                                </label>
                                <input required type="password" class="inputReg" id="password" name="password" value="" placeholder="Mật khẩu">
                            </div>
                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center">
                                    Tên
                                    <small>(publicly displayed)</small>
                                </label>
                                <input required type="text" class="inputReg" id="firstName" name="firstName" placeholder="Tên" value="">
                            </div>
                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center">
                                    Họ
                                    <small>(publicly displayed)</small>
                                </label>
                                <input required type="text" class="inputReg" id="lastname" name="lastName" placeholder="Họ" value="">
                            </div>


                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center; margin-right: 30px;">
                                    Ngày tháng năm sinh                              
                                </label>
                                <input required type="text" id="timeCheckIn" class="inputReg" placeholder="Ngày tháng năm sinh" name="birthDay"/>
                            </div>  
                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center; margin-right: 30px;">
                                    Giới tính                               
                                </label>
                                <input type="radio" id="male" name="gender" value="1">Name
                                <input type="radio"  id="famale" name="gender" value="0">Nữ

                            </div>
                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center">
                                    Số điện thoại

                                </label>
                                <input required type="text" class="inputReg" id="phone" name="phone" placeholder="Số điện thoại" value="">
                            </div>
                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center; margin-right: 30px;">
                                    Thành phố                               
                                </label>
                                <select name="city">
                                    <c:forEach var="city" items="${sessionScope.CITY}">
                                        <option value="${city.cityCode}">${city.cityName}</option>
                                    </c:forEach>
                                </select>

                            </div>
                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center">
                                    Địa chỉ
                                </label>
                                <input required type="text" class="inputReg" name="address" placeholder="Địa chỉ" value="">
                            </div>
                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center; margin-right: 30px;">
                                    Công việc                              
                                </label>
                                <select name="txtTypeOfWork">
                                    <c:forEach var="work" items="${sessionScope.WORK}">
                                        <option value="${work.id}">${work.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <input type="hidden" name="txtRoleId" value="2" />

                        <div class="welcome-tour__footer-container">
                            <button class="btn btn-success ">Đăng ký</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>

        <script>
            $(function () {
                var nowTemp = new Date();
                var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);

                var checkin = $('#timeCheckIn').datepicker({
                    onRender: function (date) {
                        return date.valueOf() > now.valueOf() ? 'disabled' : '';
                    },
                    format: 'dd/mm/yyyy'
                }).on('changeDate', function (ev) {}).data('datepicker');

            });
        </script>



        <!-- BOOTSTRAP SCRIPTS  -->
        <script src="assets/plugins/bootstrap.js"></script>
        <!-- EASING SCROLL SCRIPTS PLUGIN  -->
        <script src="assets/plugins/jquery.easing.min.js"></script>
        <!-- CUSTOM SCRIPTS   -->
        <script src="assets/js/custom.js"></script>

    </body>
</html>
