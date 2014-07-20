<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Start a Club</title>
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
				<li class="active"><a href="AddClub">Add Club</a></li>
			</ul>
		</div>
		</header>
		<!-- row 2 -->
		<div class="row">
			<div class="col-md-12">

				<h2>Start a new BookClub</h2>

				<form method="post" action="AddClub" role="form">
					<div class="form-group">
						<label for="name">Club Name</label><input class="form-control" type="text" name="name" />
					</div>
					<div class="form-group">
					<label>Select a book:</label><select  class="form-control" name="bookId">
						<c:forEach items="${bookList }" var="book">
							<option value="${book.bookId }">${book.title }</option>
						</c:forEach>
					</select>
					</div>
					<button class="btn btn-info" type="submit">Create Club</button>
				</form>
			</div>
		</div>
</body>
</html>