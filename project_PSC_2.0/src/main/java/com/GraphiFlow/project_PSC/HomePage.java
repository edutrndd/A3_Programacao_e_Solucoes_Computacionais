package com.GraphiFlow.project_PSC;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class HomePage extends JFrame {

    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color NAVBAR_COLOR = new Color(33, 37, 41);
    private static final Color PANEL_COLOR = new Color(255, 255, 255);
    private static final Color TEXT_COLOR = new Color(33, 37, 41);
    private static final Font HEADER_FONT = new Font("SansSerif", Font.BOLD, 24);
    private static final Font BUTTON_FONT = new Font("SansSerif", Font.PLAIN, 16);
    private static final Font LABEL_FONT = new Font("SansSerif", Font.BOLD, 18);

    public HomePage() {
        // Configurações da janela principal
        setTitle("GraphiFlow - Home");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
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
        navbar.setBackground(NAVBAR_COLOR);
        navbar.setForeground(Color.WHITE);
        navbar.setPreferredSize(new Dimension(1200, 50));

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
        menu.setBackground(NAVBAR_COLOR);
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
        leftPanel.setBackground(NAVBAR_COLOR);
        leftPanel.add(menuButton, BorderLayout.WEST);
        leftPanel.add(menu, BorderLayout.CENTER);

        // Search bar and profile icon
        JPanel searchProfilePanel = new JPanel(new BorderLayout());
        searchProfilePanel.setBackground(NAVBAR_COLOR);

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
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new GridLayout(4, 1, 20, 20));
        buttonsPanel.setBackground(BACKGROUND_COLOR);

        String[] buttonLabels = {"Projetos", "Tarefas", "Colaboradores", "Configurações Pessoais"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(BUTTON_FONT);
            button.setBackground(PANEL_COLOR);
            button.setBorder(BorderFactory.createLineBorder(TEXT_COLOR, 1));
            buttonsPanel.add(button);
        }

        contentPanel.add(buttonsPanel);

        // Sections Panel
        JPanel sectionsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        sectionsPanel.setBackground(BACKGROUND_COLOR);

        // Task Chart section
        DefaultPieDataset taskDataset = new DefaultPieDataset();
        taskDataset.setValue("Cancelado", 20);
        taskDataset.setValue("Aguardando Entrega", 30);
        taskDataset.setValue("Entregue", 50);

        JFreeChart taskChart = ChartFactory.createPieChart(
                "Status dos Projetos", taskDataset, true, true, false);
        ChartPanel chartPanel = new ChartPanel(taskChart);
        sectionsPanel.add(chartPanel);

        // Open Tasks section
        JPanel openTasksPanel = new JPanel(new BorderLayout());
        openTasksPanel.setBackground(PANEL_COLOR);
        openTasksPanel.setBorder(BorderFactory.createTitledBorder("Tarefas em Aberto"));

        JList<String> openTasksList = new JList<>(new String[]{"Tarefa 1", "Tarefa 2", "Tarefa 3"});
        openTasksPanel.add(new JScrollPane(openTasksList), BorderLayout.CENTER);

        sectionsPanel.add(openTasksPanel);

        // Notifications section
        JPanel notificationsPanel = new JPanel(new BorderLayout());
        notificationsPanel.setBackground(PANEL_COLOR);
        notificationsPanel.setBorder(BorderFactory.createTitledBorder("Notificações"));

        JList<String> notificationsList = new JList<>(new String[]{"Notificação 1", "Notificação 2", "Notificação 3"});
        notificationsPanel.add(new JScrollPane(notificationsList), BorderLayout.CENTER);

        sectionsPanel.add(notificationsPanel);

        // Recent Tasks section
        JPanel recentTasksPanel = new JPanel(new BorderLayout());
        recentTasksPanel.setBackground(PANEL_COLOR);
        recentTasksPanel.setBorder(BorderFactory.createTitledBorder("Tarefas Recentes"));

        JPanel recentTasksListPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        recentTasksListPanel.setBackground(BACKGROUND_COLOR);

        JPanel task1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        task1Panel.setBackground(BACKGROUND_COLOR);
        task1Panel.add(new JLabel(loadIcon("/icons/task1.png")));
        task1Panel.add(new JLabel("<html><strong>Tarefa 1</strong><br>Descrição da tarefa 1.</html>"));
        recentTasksListPanel.add(task1Panel);

        JPanel task2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        task2Panel.setBackground(BACKGROUND_COLOR);
        task2Panel.add(new JLabel(loadIcon("/icons/task2.png")));
        task2Panel.add(new JLabel("<html><strong>Tarefa 2</strong><br>Descrição da tarefa 2.</html>"));
        recentTasksListPanel.add(task2Panel);

        recentTasksPanel.add(recentTasksListPanel, BorderLayout.CENTER);

        sectionsPanel.add(recentTasksPanel);

        contentPanel.add(sectionsPanel);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
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
        SwingUtilities.invokeLater(HomePage::new);
    }
}
