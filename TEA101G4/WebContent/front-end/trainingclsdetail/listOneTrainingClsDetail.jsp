<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.trainingclsdetail.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  TrainingClsDetailVO tcdVO = (TrainingClsDetailVO) request.getAttribute("tcdVO");
%>

<html>
<head>
<title>�V�m�ҵ{���e��� - listOnetcd.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�V�m�ҵ{���e��� - ListOnetcd.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�V�m�ҵ{���e�s��</th>
		<th>�V�m�ҵ{�s��</th>
		<th>�V�m�ʧ@�s��</th>
		<th>�V�m�ʧ@����</th>
	</tr>
	<tr>
		<td><%=tcdVO.getTrainingclsdetailid()%></td>
		<td><%=tcdVO.getTrainingclsid()%></td>
		<td><%=tcdVO.getActionid()%></td>
	
		
	</tr>
</table>

    <jsp:include page="/front-end/footer.jsp" flush="true" />

</body>
</html>