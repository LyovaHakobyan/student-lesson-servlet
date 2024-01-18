package com.example.servlet;

import com.example.manager.LessonManager;
import com.example.manager.StudentManager;
import com.example.model.Lesson;
import com.example.model.Student;
import com.example.model.User;

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
        User user = (User) req.getSession().getAttribute("user");
        List<Student> allStudents = studentManager.getAllStudentsByUserId(user.getId());
        List<Lesson> allLessons = lessonManager.getAllLessonsByUserId(user.getId());
        req.getSession().setAttribute("allStudents", allStudents);
        req.getSession().setAttribute("allLessons", allLessons);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/student.jsp");
        requestDispatcher.forward(req, resp);
    }
}
