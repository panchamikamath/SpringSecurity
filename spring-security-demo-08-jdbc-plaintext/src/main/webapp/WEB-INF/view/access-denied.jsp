<!DOCTYPE html>
<html>
<head>
	<title>Access Denied Page</title>
	<style>
	.failed {
			color: red;
		}
	</style>
</head>
<body>
	<h2>Access Denied Page</h2>
	<p class="failed">You are not authorized to access this Page</p>
	<br>
	<hr>
	<a href="${pageContext.request.contextPath}/">Back to Home Page</a>
</body>
</html>