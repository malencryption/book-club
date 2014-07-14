<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Post</title>
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
				<li class="active"><a href="AddPost">Add Post</a></li>
			</ul>
		</div>
		</header>
		<!-- row 2 -->
		<div class="row">
			<div class="col-md-3">

				<h2>Add New Post</h2>
				<form role="form" method="POST" action="AddPost">
					<div class="form-group">
						<label for="title">Title:</label> <input class="form-control"
							type="text" name="title" />
					</div>
					<div class="form-group">
						<label for="content">Content:</label>
						<textarea class="form-control" name="content"></textarea>
					</div>
					<input type="hidden" name="clubId" value="${param.clubId }" />
					<button class="btn btn-info" type="submit">Add Post</button>
				</form>
				<p>
					<a href="LoadPosts">View Posts</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>