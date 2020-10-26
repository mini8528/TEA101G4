<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog_mes.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	String flag = (String) request.getAttribute("flag");
	List<Blog_MesVO> list = null;
	if(flag == null){
		Blog_MesService blogmesSvc = new Blog_MesService();
		list = blogmesSvc.getAll();
	}else{
		list = (List<Blog_MesVO>) request.getAttribute("list");
	}
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>部落格留言管理</title>

<!-- ----------以下複製到虛線--------------------以下複製到虛線------------------------------------------- -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <!-- Custom fonts for this template-->
  <link href="<%=request.getContextPath()%>/back-assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="<%=request.getContextPath()%>/back-assets/css/sb-admin-2.min.css" rel="stylesheet">
<!-- ---------------------------------------------------------------- -->
<style >
        
 .list-wrapper {
            padding: 15px;
            overflow: hidden;
        }

        .list-item {
            border: 1px solid #EEE;
            background: #FFF;
            margin-bottom: 10px;
            padding: 10px;
            box-shadow: 0px 0px 10px 0px #EEE;
        }

        .list-item h4 {
            color: #2f3c43;
            font-size: 18px;
            margin: 0 0 5px;
        }

        .list-item p {
            margin: 0;
        }

        .simple-pagination ul {
            margin: 0 0 20px;
            padding: 0;
            list-style: none;
            text-align: center;
        }

        .simple-pagination li {
            display: inline-block;
            margin-right: 5px;
        }

        .simple-pagination li a,
        .simple-pagination li span {
            color: #666;
            padding: 5px 10px;
            text-decoration: none;
            border: 1px solid #EEE;
            background-color: #FFF;
            box-shadow: 0px 0px 10px 0px #EEE;
        }

        .simple-pagination .current {  //改顏色
            color: #FFF;
            background-color: #B5B5B5;
            border-color: #B5B5B5;
        }

        .simple-pagination .prev.current,
        .simple-pagination .next.current {
            background: #2f3c43;
        }

</style>

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
            <nav class="navbar navbar-expand-lg navbar-light bg-light">


  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    <c:if test="${flag != null }">
    <a href="<%=request.getContextPath()%>/back-end/blog_mes/listAllBlog_Mes_admin.jsp" class="btn btn-secondary" role="button" aria-pressed="true">返回</a>
    </c:if>
    </ul>
    <form class="form-inline my-2 my-lg-0" method="post" action="<%=request.getContextPath()%>/blog_Mes/Blog_MesServlet?action=adminSearchBlogMes">
      <input class="form-control mr-sm-2" type="search" placeholder="搜尋留言" aria-label="Search" name="searchText">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
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

				<%@ include file="page1.file" %> 
				 <c:forEach var="blog_MesVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
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
                
                <%@ include file="page2.file" %>
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
  
	  var items = $(".list-wrapper .list-item");
	  var numItems = items.length;
	  var perPage = 9;  //列9筆
	  items.slice(perPage).hide();
	
	  $('#pagination-container').pagination({
	      items: numItems,
	      itemsOnPage: perPage,
	      prevText: "&laquo;",
	      nextText: "&raquo;",
	      onPageClick: function (pageNumber) {
	          var showFrom = perPage * (pageNumber - 1);
	          var showTo = showFrom + perPage;
	          items.hide().slice(showFrom, showTo).show();
	      }
	  });
  
  </script>
</body>
</html>