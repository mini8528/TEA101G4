<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lat.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

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


<html>
<head>
<title>所有活動資料 - listAllLat.jsp</title>

<style>
#ellipsis {
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 1; /*最多顯示5行*/
            -webkit-box-orient: vertical;
            white-space: normal;
        }
 
</style>
<!-- ----------以下複製到虛線--------------------以下複製到虛線------------------------------------------- -->
 <!-- ---------------------------------------------------------------- -->

</head>
<body bgcolor='white'>
<jsp:include page="/front-end/header.jsp" flush="true" />
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
								<a class="text-muted" href="blog-single-left-sidebar.html">
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
 <!-- ------------------------------------------------------------------------------------ -->
<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>


</html>