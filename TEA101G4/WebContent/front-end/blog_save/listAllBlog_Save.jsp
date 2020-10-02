<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog_save.model.*"%>
<%
	List<Blog_SaveVO> list = (List<Blog_SaveVO>) request.getAttribute("userSaveList");
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">
<head>

<!-- Site Tittle -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Blog Grid Left Sidebar</title>

<!-- Plugins css Style -->
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

		<h4>此頁練習採用 EL 的寫法取值:</h4>
		<table id="table-1">
			<tr>
				<td>
					<h3>所有部落格收藏資料 - listAllBlog_Save.jsp</h3>
					<h4>
						<a
							href="<%=request.getContextPath()%>/front-end/blog/select_page.jsp">回首頁
						</a>
					</h4>
				</td>
			</tr>
		</table>


		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<table>
			<tr>
				<th>文章收藏編號</th>
				<th>一般會員編號</th>
				<th>文章編號</th>
				<th>收藏狀態</th>
				<th>收藏日期</th>
				<th>更新日期</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>
			<%-- 	<%@ include file="page1.file" %>  --%>

			<%-- 	<c:forEach var="blog_SaveVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
			<c:forEach var="blog_SaveVO" items="${userSaveList}">

				<tr>
					<td>${blog_SaveVO.blogSaveno}</td>
					<td>${blog_SaveVO.memberId}</td>
					<td>${blog_SaveVO.blogno}</td>
					<td>${blog_SaveVO.status}</td>
					<td>${blog_SaveVO.saveDate}</td>
					<td>${blog_SaveVO.updateTime}</td>


					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/blog_Save/Blog_SaveServlet"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改"> <input type="hidden"
								name="blogSaveno" value="${blog_SaveVO.blogSaveno}"> <input
								type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/blog_Save/Blog_SaveServlet"
							style="margin-bottom: 0px;">
							<input type="submit" value="刪除"> <input type="hidden"
								name="blogSaveno" value="${blog_SaveVO.blogSaveno}"> <input
								type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%-- <%@ include file="page2.file" %> --%>

		<!-- ====================================
———	BLOG GRID LEFT SIDEBAR
===================================== -->
		<section class="py-8 py-md-10">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-lg-9 order-md-1">
						<div class="row">

							<jsp:useBean id="blogSvc" scope="page"
								class="com.blog.model.BlogService" />
							<jsp:useBean id="memberSvc" scope="page"
								class="com.member.model.MemberService" />
							<jsp:useBean id="blogmesSvc" scope="page"
								class="com.blog_mes.model.Blog_MesService" />
							<c:forEach var="blogSaveVO" items="${userSaveList}">
								<div class="col-md-6 col-lg-4">
									<div class="card">
										<div class="position-relative">
											<c:choose>
												<c:when
													test='${blogSvc.getOneBlog(blogSaveVO.blogno).getBlogClass() eq "健身影片"}'>
													<video class="card-img-top" width="240" height="180"
														controls alt="Card image">
														<source
															src="<%=request.getContextPath()%>/blog/ShowVideo?blogno=${blogSaveVO.blogno}"
															type="video/mp4">
													</video>
												</c:when>
												<c:otherwise>
													<a href="blog-single.html"> <img class="card-img-top"
														width="240" height="180"
														src="<%=request.getContextPath()%>/blog/ShowPicture?blogno=${blogSaveVO.blogno}"
														alt="Card image">
													</a>
												</c:otherwise>
											</c:choose>
											<div class="card-img-overlay p-0">
												<span class="badge badge-rounded badge-warning m-4"><fmt:formatDate
														value="${blogSvc.getOneBlog(blogSaveVO.blogno).getPostDate()}"
														pattern="MMM" /><br> <fmt:formatDate
														value="${blogSvc.getOneBlog(blogSaveVO.blogno).getPostDate()}"
														pattern="d日" /> </span>

											</div>
										</div>

										<div
											class="card-body border-top-5 px-3 rounded-bottom border-warning">
											<h3 class="card-title text-capitalize">
												<a class="text-warning d-block text-truncate"
													href="<%=request.getContextPath()%>/blog/BlogServlet?action=getOne_For_Display&blogno=${blogSvc.getOneBlog(blogSaveVO.blogno).getBlogno()}">${blogSvc.getOneBlog(blogSaveVO.blogno).getTitle()}</a>
											</h3>
											<ul
												class="list-unstyled d-flex flex-md-column  flex-xl-row mb-1">

												<li class="mr-2"><a class="text-muted"
													href="blog-single.html"> <i class="fa fa-user mr-2"
														aria-hidden="true"></i>${memberSvc.getOneMember(blogSvc.getOneBlog(blogSaveVO.blogno).getMemberId()).getName()}
												</a></li>


												<li class="mr-2"><a class="text-muted"
													href="blog-single.html"> <i
														class="fa fa-comments-o mr-2" aria-hidden="true"></i>${blogmesSvc.getOneBlognoMes(blogSvc.getOneBlog(blogSaveVO.blogno).getBlogno()).size() }
														Comments
												</a></li>
											</ul>
											<p class="mb-2">${blogSvc.getOneBlog(blogSaveVO.blogno).getText()}</p>
											<%-- 											<p class="mb-2">${blogVO.text}</p> --%>
											<a class="btn btn-link text-hover-warning pl-0"
												href="blog-single.html"> <i
												class="fa fa-angle-double-right mr-1" aria-hidden="true"></i>
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
		<div class="color-bar">
			<div class="container-fluid">
				<div class="row">
					<div class="col color-bar bg-warning"></div>
					<div class="col color-bar bg-danger"></div>
					<div class="col color-bar bg-success"></div>
					<div class="col color-bar bg-info"></div>
					<div class="col color-bar bg-purple"></div>
					<div class="col color-bar bg-pink"></div>
					<div class="col color-bar bg-warning d-none d-md-block"></div>
					<div class="col color-bar bg-danger d-none d-md-block"></div>
					<div class="col color-bar bg-success d-none d-md-block"></div>
					<div class="col color-bar bg-info d-none d-md-block"></div>
					<div class="col color-bar bg-purple d-none d-md-block"></div>
					<div class="col color-bar bg-pink d-none d-md-block"></div>
				</div>
			</div>
		</div>

		<div class="pt-8 pb-7  bg-repeat"
			style="background-image: url(assets/img/background/footer-bg-img-1.png);">
			<div class="container">
				<div class="row">
					<div class="col-sm-6 col-lg-3 col-xs-12">
						<a class="mb-6 d-block" href="index.html"> <img
							class="img-fluid d-inline-block w-50 lazyestload"
							data-src="assets/img/logo-footer.png"
							src="assets/img/logo-footer.png">
						</a>
						<p class="mb-6">Excepteur sint occaecat cupidatat non
							proident, sunt in culpa officia.Lorem ipsum dolor sit amet.</p>
						<p class="mb-6">consectetur adipisicing elit, sed do eiusmod
							tempor incididunt ut labore et dolore magna aliqua.</p>
					</div>

					<div class="col-sm-6 col-lg-3 col-xs-12">
						<h4 class="section-title-sm font-weight-bold text-white mb-6">Useful
							Links</h4>
						<ul class="list-unstyled">
							<li class="mb-4"><a href="index.html"> <i
									class="fa fa-angle-double-right mr-2" aria-hidden="true"></i>Kidz
									School
							</a></li>
							<li class="mb-4"><a href="about-us.html"> <i
									class="fa fa-angle-double-right mr-2" aria-hidden="true"></i>About
									Kidz School
							</a></li>
							<li class="mb-4"><a href="index-v2.html"> <i
									class="fa fa-angle-double-right mr-2" aria-hidden="true"></i>Kidz
									Store
							</a></li>
							<li class="mb-4"><a href="index-v3.html"> <i
									class="fa fa-angle-double-right mr-2" aria-hidden="true"></i>Kidz
									Daycare
							</a></li>
							<li class="mb-3"><a href="photo-gallery.html"> <i
									class="fa fa-angle-double-right mr-2" aria-hidden="true"></i>
									Photo Gallery
							</a></li>
						</ul>
					</div>

					<div class="col-sm-6 col-lg-3 col-xs-12">
						<h4 class="section-title-sm font-weight-bold text-white mb-6">Recent
							Post</h4>
						<ul class="list-unstyled list-item-border-bottom">
							<li class="mb-4 pb-4">
								<div class="media">
									<a class="mr-2" href="blog-single-left-sidebar.html"> <img
										class="rounded-lg w-100 border-warning border-2 d-block"
										data-src="assets/img/blog/blog-sm-img5.jpg"
										src="assets/img/blog/blog-sm-img5.jpg" alt="blog-sm-img5.jpg">
									</a>
									<div class="media-body">
										<h5 class="line-hight-16 mb-1">
											<a class="font-base font-size-14"
												href="blog-single-left-sidebar.html">A Clean Website
												Gives More Experience To The Visitors</a>
										</h5>
										<time class="text-white">July 7 - 2018</time>
									</div>
								</div>
							</li>

							<li class="mb-4 pb-4">
								<div class="media">
									<a class="mr-2" href="blog-single-left-sidebar.html"> <img
										class="rounded-lg w-100 border-success border-2 d-block"
										data-src="assets/img/blog/blog-sm-img-12.jpg"
										src="assets/img/blog/blog-sm-img-12.jpg"
										alt="blog-sm-img-12.jpg">
									</a>
									<div class="media-body">
										<h5 class="line-hight-16 mb-1">
											<a class="font-base font-size-14"
												href="blog-single-left-sidebar.html">Duis aute irure
												dolor in reprehenderit in voluptate.</a>
										</h5>
										<time class="text-white">Jun 7 - 2018</time>
									</div>
								</div>
							</li>

							<li class="mb-4 pb-4">
								<div class="media">
									<a class="mr-2" href="blog-single-left-sidebar.html"> <img
										class="rounded-lg w-100 border-info border-2 d-block"
										data-src="assets/img/blog/blog-sm-img7.jpg"
										src="assets/img/blog/blog-sm-img7.jpg" alt="blog-sm-img7.jpg">
									</a>
									<div class="media-body">
										<h5 class="line-hight-16 mb-1">
											<a class="font-base font-size-14"
												href="blog-single-left-sidebar.html">Duis aute irure
												dolor in reprehenderit in voluptate.</a>
										</h5>
										<time class="text-white">Jun 7 - 2018</time>
									</div>
								</div>
							</li>
						</ul>
					</div>

					<div class="col-sm-6 col-lg-3 col-xs-12">
						<h4 class="section-title-sm font-weight-bold text-white mb-6">Mailing
							List</h4>
						<p class="mb-4">Sign up for our mailing list to get latest
							updates and offers.</p>
						<form class="mb-4" action="">
							<div class="input-group shadow-light rounded-sm input-addon">
								<input type="text" class="form-control py-4"
									placeholder="Email address" aria-describedby="basic-addon21">
								<div class="input-group-append ">
									<div class="input-group-text bg-danger">
										<i class="fa fa-check text-white" aria-hidden="true"></i>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- Copy Right -->
		<div class="copyright">
			<div class="container">
				<div class="row py-4 align-items-center">
					<div class="col-sm-7 col-xs-12 order-1 order-md-0">
						<p class="copyright-text">
							© 2018 Copyright Kidz School Bootstrap Template by <a
								href="http://www.iamabdus.com/" target="_blank">Abdus.</a>
						</p>
					</div>

					<div class="col-sm-5 col-xs-12">
						<ul
							class="list-inline d-flex align-items-center justify-content-md-end justify-content-center mb-md-0">
							<li class="mr-3"><a
								class="icon-rounded-circle-small bg-warning"
								href="javascript:void(0)"> <i
									class="fa fa-facebook text-white" aria-hidden="true"></i>
							</a></li>
							<li class="mr-3"><a
								class="icon-rounded-circle-small bg-success"
								href="javascript:void(0)"> <i
									class="fa fa-twitter text-white" aria-hidden="true"></i>
							</a></li>
							<li class="mr-3"><a
								class="icon-rounded-circle-small bg-danger"
								href="javascript:void(0)"> <i
									class="fa fa-google-plus text-white" aria-hidden="true"></i>
							</a></li>
							<li class="mr-3"><a
								class="icon-rounded-circle-small bg-info"
								href="javascript:void(0)"> <i
									class="fa fa-pinterest-p text-white" aria-hidden="true"></i>
							</a></li>
							<li class=""><a class="icon-rounded-circle-small bg-purple"
								href="javascript:void(0)"> <i class="fa fa-vimeo text-white"
									aria-hidden="true"></i>
							</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</footer>

	<!-- Modal Login -->
	<div class="modal fade" id="modal-login" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="bg-warning rounded-top p-2">
					<h3 class="text-white font-weight-bold mb-0 ml-2">Login</h3>
				</div>

				<div class="border rounded-bottom-md border-top-0">
					<div class="p-3">
						<form action="#" method="POST" role="form">
							<div class="form-group form-group-icon">
								<input type="text" class="form-control border"
									placeholder="User name" required="">
							</div>

							<div class="form-group form-group-icon">
								<input type="password" class="form-control border"
									placeholder="Password" required="">
							</div>

							<div class="form-group">
								<button type="submit"
									class="btn btn-danger text-uppercase w-100">Log In</button>
							</div>

							<div class="form-group text-center text-secondary mb-0">
								<a class="text-danger" href="#">Forgot password?</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal Create Account -->
	<div class="modal fade" id="modal-createAccount" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm rounded" role="document">
			<div class="modal-content">
				<div class="bg-warning rounded-top p-2">
					<h3 class="text-white font-weight-bold mb-0 ml-2">Create An
						Account</h3>
				</div>

				<div class="border rounded-bottom-md border-top-0">
					<div class="p-3">
						<form action="#" method="POST" role="form">
							<div class="form-group form-group-icon">
								<input type="text" class="form-control border"
									placeholder="Name" required="">
							</div>

							<div class="form-group form-group-icon">
								<input type="text" class="form-control border"
									placeholder="User name" required="">
							</div>

							<div class="form-group form-group-icon">
								<input type="text" class="form-control border"
									placeholder="Phone" required="">
							</div>

							<div class="form-group form-group-icon">
								<input type="password" class="form-control border"
									placeholder="Password" required="">
							</div>

							<div class="form-group form-group-icon">
								<input type="password" class="form-control border"
									placeholder="Re-Password" required="">
							</div>

							<div class="form-group">
								<button type="submit"
									class="btn btn-danger text-uppercase w-100">Register</button>
							</div>

							<div class="form-group text-center text-secondary mb-0">
								<p class="mb-0">
									Allready have an account? <a class="text-danger" href="#">Log
										in</a>
								</p>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal Enroll -->
	<div class="modal fade" id="modal-enrolAccount" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm rounded" role="document">
			<div class="modal-content">
				<div class="bg-pink rounded-top p-2">
					<h3 class="text-white font-weight-bold mb-0 ml-2">Create An
						Account</h3>
				</div>

				<div class="border rounded-bottom-md border-top-0">
					<div class="p-3">
						<form action="#" method="POST" role="form">
							<div class="form-group form-group-icon">
								<input type="text" class="form-control border"
									placeholder="Name" required="">
							</div>

							<div class="form-group form-group-icon">
								<input type="text" class="form-control border"
									placeholder="User name" required="">
							</div>

							<div class="form-group form-group-icon">
								<input type="text" class="form-control border"
									placeholder="Phone" required="">
							</div>

							<div class="form-group form-group-icon">
								<input type="password" class="form-control border"
									placeholder="Password" required="">
							</div>

							<div class="form-group form-group-icon">
								<input type="password" class="form-control border"
									placeholder="Re-Password" required="">
							</div>

							<div class="form-group">
								<button type="submit"
									class="btn btn-pink text-uppercase text-white w-100">Register</button>
							</div>

							<div class="form-group text-center text-secondary mb-0">
								<p class="mb-0">
									Allready have an account? <a class="text-pink" href="#">Log
										in</a>
								</p>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal Products -->
	<div class="modal fade" id="modal-products" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header border-0">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-6 col-xs-12">
							<img class="img-fluid d-block mx-auto"
								src="assets/img/products/products-preview01.jpg"
								alt="preview01.jpg">
						</div>
						<div class="col-sm-6 col-xs-12">
							<div class="product-single">
								<h1>Barbie Racing Car</h1>

								<span class="pricing font-size-55">$50 <del>$60</del></span>

								<p class="mb-7">Lorem ipsum dolor sit amet, consectetur
									adipisicing elit, sed do eiusmod tempor incididunt ut labore et
									dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
									exercitation ullamco laboris nisi.</p>

								<div class="add-cart mb-0">
									<div class="count-input">
										<input class="quantity btn-primary" type="text" value="1">
										<a class="incr-btn incr-up" data-action="decrease" href="#"><i
											class="fa fa-caret-up" aria-hidden="true"></i></a> <a
											class="incr-btn incr-down" data-action="increase" href="#"><i
											class="fa fa-caret-down" aria-hidden="true"></i></a>
									</div>
									<button type="button" class="btn btn-danger text-uppercase"
										onclick="location.href='product-cart.html';">Add to
										cart</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--scrolling-->
	<div class="scrolling">
		<a href="#pageTop" class="back-to-top" id="back-to-top"
			style="opacity: 1;"> <i class="fa fa-arrow-up" aria-hidden="true"></i>
		</a>
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
</body>

</html>

