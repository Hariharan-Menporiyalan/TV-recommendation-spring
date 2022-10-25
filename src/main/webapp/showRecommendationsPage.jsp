<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Kyro TV</title>
</head>
<h1> Hi, ${userName} welcome</h1>

<form action="showRecommendations">
<input type="submit" value="Recommend Shows">
</form>
<h4>Your Recommendations</h4> 
<table border="2" width="70%" cellpadding="2">
	<tr>
		<th>Image</th>
		<th>Show Name</th>
		<th>Rating</th>
	</tr>
	<c:forEach var="recommendedShows" items="${recommendedShows}">
		<tr>
		<td><img alt="${recommendedShows.name}" src="${recommendedShows.image.medium}"></td>
			<td>${recommendedShows.name}</td>
			<td>${recommendedShows.rating.average}</td>
		</tr>
	</c:forEach>
</table>
</html>