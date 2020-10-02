<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.blog.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  BlogVO blogVO = (BlogVO) request.getAttribute("blogVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>�峹��� - listOneBlog.jsp</title>


<link href="<%=request.getContextPath()%>/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/assets/plugins/no-ui-slider/nouislider.min.css" rel="stylesheet"/>
  <link href="<%=request.getContextPath()%>/assets/plugins/owl-carousel/owl.carousel.min.css" rel="stylesheet" media="screen">
  <link href="<%=request.getContextPath()%>/assets/plugins/owl-carousel/owl.theme.default.min.css" rel="stylesheet" media="screen">
  <link href="<%=request.getContextPath()%>/assets/plugins/fancybox/jquery.fancybox.min.css" rel="stylesheet"/>
  <link href="<%=request.getContextPath()%>/assets/plugins/isotope/isotope.min.css" rel="stylesheet"/>
  <link href="<%=request.getContextPath()%>/assets/plugins/animate/animate.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/assets/plugins/select2/css/select2.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/assets/plugins/revolution/css/settings.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/assets/plugins/revolution/css/layers.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/assets/plugins/revolution/css/navigation.css" rel="stylesheet">

  <!-- Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Dosis:300,400,600,700|Open+Sans:300,400,600,700" rel="stylesheet">

  <!-- Custom css -->
  <link href="<%=request.getContextPath()%>/assets/css/kidz.css" id="option_style" rel="stylesheet">

  <!-- Favicon -->
  <link href="<%=request.getContextPath()%>/<%=request.getContextPath()%>/assets/img/favicon.png" rel="shortcut icon">


</head>
<body bgcolor='white'>
  <header class="header main-wrapper" id="pageTop">
   
      <!-- Top Bar-->
      <!-- d-none d-md-block -->
      <div class=" bg-stone  top-bar">
        <div class="container">
          <div class="row">
            <div class="col-lg-7 d-none d-lg-block">
              <ul class="list-inline d-flex justify-content-xl-start align-items-center h-100 mb-0">
              </ul>
            </div>

            <div class="col-lg-5">
              <ul class="list-inline d-flex mb-0 justify-content-xl-end justify-content-center align-items-center mr-xl-2">
                <li>
                  <!-- <span class="bg-info icon-header mr-lg-0 mr-xl-1">
                    <i class="fa fa-globe" aria-hidden="true"></i>
                  </span> -->
                </li>
                <li class="mr-3 mr-md-4 mr-lg-3 mr-xl-5 dropdown dropdown-sm">
                  <!-- <button class="btn btn-link dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
                  aria-haspopup="true" aria-expanded="false"> Language </button> -->
                  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="#">English</a>
                    <a class="dropdown-item" href="#">Spanish</a>
                    <a class="dropdown-item" href="#">Hindi</a>
                  </div>
                </li>

                <li class="text-white mr-md-3 mr-lg-2 mr-xl-5">
                  <span class="bg-purple icon-header mr-1 mr-md-2 mr-lg-1 mr-xl-2">
                    <i class="fa fa-unlock-alt text-white font-size-13" aria-hidden="true"></i>
                  </span>
                  <a class="text-white font-weight-medium opacity-80" href="javascript:void(0)" data-toggle="modal"
                  data-target="#modal-login">
                  Login
                </a>
                <span class="text-white opacity-80">or</span>
                <a class="text-white font-weight-medium opacity-80" href="javascript:void(0)" data-toggle="modal" data-target="#modal-createAccount">Create
                  an account</a>
                </li>

                <li class="cart-dropdown d-none d-md-block">
                  <div data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" data-display="static">
                    <a href="javascript:void(0)">
                      <span class="rounded-sm bg-pink icon-small icon-badge d-none close-icon">
                        <i class="fa fa-close text-white" aria-hidden="true"></i>
                      </span>
                      <span class="rounded-sm bg-pink icon-small icon-badge shopping-icon">
                        <i class="fa fa-shopping-basket text-white" aria-hidden="true"></i>
                        <span class="badge bg-warning">3</span>
                      </span>
                    </a>
                  </div>
                  <div class="dropdown-menu dropdown-menu-right">
                    <ul class="bg-white list-unstyled">
                      <li class="d-flex align-items-center">
                        <i class="fa fa-shopping-basket font-size-20 mr-3" aria-hidden="true"></i>
                        <h3 class="text-capitalize font-weight-bold mb-0">3 items in your cart</h3>
                      </li>
                      <hr>
                      <li>
                        <a href="product-single.html">
                          <div class="media">
                            <div class="image">
                              <img class="bg-light rounded-sm px-5 py-3 mr-4" src="<%=request.getContextPath()%>/<%=request.getContextPath()%>/assets/img/products/product-sm.png" alt="cart-Image">
                            </div>
                            <div class="media-body">
                              <div class="d-flex justify-content-between">
                                <h4 class="text-dark">Barbie Racing Car</h4>
                                <span class="cancel">
                                  <i class="fa fa-close text-muted" aria-hidden="true"></i>
                                </span>
                              </div>
                              <div class="price">
                                <span class="text-warning font-weight-medium">$50</span>
                              </div>
                              <span class="text-muted font-weight-medium text-muted">Qnt: 1</span>
                            </div>
                          </div>
                        </a>
                        <hr>
                      </li>
                      <li>
                        <a href="product-single.html">
                          <div class="media">
                            <div class="image">
                              <img class="bg-light rounded-sm px-5 py-3 mr-4" src="<%=request.getContextPath()%>/assets/img/products/product-sm.png" alt="cart-Image">
                            </div>
                            <div class="media-body">
                              <div class="d-flex justify-content-between">
                                <h4 class="text-dark">Barbie Racing Car</h4>
                                <span class="cancel">
                                  <i class="fa fa-close text-muted" aria-hidden="true"></i>
                                </span>
                              </div>
                              <div class="price">
                                <span class="text-warning font-weight-medium">$50</span>
                              </div>
                              <span class="text-muted font-weight-medium">Qnt: 1</span>
                            </div>
                          </div>
                        </a>
                        <hr>
                      </li>
                      <li>
                        <a href="product-single.html">
                          <div class="media">
                            <div class="image">
                              <img class="bg-light rounded-sm px-5 py-3 mr-4" src="<%=request.getContextPath()%>/assets/img/products/product-sm.png" alt="cart-Image">
                            </div>
                            <div class="media-body">
                              <div class="d-flex justify-content-between">
                                <h4 class="text-dark font-weight-bold">Barbie Racing Car</h4>
                                <span class="cancel">
                                  <i class="fa fa-close text-muted" aria-hidden="true"></i>
                                </span>
                              </div>
                              <div class="price">
                                <span class="text-warning font-weight-medium">$50</span>
                              </div>
                              <span class="text-muted font-weight-medium">Qnt: 1</span>
                            </div>
                          </div>
                        </a>
                        <hr>
                      </li>
                      <li>
                        <div class="d-flex justify-content-between mb-3">
                          <h3 class="cart-total font-weight-bold">Subtotal</h3>
                          <h3 class="cart-price font-weight-bold">$150</h3>
                        </div>
                        <div class="cart-button d-flex justify-content-between">
                          <button type="button" class="btn btn-danger text-uppercase px-4 shadow-sm mr-3" onclick="location.href='product-checkout-step-1.html';">Checkout</button>
                          <button type="button" class="btn btn-danger text-uppercase px-4 shadow-sm" onclick="location.href='product-cart.html';">View
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
        <nav class="navbar navbar-expand-md navbar-scrollUp navbar-sticky navbar-white">
          <div class="container">
            <a class="navbar-brand"  href="index.html">
              <img class="d-inline-block"  src="<%=request.getContextPath()%>/assets/img/gympayz2.png" alt="Kidz School">
            </a>

            <!-- cart-dropdown -->
            <div class="dropdown cart-dropdown ml-auto mr-5 d-md-none">
              <div data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <a href="javascript:void(0)">
                  <span class="rounded-sm bg-pink icon-small icon-badge d-none close-icon">
                    <i class="fa fa-close text-white" aria-hidden="true"></i>
                  </span>
                  <span class="rounded-sm bg-pink icon-small icon-badge shopping-icon">
                    <i class="fa fa-shopping-basket text-white" aria-hidden="true"></i>
                    <span class="badge bg-warning">3</span>
                  </span>
                </a>
              </div>
              <div class="dropdown-menu dropdown-menu-right">
                <ul class="bg-white list-unstyled">
                  <li class="d-flex align-items-center">
                    <i class="fa fa-shopping-basket font-size-20 mr-3" aria-hidden="true"></i>
                    <h3 class="text-capitalize font-weight-bold mb-0">3 items in your cart</h3>
                  </li>
                  <hr>
                  <li>
                    <a href="product-single.html">
                      <div class="media">
                        <div class="image">
                          <img class="mr-4 p-2 bg-light" src="<%=request.getContextPath()%>/assets/img/products/product-sm.png" alt="cart-Image">
                        </div>
                        <div class="media-body">
                          <div class="d-flex justify-content-between">
                            <h4 class="text-dark">Barbie Racing Car</h4>
                            <span class="cancel">
                              <i class="fa fa-close text-muted" aria-hidden="true"></i>
                            </span>
                          </div>
                          <div class="price">
                            <span class="text-warning font-weight-medium">$50</span>
                          </div>
                          <span class="text-muted font-weight-medium text-muted">Qnt: 1</span>
                        </div>
                      </div>
                    </a>
                    <hr>
                  </li>
                  <li>
                    <a href="product-single.html">
                      <div class="media">
                        <div class="image">
                          <img class="mr-4 p-2 bg-light" src="<%=request.getContextPath()%>/assets/img/products/product-sm.png" alt="cart-Image">
                        </div>
                        <div class="media-body">
                          <div class="d-flex justify-content-between">
                            <h4 class="text-dark">Barbie Racing Car</h4>
                            <span class="cancel">
                              <i class="fa fa-close text-muted" aria-hidden="true"></i>
                            </span>
                          </div>
                          <div class="price">
                            <span class="text-warning font-weight-medium">$50</span>
                          </div>
                          <span class="text-muted font-weight-medium">Qnt: 1</span>
                        </div>
                      </div>
                    </a>
                    <hr>
                  </li>
                  <li>
                    <a href="product-single.html">
                      <div class="media">
                        <div class="image">
                          <img class="mr-4 p-2 bg-light" src="<%=request.getContextPath()%>/assets/img/products/product-sm.png" alt="cart-Image">
                        </div>
                        <div class="media-body">
                          <div class="d-flex justify-content-between">
                            <h4 class="text-dark font-weight-bold">Barbie Racing Car</h4>
                            <span class="cancel">
                              <i class="fa fa-close text-muted" aria-hidden="true"></i>
                            </span>
                          </div>
                          <div class="price">
                            <span class="text-warning font-weight-medium">$50</span>
                          </div>
                          <span class="text-muted font-weight-medium">Qnt: 1</span>
                        </div>
                      </div>
                    </a>
                    <hr>
                  </li>
                  <li>
                    <div class="d-flex justify-content-between mb-3">
                      <h3 class="cart-total font-weight-bold">Subtotal</h3>
                      <h3 class="cart-price font-weight-bold">$150</h3>
                    </div>
                    <div class="cart-button d-flex justify-content-between">
                      <button type="button" class="btn btn-danger text-uppercase px-4 shadow-sm mr-3" onclick="location.href='product-checkout-step-1.html';">Checkout</button>
                      <button type="button" class="btn btn-danger text-uppercase px-4 shadow-sm" onclick="location.href='product-cart.html';">View
                        Cart</button>
                      </div>
                    </li>
                  </ul>
                </div>
              </div>

              <button class="navbar-toggler py-2" type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fa fa-bars"></i>
              </button>

              <div class="collapse navbar-collapse" id="navbarContent">
                <ul class="navbar-nav ml-lg-auto">
                  <li class="nav-item dropdown bg-warning">
                    <a class="nav-link dropdown-toggle " href="javascript:void(0)" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fa fa-home nav-icon" aria-hidden="true"></i>
                      <span>Home</span>
                    </a>
   
                  </li>

                  <li class="nav-item dropdown bg-pink">
                    <a class="nav-link dropdown-toggle " href="component-default.html">
                      <i class="fa fa-pencil-square-o nav-icon" aria-hidden="true"></i>
                      <span>News</span>
                    </a>
                  </li>

                  <li class="nav-item dropdown bg-danger">
                    <a class="nav-link dropdown-toggle " href="javascript:void(0)"  role="button" data-toggle="dropdown">
                      <i class="fa fa-list-ul nav-icon" aria-hidden="true"></i>
                      <span>Schedule</span>
                    </a>
                  </li>


                  <li class="nav-item dropdown mega-dropdown bg-success">
                    <a class="nav-link dropdown-toggle " href="#"  role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fa fa-file-text-o nav-icon" aria-hidden="true"></i>
                      <span>Courses</span>
                    </a>
                  </li>

                  <li class="nav-item dropdown bg-info">
                    <a class="nav-link dropdown-toggle " href="javascript:void(0)" id="stores" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fa fa-pencil-square-o nav-icon" aria-hidden="true"></i>
                      <span>Store</span>
                    </a>
                  </li>

                  <li class="nav-item dropdown bg-purple">
                    <a class="nav-link dropdown-toggle " href="javascript:void(0)" id="stores" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fa fa-calendar nav-icon" aria-hidden="true"></i>
                      <span>Blog</span>
                    </a>
                  </li>

                  <li class="nav-item dropdown bg-pink">
                    <a class="nav-link dropdown-toggle " href="component-default.html">
                      <i class="fa fa-home nav-icon" aria-hidden="true"></i>
                      <span>Contact us</span>
                    </a>
                  </li>

                </ul>
              </div>
            </div>
          </nav>
        </header>
        <div class="main-wrapper contact-us">

          <!-- ====================================
          �X�X�X	BREADCRUMB
          ===================================== -->
          <section class="breadcrumb-bg" style="background-image: url(<%=request.getContextPath()%>/assets/img/background/headerpic2.png); ">
            <div class="container">
              <div class="breadcrumb-holder">
                <div>
                  <h1 class="breadcrumb-title">Gympayz</h1>
<!--                   <ul class="breadcrumb breadcrumb-transparent"> -->
<!--                     <li class="breadcrumb-item"> -->
<!--                       <a class="text-white" href="index.html">Home</a> -->
<!--                     </li> -->
<!--                     <li class="breadcrumb-item text-white active" aria-current="page"> -->
<!--                       Blog Grid -->
<!--                     </li> -->
<!--                   </ul> -->
                </div>
              </div>
            </div>
          </section>


<table id="table-1">
	<tr><td>
		 <h3>�峹��� - ListOneBlog.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/blog/select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�峹�s��</th>
		<th>�@��|���s��</th>
		<th>�峹����</th>
		<th>�o����</th>
		<th>���D</th>
		<th>����</th>
		<th>�Ӥ�</th>
		<th>�v��</th>
		<th>�峹���A</th>
		<th>��s���</th>
		
	</tr>
	<tr>
		<td><%=blogVO.getBlogno()%></td>
		<td><%=blogVO.getMemberId()%></td>
		<td><%=blogVO.getBlogClass()%></td>
		<td><%=blogVO.getPostDate()%></td>
		<td><%=blogVO.getTitle()%></td>
		<td><%=blogVO.getText()%></td>
		<td><img class="blog" width="400"  src="<%=request.getContextPath()%>/blog/ShowPicture?blogno=${blogVO.blogno}"></td>
		<td>
			<video width="400" height="240" controls class="blog">
  				<source src="<%=request.getContextPath()%>/blog/ShowVideo?blogno=${blogVO.blogno}" type="video/mp4">
			</video>
		</td>
<%-- 		<td><%=blogVO.getPhoto()%></td> --%>
<%-- 		<td><%=blogVO.getVideo()%></td> --%>
		<td><%=blogVO.getStatus()%></td>
		<td><%=blogVO.getUpdateTime()%></td>
		
	</tr>
</table>


<section class="py-8 py-md-10">
	<div class="container">
		<div class="row">
			<div class="col-md-8 col-lg-9 order-md-1">
				<div class="card">
					<div class="position-relative">
						<img class="card-img-top" src="<%=request.getContextPath()%>/blog/ShowPicture?blogno=${blogVO.blogno}" alt="Card image cap">
						<div class="card-img-overlay">
		          <span class="badge badge-rounded badge-warning"><fmt:formatDate value="<%=blogVO.getPostDate()%>" pattern="MMM"/><br> <fmt:formatDate value="<%=blogVO.getPostDate()%>" pattern="dd"/></span>
<!-- 		          <span class="badge badge-rounded badge-warning"> 10 <br> July</span> -->
		        </div>
					</div>
					<div class="card-body border-top-5 px-3 rounded-bottom border-warning">
						<h3 class="card-title text-warning mb-4"><%=blogVO.getTitle()%></h3>

						<ul class="list-unstyled d-flex mb-6">
							<li class="">
								<a class="text-muted d-inline-block mr-3" href="#user"><i class="fa fa-user mr-2" aria-hidden="true"></i><%=blogVO.getMemberId()%></a>
							</li>
							<li class="mr-3">
								<a class="text-muted d-inline-block" href="#comments"><i class="fa fa-comments-o mr-1" aria-hidden="true"></i>4 Comments</a>
							</li>
						</ul>

						<p class="card-text text-justify mb-6"><%=blogVO.getText()%></p>

<!-- 						<p class="card-text text-justify mb-6">Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.</p> -->

<!-- 						<p class="card-text text-justify mb-5">The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.</p> -->

						<div class="">
							<ul class="list-inline d-flex mb-0">
				        <li class="mr-2">
				          <a class="icon-rounded-circle-small bg-warning" href="javascript:void(0)">
				            <i class="fa fa-facebook text-white" aria-hidden="true"></i>
				          </a>
				        </li>
				        <li class="mr-2">
				          <a class="icon-rounded-circle-small bg-danger" href="javascript:void(0)">
				            <i class="fa fa-twitter text-white" aria-hidden="true"></i>
				          </a>
				        </li>
				        <li class="mr-2">
				          <a class="icon-rounded-circle-small bg-success" href="javascript:void(0)">
				            <i class="fa fa-google-plus text-white" aria-hidden="true"></i>
				          </a>
				        </li>
				        <li class="mr-2">
				          <a class="icon-rounded-circle-small bg-info" href="javascript:void(0)">
				            <i class="fa fa-pinterest-p text-white" aria-hidden="true"></i>
				          </a>
				        </li>
				        <li class="">
				          <a class="icon-rounded-circle-small bg-purple" href="javascript:void(0)">
				            <i class="fa fa-vimeo text-white" aria-hidden="true"></i>
				          </a>
				        </li>
				      </ul>
						</div>
					</div>
				</div>

				<div class="bg-light shadow-sm rounded px-3 pt-3 pb-6 mb-4">
					<h3 class="mb-4 text-danger font-weight-bold">4 Comments</h3>
					<div class="media py-1">
					  <div class="mr-4">
					  	<img class=" rounded-circle" src="<%=request.getContextPath()%>/assets/img/blog/blog-sm-img1.jpg" alt="User Image">
						</div>
					  <div class="media-body">
					    <span class="mb-3 font-weight-medium text-muted d-inline-block">Sarah Smith</span>
							<time class="d-block text-muted font-size-13 mb-3">July 7, 2018</time>
					    <p class="font-size-13 line-hight-21">The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.</p>

							<a href="#reply" class="btn btn-danger text-uppercase">
								<i class="fa fa-reply-all mr-3" aria-hidden="true"></i>Reply
							</a>
					  </div>
					</div>
					<hr>

					<div class="media py-1">
					  <div class="mr-4">
					  	<img class=" rounded-circle" src="<%=request.getContextPath()%>/assets/img/blog/blog-sm-img2.jpg" alt="User Image">
						</div>
					  <div class="media-body">
					    <span class="mb-3 font-weight-medium text-muted d-inline-block">Sarah Smith</span>
							<time class="d-block text-muted font-size-13 mb-3">July 7, 2018</time>
					    <p class="font-size-13 line-hight-21">The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.</p>

							<a href="#reply" class="btn btn-danger text-uppercase">
								<i class="fa fa-reply-all mr-3" aria-hidden="true"></i>Reply
							</a>
					  </div>
					</div>
					<hr>

					<div class="media py-1">
					  <div class="mr-4">
					  	<img class="rounded-circle" src="<%=request.getContextPath()%>/assets/img/blog/blog-sm-img3.jpg" alt="User Image">
					  </div>
					  <div class="media-body">
					    <span class="mb-3 font-weight-medium text-muted d-inline-block">Sarah Smith</span>
							<time class="d-block text-muted font-size-13 mb-3">July 7, 2018</time>
					    <p class="font-size-13 line-hight-21">The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.</p>

							<a href="#reply" class="btn btn-danger text-uppercase">
								<i class="fa fa-reply-all mr-3" aria-hidden="true"></i>Reply
							</a>
					  </div>
					</div>

					<hr>

					<div class="media py-1">
					  <div class="mr-4">
					  	<img class=" rounded-circle" src="<%=request.getContextPath()%>/assets/img/blog/blog-sm-img4.jpg" alt="User Image">
						</div>
					  <div class="media-body">
					    <span class="mb-3 font-weight-medium text-muted d-inline-block">Sarah Smith</span>
							<time class="d-block text-muted font-size-13 mb-3">July 7, 2018</time>
					    <p class="font-size-13 line-hight-21">The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.</p>

							<a href="#reply" class="btn btn-danger text-uppercase">
								<i class="fa fa-reply-all mr-3" aria-hidden="true"></i>Reply
							</a>
					  </div>
					</div>
				</div>

				<div class="bg-light shadow-sm rounded p-3">
					<h3 class="mb-4 text-danger font-weight-bold">Leave A Comment</h3>

					<form>
					  <div class="row">
					    <div class="col-12 col-md-4">
								<div class="form-group form-group-icon">
		            	<i class="fa fa-user"></i>
		            	<input type="text" class="form-control border-warning" placeholder="First name" required="">
		        	  </div>
					    </div>

					    <div class="col-12 col-md-4">
								<div class="form-group form-group-icon">
		            	<i class="fa fa-envelope"></i>
		            	<input type="email" class="form-control border-success" placeholder="Email address" required="">
		          	</div>
					    </div>

					    <div class="col-12 col-md-4">
								<div class="form-group form-group-icon">
		            	<i class="fa fa-chrome"></i>
		            	<input type="text" class="form-control border-purple" placeholder="Website">
		        	  </div>
					    </div>
					  </div>

						<div class="row">
							<div class="col">
								<div class="form-group form-group-icon">
		            	<i class="fa fa-comments "></i>
		            	<textarea class="form-control border-info" placeholder="Write message" rows="6"></textarea>
		        	  </div>
							</div>
						</div>

						<button class="btn btn-danger text-uppercase">submit</button>
					</form>
				</div>
			</div>

			<div class="col-md-4 col-lg-3">
				<form class="" action="search-result.html">
					<div class="card shadow-none bg-transparent">
						<h3 class="card-header bg-warning font-weight-bold rounded-top text-white">Search</h3>
						<div class="card-body border border-top-0 rounded-bottom">
							<div class="input-group border-bottom pb-3 pt-4">
								<input type="text" class="form-control border-0 px-1" placeholder="Enter Your Search" aria-describedby="basic-addon2">
								<span class="input-group-addon" id="basic-addon2">
									<input class="btn btn-sm btn-warning text-uppercase text-white shadow-sm" type="submit" value="Search">
								</span>
							</div>
						</div>
					</div>
				</form>
			
				<div class="card shadow-none bg-transparent">
					<h4 class="card-header font-weight-bold bg-success rounded-top text-white">Categories</h4>
					<div class="card-body border border-top-0 rounded-bottom">
						<ul class="list-unstyled mb-0">
							<li class="mb-2">
								<a class="text-muted font-weight-medium d-block border rounded py-2 pl-3" href="blog-grid-left-sidebar.html">�߱o��y</a>
							</li>
							<li class="my-2">
								<a class="text-muted font-weight-medium d-block border rounded py-2 pl-3" href="blog-grid-left-sidebar.html">���d����</a>
							</li>
							<li class="my-2">
								<a class="text-muted font-weight-medium d-block border rounded py-2 pl-3" href="blog-grid-left-sidebar.html">�����v��</a>
							</li>
							<li class="my-2">
								<a class="text-muted font-weight-medium d-block border rounded py-2 pl-3" href="blog-grid-left-sidebar.html">�峹����</a>
							</li>
							<li class="mt-2">
								<a class="text-muted font-weight-medium d-block border rounded py-2 pl-3" href="blog-grid-left-sidebar.html">�o��</a>
							</li>
						</ul>
					</div>
				</div>

				<div class="mb-4">
<!-- 					<h3 class="bg-purple rounded-top font-weight-bold text-white mb-0 py-2 px-4">Related Course</h3> -->
<!-- 					<div class="border border-top-0 rounded"> -->
<!-- 						<ul class="list-unstyled mb-0"> -->
<!-- 							<li class="border-bottom p-3"> -->
<!-- 								<div class="media"> -->
<!-- 									<a class="mr-2" href="blog-single-left-sidebar.html"> -->
<%-- 										<img class="w-100 rounded" src="<%=request.getContextPath()%>/assets/img/blog/blog-sm-img8.jpg" alt="blog-sm-img5.png"> --%>
<!-- 									</a> -->
<!-- 									<div class="media-body"> -->
<!-- 										<h5 class="mb-1"> -->
<!-- 											<a class="btn-link font-base text-hover-purple" href="blog-single-left-sidebar.html">Mauris semper mass -->
<!-- 												feugiat facilisis.</a> -->
<!-- 										</h5> -->
<!-- 										<time class="text-muted">July 7 - 2018</time> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</li> -->
				
<!-- 							<li class="border-bottom p-3"> -->
<!-- 								<div class="media"> -->
<!-- 									<a class="mr-2" href="blog-single-left-sidebar.html"> -->
<%-- 										<img class="w-100 rounded" src="<%=request.getContextPath()%>/assets/img/blog/blog-sm-img9.jpg" alt="blog-sm-img5.png"> --%>
<!-- 									</a> -->
<!-- 									<div class="media-body"> -->
<!-- 										<h5 class="mb-1"> -->
<!-- 											<a class="btn-link font-base text-hover-purple" href="blog-single-left-sidebar.html">Mauris semper -->
<!-- 												mass feugiat facilisis.</a> -->
<!-- 										</h5> -->
<!-- 										<time class="text-muted">July 7 - 2018</time> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</li> -->
				
<!-- 							<li class="border-bottom p-3"> -->
<!-- 								<div class="media"> -->
<!-- 									<a class="mr-2" href="blog-single-left-sidebar.html"> -->
<%-- 										<img class="w-100 rounded" src="<%=request.getContextPath()%>/assets/img/blog/blog-sm-img10.jpg" alt="blog-sm-img5.png"> --%>
<!-- 									</a> -->
<!-- 									<div class="media-body"> -->
<!-- 										<h5 class="mb-1"> -->
<!-- 											<a class="btn-link font-base text-hover-purple" href="blog-single-left-sidebar.html">Mauris semper mass -->
<!-- 												feugiat facilisis.</a> -->
<!-- 										</h5> -->
<!-- 										<time class="text-muted">July 7 - 2018</time> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</li> -->
				
<!-- 							<li class="p-3"> -->
<!-- 								<div class="media"> -->
<!-- 									<a class="mr-2" href="blog-single-left-sidebar.html"> -->
<%-- 										<img class="w-100 rounded" src="<%=request.getContextPath()%>/assets/img/blog/blog-sm-img11.jpg" alt="blog-sm-img5.png"> --%>
<!-- 									</a> -->
<!-- 									<div class="media-body"> -->
<!-- 										<h5 class="mb-1"> -->
<!-- 											<a class="btn-link font-base text-hover-purple" href="blog-single-left-sidebar.html">Mauris semper mass -->
<!-- 												feugiat facilisis.</a> -->
<!-- 										</h5> -->
<!-- 										<time class="text-muted">Jun 7 - 2018</time> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</li> -->
<!-- 						</ul> -->
<!-- 					</div> -->
				</div>
			</div>
		</div>
	</div>
</section>

</div> <!-- element wrapper ends -->

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
          <script src="<%=request.getContextPath()%>/assets/plugins/revolution/js/jquery.themepunch.revolution.min.js"></script>

          <!-- Load revolution slider only on Local File Systems. The following part can be removed on Server -->
          <!--
          <script src="<%=request.getContextPath()%>/assets/plugins/revolution/js/extensions/revolution.extension.slideanims.min.js"></script>
          <script src="<%=request.getContextPath()%>/assets/plugins/revolution/js/extensions/revolution.extension.layeranimation.min.js"></script>
          <script src="<%=request.getContextPath()%>/assets/plugins/revolution/js/extensions/revolution.extension.navigation.min.js"></script>
        -->

        <script src="<%=request.getContextPath()%>/assets/plugins/wow/wow.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDU79W1lu5f6PIiuMqNfT1C6M0e_lq1ECY"></script>

        <script src="<%=request.getContextPath()%>/assets/js/kidz.js"></script>


</body>
</html>