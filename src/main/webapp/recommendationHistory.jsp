<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Kyro TV</title>
</head>
<h1>Hi, ${userName} welcome</h1><br>
<h3>Your Recommendation History</h3><br> 
 
<table border="2" width="70%" cellpadding="2">
	<tr>
		<th>Image</th>
		<th>Show Name</th>
		<th>Language</th>
		<th>Rating</th>
	</tr>
	<c:forEach var="recommendationHistory" items="${recommendationHistory}">
		<tr>
			<td><a href="${recommendationHistory.url}"><img alt="${recommendationHistory.name}"
				src="${recommendationHistory.image.medium}"></a> </td>
			<td><a href="${recommendationHistory.url}">${recommendationHistory.name}</a></td>
			<td>${recommendationHistory.language}</td>
			<td>${recommendationHistory.rating.average}</td>
		</tr>
	</c:forEach>
</table><br>
<form action="deleteHistory">
	<input type="submit" value="Delete Recommendation History">
</form><br>
<form action="showRecommendations">
	<input type="submit" value="Back to home page">
</form><br>
<form action="logout">
	<input type="submit" value="Log out">
</form>
</html>