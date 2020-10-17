<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.lat.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
LatVO latVO = (LatVO) request.getAttribute("latVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>



</head>
<body bgcolor='white'>
<!-- ------------------以下複製到虛線----include所在位置------------------------------------------ -->
 <jsp:include page="/front-end/header.jsp" flush="true" />
 
<!-- ---------------------------------------------------------------- -->         
<!-- ====================================
———	Blog SINGLE
===================================== -->

<section class="py-8 py-md-10">
	<div class="container">
		<div class="card">
			<div class="position-relative">
				<img class="card-img-top" 
				
				src="<%=request.getContextPath()%>/back-end/lat/latshow.do?latestnewsid=${latVO.latestnewsid}" 
				alt="Card image cap">
				<div class="card-img-overlay">
        </div>
			</div>
			<div class="card-body border-top-5 px-3 rounded-bottom border-warning">
				<ul class="list-unstyled d-flex mb-5">
					<li class="">
						<a class="text-muted d-inline-block mr-3" href="#user"><i class="fa fa-user mr-2" aria-hidden="true"></i>${latVO.adminid}</a>
					</li>
				</ul>

				<p class="card-text text-justify mb-6">${latVO.text}</p>

				
			
			</div>
		</div>

		

	</div>
</section>
<!-- -----------以下複製到虛線----------------------------以下複製到虛線------------------------- -->
			
	
<jsp:include page="/front-end/footer.jsp" flush="true" />

 
<!-- ---------------------------------------------------------------- -->

</body>
</html>