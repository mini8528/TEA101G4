<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.action.model.ActionService"%>
<%@ page import="com.action.model.ActionVO"%>
<%@ page import="org.apache.commons.codec.binary.StringUtils"%>
<%@ page import="org.apache.commons.codec.binary.Base64"%>


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


<!DOCTYPE html>
<html lang="en">

<head>



</head>
<body bgcolor=white>


<jsp:include page="/front-end/header.jsp" flush="true" />

    <section class="ftco-section ftco-no-pb ftco-no-pt ftco-program bg-light" id="programs-section">
      <div class="container">
        <div class="row no-gutters" >
          <div class="col-md-4 ftco-animate py-5 nav-link-wrap js-fullheight">
            <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
              <a class="nav-link px-4" id="v-pills-1-tab"  href="${pageContext.request.contextPath}/front-end/trainingcls/addMyCls.jsp"role="button" aria-controls="v-pills-1" aria-selected="true"><span class="mr-3 flaticon-gym"></span>健身訓練計畫</a>

              <a class="nav-link px-4" id="v-pills-2-tab"  href="${pageContext.request.contextPath}/front-end/action/listAllAction.jsp" role="tab" aria-controls="v-pills-2" aria-selected="false"><span class="mr-3 flaticon-body"></span>健身訓練運動</a>

              <a class="nav-link px-4" id="v-pills-3-tab"  href="${pageContext.request.contextPath}/front-end/trainingcls/showmycls.jsp" role="tab" aria-controls="v-pills-3" aria-selected="false"><span class="mr-3 flaticon-woman"></span>我的健身課表</a>

              <a class="nav-link px-4" id="v-pills-4-tab"  href="${pageContext.request.contextPath}/front-end/traininghist/showmyhist.jsp" role="tab" aria-controls="v-pills-4" aria-selected="false"><span class="mr-3 flaticon-abs"></span>我的健身紀錄</a>

              <a class="nav-link px-4" id="v-pills-5-tab" data-toggle="pill" href="#v-pills-5" role="tab" aria-controls="v-pills-5" aria-selected="false"><span class="mr-3 flaticon-running"></span></a>

              <a class="nav-link px-4" id="v-pills-6-tab" data-toggle="pill" href="#v-pills-6" role="tab" aria-controls="v-pills-6" aria-selected="false"><span class="mr-3 flaticon-meditation"></span></a>

              <a class="nav-link px-4" id="v-pills-7-tab" data-toggle="pill" href="#v-pills-7" role="tab" aria-controls="v-pills-7" aria-selected="false"><span class="mr-3 flaticon-aerobic"></span></a>

            	<a class="nav-link px-4" id="v-pills-08-tab" data-toggle="pill" href="#v-pills-08" role="tab" aria-controls="v-pills-08" aria-selected="false"><span class="mr-3 flaticon-gym"></span></a>
            </div>
          </div>

<!-- ====================================———	TRAINING ACTION VIDEO SECTION===================================== -->
               <section class="py-8 py-md-10 list-fullwidth">
                        <div class="container">
  <%@ include file="frontendpage1.file" %> 
                    <c:forEach var="actionVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					      <div class="media media-list-view mb-5" >
						     <video id="addVideo" controls autoplay width="320" height="220" playsinline muted>
							<source src="${actionVO.videoBase64Url}" />
						     </video>
					      <div class="media-img-overlay">
					    </div>
                        <div class="media-body" style=="width:500px">
							<h3>
							  <font size ="5" >
							    <a class="text-warning" ${actionVO.part}>${actionVO.part}</a> 
							  </font>
							</h3>
							   <ul class="list-unstyled d-flex text-muted mb-3" >
								  <li class="mr-3"></li>
							   </ul>
							<p class="text-justify mb-lg-2"></p>
				        </div>
					    <div class="media-body">
							   <h3>
						           <font size="4">	
						              <a class="text-warning" ${actionVO.actionnm}>${actionVO.actionnm}</a> 
						           </font>
							   </h3>
							   <ul class="list-unstyled d-flex text-muted mb-3">
								  <li class="mr-3"></li>
							   </ul>
							<p class="text-justify mb-lg-2"></p>
					     </div>
					     </div>
	                 </c:forEach>
	               </div>
                 </section>
                   </div>
                  </div>
                 </div>
                </div>
               </div>
              </div>
         </div>
</section>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<%@ include file="frontendpage2.file" %>


<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
</html>