<%@page import="com.mongodb.client.*"%>
<%@page import="org.bson.Document"%>
<%@page import="org.bson.types.ObjectId"%>
<%@page import="db.MongoDBUtil"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>System Data Overview</title>

    <!-- FONT AWESOME (ONLINE ICONS) -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <link rel="stylesheet" href="style.css">

    <style>
        .section { margin-top: 40px; }
        .section h3 { margin-bottom: 15px; color: #1e3a8a; }
        table { width: 85%; margin: auto; }
        .delete-btn {
            background: #ef4444;
            color: white;
            border: none;
            padding: 6px 10px;
            border-radius: 6px;
            cursor: pointer;
        }
        .delete-btn i { margin-right: 4px; }
    </style>
</head>

<body>

<h2>
    <i class="fa-solid fa-database"></i>
    System Data Overview
</h2>

<!-- ================= STUDENTS ================= -->
<div class="section">
<h3><i class="fa-solid fa-user-graduate"></i> Students</h3>

<table>
<tr>
    <th>Name</th>
    <th>Pickup Stop</th>
    <th>Action</th>
</tr>

<%
MongoCollection<Document> students =
    MongoDBUtil.getDatabase().getCollection("students");

for (Document d : students.find()) {
    ObjectId id = d.getObjectId("_id");
%>
<tr>
<td><%= d.getString("name") %></td>
<td><%= d.getString("stop") %></td>
<td>
    <form action="deleteStudent" method="post">
        <input type="hidden" name="id" value="<%= id.toHexString() %>">
        <button class="delete-btn">
            <i class="fa-solid fa-trash"></i> Delete
        </button>
    </form>
</td>
</tr>
<% } %>
</table>
</div>

<!-- ================= STOPS ================= -->
<div class="section">
<h3><i class="fa-solid fa-location-dot"></i> Bus Stops</h3>

<table>
<tr>
    <th>Stop Name</th>
    <th>Action</th>
</tr>

<%
MongoCollection<Document> stops =
    MongoDBUtil.getDatabase().getCollection("stops");

for (Document d : stops.find()) {
    ObjectId id = d.getObjectId("_id");
%>
<tr>
<td><%= d.getString("stop") %></td>
<td>
    <form action="deleteStop" method="post">
        <input type="hidden" name="id" value="<%= id.toHexString() %>">
        <button class="delete-btn">
            <i class="fa-solid fa-trash"></i> Delete
        </button>
    </form>
</td>
</tr>
<% } %>
</table>
</div>

<!-- ================= ROUTES ================= -->
<div class="section">
<h3><i class="fa-solid fa-road"></i> Routes</h3>

<table>
<tr>
    <th>From</th>
    <th>To</th>
    <th>Distance</th>
    <th>Action</th>
</tr>

<%
MongoCollection<Document> routes =
    MongoDBUtil.getDatabase().getCollection("routes");

for (Document r : routes.find()) {
    ObjectId id = r.getObjectId("_id");
%>
<tr>
<td><%= r.getString("from") %></td>
<td><%= r.getString("to") %></td>
<td><%= r.getInteger("distance") %> km</td>
<td>
    <form action="deleteRoute" method="post">
        <input type="hidden" name="id" value="<%= id.toHexString() %>">
        <button class="delete-btn">
            <i class="fa-solid fa-trash"></i> Delete
        </button>
    </form>
</td>
</tr>
<% } %>
</table>
</div>

<p style="margin-top:30px;color:#475569;">
    <i class="fa-solid fa-circle-info"></i>
    Deletions are permanent and reflected instantly in MongoDB
</p>

</body>
</html>
