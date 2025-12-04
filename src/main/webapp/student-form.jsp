<%@ page import="org.example.studentapp.model.Student" %> <%@ page
contentType="text/html;charset=UTF-8" language="java" %> <% Student student =
(Student) request.getAttribute("student"); boolean isEdit = (student != null &&
student.getId() != 0); %>

<!DOCTYPE html>
<html>
  <head>
    <title><%= isEdit ? "Edit Student" : "Add New Student" %></title>
    <style>
      body {
        font-family: Arial, sans-serif;
        margin: 20px;
      }
      h1 {
        color: #333;
      }
      form {
        max-width: 400px;
      }
      label {
        display: block;
        margin-top: 8px;
      }
      input[type="text"],
      input[type="email"] {
        width: 100%;
        padding: 6px;
        margin-top: 4px;
        box-sizing: border-box;
      }
      .buttons {
        margin-top: 12px;
      }
      .buttons input,
      .buttons a {
        padding: 6px 12px;
        margin-right: 8px;
      }
      a.button {
        text-decoration: none;
        background-color: #555;
        color: white;
        border-radius: 4px;
      }
      a.button:hover {
        background-color: #333;
      }
    </style>
  </head>
  <body>
    <h1><%= isEdit ? "Edit Student" : "Add New Student" %></h1>

    <form action="students" method="post">
      <!-- action parameter tells servlet whether to INSERT or UPDATE -->
      <input type="hidden" name="action" value="<%= isEdit ? "UPDATE" : "INSERT"
      %>"/> <% if (isEdit) { %>
      <!-- Keep ID as hidden field for updates -->
      <input type="hidden" name="id" value="<%= student.getId() %>" />
      <% } %>

      <label for="name">Name:</label>
      <input type="text" id="name" name="name" required value="<%= isEdit ?
      student.getName() : "" %>"/>

      <label for="email">Email:</label>
      <input type="email" id="email" name="email" required value="<%= isEdit ?
      student.getEmail() : "" %>"/>

      <label for="course">Course:</label>
      <input type="text" id="course" name="course" required value="<%= isEdit ?
      student.getCourse() : "" %>"/>

      <label for="country">Country:</label>
      <input type="text" id="country" name="country" required value="<%= isEdit
      ? student.getCountry() : "" %>"/>

      <div class="buttons">
        <input type="submit" value="<%= isEdit ? "Update" : "Save" %>"/>
        <a href="students?action=LIST" class="button">Back to List</a>
      </div>
    </form>
  </body>
</html>
