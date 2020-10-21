<%@page import="java.sql.Date"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lat.model.*"%>

<%
LatVO latVO = (LatVO) request.getAttribute("latVO");
System.out.print(latVO);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料新增 - addLat.jsp</title>


<!-- ----------以下複製到虛線--------------------以下複製到虛線------------------------------------------- -->
 <!-- ---------------------------------------------------------------- -->

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
		
<table id="table-1">
	<tr><td>
		 <h3>消息資料新增 - addLat.jsp</h3></td><td>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<FORM METHOD="post" ACTION="lat.do" name="form1" enctype="multipart/form-data">

<table>
 <jsp:useBean id="latSvc" scope="page" class="com.lat.model.LatService" />
 <!--  
	<tr>
		<td>管理員姓名:<font color=red><b>*</b></font></td>
		<td><select size="1" name="adminid">
						<c:forEach var="admVO" items="${empSvc.all}">
							<option value="${admVO.adminid}" ${(latVO.adminid==admVO.adminid)? 'selected':'' }>${latVO.membername}
						</c:forEach>
				</select></td>
	</tr>
	
	<tr>
		<td>管理員姓名2:<font color=red><b>*</b></font></td>
		<td><select size="1" name="admin2id">
						<c:forEach var="admVO" items="${empSvc.all}">
							<option value="${admVO.adminid}" ${(latVO.admin2id==admVO.adminid)? 'selected':'' }>${latVO.membername}
						</c:forEach>
				</select></td>
	</tr>
	-->
	
	<tr>
		<td>管理員編號:</td>
		<td><input type="hidden" name="adminid" size="45" 
			 value=${adminVO.adminid} />${adminVO.adminid}</td>
	</tr>
<!--  	<tr>
		<td>管理員編號2:</td>
		<td><input type="TEXT" name="admin2id" size="45" 
			 value="<%= (latVO==null)? "吳永志" : latVO.getAdmin2id()%>" /></td>
	</tr>-->
	
	
	
	
	<tr>
		<td>內容:</td>
		<td><input type="TEXT" name="text" size="45" 
			 value="<%= (latVO==null)? "xxxxxxxx" : latVO.getText()%>" /></td>
			 <textarea style="width:400px;height:500px;"></textarea>
	</tr>
	
	<tr>
		<td>圖片1:</td>
		<td><input type="file" name="image" class="photo"/></td>
	</tr>
	<!--  <tr>
		<td>新增日期:</td>
		<td><input name="adddate" id="adddate" type="text"></td>
	</tr>-->
	<!--  <tr>
		<td>更新日期:</td>
		<td><input name="updatetime" id="updatetime" type="text"></td>
	</tr>-->
	<tr>
<!-- 		<td>文章上傳日期:</td> -->
		<td><input name="uploaddate" id="uploaddate" type="hidden"></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
<!-- -----------以下複製到虛線----------------------------以下複製到虛線------------------------- -->
			
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



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


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




</html>