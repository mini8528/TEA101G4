<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderdetail.model.*"%>
<%@ page import="com.ordermaster.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.cart.model.*"%>
<%-- <jsp:useBean id="ordermasterSvc" scope="page" class="com.ordermaster.model.OrdermasterService" /> --%>
<% 
	/* OrderdetailService orderdetailSvc = new OrderdetailService();
	String ordermasterid = new String(request.getParameter("ordermasterid"));
	List<OrderdetailVO> list = orderdetailSvc.getDetailByMaster(ordermasterid);
	System.out.println("符合的Orderdetail共：" + list.size());
	request.getSession().setAttribute("list", list); */
	MemberVO userVO= (MemberVO) session.getAttribute("userVO");
	if(userVO!=null){System.out.println("（one_orderdetail.jsp）當前會員= "+userVO.getMemberid());};
%>
<%
	OrdermasterVO ordermaster = (OrdermasterVO) session.getAttribute("OrdermasterVO"); 
	String payment =  (String) session.getAttribute("payment"); 
%>	
<jsp:useBean id="list" scope="session" type="java.util.List<OrderdetailVO>" />
<html>
<head>
<style>
/* h3.unpaid{
color:#ff0047;
} */
</style>
<!-- <title>訂單成立-明細資料 - one_orderdetail.jsp</title> -->

<!-- <style>
table#table-1 {
	background-color: #d9ffef;
	border: 2px solid black;
	text-align: center;
}
table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>
<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

</style> -->
</head>
<body bgcolor='white'>
<jsp:include page="../header.jsp" flush="true" />
<%-- <table id="table-1">
	<tr>
		<td>
			<h3>你的訂單-明細資料 - one_orderdetail.jsp</h3>
			<h4>
				<a href="<%=request.getContextPath()%>/front-end/product/list_product.jsp"> 
				<img src="<%=request.getContextPath()%>/images/usagi.png" width="100" height="100" border="0">返回商品首頁</a>
			</h4>
		</td>
	</tr>
</table> --%>
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

<c:if test="${ordermaster.payment=='超商代碼'}">
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
	    </tr>
	  </thead>
		<tbody>
		<c:forEach var = "orderdetailVO" items = "${list}">
	
		<%-- <td>${orderdetailVO.ordermasterid}</td> --%>
		<!-- <td> -->
			<%-- <c:forEach var="i" begin="1" end="5" step="1"> --%>
				<%-- <span>${i}</span> --%>
			<%-- </c:forEach> --%>
		<!-- </td> -->
<!-- ---------------------------------- -->
	
	<tr>
	<td>
		<c:forEach var="specVO" items="${specSvc.all}">
			<c:if test="${orderdetailVO.specid==specVO.specid}">
				<c:forEach var="productVO" items="${productSvc.all}">
					<c:if test="${specVO.productid==productVO.productid}">
					<img src="<%=request.getContextPath()%>/back-end/product/productshow.do?productid=${productVO.productid}" width="100" height="100">
			 		</c:if>
			</c:forEach>
	 		</c:if>
		</c:forEach>
	</td>
<!-- ---------------------------------- -->	
	<td>
		<c:forEach var="specVO" items="${specSvc.all}">
			<c:if test="${orderdetailVO.specid==specVO.specid}">
				<c:forEach var="productVO" items="${productSvc.all}">
					<c:if test="${specVO.productid==productVO.productid}">
					${productVO.name}
			 		</c:if>
			</c:forEach>
	 		</c:if>
		</c:forEach>
	</td>
<!-- ---------------------------------- -->	
<td>
		<c:forEach var="specVO" items="${specSvc.all}">
			<c:if test="${orderdetailVO.specid==specVO.specid}">
				${specVO.specific}
	 		</c:if>
		</c:forEach>
	</td>
<!-- ---------------------------------- -->	
	<%-- <td>${orderdetailVO.specid}</td> --%>
<!-- ---------------------------------- -->	
	<td>
		<c:forEach var="specVO" items="${specSvc.all}">
			<c:if test="${orderdetailVO.specid==specVO.specid}">
				<c:forEach var="productVO" items="${productSvc.all}">
					<c:if test="${specVO.productid==productVO.productid}">
					${productVO.price}
			 		</c:if>
			</c:forEach>
	 		</c:if>
		</c:forEach>
	</td>
<!-- ---------------------------------- -->	
		<td>${orderdetailVO.quantity}</td>
	</tr>
</c:forEach>
		</tbody>
	</table>
</div>
<%-- <table>
	<tr>
		<!-- <th>訂單編號</th> -->
		<!-- <th>項目</th> -->
		<th>商品縮圖</th>
		<th>商品名稱</th>
		<th>規格</th>
		<!-- <th>*規格ID</th> -->
		<th>單價</th>
		<th>購買數量</th>
	</tr>
<c:forEach var="i" begin="1" end="${list.size()}" step="1">
<c:forEach var = "orderdetailVO" items = "${list}">
	
		<td>${orderdetailVO.ordermasterid}</td>
		<!-- <td> -->
			<c:forEach var="i" begin="1" end="5" step="1">
				<span>${i}</span>
			</c:forEach>
		<!-- </td> -->
<!-- ---------------------------------- -->
	
	
	<td>
		<c:forEach var="specVO" items="${specSvc.all}">
			<c:if test="${orderdetailVO.specid==specVO.specid}">
				<c:forEach var="productVO" items="${productSvc.all}">
					<c:if test="${specVO.productid==productVO.productid}">
					<img src="<%=request.getContextPath()%>/back-end/product/productshow.do?productid=${productVO.productid}" width="100" height="100">
			 		</c:if>
			</c:forEach>
	 		</c:if>
		</c:forEach>
	</td>
<!-- ---------------------------------- -->	
	<td>
		<c:forEach var="specVO" items="${specSvc.all}">
			<c:if test="${orderdetailVO.specid==specVO.specid}">
				<c:forEach var="productVO" items="${productSvc.all}">
					<c:if test="${specVO.productid==productVO.productid}">
					${productVO.name}
			 		</c:if>
			</c:forEach>
	 		</c:if>
		</c:forEach>
	</td>
<!-- ---------------------------------- -->	
<td>
		<c:forEach var="specVO" items="${specSvc.all}">
			<c:if test="${orderdetailVO.specid==specVO.specid}">
				${specVO.specific}
	 		</c:if>
		</c:forEach>
	</td>
<!-- ---------------------------------- -->	
	<td>${orderdetailVO.specid}</td>
<!-- ---------------------------------- -->	
	<td>
		<c:forEach var="specVO" items="${specSvc.all}">
			<c:if test="${orderdetailVO.specid==specVO.specid}">
				<c:forEach var="productVO" items="${productSvc.all}">
					<c:if test="${specVO.productid==productVO.productid}">
					${productVO.price}
			 		</c:if>
			</c:forEach>
	 		</c:if>
		</c:forEach>
	</td>
<!-- ---------------------------------- -->	
		<td>${orderdetailVO.quantity}</td>
	</tr>
</c:forEach>
</c:forEach>
</table> --%>
<br>
<br>
<%-- <jsp:useBean id="ordermasterSvc" scope="page" class="com.ordermaster.model.OrdermasterService" /> --%>
	<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/back-end/ordermaster/ordermaster.do">
	<%-- <c:forEach var="ordermasterVO" items="${ordermasterSvc.all}">
		<c:if test="${orderdetailVO.ordermasterid==ordermasterVO.ordermasterid}">
		${ordermasterVO.memberid}
			<input type="hidden" name="memberid" value="${ordermasterVO.memberid}">
 		</c:if>
	</c:forEach> --%>
		<input type="hidden" name="action" value="getOrdermasterList_memberid"> 
		<input type="hidden" name="memberid" value="${userVO.memberid}">
		<input type="submit" value="返回個人訂單列表" class="btn btn-warning mb-2">
	</FORM>
<p>
<jsp:include page="../footer.jsp" flush="true" />
</body>
</html>