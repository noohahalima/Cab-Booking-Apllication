<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Role-List</title>
   <style> 
        .GFG {  background-color: Cornsilk;  border: 2px solid RosyBrown; color: RosyBrown;  padding: 5px 10px;  text-align: center; 
display: inline-block;  font-size: 20px; margin: 10px 30px;   cursor: pointer; 
 } 
 </style> 
</head>
<body style="background-color:Linen;">
<H1>Role List</H1>
<h2><a href="add">Add Role</a></h2>

<table border="1" cellspacing="0" cellpadding="10">
<tr>
<th>ID</th><th>Role</th><th>Action</th>
</tr>
<!--  data row -->
<c:forEach var="role" items="${roles}">
<tr>
<td>${role.roleid}</td>
<td>${role.role}</td>

<td>
<a href="update/${role.roleid}"> Edit</a> || 
<a href="delete/${role.roleid}" onclick="return confirm('Are you sure you want to delete thos record?')">Delete</a>
</td>
</tr>
</c:forEach>
</table>



<button class="GFG" 
    onclick="window.location.href = 'http://localhost:8081/cba/';">   Home  </button> 
</body>
</html>