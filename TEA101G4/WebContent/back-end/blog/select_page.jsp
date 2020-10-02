<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title> Blog: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3> Blog: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Blog: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllBlog.jsp'>List</a> all Blog.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/BlogServlet"> 
        <b>��J�峹�s�� (�pB00001):</b>
        <input type="text" name="blogno">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="blogSvc" scope="page" class="com.blog.model.BlogService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/BlogServlet" >
       <b>��ܤ峹�s��:</b>
       <select size="1" name="blogno">
         <c:forEach var="blogVO" items="${blogSvc.all}" > 
          <option value="${blogVO.blogno}">${blogVO.blogno}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/BlogServlet">
       <b>��ܷ|���s��:</b>
       <select size="1" name="blogno">
         <c:forEach var="blogVO" items="${blogSvc.all}" > 
          <option value="${blogVO.blogno}">${blogVO.memberId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�峹�޲z</h3>

<ul>
  <li><a href='addBlog.jsp'>Add</a> a new Blog.</li>
</ul>

</body>
</html>