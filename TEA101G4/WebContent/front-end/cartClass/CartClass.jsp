<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cartClass.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.coachClass.model.*"%>

<%

MemberVO userVO= (MemberVO) session.getAttribute("userVO");
if(userVO!=null){System.out.println("（CartClass.jsp）當前會員= "+userVO.getMemberid());};


System.out.println("==========================================");
System.out.println("==========    購物車      Cart Class . JSP      ==============");
System.out.println("==========================================");

%>


<html>
<head>
<title>購物車 - Cart.jsp</title>
</head>
<body bgcolor='pink'>
<jsp:include page="/front-end/header.jsp" flush="true" />
<table>
	<tr>
		<th>照片</th>
		<th>課程名稱</th>
		<th>教練</th>
		<th>上課時間</th>
		<th>地址</th>
		<th>價格</th>
		<th width="80"></th>
	</tr>
	
<%List<CartClassVO> buylist = (List<CartClassVO>) session.getAttribute("shoppingCartClass");%>
<%if (buylist != null && (buylist.size() > 0)) {%>
<%
System.out.println("記錄點 _____ buylist ____");

 for (int index = 0; index < buylist.size(); index++) {
	 CartClassVO order = buylist.get(index);
 	pageContext.setAttribute("order", order);
 	
 	CoachClassService cocService = new CoachClassService();
	List<CoachClassVO> list = cocService.getAll();
	pageContext.setAttribute("list", list);
 	System.out.println("list : "+list);
%>

<!-- 購物車內課程的內容 -->
	<tr>
		<td><img src="<%=request.getContextPath()%>/front-end/coachClass/cartClass.do?coachClassID=${coachClassVO.coachClassID}"
					width="200" height="200"></td>
		<td width="200"><div align="center"><b><%=order.getClassName()%></b></div></td>
		<td width="200"><div align="center"><b><%=order.getMemberid_Coach()%></b></div></td>
		<td width="200"><div align="center"><b><%=order.getStartTime()%></b></div></td> 
		<td width="200"><div align="center"><b><%=order.getAddress()%></b></div></td> 
		<td width="200"><div align="center"><b><%=order.getPrice()%></b></div></td> 
		<td width="100"><div align="center">
		<form name="deleteForm" action="<%=request.getContextPath()%>/front-end/cart/cartClass.do" method="POST">
          <input type="hidden" name="action" value="DELETE"> 
          <input type="hidden" name="del" value="<%= index %>">
          <input type="submit" value="刪除"></div>
      	</td>
      	</form>
	</tr>
	<%}%>
<!-- </table> -->
<p>
 	<form name="checkoutForm" action="<%=request.getContextPath()%>/front-end/cart/cartClass.do" method="get">
	     <input type="hidden" name="action"	value="CHECKOUT"> 
	     <input type="hidden" name="memberid"	value="${userVO.memberid}"> 
	     <input type="submit" value="付款結帳">
	</form>
<%}%>
<button><a href="<%=request.getContextPath()%>/front-end/product/list_product.jsp">繼續購物</a></button>
</table>
<p>
<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>