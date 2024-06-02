package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserForm extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField cpfField;
    private JTextField ageField;
    private JTextField countryField;
    private JComboBox<String> genderComboBox;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private UserDAO userDAO;

    public UserForm(UserDAO userDAO) {
        this.userDAO = userDAO;

        // Configurações da janela
        setTitle("User Registration");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 2));

        // Painel de entrada de dados
        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("CPF:"));
        cpfField = new JTextField();
        add(cpfField);

        add(new JLabel("Age:"));
        ageField = new JTextField();
        add(ageField);

        add(new JLabel("Country:"));
        countryField = new JTextField();
        add(countryField);

        add(new JLabel("Gender:"));
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female"});
        add(genderComboBox);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField();
        add(confirmPasswordField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        add(registerButton);
    }

    private void registerUser() {
        String name = nameField.getText();
        String email = emailField.getText();
        String cpf = cpfField.getText();
        int age = Integer.parseInt(ageField.getText());
        String country = countryField.getText();
        String gender = (String) genderComboBox.getSelectedItem();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // Verificar se as senhas coincidem
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!");
            return;
        }

        // Adicionando um usuário normal (não administrador)
        User user = new User(0, name, email, cpf, age, gender, country, password, false);
        userDAO.addUser(user);
        JOptionPane.showMessageDialog(this, "User registered successfully");
    }
}
