<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改菜品信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="SSH">

  </head>
  
  <body>
  	编辑菜品信息
    <form action="editFood.action" method="get">
    ID:<input type="text" name="id" value="${food.id}" readonly="readonly"><br/>
         菜品名称:<input type="text" name="name" value="${food.name}"><br/>
    菜品价格:<input type="text" name="price" value="${food.price}"><br/>
    菜品备注:<input type="text" name="msg" value="${food.msg}"><br/>
    
    <input type="hidden" name="param" value="1"/>
    <input type="submit" value="提交">
    <input type="reset" value="重置">
    </form>
  </body>
</html>
