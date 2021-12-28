<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Records by Group Name</title>
 <meta charset="utf-8">
       <title>Fullscreen Overlay Navigation | CodingNepal</title>
       <link rel="stylesheet" href="style.css">
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
         <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
           <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
           <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
           <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
           <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
           <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
           <link href="${pageContext.request.contextPath}/css/menu.css" rel="stylesheet">
           <script src="${pageContext.request.contextPath}/js/menu.js" type="text/javascript"></script>

</head>
<body>
<h2>AllGroups</h2>

<table border=1>
<tr>
<th> AccountName </th>
<th>   url   </th>
<th> Password </th>
<th></th>
</tr>
<c:forEach items="${accounts}" var="account">
<tr>
<td><c:out   value= "${account.userName}"></c:out></td>
<td><c:out   value= "${account.url}"></c:out></td>
 <td><c:out   value= "${account.password}"></c:out></td>
</tr>
</c:forEach>
</table>

<a href="/">Menu</a>

</body>
</html>