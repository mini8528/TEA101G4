<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //ClassDetailServlet.java(Concroller), 存入req的coachCommentVO物件
%>

<html>
<head>
<title>Member資料 - listOneMember.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>Member資料 - ListOneMember.jsp</h3>
		 <a href="<%=request.getContextPath()%>/front-end/member/select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
	</td></tr>
</table>

<table>
	<tr>
		<th>memberid</th>
			
			<th>name</th>
			<th>account</th>
			<th>password</th>
			<th>gender</th>
			<th>phone</th>
			
			<th>birthday</th>
			<th>email</th>
			<th>photo</th>
			<th>address</th>
			<th>authority</th>
			
			<th>qualifications</th>
			<th>expertise</th>
			<th>introduction</th>
			<th>photo1</th>
			<th>photo2</th>
			
			<th>photo3</th>
			<th>adddate</th>
			<th>修改</th>
			<th>刪除</th>
	</tr>
	<tr>
		<td><%=memberVO.getMemberid()%></td>
		
		<td><%=memberVO.getName()%></td>
		<td><%=memberVO.getAccount()%></td>
		<td><%=memberVO.getPassword()%></td>
		<td><%=memberVO.getGender()%></td>
		<td><%=memberVO.getPhone()%></td>
		
		<td><%=memberVO.getBirthday()%></td>
		<td><%=memberVO.getEmail()%></td>
		<td>
		<img src="<%=request.getContextPath()%>/front-end/member/memberShow.do?memberid=${memberVO.memberid}"  width="100" height="100">
		</td>
		<td><%=memberVO.getAddress()%></td>
		<td><%=memberVO.getAuthority()%></td>
		
		<td><%=memberVO.getQualifications()%></td>
		<td><%=memberVO.getExpertise()%></td>
		<td><%=memberVO.getIntroduction()%></td>
		<td>
		<img src="<%=request.getContextPath()%>/front-end/member/memberShow1.do?memberid=${memberVO.memberid}"  width="100" height="100">
		</td>
		<td>
		<img src="<%=request.getContextPath()%>/front-end/member/memberShow2.do?memberid=${memberVO.memberid}"  width="100" height="100">
		</td>
		
		<td>
		<img src="<%=request.getContextPath()%>/front-end/member/memberShow3.do?memberid=${memberVO.memberid}"  width="100" height="100">
		</td>
		<td><%=memberVO.getAdddate()%></td>
		
		
		
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/MemberServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="memberid"  value="${memberVO.memberid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/MemberServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="memberid"  value="${memberVO.memberid}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		
	</tr>
</table>

</body>
</html>