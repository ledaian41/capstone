<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="assets/css/style.css" rel="stylesheet" />
        <title>Admin</title>
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
        <link href="assets/css/datepicker.css" rel="stylesheet" />
        <script src='https://code.jquery.com/jquery-1.10.0.min.js'></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/bootstrap-datepicker.js"></script>
        <script src='assets/js/jquery-2.1.1.min.js'></script>
        <script src='assets/js/jquery.validate.js'></script>
        <script src='assets/js/signup-form.js'></script>
    </head>
    <body style="background: #f4f4f4 !important;">

        <div id="mainArea">
            <div class="wellcome_container">                
                <div class="wellcome_header_container">
                    <div class="wellcome_tour_hearder">Introduce Your self</div>
                </div>
                <div class="Wellcome_body_container">

                    <form id="userDetailsForm" method="post" action="RegisterServlet" class="form-horizontal">
                        <div class="projectDetails">
                            <div class="user-details-intro">Tell us a little about you</div>
                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center">
                                    Email

                                </label> 
                                <input required type="email" class="inputReg" id="email" name="email"value="" placeholder="Email">
                                <span><b><c:if test="${emailIsExist!= null}" >
                                            <c:out value="${emailIsExist}"/>
                                        </c:if>
                                    </b></span>
                            </div>
                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center">
                                    Password

                                </label>
                                <input required type="password" class="inputReg" id="password" name="password" value="" placeholder="Password">
                            </div>
                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center">
                                    First Name
                                    <small>(publicly displayed)</small>
                                </label>
                                <input required type="text" class="inputReg" id="firstName" name="firstName" placeholder="First Name" value="">
                            </div>
                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center">
                                    Last Name
                                    <small>(publicly displayed)</small>
                                </label>
                                <input required type="text" class="inputReg" id="lastname" name="lastName" placeholder="Last Name" value="">
                            </div>


                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center; margin-right: 30px;">
                                    BirthDay                              
                                </label>
                                <input required type="text" id="timeCheckIn" class="inputReg" placeholder="Birthday" name="birthDay"/>
                            </div>  
                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center; margin-right: 30px;">
                                    Gender                               
                                </label>
                                <input type="radio" id="male" name="gender" value="1">Male
                                <input type="radio"  id="famale" name="gender" value="0">Famale

                            </div>
                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center">
                                    Phone

                                </label>
                                <input required type="text" class="inputReg" id="phone" name="phone" placeholder="Phone" value="">
                            </div>
                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center; margin-right: 30px;">
                                    City                               
                                </label>
                                <select name="city">
                                    <option value="HCM">Ho Chi Minh</option>
                                    <option value="HN">Ha Noi</option>
                                    <option value="DN">Da Nang</option>

                                </select>

                            </div>
                            <div class="rowReg" style="margin: auto">
                                <label  class="lableInput" style="text-align: center">
                                    Address
                                </label>


                                <textarea rows="2" cols="37" value="" name="address"></textarea>
                            </div>
                        </div>
                        <div class="welcome-tour__footer-container">
                            <button class="btn btn-success ">Register</button>
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
                    format:'dd/mm/yyyy'
                }).on('changeDate', function (ev) {}).data('datepicker');
                
            });
        </script>




    </body>
</html>