<%@page import="com.model.PickUp"%>
<%@page import="com.model.Order"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1 ,user-scalable=no ">
<title>取件单列表</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">
		<div class="panel panel-warning" style="margin-top: 20px;">
			<div class="panel-heading">
				<h3 class="panel-title">取件单&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</h3>

			</div>
			<div class="panel-body">
				<div class="bs-example" data-example-id="simple-table">
					<table class="table table-bordered">
						<caption></caption>
						<thead>
							<tr>
								<th>取件单编号</th>
								<th>订单编号</th>
								<th>出发地</th>
								<th>目的地</th>
								<th>取件单状态</th>
								<th>取件时间</th>
								<th>查看详细</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList<PickUp> pickuplist = (ArrayList<PickUp>)request.getAttribute("pickuplist");
													if(pickuplist!=null)
													{
														for(PickUp each:pickuplist)
														{
							%>
							<tr>
							<!-- <button type="button" class="btn btn-danger"> -->
								<th><%=each.getPickid()%></th>
								<th><%=each.getOrder().getOrderId()%></th>
								<th><%=each.getOrder().getOrderAddr()%></th>
								<td><%=each.getStation().getStation_name()%></td>
								<th><%=each.getPickStatus().getSendOrderStatus()%></th>
								<th><%=each.getRealTime()%></th>
								<td><a href="PickUpManager?type=getpickup&&pickid=<%=each.getPickid()%>"><button type="button" class="btn btn-danger">查看详细</button></a></td>
								<td><a href="PickUpManager?type=deletepick&&pickid=<%=each.getPickid()%>"><button type="button" class="btn btn-danger">删除</button></a></td>
							</tr>
							<%
								}
													}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
</body>
</html>