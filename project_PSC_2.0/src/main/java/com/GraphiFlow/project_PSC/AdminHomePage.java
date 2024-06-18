package com.GraphiFlow.project_PSC;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AdminHomePage extends JFrame {

    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color TEXT_COLOR = new Color(33, 37, 41);
    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 24);
    private static final Font BUTTON_FONT = new Font("Arial", Font.PLAIN, 16);

    private JPanel mainPanel;
    private JDialog projectFormModal, editFormModal, collaboratorFormModal, settingsFormModal;

    public AdminHomePage() {
        // Configurações da janela principal
        setTitle("Admin Home");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);

        // Adiciona componentes ao painel principal
        addComponents(mainPanel);

        // Adiciona o painel principal à janela
        add(mainPanel);

        // Exibe a janela
        setVisible(true);
    }

    private void addComponents(JPanel mainPanel) {
        // Navbar
        JPanel navbar = new JPanel(new BorderLayout());
        navbar.setBackground(TEXT_COLOR);
        navbar.setForeground(Color.WHITE);
        navbar.setPreferredSize(new Dimension(800, 50));

        JLabel titleLabel = new JLabel("GraphiFlow");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton menuButton = new JButton(loadIcon("/icons/menu.png"));
        menuButton.setBorderPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setFocusPainted(false);
        menuButton.setOpaque(false);

        JPanel menu = new JPanel();
        menu.setBackground(TEXT_COLOR);
        menu.setVisible(false);
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        String[] menuItems = {"Home", "Sobre", "Contato"};
        for (String item : menuItems) {
            JLabel menuItem = new JLabel(item);
            menuItem.setFont(BUTTON_FONT);
            menuItem.setForeground(Color.WHITE);
            menuItem.setBorder(new EmptyBorder(5, 5, 5, 5));
            menu.add(menuItem);
        }

        menuButton.addActionListener(e -> menu.setVisible(!menu.isVisible()));

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(TEXT_COLOR);
        leftPanel.add(menuButton, BorderLayout.WEST);
        leftPanel.add(menu, BorderLayout.CENTER);

        // Search bar and profile icon
        JPanel searchProfilePanel = new JPanel(new BorderLayout());
        searchProfilePanel.setBackground(TEXT_COLOR);

        JTextField searchField = new JTextField();
        searchField.setFont(BUTTON_FONT);
        searchField.setForeground(TEXT_COLOR);

        JButton searchButton = new JButton(loadIcon("/icons/search.png"));
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setFocusPainted(false);
        searchButton.setOpaque(false);

        JButton profileButton = new JButton(loadIcon("/icons/profile.png"));
        profileButton.setBorderPainted(false);
        profileButton.setContentAreaFilled(false);
        profileButton.setFocusPainted(false);
        profileButton.setOpaque(false);

        searchProfilePanel.add(searchField, BorderLayout.CENTER);
        searchProfilePanel.add(searchButton, BorderLayout.EAST);
        searchProfilePanel.add(profileButton, BorderLayout.WEST);

        navbar.add(leftPanel, BorderLayout.WEST);
        navbar.add(titleLabel, BorderLayout.CENTER);
        navbar.add(searchProfilePanel, BorderLayout.EAST);

        mainPanel.add(navbar, BorderLayout.NORTH);

        // Content section
        JPanel contentPanel = new JPanel(new GridLayout(1, 2));
        contentPanel.setBackground(BACKGROUND_COLOR);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonsPanel.setBackground(BACKGROUND_COLOR);
        buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] buttonLabels = {"Projetos e Tarefas", "Editar tarefas", "Colaboradores", "Configurações de perfil"};
        JButton[] buttons = new JButton[4];
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFont(BUTTON_FONT);
            buttons[i].setBackground(Color.WHITE);
            buttonsPanel.add(buttons[i]);
        }

        buttons[0].addActionListener(e -> openModal(projectFormModal));
        buttons[1].addActionListener(e -> openModal(editFormModal));
        buttons[2].addActionListener(e -> openModal(collaboratorFormModal));
        buttons[3].addActionListener(e -> openModal(settingsFormModal));

        contentPanel.add(buttonsPanel);

        // Sections Panel
        JPanel sectionsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        sectionsPanel.setBackground(BACKGROUND_COLOR);
        sectionsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Task Chart section
        JPanel chartPanel = new JPanel();
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createTitledBorder("Gráfico de Tarefas"));

        JLabel chartLabel = new JLabel(loadIcon("/icons/chart.png")); // Placeholder for chart
        chartPanel.add(chartLabel);

        sectionsPanel.add(chartPanel);

        // Open Tasks section
        JPanel openTasksPanel = new JPanel();
        openTasksPanel.setBackground(Color.WHITE);
        openTasksPanel.setBorder(BorderFactory.createTitledBorder("Tarefas em Aberto"));

        JList<String> openTasksList = new JList<>(new String[]{"Tarefa 1", "Tarefa 2", "Tarefa 3"});
        openTasksPanel.add(openTasksList);

        sectionsPanel.add(openTasksPanel);

        // Notifications section
        JPanel notificationsPanel = new JPanel();
        notificationsPanel.setBackground(Color.WHITE);
        notificationsPanel.setBorder(BorderFactory.createTitledBorder("Notificações"));

        JList<String> notificationsList = new JList<>(new String[]{"Notificação 1", "Notificação 2", "Notificação 3"});
        notificationsPanel.add(notificationsList);

        sectionsPanel.add(notificationsPanel);

        contentPanel.add(sectionsPanel);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Create modals
        createModals();
    }

    private void openModal(JDialog modal) {
        modal.setVisible(true);
    }

    private void createModals() {
        // Project Form Modal
        projectFormModal = createFormModal("Projeto", new String[]{"Nome do Projeto", "Descrição", "URL da Imagem", "Categoria"}, new String[]{"text", "textarea", "url", "combo"});

        // Edit Task Modal
        editFormModal = createFormModal("Editar Tarefa", new String[]{"Nome", "Descrição", "URL da Imagem", "Categoria"}, new String[]{"text", "textarea", "url", "combo"});

        // Collaborator Form Modal
        collaboratorFormModal = createFormModal("Adicionar Colaborador", new String[]{"Nome", "Email"}, new String[]{"text", "email"});

        // Settings Form Modal
        settingsFormModal = createFormModal("Configurações de Perfil", new String[]{"Nome de usuário", "Email", "Senha"}, new String[]{"text", "email", "password"});
    }

    private JDialog createFormModal(String title, String[] labels, String[] types) {
        JDialog formModal = new JDialog(this, title, Dialog.ModalityType.APPLICATION_MODAL);
        formModal.setSize(400, 300);
        formModal.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < labels.length; i++) {
            formPanel.add(new JLabel(labels[i]));
            switch (types[i]) {
                case "text":
                    formPanel.add(new JTextField());
                    break;
                case "textarea":
                    formPanel.add(new JTextArea(3, 20));
                    break;
                case "url":
                    formPanel.add(new JTextField());
                    break;
                case "combo":
                    formPanel.add(new JComboBox<>(new String[]{"Categoria 1", "Categoria 2"}));
                    break;
                case "email":
                    formPanel.add(new JTextField());
                    break;
                case "password":
                    formPanel.add(new JPasswordField());
                    break;
            }
        }

        JButton submitButton = new JButton("Submit");
        formPanel.add(submitButton);

        formModal.add(formPanel);
        return formModal;
    }

    private ImageIcon loadIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminHomePage::new);
    }
}
