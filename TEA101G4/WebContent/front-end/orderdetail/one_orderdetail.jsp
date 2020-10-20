<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderdetail.model.*"%>
<%@ page import="com.ordermaster.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.cart.model.*"%>
<% 
	MemberVO userVO= (MemberVO) session.getAttribute("userVO");
	if(userVO!=null){System.out.println("（one_orderdetail.jsp）當前會員= "+userVO.getMemberid());};
%>
<%
	OrdermasterVO ordermasterVO = (OrdermasterVO) session.getAttribute("OrdermasterVO"); 
	String payment =  (String) session.getAttribute("payment");
	session.setAttribute("ordermasterVO", ordermasterVO);
	
	
	OrderdetailVO orderdetailVO = (OrderdetailVO)session.getAttribute("orderdetailVO");
	String specid = (String)session.getAttribute("specid");
	session.setAttribute("orderdetailVO", orderdetailVO);
%>	
<jsp:useBean id="list" scope="session" type="java.util.List<OrderdetailVO>" />
<!DOCTYPE html>
<html>
<head>
<style>
/* h3.unpaid{
color:#ff0047;
} */
</style>

</head>
<body bgcolor='white'>
<jsp:include page="../header.jsp" flush="true" />
<!-- 錯誤列表 -->
<c:if test="${not empty errorMsgs}">
	<font style = "color: red">請修正以下錯誤：</font>
	<ul>
		<c:forEach var = "message" items = "${errorMsgs}">
			<li style = "color: green">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<jsp:useBean id="specSvc" scope="page" class="com.spec.model.SpecService" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
<table>
</table>
<h3 class="pricing font-size-25">訂單編號：<%=request.getParameter("ordermasterid")%></h3>

<h3 class="pricing font-size-25">付款方式：<%=request.getParameter("payment")%></h3>

<c:if test="${ordermasterVO.payment=='超商代碼'}">
<h3 class="pricing font-size-25">超商代碼：<%=request.getParameter("paycode")%></h3>
</c:if>
<%-- <h3 class="pricing font-size-25">付款狀態：<%=request.getParameter("paystatus")%></h3> --%>
<c:choose>
	<c:when test="${ordermasterVO.paystatus == 'Y'}">
	<h3 class="pricing font-size-25">付款狀態：已付款</h3>
	</c:when>
	<c:otherwise>
	<h3 class="pricing font-size-25">付款狀態：<span style="color:red;">未付款</span></h3>
	</c:otherwise>
</c:choose>
<h3 class="pricing font-size-25">地址：<%=request.getParameter("address")%></h3>
<div class="table-responsive table-class-schedule">
<table class="table table-bordered text-center">
  <thead>
    <tr>
      <th class="bg-dark text-white text-uppercase" scope="col">商品圖</th>
      <th class="bg-dark text-white text-uppercase" scope="col">商品名稱</th>
      <th class="bg-dark text-white text-uppercase" scope="col">規格</th>
      <th class="bg-dark text-white text-uppercase" scope="col">單價</th>
      <th class="bg-dark text-white text-uppercase" scope="col">購買數量</th>
      <th class="bg-dark text-white text-uppercase" scope="col"></th>
    </tr>
  </thead>
<tbody>
<c:forEach var = "orderdetailVO" items = "${list}">
	<tr>
		<c:forEach var="specVO" items="${specSvc.all}">
			<c:if test="${orderdetailVO.specid==specVO.specid}">
				<c:forEach var="productVO" items="${productSvc.all}">
					<c:if test="${specVO.productid==productVO.productid}">
					<td>
						<a class="img-link list_pds2"
						href="<%=request.getContextPath()%>/back-end/product/product.do?productid=${productVO.productid}&action=getOne_For_Display_Front">
						<img src="<%=request.getContextPath()%>/back-end/product/productshow.do?productid=${productVO.productid}" width="50" height="50">
				 		</a>
				 	</td>
				 	<td>${productVO.name}</td>
				 	<td>${specVO.specific}</td>
				 	<td>${productVO.price}</td>
				 	<td>${orderdetailVO.quantity}</td>
			 		</c:if>
				</c:forEach>
	 		</c:if>
		</c:forEach>
	<td>
		<FORM METHOD = "post" ACTION = "<%=request.getContextPath()%>/front-end/pro/addPro.jsp" style = "margin-bottom: 0px;">
			<input type = "hidden" name = "specid" value = "${orderdetailVO.specid}">
			<input type = "submit" value = "評論" class="btn-dark mb-2">
		</FORM>
	</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>

<br>
<br>
	<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/back-end/ordermaster/ordermaster.do">
		<input type="hidden" name="action" value="getOrdermasterList_memberid"> 
		<input type="hidden" name="memberid" value="${userVO.memberid}">
		<input type="submit" value="返回個人訂單列表" class="btn btn-warning mb-2">
	</FORM>
<p>
<jsp:include page="../footer.jsp" flush="true" />
</body>
</html>