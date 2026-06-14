package com.harsya.ukm.gui;

import com.harsya.ukm.database.DataStore;
import com.harsya.ukm.login.Admin;
import com.harsya.ukm.login.Anggota;
import com.harsya.ukm.login.User;
import javax.swing.*;
import java.awt.*;

public class RegistrasiPanel extends JPanel {
    private MainFrame mainFrame;
    private JComboBox<String> roleCombo;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField nimField;
    private JTextField jabatanField;
    private JTextField instansiField;
    private JTextField noHpField;
    private JTextField lokasiField;
    private JPanel adminFields;
    private JPanel anggotaFields;
    private JLabel messageLabel;

    public RegistrasiPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("REGISTRASI USER BARU");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0;
        add(new JLabel("Daftar sebagai:"), gbc);
        roleCombo = new JComboBox<>(new String[]{"Admin", "Anggota"});
        gbc.gridx = 1;
        add(roleCombo, gbc);

        gbc.gridy = 2; gbc.gridx = 0;
        add(new JLabel("Username:"), gbc);
        usernameField = new JTextField(20);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridy = 3; gbc.gridx = 0;
        add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(passwordField, gbc);

        adminFields = new JPanel(new GridLayout(2, 2, 5, 5));
        adminFields.add(new JLabel("NIM:"));
        nimField = new JTextField(15);
        adminFields.add(nimField);
        adminFields.add(new JLabel("Jabatan:"));
        jabatanField = new JTextField(15);
        adminFields.add(jabatanField);
        gbc.gridy = 4; gbc.gridx = 0; gbc.gridwidth = 2;
        add(adminFields, gbc);

        anggotaFields = new JPanel(new GridLayout(3, 2, 5, 5));
        anggotaFields.add(new JLabel("Instansi:"));
        instansiField = new JTextField(15);
        anggotaFields.add(instansiField);
        anggotaFields.add(new JLabel("No. HP:"));
        noHpField = new JTextField(15);
        anggotaFields.add(noHpField);
        anggotaFields.add(new JLabel("Lokasi Acara:"));
        lokasiField = new JTextField(15);
        anggotaFields.add(lokasiField);
        gbc.gridy = 5; gbc.gridx = 0; gbc.gridwidth = 2;
        add(anggotaFields, gbc);
        anggotaFields.setVisible(false);

        roleCombo.addActionListener(e -> {
            boolean isAdmin = roleCombo.getSelectedItem().equals("Admin");
            adminFields.setVisible(isAdmin);
            anggotaFields.setVisible(!isAdmin);
        });

        messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.RED);
        gbc.gridy = 6; gbc.gridx = 0; gbc.gridwidth = 2;
        add(messageLabel, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton daftarButton = new JButton("Daftar");
        JButton backButton = new JButton("Kembali ke Login");
        buttonPanel.add(daftarButton);
        buttonPanel.add(backButton);
        gbc.gridy = 7;
        add(buttonPanel, gbc);

        daftarButton.addActionListener(e -> daftar());
        backButton.addActionListener(e -> {
            mainFrame.getLoginPanel().resetForm();
            mainFrame.showPanel("Login");
        });
    }

    public void resetForm() {
        usernameField.setText("");
        passwordField.setText("");
        nimField.setText("");
        jabatanField.setText("");
        instansiField.setText("");
        noHpField.setText("");
        lokasiField.setText("");
        messageLabel.setText(" ");
        roleCombo.setSelectedIndex(0);
        adminFields.setVisible(true);
        anggotaFields.setVisible(false);
    }

    private void daftar() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Username dan password harus diisi!");
            return;
        }

        if (DataStore.findUser(username) != null) {
            messageLabel.setText("Username sudah terdaftar!");
            return;
        }

        boolean isAdmin = roleCombo.getSelectedItem().equals("Admin");

        if (isAdmin) {
            String nimStr = nimField.getText().trim();
            String jabatan = jabatanField.getText().trim();
            if (nimStr.isEmpty() || jabatan.isEmpty()) {
                messageLabel.setText("NIM dan Jabatan harus diisi!");
                return;
            }
            int nim;
            try {
                nim = Integer.parseInt(nimStr);
            } catch (NumberFormatException e) {
                messageLabel.setText("NIM harus angka!");
                return;
            }
            DataStore.daftarUser.add(new Admin(username, password, nim, jabatan));
        } else {
            String instansi = instansiField.getText().trim();
            String noHp = noHpField.getText().trim();
            String lokasi = lokasiField.getText().trim();
            if (instansi.isEmpty() || noHp.isEmpty() || lokasi.isEmpty()) {
                messageLabel.setText("Semua field harus diisi!");
                return;
            }
            DataStore.daftarUser.add(new Anggota(username, password, instansi, noHp, lokasi));
        }

        JOptionPane.showMessageDialog(this, "Registrasi berhasil! Silakan login.",
                "Sukses", JOptionPane.INFORMATION_MESSAGE);
        mainFrame.getLoginPanel().resetForm();
        mainFrame.showPanel("Login");
    }
}
