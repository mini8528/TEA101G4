<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Pro: Home</title>

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
   <tr><td><h3>Pro: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Cus: Home</p>

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
  <li><a href='listAllPro.jsp'>List</a> all Cuss.  <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="pro.do" >
        <b>��J�ӫ~�s�� (�pPD0001):</b>
        <input type="text" name="productid">
        <input type="hidden" name="action" value="getSome_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="proSvc" scope="page" class="com.pro.model.ProService" />
   
  <li>
     <FORM METHOD="post" ACTION="pro.do" >
       <b>��ܰӫ~�����s��:</b>
       <select size="1" name="productid">
         <c:forEach var="ProVO" items="${proSvc.all}" > 
          <option value="${PusVO.productid}">${PusVO.productid}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getSome_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
 
</ul>


<h3>�J�A�޲z</h3>

<ul>
  <li><a href='addPro.jsp'>Add</a> a new Pro.</li>
</ul>

</body>
</html>