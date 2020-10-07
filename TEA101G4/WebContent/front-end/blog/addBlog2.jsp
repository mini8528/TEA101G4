<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.member.model.*"%>

<%	
	BlogVO blogVO = (BlogVO) request.getAttribute("blogVO");
%>
<!DOCTYPE html>
<html lang="en">

<head>
   
</head>

<body id="body" class="up-scroll" data-spy="scroll" data-target=".element-right-sidebar">
    <jsp:include page="/front-end/header.jsp" flush="true" />
        <!-- ====================================
�X�X�X	BLOG GRID LEFT SIDEBAR
===================================== -->
        <section class="py-8 py-md-10">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-lg-9 order-md-1">

                        <%-- ���~���C --%>
                        <c:if test="${not empty errorMsgs}">
                            <font style="color: red">�Эץ��H�U���~:</font>
                            <ul>
                                <c:forEach var="message" items="${errorMsgs}">
                                    <li style="color: red">${message}</li>
                                </c:forEach>
                            </ul>
                        </c:if>

                       <jsp:useBean id="memberSvc" scope="page"
								class="com.member.model.MemberService" />
                        

                        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/BlogServlet" name="form1"
                            enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="exampleFormControlInput1">�|���W��:</label> <input type="TEXT" name="memberid2"
                                    size="45" class="form-control"
                                    value="${memberSvc.getOneMember(userVO.memberid).getName()}" readonly />
                                     <input type="hidden" name="memberid"
                                    size="45" class="form-control"
                                    value="${userVO.memberid}"  />

                            </div>
                            <div class="form-group check-step-gray">
                                <label for="exampleFormControlSelect1">�峹����:</label> <select class="form-control"
                                    name="blogclass" id="addBlog">
                                    <option value="">�п��</option>
                                    <option value="�߱o��y">�߱o��y</option>
                                    <option value="���d����">���d����</option>
                                    <option value="�����v��">�����v��</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="exampleFormControlInput1">���D:</label> <input type="TEXT" name="title"
                                    size="45" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="exampleFormControlTextarea1">����:</label>
                                <!--                     <input type="TEXTAREA" name="text" size="45" -->
                                <%-- 					value="<%=(blogVO == null) ? "��Φ��\" : blogVO.getText()%>" /> --%>

                                <textarea class="form-control" name="text" id="exampleFormControlTextarea1" rows="6" />
                                </textarea>
                            </div>
                            <div></div>

                            <div class="custom-file" id="showPhoto" style="display:none"> 
                                <input type="file" name="photo" class="custom-file-input" id="photo2">
                                <label class="custom-file-label" for="customFile">Choose
                                    photo</label>
                                    <img width="400" id="photopreview" style="margin-top:30px; margin-left:220px">
                            </div>

                            <div class="custom-file" id="showVideo" style="display:none"
                                style="margin-top: 46px; padding-left: 10.4px;">
                                <input type="file" name="video" class="custom-file-input" id="video2"">
								<label class=" custom-file-label" for="customFile">Choose
                                video</label>
                            </div>

                            <div style=" margin-top:200px; margin-left:350px">
                                <input type="hidden" name="action" value="insert">
                                <input type="submit" value="submit" class="btn btn-secondary mb-2" >
                            </div>

                        </FORM>

                        <!-- 	<FORM METHOD="post" -->
                        <%-- 		ACTION="<%=request.getContextPath()%>/blog/BlogServlet" name="form1" --%>
                        <!-- 		enctype="multipart/form-data"> -->
                        <!-- 		<table> -->
                        <!-- 			<tr> -->
                        <!-- 				<td>�@��|���s��:</td> -->
                        <!-- 				<td><input type="TEXT" name="memberid" size="45" -->
                        <%-- 					value="<%=(blogVO == null) ? "M001" : blogVO.getMemberId()%>" /></td> --%>
                        <%-- 				<td><c:forEach var="memberVO" items="${memberSvc.all}"> --%>
                        <%-- 						<c:if test="${blogVO.memberId eq memberVO.memberid}"> --%>
                        <%-- 							${memberVO.name} --%>
                        <%-- 						</c:if> --%>
                        <%-- 					</c:forEach></td> --%>
                        <!-- 			</tr> -->
                        <!-- 			<tr> -->
                        <!-- 				<td>�峹����:</td> -->
                        <!-- 				<td><select name="blogclass" id="addBlog"> -->
                        <!-- 						<option value="">�п��</option> -->
                        <!-- 						<option value="�߱o��y">�߱o��y</option> -->
                        <!-- 						<option value="���d����">���d����</option> -->
                        <!-- 						<option value="�����v��">�����v��</option> -->
                        <!-- 				</select></td> -->

                        <!-- 			<tr> -->
                        <!-- 				<td>���D:</td> -->
                        <!-- 				<td><input type="TEXT" name="title" size="45" -->
                        <%-- 					value="<%=(blogVO == null) ? "�G��" : blogVO.getTitle()%>" /></td> --%>
                        <!-- 			</tr> -->
                        <!-- 			<tr> -->
                        <!-- 				<td>����:</td> -->
                        <!-- 				<td><input type="TEXTAREA" name="text" size="45" -->
                        <%-- 					value="<%=(blogVO == null) ? "��Φ��\" : blogVO.getText()%>" /></td> --%>
                        <!-- 			</tr> -->
                        <!-- 			<tr style="display:none" id="showPhoto"> -->
                        <!-- 				<td>�Ӥ�:</td> -->
                        <!-- 				<td><input type="file" name="photo" id="photo2" size="45" /></td> -->
                        <!-- 			</tr> -->
                        <!-- 			<tr> -->
                        <!-- 				<td></td> -->
                        <!-- 				<td><img width="400" id="photopreview"></td> -->
                        <!-- 			</tr> -->
                        <!-- 			<tr style="display:none" id="showVideo"> -->
                        <!-- 				<td>�v��:</td> -->
                        <!-- 				<td><input type="file" name="video" id="video2" size="45" /></td> -->
                        <!-- 			</tr> -->

                        <!-- 		</table> -->
                        <!-- 		<br> <input type="hidden" name="action" value="insert"> <input -->
                        <!-- 			type="submit" value="�e�X�s�W"> -->
                        <!-- 	</FORM> -->

                    </div>

                    <div class="col-md-4 col-lg-3">
                        <form class="" action="search-result.html">
                            <div class="card shadow-none bg-transparent">
                                <h3 class="card-header bg-warning font-weight-bold rounded-top text-white">Search</h3>
                                <div class="card-body border border-top-0 rounded-bottom">
                                    <div class="input-group border-bottom pb-3 pt-4">
                                        <input type="text" class="form-control border-0 px-1"
                                            placeholder="Enter Your Search" aria-describedby="basic-addon2"> <span
                                            class="input-group-addon" id="basic-addon2"> <input
                                                class="btn btn-sm btn-warning text-uppercase text-white shadow-sm"
                                                type="submit" value="Search">
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </form>

                        <div class="card shadow-none bg-transparent">
                            <h4 class="card-header font-weight-bold bg-success rounded-top text-white">Categories</h4>
                            <div class="card-body border border-top-0 rounded-bottom">
                                <ul class="list-unstyled mb-0">
                                    <li class="mb-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/blog/BlogServlet?action=getAllExperience&blogClass=�߱o��y">�߱o��y</a>
                                    </li>

                                    <li class="my-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/blog/BlogServlet?action=getAllExperience&blogClass=���d����">���d����</a>
                                    </li>
                                    <li class="my-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/blog/BlogServlet?action=getAllExperience&blogClass=�����v��">�����v��</a>
                                    </li>
                                    <li class="my-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/blog_Save/Blog_SaveServlet?action=getUserSaveBlog&memberId=M001">�峹����</a>
                                    </li>
                                    <li class="mt-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/front-end/blog/addBlog.jsp">�o��</a></li>
                                    <li class="mt-2"><a
                                            class="text-muted font-weight-medium d-block border rounded py-2 pl-3"
                                            href="<%=request.getContextPath()%>/blog/BlogServlet?action=getUserBlog&memberId=M001">�ڪ��峹</a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="mb-4">
                            <!-- 					<h3 class="bg-purple rounded-top font-weight-bold text-white mb-0 py-2 px-4">Related Course</h3> -->
                            <div class="border border-top-0 rounded">
                                <ul class="list-unstyled mb-0">



                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="mt-6">
                <!-- 				====================================
<!-- �X�X�X	PAGINATION -->
                <!-- ===================================== -->
                -->
                <!-- 				<section class="py-5"> -->
                <!-- 					<div class="container"> -->
                <!-- 						<nav aria-label="Page navigation example"> -->
                <!-- 							<nav aria-label="Page navigation example"> -->
                <!-- 								<ul class="pagination justify-content-center align-items-center"> -->
                <!-- 									<li class="page-item"><a class="page-link" href="#"> <i -->
                <!-- 											class="fa fa-arrow-left mr-1" aria-hidden="true"></i> Prev -->
                <!-- 									</a></li> -->
                <!-- 									<li class="page-item"><a class="page-link" href="#">1</a></li> -->
                <!-- 									<li class="page-item"><a class="page-link" href="#">2</a></li> -->
                <!-- 									<li class="page-item"><a class="page-link" href="#">3</a></li> -->
                <!-- 									<li class="page-item"><a class="page-link" href="#">...</a></li> -->
                <!-- 									<li class="page-item"><a class="page-link" href="#">15</a></li> -->
                <!-- 									<li class="page-item"><a class="page-link" href="#"> -->
                <!-- 											Next <i class="fa fa-arrow-right ml-1" aria-hidden="true"></i> -->
                <!-- 									</a></li> -->
                <!-- 								</ul> -->
                <!-- 							</nav> -->
                <!-- 						</nav> -->
                <!-- 					</div> -->
                <!-- 				</section> -->

            </div>
        </section>

    </div>



 


    <script>
        var pictureForm = document.getElementById("photo2");
        pictureForm.addEventListener("change", function () {
            var file = this.files[0];
            var reader = new FileReader();
            reader.onload = function (event) {
                var source = event.target.result;
                var img = document.getElementById("photopreview");
                img.src = source;
            };
            reader.readAsDataURL(file);

        });

        $("#addBlog").change(function () {
            let video = $("#addBlog").val();

            if (video == '�����v��') {
                $("#showPhoto").hide();
                $("#showVideo").show();
            } else {
                console.log('photo');
                $("#showPhoto").show();
                $("#showVideo").hide();
            }

        });
    </script>
<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>


</html>