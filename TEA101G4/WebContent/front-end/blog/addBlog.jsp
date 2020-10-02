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
<title>所有文章資料 - listAllBlog.jsp</title>

<!-- Site Tittle -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- Plugins css Style -->
<link
	href="http://localhost:8081/TEA101G4/assets/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="http://localhost:8081/TEA101G4/assets/plugins/no-ui-slider/nouislider.min.css"
	rel="stylesheet" />
<link
	href="http://localhost:8081/TEA101G4/assets/plugins/owl-carousel/owl.carousel.min.css"
	rel="stylesheet" media="screen">
<link
	href="http://localhost:8081/TEA101G4/assets/plugins/owl-carousel/owl.theme.default.min.css"
	rel="stylesheet" media="screen">
<link
	href="http://localhost:8081/TEA101G4/assets/plugins/fancybox/jquery.fancybox.min.css"
	rel="stylesheet" />
<link
	href="http://localhost:8081/TEA101G4/assets/plugins/isotope/isotope.min.css"
	rel="stylesheet" />
<link
	href="http://localhost:8081/TEA101G4/assets/plugins/animate/animate.css"
	rel="stylesheet">
<link
	href="http://localhost:8081/TEA101G4/assets/plugins/select2/css/select2.min.css"
	rel="stylesheet">
<link
	href="http://localhost:8081/TEA101G4/assets/plugins/revolution/css/settings.css"
	rel="stylesheet">
<link
	href="http://localhost:8081/TEA101G4/assets/plugins/revolution/css/layers.css"
	rel="stylesheet">
<link
	href="http://localhost:8081/TEA101G4/assets/plugins/revolution/css/navigation.css"
	rel="stylesheet">

<!-- Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Dosis:300,400,600,700|Open+Sans:300,400,600,700"
	rel="stylesheet">

<!-- Custom css -->
<link href="http://localhost:8081/TEA101G4/assets/css/kidz.css"
	id="option_style" rel="stylesheet">

<!-- Favicon -->
<link href="http://localhost:8081/TEA101G4/assets/img/favicon.png"
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
		<div class="color-bars">
			<div class="container-fluid"></div>
		</div>

		<!-- Top Bar-->
		<!-- d-none d-md-block -->
		<div class=" bg-stone  top-bar">
			<div class="container">
				<div class="row">
					<div class="col-lg-7 d-none d-lg-block">
						<ul
							class="list-inline d-flex justify-content-xl-start align-items-center h-100 mb-0">
							<li>
								<!--                     <span class="bg-warning icon-header mr-xl-2"> -->
								<!--                       <i class="fa fa-envelope" aria-hidden="true"></i> -->
								<!--                     </span> --> <!--                     <a href="mailto:info@yourdomain.com" class="mr-lg-4 mr-xl-6 text-white opacity-80">info@yourdomain.com</a> -->
							</li>
							<li>
								<!--                     <span class="bg-success icon-header mr-xl-2"> -->
								<!--                       <i class="fa fa-phone" aria-hidden="true"></i> -->
								<!--                     </span> --> <!--                     <a href="tel:+1 234 567 8900" class="mr-lg-4 mr-xl-6 text-white opacity-80"> +1 234 567 8900 </a> -->
							</li>
							<li class="text-white">
								<!--                     <span class="bg-pink icon-header"> -->
								<!--                       <i class="fa fa-clock-o" aria-hidden="true"></i> -->
								<!--                     </span> --> <!--                     <span class="opacity-80">Open: 9am - 6pm</span> -->
							</li>
						</ul>
					</div>

					<div class="col-lg-5">
						<ul
							class="list-inline d-flex mb-0 justify-content-xl-end justify-content-center align-items-center mr-xl-2">
							<li>
								<!--                     <span class="bg-info icon-header mr-lg-0 mr-xl-1"> -->
								<!--                       <i class="fa fa-globe" aria-hidden="true"></i> -->
								<!--                     </span> -->
							</li>
							<li class="mr-3 mr-md-4 mr-lg-3 mr-xl-5 dropdown dropdown-sm">
								<!--                     <button class="btn btn-link dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" -->
								<!--                       aria-haspopup="true" aria-expanded="false"> Language </button> -->
								<!--                     <div class="dropdown-menu" aria-labelledby="dropdownMenuButton"> -->
								<!--                       <a class="dropdown-item" href="#">English</a> -->
								<!--                       <a class="dropdown-item" href="#">Spanish</a> -->
								<!--                       <a class="dropdown-item" href="#">Hindi</a> -->
								<!--                     </div> -->
							</li>

							<li class="text-white mr-md-3 mr-lg-2 mr-xl-5"><span
								class="bg-purple icon-header mr-1 mr-md-2 mr-lg-1 mr-xl-2">
									<i class="fa fa-unlock-alt text-white font-size-13"
									aria-hidden="true"></i>
							</span> <a class="text-white font-weight-medium opacity-80"
								href="javascript:void(0)" data-toggle="modal"
								data-target="#modal-login"> Login </a> <span
								class="text-white opacity-80">or</span> <a
								class="text-white font-weight-medium opacity-80"
								href="javascript:void(0)" data-toggle="modal"
								data-target="#modal-createAccount">Create an account</a></li>

							<li class="cart-dropdown d-none d-md-block">
								<div data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" data-display="static">
									<a href="javascript:void(0)"> <span
										class="rounded-sm bg-pink icon-small icon-badge d-none close-icon">
											<i class="fa fa-close text-white" aria-hidden="true"></i>
									</span> <span
										class="rounded-sm bg-pink icon-small icon-badge shopping-icon">
											<i class="fa fa-shopping-basket text-white"
											aria-hidden="true"></i> <span class="badge bg-warning">3</span>
									</span>
									</a>
								</div>
								<div class="dropdown-menu dropdown-menu-right">
									<ul class="bg-white list-unstyled">
										<li class="d-flex align-items-center"><i
											class="fa fa-shopping-basket font-size-20 mr-3"
											aria-hidden="true"></i>
											<h3 class="text-capitalize font-weight-bold mb-0">3
												items in your cart</h3></li>
										<hr>
										<li><a href="product-single.html">
												<div class="media">
													<div class="image">
														<img class="bg-light rounded-sm px-5 py-3 mr-4"
															src="http://localhost:8081/TEA101G4/assets/img/products/product-sm.png"
															alt="cart-Image">
													</div>
													<div class="media-body">
														<div class="d-flex justify-content-between">
															<h4 class="text-dark">Barbie Racing Car</h4>
															<span class="cancel"> <i
																class="fa fa-close text-muted" aria-hidden="true"></i>
															</span>
														</div>
														<div class="price">
															<span class="text-warning font-weight-medium">$50</span>
														</div>
														<span class="text-muted font-weight-medium text-muted">Qnt:
															1</span>
													</div>
												</div>
										</a>
											<hr></li>
										<li><a href="product-single.html">
												<div class="media">
													<div class="image">
														<img class="bg-light rounded-sm px-5 py-3 mr-4"
															src="http://localhost:8081/TEA101G4/assets/img/products/product-sm.png"
															alt="cart-Image">
													</div>
													<div class="media-body">
														<div class="d-flex justify-content-between">
															<h4 class="text-dark">Barbie Racing Car</h4>
															<span class="cancel"> <i
																class="fa fa-close text-muted" aria-hidden="true"></i>
															</span>
														</div>
														<div class="price">
															<span class="text-warning font-weight-medium">$50</span>
														</div>
														<span class="text-muted font-weight-medium">Qnt: 1</span>
													</div>
												</div>
										</a>
											<hr></li>
										<li><a href="product-single.html">
												<div class="media">
													<div class="image">
														<img class="bg-light rounded-sm px-5 py-3 mr-4"
															src="http://localhost:8081/TEA101G4/assets/img/products/product-sm.png"
															alt="cart-Image">
													</div>
													<div class="media-body">
														<div class="d-flex justify-content-between">
															<h4 class="text-dark font-weight-bold">Barbie Racing
																Car</h4>
															<span class="cancel"> <i
																class="fa fa-close text-muted" aria-hidden="true"></i>
															</span>
														</div>
														<div class="price">
															<span class="text-warning font-weight-medium">$50</span>
														</div>
														<span class="text-muted font-weight-medium">Qnt: 1</span>
													</div>
												</div>
										</a>
											<hr></li>
										<li>
											<div class="d-flex justify-content-between mb-3">
												<h3 class="cart-total font-weight-bold">Subtotal</h3>
												<h3 class="cart-price font-weight-bold">$150</h3>
											</div>
											<div class="cart-button d-flex justify-content-between">
												<button type="button"
													class="btn btn-danger text-uppercase px-4 shadow-sm mr-3"
													onclick="location.href='product-checkout-step-1.html';">Checkout</button>
												<button type="button"
													class="btn btn-danger text-uppercase px-4 shadow-sm"
													onclick="location.href='product-cart.html';">View
													Cart</button>
											</div>
										</li>
									</ul>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!-- Navbar -->
		<nav
			class="navbar navbar-expand-md navbar-scrollUp navbar-sticky navbar-white">
			<div class="container">
				<a class="navbar-brand" href="index.html"> <img
					class="d-inline-block"
					src="http://localhost:8081/TEA101G4/assets/img/gympayz2.png">
				</a>

				<!-- cart-dropdown -->
				<div class="dropdown cart-dropdown ml-auto mr-5 d-md-none">
					<div data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
						<a href="javascript:void(0)"> <span
							class="rounded-sm bg-pink icon-small icon-badge d-none close-icon">
								<i class="fa fa-close text-white" aria-hidden="true"></i>
						</span> <span
							class="rounded-sm bg-pink icon-small icon-badge shopping-icon">
								<i class="fa fa-shopping-basket text-white" aria-hidden="true"></i>
								<span class="badge bg-warning">3</span>
						</span>
						</a>
					</div>
					<div class="dropdown-menu dropdown-menu-right">
						<ul class="bg-white list-unstyled">
							<li class="d-flex align-items-center"><i
								class="fa fa-shopping-basket font-size-20 mr-3"
								aria-hidden="true"></i>
								<h3 class="text-capitalize font-weight-bold mb-0">3 items
									in your cart</h3></li>
							<hr>
							<li><a href="product-single.html">
									<div class="media">
										<div class="image">
											<img class="mr-4 p-2 bg-light"
												src="http://localhost:8081/TEA101G4/assets/img/products/product-sm.png"
												alt="cart-Image">
										</div>
										<div class="media-body">
											<div class="d-flex justify-content-between">
												<h4 class="text-dark">Barbie Racing Car</h4>
												<span class="cancel"> <i
													class="fa fa-close text-muted" aria-hidden="true"></i>
												</span>
											</div>
											<div class="price">
												<span class="text-warning font-weight-medium">$50</span>
											</div>
											<span class="text-muted font-weight-medium text-muted">Qnt:
												1</span>
										</div>
									</div>
							</a>
								<hr></li>
							<li><a href="product-single.html">
									<div class="media">
										<div class="image">
											<img class="mr-4 p-2 bg-light"
												src="http://localhost:8081/TEA101G4/assets/img/products/product-sm.png"
												alt="cart-Image">
										</div>
										<div class="media-body">
											<div class="d-flex justify-content-between">
												<h4 class="text-dark">Barbie Racing Car</h4>
												<span class="cancel"> <i
													class="fa fa-close text-muted" aria-hidden="true"></i>
												</span>
											</div>
											<div class="price">
												<span class="text-warning font-weight-medium">$50</span>
											</div>
											<span class="text-muted font-weight-medium">Qnt: 1</span>
										</div>
									</div>
							</a>
								<hr></li>
							<li><a href="product-single.html">
									<div class="media">
										<div class="image">
											<img class="mr-4 p-2 bg-light"
												src="http://localhost:8081/TEA101G4/assets/img/products/product-sm.png"
												alt="cart-Image">
										</div>
										<div class="media-body">
											<div class="d-flex justify-content-between">
												<h4 class="text-dark font-weight-bold">Barbie Racing
													Car</h4>
												<span class="cancel"> <i
													class="fa fa-close text-muted" aria-hidden="true"></i>
												</span>
											</div>
											<div class="price">
												<span class="text-warning font-weight-medium">$50</span>
											</div>
											<span class="text-muted font-weight-medium">Qnt: 1</span>
										</div>
									</div>
							</a>
								<hr></li>
							<li>
								<div class="d-flex justify-content-between mb-3">
									<h3 class="cart-total font-weight-bold">Subtotal</h3>
									<h3 class="cart-price font-weight-bold">$150</h3>
								</div>
								<div class="cart-button d-flex justify-content-between">
									<button type="button"
										class="btn btn-danger text-uppercase px-4 shadow-sm mr-3"
										onclick="location.href='product-checkout-step-1.html';">Checkout</button>
									<button type="button"
										class="btn btn-danger text-uppercase px-4 shadow-sm"
										onclick="location.href='product-cart.html';">View
										Cart</button>
								</div>
							</li>
						</ul>
					</div>
				</div>

				<button class="navbar-toggler py-2" type="button"
					data-toggle="collapse" data-target="#navbarContent"
					aria-controls="navbarContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<i class="fa fa-bars"></i>
				</button>

				<div class="collapse navbar-collapse" id="navbarContent">
					<ul class="navbar-nav ml-lg-auto">
						<li class="nav-item dropdown bg-warning"><a
							class="nav-link dropdown-toggle " href="javascript:void(0)"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<i class="fa fa-home nav-icon" aria-hidden="true"></i> <span>Home</span>
						</a></li>

						<li class="nav-item dropdown bg-pink"><a
							class="nav-link dropdown-toggle " href="component-default.html">
								<i class="fa fa-pencil-square-o nav-icon" aria-hidden="true"></i>
								<span>News</span>
						</a></li>

						<li class="nav-item dropdown bg-danger"><a
							class="nav-link dropdown-toggle " href="javascript:void(0)"
							role="button" data-toggle="dropdown"> <i
								class="fa fa-list-ul nav-icon" aria-hidden="true"></i> <span>Schedule</span>
						</a></li>
						<li class="nav-item dropdown mega-dropdown bg-success"><a
							class="nav-link dropdown-toggle " href="#" role="button"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<i class="fa fa-file-text-o nav-icon" aria-hidden="true"></i> <span>Courses</span>
						</a></li>
						<li class="nav-item dropdown bg-info"><a
							class="nav-link dropdown-toggle " href="javascript:void(0)"
							id="stores" role="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <i
								class="fa fa-pencil-square-o nav-icon" aria-hidden="true"></i> <span>Store</span>
						</a></li>

						<li class="nav-item dropdown bg-purple"><a
							class="nav-link dropdown-toggle " href="javascript:void(0)"
							id="stores" role="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <i
								class="fa fa-calendar nav-icon" aria-hidden="true"></i> <span>Blog</span>
						</a></li>

						<li class="nav-item dropdown bg-pink"><a
							class="nav-link dropdown-toggle " href="component-default.html">
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
			style="background-image: url(http://localhost:8081/TEA101G4/assets/img/background/headerpic2.png);">
			<div class="container">
				<div class="breadcrumb-holder">
					<div>
						<h1 class="breadcrumb-title">Gympayz</h1>
						<!-- 						<ul class="breadcrumb breadcrumb-transparent"> -->
						<!-- 							<li class="breadcrumb-item"><a class="text-white" -->
						<!-- 								href="index.html">Home</a></li> -->
						<!-- 							<li class="breadcrumb-item text-white active" aria-current="page"> -->
						<!-- 								Blog Single Left Sidebar</li> -->
						<!-- 						</ul> -->
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
					<table id="table-1">
		<tr>
			<td>
				<h3>文章資料新增 - addBlog.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/front-end/blog/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>新增文章:</h3>

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
	
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/blog/BlogServlet" name="form1"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>一般會員編號:</td>
				<td><input type="TEXT" name="memberid" size="45"
					value="<%=(blogVO == null) ? "M001" : blogVO.getMemberId()%>" /></td>
				<td><c:forEach var="memberVO" items="${memberSvc.all}">
						<c:if test="${blogVO.memberId eq memberVO.memberid}">
							${memberVO.name}
						</c:if>
					</c:forEach></td>
			</tr>
			<tr>
				<td>文章分類:</td>
				<td><select name="blogclass" id="addBlog">
						<option value="">請選擇</option>
						<option value="心得交流">心得交流</option>
						<option value="健康食譜">健康食譜</option>
						<option value="健身影片">健身影片</option>
				</select></td>
				<!-- 				<td><input type="TEXT" name="blogclass" size="45" -->
				<%-- 					value="<%=(blogVO == null) ? "心得交流" : blogVO.getBlogClass()%>" /></td> --%>
			</tr>
			<!-- 			<tr> -->
			<!-- 				<td>發文日期:</td> -->
			<!-- 				<td><input type="TEXT" name="xx" size="45" -->
			<%-- 					value='<fmt:formatDate value="${blogVO.getPostDate()}" pattern="yyyy-MM-dd"/>' --%>
			<!-- 					readonly="readonly" style="background-color: lightgrey" />  -->
			<!-- 					<input type="hidden" name="postdate" size="45" -->
			<%-- 					value='${blogVO.getPostDate()}' /></td> --%>
			<!-- 			</tr> -->

			<tr>
				<td>標題:</td>
				<td><input type="TEXT" name="title" size="45"
					value="<%=(blogVO == null) ? "瘦身" : blogVO.getTitle()%>" /></td>
			</tr>
			<tr>
				<td>內文:</td>
				<td><input type="TEXTAREA" name="text" size="45"
					value="<%=(blogVO == null) ? "減肥成功" : blogVO.getText()%>" /></td>
			</tr>
			<tr style="display:none" id="showPhoto">
				<td>照片:</td>
				<td><input type="file" name="photo" id="photo2" size="45" /></td>
			</tr>
			<tr>
				<td></td>
				<td><img width="400" id="photopreview"></td>
			</tr>
			<tr style="display:none" id="showVideo">
				<td>影片:</td>
				<td><input type="file" name="video" id="video2" size="45" /></td>
			</tr>

			<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
			<!-- 	<tr> -->
			<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
			<!-- 		<td><select size="1" name="deptno"> -->
			<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
			<%-- 				<option value="${deptVO.deptno}" ${(blogVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
			<%-- 			</c:forEach> --%>
			<!-- 		</select></td> -->
			<!-- 	</tr> -->

			<!-- 	<tr> -->
			<!-- 		<td>圖片:</td>> -->
			<!-- 		<td><input type="file" name="picture"/></td>> -->
			<!-- 	</tr>> -->


		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>

					</div>

					<div class="col-md-4 col-lg-3">
						<form class="" action="search-result.html">
							<div class="card shadow-none bg-transparent">
								<h3
									class="card-header bg-warning font-weight-bold rounded-top text-white">Search</h3>
								<div class="card-body border border-top-0 rounded-bottom">
									<div class="input-group border-bottom pb-3 pt-4">
										<input type="text" class="form-control border-0 px-1"
											placeholder="Enter Your Search"
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
									<li class="my-2"><a
										class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
										href="<%=request.getContextPath()%>/blog_Save/Blog_SaveServlet?action=getUserSaveBlog&memberId=M001">文章收藏</a></li>
									<li class="mt-2"><a
										class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
										href="<%=request.getContextPath()%>/front-end/blog/addBlog.jsp">發文</a></li>
									<li class="mt-2"><a
										class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
										href="<%=request.getContextPath()%>/blog/BlogServlet?action=getUserBlog&memberId=M001">我的文章</a></li>
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
<!-- ===================================== --> -->
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
	<script
		src="http://localhost:8081/TEA101G4/assets/plugins/jquery/jquery.min.js"></script>
	<script
		src="http://localhost:8081/TEA101G4/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="http://localhost:8081/TEA101G4/assets/plugins/owl-carousel/owl.carousel.min.js"></script>
	<script
		src="http://localhost:8081/TEA101G4/assets/plugins/fancybox/jquery.fancybox.min.js"></script>
	<script
		src="http://localhost:8081/TEA101G4/assets/plugins/isotope/isotope.min.js"></script>
	<script
		src="http://localhost:8081/TEA101G4/assets/plugins/syotimer/jquery.syotimer.min.js"></script>
	<script
		src="http://localhost:8081/TEA101G4/assets/plugins/select2/js/select2.min.js"></script>
	<script
		src="http://localhost:8081/TEA101G4/assets/plugins/no-ui-slider/nouislider.min.js"></script>
	<script
		src="http://localhost:8081/TEA101G4/assets/plugins/lazyestload/lazyestload.js"></script>
	<script
		src="http://localhost:8081/TEA101G4/assets/plugins/velocity/velocity.min.js"></script>
	<script
		src="http://localhost:8081/TEA101G4/assets/plugins/images-loaded/js/imagesloaded.pkgd.min.js"></script>
	<script
		src="http://localhost:8081/TEA101G4/assets/plugins/revolution/js/jquery.themepunch.tools.min.js"></script>
	<script
		src="http://localhost:8081/TEA101G4/assets/plugins/revolution/js/jquery.themepunch.revolution.min.js"></script>

	<!-- Load revolution slider only on Local File Systems. The following part can be removed on Server -->
	<!-- 
<script src="http://localhost:8081/TEA101G4/assets/plugins/revolution/js/extensions/revolution.extension.slideanims.min.js"></script>
<script src="http://localhost:8081/TEA101G4/assets/plugins/revolution/js/extensions/revolution.extension.layeranimation.min.js"></script>
<script src="http://localhost:8081/TEA101G4/assets/plugins/revolution/js/extensions/revolution.extension.navigation.min.js"></script> 
-->

	<script
		src="http://localhost:8081/TEA101G4/assets/plugins/wow/wow.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDU79W1lu5f6PIiuMqNfT1C6M0e_lq1ECY"></script>

	<script src="http://localhost:8081/TEA101G4/assets/js/kidz.js"></script>
	
	
	<script>
		var pictureForm = document.getElementById("photo2");
		pictureForm.addEventListener("change", function() {
			var file = this.files[0];
			var reader = new FileReader();
			reader.onload = function(event) {
				var source = event.target.result;
				var img = document.getElementById("photopreview");
				img.src = source;
			};
			reader.readAsDataURL(file);

		});
		
		
		$("#addBlog").change(function(){
			let video =$("#addBlog").val();
		
			if(video == '健身影片'){		
				$("#showPhoto").hide();
				$("#showVideo").show();	
			}else{
				console.log('photo');
				$("#showPhoto").show();
				$("#showVideo").hide();
			}
			
		});
		
		
	</script>
</body>


</html>