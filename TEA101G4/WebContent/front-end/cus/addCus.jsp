<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cus.model.*"%>
<%@ page import="com.member.model.*"%>

<%
CusVO cusVO = (CusVO) request.getAttribute("cusVO");
MemberVO userVO = (MemberVO) request.getAttribute("userVO");
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

</head>
<body bgcolor='white'>
<jsp:include page="/front-end/header.jsp" flush="true" />
<!-- ------------------以下複製到虛線----include所在位置------------------------------------------ -->
  <!-- Page Wrapper -->
<!--   <div id="wrapper"> -->
<%-- 	<%@ include file="/back-end/component/sidebar.jsp" %> --%>


<!--     Content Wrapper -->
<!--     <div id="content-wrapper" class="d-flex flex-column"> -->

<!--       Main Content -->
      
<!--       <div id="content"> -->
		

<%--        <%@ include file="/back-end/component/topbar.jsp" %> --%>

<!--         Begin Page Content -->
<!--         <div class="container-fluid"> -->

<!--           Page Heading -->
<!--           <h1 class="h3 mb-4 text-gray-800">Blank Page</h1> -->
    
<div>
<!-- ---------------------------------------------------------------- -->         




<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/cus/cus.do" name="form1" enctype="multipart/form-data">
<!-- ====================================———	ABOUT MEDIA===================================== -->


<section class="py-8 py-md-10">
  <div class="container">
<!--     <div class="mb-10" id="map" style="width:100%; height:420px;"></div> -->

    <div class="" action="index.jsp" method="post">
      <form>
        <div class="row">
<!--           <div class="col-sm-6 col-xs-12"> -->
<!--             <div class="form-group form-group-icon"> -->
<!--               <i class="fa fa-user"></i> -->
              <input type="hidden" name="memberid" class="form-control border-warning" 
              placeholder="" required="" value = ${userVO.memberid} />
<!--             </div> -->
<!--           </div> -->

          <div class="col-sm-6 col-xs-12">
            <div class="form-group form-group-icon">
              <i class="fa fa-envelope"></i>
              <input type="email"name="email" class="form-control border-success"
              placeholder="電子信箱" required="">
            </div>
          </div>

          <div class="col-sm-6 col-xs-12">
            <div class="form-group form-group-icon">
              <i class="fa fa-book"></i>
              <input type="text"name="subject" class="form-control border-pink" 
               placeholder="主旨" required="">
            </div>
          </div>

          <div class="col-12">
            <div class="form-group form-group-icon">
              <i class="fa fa-comments"></i>
              <textarea name="problemtext"class="form-control border-info" 
 					 placeholder="客服內容" rows="6"></textarea>
            </div>
          </div>

          <div class="col-12">
            <div class="text-sm-center mt-6">
            	<input type="hidden" name="action" value="insert">
              <input type="submit" class="btn btn-danger text-uppercase"value="送出">
              
            </div>
          </div>
        </div>
        
      </form>
    </div>
  </div>
</section>

<!-- ==================================== ———	FOOTER ===================================== -->


<!-- -----------以下複製到虛線----------------------------以下複製到虛線------------------------- -->
			
			</div>
       <!--   </div>-->
        
        <!-- /.container-fluid -->

    <!--  </div>-->
      <!-- End of Main Content -->

      
      <!-- End of Footer -->

  <!--  </div>-->
    <!-- End of Content Wrapper -->

  <!--</div>-->
  <!-- End of Page Wrapper -->

 
<!-- ---------------------------------------------------------------- -->
<jsp:include page="/front-end/footer.jsp" flush="true" />

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date hiredate = null;
  try {
	    hiredate = cusVO.getComplaintdate();
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
      
</script>
</html>