<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <!-- Site Tittle -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign Up Or Log In - Kidz</title>

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
———	SIGN-UP OR LOG-IN
===================================== -->
        <section class="py-8 py-md-10">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 offset-lg-2 col-lg-4">
                        <div class="mb-4 mb-sm-0">
                            <div class="bg-warning rounded-top p-2">
                                <h3 class="text-white font-weight-bold mb-0 ml-2">Create An Account</h3>
                            </div>

                            <div class="border rounded-bottom-sm border-top-0">
                                <div class="p-3">
                                    <form action="#" method="POST" role="form">
                                        <div class="form-group form-group-icon">
                                            <input type="text" class="form-control border" placeholder="Name"
                                                required="">
                                        </div>

                                        <div class="form-group form-group-icon">
                                            <input type="text" class="form-control border" placeholder="User name"
                                                required="">
                                        </div>

                                        <div class="form-group form-group-icon">
                                            <input type="text" class="form-control border" placeholder="Phone"
                                                required="">
                                        </div>

                                        <div class="form-group form-group-icon">
                                            <input type="password" class="form-control border" placeholder="Password"
                                                required="">
                                        </div>

                                        <div class="form-group form-group-icon">
                                            <input type="password" class="form-control border" placeholder="Re-Password"
                                                required="">
                                        </div>

                                        <div class="form-group">
                                            <button type="submit"
                                                class="btn btn-danger text-uppercase w-100">Register</button>
                                        </div>

                                        <div class="form-group text-center text-secondary mb-0">
                                            <p class="mb-0">Allready have an account? <a class="text-danger"
                                                    href="#">Log in</a></p>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-lg-4">
                        <div class="mb-4 mb-sm-0">
                            <div class="bg-warning rounded-top p-2">
                                <h3 class="text-white font-weight-bold mb-0 ml-2">Login</h3>
                            </div>

                            <div class="border rounded-bottom-sm border-top-0">
                                <div class="p-3">
                                    <form action="<%=request.getContextPath()%>/loginhandler" method="POST" role="form">
                                        <div class="form-group form-group-icon">
                                            <input type="text" class="form-control border" name="account"
                                                value="${account == null ? '' : account}" placeholder="User name"
                                                data-error="請輸入帳號" required="required">
                                        </div>

                                        <div class="form-group form-group-icon">
                                            <input type="password" class="form-control border" name="password"
                                                value="${password == null ? '' : password}" placeholder="Password"
                                                data-error="請輸入密碼" required="">
                                        </div>

                                        <div class="form-group">
                                            <button type="submit" class="btn btn-danger text-uppercase w-100">Log
                                                In</button>
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
            </div>
        </section>

    </div> <!-- element wrapper ends -->

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

    <script src="http://localhost:8081/TEA101G4/<%=request.getContextPath()%>/assets/plugins/wow/wow.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDU79W1lu5f6PIiuMqNfT1C6M0e_lq1ECY"></script>

    <script src="<%=request.getContextPath()%>/assets/js/kidz.js"></script>
    
</body>

</html>