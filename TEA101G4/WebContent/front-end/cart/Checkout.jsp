<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.brand.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GYMPAYZ-CHECKOUT</title>
</head>
<body>
<jsp:include page="/front-end/header.jsp" flush="true" />
<!-- ============================================================================================================
———	CHECKOUT
================================================================================================================= -->
<section class="py-8 py-md-10">
  <div class="container">
    <div class="table-responsive-sm table-cart">
      <table class="table mb-0">
      <thead>
          <tr>
            <th class="bg-info text-white text-uppercase" scope="col"><div align="center">名稱</div></th>
            <th class="bg-info text-white text-uppercase" scope="col"><div align="center">品牌</div></th>
            <th class="bg-info text-white text-uppercase" scope="col"><div align="center">價格</div></th>
            <th class="bg-info text-white text-uppercase" scope="col"><div align="center">數量</div></th>
            <th class="bg-info text-white text-uppercase" scope="col"><div align="center">規格</div></th>
            <!-- <th scope="col">*specID</th> -->
          </tr>
       </thead>
<jsp:useBean id="specSvc" scope="page" class="com.spec.model.SpecService" />
	<%
		List<CartVO> buylist = (List<CartVO>) session.getAttribute("shoppingcart"); 
		String amount =  (String) session.getAttribute("amount"); 
	%>	
	<%	for (int i = 0; i < buylist.size(); i++) {
			CartVO order = buylist.get(i);
			pageContext.setAttribute("order", order);
			String productid = order.getProductid();
			String brandname = order.getBrandname();
			String productname = order.getProductname();
			Integer quantity = order.getQuantity();
			Integer price = order.getPrice();
			String specific = order.getSpecific();
			String specid = order.getSpecid();
	%>
	<tbody>
		<tr>
			<td width="200"><div align="center"><b><%=productname%></b></div></td>
			<td width="200"><div align="center"><b><%=brandname%></b></div></td>
			<td width="200"><div align="center"><b><%=price%></b></div></td>
			<td width="200"><div align="center"><b><%=quantity%></b></div></td>
			<c:forEach var="specVO" items="${specSvc.all}">
				<c:if test="${order.specid==specVO.specid}">
					<td width="200"><div align="center"><b>${specVO.specific}</b></div></td>
		 		</c:if>
			</c:forEach>
			<input type="hidden" name="quantity" value="<%=specid%>">
			<%-- <td width="200"><div align="center"><b><%=specid%></b></div></td> --%>
		</tr>
	<%
		}
	%> 
	</tbody>  
        <tr>
		<td></td>
		<td></td>
		<td><div align="center"><font color="red"><b>總金額：</b></font></div></td>
		<td></td>
		<td> <font color="red"><b>$<%=amount%></b></font> </td>
	</tr>
  </table>

</div>
<p>
<br>
<button onclick="self.location.href='<%=request.getContextPath()%>/front-end/product/list_product.jsp'" type="button" class="btn btn-info mb-2" data-toggle="tooltip" data-placement="left" title="Tooltip on left">
繼續購物
</button>
</div>
</section>

<jsp:include page="/front-end/ordermaster/addOrderMaster.jsp" flush="true" />
<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>