<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pro.model.*"%>

<%
ProVO proVO = (ProVO) request.getAttribute("proVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�ק���ת��A</title>

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

<FORM METHOD="post" ACTION="pro.do" name="form1" enctype="multipart/form-data">
<table>
	 
	<tr>
		<td>�ӫ~�����s��:<font color=red><b>*</b></font></td>
		<td><%=proVO.getProdcommid()%></td>
	</tr>
	
	<tr>
		<td>�ӫ~�s��:</td>
<!-- 		<td><input type="hidden" name="productid" size="45"  -->
<%-- 			 value="<%=proVO.getProductid()%>" /></td> --%>
			 <td><%=proVO.getProductid()%></td>
	</tr>
	
	<tr>
		<td>�@��|���s��:</td>
<!-- 		<td><input type="TEXT" name="memberid" size="45"  -->
<%-- 			 value="<%=proVO.getMemberid()%>" /></td> --%>
			 <td><%=proVO.getMemberid()%></td>
	</tr>
	
	<tr>
		<td>���פ��e:</td>
<!-- 		<td><input type="TEXT" name="commtext" size="45"  -->
<%-- 			 value="<%=proVO.getCommtext()%>" /></td> --%>
			 <td><%=proVO.getCommtext()%></td>
	</tr>
	
	<tr>
		<td>�P�ż�:</td>
<!-- 		<td><input type="TEXT" name="commstar" size="45"  -->
<%-- 			 value="<%=proVO.getCommstar()%>" /></td> --%>
			 <td><%=proVO.getCommstar()%></td>
	</tr>
	
	<tr>
		<td>�s�W���:</td>
		<td><%=proVO.getAdddate()%></td>
	</tr>
	
	<tr>
		<td>�ק���:</td>
		<td><%=proVO.getEditdate()%></td>
	</tr>
	
	<tr>
		<td>���ת��A:</td>
		<td><select name="status" ${proVO.status}>
						<option>Y</option>
						<option>N</option>
				</select>
	</tr>

	
	


</table>
<br>
<input type="hidden" name="action" value="updatetext">
<input type="hidden" name="prodcommid" value="<%=proVO.getProdcommid()%>">
<input type="submit" value="�e�X�ק�">
</FORM>


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



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

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