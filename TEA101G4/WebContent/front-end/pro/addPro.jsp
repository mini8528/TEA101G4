<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.spec.model.*"%>
<%@ page import="com.orderdetail.model.*"%>
<%@ page import="com.ordermaster.model.*"%>
<%
ProVO proVO = (ProVO) request.getAttribute("proVO");

ProductVO productVO = (ProductVO) request.getAttribute("productVO");
MemberVO userVO= (MemberVO) session.getAttribute("userVO");

OrderdetailVO orderdetailVO= (OrderdetailVO) session.getAttribute("orderdetailVO");
OrdermasterVO ordermasterVO= (OrdermasterVO) session.getAttribute("ordermasterVO");

String ordermasterid =  (String) session.getAttribute("ordermasterid");
String specid =  (String) session.getAttribute("specid");


%>
<jsp:useBean id="specSvc" scope="page" class="com.spec.model.SpecService" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

</head>
<body bgcolor='white'>
<jsp:include page="/front-end/header.jsp" flush="true" />

<div>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/back-end/pro/pro.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>�W��s��:</td>
		<td><%=request.getParameter("specid")%></td>
	</tr>
	
	<tr>
		<td>�|���s��:</td>
		<td><%=userVO.getMemberid()%></td>		
	</tr>
	<tr>
		<td>���פ��e :</td>
		<td><input type="TEXT" name="commtext" size="45" 
			 value="<%=(proVO==null)? "" : proVO.getCommtext()%>"/></td>
	</tr>
	
	<tr>
		<td>�P�ż�:</td>
		<td><select name="commstar" ${proVO.getCommstar}>
						<option>5</option>
						<option>4</option>
						<option>3</option>
						<option>2</option>
						<option>1</option>
				</select>
	</tr>

	<tr>	
<!-- 	<td>�s�W�ɶ�:</td>
		<td><input name="adddate" id="adddate" type="hidden"
		 value="" /></td>
	</tr> -->
	
<!-- 	<tr> -->
		
<!-- 		<td><input type="hidden" name="status" size="45"  -->
<!-- 			 value="Y"/></td> -->
<!-- 	</tr> -->
	
</table>
<br>
<input type="hidden" name="productid" value="<%=specSvc.getOneSpec(request.getParameter("specid")).getProductid()%>">
<input type="hidden" name="memberid" value="<%=userVO.getMemberid()%>">
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W">
</FORM>
</div>
<jsp:include page="/front-end/footer.jsp" flush="true" />

</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<% 
  java.sql.Date hiredate = null;
  try {
	    hiredate = proVO.getAdddate();
   } catch (Exception e) {
	    hiredate = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
  
        
        
</script>
</html>