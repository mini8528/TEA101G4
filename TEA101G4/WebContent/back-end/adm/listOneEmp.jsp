<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.adm.model.*"%>
<%@ page import="java.util.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
AdminnoVO admVO = (AdminnoVO) request.getAttribute("admVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���u��� - listOneEmp.jsp</title>

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
<!-- -----------------�H�U�ƻs���u-----------------�H�U�ƻs���u--------------------------------------- -->
<!-- ---------------------------------------------------------------- -->

</head>
<body bgcolor='white'>
<!-- ------------------�H�U�ƻs���u-------------------�H�U�ƻs���u--------------------------- -->

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

          <!-- Page Heading -->
          <h1 class="h3 mb-4 text-gray-800">Blank Page</h1>
    
<div>
<!-- ---------------------------------------------------------------- -->         

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - ListOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�޲z���s��</th>
		<th>�޲z���m�W</th>
		<th>�b��</th>
		<th>�K�X</th>
		<th>�ʧO</th>
		<th>�q��</th>
		<th>���Τ��</th>
		<th>�H�c</th>
		<th>�Ϥ�</th>
		<th>�a�}</th>
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
<!-- -----------�H�U�ƻs���u----------------------------�H�U�ƻs���u------------------------- -->
			
			</div>
        </div>
        
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->
<!-- ---------------------------------------------------------------- -->

</body>
</html>