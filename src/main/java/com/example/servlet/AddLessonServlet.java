package com.example.servlet;

import com.example.manager.LessonManager;
import com.example.model.Lesson;
import com.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/addLesson")
public class AddLessonServlet extends HttpServlet {
    private final LessonManager lessonManager = new LessonManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int duration = Integer.parseInt(req.getParameter("duration"));
        String lecturerName = req.getParameter("lecturerName");
        double price = Double.parseDouble(req.getParameter("price"));
        User user = (User) req.getSession().getAttribute("user");
        Lesson lessonByUserAndName = lessonManager.getLessonByUserAndName(name, user.getId());
        if (lessonByUserAndName == null) {
            try {
                lessonManager.addLesson(Lesson.builder().name(name).duration(duration).lecturerName(lecturerName).price(price).user(user).build());
                resp.sendRedirect("/lessons");
            } catch (SQLException e) {
                resp.sendRedirect("/lessons");
            }
        } else {
            req.getSession().setAttribute("msg", "Lesson,By This Name Already Exists !");
            resp.sendRedirect("/lessons");
        }
    }
}
