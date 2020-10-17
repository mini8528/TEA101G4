<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lat.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
LatService latSvc = new LatService();
    List<LatVO> list = latSvc.getAll();
    pageContext.setAttribute("list",list);
    for (LatVO e : list) {
    	System.out.print(e.getLatestnewsid() + ",");
		System.out.print(e.getAdminid() + ",");
		System.out.print(e.getAdmin2id() + ",");
		System.out.print(e.getText() + ",");
		System.out.print(e.getImage() + ",");
		System.out.print(e.getAdddate() + ",");
		System.out.print(e.getUpdatetime()+",");
		System.out.print(e.getUploaddate() + ",");
		System.out.println();
    }
%>


<html>
<head>
<title>�Ҧ����ʸ�� - listAllLat.jsp</title>

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
<!-- ----------�H�U�ƻs���u--------------------�H�U�ƻs���u------------------------------------------- -->
 <!-- ---------------------------------------------------------------- -->

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

          <!-- Page Heading -->
          <h1 class="h3 mb-4 text-gray-800">Blank Page</h1>
    
<div>
<!-- ---------------------------------------------------------------- -->       
<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����ʸ��  - listAllLat.jsp</h3>
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

<table>
	<tr>
		<th>���ʮ����s��</th>
		<th>�޲z���s��</th>
		<th>�޲z���s��(��s)</th>
		<th>���e</th>
		<th>�Ϥ�1</th>
		<th>�s�W���</th>
		<th>��s���</th>
		<th>�峹�W�Ǥ��</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="latVo" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
	<tr>
			<td>${latVo.latestnewsid}</td>
			<td>${latVo.adminid}</td>
			<td>${latVo.admin2id}</td>
			<td>${latVo.text}</td>
			<td>
			<img  src="<%=request.getContextPath()%>/back-end/lat/latshow.do?latestnewsid=${latVo.latestnewsid}"  width="100" height="100" >
			</td>
		<td><fmt:formatDate value="${latVo.adddate}" pattern="yyyy-MM-dd"/></td>
			<td>${latVo.updatetime}</td>
			<td>${latVo.uploaddate}</td>
		

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/lat/lat.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="latestnewsid"  value="${latVo.latestnewsid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/lat/lat.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="latestnewsid"  value="${latVo.latestnewsid}">
			     <input type="hidden" name="action" value="delete"></FORM>   
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
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
</html>