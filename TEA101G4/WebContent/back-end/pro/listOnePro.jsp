<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.pro.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
ProVO proVO = (ProVO) request.getAttribute("proVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>


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
<!-- ---------------------測試------------------------------------------- -->         
	

<table>
	<tr>
		<th>商品評價編號</th>
		<th>商品編號</th>
		<th>一般會員編號</th>
		<th>評論內容</th>
		<th>星級數</th>
		<th>新增日期</th>
		<th>修改日期</th>
		<th>評論狀態</th>
	</tr>
	<tr>
		<td><%=proVO.getProdcommid()%></td>
		<td><%=proVO.getProductid()%></td>
		<td><%=proVO.getMemberid()%></td>
		<td><%=proVO.getCommtext()%></td>
		<td><%=proVO.getCommstar()%></td>
		<td><%=proVO.getAdddate()%></td>
		<td><%=proVO.getEditdate()%></td>
		<td><%=proVO.getStatus()%></td>
	
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