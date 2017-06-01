<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]--> 
</head>
<body>

<c:if test="${pageContext.request.userPrincipal.name != null}">   
<div class="col-md-2">
	<div class="list-group">	
	  <a href="/photo/add" class="list-group-item">Add Photo</a>	  
	</div>
</div>
<div class="col-md-7" data-spy="scroll" data-target=".navbar" data-offset="50">

<h2>Welcome ${pageContext.request.userPrincipal.name}</h2>
	<c:forEach var="photo" items="${photos}">
		<a href="/photo/edit?id=${photo.id}" >
  			<img  src="data:image/jpeg;base64,${photo.encodedImage}"  width="100%" height=""  alt="${photo.name}"/>
		</a>
	</c:forEach>
		
</div>
<div class="col-md-2">
 <a onclick="document.forms['logoutForm'].submit()">Logout</a>
 <form id="logoutForm" method="POST" action="${contextPath}/logout"> 		
    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
 </form>
    
	<div>Users :</div>
	<div class="list-group">	
		<c:forEach var="user" items="${allUsers}">
  			 <a href="/photo/getFotoByUser?id=${user.id}" class="list-group-item" >${user.name}</a>
		</c:forEach>  
	</div>
	
</div>
</c:if>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
