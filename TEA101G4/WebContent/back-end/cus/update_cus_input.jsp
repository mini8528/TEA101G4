<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cus.model.*"%>
<%@ page import="com.adm.model.*"%>
<%
CusVO cusVO = (CusVO) request.getAttribute("cusVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
AdminnoVO admVO = (AdminnoVO) request.getAttribute("admVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>管理員資料修改 - update_emp_input.jsp</title>



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
		 <h3>客服資料修改 - update_cus_input.jsp</h3>
	</td></tr>
</table>

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
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/cus/cus.do" name="form1" enctype="multipart/form-data">
<table>
	 
	<tr>
		<td>課服編號:<font color=red></font></td>
		<td><%=cusVO.getCustomerserviceid()%></td>
	</tr>
	
	<tr>
		<td>一般會員編號:</td>
		<td><%=cusVO.getMemberid()%></td>
	</tr>
	
	<tr>
		<td>管理員編號:</td>
		<td><input type="hidden" name="adminid" size="45" 
			 value=${adminVO.adminid}>${adminVO.adminid}</td>
	</tr>
	
	<tr>
		<td>主旨:</td>
		<td><%=cusVO.getSubject()%></td>
	</tr>
	
	<tr>
		<td>電子信箱 :</td>
		<td><%=cusVO.getEmail()%></td>
		<td><input type="hidden" name="email" size="45" 
			 value="<%=cusVO.getEmail()%>" /></td>
	</tr>
	
	<tr>
		<td>問題描述:</td>
		<td><%=cusVO.getProblemtext()%></td>
	</tr>

	<tr>
		<td>客訴日期:</td>
		<td><%=cusVO.getComplaintdate()%></td>
		<td><input type="hidden" name="complaintdate" size="45"
			 value="<%=cusVO.getComplaintdate()%>"/>
		</td>
	</tr>
	<tr>
		<td>回覆內容:</td>
		<td><input type="TEXT" name="replytext" size="45"
			 value="<%=cusVO.getReplytext()%>"/></td>
	</tr>
	<tr>
		<td>回覆日期:</td>
		<td><%=cusVO.getReplydate()%></td>
		<td><input type="hidden" name="replydate" size="45"
			 value="<%=cusVO.getReplydate()%>"></td>
	</tr>
	


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="customerserviceid" value="<%=cusVO.getCustomerserviceid()%>">
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