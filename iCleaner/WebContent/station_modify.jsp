<%@page import="com.model.Station"%>
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
	<div id="allmap"></div>
	<div style="margin-left: 40px;">
	<%
					Station station = (Station)request.getAttribute("station");
					if(station!=null)
					{
				%>
		<form action="StationManager?type=modifystation&&stationId=<%=station.getStation_id() %>" method="post">
			<h1>编辑站点</h1>
			<table style="">
				
				<div class="form-group"> 
					<label for="stationname">站点名称</label> <input type="text" name="stationname"
						class="form-control" id="stationname" placeholder="站点名称" value="<%= station.getStation_name()%>">
				</div>
				<div class="form-group">
					<label for="stationname">LAT</label> <input type="text"
						class="form-control" id="LAT" name="stationlati" placeholder="lat" value="<%=station.getLatitude() %>">
				</div>
				<div class="form-group">
					<label for="stationname">LNG</label> <input type="text"
						class="form-control" id="LNG" name="stationlong" placeholder="lng" value="<%=station.getLongitude()%>">
				</div>
				<%} %>

				<button type="submit" class="btn btn-default">更新站点</button>
			</table>
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
			document.getElementById('stationname').value = addComp.province
					+ addComp.city + addComp.district + addComp.street
					+ addComp.streetNumber;
			document.getElementById('LAT').value = pt.lat;
			document.getElementById('LNG').value = pt.lng;
		});
	});
</script>