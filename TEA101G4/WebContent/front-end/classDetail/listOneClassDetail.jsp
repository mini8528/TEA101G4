<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.classDetail.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.classOrder.model.*"%>
<%@ page import="com.coachClass.model.*"%>
<%@ page import="java.util.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	System.out.println("==================================================");
	System.out.println("===  List One Class Detail . JSP  ====");
	System.out.println("==================================================");

	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
	if (userVO != null) {
		System.out.println("（one_product.jsp）當前會員= " + userVO.getMemberid());
	} else {
		System.out.println("userVO 是空的 ~~~~");
	}
	;
%>

<jsp:useBean id="list" scope="session"
	type="java.util.List<ClassDetailVO>" />


<html>
<head>
<title>ClassDetail資料 - listOneClassDetail.jsp</title>

</head>
<body bgcolor='white'>
	<jsp:include page="/front-end/header.jsp" flush="true" />


	<table id="table-1">
		<h3>
			訂單編號 : <%=request.getParameter("classOrderID")%>
		</h3>
	</table>

	<table>
		<tr>
			<th>照片</th>
			<th>課程名稱</th>
			<th>教練</th>
			<th>開課時間</th>
			<th>上課地址</th>
			<th>價格</th>
		</tr>



		<c:forEach var="classDetailVO" items="${list}">
			<jsp:useBean id="coService" scope="page" class="com.classOrder.model.ClassOrderService" />
			<jsp:useBean id="cocService" scope="page" class="com.coachClass.model.CoachClassService" />
			<tr>


				<td>照片</td>


				<c:forEach var="classOrderVO" items="${coService.all}">
					<c:if
						test="${classDetailVO.classOrderID == classOrderVO.classOrderID}">
						<c:forEach var="coachClassVO" items="${cocService.all}">
							<c:if
								test="${classDetailVO.coachClassID == coachClassVO.coachClassID}">
								<!-- 			課程名稱 -->
								<td>${coachClassVO.className}</td>
								<!-- 教練名字 -->
								<td>${cocService.getMemberName(coachClassVO.coachClassID)}</td>
								<!-- 開課時間 -->
								<td>${coachClassVO.startTime}</td>
								<!-- 上課地址 -->
								<td>${coachClassVO.address}</td>
								<!-- 價格 -->
								<td>${coachClassVO.price}</td>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
				
				
			</tr>
		</c:forEach>
		
		
		 <h4><a href="<%=request.getContextPath()%>/front-end/classOrder/listAllClassOrder.jsp">返回購物清單</a></h4>
		
		
	</table>
	<jsp:include page="/front-end/footer.jsp" flush="true" />

</body>
</html>