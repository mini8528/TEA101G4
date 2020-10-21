<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adm.model.*"%>

<%
AdminnoVO admVO = (AdminnoVO) request.getAttribute("admVO");
System.out.print(admVO);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料新增 - addEmp.jsp</title>

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
<!-- ----------------------ssssss-------------------------------------------------- -->
  
<!-- ---------------------ssssss---------------------------------------------------- -->

</head>
<body bgcolor='white'>
<!-- ---------------------------------------------------------------- -->
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

          
<!-- ---------------------------------------------------------------- -->
<div>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<FORM METHOD="post" ACTION="adm.do" name="form1" enctype="multipart/form-data">

<table>
	<tr>
		<td>管理員姓名:</td>
		<td><input type="TEXT" name="membername" size="45" 
			 value="<%= (admVO==null)? "" : admVO.getMembername()%>" /></td>
	</tr>
	
	<tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="memberuser" size="45" 
			 value="<%= (admVO==null)? "" : admVO.getMemberuser()%>" /></td>
	</tr>
	
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="passwd" size="45" 
			 value="<%= (admVO==null)? "" : admVO.getPasswd()%>" /></td>
	</tr>
	
	<tr>
		<td>性別:</td>
		<td><select name="gender"  ${admVO.gender}> <!-- 這樣寫對嗎？ -->
						<option>男</option>
						<option>女</option>
				</select>
	</tr>
	
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="phone" size="45" 
			 value="<%= (admVO==null)? "" : admVO.getPhone()%>" /></td>
	</tr>

	<tr>
		<td>雇用日期:</td>
		<td><input name="birthday" id="birthday" type="text"></td>
	</tr>
	<tr>
		<td>信箱:</td>
		<td><input type="TEXT" name="email" size="45"
			 value="<%= (admVO==null)? "andyblack1126@gmail.com" : admVO.getEmail()%>" /></td>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="address" size="45"
			 value="<%= (admVO==null)? "" : admVO.getAddress()%>" /></td>
	</tr>
	<tr>
		<td>照片:</td>
		<td><input type="file" name="photo" class="photo"/></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</div>
<!-- ---------------------------------------------------------------- -->
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


</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date hiredate = null;
  try {
	    hiredate = admVO.getBirthday();
   } catch (Exception e) {
	    hiredate = new java.sql.Date(System.currentTimeMillis());
   }
%>
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
        $.datetimepicker.setLocale('zh');
        $('#birthday').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=hiredate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
       


   
        
</script>
</html>