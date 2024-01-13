package com.example.servlet;

import com.example.manager.LessonManager;
import com.example.manager.StudentManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/deleteLesson")
public class DeleteLessonServlet extends HttpServlet {
    private final LessonManager lessonManager = new LessonManager();
    private final StudentManager studentManager = new StudentManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        studentManager.deleteLessonFromStudent(id);
        try {
            lessonManager.deleteLessonById(id);
        } catch (SQLException e) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/lesson.jsp");
            requestDispatcher.forward(req,resp);
        }
        resp.sendRedirect("/lessons");
    }
}
