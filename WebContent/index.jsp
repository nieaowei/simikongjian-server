<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/bootstrap.bundle.js"></script>
<script type="text/javascript" src="js/bootstrap.bundle.min.js"></script>

<title>食堂管理系统</title>
<meta charset="UTF-8">
<script type="text/javascript">
$(function(){
	$('#submit').click(sendMessage);
	//为输入框绑定事件
	
	function sendMessage() {
		var dataString = $('#cform').serialize();
		$.post(
				"${pageContext.request.contextPath}/addFood",
				dataString,
				function(data){
					var isExist = data.success;
					//3、根据返回的isExist动态的显示信息
					var usernameInfo = "";
					if(success==1){
						usernameInfo = "成功";
						$("#usernameInfo").css("color","green");
					}else{
						usernameInfo = "失败"
						$("#usernameInfo").css("color","red");
					}
					$("#usernameInfo").html(usernameInfo);
					
				},
				"json"
			);
		
	}
	


	
});

</script>


</head>

<body>

	<h1 style="text-align:center">食堂管理系统</h1>
	<form action="${pageContext.request.contextPath}/findFoodByName.action" method="get">
	菜品名称:<input type="text"  name="name" id="name" placeholder="输入菜品名称搜索">
	<input type="submit" value="搜索">
	</form>
	<table class="table table-bordered table-hover"  align="center">
		<thead>
			<tr bgcolor="#ff0">
				<th width="5%">编号</th>
				<th width="10%">菜品名称</th>
				<th width="10%">价格</th>
				<th width="25%">备注</th>
				<th width="15%">时间</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="food" items="${foods}">
				<tr>
					<td>${food.id}</td>
					<td>${food.name}</td>
					<td>${food.price}</td>
					<td>${food.msg}</td>
					<td>${food.date}</td>
					<td><a href="${pageContext.request.contextPath}/editFood.action?param=0&id=${food.id}">编辑</a>
					<a href="${pageContext.request.contextPath}/delete.action?id=${food.id}">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br/>
	<div align="center">
	<br/><br/>
	<p >添加菜品</p>
	<form id="cform" action="addFood.action" method="post">
		菜品名称:<input type="text" name="name">
		价格:<input type="text" name="price">
		备注:<input type="text" name="msg">
		<span id="usernameInfo"></span>
		<input id="submit" type="submit" value="提交"> <input type="reset" value="重置">
	</form>
	</div>
</body>
</html>
