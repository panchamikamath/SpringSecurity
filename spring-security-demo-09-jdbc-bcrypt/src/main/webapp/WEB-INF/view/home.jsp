<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>Kamath Company Home Page</title>
</head>

<body>
	<h2>Kamath Company Home Page</h2>
	<hr>
	
	<p>
	Welcome to the Kamath company home page!
	</p>
	
	<hr>
	
	<!-- display user name and role -->
	
	<p>
		User: <security:authentication property="principal.username" />
		<br><br>
		Role(s): <security:authentication property="principal.authorities" />
	</p>
	
	<!-- Add a link to point to /leaders... this is for managers  -->
	
	<!--  Show link only to required users -->
	<security:authorize access="hasRole('MANAGER')">
		<p>
			<a href="${pageContext.request.contextPath}/leaders">Leaders Page</a> 
			(Only for managers)
		</p>
	</security:authorize>
	
	<!-- Add a link to point to /systems... this is for Admins  -->
	
	<security:authorize access="hasRole('ADMIN')">
	<p>
		<a href="${pageContext.request.contextPath}/systems">Systems Page</a> 
		(Only for Admins)
	</p>
	</security:authorize>
	
	<hr>
	
	
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
	</form:form>
	
</body>

</html>









