package org.example.studentapp.servlet;

import com.mysql.cj.PreparedQuery;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.studentapp.doa.StudentDoa;
import org.example.studentapp.model.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    // **
    // @parm
    // PreparedQuery
    // @parm resp
    // @throws ServletException
    // @thorws IOException
    // ?action=LIST (displays all the students this also has to be the default
    // action)
    // ?action=New (create a new student by inserting that record into Database)
    // ?action=Edit &ID (displays edit form)
    // ?action=Deleates$id (deleate the record from student table)
    // for post
    // action=instert
    // action=update
    //

    private StudentDoa studentDoa;

    @Override
    public void init() throws ServletException {
        studentDoa = new StudentDoa();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "LIST";
        }

        try {
            switch (action) {
                case "LIST":
                    listStudents(req, resp);
                    break;
                case "NEW":
                    showNewForm(req, resp);
                    break;
                case "EDIT":
                    showEditForm(req, resp);
                    break;
                case "DELETE":
                    deleteStudent(req, resp);
                    break;
                default:
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
            // creating an error repsonse
            req.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "LIST";
        }
        try {
            switch (action) {
                case "INSERT":
                    insertStudent(req, resp);
                    break;
                case "UPDATE":
                    updateStudent(req, resp);
                    break;
                default:
                    listStudents(req, resp);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
            // creating an error repsonse
            req.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
            rd.forward(req, resp);
        }
    }

    private void listStudents(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Student> student = studentDoa.getAllStudent();
        req.setAttribute("student", student);
        RequestDispatcher rd = req.getRequestDispatcher("students-list.jsp");
        rd.forward(req, resp);
    }

    // showNewForm - to display a form we are trying to insert new student
    // information
    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        RequestDispatcher rd = req.getRequestDispatcher("student-form.jsp");
        rd.forward(req, resp);
    }

    // ShowEditform to display a form with prepoplated data existing studnet so that
    // user can update that
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        Student exstingStudent = studentDoa.getStudentById(id);
        req.setAttribute("student", exstingStudent);
        RequestDispatcher rd = req.getRequestDispatcher("student-form.jsp");
        rd.forward(req, resp);
    }

    // insertStudent - to nsert a new record into database
    private void insertStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String course = req.getParameter("course");
        String country = req.getParameter("country");
        Student student = new Student(name, email, course, country);
        studentDoa.insertStudent(student);
        resp.sendRedirect("students?action=LIST");
        // RequestDispatcher rd = req.getRequestDispatcher("students-list.jsp");
        // rd.forward(req, resp);
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String course = req.getParameter("course");
        String country = req.getParameter("country");
        Student student = new Student(id, name, email, course, country);
        studentDoa.updateStudent(student);
        resp.sendRedirect("students?action=LIST");
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        studentDoa.deleteStudent(id);
        resp.sendRedirect("students?action=LIST");
    }
}
