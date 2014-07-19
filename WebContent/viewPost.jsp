<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Post</title>
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
				<li><a href="index.jsp">Login</a></li>
				<li><a href="home.jsp">Home Feed</a></li>
				<li><a href="ClubPosts">Club View</a></li>
				<li class="active"><a href="ViewPost">Post View</a></li>
			</ul>
		</div>
		</header>
		<!-- row 2 -->
		<div class="row">
			<div class="col-md-6">
				<h2>View Post</h2>

				<div>

					<h3>${post.title}</h3>
					<h4>${post.date}</h4>
					<p>${post.content}</p>
					<c:forEach items="${commentList }" var="comment">
						<h4>${comment.date}</h4>
						<p>${comment.content}</p>
					</c:forEach>
					<form action="AddComment" method="POST">
							<textarea name="content">Enter comment here...</textarea>
							<input type="hidden" name="postId" value="${post.postId }"/>
							<button class="btn btn-info" type="submit">Submit</button>
						</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>