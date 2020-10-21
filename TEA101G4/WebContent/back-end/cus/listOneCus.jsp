<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.cus.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
CusVO cusVO = (CusVO) request.getAttribute("cusVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>客服資料資料 - listOneCus.jsp</title>

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


<table>
	<tr>
		<th>客服編號</th>
<!-- 		<th>一般會員編號</th> -->
		<th>管理員編號</th>
<!-- 		<th>主旨</th> -->
<!-- 		<th>電子信箱 </th> -->
<!-- 		<th>問題描述</th> -->
<!-- 		<th>客訴日期</th> -->
		<th>回覆內容</th>
		<th>回覆日期</th>
	</tr>
	<tr>
		<td><%=cusVO.getCustomerserviceid()%></td>
<%-- 		<td><%=cusVO.getMemberid()%></td> --%>
		<td>${adminVO.adminid}</td>
<%-- 		<td><%=cusVO.getSubject()%></td> --%>
<%-- 		<td><%=cusVO.getEmail()%></td> --%>
<%-- 		<td><%=cusVO.getProblemtext()%></td> --%>
<%-- 		<td><%=cusVO.getComplaintdate()%></td> --%>
		<td><%=cusVO.getReplytext()%></td>
		<td><%=cusVO.getReplydate()%></td>
	</tr>
</table>
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