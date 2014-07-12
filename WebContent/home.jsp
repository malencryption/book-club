<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<!-- Bootstrap -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/custom.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<!-- row 1 -->
		<header class="row">
		<div class="col-md-12">
			<p class="little-head">Welcome to the</p>
			<h1>Book Club App</h1>
		</div>
		<!-- internal row -->
		<div class="row">
			<ul class="nav nav-tabs" role="tablist">
				<li><a href="index.jsp"></a>Login</li>
				<li class="active"><a href="home.jsp"></a>Home Feed</li>
				<li><a href="group.jsp"></a>Group View</li>
			</ul>
		</div>
		</header>
		<!-- row 2 -->
		<div class="row">
			<div class="col-md-6">
				<h2>Home Feed</h2>
				<c:forEach items="${homePostList}" var="post">
					<div>
						<h3>${post.title}</h3>
						<h4>${post.date}</h4>
						<p>${post.content}</p>
						<form action="Comment" method="POST">
							<textarea>Enter comment here...</textarea>
							<button class="btn btn-info" type="submit">Submit</button>
						</form>
						<button class="btn btn-info">View Post</button>
					</div>
				</c:forEach>

			</div>
		</div>
		<!-- row 3 -->
		<div class="row">
			<div class="col-md-6">
				<h2>Group Feed</h2>

				<c:forEach items="${groupList}" var="post">
					<div>
						<h3>${post.title}</h3>
						<h4>${post.date}</h4>
						<p>${post.content}</p>
						<button class="btn btn-info">View Group</button>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- end container -->
</body>
</html>