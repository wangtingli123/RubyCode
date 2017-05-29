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
					Price price = (Price)request.getAttribute("price");
					if(price!=null)
					{
				%>
		<form action="PriceManager?type=modifyprice" method="post">
			<h1>编辑价目表</h1>
			<table style="">
				
				<div class="form-group"> 
					<label for="pid"></label>价格等级<input type="text" name="pid"
						class="form-control" id="pid" value="<%= price.getPid()%>">
				</div>
				<div class="form-group">
					<label for="pricelow"></label>最低价格<input type="text"
						class="form-control" id="pricelow" name="pricelow" value="<%=price.getPricelow() %>">
				</div>
				<div class="form-group">
					<label for="pricehigh">最高价格</label> <input type="text"
						class="form-control" id="pricehigh" name="pricehigh" value="<%=price.getPricehigh()%>">
				</div>
				<%} %>

				<button type="submit" class="btn btn-default">编辑价格</button>
			</table>
		</form>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
	</div>
</body>
</html>
