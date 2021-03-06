<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ordermaster.model.*"%>
<%@ page import="com.orderdetail.model.*"%>
<%@ page import="com.adm.model.*"%>
<%
 	AdminnoVO adminVO = (AdminnoVO) session.getAttribute("adminVO");
	if(adminVO!=null){System.out.println("（Brand_select_page.jsp）當前管理員= "+adminVO.getAdminid());};
	pageContext.setAttribute("adminVO", adminVO);
	String adminid = new String(adminVO.getAdminid());

	OrdermasterService ordermasterSvc = new OrdermasterService();
	List<OrdermasterVO> list = ordermasterSvc.getAll();
	System.out.println("訂單總筆數：" + list.size());
	pageContext.setAttribute("list", list);
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
    <h3 class="card-title text-warning">所有訂單列表：</h3>
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
<ul> 
<li><input type="button" value="查詢全部訂單" onclick="location.href='listAllordermaster.jsp'"></li>
<!--  -->
	<li>
		<FORM METHOD="post" ACTION="ordermaster.do">
			<b>輸入訂單編號 (如OR00001):</b> 
				<input type="text" name="ordermasterid"> 
				<input type="hidden" name="action" value="getOne_For_Display"> 
				<input type="submit" value="送出">
		</FORM>
	</li>
<!--  -->
	<li>
		<FORM METHOD="post" ACTION="ordermaster.do">
			<b>會員ID查看訂單列表 (如M004):</b> 
				<input type="text" name="memberid"> 
				<input type="hidden" name="action" value="getSomeList_memberid"> 
				<input type="submit" value="送出">
		</FORM>
	</li>
<!--  -->
<li>
	<FORM METHOD="post" ACTION="ordermaster.do">
		<b>依訂單出貨狀態篩選:</b> 
			<select size="1" name="orderstatus">
				<option value="未出貨" ${(ordermasterVO.orderstatus=="未出貨")? 'selected':'' }>未出貨</option>
				<option value="出貨" ${(ordermasterVO.orderstatus=="出貨")? 'selected':'' }>出貨</option>
				<option value="已完成" ${(ordermasterVO.orderstatus=="已完成")? 'selected':'' }>已完成</option>
				<option value="取消訂單" ${(ordermasterVO.orderstatus=="取消訂單")? 'selected':'' }>取消訂單</option>
			<%-- 
			<c:forEach var="ordermasterVO" items="${ordermasterSvc.all}">
				<option value="${ordermasterVO.orderstatus}">${ordermasterVO.orderstatus}
			</c:forEach> 
			--%>
			</select> 
			<input type="hidden" name="action" value="getSomeList_orderstatus">
			<input type="submit" value="送出">
	</FORM>
</li>
<!--  -->
	<li>
		<FORM METHOD="post" ACTION="ordermaster.do">
			<b>查看未付款＆超過期限訂單:</b> 
				<input type="hidden" name="paystatus" value = "N"> 
				<input type="hidden" name="action" value="list_ByOrderstatus"> 
				<input type="submit" value="查詢">
		</FORM>
	</li>
<!--  -->	
	<li>
		<FORM METHOD="post" ACTION="ordermaster.do">
			<b>查看已付款＆未出貨:</b> 
				<input type="hidden" name="paystatus" value = "Y"> 
				<input type="hidden" name="orderstatus" value = "未出貨"> 
				<input type="hidden" name="action" value="list_ByPay_Osta"> 
				<input type="submit" value="查詢">
		</FORM>
	</li>
<!--  -->
	<li>
    <label>
      <input type="checkbox" name="CheckAll" value="核取方塊" id="CheckAll" />
      全選</label>
    </li>
</ul>
<!--  -->
<!-- ============================================================ --> 
<jsp:useBean id="orderdetailSvc" scope="page" class="com.orderdetail.model.OrderdetailService" />
<div class="table-responsive table-class-schedule">
<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/back-end/ordermaster/ordermaster.do">
<input type="hidden" name="action" value="update_some_orderstatus"> 
<input type="submit" value="多筆出貨">
<br>
<table class="table table-bordered text-center">
<thead>
	<tr>
		<th class="bg-info text-white text-uppercase" scope="col"></th>
		<th class="bg-info text-white text-uppercase" scope="col">訂單ID</th>
		<th class="bg-info text-white text-uppercase" scope="col">會員ID</th>
		<th class="bg-info text-white text-uppercase" scope="col">付款</th>
		<th class="bg-info text-white text-uppercase" scope="col">付款狀態</th>
		<th class="bg-info text-white text-uppercase" scope="col">下單日</th>
		<th class="bg-info text-white text-uppercase" scope="col">付款期限</th>
		<th class="bg-info text-white text-uppercase" scope="col">超商代碼</th>
		<th class="bg-info text-white text-uppercase" scope="col">收貨人</th>
		<th class="bg-info text-white text-uppercase" scope="col">電話</th>
		<th class="bg-info text-white text-uppercase" scope="col">地址</th>
		<th class="bg-info text-white text-uppercase" scope="col">訂單狀態</th>
		<th class="bg-info text-white text-uppercase" scope="col"></th>
		<th class="bg-info text-white text-uppercase" scope="col">編輯</th>
	</tr>
</thead>
<%@ include file = "page1.file" %>
<c:forEach var = "ordermasterVO" items = "${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<tbody>
<tr>
	<td class="py-5 align-middle text-muted font-weight-medium">
      <input type="checkbox" name="ordermasterid" value="${ordermasterVO.ordermasterid}">
    </td>
    <td class="py-5 align-middle text-muted font-weight-medium">
	<a href="<%=request.getContextPath()%>/back-end/orderdetail/orderdetail.do?ordermasterid=${ordermasterVO.ordermasterid}&action=getSomeList_ordermasterid">${ordermasterVO.ordermasterid}</a>
	</td>
	<%-- <td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.ordermasterid}</td> --%>
	
	<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.memberid}</td>
	<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.payment}</td>
	<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.paystatus}</td>
	<%-- <td>${ordermasterVO.orderdate}</td> --%>
	<td class="py-5 align-middle text-muted font-weight-medium"><fmt:formatDate value="${ordermasterVO.orderdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<%-- <td>${ordermasterVO.payexpire}</td> --%>
	<td class="py-5 align-middle text-muted font-weight-medium"><fmt:formatDate value="${ordermasterVO.payexpire}" pattern="yyyy-MM-dd"/></td>
<c:choose>
	<c:when test="${ordermasterVO.payment == '超商付款'}">
	<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.paycode}</td>
	</c:when>
	<c:otherwise>
	<td class="py-5 align-middle text-muted font-weight-medium">-</td>
	</c:otherwise>
</c:choose>
	<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.receiver}</td>
	<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.tel}</td>
	<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.address}</td>
	
	<c:choose>
		<c:when test="${ordermasterVO.orderstatus == '未出貨'}">
		<td class="py-5 align-middle text-muted font-weight-medium"><span style="color:green;">${ordermasterVO.orderstatus}</span></td>
		</c:when>
		<c:when test="${ordermasterVO.orderstatus == '出貨'}">
		<td class="py-5 align-middle text-muted font-weight-medium"><span style="color:red;">${ordermasterVO.orderstatus}</span></td>
		</c:when>
		<c:otherwise>
		<td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.orderstatus}</td>
		</c:otherwise>
	</c:choose>
	
	<%-- <td class="py-5 align-middle text-muted font-weight-medium">${ordermasterVO.orderstatus}</td> --%>
		
<%-- 	<td class="py-5 align-middle text-muted font-weight-medium">
		<FORM METHOD = "get" ACTION = "<%=request.getContextPath()%>/back-end/ordermaster/ordermaster.do" style = "margin-bottom: 0px;">
			<input type = "submit" value = "出貨">
			<input type = "hidden" name = "ordermasterid" value = "${ordermasterVO.ordermasterid}">
			<input type = "hidden" name = "action" value = "update_some_orderstatus">
		</FORM>
	</td> --%>
	<td class="py-5 align-middle text-muted font-weight-medium">
	<span>
		<a href="<%=request.getContextPath()%>/back-end/ordermaster/ordermaster.do?ordermasterid=${ordermasterVO.ordermasterid}&action=update_some_orderstatus">
		<svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="shipping-fast" class="svg-inline--fa fa-shipping-fast fa-w-20" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512"><path fill="currentColor" d="M624 352h-16V243.9c0-12.7-5.1-24.9-14.1-33.9L494 110.1c-9-9-21.2-14.1-33.9-14.1H416V48c0-26.5-21.5-48-48-48H112C85.5 0 64 21.5 64 48v48H8c-4.4 0-8 3.6-8 8v16c0 4.4 3.6 8 8 8h272c4.4 0 8 3.6 8 8v16c0 4.4-3.6 8-8 8H40c-4.4 0-8 3.6-8 8v16c0 4.4 3.6 8 8 8h208c4.4 0 8 3.6 8 8v16c0 4.4-3.6 8-8 8H8c-4.4 0-8 3.6-8 8v16c0 4.4 3.6 8 8 8h208c4.4 0 8 3.6 8 8v16c0 4.4-3.6 8-8 8H64v128c0 53 43 96 96 96s96-43 96-96h128c0 53 43 96 96 96s96-43 96-96h48c8.8 0 16-7.2 16-16v-32c0-8.8-7.2-16-16-16zM160 464c-26.5 0-48-21.5-48-48s21.5-48 48-48 48 21.5 48 48-21.5 48-48 48zm320 0c-26.5 0-48-21.5-48-48s21.5-48 48-48 48 21.5 48 48-21.5 48-48 48zm80-208H416V144h44.1l99.9 99.9V256z"></path></svg>
		click</a>
		</span>
	</td>
	<%-- <td class="py-5 align-middle text-muted font-weight-medium">
		<FORM METHOD = "post" ACTION = "<%=request.getContextPath()%>/back-end/ordermaster/ordermaster.do" style = "margin-bottom: 0px;">
			<input type = "submit" value = "Edit">
			<input type = "hidden" name = "ordermasterid" value = "${ordermasterVO.ordermasterid}">
			<input type = "hidden" name = "action" value = "getOne_For_Update">
		</FORM>
	</td> --%>
	<td class="py-5 align-middle text-muted font-weight-medium">
		<a href="<%=request.getContextPath()%>/back-end/ordermaster/ordermaster.do?ordermasterid=${ordermasterVO.ordermasterid}&action=getOne_For_Update">Edit</a>
	</td>
</tr>
</tbody>
</c:forEach>
</table>
</FORM>
</div>
<%@ include file = "page2.file" %>
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
<script>
 $(document).ready(function(){
  $("#CheckAll").click(function(){
   if($("#CheckAll").prop("checked")){//如果全選按鈕有被選擇的話（被選擇是true）
    $("input[name='ordermasterid']").each(function(){
     $(this).prop("checked",true);//把所有的核取方框的property都變成勾選
    })
   }else{
    $("input[name='ordermasterid']").each(function(){
     $(this).prop("checked",false);//把所有的核方框的property都取消勾選
    })
   }
  })
 })
</script> 
</body>
</html>