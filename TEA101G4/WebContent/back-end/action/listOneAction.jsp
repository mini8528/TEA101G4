<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.action.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  ActionVO actionVO = (ActionVO) request.getAttribute("actionVO"); 
%>

<html>
<head>
<title>後台管理-訓練動作資料 - listOneAction.jsp</title>

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

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>訓練動作資料 - ListOneAction.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/action/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>動作編號</th>
	
		<th>動作名稱</th>
		<th>運動部位</th>
		<th>影片</th>
		<th>上傳時間</th>
		<th>更新時間</th>
		
	</tr>
	<tr>
		<td><%=actionVO.getActionid()%></td>
		<td><%=actionVO.getActionnm()%></td>
		<td><%=actionVO.getPart()%></td>
		<td>
			<video id="addVideo" controls autoplay width="320" height="240">
               <source src="${actionVO.videoBase64Url}" />
            </video>

			</td>
		<td><%=actionVO.getPosttime()%></td>
		<td><%=actionVO.getUpdatetime()%></td>
	</tr>
</table>

</body>
</html>