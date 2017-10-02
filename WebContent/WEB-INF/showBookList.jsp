<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book List</title>
</head>
<body>
	<h1>This are the books from the database</h1>

	<hr>
	<c:choose>
		<c:when test="${bookList == null or bookList.isEmpty()}">
		    The List is empty.
		</c:when>
		<c:otherwise>
			<table border=1>

				<tr>
					<th>Title</th>
					<th>Description</th>
					<th>Price</th>
					<th>Author FN</th>
					<th>Author LN</th>
				</tr>
				<c:forEach var="book" items="${bookList}">
					<tr>
						<td>${book.title}</td>
						<td>${book.description}</td>
						<td>${book.price}</td>
						<td>${book.authorFirstName}</td>
						<td>${book.authorLastName}</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<hr>
	<a href='index.html'>Main Page</a>
	<br>

</body>
</html>