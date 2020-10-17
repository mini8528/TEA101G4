<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trainingclsdetail.model.*"%>

<%
  TrainingClsDetailVO tcdVO = (TrainingClsDetailVO) request.getAttribute("tcdVO");
%>

<!DOCTYPE html>
<html lang="en">


<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>訓練課程內容新增 - addTrainingClsDetail.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>
<jsp:include page="/front-end/header.jsp" flush="true" />

<table id="table-1">
	<tr><td>
		 <h3>訓練課程內容新增 - addTrainingClsDetail.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/trainingclsdetail/select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trainingclsdetail/TrainingClsDetailServlet" name="form1">
<table>
	<tr>
		<td>訓練課程內容編號:</td>
		<td><input type="TEXT" name="trainingclsdetailid" size="45" 
			 value="<%= (tcdVO==null)? "" : tcdVO.getTrainingclsdetailid()%>" /></td>
	</tr>
	<tr>
		<td>訓練課程編號:</td>
		<td><input type="TEXT" name="trainingclsid" size="45"
			 value="<%= (tcdVO==null)? "" : tcdVO.getTrainingclsid()%>" /></td>
	</tr>
	<tr>
		<td>訓練動作編號:</td>
		<td><input type="TEXT" name="actionid" size="45"
			 value="<%= (tcdVO==null)? "" : tcdVO.getActionid()%>" /></td>
	</tr>
	


<%-- 	<jsp:useBean id="actionSvc" scope="page" class="com.action.model.ActionService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>訓練動作:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="actionid"> -->
<%-- 			<c:forEach var="actVO" items="${actionSvc.all}"> --%>
<%-- 				<option value="${actVO.actionid}" ${(actVO.actionid==actVO.actionid)? 'selected':'' } >${actVO.actionnm} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
  <!-- ====================================
———	SIGN-UP OR LOG-IN
===================================== -->
        <section class="py-8 py-md-10">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 offset-lg-2 col-lg-4">
                        <div class="mb-4 mb-sm-0">
                            <div class="bg-warning rounded-top p-2">
                                <h3 class="text-white font-weight-bold mb-0 ml-2">Create An Account</h3>
                            </div>

                            <div class="border rounded-bottom-sm border-top-0">
                                <div class="p-3">
                                    <form action="#" method="POST" role="form">
                                        <div class="form-group form-group-icon">
                                            <input type="text" class="form-control border" placeholder="Name"
                                                required="">
                                        </div>

                                        <div class="form-group form-group-icon">
                                            <input type="text" class="form-control border" placeholder="User name"
                                                required="">
                                        </div>

                                        <div class="form-group form-group-icon">
                                            <input type="text" class="form-control border" placeholder="Phone"
                                                required="">
                                        </div>

                                        <div class="form-group form-group-icon">
                                            <input type="password" class="form-control border" placeholder="Password"
                                                required="">
                                        </div>

                                        <div class="form-group form-group-icon">
                                            <input type="password" class="form-control border" placeholder="Re-Password"
                                                required="">
                                        </div>

                                        <div class="form-group">
                                            <button type="submit"
                                                class="btn btn-danger text-uppercase w-100">Register</button>
                                        </div>

                                        <div class="form-group text-center text-secondary mb-0">
                                            <p class="mb-0">Allready have an account? <a class="text-danger"
                                                    href="#">Log in</a></p>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-lg-4">
                        <div class="mb-4 mb-sm-0">
                            <div class="bg-warning rounded-top p-2">
                                <h3 class="text-white font-weight-bold mb-0 ml-2">Login</h3>
                            </div>

                            <div class="border rounded-bottom-sm border-top-0">
                                <div class="p-3">
                                    <form action="<%=request.getContextPath()%>/loginhandler" method="POST" role="form">
                                        <div class="form-group form-group-icon">
                                            <input type="text" class="form-control border" name="account"
                                                value="${account == null ? '' : account}" placeholder="User name"
                                                data-error="請輸入帳號" required="required">
                                        </div>

                                        <div class="form-group form-group-icon">
                                            <input type="password" class="form-control border" name="password"
                                                value="${password == null ? '' : password}" placeholder="Password"
                                                data-error="請輸入密碼" required="">
                                        </div>

                                        <div class="form-group">
                                            <button type="submit" class="btn btn-danger text-uppercase w-100">Log
                                                In</button>
                                        </div>

                                        <div class="form-group text-center text-secondary mb-0">
                                            <a class="text-danger" href="#">Forgot password?</a>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </div> <!-- element wrapper ends -->


    <jsp:include page="/front-end/footer.jsp" flush="true" />
</body>

</html>