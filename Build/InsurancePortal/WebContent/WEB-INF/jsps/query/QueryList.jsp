<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.jp.insurance.entities.Query"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Query List</title>
</head>
<body>
	<p>${msg}</p>	
	<table border="1">
		<tr>
			<th>NAME</th>
			<th>DESCRIPTION</th>
			<th>STATUS</th>
			<th>QUERY ID</th>
		</tr>

		<c:forEach items="${ requestScope.queryList}" var="query">
			<tr>
				<td>${query.name}</td>
				<td>${query.queryDescription}</td>
				<td>${query.status}</td>
				
				<td><a href="queryDetails.qry?queryId=${query.queryId}">${query.queryId}</a></td>

			</tr>
		</c:forEach>
	</table>

	<a href="homePage.po">Go to Home Page</a>
	<a href="logout.po">Logout</a>
</body>
</html>