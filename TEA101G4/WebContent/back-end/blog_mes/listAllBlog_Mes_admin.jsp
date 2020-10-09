<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog_mes.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    Blog_MesService blogmesSvc = new Blog_MesService();
    List<Blog_MesVO> list = blogmesSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有部落格留言資料 - listAllBlog_Mes.jsp</title>

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
<body id="page-top">

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

<div>

<!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">部落格留言管理</h1>
          <p class="mb-4"></p>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Blog Mes</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                     <th>部落格留言編號</th>
					 <th>文章編號</th>
					 <th>一般會員編號</th>
					 <th>留言內容</th>
					 <th>留言日期</th>
					 <th>更新日期</th>
					 <th>留言狀態</th>
					 <th>修改</th>
<!-- 					 <th>刪除</th> -->
                     </tr>
                  </thead>

				
				 <c:forEach var="blog_MesVO" items="${list}">
	                  <tbody>
	                    <tr>
	                    	<td>${blog_MesVO.blogMesno}</td>
							<td>${blog_MesVO.blogno}</td>
							<td>${blog_MesVO.memberId}</td>
							<td>${blog_MesVO.text}</td>
							<td><fmt:formatDate value="${blog_MesVO.postDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td><fmt:formatDate value="${blog_MesVO.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog_Mes/Blog_MesServlet" style="margin-bottom: 0px;">
						  	<td><select name="status">
								<option value="N" ${(blog_MesVO.status == "N")? 'selected':'' }>顯示</option>
								<option value="Y" ${(blog_MesVO.status == "Y")? 'selected':'' }>隱藏</option>
								</select></td>
							<td>
							     <input type="submit" value="修改" onclick="javascript: return del()">
							     <input type="hidden" name="blogMesno"  value="${blog_MesVO.blogMesno}">
							     <input type="hidden" name="action"	value="admin_Update_Mes">
							</td>
                  			</FORM>
	                    </tr>
	                  </tbody>
                  </c:forEach>
                </table>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

<%-- <%@ include file="page2.file" %> --%>

<!-- -----------以下複製到虛線----------------------------以下複製到虛線------------------------- -->
			
			</div>
        </div>
        
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

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
  <script>
  function del() {
	  var msg = "確認修改?";
	  if (confirm(msg)){
	  return true;
	  }else{
	  window.location.reload();
	  return false;
	  }
	  }
  
  </script>
</body>
</html>