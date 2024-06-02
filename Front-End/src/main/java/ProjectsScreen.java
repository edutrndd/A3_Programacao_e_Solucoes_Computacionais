package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProjectsScreen extends JFrame {
    private ProjectDAO projectDAO;
    private JTextArea projectsDisplayArea;

    public ProjectsScreen() {
        projectDAO = new ProjectDAO();

        setTitle("Projects");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));

        JButton createTaskButton = new JButton("Create Task");
        createTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskScreen taskScreen = new TaskScreen();
                taskScreen.openCreateTaskWindow();
            }
        });
        buttonPanel.add(createTaskButton);

        JButton createProjectButton = new JButton("Create Project");
        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCreateProjectWindow();
            }
        });
        buttonPanel.add(createProjectButton);

        add(buttonPanel, BorderLayout.NORTH);

        projectsDisplayArea = new JTextArea();
        add(new JScrollPane(projectsDisplayArea), BorderLayout.CENTER);

        updateProjectsDisplay();
    }

    private void openCreateProjectWindow() {
        JFrame createProjectWindow = new JFrame("Create Project");
        createProjectWindow.setSize(300, 200);
        createProjectWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createProjectWindow.setLayout(new GridLayout(5, 2));

        createProjectWindow.add(new JLabel("Project Name:"));
        JTextField projectNameField = new JTextField();
        createProjectWindow.add(projectNameField);

        createProjectWindow.add(new JLabel("Description:"));
        JTextField descriptionField = new JTextField();
        createProjectWindow.add(descriptionField);

        createProjectWindow.add(new JLabel("Start Date (YYYY-MM-DD):"));
        JTextField startDateField = new JTextField();
        createProjectWindow.add(startDateField);

        createProjectWindow.add(new JLabel("End Date (YYYY-MM-DD):"));
        JTextField endDateField = new JTextField();
        createProjectWindow.add(endDateField);

        JButton createProjectButton = new JButton("Create Project");
        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String projectName = projectNameField.getText();
                String description = descriptionField.getText();
                String startDate = startDateField.getText();
                String endDate = endDateField.getText();

                Project project = new Project(projectName, description, startDate, endDate);
                projectDAO.addProject(project);
                updateProjectsDisplay();
                createProjectWindow.dispose();
            }
        });
        createProjectWindow.add(createProjectButton);

        createProjectWindow.setVisible(true);
    }

    private void updateProjectsDisplay() {
        List<Project> projects = projectDAO.getAllProjects();
        StringBuilder sb = new StringBuilder();
        for (Project project : projects) {
            sb.append("Project Name: ").append(project.getName()).append("\n");
            sb.append("Description: ").append(project.getDescription()).append("\n");
            sb.append("Start Date: ").append(project.getStartDate()).append("\n");
            sb.append("End Date: ").append(project.getEndDate()).append("\n\n");
        }
        projectsDisplayArea.setText(sb.toString());
    }
}
