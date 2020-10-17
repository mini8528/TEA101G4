<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pro.model.*"%>

<%
ProVO proVO = (ProVO) request.getAttribute("proVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>修改評論狀態</title>

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

<FORM METHOD="post" ACTION="pro.do" name="form1" enctype="multipart/form-data">
<table>
	 
	<tr>
		<td>商品評價編號:<font color=red><b>*</b></font></td>
		<td><%=proVO.getProdcommid()%></td>
	</tr>
	
	<tr>
		<td>商品編號:</td>
<!-- 		<td><input type="hidden" name="productid" size="45"  -->
<%-- 			 value="<%=proVO.getProductid()%>" /></td> --%>
			 <td><%=proVO.getProductid()%></td>
	</tr>
	
	<tr>
		<td>一般會員編號:</td>
<!-- 		<td><input type="TEXT" name="memberid" size="45"  -->
<%-- 			 value="<%=proVO.getMemberid()%>" /></td> --%>
			 <td><%=proVO.getMemberid()%></td>
	</tr>
	
	<tr>
		<td>評論內容:</td>
<!-- 		<td><input type="TEXT" name="commtext" size="45"  -->
<%-- 			 value="<%=proVO.getCommtext()%>" /></td> --%>
			 <td><%=proVO.getCommtext()%></td>
	</tr>
	
	<tr>
		<td>星級數:</td>
<!-- 		<td><input type="TEXT" name="commstar" size="45"  -->
<%-- 			 value="<%=proVO.getCommstar()%>" /></td> --%>
			 <td><%=proVO.getCommstar()%></td>
	</tr>
	
	<tr>
		<td>新增日期:</td>
		<td><%=proVO.getAdddate()%></td>
	</tr>
	
	<tr>
		<td>修改日期:</td>
		<td><%=proVO.getEditdate()%></td>
	</tr>
	
	<tr>
		<td>評論狀態:</td>
		<td><select name="status" ${proVO.status}>
						<option>Y</option>
						<option>N</option>
				</select>
	</tr>

	
	


</table>
<br>
<input type="hidden" name="action" value="updatetext">
<input type="hidden" name="prodcommid" value="<%=proVO.getProdcommid()%>">
<input type="submit" value="送出修改">
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

<script>
   

      
        
</script>
</html>