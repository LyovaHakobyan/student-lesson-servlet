package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(urlPatterns = "/downloadImg")
public class DownloadImgServlet extends HttpServlet {
    private static final String STUDENT_IMG_FILE = "C:/Users/Dell/IdeaProjects/student-lesson-servlet/StudentImageFile";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imgName = req.getParameter("imgName");
        File file = new File(STUDENT_IMG_FILE + File.separator + imgName);
        if (file.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                resp.setContentType("image/jpeg");
                resp.setContentLength((int) file.length());
                ServletOutputStream outputStream = resp.getOutputStream();
                byte[] bytes = new byte[4096];
                int readByte;
                while ((readByte = fileInputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, readByte);
                }
            }
        }
    }
}
