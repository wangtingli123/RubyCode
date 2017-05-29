<%@page import="java.util.ArrayList"%>
<%@page import="com.model.Category"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<style type="text/css">
#allmap {
	width: 95%;
	height: 300px;
	margin-top: 30px;
	margin-left: 30px;
	margin-right: 30px;
	margin-bottom: 30px;
}
</style>

<title>添加商品</title>

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
<script type="text/javascript">
var xmlrequest;
function createXMLHttpRequest(){
	if(window.XMLHttpRequest){
		xmlrequest = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		try{
			xmlrequest = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
		try {
		xmlrequest = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e) {
		}
	}
}
}
function connectServer(value){
	createXMLHttpRequest();
	//获取属性值
	var procateid = document.getElementById("procateid").value;
	var proprice = document.getElementById("price").value;
	//填写服务器地址
	var uri = "http://localhost:8080/iCleaner/ProductManager?type=verifyprice";
	xmlrequest.open("POST",uri,true);
	xmlrequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xmlrequest.send("idprice="+procateid+","+proprice);
	//调用回调函数
	xmlrequest.onreadystatechange = refresh;
}
function refresh(){
		var flag = false;
		var result = xmlrequest.responseText;
		if(result=="lower"){
			document.getElementById("message").innerHTML = "输入的价格小于所属类别的定价区间，请重新输入";
			return false;
		}else if(result=="higher"){
			document.getElementById("message").innerHTML = "输入的价格大于所属类别的定价区间，请重新输入";
			return false;
		}else if(result=="proper"){
			return true;
		}
}

function checkPrice(){
	if(connectServer()==true){
		return true;
	}else{
		return false;
	}
}
</script>


</head>
<body style="margin-bottom: 30px;">
	<!-- <div id="allmap"></div> -->
	<div style="margin-left: 40px;"> 
		<form action="ProductManager?type=addproduct" method="post" onsubmit="checkPrice();">
			<h1>添加商品</h1>
				<div class="form-group"> 
					<label for="proid">商品号</label> <input type="text" name="proid"
						class="form-control" id="proid" placeholder="商品号">
				</div>
				<div class="form-group"> 
					<label for="proname">商品名称</label> <input type="text" name="proname"
						class="form-control" id="proname" placeholder="商品名称">
				</div>
				<div class="form-group"> 
				<label for="procateid">商品类别</label><br/>
					<select id="procateid" name="procateid" style="font-size: 15px;width: 100%;">
					<%
						ArrayList<Category> res=(ArrayList<Category>)request.getAttribute("catelist");
						for(Category each:res)
						{
							System.out.print(each.getCid()+each.getCname());
							%>
								<option value=<%=each.getCid()%>><%=each.getCname()%></option>
							<%
						}
					%>
					</select>
				</div>
				<div class="form-group"> 
					<label for="proprice">商品价格</label> <input type="text" name="proprice"
						class="form-control" id="price" placeholder="商品价格" onblur="connectServer();"><br/>
					<span id="message" style="color: red; "></span>
				</div>
				<button type="submit" class="btn btn-default">添加商品</button>
		</form>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
	</div>
</body>
</html>

