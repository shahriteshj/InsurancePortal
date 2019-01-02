<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
</head>
<body>
	<springForm:form align="center" method="POST"
		commandName="queryDetails" action="updateQueryForm.qry">
		<springForm:hidden path="queryId"/>

		<c:if test="${ sessionScope.roles  == 'CUSTOMER'}">

			<table border="1">
				<tr>
					<td>NAME</td>
					<td>${requestScope.queryDetails.name}</td>
				</tr>
				<tr>
					<td>EMAIL</td>
					<td>${requestScope.queryDetails.emailId}</td>
				</tr>
				<tr>
					<td>DESCRIPTION</td>
					<td>${requestScope.queryDetails.queryDescription}</td>
				</tr>
				<tr>
					<td>QUERY TYPE</td>
					<td>${requestScope.queryDetails.queryType}</td>
				</tr>
				<tr>
					<td>STATUS</td>
					<td>${requestScope.queryDetails.status}</td>
				</tr>
			</table>
		</c:if>

		<%-- <c:if test="${ sessionScope.roles  == 'MANAGER'}">
			<table border="1">
				<tr>
					<td>NAME</td>
					<td>${requestScope.queryDetails.name}</td>
				</tr>
				<tr>
					<td>EMAIL</td>
					<td>${requestScope.queryDetails.emailId}</td>
				</tr>
				<tr>
					<td>DESCRIPTION</td>
					<td>${requestScope.queryDetails.queryDescription}</td>
				</tr>
				<tr>
					<td>QUERY TYPE</td>
					<td>${requestScope.queryDetails.queryType}</td>
				</tr>
				<tr>
					<td>STATUS</td>
					<td><springForm:select path="status">
							<springForm:option value="IN PROGRESS">IN PROGRESS</springForm:option>
							<springForm:option value="CLOSED">CLOSED</springForm:option>
						</springForm:select></td>
				</tr>
				<tr>
					<td>ASSIGN TO</td>
					<td><springForm:select path="assignedTo">
							<springForm:options items="${roleNameList}" />
						</springForm:select></td>
				</tr>
				<tr>
					<td>RESPONSE</td>
					<td><springForm:textarea path="queryResponse" rows="5"
							cols="30" /></td>

				</tr>
			</table>
		</c:if> --%>

		<c:if
			test="${ sessionScope.roles  == 'OPERATIONS' || sessionScope.roles  == 'MANAGER' }">

			<table border="1">
				<tr>
					<td>NAME</td>
					<td>${requestScope.queryDetails.name}</td>
				</tr>
				<tr>
					<td>EMAIL</td>
					<td>${requestScope.queryDetails.emailId}</td>
				</tr>
				<tr>
					<td>DESCRIPTION</td>
					<td>${requestScope.queryDetails.queryDescription}</td>
				</tr>
				<tr>
					<td>QUERY TYPE</td>
					<td>${requestScope.queryDetails.queryType}</td>
				</tr>
				<tr>
					<td>STATUS</td>
					<td><springForm:select path="status">
							<springForm:option value="IN PROGRESS">IN PROGRESS</springForm:option>
							<springForm:option value="CLOSED">CLOSED</springForm:option>
						</springForm:select></td>
				</tr>
				<tr>
					<td>ASSIGN TO</td>
					<td><springForm:select path="assignedTo">
							<springForm:options items="${roleNameList}" />
						</springForm:select></td>
				</tr>
				<tr>
					<td>RESPONSE</td>
					<td><springForm:textarea path="queryResponse" rows="5"
							cols="30" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Submit"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="Cancel"></td>
				</tr>
			</table>
		</c:if>
	</springForm:form>
	<!-- Providing HyperLink to provide list of the employee -->
	<a href="queryList.qry">Go back to List</a>
	<a href="logout.po">Logout</a>
</body>
</html>