<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Error</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        margin: 20px;
        color: #b00020;
      }
      h1 {
        color: #b00020;
      }
      a {
        color: #0066cc;
      }
    </style>
  </head>
  <body>
    <h1>Something went wrong</h1>
    <p>An error occurred while processing your request.</p>

    <p>
      <strong>Details:</strong>
      <%= request.getAttribute("errorMessage") != null ?
      request.getAttribute("errorMessage") : "No additional details." %>
    </p>

    <p><a href="students?action=LIST">Back to Student List</a></p>
  </body>
</html>
