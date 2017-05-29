<%@page import="com.model.SendPeople"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1 ,user-scalable=no ">
<title>取送人员列表</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">
		<div class="panel panel-warning" style="margin-top: 20px;">
			<div class="panel-heading">
				<h3 class="panel-title">取送人员列表&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</h3>

			</div>
			<div class="panel-body">
				<div class="bs-example" data-example-id="simple-table">
					<table class="table table-bordered">
						<caption></caption>
						<thead>
							<tr>
								<th>取送人员工号</th>
								<th>取送人员姓名</th>
								<th>初始密码</th>
								<th>联系电话</th>
								<th>家庭住址</th>
								<th>绑定站点名</th>
								<th>编辑</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList<SendPeople> sendList=(ArrayList<SendPeople>)request.getAttribute("sendList");
													if(sendList!=null)
													{
														for(SendPeople each:sendList)
														{
							%>
							<tr>
							<!-- <button type="button" class="btn btn-danger"> -->
								<th><%=each.getSendId()%></th>
								<th><%=each.getSendName()%></th>
								<th><%=each.getSendPeoplePwd() %></th>
								<td><%=each.getSendTel()%></td>
								<td><%=each.getSendHomeAddr()%></td>
								<td><%=each.getStation().getStation_name()%></td>
								<td><a href="SendPeopleManager?type=getsendpeople&&sendpeopleid=<%= each.getSendId()%>"><button type="button" class="btn btn-info">编辑</button></a></td>
								<td><a href="SendPeopleManager?type=deletesendpeople&&sendpeopleid=<%= each.getSendId()%>"><button type="button" class="btn btn-danger">删除</button></a></td>
							</tr>
							<%
								}
													}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
</body>
</html>