package com.example.manager;

import com.example.db.DBConnectionProvider;
import com.example.model.Lesson;
import com.example.model.Student;
import com.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private static final Connection CONNECTION = DBConnectionProvider.getInstance().getConnection();

    public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO student (name,surname,email,age,img_name,lesson_id,user_id) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setString(5, student.getImgName());
            if (student.getLesson() != null) {
                preparedStatement.setInt(6, student.getLesson().getId());
            } else {
                preparedStatement.setString(6, null);
            }
            preparedStatement.setInt(7, student.getUser().getId());
            preparedStatement.executeUpdate();
        }
    }

    public static Student getStudentById(int id) {
        String query = "SELECT * FROM student WHERE id = " + id;
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                String imgName = resultSet.getString("img_name");
                int lessonId = resultSet.getInt("lesson_id");
                int userId = resultSet.getInt("user_id");
                User userById = UserManager.getUserById(userId);
                Lesson lessonById = LessonManager.getLessonById(lessonId);
                return Student.builder().id(id).name(name).surname(surname).email(email).age(age).imgName(imgName).lesson(lessonById).user(userById).build();
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStudentById(int id) throws SQLException {
        String query = "DELETE FROM student WHERE id = " + id;
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(query);
        }
    }

    public List<Student> getAllStudentsByUserId(int userId) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM student WHERE user_id = " + userId;
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                int lessonId = resultSet.getInt("lesson_id");
                Lesson lessonById = LessonManager.getLessonById(lessonId);
                int age = resultSet.getInt("age");
                String imgName = resultSet.getString("img_name");
                User userById = UserManager.getUserById(userId);
                students.add(Student.builder().id(id).name(name).surname(surname).email(email).age(age).imgName(imgName).lesson(lessonById).user(userById).build());
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteLessonFromStudent(int lessonId) {
        String query = "UPDATE student SET lesson_id = NULL WHERE lesson_id = " + lessonId;
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Student getStudentByUserAndEmail(int userId, String email) {
        String query = "SELECT * FROM student WHERE user_id = ? AND email = ? ";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                String imgName = resultSet.getString("img_name");
                int lessonId = resultSet.getInt("lesson_id");
                User userById = UserManager.getUserById(userId);
                Lesson lessonById = LessonManager.getLessonById(lessonId);
                return Student.builder().id(id).name(name).surname(surname).email(email).age(age).imgName(imgName).lesson(lessonById).user(userById).build();
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
