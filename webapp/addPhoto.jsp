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

    <form:form method="POST" modelAttribute="photoForm" class="form-signin" enctype="multipart/form-data">
        <h2 class="form-signin-heading">Welcome ${pageContext.request.userPrincipal.name}</h2>
        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="name" class="form-control" placeholder="Name"></form:input>
                <form:errors path="name"></form:errors>
            </div>
        </spring:bind>
        File to upload: <form:input class="btn btn-lg btn-primary btn-block" type="file" path="file" />
	<button class="btn btn-lg btn-primary btn-block" type="submit" value="Upload">Save</button>
    
				
</form:form>
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
	</form:form>
</div>
</c:if>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
