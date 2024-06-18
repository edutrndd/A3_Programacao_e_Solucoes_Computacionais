package com.GraphiFlow.project_PSC.frontend.cadastro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroForm extends JFrame {

    public CadastroForm() {
        // Configurações da janela principal
        setTitle("Cadastro");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(193, 131, 255));

        // Painel de animação (simulado com um painel colorido)
        JPanel animationPanel = new JPanel();
        animationPanel.setBackground(new Color(196, 255, 201));
        animationPanel.setPreferredSize(new Dimension(300, 600));

        // Painel de formulário
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Campo de nome
        addFormField(formPanel, gbc, "Nome:", new JTextField(20), 0);

        // Campo de email
        addFormField(formPanel, gbc, "Email:", new JTextField(20), 1);

        // Campo de CPF
        addFormField(formPanel, gbc, "CPF:", new JTextField(20), 2);

        // Campo de idade
        addFormField(formPanel, gbc, "Idade:", new JTextField(20), 3);

        // Campo de sexo
        JLabel sexoLabel = new JLabel("Sexo:");
        JComboBox<String> sexoCombo = new JComboBox<>(new String[]{"Masculino", "Feminino", "Outro"});
        gbc.gridy = 4;
        formPanel.add(sexoLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(sexoCombo, gbc);

        // Campo de senha
        addFormField(formPanel, gbc, "Senha:", new JPasswordField(20), 5);

        // Campo de confirmar senha
        addFormField(formPanel, gbc, "Confirme a Senha:", new JPasswordField(20), 6);

        // Checkbox e link de login
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false);
        JCheckBox rememberMe = new JCheckBox("Lembrar de mim");
        bottomPanel.add(rememberMe);
        JLabel loginLink = new JLabel("<html><a href='#'>Logar</a></html>");
        bottomPanel.add(loginLink);
        formPanel.add(bottomPanel, gbc);

        // Botão de envio
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        JButton submitButton = new JButton("Entrar");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adicionar lógica de validação e envio aqui
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            }
        });
        formPanel.add(submitButton, gbc);

        // Adiciona os painéis ao painel principal
        mainPanel.add(animationPanel, BorderLayout.WEST);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Adiciona o painel principal à janela
        add(mainPanel);
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, JComponent inputField, int gridy) {
        gbc.gridy = gridy;
        gbc.gridx = 0;
        panel.add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        panel.add(inputField, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CadastroForm().setVisible(true);
            }
        });
    }
}
