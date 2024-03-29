package com.example.servlet;

import com.example.manager.LessonManager;
import com.example.model.Lesson;
import com.example.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/lessons")
public class LessonServlet extends HttpServlet {
    private final LessonManager lessonManager = new LessonManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Lesson> allLessons = lessonManager.getAllLessonsByUserId(user.getId());
        req.setAttribute("allLessons",allLessons);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/lesson.jsp");
        requestDispatcher.forward(req,resp);
    }
}
