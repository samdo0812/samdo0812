<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="dron.board.db.BoardBean"%> 
<%@ page language="java" contentType="text/html; charset=EUC-KR"%> 
<% 
    String username=(String)session.getAttribute("username"); 
    BoardBean board=(BoardBean)request.getAttribute("boarddata"); 
%> 
<html>
<head>
<head>
	<meta name="viewport" content="width=device-width" , initial-scale='1'>	<!-- 기기에 따라 화면 크기 -->
	<title>3조 조미정</title>
	<link rel="stylesheet" href="./Resources/css/bootstrap.css">	<!-- 외부적으로 만들어진 css 디자인 참조(부트 스트랩) -->
	<link rel="stylesheet" href="./Resources/css/dronStcss.css">  <!-- 외부적으로 dronStcss 다자인 참조(내가 만든거) -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="./Resources/js/bootstrap.js"></script>
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
					<li><a href="./BoardList.bo">제작 과정</a></li>
					<li class="dropdown">
						<a href="memberIntro.jsp" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">조원 소개<span class="caret"></span></a>
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
	

<!-- ---------------------------------------------------------------------------------------------------------- -->

	<!-- 게시판 등록 --> 
<div class="container">
<form  name="boardform" action="./BoardAddAction.bo" method="post" enctype="multipart/form-data"> 
<input type="hidden" name="BOARD_ID" value="<%=username %>"> 
<table class="table table-bordered"> 
    <tr align="center" valign="middle"> 
        <td colspan="5"> 제작 과정</td> 
    </tr> 
    <tr> 
        <td style="font-family:돋움; font-size:12" height="16"> <!-- 돋움체 많이봐서 소름 돋움 -->
            <div align="center">글쓴이</div> 
        </td> 
        <td> 
            <%=username %> 
        </td> 
    </tr> 
        <tr> 
        <td style="font-family:돋움; font-size:12" height="16"> 
            <div align="center">제 목</div> 
        </td> 
        <td> 
            <input name="BOARD_SUBJECT" type="text" size="50" maxlength="100" value="" class="form-control"/> <!-- class="form-control" 테이블 크게에 맞게 맞춰줌 -->
        </td>         
    </tr> 
    <tr> 
        <td style="font-family:돋움; font-size:12" height="16"> 
            <div align="center">내 용</div> 
        </td> 
        <td> 
            <textarea name="BOARD_CONTENT" cols="67" rows="15" class="form-control"></textarea>             
        </td>         
    </tr> 
    <tr> 
        <td style="font-family:돋움 ; font-size:12"> 
            <div align="center">파일첨부</div> 
        </td> 
        <td style="font-family:돋움 ; font-size:12"> 
            <input name="BOARD_FILE" type="file"/> 
        </td>     
    </tr> 
     
    <tr bgcolor="cccccc"> 
        <td colspan="2" style="height:1px;"></td> 
    </tr> 
     
    <tr><td colspan="2">&nbsp;</td></tr> 
    <tr align="center" valign="middle"> 
        <td colspan="5"> 
            <a href="javascript:boardform.submit()">[등록]</a>&nbsp;&nbsp; 
            <a href="javascript:history.go(-1)">[뒤로]</a>             
        </td> 
    </tr> 
</table> 
</form> 
</div>
<!-- ---------------------------------------------------------------------------------------------- -->

<footer  style="background-color: #000000;">
		<div class="container">
		<br>
		<div class="row"> <!-- row로 각각 공간을 나눔 -->
			<div class="col-sm-2" style="text-algin: center;"><h5 style="color:white">Copyright &copy; 2018</h5><h5 style="color:white">김도훈(DoHoon Kim)</h5></div> <!-- 12개중 2개의 칸 -->
			<div class="col-sm-3" style="color:white"><h4>비트 컴퓨터 고급과정 5기 3조
				</h4><p style="color:white"> 음성인식 AI를 통한 드론 제어</p><p style="color:white">MVC2 패턴으로 홈페이지 제작</p></div>	<!-- 4개의 칸 -->
			<div class="col-sm-2"><h4 style="text-align: center; color:white;">네비게이션</h4>
				<div class="list=group">
					<a href="./main.dron" class="list-group-item">메인 가기</a>
					<a href="#" class="list-group-item">제작 과정</a>
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
</body>
</html>

	
</body>
</html>