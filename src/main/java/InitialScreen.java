package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialScreen extends JFrame {
    private UserDAO userDAO;

    public InitialScreen() {
        userDAO = new UserDAO();

        setTitle("Initial Screen");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserDAO userDAO = new UserDAO();
                UserForm userForm = new UserForm(userDAO);
                userForm.setVisible(true);
            }
        });
        add(registerButton);

        JButton loginButton = new JButton("Login");
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui vamos fazer login usando o e-mail e a senha
                String email = JOptionPane.showInputDialog(null, "Enter your email:");
                String password = JOptionPane.showInputDialog(null, "Enter your password:");
                User user = userDAO.getUserByEmailAndPassword(email, password);
                if (user != null) {
                    JOptionPane.showMessageDialog(null, "Login successful");
                    if (user.isAdmin()) {
                        new AdminDashboard().setVisible(true);
                    } else {
                        new UserDashboard().setVisible(true);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email or password");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InitialScreen().setVisible(true);
            }
        });
    }
}
