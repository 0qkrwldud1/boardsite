<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<% request.setCharacterEncoding("UTF-8"); %>
<head>
    <meta charset="UTF-8">
   
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>signUp</title>
    <link rel="stylesheet" href="css/sign.css" > 
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

</head>

<body>
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


<main>
    <section>
      <div class="login-page">
        <div class="form">
          <!-- <form class="register-form">
            <input type="text" placeholder="name" />
            <input type="password" placeholder="password" />
            <input type="text" placeholder="email address" />
            <button>create</button>
            <p class="message">Already registered? <a href="#">Sign In</a></p>
          </form> -->
          <form  method = "post" action = "SignUpAction.jsp" class="login-form">
            <input type="text" placeholder="user id" name = "user_ID"/>
            <input type="password" placeholder="password" name = "user_PW" />
            <input type="text" placeholder="user Name" name = "user_Name"/>
            <div style = "text-align: center;">
            <div>
            <input type="radio" placeholder="user gender" name = "user_Gender" 
            autocomplate = "off" value = "남자" checked/>남자
            </div>
            <div>
            <input type="radio" placeholder="user gender" name = "user_Gender"
            autocomplate = "off" value = "여자" checked/>여자
            </div>
            </div>
            <input type="text" placeholder="user email" name = "user_Email"/>
            <input type="submit" value = "Sign Up" >
            
            <p class="message">Already registered? <a href="signIn.jsp">Sign In</a></p>
          </form>
          
        </div>
      </div>
    </section>
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