<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cartClass.model.*"%>
<%@ page import="com.classOrder.model.*"%>
<%@ page import="com.coachClass.model.*"%>


<%

System.out.println("==========================================");
System.out.println("====   確認   購物車     Checkout Class . JSP       ====");
System.out.println("==========================================");

%>

<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body bgcolor='lightblue'>
<jsp:include page="/front-end/header.jsp" flush="true" />
<table>
	<tr>
		<th>照片</th>
		<th>課程名稱</th>
		<th>教練</th>
		<th>上課時間</th>
		<th>地址</th>
		<th>價格</th>
	</tr>
 	<%
		List<CartClassVO> buylist = (List<CartClassVO>) session.getAttribute("shoppingCartClass"); 
		String amount =  (String) request.getAttribute("amount"); 
		
		if(buylist != null){
			System.out.println("Buylist 存在 amount = " + amount);
		}else{
// 			System.out.println("Buylist 是空的 ~~~~~");
		}
		
// 		System.out.println("___ buylist __ 將資料輸入進 buylist ____");
	%>	
	<%	for (int i = 0; i < buylist.size(); i++) {
		CartClassVO order = buylist.get(i);
			pageContext.setAttribute("order", order);
			
			String memberid = order.getMemberid();
			String coachClassID = order.getCoachClassID();
			String className = order.getClassName();
			String memberid_Coach = order.getMemberid_Coach();
			Integer price = order.getPrice();
			String address = order.getAddress();
			Integer quantity = order.getQuantity();
			
	%>
	<tr>
		<td width="200"><div align="center"><b><%=memberid%></b></div></td>
		<td width="200"><div align="center"><b><%=coachClassID%></b></div></td>
		<td width="200"><div align="center"><b><%=className%></b></div></td>
		<td width="200"><div align="center"><b><%=memberid_Coach%></b></div></td>
		<td width="200"><div align="center"><b><%=price%></b></div></td>
		<td width="200"><div align="center"><b><%=address%></b></div></td>
		<td width="200"><div align="center"><b><%=quantity%></b></div></td>
		

	<%
		}
	%>
	<tr>
		<td></td>
		<td></td>
		<td><div align="center"><font color="red"><b>總金額：</b></font></div></td>
		<td></td>
		<td> <font color="red"><b>$<%=amount%></b></font> </td>
	</tr>
</table>


<p><a href="<%=request.getContextPath()%>/front-end/coachClass/listAllCoachClass.jsp">繼續購物</a>


<% System.out.println(" ___ 上半部 JSP 結束 ____"); %>

<jsp:include page="/front-end/classOrder/addClassOrder.jsp" flush="true" />


<p>
<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>