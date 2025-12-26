<%@page import="com.mongodb.client.*"%>
<%@page import="org.bson.Document"%>
<%@page import="db.MongoDBUtil"%>

<!DOCTYPE html>
<html>
<head>
<title>Student Registration</title>

<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<link rel="stylesheet" href="style.css">
</head>
<body>

<h2><i class="fa-solid fa-user-graduate"></i> Student Registration</h2>

<form action="addStudent" method="post" class="card">
    <input name="name" placeholder="Student Name" required>
    <input name="stop" placeholder="Pickup Stop" required>
    <button>
        <i class="fa-solid fa-user-plus"></i> Add Student
    </button>
</form>

<h3><i class="fa-solid fa-users"></i> Registered Students</h3>

<table>
<tr>
    <th>Name</th>
    <th>Pickup Stop</th>
</tr>

<%
MongoCollection<Document> students =
    MongoDBUtil.getDatabase().getCollection("students");

for (Document d : students.find()) {
%>
<tr>
    <td><%= d.getString("name") %></td>
    <td><%= d.getString("stop") %></td>
</tr>
<% } %>
</table>

</body>
</html>
