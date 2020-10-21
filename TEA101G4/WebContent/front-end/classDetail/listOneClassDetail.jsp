<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.classDetail.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.classOrder.model.*"%>
<%@ page import="com.coachClass.model.*"%>
<%@ page import="java.util.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");

	
%>

<jsp:useBean id="list" scope="session"
	type="java.util.List<ClassDetailVO>" />

<!DOCTYPE html>
<html>
<head>
<title>ClassDetail資料 - listOneClassDetail.jsp</title>

</head>
<body bgcolor='white'>
	<jsp:include page="/front-end/header.jsp" flush="true" />







	<!-- ====================================
———	COURSES SECTION
===================================== -->
	<section class="py-8 py-md-5 list-fullwidth">
		<div class="container">

			<div class="media media-list-view mb-2">

				<div class="media-body">
					<h3 class="mb-3 mb-lg-2 mb-xl-3">
						<a class="text-purple text-capitalize font-weight-bold"
							href="course-single-left-sidebar.html">訂單編號 </a><b>- <%=request.getParameter("classOrderID")%></b>
					</h3>
					<ul class="list-unstyled d-flex text-muted mb-2">
						<li class="mr-3 text-capitalize font-weight-bold"><i
							class="fa fa-calendar-o mr-2 " aria-hidden="true"></i>訂購時間
							${classOrderVO.orderDate}</li>
					</ul>
					<ul class="list-unstyled d-flex text-muted mb-2">
						<li class="text-capitalize font-weight-bold"><i
							class="fa fa-clock-o mr-2" aria-hidden="true"></i>付款到期時間
							${classOrderVO.payExpire}</li>
					</ul>
					<p class="text-capitalize font-weight-bold mb-lg-2">付款方式 ：
						${classOrderVO.payment}</p>
					<p class="text-capitalize font-weight-bold mb-lg-2">付款狀態 ：
						${classOrderVO.paymentStatus}</p>
				</div>
				
				<div class="d-flex justify-content-left mt-8">
				<a class="btn btn-danger text-uppercase"
						href="<%=request.getContextPath()%>/front-end/classOrder/listAllClassOrder.jsp">返回購物清單</a>
						</div>
			</div>
		</div>
	</section>

	<section class="py-8 py-md-1 list-fullwidth">
		<div class="container">
			<div class="table-responsive-sm table-cart">
				<table class="table mb-0">
					<tr>
						<th>照片</th>
						<th>課程名稱</th>
						<th>教練</th>
						<th>開課時間</th>
						<th>上課地址</th>
						<th>價格</th>
					</tr>



					<c:forEach var="classDetailVO" items="${list}">
						<jsp:useBean id="coService" scope="page"
							class="com.classOrder.model.ClassOrderService" />
						<jsp:useBean id="cocService" scope="page"
							class="com.coachClass.model.CoachClassService" />


						<c:forEach var="classOrderVO" items="${coService.all}">
							<tr>

								<c:if
									test="${classDetailVO.classOrderID == classOrderVO.classOrderID}">
									<c:forEach var="coachClassVO" items="${cocService.all}">
										<c:if
											test="${classDetailVO.coachClassID == coachClassVO.coachClassID}">
											<!-- 照片 -->
											<td 
												class="mr-4"> <img class="cart-image d-none d-md-block" width="100px" 
												src="<%=request.getContextPath()%>/back-end/coachClass/coachClassShow.do?coachClassID=${coachClassVO.coachClassID}"
												alt="Card image">
											</td>
										</c:if>
									</c:forEach>
								</c:if>


								<c:if
									test="${classDetailVO.classOrderID == classOrderVO.classOrderID}">
									<c:forEach var="coachClassVO" items="${cocService.all}">
										<c:if
											test="${classDetailVO.coachClassID == coachClassVO.coachClassID}">
											<!-- 課程名稱 -->
											<td>${coachClassVO.className}</td>
										</c:if>
									</c:forEach>
								</c:if>

								<c:if
									test="${classDetailVO.classOrderID == classOrderVO.classOrderID}">
									<c:forEach var="coachClassVO" items="${cocService.all}">
										<c:if
											test="${classDetailVO.coachClassID == coachClassVO.coachClassID}">
											<!-- 教練名字 -->
											<td>${cocService.getMemberName(coachClassVO.coachClassID)}</td>
										</c:if>
									</c:forEach>
								</c:if>

								<c:if
									test="${classDetailVO.classOrderID == classOrderVO.classOrderID}">
									<c:forEach var="coachClassVO" items="${cocService.all}">
										<c:if
											test="${classDetailVO.coachClassID == coachClassVO.coachClassID}">
											<!-- 開課時間 -->
											<td>${coachClassVO.startTime}</td>
										</c:if>
									</c:forEach>
								</c:if>

								<c:if
									test="${classDetailVO.classOrderID == classOrderVO.classOrderID}">
									<c:forEach var="coachClassVO" items="${cocService.all}">
										<c:if
											test="${classDetailVO.coachClassID == coachClassVO.coachClassID}">
											<!-- 上課地址 -->
											<td>${coachClassVO.address}</td>
										</c:if>
									</c:forEach>
								</c:if>

								<c:if
									test="${classDetailVO.classOrderID == classOrderVO.classOrderID}">
									<c:forEach var="coachClassVO" items="${cocService.all}">
										<c:if
											test="${classDetailVO.coachClassID == coachClassVO.coachClassID}">
											<!-- 價格 -->
											<td>${coachClassVO.price}</td>
										</c:if>
									</c:forEach>
								</c:if>

							</tr>
						</c:forEach>
					</c:forEach>
				</table>
			</div>
		</div>
	</section>
















	<h4>
		<a
			href="<%=request.getContextPath()%>/front-end/classOrder/listAllClassOrder.jsp">返回購物清單</a>
	</h4>



	<jsp:include page="/front-end/footer.jsp" flush="true" />

</body>
</html>