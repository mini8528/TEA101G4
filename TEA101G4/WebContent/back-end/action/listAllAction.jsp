<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.action.model.ActionService"%>
<%@ page import="com.action.model.ActionVO"%>
<%@ page import="org.apache.commons.codec.binary.StringUtils"%>
<%@ page import="org.apache.commons.codec.binary.Base64"%>
<%-- �����m�߱ĥ� EL ���g�k���� org\apache\commons\codec\binary --%>

<%
	ActionService actionSvc = new ActionService();
    List<ActionVO> list = actionSvc.getAll();
    for(ActionVO tempVO: list) {
		byte[] v_byte = tempVO.getVideo();
		StringBuilder sb = new StringBuilder();
		sb.append("data:video/mp4;base64,");
		sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(v_byte, false)));
		String videoUrl = sb.toString();
// 		System.out.println("QQQQQQ "+tempVO.getActionid());
		tempVO.setVideoBase64Url(videoUrl);
	}
//     System.out.println("list...."+list.size());
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<!-- <title>�Ҧ��ʧ@��� - listAllAction.jsp</title> -->

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
          <h1 class="h3 mb-4 text-gray-800">�����V�m�v��</h1>
    
<div>
<!-- ---------------------------------------------------------------- -->   

<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>�Ҧ��ʧ@��� - listAllAction.jsp</h3> -->
<%-- 		 <h4><a href="<%=request.getContextPath()%>/back-end/action/select_page.jsp">�^����</a></h4> --%>
<!-- 	</td></tr> -->
<!-- </table> -->

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
		<th>�ʧ@�s��</th>
		<th>�ʧ@�W��</th>
		<th>�B�ʳ���</th>
		<th>�v��</th>
		<th>�W�Ǯɶ�</th>
		<th>��s�ɶ�</th>
		<th>�s�W</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	
	<c:forEach var="actionVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${actionVO.actionid}</td>
			<td>${actionVO.actionnm}</td>
			<td>${actionVO.part}</td>
			<td>
			<video id="addVideo" controls autoplay width="320" height="240">
               <source src="${actionVO.videoBase64Url}"/>
            </video>

			</td>
			<td>${actionVO.posttime}</td>
			<td>${actionVO.updatetime}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/action/ActionServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="actionid"  value="${actionVO.actionid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/action/ActionServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="actionid"  value="${actionVO.actionid}">
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