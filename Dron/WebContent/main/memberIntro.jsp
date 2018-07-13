<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
String username=null; 
if(session.getAttribute("username")!=null){ 
	username=(String)session.getAttribute("username"); 
} 
%>

<html>
<head>
	<meta name="viewport" content="width=device-width" , initial-scale='1'>	<!-- ��⿡ ���� ȭ�� ũ�� -->
	<title>3�� ������</title>
	<link rel="stylesheet" href="./Resources/css/bootstrap.css">	<!-- �ܺ������� ������� css ������ ����(��Ʈ ��Ʈ��) -->
	<link rel="stylesheet" href="./Resources/css/dronStcss.css">  <!-- �ܺ������� dronStcss ������ ����(���� �����) -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="./Resources/js/bootstrap.js"></script>
	<script>
		function btn() {
			<%if(session.getAttribute("username") == null) { %>
			alert('�α��� ���ּ���');
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
			background-image: url('../Resources/images/jumbotronBackGround.jpg');
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
			
			<div class="collapsed navbar-collapse" id="bs-example-navbar-collapse-1" >
				<ul class="nav navbar-nav">
					<li class="active"><a href="../main/main.jsp">�Ұ�<span class="sr-only"></span></a></li>
					<li><a href="javascript:void(0);" onclick="btn();">���� ����</a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">���� �Ұ�<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#kim">�赵��</a></li>
							<li><a href="#chu">������</a></li>
							<li><a href="#lee">������</a></li>
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
	
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="pnael panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-tag"></span> &nbsp; <a id="kim">����</a> 
						</h3>
					</div>
					<div class="panel-body">
						<div class="media">
							<div class="media-left">
								<a href="#">
									<img class="media-object" src="./Resources/images/kdh.jpg">
								</a>
							</div>
						<div class="media-body">
							<h4 class="media-heading">�赵��</h4><br>
								���ع��� ��λ��� ������ �⵵��
						</div>	
						</div>
					</div>
					
					<table class="table">
						<thead>
							<tr>
								<th>�������</th>
								<th>�а�</th>
								<th>�й�</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1991.08.12</td>
								<td>�Ϻ���â�������к�</td>
								<td>20112298</td>
							</tr>
						</tbody>	
					</table>
				</div>
			</div> 
			
			<div class="col-xs-12">
				<div class="pnael panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-tag"></span> &nbsp; <a id="chu">������</a> 
						</h3>
					</div>
					<div class="panel-body">
						<div class="media">
							<div class="media-left">
								<a href="#">
									<img class="media-object" src="./Resources/images/cjb.jpg">
								</a>
							</div>
						<div class="media-body">
							<h4 class="media-heading">������</h4><br>
								<strong>���� �� �ൿ�� ���� å������</strong>
						</div>	
						</div>
					</div>					
					<table class="table">
						<thead>
							<tr>
								<th>�������</th>
								<th>�а�</th>
								<th>�й�</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1992.09.28</td>
								<td>�Ϻ���â�������к�</td>
								<td>20110476</td>
							</tr>
						</tbody>	
					</table>
				</div>
			</div>
			
			<div class="col-xs-12">
				<div class="pnael panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-tag"></span> &nbsp; <a id="lee">����</a> 
						</h3>
					</div>
					<div class="panel-body">
						<div class="media">
							<div class="media-left">
								<a href="#">
									<img class="media-object" src="./Resources/images/leb.jpg">
								</a>
							</div>
						<div class="media-body">
							<h4 class="media-heading">������</h4><br>
								<strong>���� �� �տ� �ִ� �ͺ��� �� ��Ű��</strong>
						</div>	
						</div>
					</div>					
					<table class="table">
						<thead>
							<tr>
								<th>�������</th>
								<th>�а�</th>
								<th>�й�</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1993.08.17</td>
								<td>���� ������ȣ�к�</td>
								<td>20121432</td>
							</tr>
						</tbody>	
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<!-- --------------------------------------------------------------------------------------------------------------------------- -->
	<footer style="background-color: #000000;">
		<div class="container">
		<br>
		<div class="row"> <!-- row�� ���� ������ ���� -->
			<div class="col-sm-2" style="text-algin: center;"><h5 style="color:white">Copyright &copy; 2018</h5><h5 style="color:white">�赵��(DoHoon Kim)</h5></div> <!-- 12���� 2���� ĭ -->
			<div class="col-sm-3" style="color:white"><h4>��Ʈ ��ǻ�� ��ް��� 5�� 3��</h4><p style="color:white">�����ν� AI�� ���� ��� ����</p></div>	<!-- 4���� ĭ -->
			<div class="col-sm-2"><h4 style="text-align: center; color:white;">�׺���̼�</h4>
				<div class="list=group">
					<a href="./main.dron" class="list-group-item">���� ����</a>
					<a href="javascript:void(0);" onclick="btn();" class="list-group-item">���� ����</a>
					<a href="./MemberIntro.dron#kim" class="list-group-item">���� �Ұ�</a>
				</div>
			</div>
			<div class="col-sm-3"><h4 style="text-align: center; color:white;">Blog</h4>
				<div class="list=group">
					<a href="http://samdo0812.tistory.com/category" class="list-group-item">�赵�� �ľ˸�IT</a>
					<a href="01052268641.tistory.com" class="list-group-item">������ CoChu</a>
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
	
	<div style="position: fixed; bottom: 5px; right: 5px; margin-bottom: 10px; margin-right: 10px;">
		<a id="MOVE_TOP_BTN" href="#">
			<img src="./Resources/images/topButton.png">
		</a>
	</div>	
</body>
</html>