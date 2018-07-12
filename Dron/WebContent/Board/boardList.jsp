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
int maxpage=((Integer)request.getAttribute("maxpage")).intValue(); //�ִ� ��������
int startpage=((Integer)request.getAttribute("startpage")).intValue(); //���� �������� ǥ���� ù ������ ��
int endpage=((Integer)request.getAttribute("endpage")).intValue(); //���� �������� ǥ�� �� �� ������ ��

%>

<html>
<head>
<head>
	<meta name="viewport" content="width=device-width" , initial-scale='1'>	<!-- ��⿡ ���� ȭ�� ũ�� -->
	<title>3�� ������</title>
	<link rel="stylesheet" href="./Resources/css/bootstrap.css">	<!-- �ܺ������� ������� css ������ ����(��Ʈ ��Ʈ��) -->
	<link rel="stylesheet" href="./Resources/css/dronStcss.css">  <!-- �ܺ������� dronStcss ������ ����(���� �����) -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="./Resources/js/bootstrap.js"></script>
</head>
<body>

<style type="text/css">
		.jumbotron{
			background-image: url('./Resources/images/jumbotronBackGround.jpg');
			background-size: cover;	/* ��� �̹����� ũ�⸦ ������ */
			text-shadow: black 0.2em 0.2em 0.2em;
			color: white;
		}
	</style>
	
	<nav class="navbar navbar-default" > <!-- �׺���̼� �� --> 
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				
				<a class="navbar-brand" href="./main.dron">3�� ������</a> <!-- ��ũ ���� -->
			</div>
			
			<div class="collapsed navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="main.jsp">�Ұ�<span class="sr-only"></span></a></li>
					<li><a href="./BoardList.bo">���� ����</a></li>
					<li class="dropdown">
						<a href="memberIntro.jsp" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">���� �Ұ�<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="./MemberIntro.dron#kim">�赵��</a></li>
							<li><a href="./MemberIntro.dron#chu">������</a></li>
							<li><a href="./MemberIntro.dron#lee">������</a></li>
					   </ul>
					</li>
				</ul>
				<!-- �˻�â -->
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="������ �Է��ϼ���.">
					</div>
					<button type="submit" class="btn btn-default">�˻�</button>
				</form>
				
				<%if(session.getAttribute("username")==null) { %>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="/MemberJoin.dron" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">�����ϱ�<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="./MemberLogin.dron">�α���</a></li>
							<li><a href="./MemberJoin.dron">ȸ������</a></li>
					   </ul>
					</li>
				</ul>
				<% } else { %>
				<% session.setMaxInactiveInterval(180);%>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown2">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=session.getAttribute("username")%> �� ȯ���մϴ�.<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="./MemberLogoutAction.dron">�α׾ƿ�</a></li>			
					   </ul>
					</li>
				</ul>
				<% } %>
				
				
			</div>
		
		</div>
	</nav>




<!-- ------------------------------------------------- -->
<!-- �Խ��� ����Ʈ -->

<div class="container"> 
<table class="table table-striped"> 
    <tr align="center" valign="middle"> 
        <td colspan="4">���� ����</td> 
        <td align=right> <font size=2><span class="glyphicon glyphicon-pencil"></span>&nbsp;�� ���� : ${listcount}</font></td> 
    </tr> 
    <tr align="center" valign="middle" bordercolor="#333333"> 
        <td style="font-family:Tahoma;font-size:8pt" width="8%" height="26"> 
            <div align="center"> ��ȣ</div> 
        </td> 
        <td style="font-family:Tahoma;font-size:8pt" width="50%"> 
            <div align="center"> ����</div> 
        </td> 
        <td style="font-family:Tahoma;font-size:8pt" width="14%"> 
            <div align="center"> �ۼ���</div> 
        </td> 
        <td style="font-family:Tahoma;font-size:8pt" width="17%"> 
            <div align="center"> ��¥</div> 
        </td> 
        <td style="font-family:Tahoma;font-size:8pt" width="11%"> 
            <div align="center"> ��ȸ��</div> 
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
                    �� 
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
            <%if(nowpage<=1){ %>    [����] &nbsp;         
            <%}else{ %> 
                <a href ="./BoardList.no?page=<%=nowpage-1%>">[����]</a>&nbsp; 
            <%} %> 
             
            <%for(int a=startpage ; a<=endpage ; a++){  
                    if(a==nowpage){ %>  
                        [<%=a %>]     
               <%}else{ %> 
                <a href="./BoardList.bo?page=<%=a %>">[<%=a%>]</a>&nbsp; 
                <%} %> 
             <%} %>  
              
             <%if(nowpage>=maxpage){ %> [����] 
            <%}else{ %> 
                <a href ="./BoardList.bo?page=<%=nowpage+1%>">[����]</a> 
            <%} %>          
        </td> 
    </tr> 
</table> 
</div>
<hr/>
<div class="container">
<tfooter>
<a class="btn btn-default pull-right" href="./BoardWrite.bo">�۾���</a>
</tfooter>
</div>
<!-- ---------------------------------------------------------------------------------------------- -->

<footer class="footer navbar-fixed-bottom" style="background-color: #000000;">
		<div class="container">
		<br>
		<div class="row"> <!-- row�� ���� ������ ���� -->
			<div class="col-sm-2" style="text-algin: center;"><h5 style="color:white">Copyright &copy; 2018</h5><h5 style="color:white">�赵��(DoHoon Kim)</h5></div> <!-- 12���� 2���� ĭ -->
			<div class="col-sm-3" style="color:white"><h4>��Ʈ ��ǻ�� ��ް��� 5�� 3��
				</h4><p style="color:white"> �����ν� AI�� ���� ��� ����</p><p style="color:white">MVC2 �������� Ȩ������ ����</p></div>	<!-- 4���� ĭ -->
			<div class="col-sm-2"><h4 style="text-align: center; color:white;">�׺���̼�</h4>
				<div class="list=group">
					<a href="./main.dron" class="list-group-item">���� ����</a>
					<a href="#" class="list-group-item">���� ����</a>
					<a href="./MemberIntro.dron#kim" class="list-group-item">���� �Ұ�</a>
				</div>
			</div>
			<div class="col-sm-3"><h4 style="text-align: center; color:white;">Blog</h4>
				<div class="list=group">
					<a href="http://samdo0812.tistory.com/category" class="list-group-item">�赵�� �ľ˸�IT</a>
					<a href="http://01052268641.tistory.com" class="list-group-item">������ CoChu</a>
					<a href="http://s262701-id.tistory.com/" class="list-group-item">������ �̵�'s All about Coding</a>
				</div>
			</div>
			<div class="col-sm-2">
			<br>
			<h4 style="text-align: center; color:white;"><a href="https://www.bufs.ac.kr">�λ�ܱ�����б�</a></h4><br>
			<h4 style="text-align: center; color:white;"><a href="http://www.bitacademy.com/">��Ʈ��������</a></h4>
			</div>
		</div>
		</div>
	</footer>   
</body>
</html>