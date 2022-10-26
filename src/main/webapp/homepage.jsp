<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Kyro TV</title>
</head>
<h1>Hi, ${userName} welcome</h1>

<form action="showRecommendations">
	<input type="submit" value="Recommend Shows">
</form>
<h4>Your Recommendations</h4>
<table border="2" width="70%" cellpadding="2">
	<tr>
		<th>Image</th>
		<th>Show Name</th>
		<th>Language</th>
		<th>Rating</th>
	</tr>
	<c:forEach var="recommendedShows" items="${recommendedShows}">
		<tr>
			<td><a href="${recommendedShows.url}"><img alt="${recommendedShows.name}"
				src="${recommendedShows.image.medium}"></a></td>
			<td><a href="${recommendedShows.url}">${recommendedShows.name}</a></td>
			<td>${recommendedShows.language}</td>
			<td>${recommendedShows.rating.average}</td>
		</tr>
	</c:forEach>
</table>
<br>
<form action="recommendationHistory">
	<input type="submit" value="My Recomendation History">
</form>
<form action="logout">
	<input type="submit" value="Log out">
</form>
</html>