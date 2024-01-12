package com.example.servlet;

import com.example.manager.LessonManager;
import com.example.model.Lesson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/addLesson")
public class AddLessonServlet extends HttpServlet {
    private final LessonManager lessonManager = new LessonManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int duration = Integer.parseInt(req.getParameter("duration"));
        String lecturerName = req.getParameter("lecturerName");
        double price = Double.parseDouble(req.getParameter("price"));
        try {
            lessonManager.addLesson(Lesson.builder().name(name).duration(duration).lecturerName(lecturerName).price(price).build());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/lessons");
    }
}
