package GUI;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JPanel {
    private MainFrame mainFrame;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JLabel welcomeLabel;
    private JPanel menuPanel;
    private CrudBarangPanel crudBarangPanel;
    private String currentUser;

    public AdminDashboard(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        JPanel topPanel = new JPanel(new BorderLayout());
        welcomeLabel = new JLabel("Selamat datang, Admin");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(welcomeLabel, BorderLayout.WEST);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());
        topPanel.add(logoutButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        menuPanel = new JPanel(new GridBagLayout());
        buildMenuPanel();
        contentPanel.add(menuPanel, "Menu");

        crudBarangPanel = new CrudBarangPanel(this);
        contentPanel.add(crudBarangPanel, "CrudBarang");

        add(contentPanel, BorderLayout.CENTER);

        JPanel laporanPanel = new JPanel(new GridBagLayout());
        JLabel laporanLabel = new JLabel("Fitur laporan masih dalam pengembangan.");
        laporanLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        GridBagConstraints gbc = new GridBagConstraints();
        laporanPanel.add(laporanLabel, gbc);
        JButton backLaporan = new JButton("Kembali");
        gbc.gridy = 1;
        laporanPanel.add(backLaporan, gbc);
        backLaporan.addActionListener(e -> showMenu());
        contentPanel.add(laporanPanel, "Laporan");

        showMenu();
    }

    private void buildMenuPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Dashboard Admin");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        menuPanel.add(title, gbc);

        JButton btnBarang = new JButton("Manajemen Barang (CRUD)");
        btnBarang.setPreferredSize(new Dimension(250, 40));
        gbc.gridwidth = 1; gbc.gridy = 1; gbc.gridx = 0;
        menuPanel.add(btnBarang, gbc);

        JButton btnLaporan = new JButton("Laporan Transaksi");
        btnLaporan.setPreferredSize(new Dimension(250, 40));
        gbc.gridx = 1;
        menuPanel.add(btnLaporan, gbc);

        btnBarang.addActionListener(e -> showCrudBarang());
        btnLaporan.addActionListener(e -> cardLayout.show(contentPanel, "Laporan"));
    }

    public void setUser(String username) {
        this.currentUser = username;
        welcomeLabel.setText("Selamat datang, " + username);
        crudBarangPanel.refreshTable();
    }

    public void showMenu() {
        cardLayout.show(contentPanel, "Menu");
    }

    public void showCrudBarang() {
        crudBarangPanel.refreshTable();
        cardLayout.show(contentPanel, "CrudBarang");
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin logout?",
                "Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            currentUser = null;
            mainFrame.getLoginPanel().resetForm();
            mainFrame.showPanel("Login");
        }
    }

    public MainFrame getMainFrame() { return mainFrame; }

    public String getCurrentUser() { return currentUser; }
}
