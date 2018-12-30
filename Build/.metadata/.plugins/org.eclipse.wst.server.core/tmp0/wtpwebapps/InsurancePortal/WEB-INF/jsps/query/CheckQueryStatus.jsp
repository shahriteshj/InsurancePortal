<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Query Status Page</title>
<style>
.error {
    color: #ff0000;
    font-style: italic;
    font-weight: bold;
}
</style>
</head>
<body>
 	
 	<h2 align="center"><u><i>Check Query Status</i></u></h2>
 	
    <form method="POST" action="queryDetails.qry">
        <table align="center">
            <tr>
                <td>Query Id:</td>
                <td><input type ="text" name= "queryId" /></td>  
                            
            </tr>            
            <tr>            
               <td colspan="3" align="center"><button name ="Submit">Check Query Status</button></td>       
            </tr>
        </table>
 
    </form>
    	<c:if test="${requestScope.queryDetails.size()>0}">	    
			<table border = "2" align ="center">			
					<tr>					
						<th>Query Id</th>
						<th>Description</th>
						<th>Status</th>										
					</tr>
					
					<tr>
						<td>${requestScope.queryDetails.queryId}</td>
						<td>${requestScope.queryDetails.querydescription}</td>
						<td>${requestScope.queryDetails.status}</td>									
					</tr>	
		
				</table>
	 </c:if>
</body>
</html>
