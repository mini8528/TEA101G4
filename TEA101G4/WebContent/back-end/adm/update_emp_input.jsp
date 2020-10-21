<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adm.model.*"%>

<%
AdminnoVO admVO = (AdminnoVO) request.getAttribute("admVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>管理員資料修改 - update_emp_input.jsp</title>

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
<!-- -----------------以下複製到虛線-----------------以下複製到虛線--------------------------------------- -->

<!-- ---------------------------------------------------------------- -->

</head>
<body bgcolor='white'>
<!-- ------------------以下複製到虛線-------------------以下複製到虛線--------------------------- -->

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
		<td>管理員編號:<font color=red><b>*</b></font></td>
		<td><%=admVO.getAdminid()%></td>
	</tr>
	<tr>
		<td>管理員姓名:</td>
		<td><input type="TEXT" name="membername" size="45" 
			 value="<%=admVO.getMembername()%>" /></td>
	</tr>
	
	<tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="memberuser" size="45" 
			 value="<%=admVO.getMemberuser()%>" /></td>
	</tr>
	
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="passwd" size="45" 
			 value="<%=admVO.getPasswd()%>" /></td>
	</tr>
	
	<tr>
		<td>性別:</td>
		<td><select name="gender" ${admVO.gender}>
						<option>男</option>
						<option>女</option>
				</select>
	</tr>
	
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="phone" size="45" 
			 value="<%=admVO.getPhone()%>" /></td>
	</tr>

	<tr>
		<td>雇用日期:</td>
		<td><input name="birthday" id="f_date1" type="text"
			value="<%=admVO.getBirthday()%>" /></td>
	</tr>
	<tr>
		<td>信箱:</td>
		<td><input type="TEXT" name="email" size="45"
			 value="<%=admVO.getEmail()%>" /></td>
	</tr>
	
	<tr>
		<td>圖片:</td>
		<td><input type="file" name="photo" id="p_file"> <br> 
			<div id="preview">
						<span class="text"> 預覽 </span>
						<img src="<%=request.getContextPath()%>/back-end/adm/admshow.do?adminid=${admVO.adminid}" width="100" height="100">
		</div>
	</tr>
	
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="address" size="45"
			 value="<%=admVO.getAddress()%>" /></td>
	</tr>



</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="adminid" value="<%=admVO.getAdminid()%>">
<input type="submit" value="送出修改">
</FORM>
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

<script>

window.addEventListener("load", function(){
    var dd = document.getElementById('p_file');
    dd.addEventListener("change",function(){
      console.log('change 事件觸發');
      for(let i = 0; i < this.files.length;i++){
        console.log(this.files[i]);
      }
    });
    var pf = document.getElementById("p_file");
    pf.addEventListener("change", function(e){
      if(this.files.length > 0 ){
        let reader = new FileReader();
        reader.readAsDataURL(this.files[0]);
        reader.addEventListener("load", function () {
          console.log(reader.result);
          let hh= "<img src='" + reader.result + "' class='preview_img' id='uu'>";
          document.getElementById("preview").innerHTML = hh;
        });
      }else{
        document.getElementById("preview").innerHTML = "";
      }
    });
});

        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=admVO.getBirthday()%>', // value:   new Date(),
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