package com.example.manager;

import com.example.db.DBConnectionProvider;
import com.example.model.Lesson;
import com.example.model.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonManager {
    private static final Connection CONNECTION = DBConnectionProvider.getInstance().getConnection();

    public void addLesson(Lesson lesson) throws SQLException {
        String query = "INSERT INTO lesson (name,duration,lecturer_name,price,user_id) VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setInt(2, lesson.getDuration());
            preparedStatement.setString(3, lesson.getLecturerName());
            preparedStatement.setDouble(4, lesson.getPrice());
            preparedStatement.setInt(5, lesson.getUser().getId());
            preparedStatement.executeUpdate();
        }
    }

    public static Lesson getLessonById(int id) {
        String query = "SELECT * FROM lesson WHERE id = " + id;
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int duration = resultSet.getInt("duration");
                String lecturerName = resultSet.getString("lecturer_name");
                double price = resultSet.getDouble("price");
                int userId = resultSet.getInt("user_id");
                User userById = UserManager.getUserById(userId);
                return Lesson.builder().id(id).name(name).duration(duration).lecturerName(lecturerName).price(price).user(userById).build();
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteLessonById(int id) throws SQLException {
        String query = "DELETE FROM lesson WHERE id = " + id;
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(query);
        }
    }

    public List<Lesson> getAllLessonsByUserId(int userId) {
        List<Lesson> lessons = new ArrayList<>();
        String query = "SELECT * FROM lesson WHERE user_id = " + userId;
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int duration = resultSet.getInt("duration");
                String lecturerName = resultSet.getString("lecturer_name");
                double price = resultSet.getDouble("price");
                User userById = UserManager.getUserById(userId);
                lessons.add(Lesson.builder().id(id).name(name).duration(duration).lecturerName(lecturerName).price(price).user(userById).build());
            }
            return lessons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Lesson getLessonByUserAndName(String name, int userId) {
        String query = "SELECT * FROM lesson WHERE name = ? AND user_id = ? ";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int duration = resultSet.getInt("duration");
                String lecturerName = resultSet.getString("lecturer_name");
                double price = resultSet.getDouble("price");
                User userById = UserManager.getUserById(userId);
                return Lesson.builder().id(id).name(name).duration(duration).lecturerName(lecturerName).price(price).user(userById).build();
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
