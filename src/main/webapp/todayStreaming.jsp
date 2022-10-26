<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Kyro TV</title>
</head>
<h3>Today streaming shows</h3><br> 
 
<table border="2" width="70%" cellpadding="2">
    <tr>
    <th>Image</th>
        <th>Show Name</th>
        <th>Air Time</th>
     </tr>
      <c:forEach var="todayShows" items="${todayShows}">
     <tr>
     <td><a href="${todayShows.url}"><img alt="${todayShows.name}" src="${todayShows.show.image.medium}"></a></td>
     	<td><a href="${todayShows.url}">${todayShows.name}</a></td>
     	<td>${todayShows.airtime}</td>
 	</tr>
 	</c:forEach>
 </table> 
</html>