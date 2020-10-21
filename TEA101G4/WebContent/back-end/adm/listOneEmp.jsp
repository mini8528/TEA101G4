<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.adm.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
AdminnoVO admVO = (AdminnoVO) request.getAttribute("admVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
	width: 600px;
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
<!-- -----------------以下複製到虛線-----------------以下複製到虛線--------------------------------------- -->
<!-- ---------------------------------------------------------------- -->

</head>
<body bgcolor='white'>
<!-- ------------------以下複製到虛線-------------------以下複製到虛線--------------------------- -->

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


<table>
	<tr>
		<th>管理員編號</th>
		<th>管理員姓名</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>性別</th>
		<th>電話</th>
		<th>雇用日期</th>
		<th>信箱</th>
		<th>圖片</th>
		<th>地址</th>
	</tr>
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
	
	</tr>
</table>
<!-- -----------以下複製到虛線----------------------------以下複製到虛線------------------------- -->
			
			</div>
        </div>
        
        <!-- /.container-fluid -->

      </div>
   

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->
<!-- ---------------------------------------------------------------- -->

</body>
</html>