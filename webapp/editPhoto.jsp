<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

    <title>Add Photo</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/custom.css" rel="stylesheet">

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
		<a href="/" class="list-group-item">Main Page</a>	
		<a href="/photo/add" class="list-group-item">Add Photo</a>	  
	</div>
</div>
<div class="col-md-6" data-spy="scroll" data-target=".navbar" data-offset="50">

<form:form method="POST" modelAttribute="commentForm" class="form-signin" action="http://localhost:8080/photo/editWithComment">
		<form:textarea cssStyle="width:100%" path="text"/>	
		<form:hidden path="photo" />
		<button class="btn btn-lg btn-primary btn-block" type="submit" value="Add comment">Add comment</button>	
</form:form>
    <img  src="data:image/jpeg;base64,${photoForm.encodedImage}"  width="100%" height=""  alt="${photoForm.name}"/>
    <span>Creator : ${photoForm.creator.name}  Creation date : ${photoForm.date} </span>
    <br/>
    <h2>Comments</h2>
    <c:forEach var="comment" items="${photoForm.comments}">
    <hr>
        <div>${comment.date}</div>
        <div>${comment.text}</div>         
    </c:forEach>
</div>
<div class="col-md-3">
 <a onclick="document.forms['logoutForm'].submit()">Logout</a>
 <form id="logoutForm" method="POST" action="${contextPath}/logout"> 		
    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<form:form method="POST" modelAttribute="photoForm" class="form-signin"  >
		<div>Share photo with users :</div>
			<ul class="list-group">
			<form:checkboxes element="li class='list-group-item'" path="sharedUsers" items="${allUsers}" itemLabel="name"/>
		</ul>
		<c:if test="isCreator" >		
			<button class="btn btn-lg btn-primary btn-block" type="submit" value="Upload">Share photo</button>
		</c:if> 
	</form:form>
</div>
</c:if>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>