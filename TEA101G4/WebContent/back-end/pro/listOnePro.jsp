<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.pro.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
ProVO proVO = (ProVO) request.getAttribute("proVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>


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
<!-- ---------------------����------------------------------------------- -->         
	

<table>
	<tr>
		<th>�ӫ~�����s��</th>
		<th>�ӫ~�s��</th>
		<th>�@��|���s��</th>
		<th>���פ��e</th>
		<th>�P�ż�</th>
		<th>�s�W���</th>
		<th>�ק���</th>
		<th>���ת��A</th>
	</tr>
	<tr>
		<td><%=proVO.getProdcommid()%></td>
		<td><%=proVO.getProductid()%></td>
		<td><%=proVO.getMemberid()%></td>
		<td><%=proVO.getCommtext()%></td>
		<td><%=proVO.getCommstar()%></td>
		<td><%=proVO.getAdddate()%></td>
		<td><%=proVO.getEditdate()%></td>
		<td><%=proVO.getStatus()%></td>
	
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