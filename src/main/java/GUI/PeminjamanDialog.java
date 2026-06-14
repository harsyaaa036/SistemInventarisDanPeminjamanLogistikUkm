package GUI;

import Barang.Barang;
import Database.DataStore;
import Transaksi.Peminjaman;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PeminjamanDialog extends JDialog {
    private JComboBox<String> barangCombo;
    private JTextField jumlahField;
    private JTextField tglPinjamField;
    private JTextField tglRencanaField;
    private JLabel messageLabel;

    public PeminjamanDialog(MainFrame mainFrame, String username) {
        super(mainFrame, "Ajukan Peminjaman", true);
        setSize(400, 300);
        setLocationRelativeTo(mainFrame);
        setLayout(new GridBagLayout());
        initComponents(username);
    }

    private void initComponents(String username) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(new JLabel("Form Peminjaman Barang", SwingConstants.CENTER), gbc);

        gbc.gridwidth = 1; gbc.gridy = 1; gbc.gridx = 0;
        add(new JLabel("Pilih Barang:"), gbc);
        barangCombo = new JComboBox<>();
        for (Barang b : DataStore.daftarBarang) {
            barangCombo.addItem(b.getId() + " - " + b.getNama() + " (Stok: " + b.getStok() + ")");
        }
        gbc.gridx = 1;
        add(barangCombo, gbc);

        gbc.gridy = 2; gbc.gridx = 0;
        add(new JLabel("Jumlah:"), gbc);
        jumlahField = new JTextField(10);
        gbc.gridx = 1;
        add(jumlahField, gbc);

        gbc.gridy = 3; gbc.gridx = 0;
        add(new JLabel("Tgl Pinjam (yyyy-MM-dd):"), gbc);
        tglPinjamField = new JTextField(LocalDate.now().toString(), 10);
        gbc.gridx = 1;
        add(tglPinjamField, gbc);

        gbc.gridy = 4; gbc.gridx = 0;
        add(new JLabel("Rencana Kembali (yyyy-MM-dd):"), gbc);
        tglRencanaField = new JTextField(LocalDate.now().plusDays(3).toString(), 10);
        gbc.gridx = 1;
        add(tglRencanaField, gbc);

        messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.RED);
        gbc.gridy = 5; gbc.gridx = 0; gbc.gridwidth = 2;
        add(messageLabel, gbc);

        JButton submitButton = new JButton("Ajukan Peminjaman");
        gbc.gridy = 6;
        add(submitButton, gbc);

        submitButton.addActionListener(e -> ajukan(username));
    }

    private void ajukan(String username) {
        if (barangCombo.getSelectedIndex() < 0) return;
        String selected = (String) barangCombo.getSelectedItem();
        int idBarang = Integer.parseInt(selected.split(" - ")[0]);
        Barang barang = DataStore.findBarang(idBarang);
        if (barang == null) return;

        try {
            int jumlah = Integer.parseInt(jumlahField.getText().trim());
            if (jumlah <= 0) {
                messageLabel.setText("Jumlah harus lebih dari 0!");
                return;
            }
            if (jumlah > barang.getStok()) {
                messageLabel.setText("Stok tidak mencukupi! Tersedia: " + barang.getStok());
                return;
            }

            LocalDate tglPinjam = LocalDate.parse(tglPinjamField.getText().trim());
            LocalDate tglRencana = LocalDate.parse(tglRencanaField.getText().trim());

            if (tglRencana.isBefore(tglPinjam)) {
                messageLabel.setText("Tanggal kembali harus setelah tanggal pinjam!");
                return;
            }

            Peminjaman p = new Peminjaman(barang.getNama(), jumlah, tglPinjam, tglRencana);
            barang.setStok(barang.getStok() - jumlah);
            DataStore.daftarPeminjaman.add(p);

            JOptionPane.showMessageDialog(this,
                    "Peminjaman berhasil diajukan!\nID Transaksi: " + p.getIdTransaksi()
                    + "\nBarang: " + barang.getNama()
                    + "\nJumlah: " + jumlah
                    + "\nStatus: " + p.getStatus(),
                    "Sukses", JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (NumberFormatException e) {
            messageLabel.setText("Jumlah harus angka!");
        } catch (DateTimeParseException e) {
            messageLabel.setText("Format tanggal salah! Gunakan yyyy-MM-dd");
        }
    }
}
