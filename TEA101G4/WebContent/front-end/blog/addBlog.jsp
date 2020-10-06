<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.member.model.*"%>

<%	
	BlogVO blogVO = (BlogVO) request.getAttribute("blogVO");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>所有文章資料 - AddBlog.jsp</title>

    <!-- Site Tittle -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <!-- Plugins css Style -->
    <link href="<%=request.getContextPath()%>/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/plugins/no-ui-slider/nouislider.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/assets/plugins/owl-carousel/owl.carousel.min.css" rel="stylesheet"
        media="screen">
    <link href="<%=request.getContextPath()%>/assets/plugins/owl-carousel/owl.theme.default.min.css" rel="stylesheet"
        media="screen">
    <link href="<%=request.getContextPath()%>/assets/plugins/fancybox/jquery.fancybox.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/assets/plugins/isotope/isotope.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/assets/plugins/animate/animate.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/plugins/select2/css/select2.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/plugins/revolution/css/settings.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/plugins/revolution/css/layers.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/plugins/revolution/css/navigation.css" rel="stylesheet">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Dosis:300,400,600,700|Open+Sans:300,400,600,700"
        rel="stylesheet">

    <!-- Custom css -->
    <link href="<%=request.getContextPath()%>/assets/css/kidz.css" id="option_style" rel="stylesheet">

    <!-- Favicon -->
    <link href="<%=request.getContextPath()%>/assets/img/favicon.png" rel="shortcut icon">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

</head>

<body id="body" class="up-scroll" data-spy="scroll" data-target=".element-right-sidebar">
     <!-- ====================================
  ——— HEADER
  ===================================== -->
    <header class="header main-wrapper" id="pageTop">
        <!-- Top Color Bar -->


        <!-- Top Bar-->
        <!-- d-none d-md-block -->
        <div class=" bg-stone  top-bar">
            <div class="container">
                <div class="row">
                    <div class="col-lg-7 d-none d-lg-block">
                    </div>

                    <div class="col-lg-5">
                        <ul
                            class="list-inline d-flex mb-0 justify-content-xl-end justify-content-center align-items-center mr-xl-2">

                            <c:choose>
                                <c:when test="${userVO == null}">
                                    <li class="text-white mr-md-3 mr-lg-2 mr-xl-5">
                                        <img src="<%=request.getContextPath()%>/assets/img/login4.png" width="30px"
                                            height="30px" style="border-radius:100%; magin-right:20px">
                                        <a class="text-white font-weight-medium opacity-80"
                                            href="<%=request.getContextPath()%>/front-end/login.jsp"> Login or Create an
                                            account
                                        </a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="text-white mr-md-3 mr-lg-2 mr-xl-5">
                                        <div data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                            data-display="static">
                                            <img src="<%=request.getContextPath()%>/assets/img/user.png" width="30px"
                                                height="30px" style="border-radius:100%; magin-right:20px">
                                            <!--                                			 加herf連結至個人頁面 -->
                                            <a class="text-white font-weight-medium opacity-80"> ${userVO.name}</a>
                                            <a href="javascript:void(0)">
                                                <span
                                                    class="rounded-sm bg-pink icon-small icon-badge d-none close-icon">
                                                    <i class="fa fa-close text-white" aria-hidden="true"></i>
                                                </span>
                                            </a>
                                        </div>
                                        <div class="dropdown-menu dropdown-menu-right">
                                            <ul class="bg-white list-unstyled">
                                                <a href="product-single.html">
                                                    <li>
                                                        <div class="media">
                                                            <div class="media-body">
                                                                <div class="d-flex justify-content-between">
                                                                    <h4 class="text-dark">Profile</h4>

                                                                </div>
                                                            </div>
                                                            <hr>
                                                    </li>
                                                </a>
                                                <hr>
                                                <a href="<%=request.getContextPath()%>/front-end/chat/index.jsp">
                                                    <li>
                                                        <div class="media">
                                                            <div class="media-body">
                                                                <div class="d-flex justify-content-between">
                                                                    <h4 class="text-dark">Message</h4>

                                                                </div>
                                                            </div>
                                                            <hr>
                                                    </li>
                                                </a>
                                                <hr>

                                                <a href="<%=request.getContextPath()%>/MemberLogout">
                                                    <div>
                                                        <p class="media">
                                                            <div class="media-body">
                                                                <div class="d-flex justify-content-between">
                                                                    <h4 class="text-dark">Logout</h4>

                                                                </div>
                                                        </div>
                                                    </li>
                                                </a>

                                            </ul>
                                        </div>
                                    </li>
                                </c:otherwise>
                            </c:choose>

                        </ul>
                    </div>
                </div>
            </div>
        </div>


        <!-- Navbar -->
        <nav class="navbar navbar-expand-md navbar-scrollUp navbar-sticky navbar-white">
            <div class="container">
                <a class="navbar-brand" href="index.html"> <img class="d-inline-block"
                        src="<%=request.getContextPath()%>/assets/img/gympayz2.png">
                </a>



                <button class="navbar-toggler py-2" type="button" data-toggle="collapse" data-target="#navbarContent"
                    aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fa fa-bars"></i>
                </button>

                <div class="collapse navbar-collapse" id="navbarContent">
                    <ul class="navbar-nav ml-lg-auto">
                        <li class="nav-item dropdown bg-warning"><a class="nav-link dropdown-toggle "
                                href="javascript:void(0)" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false">
                                <i class="fa fa-home nav-icon" aria-hidden="true"></i> <span>Home</span>
                            </a></li>

                        <li class="nav-item dropdown bg-pink"><a class="nav-link dropdown-toggle "
                                href="component-default.html">
                                <i class="fa fa-pencil-square-o nav-icon" aria-hidden="true"></i>
                                <span>News</span>
                            </a></li>

                        <li class="nav-item dropdown bg-danger"><a class="nav-link dropdown-toggle "
                                href="javascript:void(0)" role="button"> <i class="fa fa-list-ul nav-icon"
                                    aria-hidden="true"></i> <span>Schedule</span>
                            </a></li>
                        <li class="nav-item dropdown mega-dropdown bg-success"><a class="nav-link dropdown-toggle "
                                href="#" role="button" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-file-text-o nav-icon" aria-hidden="true"></i> <span>Courses</span>
                            </a></li>
                        <li class="nav-item dropdown bg-info"><a class="nav-link dropdown-toggle "
                                href="javascript:void(0)" id="stores" role="button" aria-haspopup="true"
                                aria-expanded="false"> <i class="fa fa-pencil-square-o nav-icon" aria-hidden="true"></i>
                                <span>Store</span>
                            </a></li>

                        <li class="nav-item dropdown bg-purple">
                            <a class="nav-link dropdown-toggle "
                                href="<%=request.getContextPath()%>/front-end/blog/listAllBlog.jsp" id="stores"
                                role="button" aria-haspopup="true" aria-expanded="false"> <i
                                    class="fa fa-calendar nav-icon" aria-hidden="true"></i> <span>Blog</span>
                            </a>
                        </li>

                        <li class="nav-item dropdown bg-pink"><a class="nav-link dropdown-toggle "
                                href="component-default.html">
                                <i class="fa fa-home nav-icon" aria-hidden="true"></i> <span>Contact
                                    us</span>
                            </a></li>

                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <div class="main-wrapper blog-single-left-sidebar">


        <!-- ====================================
  ———	BREADCRUMB
  ===================================== -->
        <section class="breadcrumb-bg"
            style="background-image: url(<%=request.getContextPath()%>/assets/img/background/headerpic2.png);">
            <div class="container">
                <div class="breadcrumb-holder">
                    <div>
                        <h1 class="breadcrumb-title">Gympayz</h1>
                    </div>
                </div>
            </div>
        </section>

        <!-- ====================================
———	BLOG GRID LEFT SIDEBAR
===================================== -->
        <section class="py-8 py-md-10">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-lg-9 order-md-1">

                        <%-- 錯誤表列 --%>
                        <c:if test="${not empty errorMsgs}">
                            <font style="color: red">請修正以下錯誤:</font>
                            <ul>
                                <c:forEach var="message" items="${errorMsgs}">
                                    <li style="color: red">${message}</li>
                                </c:forEach>
                            </ul>
                        </c:if>

                       <jsp:useBean id="memberSvc" scope="page"
								class="com.member.model.MemberService" />
                        

                        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/BlogServlet" name="form1"
                            enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="exampleFormControlInput1">會員名稱:</label> <input type="TEXT" name="memberid"
                                    size="45" class="form-control"
                                    value="${memberSvc.getOneMember(userVO.memberid).getName()}" readonly />

                            </div>
                            <div class="form-group check-step-gray">
                                <label for="exampleFormControlSelect1">文章分類:</label> <select class="form-control"
                                    name="blogclass" id="addBlog">
                                    <option value="">請選擇</option>
                                    <option value="心得交流">心得交流</option>
                                    <option value="健康食譜">健康食譜</option>
                                    <option value="健身影片">健身影片</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="exampleFormControlInput1">標題:</label> <input type="TEXT" name="title"
                                    size="45" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="exampleFormControlTextarea1">內文:</label>
                                <!--                     <input type="TEXTAREA" name="text" size="45" -->
                                <%-- 					value="<%=(blogVO == null) ? "減肥成功" : blogVO.getText()%>" /> --%>

                                <textarea class="form-control" name="text" id="exampleFormControlTextarea1" rows="6" />
                                </textarea>
                            </div>
                            <div></div>

                            <div class="custom-file" id="showPhoto" style="display:none"
                                style="margin-top: 46px; padding-left: 10.4px;">
                                <input type="file" name="photo" class="custom-file-input" id="photo2">
                                <label class="custom-file-label" for="customFile">Choose
                                    photo</label>
                                    <img width="400" id="photopreview">
                            </div>

                            <div class="custom-file" id="showVideo" style="display:none"
                                style="margin-top: 46px; padding-left: 10.4px;">
                                <input type="file" name="video" class="custom-file-input" id="video2"">
								<label class=" custom-file-label" for="customFile">Choose
                                video</label>
                            </div>

                            <div style=" margin-left:350px">
                                <input type="hidden" name="action" value="insert">
                                <input type="submit" value="submit" class="btn btn-secondary mb-2">
                            </div>

                        </FORM>

                        <!-- 	<FORM METHOD="post" -->
                        <%-- 		ACTION="<%=request.getContextPath()%>/blog/BlogServlet" name="form1" --%>
                        <!-- 		enctype="multipart/form-data"> -->
                        <!-- 		<table> -->
                        <!-- 			<tr> -->
                        <!-- 				<td>一般會員編號:</td> -->
                        <!-- 				<td><input type="TEXT" name="memberid" size="45" -->
                        <%-- 					value="<%=(blogVO == null) ? "M001" : blogVO.getMemberId()%>" /></td> --%>
                        <%-- 				<td><c:forEach var="memberVO" items="${memberSvc.all}"> --%>
                        <%-- 						<c:if test="${blogVO.memberId eq memberVO.memberid}"> --%>
                        <%-- 							${memberVO.name} --%>
                        <%-- 						</c:if> --%>
                        <%-- 					</c:forEach></td> --%>
                        <!-- 			</tr> -->
                        <!-- 			<tr> -->
                        <!-- 				<td>文章分類:</td> -->
                        <!-- 				<td><select name="blogclass" id="addBlog"> -->
                        <!-- 						<option value="">請選擇</option> -->
                        <!-- 						<option value="心得交流">心得交流</option> -->
                        <!-- 						<option value="健康食譜">健康食譜</option> -->
                        <!-- 						<option value="健身影片">健身影片</option> -->
                        <!-- 				</select></td> -->

                        <!-- 			<tr> -->
                        <!-- 				<td>標題:</td> -->
                        <!-- 				<td><input type="TEXT" name="title" size="45" -->
                        <%-- 					value="<%=(blogVO == null) ? "瘦身" : blogVO.getTitle()%>" /></td> --%>
                        <!-- 			</tr> -->
                        <!-- 			<tr> -->
                        <!-- 				<td>內文:</td> -->
                        <!-- 				<td><input type="TEXTAREA" name="text" size="45" -->
                        <%-- 					value="<%=(blogVO == null) ? "減肥成功" : blogVO.getText()%>" /></td> --%>
                        <!-- 			</tr> -->
                        <!-- 			<tr style="display:none" id="showPhoto"> -->
                        <!-- 				<td>照片:</td> -->
                        <!-- 				<td><input type="file" name="photo" id="photo2" size="45" /></td> -->
                        <!-- 			</tr> -->
                        <!-- 			<tr> -->
                        <!-- 				<td></td> -->
                        <!-- 				<td><img width="400" id="photopreview"></td> -->
                        <!-- 			</tr> -->
                        <!-- 			<tr style="display:none" id="showVideo"> -->
                        <!-- 				<td>影片:</td> -->
                        <!-- 				<td><input type="file" name="video" id="video2" size="45" /></td> -->
                        <!-- 			</tr> -->

                        <!-- 		</table> -->
                        <!-- 		<br> <input type="hidden" name="action" value="insert"> <input -->
                        <!-- 			type="submit" value="送出新增"> -->
                        <!-- 	</FORM> -->

                    </div>

                    <div class="col-md-4 col-lg-3">
                        <form class="" action="search-result.html">
                            <div class="card shadow-none bg-transparent">
                                <h3 class="card-header bg-warning font-weight-bold rounded-top text-white">Search</h3>
                                <div class="card-body border border-top-0 rounded-bottom">
                                    <div class="input-group border-bottom pb-3 pt-4">
                                        <input type="text" class="form-control border-0 px-1"
                                            placeholder="Enter Your Search" aria-describedby="basic-addon2"> <span
                                            class="input-group-addon" id="basic-addon2"> <input
                                                class="btn btn-sm btn-warning text-uppercase text-white shadow-sm"
                                                type="submit" value="Search">
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </form>

                        <div class="card shadow-none bg-transparent">
                            <h4 class="card-header font-weight-bold bg-success rounded-top text-white">Categories</h4>
                            <div class="card-body border border-top-0 rounded-bottom">
                                <ul class="list-unstyled mb-0">
                                    <li class="mb-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/blog/BlogServlet?action=getAllExperience&blogClass=心得交流">心得交流</a>
                                    </li>

                                    <li class="my-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/blog/BlogServlet?action=getAllExperience&blogClass=健康食譜">健康食譜</a>
                                    </li>
                                    <li class="my-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/blog/BlogServlet?action=getAllExperience&blogClass=健身影片">健身影片</a>
                                    </li>
                                    <li class="my-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/blog_Save/Blog_SaveServlet?action=getUserSaveBlog&memberId=M001">文章收藏</a>
                                    </li>
                                    <li class="mt-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/front-end/blog/addBlog.jsp">發文</a></li>
                                    <li class="mt-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/blog/BlogServlet?action=getUserBlog&memberId=M001">我的文章</a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="mb-4">
                            <!-- 					<h3 class="bg-purple rounded-top font-weight-bold text-white mb-0 py-2 px-4">Related Course</h3> -->
                            <div class="border border-top-0 rounded">
                                <ul class="list-unstyled mb-0">



                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="mt-6">
                <!-- 				====================================
<!-- ———	PAGINATION -->
                <!-- ===================================== -->
                -->
                <!-- 				<section class="py-5"> -->
                <!-- 					<div class="container"> -->
                <!-- 						<nav aria-label="Page navigation example"> -->
                <!-- 							<nav aria-label="Page navigation example"> -->
                <!-- 								<ul class="pagination justify-content-center align-items-center"> -->
                <!-- 									<li class="page-item"><a class="page-link" href="#"> <i -->
                <!-- 											class="fa fa-arrow-left mr-1" aria-hidden="true"></i> Prev -->
                <!-- 									</a></li> -->
                <!-- 									<li class="page-item"><a class="page-link" href="#">1</a></li> -->
                <!-- 									<li class="page-item"><a class="page-link" href="#">2</a></li> -->
                <!-- 									<li class="page-item"><a class="page-link" href="#">3</a></li> -->
                <!-- 									<li class="page-item"><a class="page-link" href="#">...</a></li> -->
                <!-- 									<li class="page-item"><a class="page-link" href="#">15</a></li> -->
                <!-- 									<li class="page-item"><a class="page-link" href="#"> -->
                <!-- 											Next <i class="fa fa-arrow-right ml-1" aria-hidden="true"></i> -->
                <!-- 									</a></li> -->
                <!-- 								</ul> -->
                <!-- 							</nav> -->
                <!-- 						</nav> -->
                <!-- 					</div> -->
                <!-- 				</section> -->

            </div>
        </section>

    </div>



    <!-- Javascript -->
    <script src="<%=request.getContextPath()%>/assets/plugins/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/owl-carousel/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/fancybox/jquery.fancybox.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/isotope/isotope.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/syotimer/jquery.syotimer.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/select2/js/select2.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/no-ui-slider/nouislider.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/lazyestload/lazyestload.js"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/velocity/velocity.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/images-loaded/js/imagesloaded.pkgd.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/revolution/js/jquery.themepunch.tools.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/revolution/js/jquery.themepunch.revolution.min.js">
    </script>

    <!-- Load revolution slider only on Local File Systems. The following part can be removed on Server -->
    <!-- 
<script src="<%=request.getContextPath()%>/assets/plugins/revolution/js/extensions/revolution.extension.slideanims.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/plugins/revolution/js/extensions/revolution.extension.layeranimation.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/plugins/revolution/js/extensions/revolution.extension.navigation.min.js"></script> 
-->

    <script src="<%=request.getContextPath()%>/assets/plugins/wow/wow.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDU79W1lu5f6PIiuMqNfT1C6M0e_lq1ECY"></script>

    <script src="<%=request.getContextPath()%>/assets/js/kidz.js"></script>


    <script>
        var pictureForm = document.getElementById("photo2");
        pictureForm.addEventListener("change", function () {
            var file = this.files[0];
            var reader = new FileReader();
            reader.onload = function (event) {
                var source = event.target.result;
                var img = document.getElementById("photopreview");
                img.src = source;
            };
            reader.readAsDataURL(file);

        });

        $("#addBlog").change(function () {
            let video = $("#addBlog").val();

            if (video == '健身影片') {
                $("#showPhoto").hide();
                $("#showVideo").show();
            } else {
                console.log('photo');
                $("#showPhoto").show();
                $("#showVideo").hide();
            }

        });
    </script>
</body>


</html>