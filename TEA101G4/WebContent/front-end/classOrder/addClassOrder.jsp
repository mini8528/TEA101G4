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
	System.out.println("==================================================");
	System.out.println("======  填寫資料  加入訂單    add class Order . JSP   ================");
	System.out.println("==================================================");
%>



<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
	if (userVO != null) {
		System.out.println("（addClassOrder.jsp）當前會員= " + userVO.getMemberid());
	} else {
		System.out.println("userVO 是空的~~~");
	}
	;
	pageContext.setAttribute("userVO", userVO);

	ClassOrderVO classOrderVO = (ClassOrderVO) request.getAttribute("classOrderVO");
	System.out.println("ClassOrderVO = " + classOrderVO);

	List<CartClassVO> buylist = (List<CartClassVO>) session.getAttribute("shoppingCartClass");
	List<String> coachClassIDAll = new ArrayList<String>();
	List<String> quantityAll = new ArrayList<String>();

	System.out.println("buylist = "+ buylist);
	
	for (CartClassVO aa : buylist) {
		System.out.println("CartVO aa=" + aa.getCoachClassID() + ";" + aa.getQuantity());
		String coachClassID = aa.getCoachClassID();
		coachClassIDAll.add(coachClassID);

		
		
		Integer quantity = aa.getQuantity();
		quantityAll.add(quantity.toString());
		pageContext.setAttribute("aa", aa);
		pageContext.setAttribute("coachClassIDAll", coachClassIDAll);
		pageContext.setAttribute("quantityAll", quantityAll);
	}
	for (String ss : coachClassIDAll) {
		System.out.println("ss.toString() =" + ss.toString());
		pageContext.setAttribute("ss", ss);
	}
	for (String tt : quantityAll) {
		System.out.println("tt.toString() =" + tt.toString());
		pageContext.setAttribute("tt", tt);
	}

	long long1 = System.currentTimeMillis();
	java.sql.Date ds1 = new java.sql.Date(long1);

	long long2 = long1 + 3 * 24 * 60 * 60 * 1000L;
	java.sql.Date ds2 = new java.sql.Date(long2);
%>

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