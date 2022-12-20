<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>main</title>
    <link rel="stylesheet" href="css/main.css" > 
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous">  
    </script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>
    <script src="js/main.js"></script> 
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
  <div id="logo">
    <div class="a">
      <span class="banddy"> banddy </span><span class="logo_text" style="font-style: italic; color: rgb(83, 105, 83); font-size: 15px;"> _ is a space for the group members.</span>
    </div>
    
   </div>  
</header>
  
<main>
  <section>
    <article>
      <div id="seach">
        
          <form class="search-container">
            <input type="text" id="search-bar" placeholder="검색어를 입력하세요.">
            <a href="https://www.btc.ac.kr/"><img class="search-icon" src="pic/search (2).png"></a>
          </form>
       
      </div>
      <div id="ar">
        <p class="ar_bar">
          
        </p>
      </div>
    </article> 
    <article>  
    <div id="grid_category">
      <div class="cards">

        <div class="card">
          <div class="card__image-holder">
            <img class="card__image" src="pic/문화.jpg" alt="문화/예술" />
          </div>
          <div class="card-title">
            <a href="#" class="toggle-info btn">
              <span class="left"></span>
              <span class="right"></span>
            </a>
            <h2>
              문화/예술
              
            </h2>
          </div>
          <div class="card-flap flap1">
            <div class="card-description">
              - 그림감상, 작가의조언, 수채화, 아크릴화, 유화, 회화팁, 문화행사소식 등을 나누는 공간 입니다.<br>
              - 수채화를 좋아하고 수채화를 그리고 수채화를 함께 전시하는 사람들.<br>
              <br>더 많은 글들을 보려면 아래 바로가기 버튼을 눌러주세요.<br>
              
            </div>
            <div class="card-flap flap2">
              <div class="card-actions">
                <a href="#" class="btn">바로가기</a>
              </div>
            </div>
          </div>
        </div>
      
        <div class="card">
          <div class="card__image-holder">
            <img class="card__image" src="pic/고양이.jpg" alt="고양이" />
          </div>
          <div class="card-title">
            <a href="#" class="toggle-info btn">
              <span class="left"></span>
              <span class="right"></span>
            </a>
            <h2>
              반려동물
              
            </h2>
          </div>
          <div class="card-flap flap1">
            <div class="card-description">
              - 이곳은 아기동물들의 사진을 공유하는 곳입니다!!<br>
              - 고양이와 함께 살아가는 집사들 의 뒷담화!<br>
              <br>더 많은 글들을 보려면 아래 바로가기 버튼을 눌러주세요.<br>
            </div>
            <div class="card-flap flap2">
              <div class="card-actions">
                <a href="#" class="btn">바로가기</a>
              </div>
            </div>
          </div>
        </div>
      
        <div class="card">
          <div class="card__image-holder">
            <img class="card__image" src="pic/캠핑.jpg" alt="캠핑" />
          </div>
          <div class="card-title">
            <a href="#" class="toggle-info btn">
              <span class="left"></span>
              <span class="right"></span>
            </a>
            <h2>
              여행/캠핑
              
            </h2>
          </div>
          <div class="card-flap flap1">
            <div class="card-description">
              - 국내여행에서 세계여행까지! 즐거운 여행 후기.<br>
              - 여행을좋아하는 사람들~<br>
              <br>더 많은 글들을 보려면 아래 바로가기 버튼을 눌러주세요.<br>
            </div>
            <div class="card-flap flap2">
              <div class="card-actions">
                <a href="#" class="btn">바로가기</a>
              </div>
            </div>
          </div>
        </div>
      
        <div class="card">
          <div class="card__image-holder">
            <img class="card__image" src="pic/일상.jpg" alt="일상" />
          </div>
          <div class="card-title">
            <a href="#" class="toggle-info btn">
              <span class="left"></span>
              <span class="right"></span>
            </a>
            <h2>
              일상/이야기
              
            </h2>
          </div>
          <div class="card-flap flap1">
            <div class="card-description">
              - 마음 따뜻한 사람들의 공간<br>
              - 아침편지, 좋은글, 힐링글 모음<br>
              <br>더 많은 글들을 보려면 아래 바로가기 버튼을 눌러주세요.<br>
            </div>
            <div class="card-flap flap2">
              <div class="card-actions">
                <a href="#" class="btn">바로가기</a>
              </div>
            </div>
          </div>
        </div>
      
        <div class="card">
          <div class="card__image-holder">
            <img class="card__image" src="pic/공부.jpg" alt="공부" />
          </div>
          <div class="card-title">
            <a href="#" class="toggle-info btn">
              <span class="left"></span>
              <span class="right"></span>
            </a>
            <h2>
              교육/공부
             
            </h2>
          </div>
          <div class="card-flap flap1">
            <div class="card-description">
              - 수학문제 혹은 개념에 대한 질문,토론방입니다.<br>
              - 하루 10분 영어공부!!<br>
              <br>더 많은 글들을 보려면 아래 바로가기 버튼을 눌러주세요.<br>
            </div>
            <div class="card-flap flap2">
              <div class="card-actions">
                <a href="#" class="btn">바로가기</a>
              </div>
            </div>
          </div>
        </div>
      
        <div class="card">
          <div class="card__image-holder">
            <img class="card__image" src="pic/모임.jpg" alt="모임" />
          </div>
          <div class="card-title">
            <a href="#" class="toggle-info btn">
              <span class="left"></span>
              <span class="right"></span>
            </a>
            <h2>
              친목/모임
             
            </h2>
          </div>
          <div class="card-flap flap1">
            <div class="card-description">
              - 아름다운 사람들의 밝고 따뜻한 이야기<br>
              - 뉴질랜드 워홀 소통을 위한 방입니다.<br>
              <br>더 많은 글들을 보려면 아래 바로가기 버튼을 눌러주세요.<br>
            </div>
            <div class="card-flap flap2">
              <div class="card-actions">
                <a href="#" class="btn">바로가기</a>
              </div>
            </div>
          </div>
        </div>
      
        <div class="card">
          <div class="card__image-holder">
            <img class="card__image" src="pic/스포츠.jpg" alt="스포츠" />
          </div>
          <div class="card-title">
            <a href="#" class="toggle-info btn">
              <span class="left"></span>
              <span class="right"></span>
            </a>
            <h2>
              스포츠/레저
              
            </h2>
          </div>
          <div class="card-flap flap1">
            <div class="card-description">
              - 둘레길 걷기 , 전국 도보여행 , 등산 , 배낭여행<br>
              - 부산 아마추어 축구클럽 모임<br>
              <br>더 많은 글들을 보려면 아래 바로가기 버튼을 눌러주세요.<br>
            </div>
            <div class="card-flap flap2">
              <div class="card-actions">
                <a href="#" class="btn">바로가기</a>
              </div>
            </div>
          </div>
        </div>
      
        <div class="card">
          <div class="card__image-holder">
            <img class="card__image" src="pic/코딩.jpg" alt="컴퓨터" />
          </div>
          <div class="card-title">
            <a href="#" class="toggle-info btn">
              <span class="left"></span>
              <span class="right"></span>
            </a>
            <h2>
              IT/컴퓨터
             
            </h2>
          </div>
          <div class="card-flap flap1">
            <div class="card-description">
              - IT, 자격증 정보교류 및 프로그래밍 개발<br>
              - IT로 말하고 IT로 시작하는 일상 속 함께할래요?<br>
              <br>더 많은 글들을 보려면 아래 바로가기 버튼을 눌러주세요.<br>
            </div>
            <div class="card-flap flap2">
              <div class="card-actions">
                <a href="#" class="btn">바로가기</a>
              </div>
            </div>
          </div>
        </div>
      
        <div class="card">
          <div class="card__image-holder">
            <img class="card__image" src="pic/취업.jpg" alt="취업" />
          </div>
          <div class="card-title">
            <a href="#" class="toggle-info btn">
              <span class="left"></span>
              <span class="right"></span>
            </a>
            <h2>
              취업/자격증
             
            </h2>
          </div>
          <div class="card-flap flap1">
            <div class="card-description">
              - 취업을 준비하는 엄마들의 스터디 모임<br>
              - 한국사능력검정시험을 준비하는 공간입니다.<br>
              <br>더 많은 글들을 보려면 아래 바로가기 버튼을 눌러주세요.<br>
            </div>
            <div class="card-flap flap2">
              <div class="card-actions">
                <a href="#" class="btn">바로가기</a>
              </div>
            </div>
          </div>
        </div>
      
      </div>
      </div>
    </article>
  </section>
</main>  

<footer>
  <div id="foot">
    <nav>
      <a href="https://band.us/band/89389707?referrer=" target="_blank">band </a> |
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