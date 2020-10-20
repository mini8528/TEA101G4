<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cus.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
CusService cusSvc = new CusService();
    List<CusVO> list = cusSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有客服資料 - listAllCus.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>
<!-- ------------------以下複製到虛線----include所在位置------------------------------------------ -->
  <!-- Page Wrapper -->
  <div id="wrapper">
	<%@ include file="/back-end/component/sidebar.jsp" %>


    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      
      <div id="content">
		

       <%@ include file="/back-end/component/topbar.jsp" %>

        <!-- Begin Page Content -->
        <div class="container-fluid">

 
    
<div>
<!-- ---------------------------------------------------------------- -->         



<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>客服編號</th>
		<th>一般會員編號</th>
		<th>管理員編號</th>
		<th>主旨</th>
		<th>電子信箱 </th>
		<th>問題描述</th>
		<th>客訴日期</th>
		<th>回覆內容</th>
		<th>回覆日期</th>
		<th>操作</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="cusVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
	<tr>
			
					
			<td>${cusVO.customerserviceid}</td>
			<td>${cusVO.memberid}</td>
			<td>${cusVO.adminid}</td>
			<td>${cusVO.subject}</td>
			<td>${cusVO.email}</td>
			<td>${cusVO.problemtext}</td>
			<td>${cusVO.complaintdate}</td>
			<td>${cusVO.replytext}</td>
			<td>${cusVO.replydate}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/cus/cus.do" style="margin-bottom: 0px;">
			     <input type="submit" value="回覆">
			     <input type="hidden" name="customerserviceid"  value="${cusVO.customerserviceid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cus/cus.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="customerserviceid"  value="${cusVO.customerserviceid}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
			     
			     
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<!-- -----------以下複製到虛線----------------------------以下複製到虛線------------------------- -->
			
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
</html>