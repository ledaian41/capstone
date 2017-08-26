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
        <title>House Decor: Photo </title>
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FONT AWESOME STYLE CSS -->
        <link href="assets/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" />
        <!-- CUSTOM STYLE CSS -->
        <link href="assets/css/style.css" rel="stylesheet" />        
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
        <link href="assets/css/datepicker.css" rel="stylesheet" />
        <script src="assets/plugins/jquery-1.10.2.js"></script> 

        <script>
            $(document).ready(function () {
                $('.category').on('change', function () {
                    var category_list = [];
                    $('#filters :input:checked').each(function () {
                        var category = $(this).val();
                        category_list.push(category);
                    });

                    if (category_list.length == 0)
                        $('.resultblock').fadeIn();
                    else {
                        $('.resultblock').each(function () {
                            var item = $(this).attr('data-tag');
                            if (jQuery.inArray(item, category_list) > -1)
                                $(this).fadeIn('slow');
                            else
                                $(this).hide();
                        });
                    }
                });
            });
        </script>
    </head>
    <body data-spy="scroll" data-target=".navbar-fixed-top" style="background: #f4f4f4 !important;">
        <!--NAVBAR SECTION-->
        <%@include file="header.jsp" %>
        <!--END NAVBAR SECTION-->

        <div class="" style="margin-top: 100px;">
            <!--            LeftSide Bar-->
            <nav class="leftSideBar">
                <div class="sidebar">
                    <div class="sidebar-header" style="PADDING-LEFT: 13PX;">Thể loại<span class="toggle down-icon"></span>
                    </div>
                    <div class="sidebar-body">		
                        <ul id="roomFilter">
                            <li class="sidebar-item" compid="opt" posid="0">			
                                <a class="sidebar-item-label_12" href="ShowAllPhotoServlet">
                                    <span class="option-text">Tất cả</span>
                                </a>
                            </li>
                            <c:forEach items="${sessionScope.CATE}" var="category" varStatus="count" step="1">
                                <li class="sidebar-item" compid="opt" posid="${count}">			
                                    <a class="sidebar-item-label_12" href="ShowListPhotoByCategoryServlet?txtCategoryId=${category.categoryId}">
                                        <span class="option-text">${category.categoryName}</span>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="sidebar-header" style="PADDING-LEFT: 13PX;">Phong cách<span class="toggle down-icon"></span>
                    </div>
                    <div class="sidebar-body">
                        <div id="filters">
                            <ul id="filterblock">
                                <c:forEach items="${sessionScope.STYLE}" var="style" varStatus="countStyle" step="1">
                                    <li class="sidebar-item" compid="opt" posid="${countStyle}">			
                                        <input id="check1" type="checkbox" name="check" value="${style.styleId}" class="category">
                                            <span for="check1">${style.name}</span>                 
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </nav>
            <!--        right side content-->
            <div class="rightSideContent">
                <div class="browsePhotos">
                    <div class="headerContainer">
                        <div style="padding: 0px 10px 0px 10px">
                            <h3></h3>
                            <div class="browseListBody">
                                <div id="listContent" class="content-rowxl">


                                </div>
                                <!--                                <div class="content-rowxl">
                                <c:forEach var="dto" items="${listDTO}">
                                    <div class="resultblock" data-tag="${dto.styleId}">
                                        <div class="searchresults imageArea">   
                                            <a href="ShowPhotoDetailServlet?txtPhotoID=${dto.photoID}">
                                                <img src="<c:out value="${dto.url}"/>" width="250px" height="270px" >
                                            </a>
                                            <a href="ViewMyProjectDetailServlet?txtProjectId=${dto.projectID}">
                                                <p style="color: #9f9f9f"><c:out value="${dto.projectName}"/></p>
                                            </a>
                                            <a href="ViewOverServlet?txtID=${dto.userID}">
                                                <h6 style="margin-top: 10px; color: #3d8901"><c:out value="${dto.email}"/></h6>    
                                            </a>
                                        </div>

                                    </div>

                                </c:forEach>
                            </div>-->
                                <div style=" bottom: 4px; position: absolute">
                                    <c:forEach var="page" items="${pages}">
                                        <a style="font-size: 20px; margin-left: 15px" class="pageNum" id="${page}" >${page}</a> 
                                    </c:forEach> <span style="font-size: 20px; margin-left: 10px">Page</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- JAVASCRIPT FILES PLACED AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
        <!-- CORE JQUERY  -->

        <!-- BOOTSTRAP SCRIPTS  -->
        <script src="assets/plugins/bootstrap.js"></script>
        <!-- EASING SCROLL SCRIPTS PLUGIN  -->
        <script src="assets/plugins/jquery.easing.min.js"></script>
        <!-- CUSTOM SCRIPTS   -->
        <script src="assets/js/custom.js"></script>


    </body>
</html>
<script>
            var firstLoad = true;
            var dtos = [
    <c:forEach var="dto" items="${listDTO}">
                {
                    "styleId": "${dto.styleId}",
                    "photoID": "${dto.photoID}",
                    "url": "${dto.url}",
                    "projectID": "${dto.projectID}",
                    "projectName": "${dto.projectName}",
                    "userID": "${dto.userID}",
                    "email": "${dto.email}",
                },
    </c:forEach>
            ];
            function showPage(num) {
                var html = '';
                for (var i = num * 9, max = (num + 1) * 9 < dtos.length ? (num + 1) * 9 : dtos.length; i < max; i++) {
                    var dto = dtos[i];
                    html += '<div class="resultblock" data-tag="' + dto.styleId + '">';
                    html += '<div class="searchresults imageArea">';
                    html += '<a href="ShowPhotoDetailServlet?txtPhotoID=' + dto.photoID + '">';
                    html += '<img src="' + dto.url + '" width="250px" height="270px" >';
                    html += '</a>';
                    html += '<a href="ViewMyProjectDetailServlet?txtProjectId=' + dto.projectID + '">';
                    html += '<p style="color: #9f9f9f">' + dto.projectName + '</p>';
                    html += '</a>';
                    html += '<a href="ViewOverServlet?txtID=' + dto.userID + '">';
                    html += '<h6 style="margin-top: 10px; color: #3d8901">' + dto.email + '</h6>';
                    html += '</a></div></div>';
                }
                $('#listContent').html(html);
                $('.pageNum.selected').removeClass("selected");
                $('.pageNum#' + num).addClass("selected");
            }

            $(document).ready(function () {
                if (firstLoad) {
                    showPage(0);
                    firstLoad = false;
                }

                $('.pageNum').click(function () {
                    debugger;
                    showPage(this.id - 1)
                });

            })

</script>
