<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cartClass.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.coachClass.model.*"%>

<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
%>

<!DOCTYPE html>
<html>
<head>
<title>購物車 - Cart.jsp</title>
</head>
<body bgcolor='pink'>
	<jsp:include page="/front-end/header.jsp" flush="true" />

		<%
			List<CartClassVO> buylist = (List<CartClassVO>) session.getAttribute("shoppingCartClass");

			CoachClassService cocService = new CoachClassService();
			pageContext.setAttribute("cocService", cocService);
		%>
		<%
			if (buylist != null && (buylist.size() > 0)) {
		%>
		<%
			for (int index = 0; index < buylist.size(); index++) {
					CartClassVO order = buylist.get(index);
					pageContext.setAttribute("order", order);

					pageContext.setAttribute("order_CoachClassID", order.getCoachClassID());
		%>

		<%
			}
		%>
		<%
			}
		%>





		<!-- ====================================
———	CART
===================================== -->
		<section class="py-8 py-md-10">
			<div class="container">
				<div class="table-responsive-sm table-cart">
					<table class="table mb-0">
						<thead>
							<tr>
								<th scope="col"></th>
								<th scope="col">課程名稱</th>
								<th scope="col">教練</th>
								<th scope="col">價格</th>
								<th scope="col">時間</th>
								<th scope="col">地址</th>
							</tr>
						</thead>
						<tbody>
							<%
								if (buylist != null && (buylist.size() > 0)) {
							%>
							<%
								for (int index = 0; index < buylist.size(); index++) {
										CartClassVO order = buylist.get(index);
										pageContext.setAttribute("order", order);

										pageContext.setAttribute("order_CoachClassID", order.getCoachClassID());
							%>


							<tr>
								<td>
								
						              <button type="button" class="close" data-dismiss="alert" aria-label="Close">
						                <span aria-hidden="true">&times;</span>
						              </button>
														
									
									<img width="100" src="<%=request.getContextPath()%>/back-end/coachClass/coachClassShow.do?coachClassID=${order_CoachClassID}"
									alt="image" class="cart-image d-none d-md-block">
									</form>
								</td>



								<td class="td-product-name"><%=order.getClassName()%></td>
								<td class="td-product-name">${cocService.getMemberName(order_CoachClassID)}</td>
								<td class="td-product-name"><%=order.getPrice()%></td>
								<td class="td-product-name"><%=order.getStartTime()%></td>
								<td class="td-product-name"><%=order.getAddress()%></td>
							</tr>

							

						</tbody>


						<%
							}
						%>
						<%
							}
						%>
						
						<tr>
								<td colspan="4"></td>

								<td><a
									href="<%=request.getContextPath()%>/front-end/coachClass/listAllCoachClass.jsp"
									class="btn btn-danger text-uppercase btn-update">繼續瀏覽課程</a>
								</td>

								<td colspan="2" class="btnPart py-4">
								<form name="checkoutForm" action="<%=request.getContextPath()%>/front-end/cart/cartClass.do" method="get">
								<input type="hidden" name="action" value="CHECKOUT"> 
								<input type="hidden" name="memberid" value="${userVO.memberid}">
								<input type="submit" value="前往結帳"  class="btn btn-danger text-uppercase btn-update" >
								<i class="fa fa-arrow-circle-right ml-auto" aria-hidden="true"></i>
								</form>
								</td>
								
								
							</tr>

					</table>

				</div>
		</section>







		<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>