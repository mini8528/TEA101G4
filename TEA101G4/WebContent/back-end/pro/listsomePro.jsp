<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>
<%

String str = request.getParameter("productid");

ProService proSvc = new ProService();
   List<ProVO> list = proSvc.getAllStatusYById(str);
   pageContext.setAttribute("list",list);
   
%>


<html>
<head>
<title>����</title>



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
	<%@ include file="page1.file" %> 
	<c:forEach var="proVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
	<tr>
			
					
			<td>${proVO.prodcommid}</td>
			<td>${proVO.productid}</td>
			<td>${proVO.memberid}</td>
			<td>${proVO.commtext}</td>
			<td>${proVO.commstar}</td>
			<td>${proVO.adddate}</td>
			<td>${proVO.editdate}</td>
			<td>${proVO.status}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/pro/pro.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="prodcommid"  value="${proVO.prodcommid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
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