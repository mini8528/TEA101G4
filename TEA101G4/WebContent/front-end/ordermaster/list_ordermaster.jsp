<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ordermaster.model.*"%>
<%@ page import="com.member.model.*"%>
<%
MemberVO userVO= (MemberVO) session.getAttribute("userVO");
if(userVO!=null){System.out.println("（Cart.jsp）當前會員= "+userVO.getMemberid());};
pageContext.setAttribute("userVO", userVO);
String memberid = new String(userVO.getMemberid());

	OrdermasterService ordermasterSvc = new OrdermasterService();
	List<OrdermasterVO> list = ordermasterSvc.getOrderByMemberid(memberid);
	System.out.println("符合的Ordermaster共：" + list.size());
	request.getSession().setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>

</head>
<body bgcolor='white'>
<jsp:include page="/front-end/header.jsp" flush="true" />
<!-- 錯誤列表 -->
<c:if test="${not empty errorMsgs}">
	<font style = "color: red">請修正以下錯誤：</font>
	<ul>
		<c:forEach var = "message" items = "${errorMsgs}">
			<li style = "color: green">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<h3 class="card-title text-warning">訂單紀錄：</h3>
<div class="table-responsive table-class-schedule">
	<table class="table table-bordered text-center">
	  <thead>
	    <tr>
	      <th class="bg-info text-white text-uppercase" scope="col">No.</th>
	      <th class="bg-info text-white text-uppercase" scope="col">付款方式</th>
	      <th class="bg-info text-white text-uppercase" scope="col">付款狀態</th>
	      <th class="bg-info text-white text-uppercase" scope="col">下單日</th>
	      <th class="bg-info text-white text-uppercase" scope="col">付款期限</th>
	      <th class="bg-info text-white text-uppercase" scope="col">超商付款代碼</th>
	      <th class="bg-info text-white text-uppercase" scope="col">收貨人</th>
	      <th class="bg-info text-white text-uppercase" scope="col">電話</th>
	      <th class="bg-info text-white text-uppercase" scope="col">地址</th>
	      <th class="bg-info text-white text-uppercase" scope="col">訂單狀態</th>
	      <th class="bg-info text-white text-uppercase" scope="col">明細</th>
	    </tr>
	  </thead>
		<tbody>
		<c:forEach var = "ordermasterVO" items = "${list}">
	<tr>
		<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.ordermasterid}</td>
		<%-- <td>${ordermasterVO.memberid}</td> --%>
		<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.payment}</td>
		<c:choose>
			<c:when test="${ordermasterVO.paystatus == 'Y'}">
			<td class="py-5 align-middle text-muted font-weight-medium">已付款</td>
			</c:when>
			<c:otherwise>
			<td class="py-5 align-middle text-muted font-weight-medium">待付款</td>
			</c:otherwise>
		</c:choose>
		<%-- <td>${ordermasterVO.paystatus}</td> --%>
		
		<%-- <td>${ordermasterVO.orderdate}</td> --%>
		<td class="py-5 align-middle text-muted font-weight-medium"><fmt:formatDate value="${ordermasterVO.orderdate}" pattern="yyyy-MM-dd"/></td>
		<%-- <td>${ordermasterVO.payexpire}</td> --%>
		<td class="py-5 align-middle text-muted font-weight-medium"><fmt:formatDate value="${ordermasterVO.payexpire}" pattern="yyyy-MM-dd"/></td>
		<c:choose>
			<c:when test="${ordermasterVO.payment == '超商代碼'}">
			<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.paycode}</td>
			</c:when>
			<c:otherwise>
			<td class="py-5 align-middle text-muted font-weight-medium">-</td>
			</c:otherwise>
		</c:choose>
		
		<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.receiver}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.tel}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.address}</td>
		<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.orderstatus}</td>
<!--  -->		
		<td class="py-5 align-middle text-muted font-weight-medium">
			<FORM METHOD = "post" ACTION = "<%=request.getContextPath()%>/back-end/orderdetail/orderdetail.do" style = "margin-bottom: 0px;">
				<input type = "hidden" name = "ordermasterid" value = "${ordermasterVO.ordermasterid}">
				<input type = "hidden" name = "payment" value = "${ordermasterVO.payment}">
				<input type = "hidden" name = "paycode" value = "${ordermasterVO.paycode}">
				<input type = "hidden" name = "paystatus" value = "${ordermasterVO.paystatus}">
				<input type = "hidden" name = "address" value = "${ordermasterVO.address}">
				<input type = "hidden" name = "action" value = "getOneList_ordermasterid">
				<input type = "submit" value = "View" class="btn-dark mb-2">
			</FORM>
		</td>
<!--  -->	
	</tr>
</c:forEach>
		</tbody>
	</table>
</div>
<p>
<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>