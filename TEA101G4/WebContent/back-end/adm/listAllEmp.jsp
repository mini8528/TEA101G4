<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.*"%>
<%@ page import="com.adm.model.*"%>
<%
AdminnoService admSvc = new AdminnoService();
    List<AdminnoVO> list = admSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html lang="en">

<head>


</head>
<body id="page-top">
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
<!-- ---------------------測試------------------------------------------- -->         
			
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>所有員工資料 - listAllEmp.jsp</h3> -->
		
<!-- 	</td></tr> -->
<!-- </table> -->

<%-- <%-- 錯誤表列 --%> 
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<table class="table table-bordered text-center">
	<tr>
		<th>管理員編號</th>
		<th>管理員姓名</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>性別</th>
		<th>電話</th>
		<th>雇用日期</th>
		<th>信箱</th>
		<th>員工照片</th>
		<th>地址</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	
	  <%@ include file="page1.file" %> 
	<c:forEach var="admVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
	<tr>
			
			<td>${admVO.adminid}</td>
			<td>${admVO.membername}</td>
			<td>${admVO.memberuser}</td>
			<td>${admVO.passwd}</td>
			<td>${admVO.gender}</td>
			<td>${admVO.phone}</td>
			<td>${admVO.birthday}</td>
			<td>${admVO.email}</td>
			<td>
			<img  src="<%=request.getContextPath()%>/back-end/adm/admshow.do?adminid=${admVO.adminid}"  width="100" height="100">
			</td>
			<td>${admVO.address}</td>
		
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/adm/adm.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="adminid"  value="${admVO.adminid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/adm/adm.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="adminid"  value="${admVO.adminid}">
			     <input type="hidden" name="action" value="delete"></FORM>
			     
			     
			</td>
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