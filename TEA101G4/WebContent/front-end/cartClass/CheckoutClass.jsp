<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cartClass.model.*"%>
<%@ page import="com.classOrder.model.*"%>
<%@ page import="com.coachClass.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body bgcolor='lightblue'>
	<jsp:include page="/front-end/header.jsp" flush="true" />

	<section class="py-8 py-md-5">
		<div class="container">
			<div class="table-responsive-sm table-cart">
				<table class="table mb-0">
					<thead>
						<tr>
							<th scope="col"></th>
							<th scope="col">課程名稱</th>
							<th scope="col">教練</th>
							<th scope="col">價格</th>
							<th scope="col">上課時間</th>
							<th scope="col">地址</th>

						</tr>
					</thead>
					<%
						List<CartClassVO> buylist = (List<CartClassVO>) session.getAttribute("shoppingCartClass");
						String amount = (String) request.getAttribute("amount");
						CoachClassService cocService = new CoachClassService();
						pageContext.setAttribute("cocService", cocService);
					%>
					<%
						for (int i = 0; i < buylist.size(); i++) {
							CartClassVO order = buylist.get(i);
							pageContext.setAttribute("order", order);
							String memberid = order.getMemberid();
							String coachClassID = order.getCoachClassID();
							String className = order.getClassName();
							String memberid_Coach = order.getMemberid_Coach();
							Integer price = order.getPrice();
							String address = order.getAddress();
							pageContext.setAttribute("coachClassID", coachClassID);
					%>
					<tbody>
						<tr>
							<td><img width="100"
								src="<%=request.getContextPath()%>/back-end/coachClass/coachClassShow.do?coachClassID=${coachClassID}"
								alt="image" class="cart-image d-none d-md-block"></td>
							<td><%=className%></td>
							<td>${cocService.getMemberName(coachClassID)}</td>
							<td><%=price%></td>
							<td><%=order.getStartTime()%></td>
							<td><%=address%></td>
						</tr>
					</tbody>
					<%
						}
					%>
				</table>
			</div>
		</div>
	</section>

		<jsp:include page="/front-end/classOrder/addClassOrder.jsp" flush="true" />


		<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>