<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>signIn</title>
    <link rel="stylesheet" href="css/sign.css" > 
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

</head>

<body>
<jsp:include page="menu_sign.jsp" />
 <!--   
    <header>
        <div id="grid_header">
          <nav class="header_top" >
              <ul class="top_member_box">
                  <li><a href="signUp.jsp">Sign Up</a></li>
                  <li><a href="signIn.jsp">Sign In</a></li>
                  <li><a href="write.jsp">Post</a></li>
                  <li><a href="list.do">Board List</a></li>
                  <li><a href="main.jsp">Home</a></li>
                  <li><a href="signOutAction.jsp">Sign Out</a></li>
              </ul>
            </nav>
          </div>
          <div id="up_a">
             <div class="up_b">
               <span class="banddy"> banddy </span><span class="logo_text" style="font-style: italic; color: rgb(195, 209, 189); font-size: 15px;"> _ is a space for the group members.</span>
            </div>
          </div>
    </header>
-->

<main>
    <section>
      <div class="login-page">
        <div class="form">
    
          <form method = "post" action = "signInAction.jsp" class="login-form">
            <input type="text" placeholder="user name" name = "user_ID" >
            <input type="password" placeholder="password" name = "user_PW" >
            <input type="submit" value = "Sign In" >
            
            <p class="message">Not registered? <a href="signUp.jsp">Create an account</a></p>
          </form>
        </div>
      </div>
    </section>
    <h4>유저, 관리자 1:1 채팅 - 유저</h4>
  <!-- 채팅 영역 운영자, 유저간의 채팅 send-유저, 받는쪽이 관리자 
   호스트네임 -> admin_broadsocket
   관리자로 로그인하면 유저로부터온 채팅창을 확인할 수 있다.
   채팅창이 아래쪽에 하나더 만들어지게-->
  <form>
    <!-- 텍스트 박스에 채팅의 내용을 작성한다. -->
    <input id="textMessage" type="text" onkeydown="return enter()">
    <!-- 서버로 메시지를 전송하는 버튼 -->
    <input onclick="sendMessage()" value="Send" type="button">
  </form>
  <br />
  <!-- 서버와 메시지를 주고 받는 콘솔 텍스트 영역 -->
  <textarea id="messageTextArea" rows="10" cols="50" disabled="disabled"></textarea>
  <script type="text/javascript">
    // 서버의 broadsocket의 서블릿으로 웹 소켓을 한다.
    var webSocket = new WebSocket("ws://localhost:8090/boardsite/admin_broadsocket");
    // 콘솔 텍스트 영역
    var messageTextArea = document.getElementById("messageTextArea");
    // 접속이 완료되면
    webSocket.onopen = function(message) {
      // 콘솔에 메시지를 남긴다.
      messageTextArea.value += "Server connect...\n";
    };
    // 접속이 끝기는 경우는 브라우저를 닫는 경우이기 떄문에 이 이벤트는 의미가 없음.
    webSocket.onclose = function(message) { };
    // 에러가 발생하면
    webSocket.onerror = function(message) {
    // 콘솔에 메시지를 남긴다.
    messageTextArea.value += "error...\n";
    };
    // 서버로부터 메시지가 도착하면 콘솔 화면에 메시지를 남긴다.
    webSocket.onmessage = function(message) {
      messageTextArea.value += "(operator) => " + message.data + "\n";
    };
    // 서버로 메시지를 발송하는 함수
    // Send 버튼을 누르거나 텍스트 박스에서 엔터를 치면 실행
    function sendMessage() {
      // 텍스트 박스의 객체를 가져옴
      let message = document.getElementById("textMessage");
      // 콘솔에 메세지를 남긴다.
      messageTextArea.value += "(me) => " + message.value + "\n";
      // 소켓으로 보낸다.
      webSocket.send(message.value);
      // 텍스트 박스 추기화
      message.value = "";
    }
    // 텍스트 박스에서 엔터를 누르면
    function enter() {
      // keyCode 13은 엔터이다.
      if(event.keyCode === 13) {
        // 서버로 메시지 전송
        sendMessage();
        // form에 의해 자동 submit을 막는다.
        return false;
      }
      return true;
    }
  </script>
</main>
<footer>
  <div id="foot">
    <nav>
      <a href="https://band.us/band/89389707?referrer=" target="_blank">band </a> ㅣ
      <a href="https://github.com/0qkrwldud1/web_wldud.git" target="_blank">github</a> 
    </nav>
    <p>
      <span>박지영</span><br/>
      <span>e-mail : 0qkrwldud1@naver.com</span><br/>
      <span>© 2022 Copyright : 0qkrwldud1. All Rights Reserved.</span><br/>
    </p>
  </div>
</footer>
</body>
</html>