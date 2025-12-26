<%@page import="com.mongodb.client.*"%>
<%@page import="org.bson.Document"%>
<%@page import="db.MongoDBUtil"%>

<!DOCTYPE html>
<html>
<head>
<title>Route Management</title>

<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<link rel="stylesheet" href="style.css">
</head>
<body>

<h2><i class="fa-solid fa-road"></i> Route Management</h2>

<form action="addRoute" method="post" class="card">
    <input name="from" placeholder="From Stop" required>
    <input name="to" placeholder="To Stop" required>
    <input name="distance" placeholder="Distance (km)" required>
    <button>
        <i class="fa-solid fa-plus"></i> Add Route
    </button>
</form>

<h3><i class="fa-solid fa-map"></i> Route List</h3>

<table>
<tr>
    <th>From</th>
    <th>To</th>
    <th>Distance (km)</th>
</tr>

<%
MongoCollection<Document> routes =
    MongoDBUtil.getDatabase().getCollection("routes");

for (Document r : routes.find()) {
%>
<tr>
    <td><%= r.getString("from") %></td>
    <td><%= r.getString("to") %></td>
    <td><%= r.getInteger("distance") %></td>
</tr>
<% } %>
</table>

<p style="color:#475569;">
    Routes are dynamically loaded from MongoDB
</p>

</body>
</html>
