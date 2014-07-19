<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Start a Club</title>
</head>
<body>
	<form method="post" action="addClub">
		<label for="title">Club Name</label><input type="text" name="title" />
		
		<label>Select a book:</label><select name="books">
			<c:forEach items="${bookList }" var="book">
				<option value="${book.title }">${book.title }</option>
			</c:forEach>
		</select>
		<button class="btn btn-info" type="submit">Create Club</button>
	</form>

</body>
</html>