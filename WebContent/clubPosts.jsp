<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Club View</title>
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
			<div class="col-md-9 col-xs-9">
			<ul class="nav nav-tabs" role="tablist">
				<li><a href="index.jsp">Login</a></li>
				<li><a href="HomePosts">Home Feed</a></li>
				<li class="active"><a href="clubPosts">Club View</a></li>
			</ul>
				</div>
			<div class="col-md-3 col-xs-3">
				<ul class="nav nav-tabs" role="tablist">
					<li><a href="Logout">Logout</a></li>
				</ul>
			</div>
		</div>
		</header>
		<!-- row 2 -->
		<div class="row">
			<div class="col-md-6">
				<h2>Club: ${clubName}</h2>
				<c:if test="${clubStatus == false } && ${!homeStatus} "> 
				<p><a href="JoinClub?clubId=${param.clubId }">Join Club</a></p>
				</c:if>
				<p>${param.msg}</p>
				<p><a href="AddPost?clubId=${param.clubId }">New Post</a></p>
				<h3>Posts:</h3>
				<c:forEach items="${clubPostList}" var="post">
					<div>
						<h3>${post.title }</h3>
						<h4>${post.date}</h4>
						<p>${post.content}</p>
					<a class="btn btn-info" href="ViewPost?postId=${post.postId }">View Post</a>
					</div>
				</c:forEach>

			</div>
		</div>
	</div>
	<!-- end container -->
</body>
</html>