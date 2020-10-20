<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.lat.model.*"%>
<%@ page import="java.util.*"%>

<%
LatService latSvc = new LatService();
    List<LatVO> list = latSvc.getAll();
    pageContext.setAttribute("list",list);
    for (LatVO e : list) {
    	System.out.print(e.getLatestnewsid() + ",");
		System.out.print(e.getAdminid() + ",");
		System.out.print(e.getAdmin2id() + ",");
		System.out.print(e.getText() + ",");
		System.out.print(e.getImage() + ",");
		System.out.print(e.getAdddate() + ",");
		System.out.print(e.getUpdatetime()+",");
		System.out.print(e.getUploaddate() + ",");
		System.out.println();
    }
%>
<!DOCTYPE html>
<html lang="en">

<head>

<title>Gympayz首頁</title>

</head>

<body id="body" class="up-scroll">

  <!-- ====================================
  ——— PRELOADER
  ===================================== -->
  <div id="preloader" class="smooth-loader-wrapper">
    <div class="smooth-loader">
      <div class="loader">
        <span class="dot dot-1"></span>
        <span class="dot dot-2"></span>
        <span class="dot dot-3"></span>
        <span class="dot dot-4"></span>
      </div>
    </div>
  </div>

  <!-- ====================================
  ——— HEADER
  ===================================== -->
  <jsp:include page="indexheader.jsp" flush="true" />
  
 
 <!-- ====================================
———	CALL TO ACTION
===================================== -->
<section class="py-9 bg-parallax" style="background-image: url(<%=request.getContextPath()%>/assets/img/features/run.png);">
	<div class="container">
		<div class="wow fadeInUp">
			<div class="section-title justify-content-center">
				<h2 class="text-white text-center">Wellcome to GYMPAYZ</h2>
			</div>
			<div class="text-center mt-1">
<!-- 				<p class="text-white p-text-large mb-0">Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod</p> -->
				<a href="<%=request.getContextPath()%>/front-end/login.jsp" class="btn btn-white text-danger text-uppercase mt-5 font-size-16" style="background-color: lightgray">
					<i class="fa fa-sign-in" aria-hidden="true"></i>&nbsp &nbsp Join us
				</a>
			</div>
		</div>
	</div>
</section> 
  
  
  <!-- ====================================
———	ABOUT SCHOOL SECTION
===================================== -->
<section class="py-7 pb-lg-0 pt-lg-10" style="background-color: #212529">
	<div class="container">
		<div class="row align-items-center wow fadeInUp">
			<div class="col-sm-6 col-xs-12 order-sm-2 align-self-md-start align-self-lg-end">
<!--  			<video width="1000"  controls>    -->
<!--  			    <source src="./video/movie.mp4" type="video/mp4">  -->
<!--  			    Your browser does not support the video tag.    -->
<!--  			</video>  -->
				<img class="img-fluid px-8 px-md-0 lazyestload" data-src="<%=request.getContextPath()%>/assets/img/features/index2.jpg" alt="about-school" style="margin-bottom: 100px">
			</div>

			<div class="col-sm-6 col-xs-12">
        <div class="mb-lg-7 mb-xl-10">
          <div class="section-title align-items-baseline mt-5 mt-md-0 mb-3 px-5 pl-md-1">
            <h2 class="text-color text-uppercase pl-lg-0"><span class="small text-danger font-weight-bold" style="font-size:60px">Gympayz <br> </span></h2>
          </div>
          <p class="font-size-18 font-weight-medium px-7 pl-md-6 pr-md-0 pl-lg-0" style="color: #f0c24b"><i class="fa fa-check-square-o" aria-hidden="true"></i>&nbsp有效率的安排健身課表</p>
          <p class="font-size-18 font-weight-medium px-7 pl-md-6 pr-md-0 pl-lg-0" style="color: #f0c24b"><i class="fa fa-check-square-o" aria-hidden="true"></i>&nbsp多元化挑選適合自己的教練</p>
          <p class="font-size-18 font-weight-medium px-7 pl-md-6 pr-md-0 pl-lg-0" style="color: #f0c24b"><i class="fa fa-check-square-o" aria-hidden="true"></i>&nbsp輕鬆交流分享資訊</p>
          <div class="px-7 pr-md-0 pl-md-6 pl-lg-0">
<%--             <a href="<%=request.getContextPath()%>/front-end/login.jsp" class="btn btn-danger text-uppercase mt-5">Join us</a> --%>
          </div>
        </div>
			</div>
		</div>
	</div>
</section>
  
  
<!-- ====================================
———	LAST SECTION
===================================== -->
  <section class="pt-9 pb-7" id="blog" style="background-color:#BDC2BB">
  <div class="container">
    <div class="section-title justify-content-center mb-4 mb-md-8 wow fadeInUp">
      <span class="shape shape-left bg-info"></span>
      <h2 class="text-danger">Latest News</h2>
      <span class="shape shape-right bg-info"></span>
    </div>

    <div class="row wow fadeInUp">
    <c:forEach var="latVo" items="${list}">
			<div class="col-md-4">
			
        <div class="card">
					<div class="position-relative">
						<a href="<%=request.getContextPath()%>/back-end/lat/lat.do?action=getOne_For_Display&latestnewsid=${latVo.latestnewsid}">
	            <img class="card-img-top lazyestload" 
	            data-src="<%=request.getContextPath()%>/back-end/lat/latshow.do?latestnewsid=${latVo.latestnewsid}"
	             src="<%=request.getContextPath()%>/back-end/lat/latshow.do?latestnewsid=${latVo.latestnewsid}"
	              alt="Card image">
						</a>
<!-- 	          <div class="card-img-overlay p-0"> -->
<!-- 	            <span class="badge badge-danger badge-rounded m-4"> 14 <br> Jun</span> -->
<!-- 	          </div> -->
					</div>

          <div class="card-body border-top-5 px-3 border-danger">
<!--             <h3 class="card-title"> -->
<!--               <a class="text-danger text-capitalize d-block text-truncate" href="blog-single-left-sidebar.html">Vestibulum congue massa vitae.</a> -->
<!--             </h3> -->
						<ul class="list-unstyled d-flex flex-md-column flex-lg-row">
              <li class="mr-2">
								<a class="text-muted" href="#">
									<i class="fa fa-user mr-2" aria-hidden="true"></i>${latVo.adminid}
								</a>
              </li>

            </ul>
            <p class="mb-2" id="ellipsis">${latVo.text}</p>
            
            <a class="btn btn-link text-hover-danger pl-0"
            href="<%=request.getContextPath()%>/back-end/lat/lat.do?action=getOne_For_Display&latestnewsid=${latVo.latestnewsid}">
              <i class="fa fa-angle-double-right mr-1" aria-hidden="true"></i> Read More
            </a>
         
          </div>
        </div>
      </div>
      </c:forEach>

      <div class="col-md-4">
      </div>

      <div class="col-md-4">
    </div>

<!--     <div class="btn-aria text-center mt-4 wow fadeInUp"> -->
<!-- 			<a href="blog-grid.html" class="btn btn-danger text-uppercase">View More</a> -->
<!-- 		</div> -->
  </div>
  
</section>




</div> <!-- element wrapper ends -->


<jsp:include page="footer.jsp" flush="true" />
</body>

</html>

