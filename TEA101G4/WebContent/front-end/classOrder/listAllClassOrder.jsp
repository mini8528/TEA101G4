<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.classOrder.model.*"%>
<%@ page import="com.member.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%


	MemberVO userVO= (MemberVO) session.getAttribute("userVO");
	pageContext.setAttribute("userVO", userVO);
	if(userVO!=null){System.out.println("（Cart.jsp）當前會員= "+userVO.getMemberid());};

	String memberid = new String(userVO.getMemberid());
	
	ClassOrderService coService = new ClassOrderService();
	
	List<ClassOrderVO> list = coService.getOrderByMemberId(memberid);
	request.getSession().setAttribute("list", list);
	
	System.out.println("list = "+list);
	
%>

<!DOCTYPE html>
<html>
<head>
<title>所有訂單資料 - listAllClassOrder.jsp</title>


</head>
<body bgcolor='white'>
<jsp:include page="/front-end/header.jsp" flush="true" />


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


	<%@ include file="page1.file" %> 
	<c:forEach var="classOrderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	


<!-- ====================================
———	COURSES SECTION
===================================== -->
<section class="py-8 py-md-1 list-fullwidth">
  <div class="container">

		<div class="media media-list-view mb-5">

		  <div class="media-body">
		    <h3 class="mb-3 mb-lg-2 mb-xl-3">
          <a class="text-purple text-capitalize font-weight-bold" href="course-single-left-sidebar.html">訂單編號　</a><b>-　${classOrderVO.classOrderID}</b>
        </h3>

				<ul class="list-unstyled d-flex text-muted mb-2">
					<li class="mr-3 text-capitalize font-weight-bold">
						<i class="fa fa-calendar-o mr-2 " aria-hidden="true"></i>訂購時間 ${classOrderVO.orderDate}
					</li>
					<li class="text-capitalize font-weight-bold">
						<i class="fa fa-clock-o mr-2" aria-hidden="true"></i>付款到期時間 ${classOrderVO.payExpire}
					</li>
				</ul>

		    <p class="text-capitalize font-weight-bold mb-lg-2">付款方式　：　${classOrderVO.payment}</p>
		    <p class="text-capitalize font-weight-bold mb-lg-2">付款狀態　：　${classOrderVO.paymentStatus}</p>

				<div class="">
<!-- 					<a href="product-cart.html" class="btn btn-sm btn-white text-uppercase mb-1 mr-2 btn-hover-purple"> -->
<!-- 			            <i class="fa fa-angle-double-right mr-2" aria-hidden="true"></i>更多明細 -->
<!-- 			        </a> -->
			        
			        
			        <FORM METHOD = "post" ACTION = "<%=request.getContextPath()%>/back-end/classDetail/classDetail.do" style = "margin-bottom: 0px;">
						<i class="fa fa-angle-double-right mr-2" aria-hidden="true">
						<input class="btn btn-sm btn-white text-uppercase mb-1 mr-2 btn-hover-purple fa-angle-double-right" type = "submit" value = "更多明細">
						</i>
						<input type = "hidden" name = "classOrderID" value = "${classOrderVO.classOrderID}">
						<input  type = "hidden" name = "action" value = "getOneList_classOrderID">
					</FORM>
			        
          
        		</div>
		  </div>
		</div>

  </div>
</section>




	</c:forEach>
</table>
<%@ include file="page2.file" %>




<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>