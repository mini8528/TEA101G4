<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.coachClass.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	CoachClassVO coachClassVO = (CoachClassVO) request.getAttribute("coachClassVO"); //ClassDetailServlet.java(Concroller), 存入req的coachCommentVO物件
%>

<html>
<head>
<title>coachClass資料 - listOneCoachClass.jsp</title>

</head>
<body bgcolor='white'>

	<!-- ------------------以下複製到虛線----include所在位置------------------------------------------ -->
	<!-- Page Wrapper -->
	<div id="wrapper">
		<%@ include file="/back-end/component/sidebar.jsp"%>


		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->

			<div id="content">


				<%@ include file="/back-end/component/topbar.jsp"%>

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="h3 mb-4 text-gray-800">單一課程顯示</h1>

					<div>
						<!-- ---------------------------------------------------------------- -->

						<h4>
							<a href="<%=request.getContextPath()%>/back-end/coachClass/select_page.jsp">回 課程 查詢</a>
						</h4>
						<h4>
							<a href="<%=request.getContextPath()%>/back-end/coachClass/listAllCoachClass.jsp">回 課程 清單</a>
						</h4>

						<table>
							<tr>
								<th>coachClassID</th>
								<th>memberID</th>
								<th>className</th>
								<th>classContext</th>
								<th>photo</th>
								<th>startTime</th>
								<th>price</th>
								<th>quantity</th>
								<th>address</th>
								<th>addDate</th>
								<th>editDate</th>
								<th>修改</th>
								<th>刪除</th>
							</tr>
							<tr>
								<td><%=coachClassVO.getCoachClassID()%></td>
								<td><%=coachClassVO.getMemberID()%></td>
								<td><%=coachClassVO.getClassName()%></td>
								<td><%=coachClassVO.getClassContext()%></td>
								<td><img
									src="<%=request.getContextPath()%>/back-end/coachClass/coachClassShow.do?coachClassID=${coachClassVO.coachClassID}"
									width="100" height="100"></td>
								<td><%=coachClassVO.getStartTime()%></td>
								<td><%=coachClassVO.getPrice()%></td>
								<td><%=coachClassVO.getQuantity()%></td>
								<td><%=coachClassVO.getAddress()%></td>
								<td><%=coachClassVO.getAddDate()%></td>
								<td><%=coachClassVO.getEditDate()%></td>


								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/back-end/coachClass/coachClass.do"
										style="margin-bottom: 0px;">
										<input type="submit" value="修改"> <input type="hidden"
											name="coachClassID" value="${coachClassVO.coachClassID}">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/back-end/coachClass/coachClass.do"
										style="margin-bottom: 0px;">
										<input type="submit" value="刪除"> <input type="hidden"
											name="coachClassID" value="${coachClassVO.coachClassID}">
										<input type="hidden" name="action" value="delete">
									</FORM>
								</td>

							</tr>
						</table>



						<!-- -----------以下複製到虛線----------------------------以下複製到虛線------------------------- -->

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