<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	BlogService blogSvc = new BlogService();
	List<BlogVO> list = blogSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有文章資料 - listAllBlog.jsp</title>


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

</head>
<body>
	<header class="header main-wrapper" id="pageTop">
		<div class=" bg-stone  top-bar">
			<div class="container">
				<div class="row">
					<div class="col-lg-7 d-none d-lg-block">
						<ul
							class="list-inline d-flex justify-content-xl-start align-items-center h-100 mb-0">
							<li><span class="bg-warning icon-header mr-xl-2"> <i
									class="fa fa-envelope" aria-hidden="true"></i>
							</span> <a href="mailto:info@yourdomain.com"
								class="mr-lg-4 mr-xl-6 text-white opacity-80">info@yourdomain.com</a>
							</li>
							<li><span class="bg-success icon-header mr-xl-2"> <i
									class="fa fa-phone" aria-hidden="true"></i>
							</span> <a href="tel:+1 234 567 8900"
								class="mr-lg-4 mr-xl-6 text-white opacity-80"> +1 234 567
									8900 </a></li>
							<li class="text-white"><span class="bg-pink icon-header">
									<i class="fa fa-clock-o" aria-hidden="true"></i>
							</span> <span class="opacity-80">Open: 9am - 6pm</span></li>
						</ul>
					</div>

					<div class="col-lg-5">
						<ul
							class="list-inline d-flex mb-0 justify-content-xl-end justify-content-center align-items-center mr-xl-2">
							<li><span class="bg-info icon-header mr-lg-0 mr-xl-1">
									<i class="fa fa-globe" aria-hidden="true"></i>
							</span></li>
							<li class="mr-3 mr-md-4 mr-lg-3 mr-xl-5 dropdown dropdown-sm">
								<button class="btn btn-link dropdown-toggle" type="button"
									id="dropdownMenuButton" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">Language</button>
								<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
									<a class="dropdown-item" href="#">English</a> <a
										class="dropdown-item" href="#">Spanish</a> <a
										class="dropdown-item" href="#">Hindi</a>
								</div>
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
															src="<%=request.getContextPath()%>/assets/img/products/product-sm.png"
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
															src="<%=request.getContextPath()%>/assets/img/products/product-sm.png"
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
															src="<%=request.getContextPath()%>/assets/img/products/product-sm.png"
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
					src="<%=request.getContextPath()%>/assets/img/Gympayz_logo.jpg"
					alt="Kidz School">
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
												src="<%=request.getContextPath()%>/assets/img/products/product-sm.png"
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
												src="<%=request.getContextPath()%>/assets/img/products/product-sm.png"
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
												src="<%=request.getContextPath()%>/assets/img/products/product-sm.png"
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
						</a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown1">
								<li><a class="dropdown-item " href="index.html">Kidz
										School</a></li>
								<li><a class="dropdown-item " href="index-v2.html">Kidz
										Store</a></li>
								<li><a class="dropdown-item " href="index-v3.html">Kidz
										Daycare</a></li>
								<li><a class="dropdown-item " href="index-v4.html">Kidz
										Fashion</a></li>
							</ul></li>

						<li class="nav-item dropdown bg-pink"><a
							class="nav-link dropdown-toggle " href="component-default.html">
								<i class="fa fa-home nav-icon" aria-hidden="true"></i> <span>News</span>
						</a></li>

						<li class="nav-item dropdown bg-danger"><a
							class="nav-link dropdown-toggle  active "
							href="javascript:void(0)" role="button" data-toggle="dropdown">
								<i class="fa fa-list-ul nav-icon" aria-hidden="true"></i> <span>Schedule</span>
						</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item " href="about-us.html">About
										Us</a></li>
								<li><a class="dropdown-item " href="javascript:void(0)">
										Team <i class="fa fa-chevron-right" aria-hidden="true"></i>
								</a>
									<ul class="sub-menu">
										<li><a class="" href="teachers.html">Teachers</a></li>
										<li><a class="" href="teachers-details.html">Teachers
												Details</a></li>
									</ul></li>
								<li><a class="dropdown-item " href="testimonial.html">Testimonial</a>
								</li>
								<li><a
									class="dropdown-item
                        ||
                        ||

                        "
									href="javascript:void(0)">Events<i
										class="fa fa-chevron-right" aria-hidden="true"></i></a>
									<ul class="sub-menu">
										<li><a class=" " href="events.html">All Events</a></li>
										<li><a class="" href="event-single-left-sidebar.html">Events
												Left Sidebar</a></li>
										<li><a class="" href="event-single-right-sidebar.html">Events
												Right Sidebar</a></li>
									</ul></li>
								<li><a class="dropdown-item " href="photo-gallery.html">Photo
										Gallery</a></li>
								<li><a class="dropdown-item " href="pricing-table.html">Pricing
										Table</a></li>
								<li><a class="dropdown-item " href="services.html">Services</a>
								</li>
								<li><a class="dropdown-item " href="services-details.html">Services
										Details</a></li>
								<li><a class="dropdown-item " href="search-result.html">Search
										Result</a></li>
								<li><a class="dropdown-item  active "
									href="contact-us.html">Contact Us</a></li>
								<li><a class="dropdown-item " href="faq.html">FAQ</a></li>
								<li><a class="dropdown-item " href="sign-up-or-login.html">Sign
										Up / Login</a></li>
								<li><a class="dropdown-item " href="404.html">Error 404</a>
								</li>
								<li><a class="dropdown-item" href="coming-soon.html">Coming
										Soon</a></li>
							</ul></li>

						<li class="nav-item dropdown mega-dropdown bg-success"><a
							class="nav-link dropdown-toggle " href="#" role="button"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<i class="fa fa-file-text-o nav-icon" aria-hidden="true"></i> <span>Courses</span>
						</a>
							<div class="dropdown-menu row">
								<div class="col-12 col-md-3">
									<ul class="list-unstyled">
										<li>Courses Grid</li>
										<li><a class="" href="courses-grid-full.html">Courses
												Grid Fullwidth</a></li>
										<li><a class="" href="courses-grid-left-sidebar.html">Courses
												Grid Left Sidebar</a></li>
										<li><a class="" href="courses-grid-right-sidebar.html">Courses
												Grid Right Sidebar</a></li>
									</ul>
								</div>

								<div class="col-12 col-md-3">
									<ul class="list-unstyled">
										<li>Courses List</li>
										<li><a class="" href="course-list-fullwidth.html">Courses
												List Fullwidth</a></li>
										<li><a class="" href="course-list-left-sidebar.html">Courses
												List left Sidebar</a></li>
										<li><a class="" href="course-list-right-sidebar.html">Courses
												List Right Sidebar</a></li>
									</ul>
								</div>

								<div class="col-12 col-md-3">
									<ul class="list-unstyled">
										<li>Single Course</li>
										<li><a class="" href="course-single.html">Single
												Course Fullwidth</a></li>
										<li><a class="" href="course-single-left-sidebar.html">Single
												Course left Sidebar</a></li>
										<li><a class="" href="course-single-right-sidebar.html">Single
												Course Right Sidebar</a></li>
									</ul>
								</div>

								<div class="col-12 col-md-3">
									<ul class="list-unstyled">
										<li>Checkout</li>
										<li><a class="" href="product-checkout-step-1.html">Personal
												Info</a></li>
										<li><a class="" href="product-checkout-step-2.html">Payment
												Info</a></li>
										<li><a class="" href="product-checkout-step-3.html">Confirmation</a></li>
									</ul>
								</div>
							</div></li>

						<li class="nav-item dropdown bg-info"><a
							class="nav-link dropdown-toggle " href="javascript:void(0)"
							id="stores" role="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <i
								class="fa fa-pencil-square-o nav-icon" aria-hidden="true"></i> <span>Store</span>
						</a>
							<ul class="dropdown-menu dropdown-menu-right"
								aria-labelledby="stores">
								<li><a
									class="dropdown-item
                        ||
                        ||


                        "
									href="javascript:void(0)">商品<i class="fa fa-chevron-right"
										aria-hidden="true"></i></a>
									<ul class="sub-menu">
										<li><a class="" href="products.html">健身食品</a></li>
										<li><a class="" href="product-left-sidebar.html">健身用品</a></li>
										<!-- <li><a class="" href="product-right-sidebar.html">Products Right Sidebar</a></li> -->
									</ul></li>
								<li><a class="dropdown-item " href="product-single.html">購物清單</a>
								</li>
								<li><a class="dropdown-item " href="product-category.html">購物車</a>
								</li>
								<li><a class="dropdown-item " href="product-cart.html">收藏商品</a>
								</li>
								<!-- <li>
                        <a class="dropdown-item
                        ||
                        ||

                        " href="javascript:void(0)">Checkout<i class="fa fa-chevron-right" aria-hidden="true"></i></a>
                        <ul class="sub-menu">
                          <li>
                            <a class="" href="product-checkout-step-1.html">Personal Info</a>
                          </li>
                          <li>
                            <a class="" href="product-checkout-step-2.html">Payment Info</a>
                          </li>
                          <li>
                            <a class="" href="product-checkout-step-3.html">Confirmation</a>
                          </li>
                        </ul>
                      </li> -->
							</ul></li>

						<li class="nav-item dropdown bg-purple"><a
							class="nav-link dropdown-toggle " href="javascript:void(0)"
							id="stores" role="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <i
								class="fa fa-calendar nav-icon" aria-hidden="true"></i> <span>Blog</span>
						</a>
							<ul class="dropdown-menu dropdown-menu-right"
								aria-labelledby="stores">
								<li><a
									class="dropdown-item
                        ||
                        ||

                        "
									href="javascript:void(0)">心得交流<i
										class="fa fa-chevron-right" aria-hidden="true"></i></a>
									<ul class="sub-menu">
										<li><a class="" href="blog-grid.html">心得交流</a></li>
										<li><a class="" href="blog-grid-left-sidebar.html">健康食譜</a></li>
										<li><a class="" href="blog-grid-right-sidebar.html">健身影片</a></li>
										<li><a class="" href="blog-grid-right-sidebar.html">收藏文章</a></li>
									</ul></li>
								<li><a
									class="dropdown-item
                        ||
                        ||
                        "
									href="javascript:void(0)">健康食譜<i
										class="fa fa-chevron-right" aria-hidden="true"></i></a>
									<ul class="sub-menu">
										<li><a class="" href="blog-list.html">Blog List
												Fullwidth</a></li>
										<li><a class="" href="blog-list-left-sidebar.html">Blog
												List Left Sidebar</a></li>
										<li><a class="" href="blog-list-right-sidebar.html">Blog
												List Right Sidebar</a></li>
									</ul></li>
								<li><a
									class="dropdown-item
                        ||
                        ||

                        "
									href="javascript:void(0)">健身影片<i
										class="fa fa-chevron-right" aria-hidden="true"></i></a>
									<ul class="sub-menu">
										<li><a class="" href="blog-single.html">Blog Single
												Fullwidth</a></li>
										<li><a class="" href="blog-single-left-sidebar.html">Blog
												Single Left Sidebar</a></li>
										<li><a class="" href="blog-single-right-sidebar.html">Blog
												Single Right Sidebar</a></li>
									</ul></li>
								<li><a
									class="dropdown-item
                        ||
                        ||

                        "
									href="javascript:void(0)">收藏文章<i
										class="fa fa-chevron-right" aria-hidden="true"></i></a>
									<ul class="sub-menu">
										<li><a class="" href="blog-single.html">Blog Single
												Fullwidth</a></li>
										<li><a class="" href="blog-single-left-sidebar.html">Blog
												Single Left Sidebar</a></li>
										<li><a class="" href="blog-single-right-sidebar.html">Blog
												Single Right Sidebar</a></li>
									</ul></li>
							</ul></li>

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
	<div class="main-wrapper contact-us">

		<!-- ====================================
          ———	BREADCRUMB
          ===================================== -->
		<section class="breadcrumb-bg"
			style="background-image: url(<%=request.getContextPath()%>/assets/img/background/headerpic2.png);">
			<div class="container">
				<div class="breadcrumb-holder">
					<div>
						<h1 class="breadcrumb-title">Blog Grid</h1>
						<ul class="breadcrumb breadcrumb-transparent">
							<li class="breadcrumb-item"><a class="text-white"
								href="index.html">Home</a></li>
							<li class="breadcrumb-item text-white active" aria-current="page">
								Blog Grid</li>
						</ul>
					</div>
				</div>
			</div>
		</section>

		
		<table id="table-1">
			<tr>
				<td>
					<h3>所有文章資料 - listAllBlog2.jsp</h3>
					<h4>
						<a href="select_page.jsp">回首頁</a>
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
				<th>文章編號</th>
				<th>一般會員編號</th>
				<th>文章分類</th>
				<th>發文日期</th>
				<th>標題</th>
				<th>內文</th>
				<th>文章狀態</th>
				<th>更新日期</th>
				<th>照片</th>
				<th>影片</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>
			<%@ include file="page1.file"%>
			<%-- 	<c:forEach var="blogVO" items="${list}"> --%>
			<c:forEach var="blogVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<td>${blogVO.blogno}</td>
					<td>${blogVO.memberId}</td>
					<td>${blogVO.blogClass}</td>
					<td>${blogVO.postDate}</td>
					<td>${blogVO.title}</td>
					<td>${blogVO.text}</td>
					<td>${blogVO.status}</td>
					<td>${blogVO.updateTime}</td>
					<td><img class="blog" width="400"
						src="<%=request.getContextPath()%>/blog/ShowPicture?blogno=${blogVO.blogno}"></td>
					<td><video width="400" height="240" controls class="blog">
							<source
								src="<%=request.getContextPath()%>/blog/ShowVideo?blogno=${blogVO.blogno}"
								type="video/mp4">
						</video></td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/blog/BlogServlet"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改"> <input type="hidden"
								name="blogno" value="${blogVO.blogno}"> <input
								type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/blog/BlogServlet"
							style="margin-bottom: 0px;">
							<input type="submit" value="刪除"> <input type="hidden"
								name="blogno" value="${blogVO.blogno}"> <input
								type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>

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
			src="<%=request.getContextPath()%>//assets/plugins/isotope/isotope.min.js"></script>
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
          <script src="assets/plugins/revolution/js/extensions/revolution.extension.slideanims.min.js"></script>
          <script src="assets/plugins/revolution/js/extensions/revolution.extension.layeranimation.min.js"></script>
          <script src="assets/plugins/revolution/js/extensions/revolution.extension.navigation.min.js"></script>
        -->

		<script
			src="<%=request.getContextPath()%>/assets/plugins/wow/wow.min.js"></script>
		<script
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDU79W1lu5f6PIiuMqNfT1C6M0e_lq1ECY"></script>

		<script src="<%=request.getContextPath()%>/assets/js/kidz.js"></script>
</body>
</html>