<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Group View</title>
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
				<li><a href="home.jsp"></a>Home Feed</li>
				<li class="active"><a href="ClubPosts"></a>Club View</li>
			</ul>
		</div>
		</header>
		<!-- row 2 -->
		<div class="row">
			<div class="col-md-6">
				<h2>Group Title</h2>
				<div>
					<h3>Example Post Title</h3>
					<h4>Post Date, Username</h4>
					<p>Post Content</p>
					<form action="Comment" method="POST">
						<textarea>Enter comment here...</textarea>
						<button class="btn btn-info" type="submit">Submit</button>
					</form>
					<button class="btn btn-info">View Post</button>
				</div>
				<c:forEach items="${clubPostList}" var="post">
					<div>
						<h3>${post.title }</h3>
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
	</div>
	<!-- end container -->
</body>
</html>