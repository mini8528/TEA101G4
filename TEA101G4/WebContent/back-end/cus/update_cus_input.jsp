<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cus.model.*"%>
<%@ page import="com.adm.model.*"%>
<%
CusVO cusVO = (CusVO) request.getAttribute("cusVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
AdminnoVO admVO = (AdminnoVO) request.getAttribute("admVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�޲z����ƭק� - update_emp_input.jsp</title>



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

<table id="table-1">
	<tr><td>
		 <h3>�ȪA��ƭק� - update_cus_input.jsp</h3>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/cus/cus.do" name="form1" enctype="multipart/form-data">
<table>
	 
	<tr>
		<td>�ҪA�s��:<font color=red></font></td>
		<td><%=cusVO.getCustomerserviceid()%></td>
	</tr>
	
	<tr>
		<td>�@��|���s��:</td>
		<td><%=cusVO.getMemberid()%></td>
	</tr>
	
	<tr>
		<td>�޲z���s��:</td>
		<td><input type="hidden" name="adminid" size="45" 
			 value=${adminVO.adminid}>${adminVO.adminid}</td>
	</tr>
	
	<tr>
		<td>�D��:</td>
		<td><%=cusVO.getSubject()%></td>
	</tr>
	
	<tr>
		<td>�q�l�H�c :</td>
		<td><%=cusVO.getEmail()%></td>
		<td><input type="hidden" name="email" size="45" 
			 value="<%=cusVO.getEmail()%>" /></td>
	</tr>
	
	<tr>
		<td>���D�y�z:</td>
		<td><%=cusVO.getProblemtext()%></td>
	</tr>

	<tr>
		<td>�ȶD���:</td>
		<td><%=cusVO.getComplaintdate()%></td>
		<td><input type="hidden" name="complaintdate" size="45"
			 value="<%=cusVO.getComplaintdate()%>"/>
		</td>
	</tr>
	<tr>
		<td>�^�Ф��e:</td>
		<td><input type="TEXT" name="replytext" size="45"
			 value="<%=cusVO.getReplytext()%>"/></td>
	</tr>
	<tr>
		<td>�^�Ф��:</td>
		<td><%=cusVO.getReplydate()%></td>
		<td><input type="hidden" name="replydate" size="45"
			 value="<%=cusVO.getReplydate()%>"></td>
	</tr>
	


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="customerserviceid" value="<%=cusVO.getCustomerserviceid()%>">
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