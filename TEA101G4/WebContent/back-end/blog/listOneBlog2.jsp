<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.blog.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  BlogVO blogVO = (BlogVO) request.getAttribute("blogVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>�峹��� - listOneBlog.jsp</title>

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
	width: 600px;
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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�峹��� - ListOneBlog.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/blog/select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�峹�s��</th>
		<th>�@��|���s��</th>
		<th>�峹����</th>
		<th>�o����</th>
		<th>���D</th>
		<th>����</th>
		<th>�Ӥ�</th>
		<th>�v��</th>
		<th>�峹���A</th>
		<th>��s���</th>
		
	</tr>
	<tr>
		<td><%=blogVO.getBlogno()%></td>
		<td><%=blogVO.getMemberId()%></td>
		<td><%=blogVO.getBlogClass()%></td>
		<td><%=blogVO.getPostDate()%></td>
		<td><%=blogVO.getTitle()%></td>
		<td><%=blogVO.getText()%></td>
		<td><img class="blog" width="400"  src="<%=request.getContextPath()%>/blog/ShowPicture?blogno=${blogVO.blogno}"></td>
		<td>
			<video width="400" height="240" controls class="blog">
  				<source src="<%=request.getContextPath()%>/blog/ShowVideo?blogno=${blogVO.blogno}" type="video/mp4">
			</video>
		</td>
<%-- 		<td><%=blogVO.getPhoto()%></td> --%>
<%-- 		<td><%=blogVO.getVideo()%></td> --%>
		<td><%=blogVO.getStatus()%></td>
		<td><%=blogVO.getUpdateTime()%></td>
		
	</tr>
</table>

</body>
</html>