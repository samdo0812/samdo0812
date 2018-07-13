<%@page import="dron.board.db.BoardBean"%> 
<%-- <%@page import="dron.board.action.BoardReplyView"%>  --%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"%> 
<% 
    String username=(String)session.getAttribute("username"); 
    BoardBean board=(BoardBean)request.getAttribute("boarddata"); 
%> 
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
					
					<%if(session.getAttribute("username")==null){ %>
					<script>alert("�α����� ���ּ���");</script>
					<% response.sendRedirect("./login.dron"); %>
					
					<%} else {%>
						<li><a href="./BoardList.bo">���� ����</a></li>
					<%} %>
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
	

<!-- --------------------------------------------------------------------------------
<!-- �Խ��� ��� -->
<div class="container">
<form  name="modifyform" action="./BoardModifyAction.bo" method="post">
<input type="hidden" name="BOARD_NUM" value="<%=board.getBOARD_NUM() %>">
<input type="hidden" name="BOARD_NAME" value="<%=board.getBOARD_NAME() %>">


<table class="table table-bordered"> 
    <tr align="center" valign="middle"><td colspan="5"> �����ϱ�</td></tr> 
    <tr> 
        <td style="font-family:����; font-size:12" height="16"> 
            <div align="center">�۾���</div>
        </td> 
        <td><%=board.getBOARD_NAME() %></td> 
    </tr>
        <tr> 
        <td style="font-family:����; font-size:12" height="16"> 
            <div align="center">�� ��</div> 
        </td> 
        <td> 
            <input name="BOARD_SUBJECT" type="text" size="50" maxlength="100" value="<%=board.getBOARD_SUBJECT()%>" class="form-control"/> 
        </td>         
    </tr> 
    <tr> 
        <td style="font-family:����; font-size:12"> 
            <div align="center">�� ��</div> 
        </td> 
        <td> 
            <textarea name="BOARD_CONTENT" cols="67" rows="15" align="left" class="form-control"><%=board.getBOARD_CONTENT() %></textarea> 
        </td> 
    </tr> 
    <tr> 
    <%if(board.getBOARD_FILE()!=null){%> 
        <td style="font-family:���� ; font-size:12"> 
            <div align="center">����÷��</div> 
        </td> 
        <td style="font-family:���� ; font-size:12"> 
            &nbsp;&nbsp;<%=board.getBOARD_FILE() %> 
        </td> 
    </tr>     
    <% }%> 
     
    <tr bgcolor="cccccc"> 
        <td colspan="2" style="height:1px;"></td> 
    </tr> 
     
    <tr><td colspan="2">&nbsp;</td></tr> 
    <tr align="center" valign="middle"> 
        <td colspan="5"> 
            <a href="javascript:modifyform.submit()">[����]</a>&nbsp;&nbsp; 
            <a href="javascript:history.go(-1)">[�ڷ�]</a>             
        </td> 
    </tr> 
</table> 
</form> 
</div>
<!-- ---------------------------------------------------------------------------------------------- -->

<footer  style="background-color: #000000;">
		<div class="container">
		<br>
		<div class="row"> <!-- row�� ���� ������ ���� -->
			<div class="col-sm-2" style="text-algin: center;"><h5 style="color:white">Copyright &copy; 2018</h5><h5 style="color:white">�赵��(DoHoon Kim)</h5></div> <!-- 12���� 2���� ĭ -->
			<div class="col-sm-3" style="color:white"><h4>��Ʈ ��ǻ�� ��ް��� 5�� 3��
				</h4><p style="color:white"> �����ν� AI�� ���� ��� ����</p><p style="color:white">MVC2 �������� Ȩ������ ����</p></div>	<!-- 4���� ĭ -->
			<div class="col-sm-2"><h4 style="text-align: center; color:white;">�׺���̼�</h4>
				<div class="list=group">
					<a href="./main.dron" class="list-group-item">���� ����</a>
					<a href="./BoardList.bo" class="list-group-item">���� ����</a>
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