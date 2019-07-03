<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>展示</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

</head>
<body>
	
	<a href="exit">退出</a>
	<a href="insertCat.jsp">添加</a>
	<table>
		<tr>
			<td>编号</td>
			<td>姓名</td>
			<td>颜色</td>
			<td>性别</td>
			<td>操作</td>
		
		
		</tr>
		<c:forEach items="${cat}" var="i">
			<tr>
			<td>${i.id }</td>
			<td>${i.name }</td>
			<td>${i.color }</td>
			<td>${i.sex }</td>
			<td><a href="deleteCat.do?id=${i.id }">删除</a>
			<a href="updateCatShow.do?id=${i.id }">修改</a></td>
		
		
		</tr>
		
		</c:forEach>
	
	</table>
	
</body>
</html>