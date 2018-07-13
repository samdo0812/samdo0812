<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
String username=null; 
if(session.getAttribute("username")!=null){ 
	username=(String)session.getAttribute("username"); 
} 
%>

<html>
<head>
	<meta name="viewport" content="width=device-width" , initial-scale='1'>	<!-- 기기에 따라 화면 크기 -->
	<title>3조 조미정</title>
	<link rel="stylesheet" href="./Resources/css/bootstrap.css">	<!-- 외부적으로 만들어진 css 디자인 참조(부트 스트랩) -->
	<link rel="stylesheet" href="./Resources/css/dronStcss.css">  <!-- 외부적으로 dronStcss 다자인 참조(내가 만든거) -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="./Resources/js/bootstrap.js"></script>
	<script>
		function btn() {
			<%if(session.getAttribute("username") == null) { %>
			alert('로그인 해주세요');
			location.href="./MemberLogin.dron";
		<% } else { %>
			location.href="./BoardList.bo";
		<% } %>
		}
	</script>
</head>
<body>
	<style type="text/css">
		.jumbotron{
			background-image: url('./Resources/images/jumbotronBackGround.jpg');
			background-size: cover;	/* 배경 이미지의 크기를 맞춰줌 */
			text-shadow: black 0.2em 0.2em 0.2em;
			color: white;
		}
	</style>
	
	<nav class="navbar navbar-default" > <!-- 네비게이션 바 --> 
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				
				<a class="navbar-brand" href="./main.dron">3조 조미정</a> <!-- 링크 수정 -->
			</div>
			
			<div class="collapsed navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="main.jsp">소개<span class="sr-only"></span></a></li>
					<li><a href="javascript:void(0);" onclick="btn();">제작 과정</a></li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">조원 소개<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="./MemberIntro.dron#kim">김도훈</a></li>
							<li><a href="./MemberIntro.dron#chu">추정범</a></li>
							<li><a href="./MemberIntro.dron#lee">이은빈</a></li>
					   </ul>
					</li>
				</ul>
				<!-- 검색창 -->
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="내용을 입력하세요.">
					</div>
					<button type="submit" class="btn btn-default">검색</button>
				</form>
				
				<%if(session.getAttribute("username")==null) { %>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="/MemberJoin.dron" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">접속하기<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="./MemberLogin.dron">로그인</a></li>
							<li><a href="./MemberJoin.dron">회원가입</a></li>
					   </ul>
					</li>
				</ul>
				<% } else { %>
				<% session.setMaxInactiveInterval(180);%>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown2">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=session.getAttribute("username")%> 님 환영합니다.<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="./MemberLogoutAction.dron">로그아웃</a></li>			
					   </ul>
					</li>
				</ul>
				<% } %>
				
				
			</div>
		
		</div>
	</nav>
	
	<!-- --------------------------------------------------------------------------------------------------------------------------- -->
	<div class="container">	<!-- 점보트론 -->
		<div class="jumbotron">
			<h1 class="text-center">3조 조미정</h1>
			<!-- <p class="text-center">김도훈 추정범 이은빈</p> -->
			<p class="text-center">
				<a class="btn btn-primary btn-lg" href="#" role="button">이동</a>
			</p>
		</div>
		
	<div class="row">
		<div class="col-md-4">
			<h4 style="text-align: center;">Why 조미정?</h4>
			<p>동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세 무궁화 삼천리 화려 강산 대한사람 대한으로 길이 보전하세</p>
			<p><a class="btn btn-default" data-target="#modal" data-toggle="modal">자세히 알아보기</a></p> <!-- data-target="#id" data-toggle은 형식을 modal로 하겠다 -->
		</div>
		
		<div class="col-md-4">
			<h4 style="text-align: center;">Why Dron?</h4>
			<p>동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세 무궁화 삼천리 화려 강산 대한사람 대한으로 길이 보전하세</p>
			<p><a class="btn btn-default" data-target="#modal2" data-toggle="modal">자세히 알아보기</a></p>
		</div>
		
		<div class="col-md-4">
			<h4 style="text-align: center;">Why ?</h4>
			<p>동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세 무궁화 삼천리 화려 강산 대한사람 대한으로 길이 보전하세</p>
			<p><a class="btn btn-default" data-target="#modal3" data-toggle="modal">자세히 알아보기</a></p>
		</div>
	</div>
	<hr>
	<!-- 미디어 -->
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title"><span class="glyphicon glyphicon-pencil"></span>
			&nbsp; Language</h3>
		</div>
		<div class="panel-body">
			<div class="media">
				<div class="media-left">
					<a href="#"><img class="media-object" src="./Resources/images/android.jpg" alt="안드로이드"></a>
				</div>
				<div class="media-body">
					<h4 class="media-heading"><a href="#">Android</a>&nbsp;<span class="badge">New</span></h4>
					JAVA Android Studio
				</div>
			</div>
			<hr>
			<div class="media">
				<div class="media-left">
					<a href="#"><img class="media-object" src="./Resources/images/google.jpg" alt="구글 API"></a>
				</div>
				<div class="media-body">
					<h4 class="media-heading"><a href="#">Google</a>&nbsp;<span class="badge">New</span></h4>
					구글 API
				</div>
			</div>
			<hr>
			<div class="media">
				<div class="media-left">
					<a href="#"><img class="media-object" src="./Resources/images/jsp.png" alt="JSP"></a>
				</div>
				<div class="media-body">
					<h4 class="media-heading"><a href="#">JSP</a>&nbsp;<span class="badge">New</span></h4>
					HTML5 CSS3 JavaScript JQuery Bootstrap 
				</div>
			</div>
		</div>
	</div>
	</div>
	
	
	
	
	<!-- --------------------------------------------------------------------------------------------------------------------------- -->
	<footer style="background-color: #000000;">
		<div class="container">
		<br>
		<div class="row"> <!-- row로 각각 공간을 나눔 -->
			<div class="col-sm-2" style="text-algin: center;"><h5 style="color:white">Copyright &copy; 2018</h5><h5 style="color:white">김도훈(DoHoon Kim)</h5></div> <!-- 12개중 2개의 칸 -->
			<div class="col-sm-3" style="color:white"><h4>비트 컴퓨터 고급과정 5기 3조
				</h4><p style="color:white"> 음성인식 AI를 통한 드론 제어</p><p style="color:white">MVC2 패턴으로 홈페이지 제작</p></div>	<!-- 4개의 칸 -->
			<div class="col-sm-2"><h4 style="text-align: center; color:white;">네비게이션</h4>
				<div class="list=group">
					<a href="./main.dron" class="list-group-item">메인 가기</a>
					<a href="javascript:void(0);" onclick="btn();" class="list-group-item">제작 과정</a>
					<a href="./MemberIntro.dron#kim" class="list-group-item">조원 소개</a>
				</div>
			</div>
			<div class="col-sm-3"><h4 style="text-align: center; color:white;">Blog</h4>
				<div class="list=group">
					<a href="http://samdo0812.tistory.com/category" class="list-group-item">김도훈 컴알못IT</a>
					<a href="http://01052268641.tistory.com" class="list-group-item">추정범 CoChu</a>
					<a href="http://s262701-id.tistory.com/" class="list-group-item">이은빈 이드's All about Coding</a>
				</div>
			</div>
			<div class="col-sm-2">
			<br>
			<h4 style="text-align: center; color:white;"><a href="https://www.bufs.ac.kr">부산외국어대학교</a></h4><br>
			<h4 style="text-align: center; color:white;"><a href="http://www.bitacademy.com/">비트교육센터</a></h4>
			</div>
		</div>
		</div>
	</footer>   
	
	
	<!-- 모달창 -->
	<div class="row">
		<!-- 첫번째 모달-->
		<div class="modal" id="modal" tabindex="-1"> 
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<strong>Why? 조미정</strong>
						<button class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body" style="text-align: center;">
						<img src="./Resources/images/jo.jpg" id="imagepreview" style="width: 256px; height: 256px;">
						<br>
						빠빠빠빠빨간맛 궁금해 허니 <br>
						빠빠빠빠빨간맛 궁금해 허니 <br>
						빠빠빠빠빨간맛 궁금해 허니 <br>
					</div>
				</div>
			</div>
		</div>
		<!-- 두번째 모달 -->
		<div class="modal" id="modal2" tabindex="-1"> 
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<strong>Why? Dron</strong>
						<button class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body" style="text-align: center;">
						빠빠빠빠빨간맛 궁금해 허니 <br>
						<img src="" id="imagepreview" style="width: 256px; height: 256px;">
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 세번째 모달 -->
	<div class="modal" id="modal3" tabindex="-1"> 
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<strong>Why  ? </strong>
						<button class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body" style="text-align: center;">
						세번째 모달입니다. <br>
						<img src="" id="imagepreview" style="width: 256px; height: 256px;">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="position: fixed; bottom: 5px; right: 5px; margin-bottom: 10px; margin-right: 10px;">
		<a id="MOVE_TOP_BTN" href="./main.dron">
			<img src="./Resources/images/topButton.png">
		</a>
	</div>
</body>
</html>