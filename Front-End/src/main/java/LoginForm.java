package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private UserDAO userDAO;

    public LoginForm(UserDAO userDAO) {
        this.userDAO = userDAO;

        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                User user = userDAO.getUserByEmailAndPassword(email, password);
                if (user != null) {
                    JOptionPane.showMessageDialog(LoginForm.this, "Login successful");
                    if (user.isAdmin()) {
                        // Redirecionar para o dashboard do administrador
                        new AdminDashboard().setVisible(true); // Suponha que você tenha uma classe AdminDashboard
                    } else {
                        new UserDashboard().setVisible(true);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid email or password");
                }
            }
        });
        add(loginButton);
    }
}
