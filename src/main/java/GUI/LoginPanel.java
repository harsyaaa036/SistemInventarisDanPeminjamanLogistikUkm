package GUI;

import Database.DataStore;
import Login.Admin;
import Login.User;
import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private MainFrame mainFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("SISTEM INVENTARIS & PEMINJAMAN LOGISTIK UKM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0;
        add(new JLabel("Username:"), gbc);
        usernameField = new JTextField(20);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridy = 2; gbc.gridx = 0;
        add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(passwordField, gbc);

        messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.RED);
        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2;
        add(messageLabel, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridy = 4; gbc.gridx = 0; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(loginButton, gbc);

        JButton registerButton = new JButton("Belum punya akun? Daftar");
        registerButton.setBorderPainted(false);
        registerButton.setForeground(Color.BLUE);
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridy = 5;
        add(registerButton, gbc);

        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> {
            mainFrame.getRegistrasiPanel().resetForm();
            mainFrame.showPanel("Registrasi");
        });

        passwordField.addActionListener(e -> login());
    }

    public void resetForm() {
        usernameField.setText("");
        passwordField.setText("");
        messageLabel.setText(" ");
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Username dan password harus diisi!");
            return;
        }

        User user = DataStore.findUser(username);
        if (user != null && user.verifyPassword(password)) {
            messageLabel.setText(" ");
            if (user instanceof Admin) {
                mainFrame.getAdminDashboard().setUser(username);
                mainFrame.showPanel("AdminDashboard");
            } else {
                mainFrame.getAnggotaDashboard().setUser(username);
                mainFrame.showPanel("AnggotaDashboard");
            }
        } else {
            messageLabel.setText("Username/password salah!");
        }

        usernameField.setText("");
        passwordField.setText("");
    }
}
