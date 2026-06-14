package com.harsya.ukm.gui;

import com.harsya.ukm.database.DataStore;
import com.harsya.ukm.login.Anggota;
import com.harsya.ukm.login.ILogin;
import com.harsya.ukm.login.User;
import javax.swing.*;
import java.awt.*;

public class AnggotaDashboard extends JPanel {
    private MainFrame mainFrame;
    private JLabel welcomeLabel;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private String currentUser;

    public AnggotaDashboard(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        JPanel topPanel = new JPanel(new BorderLayout());
        welcomeLabel = new JLabel("Selamat datang, Anggota");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(welcomeLabel, BorderLayout.WEST);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());
        topPanel.add(logoutButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        JPanel menuPanel = new JPanel(new GridBagLayout());
        buildMenuPanel(menuPanel);
        contentPanel.add(menuPanel, "Menu");

        add(contentPanel, BorderLayout.CENTER);
        cardLayout.show(contentPanel, "Menu");
    }

    private void buildMenuPanel(JPanel menuPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Dashboard Anggota");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0;
        menuPanel.add(title, gbc);

        JButton btnPinjam = new JButton("Ajukan Peminjaman");
        btnPinjam.setPreferredSize(new Dimension(250, 40));
        gbc.gridy = 1;
        menuPanel.add(btnPinjam, gbc);

        JButton btnKembali = new JButton("Proses Pengembalian");
        btnKembali.setPreferredSize(new Dimension(250, 40));
        gbc.gridy = 2;
        menuPanel.add(btnKembali, gbc);

        JButton btnStatus = new JButton("Lihat Status Peminjaman");
        btnStatus.setPreferredSize(new Dimension(250, 40));
        gbc.gridy = 3;
        menuPanel.add(btnStatus, gbc);

        btnPinjam.addActionListener(e -> {
            PeminjamanDialog dialog = new PeminjamanDialog(mainFrame, currentUser);
            dialog.setVisible(true);
        });

        btnKembali.addActionListener(e -> {
            PengembalianDialog dialog = new PengembalianDialog(mainFrame);
            dialog.setVisible(true);
        });

        btnStatus.addActionListener(e -> {
            StatusDialog dialog = new StatusDialog(mainFrame, currentUser);
            dialog.setVisible(true);
        });
    }

    public void setUser(String username) {
        this.currentUser = username;
        welcomeLabel.setText("Selamat datang, " + username);

        User user = DataStore.findUser(username);
        if (user instanceof Anggota) {
            Anggota anggota = (Anggota) user;
            ILogin loginRef = anggota;
            loginRef.login();
        }
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin logout?",
                "Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (currentUser != null) {
                User user = DataStore.findUser(currentUser);
                if (user instanceof ILogin) {
                    ILogin loginRef = (ILogin) user;
                    loginRef.logout();
                }
            }
            currentUser = null;
            mainFrame.getLoginPanel().resetForm();
            mainFrame.showPanel("Login");
        }
    }
}
