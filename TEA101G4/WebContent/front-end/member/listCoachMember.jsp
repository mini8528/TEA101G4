<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
// MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //ClassDetailServlet.java(Concroller), 存入req的coachCommentVO物件
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


<section class="py-8 py-md-10">
  <div class="container">
    <div class="row">
      <div class="col-sm-4 col-xs-12">
        <div class="image mb-5 mb-md-0">
          <img class="w-100 rounded" src="<%=request.getContextPath()%>/front-end/member/memberShow.do?memberid=${userVO.memberid}" alt="team-1.jpg">
        </div>
      </div>
                <div class="card bg-transparent shadow-none">
						<div class="card-header card-header-lg bg-warning text-white p-6 rounded-top">
              <h4 class="font-weight-bold mb-0">會員資料</h4>
          	</div>

            <div class="card-body border border-top-0 rounded-bottom-sm p-10">
              <div class="row">
                <div class="form-group form-group-icon col-lg-6">
                  <label for="first-name">姓名</label>
                  <div class="form-control border-warning rounded-sm" id="first-name" >${userVO.name}</div>
                </div>

                <div class="form-group form-group-icon col-lg-6">
                  <label for="last-name">帳號</label>
                  <div class="form-control border-success rounded-sm" id="last-name">${userVO.account}</div>
                </div>
              </div>

              <div class="row">
                <div class="form-group form-group-icon col-lg-6">
                  <label for="address-1">性別</label>
                  <div class="form-control border-danger rounded-sm" id="address-1">${userVO.gender}</div>
                </div>

                <div class="form-group form-group-icon col-lg-6">
                  <label for="address-2">電話</label>
                  <div class="form-control border-info rounded-sm" id="address-2">${userVO.phone}</div>
                </div>
              </div>

              <div class="row">
                <div class="form-group form-group-icon col-lg-5">
                  <label for="city">生日</label>
                  <div class="form-control border-purple rounded-sm" id="city">${userVO.birthday}</div>
                </div>

                <div class="form-group form-group-icon col-lg-7">
                  <label for="zip-code">Email</label>
                  <div class="form-control border-pink rounded-sm" id="zip-code">${userVO.email}</div>
                </div>
              </div>

              <div class="row">
                <div class="form-group form-group-icon col-lg-6">
                  <label for="state">地址</label>
                  <div class="form-control border-warning rounded-sm" id="state">${userVO.address}</div>
                </div>

                <div class="form-group form-group-icon col-lg-6">
                  <label for="country">資歷</label>
                  <div class="form-control border-success rounded-sm" id="country">${userVO.qualifications}</div>
                </div>
              </div>
                
                <div class="row">
                <div class="form-group form-group-icon col-lg-6">
                  <label for="first-name">專長</label>
                  <div class="form-control border-danger rounded-sm" id="first-name" >${userVO.expertise}</div>
                </div>

                <div class="form-group form-group-icon col-lg-6">
                  <label for="last-name">個人介紹</label>
                  <div class="form-control border-info rounded-sm" id="last-name">${userVO.introduction}</div>
                </div>
              </div>

              <div class="row">
                <div class="form-group form-group-icon col-lg-6">
                  <label for="photo1">證照</label>
                  <div class="image mb-5 mb-md-0" id="photo1">
                  	<img class="w rounded" src="<%=request.getContextPath()%>/front-end/member/memberShow1.do?memberid=${userVO.memberid}"  width="180" height="120">
                  </div>
                </div>

                <div class="form-group form-group-icon col-lg-6">
                  <label for="photo2">證照</label>
                  <div class="image mb-5 mb-md-0" id="photo2">
                  	<img class="w rounded" src="<%=request.getContextPath()%>/front-end/member/memberShow2.do?memberid=${userVO.memberid}"  width="180" height="120">
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="form-group form-group-icon col-lg-5">
                  <label for="photo3">證照</label>
                  <div class="image mb-5 mb-md-0" id="photo3">
                  	<img class="w rounded" src="<%=request.getContextPath()%>/front-end/member/memberShow3.do?memberid=${userVO.memberid}"  width="180" height="120">
                  </div>
                </div>

                <div class="form-group form-group-icon col-lg-7">
                  <label for="adddate">新增日期</label>
                  <div class="form-control border-pink rounded-sm" id="adddate">${userVO.adddate}</div>
                </div>
        <table>
        <tr>
        	
			<br>
            <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/MemberServlet" style="margin-bottom: 0px;">
			  
			     <input type="submit" value="修改" class="btn btn-danger mb-2">
			     <input type="hidden" name="memberid"  value="${userVO.memberid}">
			     <input type="hidden" name="action"	value="getOne_For_Update3"></FORM>
			</td>
			<br>
			<div>
				<ul>
   					<li class="btn btn-warning mb-2">
   					<a href='<%=request.getContextPath()%>/front-end/coachClass/addCoachClass.jsp'>新增課程</a>
    				</li>
  				</ul>
			</div>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/MemberServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除" class="btn btn-danger mb-2"> -->
<%-- 			     <input type="hidden" name="memberid"  value="${memberVO.memberid}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		 </tr>
		</table>
      </div>
     </div>
    </div>
   </div>
  </div>
</section>

<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>