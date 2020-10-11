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
<body>

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