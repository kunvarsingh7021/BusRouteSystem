<%@page import="com.mongodb.client.*"%>
<%@page import="org.bson.Document"%>
<%@page import="db.MongoDBUtil"%>

<!DOCTYPE html>
<html>
<head>
<title>Bus Stops</title>

<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<link rel="stylesheet" href="style.css">
</head>
<body>

<h2><i class="fa-solid fa-location-dot"></i> Bus Stop Management</h2>

<form action="addStop" method="post" class="card">
    <input name="stop" placeholder="Stop Name" required>
    <button>
        <i class="fa-solid fa-plus"></i> Add Stop
    </button>
</form>

<h3><i class="fa-solid fa-list"></i> Available Stops</h3>

<table>
<tr><th>Stop Name</th></tr>

<%
MongoCollection<Document> stops =
    MongoDBUtil.getDatabase().getCollection("stops");

for (Document d : stops.find()) {
%>
<tr>
    <td><%= d.getString("stop") %></td>
</tr>
<% } %>
</table>

</body>
</html>
