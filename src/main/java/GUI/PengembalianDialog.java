package GUI;

import Database.DataStore;
import Transaksi.Peminjaman;
import Transaksi.Pengembalian;
import javax.swing.*;
import java.awt.*;

public class PengembalianDialog extends JDialog {
    private JTextField idField;
    private JComboBox<String> kondisiCombo;
    private JLabel messageLabel;

    public PengembalianDialog(MainFrame mainFrame) {
        super(mainFrame, "Proses Pengembalian", true);
        setSize(400, 250);
        setLocationRelativeTo(mainFrame);
        setLayout(new GridBagLayout());
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(new JLabel("Form Pengembalian Barang", SwingConstants.CENTER), gbc);

        gbc.gridwidth = 1; gbc.gridy = 1; gbc.gridx = 0;
        add(new JLabel("ID Peminjaman:"), gbc);
        idField = new JTextField(10);
        gbc.gridx = 1;
        add(idField, gbc);

        gbc.gridy = 2; gbc.gridx = 0;
        add(new JLabel("Kondisi Barang:"), gbc);
        kondisiCombo = new JComboBox<>(new String[]{"Baik", "Rusak", "Hilang"});
        gbc.gridx = 1;
        add(kondisiCombo, gbc);

        messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.RED);
        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2;
        add(messageLabel, gbc);

        JButton submitButton = new JButton("Proses Pengembalian");
        gbc.gridy = 4;
        add(submitButton, gbc);

        submitButton.addActionListener(e -> proses());
    }

    private void proses() {
        try {
            int idPeminjaman = Integer.parseInt(idField.getText().trim());

            Peminjaman peminjaman = null;
            for (Peminjaman p : DataStore.daftarPeminjaman) {
                if (p.getIdTransaksi() == idPeminjaman) {
                    peminjaman = p;
                    break;
                }
            }

            if (peminjaman == null) {
                messageLabel.setText("ID Peminjaman tidak ditemukan!");
                return;
            }

            String kondisi = (String) kondisiCombo.getSelectedItem();
            Pengembalian pengembalian = new Pengembalian(idPeminjaman, peminjaman.getRencanaPengembalian());
            pengembalian.setKondisiAkhir(kondisi);
            peminjaman.setStatus("Selesai");

            DataStore.daftarPengembalian.add(pengembalian);

            JOptionPane.showMessageDialog(this,
                    "Pengembalian berhasil diproses!\nID Peminjaman: " + idPeminjaman
                    + "\nKondisi: " + kondisi
                    + "\nDenda: Rp" + pengembalian.getDenda(),
                    "Sukses", JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (NumberFormatException e) {
            messageLabel.setText("ID Peminjaman harus angka!");
        }
    }
}
