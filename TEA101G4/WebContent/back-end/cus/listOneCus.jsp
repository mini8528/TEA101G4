<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.cus.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
CusVO cusVO = (CusVO) request.getAttribute("cusVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>�ȪA��Ƹ�� - listOneCus.jsp</title>

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
<!-- ------------------�H�U�ƻs���u----include�Ҧb��m------------------------------------------ -->
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
		<th>�ȪA�s��</th>
<!-- 		<th>�@��|���s��</th> -->
		<th>�޲z���s��</th>
<!-- 		<th>�D��</th> -->
<!-- 		<th>�q�l�H�c </th> -->
<!-- 		<th>���D�y�z</th> -->
<!-- 		<th>�ȶD���</th> -->
		<th>�^�Ф��e</th>
		<th>�^�Ф��</th>
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
<!-- -----------�H�U�ƻs���u----------------------------�H�U�ƻs���u------------------------- -->
			
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