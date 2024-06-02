package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateProjectForm extends JFrame {
    private JTextField companyField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextArea descriptionArea;
    private ProjectDAO projectDAO;

    public CreateProjectForm() {
        projectDAO = new ProjectDAO();

        setTitle("Create Project");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Company:"));
        companyField = new JTextField();
        add(companyField);

        add(new JLabel("Start Date (YYYY-MM-DD):"));
        startDateField = new JTextField();
        add(startDateField);

        add(new JLabel("End Date (YYYY-MM-DD):"));
        endDateField = new JTextField();
        add(endDateField);

        add(new JLabel("Description:"));
        descriptionArea = new JTextArea();
        add(descriptionArea);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createProject();
            }
        });
        add(createButton);
    }

    private void createProject() {
        String company = companyField.getText();
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();
        String description = descriptionArea.getText();

        Project project = new Project(company, startDate, endDate, description);
        projectDAO.addProject(project);

        JOptionPane.showMessageDialog(this, "Project created successfully");
        dispose();
    }
}
