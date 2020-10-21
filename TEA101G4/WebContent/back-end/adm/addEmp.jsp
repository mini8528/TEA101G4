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
<title>���u��Ʒs�W - addEmp.jsp</title>

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

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<FORM METHOD="post" ACTION="adm.do" name="form1" enctype="multipart/form-data">

<table>
	<tr>
		<td>�޲z���m�W:</td>
		<td><input type="TEXT" name="membername" size="45" 
			 value="<%= (admVO==null)? "" : admVO.getMembername()%>" /></td>
	</tr>
	
	<tr>
		<td>�b��:</td>
		<td><input type="TEXT" name="memberuser" size="45" 
			 value="<%= (admVO==null)? "" : admVO.getMemberuser()%>" /></td>
	</tr>
	
	<tr>
		<td>�K�X:</td>
		<td><input type="TEXT" name="passwd" size="45" 
			 value="<%= (admVO==null)? "" : admVO.getPasswd()%>" /></td>
	</tr>
	
	<tr>
		<td>�ʧO:</td>
		<td><select name="gender"  ${admVO.gender}> <!-- �o�˼g��ܡH -->
						<option>�k</option>
						<option>�k</option>
				</select>
	</tr>
	
	<tr>
		<td>�q��:</td>
		<td><input type="TEXT" name="phone" size="45" 
			 value="<%= (admVO==null)? "" : admVO.getPhone()%>" /></td>
	</tr>

	<tr>
		<td>���Τ��:</td>
		<td><input name="birthday" id="birthday" type="text"></td>
	</tr>
	<tr>
		<td>�H�c:</td>
		<td><input type="TEXT" name="email" size="45"
			 value="<%= (admVO==null)? "andyblack1126@gmail.com" : admVO.getEmail()%>" /></td>
	</tr>
	<tr>
		<td>�a�}:</td>
		<td><input type="TEXT" name="address" size="45"
			 value="<%= (admVO==null)? "" : admVO.getAddress()%>" /></td>
	</tr>
	<tr>
		<td>�Ӥ�:</td>
		<td><input type="file" name="photo" class="photo"/></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
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



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

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
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=hiredate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
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