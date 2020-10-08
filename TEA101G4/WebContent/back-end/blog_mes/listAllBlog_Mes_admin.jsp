<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog_mes.model.*"%>

<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    Blog_MesService blogmesSvc = new Blog_MesService();
    List<Blog_MesVO> list = blogmesSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ�������d����� - listAllBlog_Mes.jsp</title>

<!-- ----------�H�U�ƻs���u--------------------�H�U�ƻs���u------------------------------------------- -->
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

<!-- <h4>�����m�߱ĥ� EL ���g�k����:</h4> -->
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>�Ҧ�������d����� - listAllBlog_Mes.jsp</h3> -->
<!-- 		 <h4><a href="select_page.jsp">�^����</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<%-- <%-- ���~��C --%> 
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">�Эץ��H�U���~:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>������d���s��</th> -->
<!-- 		<th>�峹�s��</th> -->
<!-- 		<th>�@��|���s��</th> -->
<!-- 		<th>�d�����e</th> -->
<!-- 		<th>�d�����</th> -->
<!-- 		<th>��s���</th> -->
<!-- 		<th>�d�����A</th> -->
<!-- 		<th>�ק�</th> -->
<!-- 		<th>�R��</th> -->
<!-- 	</tr> -->
<%-- 	<%@ include file="page1.file" %>  --%>

<%-- 	<c:forEach var="blog_MesVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
	
<!-- 		<tr> -->
<%-- 			<td>${blog_MesVO.blogMesno}</td> --%>
<%-- 			<td>${blog_MesVO.blogno}</td> --%>
<%-- 			<td>${blog_MesVO.memberId}</td> --%>
<%-- 			<td>${blog_MesVO.text}</td> --%>
<%-- 			<td>${blog_MesVO.postDate}</td> --%>
<%-- 			<td>${blog_MesVO.updateTime}</td> --%>
<%-- 			<td>${blog_MesVO.status}</td> --%>
			
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog_Mes/Blog_MesServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�ק�"> -->
<%-- 			     <input type="hidden" name="blogMesno"  value="${blog_MesVO.blogMesno}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog_Mes/Blog_MesServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�R��"> -->
<%-- 			     <input type="hidden" name="blogMesno"  value="${blog_MesVO.blogMesno}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
<!-- </table> -->

<!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">������d���޲z</h1>
          <p class="mb-4"></p>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">All Article</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                     <th>������d���s��</th>
					 <th>�峹�s��</th>
					 <th>�@��|���s��</th>
					 <th>�d�����e</th>
					 <th>�d�����</th>
					 <th>��s���</th>
					 <th>�d�����A</th>
					 <th>�ק�</th>
<!-- 					 <th>�R��</th> -->
                     </tr>
                  </thead>

				
				 <c:forEach var="blog_MesVO" items="${list}">
	                  <tbody>
	                    <tr>
	                    	<td>${blog_MesVO.blogMesno}</td>
							<td>${blog_MesVO.blogno}</td>
							<td>${blog_MesVO.memberId}</td>
							<td>${blog_MesVO.text}</td>
							<td>${blog_MesVO.postDate}</td>
							<td>${blog_MesVO.updateTime}</td>
						  	<td><select name="status">
								<option value="N">���</option>
								<option value="Y">����</option>
								</select></td>
							<td>
							  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog_Mes/Blog_MesServlet" style="margin-bottom: 0px;">
							     <input type="submit" value="�ק�">
							     <input type="hidden" name="blogMesno"  value="${blog_MesVO.blogMesno}">
							     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
							</td>
						  
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

<!-- -----------�H�U�ƻs���u----------------------------�H�U�ƻs���u------------------------- -->
			
			</div>
        </div>
        
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
<!--       <footer class="sticky-footer bg-white"> -->
<!--         <div class="container my-auto"> -->
<!--           <div class="copyright text-center my-auto"> -->
<!--             <span>Copyright &copy; Your Website 2020</span> -->
<!--           </div> -->
<!--         </div> -->
<!--       </footer> -->
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
            <span aria-hidden="true">��</span>
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
</body>
</html>