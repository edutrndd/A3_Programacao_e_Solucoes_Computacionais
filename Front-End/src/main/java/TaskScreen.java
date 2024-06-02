package main.java;

import javax.swing.*;
import java.awt.*;

public class TaskScreen extends JFrame {
    public TaskScreen() {
        setTitle("Task Screen");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void openCreateTaskWindow() {
        JFrame createTaskWindow = new JFrame("Create Task");
        createTaskWindow.setSize(300, 200);
        createTaskWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createTaskWindow.setLayout(new GridLayout(5, 2));

        // Adicione os componentes para criar uma tarefa aqui

        createTaskWindow.setVisible(true);
    }
}
