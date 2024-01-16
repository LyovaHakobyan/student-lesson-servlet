package com.example.servlet;

import com.example.manager.LessonManager;
import com.example.manager.StudentManager;
import com.example.model.Lesson;
import com.example.model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/students")
public class StudentServlet extends HttpServlet {
    private final StudentManager studentManager = new StudentManager();
    private final LessonManager lessonManager = new LessonManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> allStudents = studentManager.getAllStudents();
        List<Lesson> allLessons = lessonManager.getAllLessons();
        req.getSession().setAttribute("allStudents",allStudents);
        req.getSession().setAttribute("allLessons",allLessons);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/student.jsp");
        requestDispatcher.forward(req, resp);
    }
}
