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
<title>動作資料新增 - addAction.jsp</title>

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

          <!-- Page Heading -->
          <h1 class="h3 mb-4 text-gray-800">新增健身訓練影片</h1>
    
<div>
<!-- ---------------------------------------------------------------- -->   

<!-- 	<table id="table-1"> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<h3>動作資料新增 - addAction.jsp</h3> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 				<h4> -->
<%-- 					<a href="<%=request.getContextPath()%>/back-end/action/select_page.jsp"><img --%>
<!-- 						src="images/001.GIF" width="100" height="100" border="0">回首頁</a> -->
<!-- 				</h4> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/action/ActionServlet" name="form1" enctype="multipart/form-data">
		<table>
			
			<tr>
				<td>動作名稱:</td>
				<td><input name="actionnm" id="f_date1" type="text"></td>
			</tr>
			<tr>
				<td>部位:</td>
				<td><input type="TEXT" name="part" size="45"
					value="<%= (actionVO==null)? "" : actionVO.getPart()%>" /></td>
			</tr>
			<tr>
				<td>影片:</td>
				<td><input type="file" name="video" size="45"
					value="<%= (actionVO==null)? "" : actionVO.getVideo()%>" /></td>
			</tr>
			<tr>
				<td>上傳日期:</td>
				<td><input type="TEXT" name="posttime" size="45"
					value="<%= (actionVO==null)? "" : actionVO.getPosttime()%>" /></td>
			</tr>
			<tr>
				<td>更新日期:</td>
				<td><input type="TEXT" name="updatetime" size="45"
					value="<%= (actionVO==null)? "" : actionVO.getUpdatetime()%>" /></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
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