<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.action.model.*"%>

<%
ActionVO actionVO = (ActionVO) request.getAttribute("actionVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>訓練動作資料修改</title>

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

<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>back-end訓練動作資料修改 -</h3> -->
<%-- 		 <h4><a href="<%=request.getContextPath()%>/back-end/action/select_page.jsp">回首頁</a></h4> --%>
<!-- 	</td></tr> -->
<!-- </table> -->

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/action/ActionServlet" name="form1" enctype="multipart/form-data">

<table>
	<tr>
		<td>動作名稱:</td>
		<td><input type="TEXT" name="actionnm" size="45"
			 value="<%= (actionVO==null)? "" : actionVO.getActionnm()%>" /></td>
	</tr>
	<tr>
		<td>運動部位:</td>
		<td><input type="TEXT" name="part" size="45"
			 value="<%= (actionVO==null)? "" : actionVO.getPart()%>" /></td>
	</tr>
	<td>影片:</td>
				<td><input type="file" name="video" size="45"
					value="<%= (actionVO==null)? "" : actionVO.getVideo()%>" /></td>
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
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="actionid" value="<%=actionVO.getActionid()%>">
<input type="submit" value="送出修改"></FORM>
</body>


</html>