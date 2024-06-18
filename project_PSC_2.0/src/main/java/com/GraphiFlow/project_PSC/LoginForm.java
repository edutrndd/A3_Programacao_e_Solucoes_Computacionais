package com.GraphiFlow.project_PSC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;

public class LoginForm extends JFrame {

    private JFrame frame;
    private JPanel mainPanel;

    // Campos do formulário
    private JTextField emailField;
    private JPasswordField senhaField;
    private JCheckBox rememberMeCheckBox;

    // Constantes para aparência
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color FORM_BACKGROUND_COLOR = new Color(255, 255, 255);
    private static final Color BUTTON_COLOR = new Color(0, 123, 255);
    private static final Color LABEL_COLOR = new Color(33, 37, 41);
    private static final Color LINK_COLOR = new Color(0, 123, 255);
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 18);
    private static final Font INPUT_FONT = new Font("Arial", Font.PLAIN, 18);
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 18);
    private static final Font LINK_FONT = new Font("Arial", Font.ITALIC, 14);

    public LoginForm(ApplicationContext context) {
        // Configurações da janela principal
        frame = new JFrame("Login");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Painel principal
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);

        // Adiciona componentes ao painel principal
        addComponents();

        // Adiciona o painel principal à janela
        frame.add(mainPanel);

        // Exibe a janela
        frame.setVisible(true);
    }

    private void addComponents() {
        // Painel de formulário
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(true);
        formPanel.setBackground(FORM_BACKGROUND_COLOR);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Campos do formulário
        emailField = createTextField();
        senhaField = createPasswordField();
        rememberMeCheckBox = new JCheckBox("Lembrar de mim");

        // Adiciona os campos do formulário ao painel de formulário
        addFormField(formPanel, gbc, "Email:", emailField, 0);
        addFormField(formPanel, gbc, "Senha:", senhaField, 1);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(rememberMeCheckBox, gbc);

        // Botão de envio
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        JButton loginButton = createLoginButton();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        formPanel.add(loginButton, gbc);

        // Adiciona os painéis ao painel principal
        mainPanel.add(formPanel, BorderLayout.CENTER);
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, JComponent inputField, int gridy) {
        gbc.gridy = gridy;
        gbc.gridx = 0;
        JLabel label = new JLabel(labelText);
        label.setFont(LABEL_FONT);
        label.setForeground(LABEL_COLOR);
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(inputField, gbc);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(20);
        styleTextField(textField);
        return textField;
    }

    private JPasswordField createPasswordField() {
        JPasswordField passwordField = new JPasswordField(20);
        styleTextField(passwordField);
        return passwordField;
    }

    private void styleTextField(JTextField textField) {
        textField.setFont(INPUT_FONT);
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        textField.setOpaque(true);
        textField.setBackground(Color.WHITE);
    }

    private JButton createLoginButton() {
        JButton button = new JButton("Entrar");
        button.setFont(BUTTON_FONT);
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void login() {
        String email = emailField.getText();
        String password = new String(senhaField.getPassword());
        boolean rememberMe = rememberMeCheckBox.isSelected();

        // Create login data map
        Map<String, String> loginData = new HashMap<>();
        loginData.put("email", email);
        loginData.put("senha", password);
        loginData.put("rememberMe", String.valueOf(rememberMe));

        // Convert map to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonLoginData = "";
        try {
            jsonLoginData = objectMapper.writeValueAsString(loginData);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao criar dados de login.");
            return;
        }

        // Create HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Create HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/auth/login")) // Update with your server URL
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonLoginData))
                .build();

        // Send HTTP request
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(responseBody -> {
                    // Process response
                    try {
                        Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);
                        boolean success = (Boolean) responseMap.get("success");
                        if (success) {
                            JOptionPane.showMessageDialog(this, "Login bem sucedido!");
                            String userType = (String) responseMap.get("userType");
                            if ("USER".equals(userType)) {
                                SwingUtilities.invokeLater(() -> {
                                    new HomePage();
                                    frame.dispose();
                                });
                            } else if ("ADMIN".equals(userType)) {
                                SwingUtilities.invokeLater(() -> {
                                    new AdminHomePage();
                                    frame.dispose();
                                });
                            }
                        } else {
                            String message = (String) responseMap.get("message");
                            JOptionPane.showMessageDialog(this, message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Erro ao processar resposta do servidor.");
                    }
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erro ao realizar login. Por favor, tente novamente.");
                    return null;
                });
    }

    public static void main(String[] args) {
        ApplicationContext context = null;
        SwingUtilities.invokeLater(() -> new LoginForm(context));
    }
}
