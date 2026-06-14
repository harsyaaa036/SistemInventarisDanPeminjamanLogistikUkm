package com.harsya.ukm.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginPanel loginPanel;
    private RegistrasiPanel registrasiPanel;
    private AdminDashboard adminDashboard;
    private AnggotaDashboard anggotaDashboard;

    public MainFrame() {
        setTitle("Sistem Inventaris & Peminjaman Logistik UKM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        loginPanel = new LoginPanel(this);
        registrasiPanel = new RegistrasiPanel(this);
        adminDashboard = new AdminDashboard(this);
        anggotaDashboard = new AnggotaDashboard(this);

        mainPanel.add(loginPanel, "Login");
        mainPanel.add(registrasiPanel, "Registrasi");
        mainPanel.add(adminDashboard, "AdminDashboard");
        mainPanel.add(anggotaDashboard, "AnggotaDashboard");

        add(mainPanel);
        showPanel("Login");
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }

    public LoginPanel getLoginPanel() { return loginPanel; }
    public RegistrasiPanel getRegistrasiPanel() { return registrasiPanel; }
    public AdminDashboard getAdminDashboard() { return adminDashboard; }
    public AnggotaDashboard getAnggotaDashboard() { return anggotaDashboard; }
}
