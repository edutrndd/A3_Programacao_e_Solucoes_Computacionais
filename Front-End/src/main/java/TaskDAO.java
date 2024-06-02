package main.java;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public void addTask(Task task) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tasks (start_date, end_date, description) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, task.getStartDate());
            preparedStatement.setString(2, task.getEndDate());
            preparedStatement.setString(3, task.getDescription());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
    
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks")) {
    
            while (resultSet.next()) {
                String startDate = resultSet.getString("start_date");
                String endDate = resultSet.getString("end_date");
                String description = resultSet.getString("description");
    
                Task task = new Task(startDate, endDate, description);
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Em caso de exceção, retorna uma lista vazia para evitar o erro
            return tasks;
        }
    
        return tasks; // Retorna a lista de tarefas preenchida
    }
}   