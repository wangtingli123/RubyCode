<%@page import="com.model.Station"%>
<%@page import="com.model.CleanStatus"%>
<%@page import="com.model.Product"%>
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
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=0VG0iZiod5hieaBR2qjCfn2RukQzi77N"></script>
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

<title>下单</title>

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
	var productTypeId = document.getElementById("productTypeId").value;
	//填写服务器地址
	var uri = "http://localhost:8080/iCleaner/OrderManager?type=getproductprice";
	xmlrequest.open("POST",uri,true);
	xmlrequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xmlrequest.send("protypeid="+productTypeId);
	//调用回调函数
	xmlrequest.onreadystatechange = refresh;
}
function refresh(){
		var result = xmlrequest.responseText;
		document.getElementById("price").value = result;
}

</script>


</head>
<body style="margin-bottom: 30px;">
	<div id="allmap"></div>
	<div style="margin-left: 40px;"> 
		<form action="OrderManager?type=addpayedorder" method="post">
			<h1>下单</h1>
				<div class="form-group"> 
				<label for="productType">请选择清洗物品</label><br/>
					<select id="productTypeId" name="productTypeId" style="font-size: 15px;width: 100%;" onchange="connectServer();">
					<%
						ArrayList<Product> res=(ArrayList<Product>)request.getAttribute("productList");
						for(Product each:res)
						{
							%>
								<option value=<%=each.getProid()%>><%=each.getProname()%></option>
							<%
						}
					%>
					</select>
				</div>
				<div class="form-group"> 
					<label for="proprice">商品价格</label> <input type="text" name="proprice"
						class="form-control" id="price"><br/>
				</div>
				<div class="form-group"> 
					<label for="orderaddr">订单地址</label> <input type="text" name="orderaddr"
						class="form-control" id="orderaddr" placeholder="订单地址">
				</div>
				<div class="form-group"> 
				<label for="stationId">发往的站点</label><br/>
					<select id=stationId name="stationId" style="font-size: 15px;width: 100%;">
					<%
						ArrayList<Station> sta=(ArrayList<Station>)request.getAttribute("stationlist");
						for(Station each:sta)
						{
							%>
								<option value=<%=each.getStation_id()%>><%=each.getStation_name()%></option>
							<%
						}
					%>
					</select>
				</div>
				<div class="form-group"> 
					<label for="cleanstatus">加工状态</label> 
					<select id="cleanstatus" name="cleanstatus" style="font-size: 15px;width: 100%;">
					<%
						ArrayList<CleanStatus> cs=(ArrayList<CleanStatus>)request.getAttribute("cslist");
						for(CleanStatus each:cs)
						{
							%>
								<option value=<%=each.getCleanStatusId()%>><%=each.getCleanStatus()%></option>
							<%
						}
					%>
					</select>
				</div>
				<button type="submit" class="btn btn-default">立即支付</button>
		</form>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
	</div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(116.331398, 39.897445);
	map.centerAndZoom(point, 12);
	map.enableScrollWheelZoom();
	var geoc = new BMap.Geocoder();

	map.addEventListener("click", function(e) {
		var pt = e.point;
		geoc.getLocation(pt, function(rs) {
			var addComp = rs.addressComponents;
			document.getElementById('orderaddr').value = addComp.province
					+ addComp.city + addComp.district + addComp.street
					+ addComp.streetNumber;
		});
	});
</script>
