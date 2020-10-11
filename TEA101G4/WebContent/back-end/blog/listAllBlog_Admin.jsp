<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.net.URLDecoder"%> 

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    String search = (String) request.getAttribute("search"); 
    String searchBlogno = (String) request.getParameter("searchBlogno"); 
    request.setAttribute("searchBlogno", searchBlogno);
    String searchMemberid = (String) request.getParameter("searchMemberid"); 
    request.setAttribute("searchMemberid", searchMemberid);
    String searchTitle = (String)request.getParameter("searchTitle");
    request.setAttribute("searchTitle", searchTitle);
    String searchText = (String)request.getParameter("searchText");
    request.setAttribute("searchText", searchText);
    if(searchTitle!= null){
    	searchTitle = new String(searchTitle.getBytes("ISO8859-1"), "UTF-8"); 
    }
	if(searchText!= null){
    	searchText = new String(searchText.getBytes("ISO8859-1"), "UTF-8"); 
	}
  
	List<BlogVO> list = null;
	if(search == null){
    	BlogService blogSvc = new BlogService();
        if(searchBlogno == null && searchMemberid == null && searchTitle == null && searchText == null){
        	list = blogSvc.getAll();
            pageContext.setAttribute("list",list);
        }else{
        	if(searchBlogno == null) {
        		searchBlogno = "";
			}
        	if(searchMemberid == null) {
        		searchMemberid = "";
			}
        	if(searchTitle == null) {
        		searchTitle = "";
			}
        	if(searchText == null) {
        		searchText = "";
			}
        	list = blogSvc.adminSearch(searchBlogno, searchMemberid, searchTitle, searchText);
        }
    	
    }else{
    	list = (List <BlogVO>) request.getAttribute("adminSearchList");	
    }
	pageContext.setAttribute("list",list);
	
%>


<html>
<head>
<title>文章管理</title>


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
<!-- 修改假資料 -->


<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>文章編號</th> -->
<!-- 		<th>一般會員編號</th> -->
<!-- 		<th>文章分類</th> -->
<!-- 		<th>發文日期</th> -->
<!-- 		<th>標題</th> -->
<!-- 		<th>內文</th> -->
<!-- <!-- 		<th>影片</th> -->
<!-- 		<th>文章狀態</th> -->
<!-- 		<th>更新日期</th> -->
<!-- 		<th>照片</th> -->
<!-- 		<th>影片</th> -->
<!-- 		<th>修改</th> -->
<!-- 		<th>刪除</th> -->
<!-- 	</tr> -->
<%-- 	<%@ include file="page1.file" %>  --%>
<%-- <%-- 	<c:forEach var="blogVO" items="${list}"> --%> 
<%-- 	<c:forEach var="blogVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
	
<!-- 		<tr> -->
<%-- 			<td>${blogVO.blogno}</td> --%>
<%-- 			<td>${blogVO.memberId}</td> --%>
<%-- 			<td>${blogVO.blogClass}</td> --%>
<%-- 			<td>${blogVO.postDate}</td> --%>
<%-- 			<td>${blogVO.title}</td> --%>
<%-- 			<td>${blogVO.text}</td>  --%>
<%-- <%-- 			<td>${blogVO.photo}</td> --%> 
<%-- <%-- 			<td>${blogVO.video}</td> --%> 
<%-- 			<td>${blogVO.status}</td> --%>
<%-- 			<td>${blogVO.updateTime}</td> --%>
<%-- 			<td><img class="blog" width="400" src="<%=request.getContextPath()%>/blog/ShowPicture?blogno=${blogVO.blogno}"></td> --%>
<!-- 			<td> -->
<!-- 				<video width="400" height="240" controls class="blog"> -->
<%--   					<source src="<%=request.getContextPath()%>/blog/ShowVideo?blogno=${blogVO.blogno}" type="video/mp4"> --%>
<!-- 				</video> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/BlogServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="blogno"  value="${blogVO.blogno}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/BlogServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="blogno"  value="${blogVO.blogno}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
<!-- </table> -->
<%-- <%@ include file="page2.file" %> --%>


 <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">文章管理</h1>
          <p class="mb-4"></p>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">All Article</h6>
            </div>
            <nav class="navbar navbar-light bg-light justify-content-between">
			  <form class="form-inline" method="post" action="<%=request.getContextPath() %>/blog/BlogServlet?action=adminSearchBlog">
			    <label>文章編號: </label><input class="form-control mr-sm-2" type="search" name="searchBlogno" placeholder="Blogno" aria-label="Search" style="width: 100px; height: 35px" value="${searchBlogno == null? '' : searchBlogno}">
			    <label>會員編號: </label><input class="form-control mr-sm-2" type="search" name="searchMemberid" placeholder="Memberid" aria-label="Search" style="width: 120px; height: 35px" value="${searchMemberid == null? '' : searchMemberid}">
			    <label>標題: </label><input class="form-control mr-sm-2" type="search" name="searchTitle" placeholder="Title" aria-label="Search" style="width: 150px; height: 35px" value="${searchTitle == null? '' : searchTitle}">
			    <label>內文: </label><input class="form-control mr-sm-2" type="search" name="searchText" placeholder="Text" aria-label="Search" style="width: 150px; height: 35px" value="${searchText == null? '' : searchText}">
			    <button class="btn btn-outline-success my-2 my-sm-0" type="submit" style="width: 80px; height: 35px">Search</button>
			  </form>
			</nav>
            <div class="card-body">
              <div class="table-responsive  list-wrapper">
                <table class="table table-bordered list-item" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>文章編號</th>
                      <th>會員編號</th>
                      <th>文章分類</th>
                      <th>發文日期</th>
                      <th>標題</th>
                      <th>內文</th>
                      <th>狀態(隱藏)</th>
                      <th>更新日期</th>
                      <th>修改</th>
<!-- 					  <th>刪除</th> -->
                    </tr>
                  </thead>
<!--                   <tfoot> -->
<!--                     <tr> -->
<!--                       <th>文章編號</th> -->
<!--                       <th>一般會員編號</th> -->
<!--                       <th>文章分類</th> -->
<!--                       <th>發文日期</th> -->
<!--                       <th>標題</th> -->
<!--                       <th>內文</th> -->
<!--                       <th>文章狀態</th> -->
<!--                       <th>更新日期</th> -->
<!--                       <th>修改</th> -->
<!-- 					  <th>刪除</th> -->
<!--                     </tr> -->
<!--                   </tfoot> -->
				<%@ include file="page1.file" %> 
				 <c:forEach var="blogVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	                  <tbody>
	                    <tr>
	                      <td>${blogVO.blogno}</td>
						  <td>${blogVO.memberId}</td>
						  <td>${blogVO.blogClass}</td>
						  <td><fmt:formatDate value="${blogVO.postDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						  <td>${blogVO.title}</td>
						  <td>${blogVO.text}</td>
						  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/BlogServlet" style="margin-bottom: 0px;">
						  <td><select name="status">
										<option value="N" ${blogVO.status=="N"? 'selected' : ''}>顯示</option>
										<option value="Y" ${blogVO.status=="Y"? 'selected' : ''}>隱藏</option>
				
								</select></td>
						  <td><fmt:formatDate value="${blogVO.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						  <td>
						     <input type="submit" value="修改" onclick="javascript: return del()">
						     <input type="hidden" name="blogno"  value="${blogVO.blogno}">
						     <input type="hidden" name="action"	value="admin_Update">
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
		<div id="pagination-container"></div>
  <!-- Bootstrap core JavaScript-->
  <script src="<%=request.getContextPath()%>/back-assets/vendor/jquery/jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="<%=request.getContextPath()%>/back-assets/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="<%=request.getContextPath()%>/back-assets/js/sb-admin-2.min.js"></script>
  <script >
  
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