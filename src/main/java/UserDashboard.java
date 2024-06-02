package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDashboard extends JFrame {
    public UserDashboard() {
        setTitle("Dashboard");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        JButton projectsButton = new JButton("Projects");
        projectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProjectsScreen().setVisible(true);
            }
        });
        add(projectsButton);

        JButton tasksButton = new JButton("Tasks");
        tasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TaskScreen().setVisible(true);
            }
        });
        add(tasksButton);
    }
}
