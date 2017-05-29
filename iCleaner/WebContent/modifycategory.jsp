<%@page import="com.model.Category"%>
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

<title>品类编辑</title>

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
					Category cate = (Category)request.getAttribute("cate");
					if(cate!=null)
					{
				%>
		<form action="CategoryManager?type=modifycate" method="post">
			<h1>品类编辑</h1>
			<table style="">
				
				<div class="form-group"> 
					<label for="cateid"></label>品类编号<input type="text" name="cateid"
						class="form-control" id="cateid" value="<%= cate.getCid()%>" readonly="readonly">
				</div>
				<div class="form-group">
					<label for="catename"></label>品类名称<input type="text"
						class="form-control" id="catename" name="catename" value="<%=cate.getCname() %>">
				</div>
				<div class="form-group">
					<label for="pricegrade">价格等级</label> <input type="text"
						class="form-control" id="pricegrade" name="pricegrade" value="<%=cate.getPrice().getPid()%>">
				</div>
				<div class="form-group">
					<label for="pricelow">最低价格</label> <input type="text"
						class="form-control" id="pricelow" name="pricelow" value="<%=cate.getPrice().getPricelow()%>" readonly="readonly">
				</div>
				<div class="form-group">
					<label for="pricehigh">最高价格</label> <input type="text"
						class="form-control" id="pricehigh" name="pricehigh" value="<%=cate.getPrice().getPricehigh()%>" readonly="readonly">
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
