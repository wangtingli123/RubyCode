<%@page import="com.model.SendPeople"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>取送人员信息编辑</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>



</head>
<body style="margin-bottom: 30px;">
	<div style="margin-left: 40px;">
	<%
					SendPeople sendPeople = (SendPeople)request.getAttribute("sp");
					if(sendPeople!=null)
					{
				%>
		<form action="SendPeopleManager?type=modifysendpeople" method="post">
			<h1>取送人员信息编辑</h1>
			<table style="">
				
				<div class="form-group"> 
					<label for="sendid"></label>人员工号<input type="text" name="sendid"
						class="form-control" id="sendid" value="<%= sendPeople.getSendId()%>" readonly="readonly">
				</div>
				<div class="form-group">
					<label for="sendname"></label>人员姓名<input type="text"
						class="form-control" id="sendname" name="sendname" value="<%=sendPeople.getSendName() %>">
				</div>
				<div class="form-group">
					<label for="sendtel">人员电话</label> <input type="text"
						class="form-control" id="sendtel" name="sendtel" value="<%=sendPeople.getSendTel()%>">
				</div>
				<div class="form-group">
					<label for="sendhomeaddr"></label> <input type="text"
						class="form-control" id="sendhomeaddr" name="sendhomeaddr" value="<%=sendPeople.getSendHomeAddr()%>">
				</div>
				<div class="form-group">
					<label for="sendboundstation"></label> <input type="text"
						class="form-control" id="sendboundstation" name="sendboundstation" value="<%=sendPeople.getStation().getStation_id()%>" readonly="readonly">
				</div>
				<%} %>

				<button type="submit" class="btn btn-default">保存</button>
			</table>
		</form>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
	</div>
</body>
</html>
