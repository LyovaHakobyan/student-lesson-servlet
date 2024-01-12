package com.example.manager;

import com.example.db.DBConnectionProvider;
import com.example.model.Lesson;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LessonManager {
    private static final Connection CONNECTION = DBConnectionProvider.getInstance().getConnection();

    public void addLesson(Lesson lesson) throws SQLException {
        String query = "INSERT INTO lesson (name,duration,lecturer_name,price) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setInt(2, lesson.getDuration());
            preparedStatement.setString(3, lesson.getLecturerName());
            preparedStatement.setDouble(4, lesson.getPrice());
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
                return Lesson.builder().id(id).name(name).duration(duration).lecturerName(lecturerName).price(price).build();
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

    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = new ArrayList<>();
        String query = "SELECT * FROM lesson";
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int duration = resultSet.getInt("duration");
                String lecturerName = resultSet.getString("lecturer_name");
                double price = resultSet.getDouble("price");
                lessons.add(Lesson.builder().id(id).name(name).duration(duration).lecturerName(lecturerName).price(price).build());
            }
            return lessons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
