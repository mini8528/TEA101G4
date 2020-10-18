<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
MemberService mService = new MemberService();
	List<MemberVO> list = mService.getAll();
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html lang="en">
<head>
<title>所有Member資料 - listAllMember.jsp</title>

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
	width: 800px;
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
          <h1 class="h3 mb-4 text-gray-800">會員資料管理</h1>
    
<div>
<!-- ---------------------------------------------------------------- -->         

<!-- 	<table id="table-1"> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<h3>所有Member資料</h3> -->
<!-- 				<h4> -->
<!-- 					<a href="select_page.jsp"><img src="images/back1.gif" -->
<!-- 						width="100" height="32" border="0">回首頁</a> -->
<!-- 				</h4> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

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
		<tr>
			<th>memberid</th>
			
			<th>name</th>
			<th>account</th>
<!-- 			<th>password</th> -->
<!-- 			<th>gender</th> -->
<!-- 			<th>phone</th> -->
			
<!-- 			<th>birthday</th> -->
			<th>email</th>
<!-- 			<th>photo</th> -->
			<th>address</th>
			<th>authority</th>
			
<!-- 			<th>qualifications</th> -->
<!-- 			<th>expertise</th> -->
<!-- 			<th>introduction</th> -->
<!-- 			<th>photo1</th> -->
<!-- 			<th>photo2</th> -->
			
<!-- 			<th>photo3</th> -->
			<th>adddate</th>
			<th>修改</th>
<!-- 			<th>刪除</th> -->
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${memberVO.memberid}</td>
				
				<td>${memberVO.name}</td>
				<td>${memberVO.account}</td>
<%-- 				<td>${memberVO.password}</td> --%>
<%-- 				<td>${memberVO.gender}</td> --%>
<%-- 				<td>${memberVO.phone}</td> --%>
				
<%-- 				<td>${memberVO.birthday}</td> --%>
				<td>${memberVO.email}</td>
<!-- 				<td> -->
<!-- 				<img -->
<%-- 					src="<%=request.getContextPath()%>/back-end/member/memberShow.do?memberid=${memberVO.memberid}" --%>
<!-- 					width="100" height="100"> -->
<!-- 				</td> -->
				<td>${memberVO.address}</td>
				<td>${memberVO.authority}</td>
				
<%-- 				<td>${memberVO.qualifications}</td> --%>
<%-- 				<td>${memberVO.expertise}</td> --%>
<%-- 				<td>${memberVO.introduction}</td> --%>
<!-- 				<td> -->
<!-- 				<img -->
<%-- 					src="<%=request.getContextPath()%>/back-end/member/memberShow1.do?memberid=${memberVO.memberid}" --%>
<!-- 					width="100" height="100"> -->
<!-- 				</td> -->
<!-- 				<td> -->
<!-- 				<img -->
<%-- 					src="<%=request.getContextPath()%>/back-end/member/memberShow2.do?memberid=${memberVO.memberid}" --%>
<!-- 					width="100" height="100"> -->
<!-- 				</td> -->
				
<!-- 				<td> -->
<!-- 				<img -->
<%-- 					src="<%=request.getContextPath()%>/back-end/member/memberShow3.do?memberid=${memberVO.memberid}" --%>
<!-- 					width="100" height="100"> -->
<!-- 				</td> -->
				<td>${memberVO.adddate}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/member/MemberServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="memberid" value="${memberVO.memberid}">
						<input type="hidden" name="action" value="getOne_For_Update2">
					</FORM>
				</td>
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/member/MemberServlet" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 							name="memberid" value="${memberVO.memberid}"> --%>
<!-- 						<input type="hidden" name="action" value="delete"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
			</tr>
		</c:forEach>
		</thead>
		</table>
		</div>
		</div>
		</div>


	<%@ include file="page2.file"%>
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
</html>