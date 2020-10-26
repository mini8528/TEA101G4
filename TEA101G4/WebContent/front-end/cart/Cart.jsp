<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.brand.model.*"%>
<%@ page import="com.spec.model.*"%>
<%@ page import="com.member.model.*"%>

<%
/* SpecService specSvc = new SpecService();
List<SpecVO> list = specSvc.getAll();
System.out.println("Spec筆數：" + list.size());
pageContext.setAttribute("list", list); */
/* SpecVO specVO = (SpecVO) request.getAttribute("specVO"); */
MemberVO userVO= (MemberVO) session.getAttribute("userVO");
if(userVO!=null){System.out.println("（Cart.jsp）當前會員= "+userVO.getMemberid());};
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GYMPAYZ-CART</title>
</head>
<body>
<jsp:include page="/front-end/header.jsp" flush="true" />

<!-- ============================================================================================================
———	CART
================================================================================================================= -->
<%List<CartVO> buylist = (List<CartVO>) session.getAttribute("shoppingcart");
pageContext.setAttribute("buylist", buylist);
%>

<section class="py-8 py-md-10">
  <div class="container">
    <div class="table-responsive-sm table-cart">
      <table class="table mb-0">
      <!-- buylist != null && buylist.size > 0-->
      <c:if test="${buylist != null && buylist.size() > 0}">
      <thead>
          <tr>
            <th class="bg-info text-white text-uppercase" align="center" scope="col" width="50"></th>
            <th class="bg-info text-white text-uppercase" align="center" scope="col">名稱</th>
            <th class="bg-info text-white text-uppercase" align="center" scope="col">品牌</th>
            <th class="bg-info text-white text-uppercase" align="center" scope="col">*specID</th>
            <th class="bg-info text-white text-uppercase" align="center" scope="col">規格</th>
            <th class="bg-info text-white text-uppercase" align="center" scope="col">價格</th>
            <th class="bg-info text-white text-uppercase" align="center" scope="col">數量</th>
            <th class="bg-info text-white text-uppercase" align="center" scope="col" width="10"></th>
          </tr>
        </thead>
        </c:if>
        <!--  -->
<jsp:useBean id="specSvc" scope="page" class="com.spec.model.SpecService" />
<%-- <%List<CartVO> buylist = (List<CartVO>) session.getAttribute("shoppingcart");%> --%>
<%	/* System.out.println("buylist.size()="+buylist.toString()); */ %>
<%if (buylist != null && (buylist.size() > 0)) {%>
<%
 for (int index = 0; index < buylist.size(); index++) {
 	CartVO order = buylist.get(index);
 	pageContext.setAttribute("order", order);
%>
<tbody>
	<tr>
		<td>
			<%-- <img src="<%=request.getContextPath()%>/back-end/product/showphoto.do?productid=${order.productid}&action=showPhoto1" alt="image" class="cart-image d-none d-md-block" width="60" height="60"> --%>
 			<img src="<%=request.getContextPath()%>/back-end/product/productshow.do?productid=${order.productid}" alt="image" class="cart-image d-none d-md-block" width="60" height="60">
		</td>
	  <!-- <td class="td-product-name">Product Name</td> -->
		<td width="200"><div align="center"><b><%=order.getProductname()%></b></div></td>
	<!-- <td>Brand</td> -->
		<td width="200"><div align="center"><b><%=order.getBrandname()%></b></div></td>
	<!-- <td>*specID</td> -->
		<td width="200"><div align="center"><b><%=order.getSpecid()%></b></div></td>
	<!-- <td>Specific</td> -->
			<c:forEach var="specVO" items="${specSvc.all}">
			<c:if test="${order.specid==specVO.specid}">
		<td width="200"><div align="center"><b>${specVO.specific}</b></div></td>
			</c:if>
			</c:forEach>
	<!-- <td>$Price</td> -->
		<td width="200"><div align="center"><b><%=order.getPrice()%></b></div></td>
	
		<td><input type="text" placeholder="<%=order.getQuantity()%>"></td>
	         
		<td width="100"><div align="center">
		<form name="deleteForm" action="<%=request.getContextPath()%>/front-end/cart/cart.do" method="POST">
			<input type="hidden" name="action" value="DELETE"> 
			<input type="hidden" name="del" value="<%= index %>">
			<input type="submit" value="刪除"></div>
		</td>
		</form>
	</tr>
<%}%>
  </tbody>
  </table>
</div>
<!--  -->
<p>
<p>
<td colspan="2" class="btnPart py-4">
<form name="checkoutForm" action="<%=request.getContextPath()%>/front-end/cart/cart.do" method="get">
<input type="hidden" name="action"	value="CHECKOUT"> 
<input type="hidden" name="memberid"	value="${userVO.memberid}"> 
<input type="submit" value="結帳" class="btn btn-danger text-uppercase pull-right">
</form>
</td>
<!--  -->
<%-- <%}%> --%>
<%}else{%>
	<div width="100%" style="text-align:center"><a><img src="<%=request.getContextPath()%>/images/usagi.png" width="100" height="100" border="0">購物車是空的</a></div>
	<br>
	<br>

<%}%>
</div>
</section>
<p>
<!-- ============================================================================================================
================================================================================================================= -->
<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>

