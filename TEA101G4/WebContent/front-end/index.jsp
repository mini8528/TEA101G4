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
<section class="py-7 pb-lg-0 pt-lg-10">
	<div class="container">
		<div class="row align-items-center wow fadeInUp">
			<div class="col-sm-6 col-xs-12 order-sm-2 align-self-md-start align-self-lg-end">
<!--  			<video width="1000"  controls>    -->
<!--  			    <source src="./video/movie.mp4" type="video/mp4">  -->
<!--  			    Your browser does not support the video tag.    -->
<!--  			</video>  -->
				<img class="img-fluid px-8 px-md-0 lazyestload" data-src="<%=request.getContextPath()%>/assets/img/features/index2.jpg" alt="about-school">
			</div>

			<div class="col-sm-6 col-xs-12">
        <div class="mb-lg-7 mb-xl-10">
          <div class="section-title align-items-baseline mt-5 mt-md-0 mb-3 px-5 pl-md-1">
            <h2 class="text-color text-uppercase pl-lg-0"><span class="small text-danger font-weight-bold" style="font-size:60px">Gympayz <br> </span></h2>
          </div>
          <p class="font-size-18 font-weight-medium px-7 pl-md-6 pr-md-0 pl-lg-0">迅速找到需要的資源 find necessary resource at once</p>
          <p class="font-size-18 font-weight-medium px-7 pl-md-6 pr-md-0 pl-lg-0">有效率的安排課表arrange schedule efficiently</p>
          <p class="font-size-18 font-weight-medium px-7 pl-md-6 pr-md-0 pl-lg-0">多元化挑選適合自己的教練choose a suitable coach</p>
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
  <section class="pt-9 pb-7" id="blog">
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



<!-- ====================================
———	HOME FEATURE
===================================== -->
<!-- <section class="pt-9 pb-6 py-md-7"> -->
<!--   <div class="container"> -->
<!--     <div class="section-title justify-content-center mb-4 mb-md-8 wow fadeInUp"> -->
<!--       <span class="shape shape-left bg-info"></span> -->
<!--       <h2 class="text-danger">Our Features</h2> -->
<!--       <span class="shape shape-right bg-info"></span> -->
<!--     </div> -->

<!--     <div class="row wow fadeInUp"> -->
<!-- 			<!-- Media --> -->
<!--       <div class="col-sm-6 col-xl-4 col-xs-12"> -->
<!--         <div class="media mb-6"> -->
<!-- 					<div class="media-icon-large bg-warning mr-xl-4"> -->
<!-- 						<i class="fa fa-graduation-cap" aria-hidden="true"></i> -->
<!-- 					</div> -->

<!--           <div class="media-body"> -->
<!--             <h3 class="text-warning">Experienced Teachers</h3> -->
<!--             <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt.</p> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->

<!-- 			<!-- Media --> -->
<!--       <div class="col-sm-6 col-xl-4 col-xs-12"> -->
<!--         <div class="media mb-6"> -->
<!-- 					<div class="media-icon-large bg-success mr-xl-4"> -->
<!-- 						<i class="fa fa-leaf" aria-hidden="true"></i> -->
<!-- 					</div> -->

<!--           <div class="media-body"> -->
<!--             <h3 class="text-success">Physical Activity</h3> -->
<!--             <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt.</p> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->

<!-- 			<!-- Media --> -->
<!--       <div class="col-sm-6 col-xl-4 col-xs-12"> -->
<!--         <div class="media mb-6"> -->
<!-- 					<div class="media-icon-large bg-danger mr-xl-4"> -->
<!-- 						<i class="fa fa-car" aria-hidden="true"></i> -->
<!-- 					</div> -->
<!--           <div class="media-body"> -->
<!--             <h3 class="text-danger">Transportation</h3> -->
<!--             <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt.</p> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->

<!-- 			<!-- Media --> -->
<!--       <div class="col-sm-6 col-lg-4 col-xs-12"> -->
<!--         <div class="media mb-6"> -->
<!-- 					<div class="media-icon-large bg-info mr-xl-4"> -->
<!-- 						<i class="fa fa-cutlery" aria-hidden="true"></i> -->
<!-- 					</div> -->
<!--           <div class="media-body"> -->
<!--             <h3 class="text-info">Delicious Food</h3> -->
<!--             <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt.</p> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->

<!-- 			<!-- Media --> -->
<!--       <div class="col-sm-6 col-xl-4 col-xs-12"> -->
<!--         <div class="media mb-6"> -->
<!-- 					<div class="media-icon-large bg-purple mr-xl-4"> -->
<!-- 						<i class="fa fa-heart" aria-hidden="true"></i> -->
<!-- 					</div> -->
<!--           <div class="media-body"> -->
<!--             <h3 class="text-purple">Love & Care</h3> -->
<!--             <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt.</p> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->

<!-- 			<!-- Media --> -->
<!--       <div class="col-sm-6 col-xl-4 col-xs-12"> -->
<!--         <div class="media mb-7"> -->
<!-- 					<div class="media-icon-large bg-pink mr-xl-4"> -->
<!-- 						<i class="fa fa-shield" aria-hidden="true"></i> -->
<!-- 					</div> -->
<!--           <div class="media-body"> -->
<!--             <h3 class="text-pink">Annual Sports</h3> -->
<!--             <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt.</p> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
<!-- </section> -->

<!-- ====================================
———	CALL TO ACTION
===================================== -->
<!-- <section class="py-9 bg-parallax" style="background-image: url(assets/img/background/background-img-1.jpg);"> -->
<!-- 	<div class="container"> -->
<!--     <div class="wow fadeInUp"> -->
<!--       <div class="section-title justify-content-center"> -->
<!--         <h2 class="text-white text-center">Need More Information?</h2> -->
<!--       </div> -->
<!--       <div class="text-center"> -->
<!--         <p class="text-white font-size-18 mb-0">Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod</p> -->
<!--         <a href="contact-us.html" class="btn btn-danger shadow-sm text-uppercase mt-4"> -->
<!--           <i class="fa fa-phone mr-2" aria-hidden="true"></i>Contact -->
<!--         </a> -->
<!--       </div> -->
<!--     </div> -->
<!-- 	</div> -->
<!-- </section> -->

<!-- ====================================
———	COURSES SECTION
===================================== -->
<!-- <section class="py-9" id="courses"> -->
<!--   <div class="container"> -->
<!--     <div class="section-title justify-content-center mb-4 mb-md-8 wow fadeInUp"> -->
<!--       <span class="shape shape-left bg-info"></span> -->
<!--       <h2 class="text-danger">Our Courses</h2> -->
<!--       <span class="shape shape-right bg-info"></span> -->
<!--     </div> -->

<!--     <div class="row wow fadeInUp"> -->
<!--       <div class="col-sm-6 col-lg-3 col-xs-12"> -->
<!--         <div class="card"> -->
<!--           <a href="course-single-left-sidebar.html" class="position-relative"> -->
<!--             <img class="card-img-top lazyestload" data-src="assets/img/courses/courses-img1.jpg" src="assets/img/courses/courses-img1.jpg" alt="Card image"> -->
<!--             <div class="card-img-overlay"> -->
<!--               <span class="badge badge-warning badge-rounded-circle">$50</span> -->
<!--             </div> -->
<!--           </a> -->
<!--           <div class="card-body border-top-5 px-3 border-warning"> -->
<!--             <h3 class="card-title"> -->
<!--               <a class="text-warning text-capitalize d-block text-truncate" href="course-single-left-sidebar.html">Morbi Scelerisque Nibh.</a> -->
<!--             </h3> -->
<!--             <ul class="list-unstyled text-muted"> -->
<!--               <li class="mb-1"> -->
<!--                 <i class="fa fa-calendar-o mr-2" aria-hidden="true"></i>Age 2 to 4 Years -->
<!--               </li> -->
<!--               <li> -->
<!--                 <i class="fa fa-clock-o mr-2" aria-hidden="true"></i>9.00AM-11.00AM -->
<!--               </li> -->
<!--             </ul> -->

<!--             <p> It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. </p> -->
<!--             <div class="d-block"> -->
<!--               <a href="product-cart.html" class="btn btn-white text-uppercase mb-1 btn-hover-warning"> -->
<!--                 <i class="fa fa-shopping-basket mr-2" aria-hidden="true"></i>Add to Cart -->
<!--               </a> -->
<!--               <a href="course-single-left-sidebar.html" class="btn btn-link text-hover-warning pl-2 pl-lg-0"> -->
<!--                 <i class="fa fa-angle-double-right mr-1" aria-hidden="true"></i> More -->
<!--               </a> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->

<!-- 			<!-- Card --> -->
<!--       <div class="col-sm-6 col-lg-3 col-xs-12"> -->
<!--         <div class="card"> -->
<!--           <a href="course-single-left-sidebar.html" class="position-relative"> -->
<!--             <img class="card-img-top lazyestload" data-src="assets/img/courses/courses-img2.jpg" src="assets/img/courses/courses-img2.jpg" alt="Card image"> -->
<!--             <div class="card-img-overlay"> -->
<!--               <span class="badge badge-success badge-rounded-circle">$50</span> -->
<!--             </div> -->
<!--           </a> -->
<!--           <div class="card-body border-top-5 px-3 border-success"> -->
<!--             <h3 class="card-title"> -->
<!--               <a class="text-success text-capitalize d-block text-truncate" href="course-single-left-sidebar.html">Phasellus convallis eros.</a> -->
<!--             </h3> -->
<!--             <ul class="list-unstyled text-muted"> -->
<!--               <li class="mb-1"> -->
<!--                 <i class="fa fa-calendar-o mr-2" aria-hidden="true"></i>Age 2 to 4 Years -->
<!--               </li> -->
<!--               <li> -->
<!--                 <i class="fa fa-clock-o mr-2" aria-hidden="true"></i>9.00AM-11.00AM -->
<!--               </li> -->
<!--             </ul> -->
<!--             <p> It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. </p> -->
<!--             <div class="d-block"> -->
<!--               <a href="product-cart.html" class="btn btn-white text-uppercase mb-1 btn-hover-success"> -->
<!--                 <i class="fa fa-shopping-basket mr-2" aria-hidden="true"></i>Add to Cart -->
<!--               </a> -->
<!--               <a href="course-single-left-sidebar.html" class="btn btn-link text-hover-success pl-2 pl-lg-0"> -->
<!--                 <i class="fa fa-angle-double-right mr-1" aria-hidden="true"></i> More -->
<!--               </a> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->

<!-- 			<!-- Card --> -->
<!--       <div class="col-sm-6 col-lg-3 col-xs-12"> -->
<!--         <div class="card"> -->
<!--           <a href="course-single-left-sidebar.html" class="position-relative"> -->
<!--             <img class="card-img-top lazyestload" data-src="assets/img/courses/courses-img3.jpg" src="assets/img/courses/courses-img3.jpg" alt="Card image"> -->
<!--             <div class="card-img-overlay"> -->
<!--               <span class="badge badge-danger badge-rounded-circle">$50</span> -->
<!--             </div> -->
<!--           </a> -->
<!--           <div class="card-body border-top-5 px-3 border-danger"> -->
<!--             <h3 class="card-title"> -->
<!--               <a class="text-danger text-capitalize d-block text-truncate" href="course-single-left-sidebar.html">Suspendisse a libero da.</a> -->
<!--             </h3> -->
<!--             <ul class="list-unstyled text-muted"> -->
<!--               <li class="mb-1"> -->
<!--                 <i class="fa fa-calendar-o mr-2" aria-hidden="true"></i>Age 2 to 4 Years -->
<!--               </li> -->
<!--               <li> -->
<!--                 <i class="fa fa-clock-o mr-2" aria-hidden="true"></i>9.00AM-11.00AM -->
<!--               </li> -->
<!--             </ul> -->
<!--             <p> It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. </p> -->
<!--             <div class="d-block"> -->
<!--               <a href="product-cart.html" class="btn btn-white text-uppercase mb-1 btn-hover-danger"> -->
<!--                 <i class="fa fa-shopping-basket mr-2" aria-hidden="true"></i>Add to Cart -->
<!--               </a> -->
<!--               <a href="course-single-left-sidebar.html" class="btn btn-link text-hover-danger pl-2 pl-lg-0"> -->
<!--                 <i class="fa fa-angle-double-right mr-1" aria-hidden="true"></i> More -->
<!--               </a> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->

<!-- 			<!-- Card --> -->
<!--       <div class="col-sm-6 col-lg-3 col-xs-12"> -->
<!--         <div class="card"> -->
<!--           <a href="course-single-left-sidebar.html" class="position-relative"> -->
<!--             <img class="card-img-top lazyestload" data-src="assets/img/courses/courses-img4.jpg" src="assets/img/courses/courses-img4.jpg" alt="Card image"> -->
<!--             <div class="card-img-overlay"> -->
<!--               <span class="badge badge-info badge-rounded-circle">$50</span> -->
<!--             </div> -->
<!--           </a> -->
<!--           <div class="card-body border-top-5 px-3 border-info"> -->
<!--             <h3 class="card-title"> -->
<!--               <a class="text-info text-capitalize d-block text-truncate" href="course-single-left-sidebar.html">Aenean cursus urna nec.</a> -->
<!--             </h3> -->
<!--             <ul class="list-unstyled text-muted"> -->
<!--               <li class="mb-1"> -->
<!--                 <i class="fa fa-calendar-o mr-2" aria-hidden="true"></i>Age 2 to 4 Years -->
<!--               </li> -->
<!--               <li> -->
<!--                 <i class="fa fa-clock-o mr-2" aria-hidden="true"></i>9.00AM-11.00AM -->
<!--               </li> -->
<!--             </ul> -->
<!--             <p> It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. </p> -->
<!--             <div class="d-block"> -->
<!--               <a href="product-cart.html" class="btn btn-white text-uppercase mb-1 btn-hover-info"> -->
<!--                 <i class="fa fa-shopping-basket mr-2" aria-hidden="true"></i>Add to Cart -->
<!--               </a> -->
<!--               <a href="course-single-left-sidebar.html" class="btn btn-link text-hover-info pl-2 pl-lg-0"> -->
<!--                 <i class="fa fa-angle-double-right mr-1" aria-hidden="true"></i> More -->
<!--               </a> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
<!-- </section> -->

<!-- ====================================
———	TEACHERS SECTION
===================================== -->
<!-- <section class="pt-9 pb-2 py-md-10 bg-purple" id="teachers" style="background-image: url(assets/img/background/avator-bg.png);"> -->
<!--   <div class="container"> -->
<!--     <div class="section-title justify-content-center mb-2 mb-md-8 wow fadeInUp"> -->
<!--       <span class="shape shape-left bg-danger"></span> -->
<!--       <h2 class="text-white">Meet Our Teachers</h2> -->
<!--       <span class="shape shape-right bg-danger"></span> -->
<!--     </div> -->

<!--     <div class="team-slider owl-carousel owl-theme wow fadeInUp" dir="ltr"> -->
<!--       <div class="card card-hover card-transparent shadow-none"> -->
<!--         <div class="card-img-wrapper position-relative shadow-sm rounded-circle mx-auto"> -->
<!--           <img class="card-img-top rounded-circle lazyestload" data-src="assets/img/avator/avator-img1.jpg" src="assets/img/avator/avator-img1.jpg" alt="carousel-img"/> -->
<!--           <div class="card-img-overlay text-center rounded-circle"> -->
<!--             <ul class="list-unstyled mb-0 d-flex align-items-center h-100 justify-content-center"> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-facebook" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!--               </li> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-twitter" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!--               </li> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-google-plus" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!--               </li> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-pinterest-p" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="card-body text-center"> -->
<!--           <a class="font-size-20 font-weight-medium d-block" href="teachers-details.html">Amanda Smith</a> -->
<!--           <span class="text-white">English Teacher</span> -->
<!--         </div> -->
<!--       </div> -->

<!--       <div class="card card-hover card-transparent shadow-none"> -->
<!--         <div class="card-img-wrapper position-relative shadow-sm rounded-circle mx-auto"> -->
<!--           <img class="card-img-top rounded-circle lazyestload" data-src="assets/img/avator/avator-img2.jpg" src="assets/img/avator/avator-img2.jpg" alt="carousel-img"/> -->
<!--           <div class="card-img-overlay text-center rounded-circle"> -->
<!--             <ul class="list-unstyled mb-0 d-flex align-items-center h-100 justify-content-center"> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-facebook" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!--               </li> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-twitter" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!--               </li> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-google-plus" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!--               </li> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-pinterest-p" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="card-body position-relative text-center pb-0"> -->
<!--           <a class="font-size-20 font-weight-medium d-block" href="teachers-details.html">Katrina Owen</a> -->
<!--           <span class="text-white">History Teacher</span> -->
<!--         </div> -->
<!--       </div> -->

<!--       <div class="card card-hover card-transparent shadow-none"> -->
<!--         <div class="card-img-wrapper position-relative shadow-sm rounded-circle mx-auto"> -->
<!--           <img class="card-img-top rounded-circle lazyestload" data-src="assets/img/avator/avator-img3.jpg" src="assets/img/avator/avator-img3.jpg" alt="carousel-img"/> -->
<!--           <div class="card-img-overlay text-center rounded-circle"> -->
<!--             <ul class="list-unstyled mb-0 d-flex align-items-center h-100 justify-content-center"> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-facebook" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!-- 							</li> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-twitter" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!-- 							</li> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-google-plus" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!-- 							</li> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-pinterest-p" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!-- 							</li> -->
<!--             </ul> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="card-body position-relative text-center pb-0"> -->
<!--           <a class="font-size-20 font-weight-medium d-block" href="teachers-details.html">Samanta Doe</a> -->
<!--           <span class="text-white">Math Teacher</span> -->
<!--         </div> -->
<!--       </div> -->

<!--       <div class="card card-hover card-transparent shadow-none"> -->
<!--         <div class="card-img-wrapper position-relative shadow-sm rounded-circle mx-auto"> -->
<!--           <img class="card-img-top rounded-circle lazyestload" data-src="assets/img/avator/avator-img4.jpg" src="assets/img/avator/avator-img4.jpg" alt="carousel-img"/> -->
<!--           <div class="card-img-overlay text-center rounded-circle"> -->
<!--             <ul class="list-unstyled mb-0 d-flex align-items-center h-100 justify-content-center"> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-facebook" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!--               </li> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-twitter" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!--               </li> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-google-plus" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!--               </li> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-pinterest-p" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="card-body position-relative text-center pb-0"> -->
<!--           <a class="font-size-20 font-weight-medium d-block" href="teachers-details.html">Monica Dincule</a> -->
<!--           <span class="text-white">Languages Teacher</span> -->
<!--         </div> -->
<!--       </div> -->

<!--       <div class="card card-hover card-transparent shadow-none"> -->
<!--         <div class="card-img-wrapper position-relative shadow-sm rounded-circle mx-auto"> -->
<!--           <img class="card-img-top rounded-circle lazyestload" data-src="assets/img/avator/avator-img3.jpg" src="assets/img/avator/avator-img3.jpg" alt="carousel-img"/> -->
<!--           <div class="card-img-overlay text-center rounded-circle"> -->
<!--             <ul class="list-unstyled mb-0 d-flex align-items-center h-100 justify-content-center"> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-facebook" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!--               </li> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-twitter" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!--               </li> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-google-plus" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!--               </li> -->
<!--               <li class="mr-2"> -->
<!--                 <a href="#" class="text-white"> -->
<!-- 									<i class="fa fa-pinterest-p" aria-hidden="true"></i> -->
<!-- 								</a> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="card-body position-relative text-center pb-0"> -->
<!--           <a class="font-size-20 font-weight-medium d-block" href="teachers-details.html">Jone Doe</a> -->
<!--           <span class="text-white">History Teacher</span> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
<!-- </section> -->

<!-- ====================================
———	CONTACT SECTION
===================================== -->
<!-- <section class="bg-light py-7 py-md-10"> -->
<!--   <div class="container"> -->
<!--     <div class="row wow fadeInUp"> -->
<!--       <div class="col-sm-6 col-xs-12"> -->
<!--         <div class="section-title align-items-baseline mb-4"> -->
<!--           <h2 class="text-danger px-0 mb-0">Our Address</h2> -->
<!--         </div> -->
<!--         <p class="text-dark font-size-15">Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p> -->
<!--         <ul class="list-unstyled"> -->
<!--           <li class="media align-items-center mb-3"> -->
<!--             <div class="icon-rounded-circle-small bg-warning mr-2"> -->
<!--               <i class="fa fa-map-marker text-white"></i> -->
<!--             </div> -->
<!--             <div class="media-body"> -->
<!--               <p class="mb-0">9/4/C Ring Road,Garden Street Dhaka,Bangladesh-1200</p> -->
<!--             </div> -->
<!--           </li> -->
<!--           <li class="media align-items-center mb-3"> -->
<!--             <div class="icon-rounded-circle-small bg-success mr-2"> -->
<!--               <i class="fa fa-envelope text-white"></i> -->
<!--             </div> -->
<!--             <div class="media-body"> -->
<!--               <p class="mb-0"><a class="text-color" href="mailto:hello@example.com">hello@example.com</a></p> -->
<!--             </div> -->
<!--           </li> -->
<!--           <li class="media align-items-center mb-3"> -->
<!--             <div class="icon-rounded-circle-small bg-info mr-2"> -->
<!--               <i class="fa fa-phone text-white"></i> -->
<!--             </div> -->
<!--             <div class="media-body"> -->
<!--               <p class="mb-0"><a class="text-color" href="tel:[00] 333 555 888">333 555 888</a></p> -->
<!--             </div> -->
<!--           </li> -->
<!--         </ul> -->
<!--       </div> -->
<!--       <div class="col-sm-6 col-xs-12"> -->
<!--         <form> -->
<!--           <div class="form-group form-group-icon"> -->
<!--             <i class="fa fa-user "></i> -->
<!--             <input type="text" class="form-control border-warning" placeholder="First name" required> -->
<!--           </div> -->
<!--           <div class="form-group form-group-icon"> -->
<!--             <i class="fa fa-envelope "></i> -->
<!--             <input type="email" class="form-control border-success" placeholder="Email address" required> -->
<!--           </div> -->
<!--           <div class="form-group form-group-icon"> -->
<!--             <i class="fa fa-comments "></i> -->
<!--             <textarea class="form-control border-info" placeholder="Write message" rows="6"></textarea> -->
<!--           </div> -->
<!--             <button type="submit" class="btn btn-danger float-right text-uppercase"> -->
<!--               Send Message -->
<!--             </button> -->
<!--         </form> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
<!-- </section> -->

</div> <!-- element wrapper ends -->


<jsp:include page="footer.jsp" flush="true" />
</body>

</html>

