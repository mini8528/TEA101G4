<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ordermaster.model.*"%>
<%@ page import="com.adm.model.*"%>
<%
 	AdminnoVO adminVO = (AdminnoVO) session.getAttribute("adminVO");
	if(adminVO!=null){System.out.println("（Brand_select_page.jsp）當前管理員= "+adminVO.getAdminid());};
	pageContext.setAttribute("adminVO", adminVO);

	OrdermasterVO ordermasterVO = (OrdermasterVO) request.getAttribute("ordermasterVO");
	System.out.println(ordermasterVO);
%>
<html>
<body id="page-top">
<!-- =================================Page Wrapper(include)================================= -->
   <div id="wrapper">
	<%@ include file="/back-end/component/sidebar.jsp" %>
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
    <!-- Main Content -->
    <div id="content">
    <%@ include file="/back-end/component/topbar.jsp" %>
    <!-- Begin Page Content -->
    <div class="container-fluid">
    <!-- Page Heading -->
<!-- ============================================================ -->  
<h4>
	<a href="<%=request.getContextPath()%>/back-end/ordermaster/listAllordermaster.jsp">
	<img src="<%=request.getContextPath()%>/images/usagi.png" width="100" height="100" border="0"></a>
</h4>
<!-- ============================================================ --> 
    <h3 class="card-title text-warning">編輯訂單內容：</h3>
<div>
<!-- ============================================================ -->         
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<!-- ============================================================ --> 

<!-- ============================================================ -->
<FORM METHOD="post" ACTION="ordermaster.do" name="form1">
	<table class="table table-bordered text-center">
	<thead>
		<tr>
			<td>會員編號：<font color=blue><b>*</b></font></td>
			<td>
			<%=ordermasterVO.getMemberid()%>
			<input type="hidden" name="memberid"  value="<%=ordermasterVO.getMemberid()%>" />
			</td>
		</tr>
		<tr>
			<td>付款方式：<font color=blue><b>*</b></font></td>
			<td>
			<%=ordermasterVO.getPayment()%>
			<input type="hidden" name="payment"  value="<%=ordermasterVO.getPayment()%>" />
			<%-- <input type="radio" name="payment" value="信用卡">
	 		 <label for="信用卡" ${(ordermasterVO.payment=="信用卡")? 'selected':'' }>信用卡</label>
	 		 <input type="radio" name="payment" value="超商付款">
	 		 <label for="超商付款" ${(ordermasterVO.payment=="信用卡")? 'selected':'' }>超商付款</label> --%>
			</td>
		</tr>

		<tr>
			<td>下單日：<font color=blue><b>*</b></font></td>
			<td>
			<%=ordermasterVO.getOrderdate()%>
			</td>
		</tr>
		<tr>
			<td>付款期限：<font color=blue><b>*</b></font></td>
			<td>
			<%=ordermasterVO.getPayexpire()%>
			</td>
		</tr>
		<tr>
			<td>超商付款代碼：</td>
			<td><%=ordermasterVO.getPaycode()%>
			<input type="hidden" name="paycode" value="<%=ordermasterVO.getPaycode()%>" />
			</td>
		</tr>
		<tr>
			<td>付款狀態：</td>
			<td><%=ordermasterVO.getPaystatus()%>
			<input type="hidden" name="paystatus"  value="<%=ordermasterVO.getPaystatus()%>" />
			<%-- <select name="paystatus">
			<option value="N" ${(ordermasterVO.paystatus=="N")? 'selected':'' }>N</option>
			<option value="Y" ${(ordermasterVO.paystatus=="Y")? 'selected':'' }>Y</option>
			</select>  --%>
			</td>
		</tr>
		<tr>
			<td>收貨人：</td>
			<td>
			<input type="TEXT" name="receiver" value="<%=ordermasterVO.getReceiver()%>" />
			</td>
		</tr>
		<tr>
			<td>電話：</td>
			<td>
			<input type="TEXT" name="tel" value="<%=ordermasterVO.getTel()%>" />
			</td>
		</tr>
		<tr>
			<td>地址：</td>
			<td>
			<input type="TEXT" name="address" size="45" value="<%=ordermasterVO.getAddress()%>" />
			</td>
		</tr>
	
		<tr>
			<td>訂單狀態：</td>
			<td>
			<select name="orderstatus">
			<option value="未出貨" ${(ordermasterVO.orderstatus=="未出貨")? 'selected':'' }>未出貨</option>
			<option value="出貨" ${(ordermasterVO.orderstatus=="出貨")? 'selected':'' }>出貨</option>
			<option value="已完成" ${(ordermasterVO.orderstatus=="已完成")? 'selected':'' }>已完成</option>
			<option value="取消訂單" ${(ordermasterVO.orderstatus=="取消訂單")? 'selected':'' }>取消訂單</option>
			</select> 
			</td>
		</tr>
	</thead>
</table>
	<br> 
	<input type="hidden" name="action" value="update"> 
	<input type="hidden" name="orderdate" value="<%=ordermasterVO.getOrderdate()%>" />
	<input type="hidden" name="payexpire" value="<%=ordermasterVO.getPayexpire()%>" />
	<input type="hidden" name="ordermasterid" value="<%=ordermasterVO.getOrdermasterid()%>"> 
	<input type="submit" value="Send">
</FORM>

<!-- product section end -->
<!-- ---------------------------------------------------------------- -->
</div>
</div>
<!-- /.container-fluid -->
</div>
<!-- End of Main Content -->
<!-- End of Footer -->
</div>
<!-- End of Content Wrapper -->
</div>
<!-- End of Page Wrapper -->
<!-- ---------------------------------------------------------------- -->
	
</body>

<%
Timestamp orderdate = null;
try {
	orderdate = ordermasterVO.getOrderdate();
} catch (Exception e) {
	orderdate = new Timestamp(System.currentTimeMillis());
}
%>
<script>
   	function ShowTime(){
 		　document.getElementById('now').innerHTML = new Date();
 		　setTimeout('ShowTime()',1000);
 		} 
</script>

</html>