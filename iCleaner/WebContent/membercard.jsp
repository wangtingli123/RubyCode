<%@page import="com.model.MemberCard"%>
<%@page import="com.model.Price"%>
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

<title>编辑站点</title>

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
					MemberCard membercard = (MemberCard)request.getAttribute("station");
					if(membercard!=null)
					{
				%>
		<form action="MemberCardManager?type=modifystation" method="post">
			<h1>编辑会员</h1>
			<table style="">
				<div class="form-group"> 
					<label for="stationname"></label> 会员账户<input type="text" 
						class="form-control" id="stationname" name="stationname" value="<%= membercard.getCard_name()%>">
				</div>
				<div class="form-group">
					<label for="stationphone"></label>联系电话 <input type="text"
						 class="form-control"  id="stationphone" name="stationphone" value="<%= membercard.getCard_phone()%>">
				</div>
				<div class="form-group">
					<label for="stationlMoney"></label>会员金额 <input type="text"
						class="form-control" id="stationlMoney" name="stationlMoney" value="<%= membercard.getCard_money()%>">
				</div>
				<div class="form-group">
					<label for="stationlIntegral"></label> 会员积分<input type="text"
						class="form-control"  id="stationlIntegral" name="stationlIntegral" value="<%= membercard.getCard_integral()%>">
				</div>
				<%} %>
				<button type="submit" class="btn btn-default">会员编辑</button>
			</table>
			
		</form>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
	</div>
</body>
</html>
