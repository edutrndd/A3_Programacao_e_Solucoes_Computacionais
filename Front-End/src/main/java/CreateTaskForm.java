package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTaskForm extends JFrame {
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextArea descriptionArea;
    private TaskDAO taskDAO;

    public CreateTaskForm() {
        taskDAO = new TaskDAO();

        setTitle("Create Task");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

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
                createTask();
            }
        });
        add(createButton);
    }

    private void createTask() {
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();
        String description = descriptionArea.getText();

        Task task = new Task(startDate, endDate, description);
        taskDAO.addTask(task);

        JOptionPane.showMessageDialog(this, "Task created successfully");
        dispose();
    }
}
