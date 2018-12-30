<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Employee Details</title>
	</head>
		<body>
<%-- 		<jsp:include page="Head01.jsp"></jsp:include>
		<jsp:include page="Head02.jsp"></jsp:include> --%>
			<%-- Welcome Mr./Ms. ${ sessionScope.userFullName }
			<a href="logout.do">Logout</a> --%>
			<table border = "1">
				<tr>
			 		<th>NAME</th>
			 		<th>EMAIL</th>
			 		<th>DESCRIPTION</th>
			 		<th>QUERY TYPE</th>			 					 		
			 		<th>STATUS</th>		
				</tr>
				
				<tr>
					<td>${requestScope.queryDetails.name}</td>
					<td>${requestScope.queryDetails.emailId}</td>
					<td>${requestScope.queryDetails.queryDescription}</td>
					<td>${requestScope.queryDetails.queryType}</td>
					<td>${requestScope.queryDetails.status}</td>
				</tr>				
			</table>	
			
			<!-- Providing HyperLink to provide list of the employee -->
			<a href="queryList.qry">Go back to List</a> 		
		</body>
	</html>