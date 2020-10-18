<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lat.model.*"%>
<%@page import="java.sql.Date"%>
<%
LatVO latVO = (LatVO) request.getAttribute("latVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�̷s�����ק� - update_lat_input.jsp</title>



</head>
<body bgcolor='white'>
<!-- ------------------�H�U�ƻs���u----include�Ҧb��m------------------------------------------ -->
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
		 <h3>�ҪA��ƭק� - update_lat_input.jsp</h3>
	</td></tr>
</table>


<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<FORM METHOD="post" ACTION="lat.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>�޲z���m�W:</td>
		<td>${latVO.adminid}</td>
		<td><input type="hidden" name="adminid" size="45" 
			 value="<%=latVO.getAdminid()%>" /></td>
	</tr>
	<tr>
		<td>�޲z���m�W2:</td>
		<td>${adminVO.adminid}</td>
		<td><input type="hidden" name="admin2id" size="45" 
			 value="<%=latVO.getAdmin2id()%>" /></td>
	</tr>
	
	<tr>
		<td>���e:</td>
		<td><input type="TEXT" name="text" size="45" 
			 value="<%=latVO.getText()%>" /></td>
	</tr>
	
	<tr>
		<td>�Ϥ�:</td>
		<td><input type="file" name="image" id="p_file"> <br> 
			<div id="preview">
						<span class="text"> �w�� </span>
						<img
						src="<%=request.getContextPath()%>/back-end/lat/latshow.do?latestnewsid=${latVo.latestnewsid}" 
						width="100" height="100">
		</div>
	</tr>
	
	<tr>
		<td>�s�W���:</td>
		<td><%=latVO.getAdddate()%></td>
		<td><input type="hidden" name="adddate" value="<%=latVO.getAdddate()%>" />
		</td>
	</tr>
	<!--<tr>
		<td>��s���:</td>
		<td><input name="updatetime" id="updatetime" type="text"></td>
	</tr>-->
	<tr>
		<td>�峹�W�Ǥ��:</td>
		<td>${latVO.uploaddate}</td>
		<td><input name="uploaddate" id="uploaddate" type="hidden"></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="latestnewsid" value="<%=latVO.getLatestnewsid()%>">
<input type="submit" value="�e�X�ק�">
<input type="file" name="upfile2"></FORM>


<!-- -----------�H�U�ƻs���u----------------------------�H�U�ƻs���u------------------------- -->
			
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



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

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
      console.log('change �ƥ�Ĳ�o');
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
        $('#adddate').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=latVO.getAdddate()%>', // value:   new Date(),
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

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
<% 
  Date updatetime = null;
  try {
	  updatetime = latVO.getUpdatetime();
   } catch (Exception e) {
	   updatetime = new Date(System.currentTimeMillis());
   }
%>
</html>