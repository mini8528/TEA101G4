<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料修改 - update_member_input.jsp</title>

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

img{
	max-width: 200px;
}
</style>
<!-- ----------以下複製到虛線--------------------以下複製到虛線------------------------------------------- -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SB Admin 2 - Blank</title>

  <!-- Custom fonts for this template-->
  <link href="<%=request.getContextPath()%>/back-assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="<%=request.getContextPath()%>/back-assets/css/sb-admin-2.min.css" rel="stylesheet">
<!-- ---------------------------------------------------------------- -->
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
          <h1 class="h3 mb-4 text-gray-800">會員資料修改</h1>
    
<div>
<!-- ---------------------------------------------------------------- -->         

<!-- 	<table id="table-1"> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<h3>memberID修改 - update_member_input.jsp</h3> -->
<!-- 				<h4> -->
<!-- 					<a href="select_page.jsp"><img src="images/back1.gif" -->
<!-- 						width="100" height="32" border="0">回首頁</a> -->
<!-- 				</h4> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

<!-- 	<h3>資料修改:</h3> -->

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">會員資料</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/MemberServlet" name="form1"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>memberid:<font color=red><b>*</b></font></td>
				<td><%=memberVO.getMemberid()%></td>
			</tr>
			
			
			<tr>
				<td>新增日：<font color=blue><b>*</b></font></td>
				<td><%=memberVO.getAdddate()%> <input type="hidden"
					name="adddate" value="<%=memberVO.getAdddate()%>" /></td>
			</tr>
			
			<tr>
				<td>姓名:</td>
				<td><input type="TEXT" name="name" size="45" value="<%=memberVO.getName()%>" readonly/></td>
			</tr>
			
			<tr>
				<td>帳號:</td>
				<td><input type="TEXT" name="account" size="45" value="<%=memberVO.getAccount()%>" readonly/></td>
			</tr>

			<tr>
				<td>密碼:</td>
				<td><input type="password" name="password" size="45" value="<%=memberVO.getPassword()%>" readonly/></td>
			</tr>


<!-- 			<tr> -->
<!-- 				<td>性別：</td> -->
<!-- 				<td><select name="gender" > -->
<%-- 						<option value="F" ${(memberVO.gender=="F")? 'selected':'' } >女</option> --%>
<%-- 						<option value="M" ${(memberVO.gender=="M")? 'selected':'' } >男</option> --%>
<!-- 				</select></td> -->
<!-- 			</tr> -->

			<tr>
				<td>電話:</td>
				<td><input type="TEXT" name="phone" size="45" value="<%=memberVO.getPhone()%>" readonly/></td>
			</tr>
			
			<tr>
				<td>生日:</td>
				<td><input type="TEXT" name="birthday" size="45" value="<%=memberVO.getBirthday()%>" readonly/></td>
			</tr>

			
			<tr>
				<td>email:</td>
				<td><input type="email" name="email" size="45" value="<%=memberVO.getEmail()%>" readonly/></td>
			</tr>


			<tr>
<!-- 				<td>照片:</td> -->
				<td><input type="hidden" name="photo" id="p_file">
<!-- 					<div id="preview"> -->
<!-- 						<span class="text"> 預覽 </span> -->
<!-- 					</div> -->
			</tr>
			
			<tr>
				<td>地址:</td>
				<td><input type="TEXT" name="address" size="45" value="<%=memberVO.getAddress()%>" readonly/></td>
			</tr>

			<tr>
				<td>權限：</td>
				<td><select name="authority">
						<option value="N" ${(memberVO.gender=="N")? 'selected':'' }>N</option>
						<option value="Y" ${(memberVO.gender=="Y")? 'selected':'' }>Y</option>
				</select></td>
			</tr>
			
			<tr>
				<td>資歷:</td>
				<td><input type="TEXT" name="qualifications" size="45" value="<%=memberVO.getQualifications()%>" readonly/></td>
			</tr>
			
			<tr>
				<td>專長:</td>
				<td><input type="TEXT" name="expertise" size="45" value="<%=memberVO.getExpertise()%>" readonly/></td>
			</tr>
			
			<tr>
				<td>個人介紹:</td>
				<td><input type="TEXT" name="introduction" size="45" value="<%=memberVO.getIntroduction()%>" readonly/></td>
			</tr>
			
			<tr>
<!-- 				<td>照片:</td> -->
				<td><input type="hidden" name="photo1" id="p_file1"> 
<!-- 					<div id="preview1"> -->
<!-- 						<span class="text"> 預覽 </span> -->
<!-- 					</div> -->
			</tr>
			
			<tr>
<!-- 				<td>照片:</td> -->
				<td><input type="hidden" name="photo2" id="p_file2">
<!-- 					<div id="preview2"> -->
<!-- 						<span class="text"> 預覽 </span> -->
<!-- 					</div> -->
			</tr>
			
			<tr>
<!-- 				<td>照片:</td> -->
				<td><input type="hidden" name="photo3" id="p_file3">
<!-- 					<div id="preview3"> -->
<!-- 						<span class="text"> 預覽 </span> -->
<!-- 					</div> -->
			</tr>



		</table>
		<br> <input type="hidden" name="action" value="update2">
			 <input	type="hidden" name="memberid" value="<%=memberVO.getMemberid()%>">
			 <input type="hidden" name="gender" value="<%=memberVO.getGender()%>"> 
			 <input	type="submit" value="送出修改">
     </FORM>
    </thead>
   </table>
  </div>
 </div>
</div>

<!-- -----------以下複製到虛線----------------------------以下複製到虛線------------------------- -->
			
			</div>
        </div>
        
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy; Your Website 2020</span>
          </div>
        </div>
      </footer>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="login.html">Logout</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="<%=request.getContextPath()%>/back-assets/vendor/jquery/jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="<%=request.getContextPath()%>/back-assets/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="<%=request.getContextPath()%>/back-assets/js/sb-admin-2.min.js"></script>
<!-- ---------------------------------------------------------------- -->
	
</body>








<!-- 預覽圖片 -->
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
	    
	    
	    
	    var dd = document.getElementById('p_file1');
	    dd.addEventListener("change",function(){
	      console.log('change 事件觸發');
	      for(let i = 0; i < this.files.length;i++){
	        console.log(this.files[i]);
	      }
	    });
	    var pf = document.getElementById("p_file1");
	    pf.addEventListener("change", function(e){
	      if(this.files.length > 0 ){
	        let reader = new FileReader();
	        reader.readAsDataURL(this.files[0]);
	        reader.addEventListener("load", function () {
	          console.log(reader.result);
	          let hh= "<img src='" + reader.result + "' class='preview_img' id='uu'>";
	          document.getElementById("preview1").innerHTML = hh;
	        });
	      }else{
	        document.getElementById("preview1").innerHTML = "";
	      }
	    });
	    
	    
	    
	    var dd = document.getElementById('p_file2');
	    dd.addEventListener("change",function(){
	      console.log('change 事件觸發');
	      for(let i = 0; i < this.files.length;i++){
	        console.log(this.files[i]);
	      }
	    });
	    var pf = document.getElementById("p_file2");
	    pf.addEventListener("change", function(e){
	      if(this.files.length > 0 ){
	        let reader = new FileReader();
	        reader.readAsDataURL(this.files[0]);
	        reader.addEventListener("load", function () {
	          console.log(reader.result);
	          let hh= "<img src='" + reader.result + "' class='preview_img' id='uu'>";
	          document.getElementById("preview2").innerHTML = hh;
	        });
	      }else{
	        document.getElementById("preview2").innerHTML = "";
	      }
	    });
	    
	    
	    
	    var dd = document.getElementById('p_file3');
	    dd.addEventListener("change",function(){
	      console.log('change 事件觸發');
	      for(let i = 0; i < this.files.length;i++){
	        console.log(this.files[i]);
	      }
	    });
	    var pf = document.getElementById("p_file3");
	    pf.addEventListener("change", function(e){
	      if(this.files.length > 0 ){
	        let reader = new FileReader();
	        reader.readAsDataURL(this.files[0]);
	        reader.addEventListener("load", function () {
	          console.log(reader.result);
	          let hh= "<img src='" + reader.result + "' class='preview_img' id='uu'>";
	          document.getElementById("preview3").innerHTML = hh;
	        });
	      }else{
	        document.getElementById("preview3").innerHTML = "";
	      }
	    });
	});
</script>


</script>
</html>