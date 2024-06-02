package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame {
    public MainScreen() {
        setTitle("Main Screen");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1));

        // Botões
        JButton projectButton = new JButton("Projetos");
        JButton taskButton = new JButton("Tarefas");

        // Ações dos botões
        projectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para abrir a tela de projetos
                JOptionPane.showMessageDialog(MainScreen.this, "Abrindo Projetos...");
            }
        });

        taskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para abrir a tela de tarefas
                JOptionPane.showMessageDialog(MainScreen.this, "Abrindo Tarefas...");
            }
        });

        add(projectButton);
        add(taskButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }
}
