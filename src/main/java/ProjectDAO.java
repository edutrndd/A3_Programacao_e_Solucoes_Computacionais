package main.java;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public void addProject(Project project) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO projects (company, start_date, end_date, description) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, project.getCompany());
            preparedStatement.setString(2, project.getStartDate());
            preparedStatement.setString(3, project.getEndDate());
            preparedStatement.setString(4, project.getDescription());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM projects")) {

            while (resultSet.next()) {
                String company = resultSet.getString("company");
                String startDate = resultSet.getString("start_date");
                String endDate = resultSet.getString("end_date");
                String description = resultSet.getString("description");

                Project project = new Project(company, startDate, endDate, description);
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }
}
