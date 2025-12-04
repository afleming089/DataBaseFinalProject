<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ page
import="java.util.List" %> <%@ page
import="org.example.studentapp.model.Student" %> <% List<Student>
  studentList = (List<Student
    >) request.getAttribute("student"); %>

    <!DOCTYPE html>
    <html>
      <head>
        <title>Student List</title>
        <style>
          body {
            font-family: Arial, sans-serif;
            margin: 20px;
          }
          h1 {
            color: #333;
          }
          table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 10px;
          }
          th,
          td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
          }
          th {
            background-color: #f2f2f2;
          }
          a.button {
            display: inline-block;
            padding: 6px 12px;
            margin-bottom: 10px;
            text-decoration: none;
            background-color: #4caf50;
            color: white;
            border-radius: 4px;
          }
          a.button:hover {
            background-color: #45a049;
          }
          .actions a {
            margin-right: 8px;
          }
        </style>
        <script>
          function confirmDelete(id) {
            if (confirm("Are you sure you want to delete this student?")) {
              window.location.href = "students?action=DELETE&id=" + id;
            }
          }
        </script>
      </head>
      <body>
        <h1>Student Registration - Student List</h1>

        <a href="students?action=NEW" class="button">Add New Student</a>

        <table>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
            <th>Country</th>
            <th>Actions</th>
          </tr>

          <% if (studentList != null && !studentList.isEmpty()) { for (Student s
          : studentList) { %>
          <tr>
            <td><%= s.getId() %></td>
            <td><%= s.getName() %></td>
            <td><%= s.getEmail() %></td>
            <td><%= s.getCourse() %></td>
            <td><%= s.getCountry() %></td>
            <td class="actions">
              <a href="students?action=EDIT&id=<%= s.getId() %>">Edit</a>
              <a
                href="javascript:void(0);"
                onclick="confirmDelete(<%= s.getId() %>)"
                >Delete</a
              >
            </td>
          </tr>
          <% } } else { %>
          <tr>
            <td colspan="6">No students found.</td>
          </tr>
          <% } %>
        </table>
      </body>
    </html>
  </Student></Student
>
