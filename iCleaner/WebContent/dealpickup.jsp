<%@page import="java.util.Date"%>
<%@page import="com.model.SendOrderStatus"%>
<%@page import="com.model.Station"%>
<%@page import="com.model.Order"%>
<%@page import="java.util.ArrayList"%>
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
<link rel="stylesheet" type="text/css" href="tcal.css" />
	<script type="text/javascript" src="tcal.js"></script> 
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

<title>准备派单</title>

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
		<form action="PickUpManager?type=createpickup" method="post" >
			<h1>准备派单</h1>
			<table>
				<div class="form-group"> 
				<%
					Order order = (Order)request.getAttribute("order");
					String orderid = order.getOrderId();
					String orderaddr = order.getOrderAddr();
				%>
					<label for="orderid">订单编号</label><br/>
					<input type="text" style="width:100%;float: left;" id="orderid" name="orderid" value="<%=orderid%>" readonly="true"></tr>
				</div>
				<div class="form-group"> 
				<br/>
					<label for="orderstationid">订单地址</label><br> 
					<input type="text" style="width:100%;float: left;" id="orderstationid" name="orderstationid" value="<%=orderaddr%>" readonly="true"></tr>
				</div>
				<br/><br/>
				<div class="form-group"> 
				<label for="stationId">选择派送站点</label><br/>
					<select id="stationId" name="stationId" style="font-size: 15px;width: 100%;">
 					<%
						ArrayList<Station> res=(ArrayList<Station>)request.getAttribute("stationList");
 					for(Station each:res)
					{
						%>
							<option value=<%=each.getStation_id()%>><%=each.getStation_name()%></option>
						<%
					}
				%>
 					</select>
 				</div>
 				<br/>
				<div class="form-group"> 
				<label for="sendstatusid">取件单状态</label><br/>
					<select id="sendstatusid" name="sendstatusid" style="font-size: 15px;width: 100%;">
 					<%
						ArrayList<SendOrderStatus> statuslist=(ArrayList<SendOrderStatus>)request.getAttribute("soslist");
 					for(SendOrderStatus each:statuslist)
					{
						%>
							<option value=<%=each.getSendOrderId()%>><%=each.getSendOrderStatus()%></option>
						<%
					}
				%>
 					</select>
 				</div>
				<button type="submit" class="btn btn-default">生成取件单</button>
				</table>
		</form>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
	</div>
</body>
</html>
