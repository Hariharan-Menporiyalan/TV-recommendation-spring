<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Kyro TV</title>
</head>
<h3>Today streaming shows</h3>
<br>

<table border="2" width="70%" cellpadding="2">
	<tr>
		<th>Image</th>
		<th>Show Name</th>
		<th>Rating</th>
	</tr>
	<c:forEach var="searchResults" items="${searchResults}">
		<tr>
		<td><img alt="${searchResults.show.name}" src="${searchResults.show.image.medium}"></td>
			<td>${searchResults.show.name}</td>
			<td>${searchResults.show.rating.average}</td>
		</tr>
	</c:forEach>
</table>
</html>