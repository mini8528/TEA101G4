<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
MemberVO memberVO = (MemberVO) session.getAttribute("userVO"); //ClassDetailServlet.java(Concroller), 存入req的coachCommentVO物件
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<!-- Site Tittle -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Member資料 - listOneMember.jsp</title>

	    <!-- Plugins css Style -->
    <link href="<%=request.getContextPath()%>/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/plugins/no-ui-slider/nouislider.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/assets/plugins/owl-carousel/owl.carousel.min.css" rel="stylesheet"
        media="screen">
    <link href="<%=request.getContextPath()%>/assets/plugins/owl-carousel/owl.theme.default.min.css" rel="stylesheet"
        media="screen">
    <link href="<%=request.getContextPath()%>/assets/plugins/fancybox/jquery.fancybox.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/assets/plugins/isotope/isotope.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/assets/plugins/animate/animate.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/plugins/select2/css/select2.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/plugins/revolution/css/settings.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/plugins/revolution/css/layers.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/plugins/revolution/css/navigation.css" rel="stylesheet">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Dosis:300,400,600,700|Open+Sans:300,400,600,700"
        rel="stylesheet">

    <!-- Custom css -->
    <link href="<%=request.getContextPath()%>/assets/css/kidz.css" id="option_style" rel="stylesheet">

    <!-- Favicon -->
    <link href="<%=request.getContextPath()%>/assets/img/favicon.png" rel="shortcut icon">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->


</head>



<body id="body" class="up-scroll" data-spy="scroll" data-target=".element-right-sidebar">

<jsp:include page="/front-end/header.jsp" flush="true" />


<section class="py-7 py-md-10">
  <div class="container">
    <div class="row">
      <div class="col-sm-4 col-xs-12">
        <div class="image mb-5 mb-md-0">
          <img class="w-100 rounded" src="<%=request.getContextPath()%>/front-end/member/memberShow.do?memberid=${userVO.memberid}" alt="team-1.jpg">
        </div>
      </div>

      <div class="col-sm-8 col-xs-12">
        
                <h2 class="text-danger font-weight-medium mb-3 ">會員資料</h2>

        <div class="text-white rounded bg-warning text-uppercase font-weight-medium px-6 py-3 mb-3">name</div>

        <div class="text-muted text-capitalize font-weight-medium ml-4 mb-5 font-size-20"><%=memberVO.getName()%></div>

        <div class="text-white rounded bg-success text-uppercase font-weight-medium px-6 py-3 mb-3">account</div>

        <div class="text-muted text-capitalize font-weight-medium ml-4 mb-5 font-size-20"><%=memberVO.getAccount()%></div>

        <div class="text-white rounded bg-danger text-uppercase font-weight-medium px-6 py-3 mb-3">gender</div>

        <div class="text-muted text-capitalize font-weight-medium ml-4 mb-5 font-size-20"><%=memberVO.getGender()%></div>

        <div class="text-white rounded bg-info text-uppercase font-weight-medium px-6 py-3 mb-3">phone</div>

        <div class="text-muted text-capitalize font-weight-medium ml-4 mb-5 font-size-20"><%=memberVO.getPhone()%></div>

        <div class="text-white rounded bg-purple text-uppercase font-weight-medium px-6 py-3 mb-3">birthday</div>

        <div class="text-muted text-capitalize font-weight-medium ml-4 mb-5 font-size-20"><%=memberVO.getBirthday()%></div>
        
        <div class="text-white rounded bg-warning text-uppercase font-weight-medium px-6 py-3 mb-3">email</div>

        <div class="text-muted text-capitalize font-weight-medium ml-4 mb-5 font-size-20"><%=memberVO.getEmail()%></div>

        <div class="text-white rounded bg-success text-uppercase font-weight-medium px-6 py-3 mb-3">address</div>

        <div class="text-muted text-capitalize font-weight-medium ml-4 mb-5 font-size-20"><%=memberVO.getAddress()%></div>

        <div class="text-white rounded bg-danger text-uppercase font-weight-medium px-6 py-3 mb-3">authority</div>

        <div class="text-muted text-capitalize font-weight-medium ml-4 mb-5 font-size-20"><%=memberVO.getAuthority()%></div>

        <div class="text-white rounded bg-info text-uppercase font-weight-medium px-6 py-3 mb-3">qualifications</div>

        <div class="text-muted text-capitalize font-weight-medium ml-4 mb-5 font-size-20"><%=memberVO.getQualifications()%></div>
        
        <div class="text-white rounded bg-purple text-uppercase font-weight-medium px-6 py-3 mb-3">expertise</div>

        <div class="text-muted text-capitalize font-weight-medium ml-4 mb-5 font-size-20"><%=memberVO.getExpertise()%></div>
        
        <div class="text-white rounded bg-warning text-uppercase font-weight-medium px-6 py-3 mb-3">introduction</div>

        <div class="text-muted text-capitalize font-weight-medium ml-4 mb-5 font-size-20"><%=memberVO.getIntroduction()%></div>

        <div class="text-white rounded bg-success text-uppercase font-weight-medium px-6 py-3 mb-3">證照一</div>

		<div class="image mb-5 mb-md-0">
          <img class="w rounded" src="<%=request.getContextPath()%>/front-end/member/memberShow1.do?memberid=${memberVO.memberid}"  width="100" height="100">
        </div>
        <div class="text-white rounded bg-danger text-uppercase font-weight-medium px-6 py-3 mb-3">證照二</div>
		
		<div class="image mb-5 mb-md-0">
          <img class="w rounded" src="<%=request.getContextPath()%>/front-end/member/memberShow2.do?memberid=${memberVO.memberid}"  width="100" height="100">
        </div>

        <div class="text-white rounded bg-info text-uppercase font-weight-medium px-6 py-3 mb-3">證照三</div>
		
		<div class="image mb-5 mb-md-0">
          <img class="w rounded" src="<%=request.getContextPath()%>/front-end/member/memberShow3.do?memberid=${memberVO.memberid}"  width="100" height="100">
        </div>

        <div class="text-white rounded bg-purple text-uppercase font-weight-medium px-6 py-3 mb-3">adddate</div>

        <div class="text-muted text-capitalize font-weight-medium ml-4 mb-5 font-size-20"><%=memberVO.getAdddate()%></div>
        <tr>
            <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/MemberServlet" style="margin-bottom: 0px;">
			  
			     <input type="submit" value="修改" class="btn btn-danger mb-2">
			     <input type="hidden" name="memberid"  value="${userVO.memberid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/MemberServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除" class="btn btn-danger mb-2"> -->
<%-- 			     <input type="hidden" name="memberid"  value="${memberVO.memberid}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
      </div>
    </div>
  </div>
</section>

<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>