package com.example.servlet;

import com.example.manager.LessonManager;
import com.example.manager.StudentManager;
import com.example.model.Lesson;
import com.example.model.Student;
import com.example.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/addStudent")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024
)
public class AddStudentServlet extends HttpServlet {
    private final StudentManager studentManager = new StudentManager();
    private static final String STUDENT_IMG_FILE = "C:/Users/Dell/IdeaProjects/student-lesson-servlet/StudentImageFile";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        Part img = req.getPart("img");
        String imgName = null;
        if (img != null && img.getSize() > 0) {
            imgName = System.currentTimeMillis() + "_" + img.getSubmittedFileName();
            img.write(STUDENT_IMG_FILE + File.separator + imgName);
        }
        int lessonId = 0;
        String lessonIdStr = req.getParameter("lessonId");
        if (lessonIdStr != null) {
            lessonId = Integer.parseInt(lessonIdStr);
        }
        Lesson lessonById = LessonManager.getLessonById(lessonId);
        Student studentByUserAndEmail = studentManager.getStudentByUserAndEmail(user.getId(), email);
        if (studentByUserAndEmail == null) {
            try {
                studentManager.addStudent(Student.builder().name(name).surname(surname).email(email).age(age).imgName(imgName).lesson(lessonById).user(user).build());
                resp.sendRedirect("/students");
            } catch (SQLException e) {
                resp.sendRedirect("/students");
            }
        } else {
            req.getSession().setAttribute("msg", "Student,By This Email Already Exists !");
            resp.sendRedirect("/students");
        }
    }
}
