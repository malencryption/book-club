<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<!-- Bootstrap -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/custom.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<!-- row 1 -->
		<header class="row head-section">
		<div class="col-md-9">
			<p class="little-head">Welcome to the</p>
			<h1>
				Book<span class="txt-blue">Club</span>App
			</h1>
		</div>
		<!-- internal row -->
		<div class="row">
			<div class="col-md-12">
				<ul class="nav nav-tabs" role="tablist">
					<li class="active"><a href="index.jsp">Login</a></li>
					<li><a href="HomePosts">Home Feed</a></li>
					<li><a href="ClubPosts">Club View</a></li>
				</ul>
			</div>
		</div>
		</header>
		<!-- row 2 -->
		<div class="row main-section">
			<div class="col-md-6 col-xs-12">
				<h2>Login</h2>
				<form role="form" action="Login" method="POST">
					<div class="form-group">
						<label for="email">Email:</label> <input class="form-control"
							type="email" name="email" />
					</div>
					<div class="form-group">
						<label for="password">Password:</label> <input
							class="form-control" type="password" name="password" />
					</div>
					<button class="btn btn-info" type="submit">Login</button>
				</form>
			</div>
			<div class="col-md-6 col-xs-12">
				<h2>-OR-</h2>
				<p>click here to</p>
				<a class="btn btn-info" href="SignIn"> Login with Facebook</a>
				<p>click here to</p>
				<a class="btn btn-info" href="register.jsp"> Register with
					BookClub</a>
			</div>
		</div>
	</div>
	<!-- end container -->
</body>
</html>