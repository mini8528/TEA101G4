<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="css/friendchat.css" type="text/css" />
<style type="text/css">

</style>
<title>Message</title>
</head>
<body onload="connect();" onunload="disconnect();" id="body" class="up-scroll" data-spy="scroll"
	data-target=".element-right-sidebar">
	<jsp:include page="/front-end/header.jsp" flush="true" />
	
<!-- 	<div id = "chat"> -->
	
	
	 <div class="container2" style="background-color: #FFFFF">
	 <div class="row">
	<div class="col-md-6">
	<div id="row"></div>
	</div>
	<div class="col-md-6" >
	<h3 id="statusOutput" class="statusOutput"></h3>
	<div id="messagesArea" class="panel message-area" style=" display: inline-block" ></div>
	<div class="panel input-area">
		<input id="message" class="text-field" type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage();" /> 
		<input type="submit" id="sendMessage" class="button" value="Send" onclick="sendMessage();" /> 
		<input type="button" id="connect" class="button" value="Connect" onclick="connect();" /> 
		<input type="button" id="disconnect" class="button" value="Disconnect" onclick="disconnect();" />
	</div>
	</div>
	</div>
	</div>
	<jsp:include page="/front-end/footer.jsp" flush="true" />
</body>
<script>
	
	var MyPoint = "/FriendWS/${memberid}"; //EL NamesServlet setAttribute
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
	var self = '${memberid}';
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);
		webSocket.onopen = function(event) {
			console.log("Connect Success!");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			
			if ("open" === jsonObj.type) { //與FriendWS做比對
				refreshFriendList(jsonObj); //110
			} else if ("history" === jsonObj.type) {
				messagesArea.innerHTML = '';
				var ul = document.createElement('ul');
				ul.id = "area";
				messagesArea.appendChild(ul);
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				var friendno = $("#statusOutput").text();
				console.log("friendno =" + friendno);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					var li = document.createElement('li');
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分(css)
					historyData.sender === self ? li.className += 'me' : li.className += 'friend';
					li.className === 'me'? li.innerHTML = '<div class="avatar"> '+ showMsg + '</div>': li.innerHTML = '<div class="avatar"><img style="height: 50px " src="<%=request.getContextPath()%>/front-end/member/memberShow.do?memberid='+ friendno +'"></div> <div class="msg">' + showMsg + '</div>';
					ul.appendChild(li);
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {
				//如果是自己，顯示訊息
				if (jsonObj.sender === self) {
					var li = document.createElement('li');
					console.log("jsonObj.sender === self");
					jsonObj.sender === self ? li.className += 'me' : li.className += 'friend';
					li.innerHTML = jsonObj.message;
					console.log(li);
					document.getElementById("area").appendChild(li);
					messagesArea.scrollTop = messagesArea.scrollHeight;
					//避免共用視窗，寄訊息的不是朋友編號，不顯示
				} else if (jsonObj.sender !== localStorage.getItem("friend")) {
					return;
				} else {
					var li = document.createElement('li');
					var friendno = $("#statusOutput").text();
					jsonObj.sender === self ? li.className += 'me' : li.className += 'friend';
					li.className === 'me'? li.innerHTML = '<div class="avatar"> '+ showMsg + '</div>': li.innerHTML = '<div class="avatar"><img style="height: 50px " src="<%=request.getContextPath()%>/front-end/member/memberShow.do?memberid='+ friendno +'"></div> <div class="msg">' + jsonObj.message + '</div>';
					
					console.log(li);
					document.getElementById("area").appendChild(li);
					messagesArea.scrollTop = messagesArea.scrollHeight;
				}
			} else if ("close" === jsonObj.type) {
				refreshFriendList(jsonObj);
			}
			
		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	
	function sendMessage() {
		var inputMessage = document.getElementById("message");
		var friend = statusOutput.textContent;
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else if (friend === "") {
			alert("Choose a friend");
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : friend,
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	
	// 有好友上線或離線就更新列表
	function refreshFriendList(jsonObj) {
		console.log("refresh friend list");
		var friends = jsonObj.users;
		var friendno = statusOutput.textContent;
		var row = document.getElementById("row");
		console.log("friendno = " + friendno);
		console.log("friends = " + friends);
		console.log("row = " + row);
		row.innerHTML = '';
		for (var key in friends) {
			console.log(friends[key]);
			if (key === self) { continue; } //去除自己
			row.innerHTML +='<div id=' + key +  ' data-no=' + key + ' class="column" name="friendName" value=' + key + '>'+'<img class="img-circle" style="width: 40px" src="<%=request.getContextPath()%>/front-end/member/memberShow.do?memberid='+ key +'">'+'<h2 class="namerow">' +friends[key] + '</h2></div>';
			
		}
			addListener();
		
	}
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
	function addListener() {
		$(".column").on("click", function(e) {
			$(".column").removeClass("-on");
			$(this).addClass("-on");
			  console.log("click!");
			  var friend = $(this).attr("data-no");
			  localStorage.setItem("friend", friend);
			  console.log(friend);
			  updateFriendName(friend);
			  var jsonObj = {
						"type" : "history",
						"sender" : self,
						"receiver" : friend,
						"message" : ""
					};
			  webSocket.send(JSON.stringify(jsonObj));
			 });
		
	}
	
	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}
	
	function updateFriendName(name) {
		statusOutput.innerHTML = name;
	}
</script>
</html>