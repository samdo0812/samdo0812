<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="sun.java2d.loops.Blit"%> 
<%@page import="java.util.List"%> 
<%@page import="dron.board.db.BoardBean"%> 
<% 
String username=null; 
if(session.getAttribute("username")!=null){ 
	username=(String)session.getAttribute("username"); 
} 


List boardList =(List)request.getAttribute("boardlist"); 
int listcount=((Integer)request.getAttribute("listcount")).intValue(); 
int nowpage=((Integer)request.getAttribute("page")).intValue(); 
int maxpage=((Integer)request.getAttribute("maxpage")).intValue(); //최대 페이지수
int startpage=((Integer)request.getAttribute("startpage")).intValue(); //현재 페이지에 표시할 첫 페이지 수
int endpage=((Integer)request.getAttribute("endpage")).intValue(); //현제 페이지에 표시 할 끝 페이지 수

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




<!-- ------------------------------------------------- -->
<!-- 게시판 리스트 -->

<div class="container"> 
<table class="table table-striped"> 
    <tr align="center" valign="middle"> 
        <td colspan="4">제작 과정</td> 
        <td align=right> <font size=2><span class="glyphicon glyphicon-pencil"></span>&nbsp;글 개수 : ${listcount}</font></td> 
    </tr> 
    <tr align="center" valign="middle" bordercolor="#333333"> 
        <td style="font-family:Tahoma;font-size:8pt" width="8%" height="26"> 
            <div align="center"> 번호</div> 
        </td> 
        <td style="font-family:Tahoma;font-size:8pt" width="50%"> 
            <div align="center"> 제목</div> 
        </td> 
        <td style="font-family:Tahoma;font-size:8pt" width="14%"> 
            <div align="center"> 작성자</div> 
        </td> 
        <td style="font-family:Tahoma;font-size:8pt" width="17%"> 
            <div align="center"> 날짜</div> 
        </td> 
        <td style="font-family:Tahoma;font-size:8pt" width="11%"> 
            <div align="center"> 조회수</div> 
        </td>                 
    </tr>     
    <% 
        for(int i=0 ; i<boardList.size() ; i++){ 
            BoardBean bl=(BoardBean)boardList.get(i);                     
    %> 
    <tr align="center" valign="middle" bordercolor="#333333"  
        onmouseover="this.style.backgroundColor='f8f8f8'" onmouseout="this.style.backgroundColor=''"> 
        <td height="23" style="font-family:Tahoma;font-size:10pt"><%=bl.getBOARD_NUM() %></td> 
        <td style="font-family:tahoma;font-size:10pt"> 
            <div align="left"> 
            <%if(bl.getBOARD_RE_LEV()!=0){ %> 
                <%for(int a=0 ; a<=bl.getBOARD_RE_LEV()*2 ; a++){ %> 
                    &nbsp; 
                <%} %> 
                    ▶ 
            <%}else{ %> 
                    
            <%} %> 
            <a href="./BoardDetailAction.bo?num=<%=bl.getBOARD_NUM() %>"> 
                <%=bl.getBOARD_SUBJECT() %></a></div>                 
        </td>         
        <td style="font-family:Tahoma;font-size:10pt"> 
            <div align="center"><%=username %></div> 
        </td> 
        <td style="font-family:Tahoma;font-size:10pt"> 
            <div align="center"><%=bl.getBOARD_DATE() %></div> 
        </td> 
        <td style="font-family:Tahoma;font-size:10pt"> 
            <div align="center"><%=bl.getBOARD_READCOUNT() %></div> 
        </td> 
    </tr> 
    <%} %> 
    <tr align=center height=20> 
        <td colspan=7 style=font-family.Tahoma, font-size:10pt> 
            <%if(nowpage<=1){ %>    [이전] &nbsp;         
            <%}else{ %> 
                <a href ="./BoardList.no?page=<%=nowpage-1%>">[이전]</a>&nbsp; 
            <%} %> 
             
            <%for(int a=startpage ; a<=endpage ; a++){  
                    if(a==nowpage){ %>  
                        [<%=a %>]     
               <%}else{ %> 
                <a href="./BoardList.bo?page=<%=a %>">[<%=a%>]</a>&nbsp; 
                <%} %> 
             <%} %>  
              
             <%if(nowpage>=maxpage){ %> [다음] 
            <%}else{ %> 
                <a href ="./BoardList.bo?page=<%=nowpage+1%>">[다음]</a> 
            <%} %>          
        </td> 
    </tr> 
</table> 
</div>
<hr/>
<div class="container">
<tfooter>
<a class="btn btn-default pull-right" href="./BoardWrite.bo">글쓰기</a>
</tfooter>
</div>
<!-- ---------------------------------------------------------------------------------------------- -->

<footer class="footer navbar-fixed-bottom" style="background-color: #000000;">
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