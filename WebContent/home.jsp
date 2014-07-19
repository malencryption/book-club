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
				<li><a href="index.jsp">Login</a></li>
				<li class="active"><a href="HomePosts">Home Feed</a></li>
				<li><a href="ClubPosts">Club View</a></li>
			</ul>
		</div>
		</header>
		<!-- row 2 -->
		<div class="row">
			<div class="col-md-12">
				<h2>Home Feed</h2>
				<p>
					<a href="AddPost?clubId=${clubId}">New Post</a>
				</p>
				<c:forEach items="${homePostList}" var="post">
					<div>
						<h3>${post.title}</h3>
						<h4>${post.date}</h4>
						<p>${post.content}</p>
						<a class="btn btn-info" href="ViewPost?postId=${post.postId }">View
							Post</a>
					</div>
				</c:forEach>

			</div>
		</div>
		<!-- row 3 -->
		<div class="row">
			<div class="col-md-12">
				<h2>Your BookClubs</h2>

				<c:forEach items="${userClubList}" var="club">
					<div>
						<h3>${club.name}</h3>
						<h4>${club.dateCreated}</h4>
						<p> <span class="glyphicon glyphicon-book"></span> ${club.bookTitle}</p>
						<a class="btn btn-info" href="clubPosts?clubId=${club.clubId }">View
							Club</a>
					</div>
				</c:forEach>
			</div>
		</div>
		<!-- row 4 -->
		<div class="row">
			<div class="col-md-12">
				<h2>All BookClubs</h2>
				<p>
					<a href="AddClub">Start a new BookClub</a>
				</p>
				<c:forEach items="${clubList}" var="club">
					<div>
						<h3>${club.name}</h3>
						<h4>${club.dateCreated}</h4>
						<h4> <span class="glyphicon glyphicon-book"></span> ${club.bookTitle}</h4>
						<a class="btn btn-info" href="viewClub?clubId=${club.clubId }">View
							Club</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- end container -->
</body>
</html>