<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.action.model.*"%>

<%
System.out.println("4");
  ActionVO actionVO = (ActionVO) request.getAttribute("actionVO");
%>

<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�ʧ@��Ʒs�W - addAction.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
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

          <!-- Page Heading -->
          <h1 class="h3 mb-4 text-gray-800">�s�W�����V�m�v��</h1>
    
<div>
<!-- ---------------------------------------------------------------- -->   

<!-- 	<table id="table-1"> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<h3>�ʧ@��Ʒs�W - addAction.jsp</h3> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<h4> -->
<%-- 					<a href="<%=request.getContextPath()%>/back-end/action/select_page.jsp"><img --%>
<!-- 						src="images/001.GIF" width="100" height="100" border="0">�^����</a> -->
<!-- 				</h4> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

	<h3>��Ʒs�W:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/action/ActionServlet" name="form1" enctype="multipart/form-data">
		<table>
			
			<tr>
				<td>�ʧ@�W��:</td>
				<td><input name="actionnm" id="f_date1" type="text"></td>
			</tr>
			<tr>
				<td>����:</td>
				<td><input type="TEXT" name="part" size="45"
					value="<%= (actionVO==null)? "" : actionVO.getPart()%>" /></td>
			</tr>
			<tr>
				<td>�v��:</td>
				<td><input type="file" name="video" size="45"
					value="<%= (actionVO==null)? "" : actionVO.getVideo()%>" /></td>
			</tr>
			<tr>
				<td>�W�Ǥ��:</td>
				<td><input type="TEXT" name="posttime" size="45"
					value="<%= (actionVO==null)? "" : actionVO.getPosttime()%>" /></td>
			</tr>
			<tr>
				<td>��s���:</td>
				<td><input type="TEXT" name="updatetime" size="45"
					value="<%= (actionVO==null)? "" : actionVO.getUpdatetime()%>" /></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="�e�X�s�W">
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


</html>