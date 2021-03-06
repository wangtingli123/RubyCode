<%@page import="com.model.Station"%>
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

<title>新增取送人员</title>

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
	<!-- <div id="allmap"></div> -->
	<div style="margin-left: 40px;"> 
		<form action="SendPeopleManager?type=addsendpeople" method="post">
			<h1>新增取送人员</h1>
			<div class="bs-example" data-example-id="simple-table">
					<table class="table table-bordered">
						<caption></caption>
						<thead>
							<tr>
								<th>站点名称</th>
								<th>站点ID</th>
								<th>经度</th>
								<th>纬度</th>
								<th>编辑</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList<Station> station_list=(ArrayList<Station>)request.getAttribute("stationlist");
													if(station_list!=null)
													{
														for(Station each:station_list)
														{
							%>
							<tr>
							<!-- <button type="button" class="btn btn-danger"> -->
								<th><%=each.getStation_name()%></th>
								<th><%=each.getStation_id()%></th>
								<td><%=each.getLongitude()%></td>
								<td><%=each.getLatitude()%></td>
								<td><a href="StationManager?type=findStationById&&stationId=<%= each.getStation_id()%>"><button type="button" class="btn btn-info">编辑</button></a></td>
								<td><a href="StationManager?type=deletestation&&stationId=<%= each.getStation_id()%>"><button type="button" class="btn btn-danger">删除</button></a></td>
							</tr>
							<%
								}
													}
							%>
						</tbody>
					</table>
				</div>
				<div class="form-group"> 
					<label for="sendpeopleid">取送人员工号</label> <input type="text" name="sendpeopleid"
						class="form-control" id="sendpeopleid" placeholder="取送人员工号">
				</div>
				<div class="form-group"> 
					<label for="sendpeoplename">取送人员名称</label> <input type="text" name="sendpeoplename"
						class="form-control" id="sendpeoplename" placeholder="取送人员名称">
				</div>
				<div class="form-group">
					<label for="sendpwd">初始密码</label> 
					<input type="text" class="form-control" id="sendpwd" name="sendpwd" placeholder="初始密码">
				</div>
				<div class="form-group"> 
					<label for="sendpeopletel">取送人员电话</label> <input type="text" name="sendpeopletel"
						class="form-control" id="sendpeopletel" placeholder="取送人员电话">
				</div>
				<div class="form-group"> 
					<label for="sendpeoplehome">家庭住址</label> <input type="text" name="sendpeoplehome"
						class="form-control" id="sendpeoplehome" placeholder="家庭住址">
				</div>
				<div class="form-group"> 
					<label for="stationid">站点ID</label> <input type="text" name="stationid"
						class="form-control" id="stationid" placeholder="站点ID">
				</div>
				<!-- <div class="form-group">
					<label for="stationname">LAT</label> <input type="text"
						class="form-control" id="LAT" name="stationlati" placeholder="lat">
				</div>
				<div class="form-group">
					<label for="stationname">LNG</label> <input type="text"
						class="form-control" id="LNG" name="stationlong" placeholder="lng">
				</div> -->

				<button type="submit" class="btn btn-default">注册</button>
		</form>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
	</div>
</body>
</html>

