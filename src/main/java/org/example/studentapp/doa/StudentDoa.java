package org.example.studentapp.doa;

import org.example.studentapp.model.Student;
import org.example.studentapp.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDoa {
    private static final String INSERT_QUERY = "insert into student(name, email, course, country) values(?,?,?,?)";
    private static final String GET_ALL_QUERY = "select id, name, email, course, country from student;";
    private static final String GET_STUDENT_BY_ID = "select id, name, email, course, country from student where id = ?";
    private static String UPDATE_STUDENT = "update student set name=?, email=?, course=?, country=? where id=?";
    private static String DELETE_STUDENT = "delete from student where id = ?";

    public void insertStudent(Student student) throws Exception {
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
        ps.setString(1, student.getName());
        ps.setString(2, student.getEmail());
        ps.setString(3, student.getCourse());
        ps.setString(4, student.getCountry());

        ps.executeUpdate();
    }

    public List<Student> getAllStudent() throws Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(GET_ALL_QUERY);
        ResultSet rs = ps.executeQuery();

        List<Student> students = new ArrayList<Student>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String course = rs.getString("course");
            String country = rs.getString("country");

            Student student = new Student(id, name, email, course, country);
            students.add(student);
        }

        return students;
    }

    public Student getStudentById(int id) throws Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(GET_STUDENT_BY_ID);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        Student student = null;

        if (rs.next()) {
            String name = rs.getString("name");
            String email = rs.getString("email");
            String course = rs.getString("course");
            String country = rs.getString("country");

            student = new Student(id, name, email, course, country);
        }

        return student;
    }

    public void updateStudent(Student student) throws Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(UPDATE_STUDENT);

        ps.setString(1, student.getName());
        ps.setString(2, student.getEmail());
        ps.setString(3, student.getCourse());
        ps.setString(4, student.getCountry());
        ps.setInt(5, student.getId());

        ps.executeUpdate();
    }

    public void deleteStudent(int id) throws Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(DELETE_STUDENT);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
