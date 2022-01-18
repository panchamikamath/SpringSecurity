<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>App Home Page</title>
</head>
<body>
	<h2>Welcome to home page</h2>
	
	<form:form action="${pageContext.request.contextPath}/logout" 
		method="POST">
		
		<input type="submit" value="Logout"/>
	</form:form>
</body>
</html>