<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="/front-end/header.jsp" flush="true" />


    <section class="ftco-section ftco-no-pb ftco-no-pt ftco-program bg-light" id="programs-section">
      <div class="container">
        <div class="row no-gutters">
          <div class="col-md-4 ftco-animate py-5 nav-link-wrap js-fullheight">
            <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
              <a class="nav-link px-4 active" id="v-pills-1-tab" data-toggle="pill" href="#v-pills-1" role="tab" aria-controls="v-pills-1" aria-selected="true"><span class="mr-3 flaticon-gym"></span> 健身訓練計畫</a>

              <a class="nav-link px-4" id="v-pills-2-tab" data-toggle="pill" href="#v-pills-2" role="tab" aria-controls="v-pills-2" aria-selected="false"><span class="mr-3 flaticon-body"></span> 健身訓練運動</a>

              <a class="nav-link px-4" id="v-pills-4-tab" data-toggle="pill" href="#v-pills-4" role="tab" aria-controls="v-pills-4" aria-selected="false"><span class="mr-3 flaticon-abs"></span> 我的健身課表</a>

              <a class="nav-link px-4" id="v-pills-5-tab" data-toggle="pill" href="#v-pills-5" role="tab" aria-controls="v-pills-5" aria-selected="false"><span class="mr-3 flaticon-running"></span> 我的健身紀錄</a>

<!--               <a class="nav-link px-4" id="v-pills-6-tab" data-toggle="pill" href="#v-pills-6" role="tab" aria-controls="v-pills-6" aria-selected="false"><span class="mr-3 flaticon-meditation"></span> Power Yoga</a> -->

<!--               <a class="nav-link px-4" id="v-pills-7-tab" data-toggle="pill" href="#v-pills-7" role="tab" aria-controls="v-pills-7" aria-selected="false"><span class="mr-3 flaticon-aerobic"></span> Aerobics Program</a> -->

<!--             	<a class="nav-link px-4" id="v-pills-08-tab" data-toggle="pill" href="#v-pills-08" role="tab" aria-controls="v-pills-08" aria-selected="false"><span class="mr-3 flaticon-gym"></span> Crossfit Program</a> -->
            </div>
          </div>
          <div class="col-md-8 ftco-animate p-4 p-md-5 d-flex align-items-center js-fullheight">
            
            <div class="tab-content pl-md-5" id="v-pills-tabContent">

              <div class="tab-pane fade show active py-0" id="v-pills-1" role="tabpanel" aria-labelledby="v-pills-1-tab">
                <span class="icon mb-3 d-block flaticon-gym"></span>
                <h2 class="mb-4">健身訓練計畫</h2>
                <p>提供個人計畫訓練開始，同時還能根據自己的運動程度，計畫運動課表。</p>
                <p>透過系統性的設計課表，讓生活繁忙的你，也能有效的健身，保持健康</p>
                <p><a href="${pageContext.request.contextPath}/front-end/trainingcls/addMyCls.jsp" class="btn btn-primary px-4 py-3">開始我的訓練計畫</a></p>
              </div>

              <div class="tab-pane fade py-0" id="v-pills-2" role="tabpanel" aria-labelledby="v-pills-2-tab">
                <span class="icon mb-3 d-block flaticon-body"></span>
                <h2 class="mb-4">健身訓練動作</h2>
                <p>針對身體不同部位，安排不同訓練動作，並提供專業教學影片</p>
                <p>讓你不需要健身教練，也能透過專業教學，學習如何正確地掌握健身</p>
                <a href="${pageContext.request.contextPath}/front-end/action/listAllAction.jsp" role="button" class="btn btn-primary px-4 py-3">瀏覽訓練動作</a>
              </div>

<!--               <div class="tab-pane fade py-0" id="v-pills-3" role="tabpanel" aria-labelledby="v-pills-3-tab"> -->
<!--                 <span class="icon mb-3 d-block flaticon-woman"></span> -->
<!--                 <h2 class="mb-4">個人專屬</h2> -->
<!--                 <p>設計屬於自己的專屬課表</p> -->
<!--                 <p>打造獨一無二的專屬健身計畫</p> -->
<%--                 <p><a href="${pageContext.request.contextPath}/front-end/trainingcls/addMyCls.jsp" class="btn btn-primary px-4 py-3">個人專屬</a></p> --%>
<!--               </div> -->

              <div class="tab-pane fade py-0" id="v-pills-4" role="tabpanel" aria-labelledby="v-pills-4-tab">
                <span class="icon mb-3 d-block flaticon-abs"></span>
                <h2 class="mb-4">我的健身課表</h2>
                <p>安排一個專於自己的課表</p>
                <p>身體主要由胸、背和臀等大肌群所組合，又將各大肌群各自細分</p>
                <p>一起來安排屬於自己個運動部位的課表吧!</p>
                <p><a href="${pageContext.request.contextPath}/front-end/trainingcls/showmycls.jsp" class="btn btn-primary px-4 py-3">健身課表</a></p>
              </div>

              <div class="tab-pane fade py-0" id="v-pills-5" role="tabpanel" aria-labelledby="v-pills-5-tab">
                <span class="icon mb-3 d-block flaticon-running"></span>
                <h2 class="mb-4">我的健身紀錄</h2>
                <p>有效掌握每一次訓練計畫的詳細記錄</p>
                <p>不論是想要增肌減脂，或是挑塑曲線，都能透訓練紀錄，隨時掌握您的健身紀錄</p>
                <p><a href="${pageContext.request.contextPath}/front-end/traininghist/showmyhist.jsp" class="btn btn-primary px-4 py-3">健身紀錄</a></p>
              </div>

<!--               <div class="tab-pane fade py-0" id="v-pills-6" role="tabpanel" aria-labelledby="v-pills-6-tab"> -->
<!--                 <span class="icon mb-3 d-block flaticon-meditation"></span> -->
<!--                 <h2 class="mb-4">Power Yoga</h2> -->
<!--                 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nesciunt voluptate, quibusdam sunt iste dolores consequatur</p> -->
<!--                 <p>Inventore fugit error iure nisi reiciendis fugiat illo pariatur quam sequi quod iusto facilis officiis nobis sit quis molestias asperiores rem, blanditiis! Commodi exercitationem vitae deserunt qui nihil ea, tempore et quam natus quaerat doloremque.</p> -->
<!--                 <p><a href="#" class="btn btn-primary px-4 py-3">Learn More</a></p> -->
<!--               </div> -->

<!--               <div class="tab-pane fade py-0" id="v-pills-7" role="tabpanel" aria-labelledby="v-pills-7-tab"> -->
<!--                 <span class="icon mb-3 d-block flaticon-aerobic"></span> -->
<!--                 <h2 class="mb-4">Aerobics Program</h2> -->
<!--                 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nesciunt voluptate, quibusdam sunt iste dolores consequatur</p> -->
<!--                 <p>Inventore fugit error iure nisi reiciendis fugiat illo pariatur quam sequi quod iusto facilis officiis nobis sit quis molestias asperiores rem, blanditiis! Commodi exercitationem vitae deserunt qui nihil ea, tempore et quam natus quaerat doloremque.</p> -->
<!--                 <p><a href="#" class="btn btn-primary px-4 py-3">Learn More</a></p> -->
<!--               </div> -->

<!--               <div class="tab-pane fade py-0" id="v-pills-08" role="tabpanel" aria-labelledby="v-pills-08-tab"> -->
<!--                 <span class="icon mb-3 d-block flaticon-gym"></span> -->
<!--                 <h2 class="mb-4">Crossfit Program</h2> -->
<!--                 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nesciunt voluptate, quibusdam sunt iste dolores consequatur</p> -->
<!--                 <p>Inventore fugit error iure nisi reiciendis fugiat illo pariatur quam sequi quod iusto facilis officiis nobis sit quis molestias asperiores rem, blanditiis! Commodi exercitationem vitae deserunt qui nihil ea, tempore et quam natus quaerat doloremque.</p> -->
<!--                 <p><a href="#" class="btn btn-primary px-4 py-3">Learn More</a></p> -->
<!--               </div> -->
            </div>
          </div>
        </div>
      </div>
    </section>
    


<jsp:include page="/front-end/footer.jsp" flush="true" />

</body>
</html>