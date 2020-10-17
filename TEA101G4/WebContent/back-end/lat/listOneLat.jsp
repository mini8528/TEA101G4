<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.lat.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
LatVO latVO = (LatVO) request.getAttribute("latVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
          <h1 class="h3 mb-4 text-gray-800">Blank Page</h1>
    
<div>
<!-- ---------------------------------------------------------------- -->         
<table id="table-1">
	<tr><td>
		 <h3>最新消息資料 - ListOneLat.jsp</h3>
	</td></tr>
</table>

<table>
	<tr>
		<th>活動消息編號</th>
		<th>管理員姓名</th>
		<th>管理員編號(更新)</th>
		<th>內容</th>
		<th>圖片1</th>
		<th>新增日期</th>
		<th>更新日期</th>
		<th>文章上傳日期</th>
	</tr>
	<tr>
			<td><%=latVO.getLatestnewsid()%></td>
			<td><%=latVO.getAdminid()%></td>
			<td><%=latVO.getAdmin2id()%></td>
			<td><%=latVO.getText()%></td>
			<td>
			<img  src="<%=request.getContextPath()%>/lat/latshow.do?latestnewsid=${latVO.latestnewsid}"  width="100" height="100">
			</td>
			<td><%=latVO.getAdddate()%></td>
			<td><%=latVO.getUpdatetime()%></td>
			<td><%=latVO.getUploaddate()%></td>
	
	</tr>
</table>
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