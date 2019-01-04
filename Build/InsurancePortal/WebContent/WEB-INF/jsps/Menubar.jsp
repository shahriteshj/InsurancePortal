<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${ sessionScope.roles  == 'CUSTOMER'}">
	<jsp:include page="customer/Menubar.jsp"/>
</c:if>
<c:if test="${ sessionScope.roles  == 'OPERATIONS'}">
	<jsp:include page="operations/Menubar.jsp"/>
</c:if>
<c:if test="${ sessionScope.roles  == 'MANAGER'}">
	<jsp:include page="manager/Menubar.jsp"/>
</c:if>
<c:if test="${ sessionScope.roles  == 'ADMIN'}">
	<jsp:include page="admin/Menubar.jsp"/>
</c:if>
<c:if test="${ sessionScope.roles  == 'INSPECTOR'}">
	<jsp:include page="inspector/Menubar.jsp"/>
</c:if>

</body>
</html>