<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.trainingsche.model.*"%>


<%
System.out.println("xxxxx");
TrainingScheVO tsVO = (TrainingScheVO) request.getAttribute("tsVO");
%>

<html>
<head>
<title>訓練行程資料 - listOnets.jsp</title>

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
 <jsp:include page="/front-end/header.jsp" flush="true" />

<h4>TrainingSche查單筆:</h4>
	<table id="table-1">
		<tr><td>
				<h3>運動行程資料 - ListOneTrainingSche.jsp</h3>
				<h4>
					<a href="select_page.jsp">
					<img src="images/back1.gif" width="100" height="32" border="0">返回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<th>訓練行程編號</th>
			<th>訓練課程編號</th>
			<th>會員編號</th>
			<th>訓練開始時間</th>
			<th>訓練結束時間</th>
		</tr>
		<tr>
		    <td><%=tsVO.getTrainingscheid()%></td>
		    <td><%=tsVO.getTrainingclsid()%></td>
		    <td><%=tsVO.getMemberid()%></td>
		    <td><%=tsVO.getStarttime()%></td>
		    <td><%=tsVO.getEndtime()%></td>
		</tr>
	</table>
    <jsp:include page="/front-end/footer.jsp" flush="true" />

</body>
</html>