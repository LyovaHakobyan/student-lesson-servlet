package com.example.servlet;

import com.example.manager.LessonManager;
import com.example.manager.StudentManager;
import com.example.model.Lesson;
import com.example.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/addStudent")
public class AddStudentServlet extends HttpServlet {
    private final StudentManager studentManager = new StudentManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        int lessonId = 0;
        String lessonIdStr = req.getParameter("lessonId");
        if (lessonIdStr != null) {
            lessonId = Integer.parseInt(lessonIdStr);
        }
        Lesson lessonById = LessonManager.getLessonById(lessonId);
        try {
            studentManager.addStudent(Student.builder().name(name).surname(surname).email(email).age(age).lesson(lessonById).build());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/students");
    }
}
