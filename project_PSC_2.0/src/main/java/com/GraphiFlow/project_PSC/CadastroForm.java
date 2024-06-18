package com.GraphiFlow.project_PSC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.springframework.context.ApplicationContext;
import com.GraphiFlow.project_PSC.entities.User;
import com.GraphiFlow.project_PSC.services.UserService;

public class CadastroForm extends JFrame {

    private JFrame frame;
    private JPanel mainPanel;
    private ApplicationContext context;

    // Campos do formulário
    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JTextField idadeField;
    private JTextField cpfField;
    private JComboBox<String> sexoField;

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

    public CadastroForm(ApplicationContext context) {
        this.context = context;

        // Configurações da janela principal
        frame = new JFrame("Cadastro");
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
        nomeField = createTextField();
        emailField = createTextField();
        senhaField = createPasswordField();
        idadeField = createTextField();
        cpfField = createTextField();
        sexoField = new JComboBox<>(new String[]{"Masculino", "Feminino", "Outro"});
        sexoField.setFont(INPUT_FONT);
        sexoField.setBackground(Color.WHITE);

        // Adiciona os campos do formulário ao painel de formulário
        addFormField(formPanel, gbc, "Nome:", nomeField, 0);
        addFormField(formPanel, gbc, "Email:", emailField, 1);
        addFormField(formPanel, gbc, "Senha:", senhaField, 2);
        addFormField(formPanel, gbc, "Idade:", idadeField, 3);
        addFormField(formPanel, gbc, "CPF:", cpfField, 4);
        addFormField(formPanel, gbc, "Sexo:", sexoField, 5);

        // Botão de envio
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        JButton submitButton = createSubmitButton();
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obter o serviço do contexto do Spring
                UserService userService = context.getBean(UserService.class);

                // Criar um novo usuário a partir dos dados do formulário
                User newUser = new User();
                newUser.setName(nomeField.getText());
                newUser.setEmail(emailField.getText());
                newUser.setSenha(new String(senhaField.getPassword()));  // Corrigido para converter char[] em String
                newUser.setIdade(Integer.parseInt(idadeField.getText()));
                newUser.setCpf(cpfField.getText());
                newUser.setSexo((String) sexoField.getSelectedItem());

                // Salvar o novo usuário usando o serviço
                try {
                    userService.insert(newUser);
                    JOptionPane.showMessageDialog(frame, "Usuário cadastrado com sucesso!");
                    showLoginScreen(); // Navegar para a tela de login após o cadastro
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao cadastrar usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        formPanel.add(submitButton, gbc);

        // Link para tela de login
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        JButton loginLink = createLoginLink();
        loginLink.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginScreen(); // Navegar para a tela de login
            }
        });
        formPanel.add(loginLink, gbc);

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

    private JButton createSubmitButton() {
        JButton button = new JButton("Registrar");
        button.setFont(BUTTON_FONT);
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private JButton createLoginLink() {
        JButton button = new JButton("Logar");
        button.setFont(LINK_FONT);
        button.setForeground(LINK_COLOR);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void showLoginScreen() {
        frame.dispose(); // Fecha a tela de cadastro
        new LoginForm(context); // Abre a tela de login
    }

    public static void main(String[] args) {
        // Simulação de contexto do Spring
        ApplicationContext context = null; // Inicialize o contexto do Spring aqui
        new CadastroForm(context);
    }
}
