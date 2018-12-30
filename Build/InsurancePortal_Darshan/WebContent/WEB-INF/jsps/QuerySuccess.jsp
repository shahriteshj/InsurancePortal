<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Query Saved Successfully</title>
</head>
<body>
 
	<h6></h6>
	<p align="center"><i>Query Id for this request is :${command.queryId}.</i></p>
	<p align="center"><i>Thanks for raising a Query.</i></p><br>
<a  href="queryDetails.qry?queryId=${command.queryId}"><p style="text-align:center">Check Query Status</a> &nbsp;&nbsp; <a href="QueryPage.qry">Raise New Query</a>
	 
	 
</body>
</html>
