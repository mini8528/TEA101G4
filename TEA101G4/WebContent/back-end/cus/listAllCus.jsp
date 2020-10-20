<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cus.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
CusService cusSvc = new CusService();
    List<CusVO> list = cusSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ��ȪA��� - listAllCus.jsp</title>

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



<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>�ȪA�s��</th>
		<th>�@��|���s��</th>
		<th>�޲z���s��</th>
		<th>�D��</th>
		<th>�q�l�H�c </th>
		<th>���D�y�z</th>
		<th>�ȶD���</th>
		<th>�^�Ф��e</th>
		<th>�^�Ф��</th>
		<th>�ާ@</th>
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
			     <input type="submit" value="�^��">
			     <input type="hidden" name="customerserviceid"  value="${cusVO.customerserviceid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cus/cus.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�R��"> -->
<%-- 			     <input type="hidden" name="customerserviceid"  value="${cusVO.customerserviceid}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
			     
			     
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
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