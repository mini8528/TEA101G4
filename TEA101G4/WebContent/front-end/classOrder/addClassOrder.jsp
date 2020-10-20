<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.classOrder.model.*"%>
<%@ page import="com.classDetail.model.*"%>
<%@ page import="com.coachClass.model.*"%>
<%@ page import="com.cartClass.model.*"%>
<%@ page import="com.coachComment.model.*"%>
<%@ page import="com.member.model.*"%>





<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
	pageContext.setAttribute("userVO", userVO);

	String amount = (String) request.getAttribute("amount");

	ClassOrderVO classOrderVO = (ClassOrderVO) request.getAttribute("classOrderVO");

	List<CartClassVO> buylist = (List<CartClassVO>) session.getAttribute("shoppingCartClass");
	List<String> coachClassIDAll = new ArrayList<String>();
	List<String> quantityAll = new ArrayList<String>();

	for (CartClassVO aa : buylist) {
		String coachClassID = aa.getCoachClassID();
		coachClassIDAll.add(coachClassID);

		Integer quantity = aa.getQuantity();
		quantityAll.add(quantity.toString());
		pageContext.setAttribute("aa", aa);
		pageContext.setAttribute("coachClassIDAll", coachClassIDAll);
		pageContext.setAttribute("quantityAll", quantityAll);
	}
	for (String ss : coachClassIDAll) {
		pageContext.setAttribute("ss", ss);
	}
	for (String tt : quantityAll) {
		pageContext.setAttribute("tt", tt);
	}

	long long1 = System.currentTimeMillis();
	java.sql.Date ds1 = new java.sql.Date(long1);

	long long2 = long1 + 3 * 24 * 60 * 60 * 1000L;
	java.sql.Date ds2 = new java.sql.Date(long2);
%>
<!DOCTYPE html>
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /> -->
<!-- <title>課程訂單新增 - addClassOrder.jsp</title> -->

<!-- </head> -->
<!-- <body bgcolor='white'> -->


<%-- 	<%-- 錯誤表列 --%> --%>
<%-- 	<c:if test="${not empty errorMsgs}"> --%>
<!-- 		<font style="color: red">請修正以下錯誤:</font> -->
<!-- 		<ul> -->
<%-- 			<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 				<li style="color: red">${message}</li> --%>
<%-- 			</c:forEach> --%>
<!-- 		</ul> -->
<%-- 	</c:if> --%>


<!-- 	<!-- ==================================== -->
<!-- ———	CHECK-OUT SECTION -->
<!-- ===================================== --> -->
<!-- 	<section class="py-7 py-md-1"> -->
<!-- 		<div class="container"> -->
<!-- 			<FORM METHOD="post" -->
<%-- 				ACTION="<%=request.getContextPath()%>/back-end/classOrder/classOrder.do" --%>
<!-- 				name="form1"> -->

<!-- 				<tr> -->
<!-- 					<td>＊會員編號：</td> -->
<!-- 					<td><input type=hidden name="memberid" size="45" -->
<%-- 						value="<%=userVO.getMemberid()%>" /></td> --%>
<!-- 				</tr> -->


<!-- 				<td><input type=hidden name="memberid" size="45" -->
<%-- 					value="${userVO.getMemberid()}" /></td> --%>

<!-- 				<div class="row"> -->
<!-- 					<div class="col-md-7 col-lg-8 order-1 order-md-0"> -->
<!-- 						<form class="" action="index.html" method="post"> -->
<!-- 							<div class="card shadow-none"> -->
<!-- 								<div -->
<!-- 									class="card-header card-header-lg bg-danger text-white p-6 rounded-top"> -->
<!-- 									<h4 class="font-weight-bold mb-0">付款方式</h4> -->
<!-- 								</div> -->

<!-- 								<div class="card-body border border-top-0 rounded-bottom-sm p-7"> -->
<!-- 									<div class="mb-5"> -->
<!-- 										<div class="row align-items-center"> -->



<!-- 											<div class="col-6 col-md-7 col-lg-4 col-xl-4"> -->
<!-- 												<h3 class="font-weight-bold text-danger mb-0"> -->
<!-- 													信用卡 <span class="text-danger ml-2"> <i -->
<!-- 														class="fa fa-credit-card" aria-hidden="true"></i> -->
<!-- 													</span> <input type="radio" name="payment" value="信用卡" checked> -->
<!-- 													<label for="信用卡" -->
<%-- 														${(classOrderVO.payment=="信用卡")? 'selected':'' }></label> --%>
<!-- 												</h3> -->
<!-- 											</div> -->

<!-- 											<div class="col-6 col-md-7 col-lg-4 col-xl-4" type="radio" -->
<!-- 												name="payment" value="信用卡" checked> -->
<!-- 												<h3 class="font-weight-bold text-danger mb-0"> -->
<!-- 													超商付款 <span class="text-danger ml-2"> <i -->
<!-- 														class=" fa fa-calendar-o" aria-hidden="true"></i> -->
<!-- 													</span> <input type="radio" name="payment" value="超商付款"> <label -->
<!-- 														for="超商付款" -->
<%-- 														${(classOrderVO.payment=="超商付款")? 'selected':'' }></label> --%>
<!-- 												</h3> -->
<!-- 											</div> -->

<!-- 											<div> -->
<!-- 												<tr> -->
<!-- 													<td>付款狀態：</td> -->
<!-- 													<td><select name="paymentStatus"> -->
<!-- 															<option value="N" -->
<%-- 																${(classOrderVO.paymentStatus=="N")? 'selected':'' }>N</option> --%>
<!-- 															<option value="Y" -->
<%-- 																${(classOrderVO.paymentStatus=="Y")? 'selected':'' }>Y</option> --%>
<!-- 													</select></td> -->
<!-- 												</tr> -->
<!-- 											</div> -->
<!-- 											<div class="col-6 col-lg-5 col-xl-4 d-none d-lg-block"></div> -->
<!-- 										</div> -->
<!-- 									</div> -->

<!-- 									<div class="row"> -->
<!-- 										<div class="col-12"> -->
<!-- 											<label for="first-name">Card Number</label> -->
<!-- 											<div class="form-group form-group-icon"> -->
<!-- 												<i class="fa fa-lock"></i> <input type="text" -->
<!-- 													class="form-control border-warning" -->
<!-- 													placeholder="1234 5678 9012 3456" required=""> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->

<!-- 									<label for="first-name">Expiry Date</label> -->
<!-- 									<div class="form-group form-group-icon"> -->
<!-- 										<input type="text" class="form-control border-pink" -->
<!-- 											placeholder="MM/YY" required=""> -->
<!-- 									</div> -->

<!-- 									<label for="first-name">Full Name</label> -->
<!-- 									<div class="form-group form-group-icon"> -->
<!-- 										<input type="text" class="form-control border-purple" -->
<!-- 											placeholder="John Doe" required=""> -->
<!-- 									</div> -->

<!-- 									<label for="first-name">付款代碼：</label> -->
<!-- 									<div class="form-group form-group-icon"> -->

<!-- 										<input class="form-control border-info" type="TEXT" -->
<!-- 											name="payCode" size="45" -->
<%-- 											value="<%=(classOrderVO == null) ? "code9999" : classOrderVO.getPayCode()%>" --%>
<!-- 											placeholder="code" required="" /> -->
<!-- 									</div> -->


<!-- 									<div class="row"> -->
<!-- 										<div class="col-7"></div> -->
										
<!-- 										<input type="hidden" name="action" value="customer_insert"> -->

<%-- 									<c:forEach var="ss" items="${coachClassIDAll}"> --%>
<%-- 										<input type="hidden" name="coachClassID" value="${ss}"> --%>

<%-- 									</c:forEach> --%>

<%-- 									<c:forEach var="tt" items="${quantityAll}"> --%>
<%-- 										<input type="hidden" name="quantity" value="${tt}"> --%>
<%-- 									</c:forEach> --%>
										
<!-- 										<div class="col-4"> -->
<!-- 											<ul class="list-unstyled d-flex justify-content-between mt-4"> -->
<!-- 												<li><input type="submit" value="送出新增" -->
<!-- 													class="btn btn-danger text-white text-uppercase px-10"> -->
<!-- 												</li> -->
<!-- 											</ul> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->

<!-- 						</form> -->
<!-- 					</div> -->
<!-- 					=============================================================================================================================== -->
<!-- 					<div class="col-md-5 col-lg-4"> -->
<!-- 						<div class="card"> -->
<!-- 							<div -->
<!-- 								class="card-header card-header-lg bg-danger text-white p-6 rounded-top"> -->
<!-- 								<h4 class="font-weight-medium font-size-24 mb-0">注意項目</h4> -->
<!-- 							</div> -->

<!-- 							<table class="table mb-1"> -->
<!-- 								<tbody> -->
<!-- 									<tr> -->
<!-- 										<td class="col-xs-6 p-5"><strong>下單日</strong></td> -->
<%-- 										<td class="col-xs-6 text-right p-5"><strong><%=ds1%></strong></td> --%>
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td class="col-xs-6 p-5"><strong>付款期限</strong></td> -->
<%-- 										<td class="col-xs-6  p-5 text-right"><strong><%=ds2%></strong></td> --%>
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td class="col-xs-6 p-5"><strong>總金額：</strong></td> -->
<%-- 										<td class="col-xs-6 text-right p-5"><strong>$<%=amount%></strong></td> --%>
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td colspan="2" class="btnPart py-4"><a -->
<%-- 											href="<%=request.getContextPath()%>/front-end/cartClass/CartClass.jsp" --%>
<!-- 											class="btn btn-danger text-uppercase pull-right">返回購物車 <i -->
<!-- 												class="fa fa-arrow-circle-right ml-auto" aria-hidden="true"></i> -->
<!-- 										</a></td> -->
<!-- 									</tr> -->
<!-- 								</tbody> -->
<!-- 							</table> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</FORM> -->
<!-- 		</div> -->
<!-- 	</section> -->

<!-- </body> -->
<!-- </html> -->




<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>課程訂單新增 - addClassOrder.jsp</title>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>課程訂單新增 - addClassOrder.jsp</h3>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/classOrder/classOrder.do" name="form1">
		<table>

			<tr>
				<!-- <td>＊會員編號：</td> -->
				<td><input type=hidden name="memberid" size="45"
					value="<%=userVO.getMemberid()%>" /></td>
			</tr>


			<tr>
				<td>下單日：<font color=blue><b>*</b></font></td>
				<td><%=ds1%></td>
			</tr>
			<tr>
				<td>付款期限：<font color=blue><b>*</b></font></td>
				<td><%=ds2%></td>
			</tr>


			<td><input type=hidden name="memberid" size="45"
				value="${userVO.getMemberid()}" /></td>


			<tr>
				<td>付款方式：</td>
				<td><input type="radio" name="payment" value="信用卡" checked>
						<label for="信用卡" ${(classOrderVO.payment=="信用卡")? 'selected':'' }>信用卡</label>
					<input type="radio" name="payment" value="超商付款">
						<label for="超商付款" ${(classOrderVO.payment=="超商付款")? 'selected':'' }>超商付款</label>
				</td>
			</tr>


			<tr>
				<td>超商付款代碼：</td>
				<td><input type="TEXT" name="payCode" size="45"
					value="<%=(classOrderVO == null) ? "code9999" : classOrderVO.getPayCode()%>" />
				</td>
			</tr>

			<tr>
				<td>付款狀態：</td>
				<td><select name="paymentStatus">
						<option value="N"
							${(classOrderVO.paymentStatus=="N")? 'selected':'' }>N</option>
						<option value="Y"
							${(classOrderVO.paymentStatus=="Y")? 'selected':'' }>Y</option>
				</select></td>
			</tr>

			<!-- 測試 : 時間日期沒有 繳費代碼沒有 -->


		</table>
		<br> <input type="hidden" name="action" value="customer_insert">


		<c:forEach var="ss" items="${coachClassIDAll}">
			<input type="hidden" name="coachClassID" value="${ss}">
			
		</c:forEach>
		<h1></h1>
		<c:forEach var="tt" items="${quantityAll}">
			<input type="hidden" name="quantity" value="${tt}">
		</c:forEach>

		<input type="submit" value="送出新增">
	</FORM>

</body>




</html>