<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.blog_mes.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	String flag = (String) request.getAttribute("flag");
	List<BlogVO> list = null;
	if (flag == null) {
		BlogService blogSvc = new BlogService();
		list = blogSvc.getAll();
	} else {
		list = (List<BlogVO>) request.getAttribute("list");
	}
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>所有文章資料 - listAllBlog.jsp</title>

<!-- Site Tittle -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- Plugins css Style -->
<link
	href="<%=request.getContextPath()%>/assets/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/assets/plugins/no-ui-slider/nouislider.min.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/assets/plugins/owl-carousel/owl.carousel.min.css"
	rel="stylesheet" media="screen">
<link
	href="<%=request.getContextPath()%>/assets/plugins/owl-carousel/owl.theme.default.min.css"
	rel="stylesheet" media="screen">
<link
	href="<%=request.getContextPath()%>/assets/plugins/fancybox/jquery.fancybox.min.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/assets/plugins/isotope/isotope.min.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/assets/plugins/animate/animate.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/assets/plugins/select2/css/select2.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/assets/plugins/revolution/css/settings.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/assets/plugins/revolution/css/layers.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/assets/plugins/revolution/css/navigation.css"
	rel="stylesheet">

<!-- Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Dosis:300,400,600,700|Open+Sans:300,400,600,700"
	rel="stylesheet">

<!-- Custom css -->
<link href="<%=request.getContextPath()%>/assets/css/kidz.css"
	id="option_style" rel="stylesheet">

<!-- Favicon -->
<link href="<%=request.getContextPath()%>/assets/img/favicon.png"
	rel="shortcut icon">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

</head>
<body id="body" class="up-scroll" data-spy="scroll"
	data-target=".element-right-sidebar">
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



<!-- 				<h4>此頁練習採用 EL 的寫法取值:</h4> -->
<!-- 				<table id="table-1"> -->
<!-- 					<tr> -->
<!-- 						<td> -->
<!-- 							<h3>所有文章資料 - listAllBlog.jsp</h3> -->
<!-- 							<h4> -->
<!-- 								<a -->
<%-- 									href="<%=request.getContextPath()%>/front-end/blog/select_page.jsp">回首頁</a> --%>
<!-- 							</h4> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 				</table> -->

<!-- 				 錯誤表列 -->
<%-- 				<c:if test="${not empty errorMsgs}"> --%>
<!-- 		 			<font style="color: red">請修正以下錯誤:</font>  -->
<!-- 					<ul>  -->
<%-- 						<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 							<li style="color: red">${message}</li> --%>
<%-- 						</c:forEach> --%>
<!-- 					</ul>  -->
<%-- 				</c:if> --%>

<!-- 				<table> -->
<!-- 					<tr> -->
<!-- 						<th>文章編號</th> -->
<!-- 						<th>一般會員編號</th> -->
<!-- 						<th>文章分類</th> -->
<!-- 						<th>發文日期</th> -->
<!-- 						<th>標題</th> -->
<!-- 						<th>內文</th> -->
<!-- 								<th>影片</th> -->
<!-- 						<th>文章狀態</th> -->
<!-- 						<th>更新日期</th> -->
<!-- 						<th>照片</th> -->
<!-- 						<th>影片</th> -->
<!-- 						<th>修改</th> -->
<!-- 						<th>刪除</th> -->
<!-- 					</tr> -->
<%-- 					<%@ include file="page1.file"%> --%>
<%-- 						<c:forEach var="blogVO" items="${list}"> --%>
<%-- <%-- 				<c:forEach var="blogVO" items="${list}" begin="<%=pageIndex%>"end="<%=pageIndex+rowsPerPage-1%>">  --%> --%>

<!-- 						<tr> -->
<%-- 							<td>${blogVO.blogno}</td> --%>
<%-- 							<td>${blogVO.memberId}</td> --%>
<%-- 							<td>${blogVO.blogClass}</td> --%>
<%-- 							<td>${blogVO.postDate}</td> --%>
<%-- 							<td>${blogVO.title}</td> --%>
<%-- 							<td>${blogVO.text}</td> --%>
<%-- 										<td>${blogVO.photo}</td> --%>
<%-- 										<td>${blogVO.video}</td> --%>
<%-- 							<td>${blogVO.status}</td> --%>
<%-- 							<td>${blogVO.updateTime}</td> --%>
<!-- 							<td><img class="blog" width="400" -->
<%-- 								src="<%=request.getContextPath()%>/blog/ShowPicture?blogno=${blogVO.blogno}"></td> --%>
<!-- 							<td><video width="400" height="240" controls class="blog"> -->
<!-- 									<source -->
<%-- 										src="<%=request.getContextPath()%>/blog/ShowVideo?blogno=${blogVO.blogno}" --%>
<!-- 										type="video/mp4"> -->
<!-- 								</video></td> -->
<!-- 							<td> -->
<!-- 								<FORM METHOD="post" -->
<%-- 									ACTION="<%=request.getContextPath()%>/blog/BlogServlet" --%>
<!-- 									style="margin-bottom: 0px;"> -->
<!-- 									<input type="submit" value="修改"> <input type="hidden" -->
<%-- 										name="blogno" value="${blogVO.blogno}"> <input --%>
<!-- 										type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 								</FORM> -->
<!-- 							</td> -->
<!-- 							<td> -->
<!-- 								<FORM METHOD="post" -->
<%-- 									ACTION="<%=request.getContextPath()%>/blog/BlogServlet" --%>
<!-- 									style="margin-bottom: 0px;"> -->
<!-- 									<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 										name="blogno" value="${blogVO.blogno}"> <input --%>
<!-- 										type="hidden" name="action" value="delete"> -->
<!-- 								</FORM> -->
<!-- 							</td> -->
<!-- 						</tr> -->
<%-- 					</c:forEach> --%>
<!-- 				</table> -->
<%-- <%-- 				<%@ include file="page2.file"%> --%> --%>


		<!-- ====================================
———	BLOG GRID LEFT SIDEBAR
===================================== -->

		<section class="py-8 py-md-10">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-lg-9 order-md-1">
						<div class="row">
							<jsp:useBean id="memberSvc" scope="page"
								class="com.member.model.MemberService" />

							<jsp:useBean id="blogmesSvc" scope="page"
								class="com.blog_mes.model.Blog_MesService" />
								
							<c:forEach var="blogVO" items="${list}">
								<div class="col-md-6 col-lg-4">
									<div class="card">
										<div class="position-relative">
											<c:choose>
												<c:when test='${blogVO.blogClass eq "健身影片"}'>
													<video class="card-img-top" width="240" height="180"
														controls alt="Card image">
														<source
															src="<%=request.getContextPath()%>/blog/ShowVideo?blogno=${blogVO.blogno}"
															type="video/mp4">
													</video>
												</c:when>
												<c:otherwise>
													<a href="blog-single.html"> <img class="card-img-top"
														width="240" height="180"
														src="<%=request.getContextPath()%>/blog/ShowPicture?blogno=${blogVO.blogno}"
														alt="Card image">
													</a>

												</c:otherwise>
											</c:choose>
											<div class="card-img-overlay p-0">
												<span class="badge badge-rounded badge-warning m-4"><fmt:formatDate
														value="${blogVO.postDate}" pattern="MMM" /><br> <fmt:formatDate
														value="${blogVO.postDate}" pattern="d日" /> </span>
											</div>
										</div>

										<div
											class="card-body border-top-5 px-3 rounded-bottom border-warning">
											<h3 class="card-title text-capitalize">
												<a class="text-warning d-block text-truncate"
													href="<%=request.getContextPath()%>/blog/BlogServlet?action=getOne_For_Display&blogno=${blogVO.blogno}">${blogVO.title}</a>
											</h3>
											<ul
												class="list-unstyled d-flex flex-md-column  flex-xl-row mb-1">

												<li class="mr-2"><a class="text-muted"
													href="blog-single.html"> <i class="fa fa-user mr-2"
														aria-hidden="true"></i>${memberSvc.getOneMember(blogVO.memberId).getName()}
												</a></li>

												<li class="mr-2"><a class="text-muted"
													href="blog-single.html"> <i
														class="fa fa-comments-o mr-2" aria-hidden="true"></i>${blogmesSvc.getOneBlognoMes(blogVO.blogno).size() }
														Comments
												</a></li>
											</ul>
											<p class="mb-2">${blogVO.text}</p>
											<a class="btn btn-link text-hover-warning pl-0"
												href="<%=request.getContextPath()%>/blog/BlogServlet?action=getOne_For_Display&blogno=${blogVO.blogno}">
												<i class="fa fa-angle-double-right mr-1" aria-hidden="true"></i>
												Read More
											</a>
										</div>
									</div>
								</div>
							</c:forEach>

							<div class="col-md-6 col-lg-4"></div>

							<div class="col-md-6 col-lg-4"></div>

							<div class="col-md-6 col-lg-4"></div>

							<div class="col-md-6 col-lg-4"></div>

							<div class="col-md-6 col-lg-4"></div>

							<div class="col-md-6 col-lg-4"></div>

							<div class="col-md-6 col-lg-4"></div>

							<div class="col-md-6 col-lg-4"></div>
						</div>
					</div>

					<div class="col-md-4 col-lg-3">
						<form method="post" class="" action="<%=request.getContextPath()%>/blog/BlogServlet?action=searchBlog">
							<div class="card shadow-none bg-transparent">
								<h3
									class="card-header bg-warning font-weight-bold rounded-top text-white">Search</h3>
								<div class="card-body border border-top-0 rounded-bottom">
									<div class="input-group border-bottom pb-3 pt-4">
										<input type="text" class="form-control border-0 px-1"
											placeholder="Enter Your Search" name="searchWord"
											aria-describedby="basic-addon2"> <span
											class="input-group-addon" id="basic-addon2"> <input
											class="btn btn-sm btn-warning text-uppercase text-white shadow-sm"
											type="submit" value="Search">
										</span>
									</div>
								</div>
							</div>
						</form>

						<div class="card shadow-none bg-transparent">
							<h4
								class="card-header font-weight-bold bg-success rounded-top text-white">Categories</h4>
							<div class="card-body border border-top-0 rounded-bottom">
								<ul class="list-unstyled mb-0">
									<li class="mb-2"><a
										class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
										href="<%=request.getContextPath()%>/blog/BlogServlet?action=getAllExperience&blogClass=心得交流">心得交流</a></li>

									<li class="my-2"><a
										class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
										href="<%=request.getContextPath()%>/blog/BlogServlet?action=getAllExperience&blogClass=健康食譜">健康食譜</a></li>
									<li class="my-2"><a
										class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
										href="<%=request.getContextPath()%>/blog/BlogServlet?action=getAllExperience&blogClass=健身影片">健身影片</a></li>
									<li class="my-2">
												<a class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
													href="<%=request.getContextPath()%>/front-end/blog_save/listAllBlog_Save.jsp">
													文章收藏
												</a>
									</li>
									<li class="mt-2">
											<a class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
												href="<%=request.getContextPath()%>/front-end/blog/addBlog.jsp">
												發文
											</a>
									</li>
									<li class="mt-2">
										<a class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
											href="<%=request.getContextPath()%>/front-end/blog/userArticle.jsp">
											我的文章
										</a>
									</li>
									<li class="mt-2"><a
										class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
										href="<%=request.getContextPath()%>/front-end/chat/index.jsp">訊息</a></li>
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
				<!-- ====================================
———	PAGINATION
===================================== -->
				<section class="py-5">
					<div class="container">
						<nav aria-label="Page navigation example">
							<nav aria-label="Page navigation example">
								<ul class="pagination justify-content-center align-items-center">
									<li class="page-item"><a class="page-link" href="#"> <i
											class="fa fa-arrow-left mr-1" aria-hidden="true"></i> Prev
									</a></li>
									<li class="page-item"><a class="page-link" href="#">1</a></li>
									<li class="page-item"><a class="page-link" href="#">2</a></li>
									<li class="page-item"><a class="page-link" href="#">3</a></li>
									<li class="page-item"><a class="page-link" href="#">...</a></li>
									<li class="page-item"><a class="page-link" href="#">15</a></li>
									<li class="page-item"><a class="page-link" href="#">
											Next <i class="fa fa-arrow-right ml-1" aria-hidden="true"></i>
									</a></li>
								</ul>
							</nav>
						</nav>
					</div>
				</section>

			</div>
		</section>

	</div>
	<!-- element wrapper ends -->

  <!-- ====================================
      ———	FOOTER
      ===================================== -->
    <footer class="footer-bg-img">
        <!-- Footer Color Bar -->


        <!-- Copy Right -->
        <div class="copyright">
            <div class="container">
                <div class="row py-4 align-items-center">
                    <div class="col-sm-7 col-xs-12 order-1 order-md-0">
                        <p class="copyright-text"> © 2020 Copyright Gympayz by TEA101G4. <a
                                href="http://www.iamabdus.com/" target="_blank"></a></p>
                    </div>

                </div>
            </div>
        </div>
    </footer>

    <!--scrolling-->
    <div class="scrolling">
        <a href="#pageTop" class="back-to-top" id="back-to-top" style="opacity: 1;">
            <i class="fa fa-arrow-up" aria-hidden="true"></i>
        </a>
    </div>






	<!-- Javascript -->
	<script
		src="<%=request.getContextPath()%>/assets/plugins/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/plugins/owl-carousel/owl.carousel.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/plugins/fancybox/jquery.fancybox.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/plugins/isotope/isotope.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/plugins/syotimer/jquery.syotimer.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/plugins/select2/js/select2.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/plugins/no-ui-slider/nouislider.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/plugins/lazyestload/lazyestload.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/plugins/velocity/velocity.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/plugins/images-loaded/js/imagesloaded.pkgd.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/plugins/revolution/js/jquery.themepunch.tools.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/plugins/revolution/js/jquery.themepunch.revolution.min.js"></script>

	<!-- Load revolution slider only on Local File Systems. The following part can be removed on Server -->
	<!-- 
<script src="<%=request.getContextPath()%>/assets/plugins/revolution/js/extensions/revolution.extension.slideanims.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/plugins/revolution/js/extensions/revolution.extension.layeranimation.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/plugins/revolution/js/extensions/revolution.extension.navigation.min.js"></script> 
-->

	<script
		src="<%=request.getContextPath()%>/assets/plugins/wow/wow.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDU79W1lu5f6PIiuMqNfT1C6M0e_lq1ECY"></script>

	<script src="<%=request.getContextPath()%>/assets/js/kidz.js"></script>
	<script>
		$(function(){
			<c:if test="${not empty errorMsgs}"> 
				<c:forEach var="message" items="${errorMsgs}"> 
			 		alert(${message});
				</c:forEach>	
			</c:if> 
		});
	
	</script>
</body>
</html>