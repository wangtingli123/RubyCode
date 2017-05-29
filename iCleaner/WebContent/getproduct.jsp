<%@page import="com.model.Product"%>
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

<title>商品信息编辑</title>

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
					Product pro = (Product)request.getAttribute("product");
					if(pro!=null)
					{
				%>
		<form action="ProductManager?type=modifyproduct" method="post">
			<h1>商品信息编辑</h1>
			<table style="">
				
				<div class="form-group"> 
					<label for="proid"></label>商品编号<input type="text" name="proid"
						class="form-control" id="proid" value="<%= pro.getProid()%>" readonly="readonly">
				</div>
				<div class="form-group">
					<label for="proname"></label>商品名<input type="text"
						class="form-control" id="proname" name="proname" value="<%=pro.getProname() %>">
				</div>
				<div class="form-group">
					<label for="proprice">商品价格</label> <input type="text"
						class="form-control" id="proprice" name="proprice" value="<%=pro.getProduct_price()%>">
				</div>
				<div class="form-group">
					<label for="procategory">所属类别</label> <input type="text"
						class="form-control" id="procategory" name="procategory" value="<%=pro.getCategory().getCname()%>" readonly="readonly">
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
