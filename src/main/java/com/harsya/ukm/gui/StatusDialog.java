package com.harsya.ukm.gui;

import com.harsya.ukm.database.DataStore;
import com.harsya.ukm.transaksi.Peminjaman;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StatusDialog extends JDialog {
    private DefaultTableModel tableModel;
    private String currentUser;

    public StatusDialog(MainFrame mainFrame, String currentUser) {
        super(mainFrame, "Status Peminjaman", true);
        this.currentUser = currentUser;
        setSize(600, 400);
        setLocationRelativeTo(mainFrame);
        setLayout(new BorderLayout(10, 10));
        initComponents();
    }

    private void initComponents() {
        JLabel title = new JLabel("Daftar Transaksi Peminjaman", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        String[] columns = {"ID", "Barang", "Jumlah", "Tgl Pinjam", "Rencana Kembali", "Durasi", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        List<Peminjaman> filteredList = new ArrayList<>();
        for (Peminjaman p : DataStore.daftarPeminjaman) {
            if (currentUser == null || p.getNamaBarang().toLowerCase().contains(currentUser.toLowerCase())) {
                filteredList.add(p);
            }
        }

        for (Peminjaman p : DataStore.daftarPeminjaman) {
            tableModel.addRow(new Object[]{
                p.getIdTransaksi(),
                p.getNamaBarang(),
                p.getJumlah(),
                p.getTanggalPinjam().toString(),
                p.getRencanaPengembalian().toString(),
                p.getDurasi() + " hari",
                p.getStatus()
            });
        }

        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        int totalPeminjaman = DataStore.daftarPeminjaman.size();
        int aktif = 0;
        for (Peminjaman p : DataStore.daftarPeminjaman) {
            if (p.getStatus().equals("Diproses")) {
                aktif++;
            }
        }
        infoPanel.add(new JLabel("User: " + (currentUser != null ? currentUser : "Semua")));
        infoPanel.add(new JLabel("Total Peminjaman: " + totalPeminjaman));
        infoPanel.add(new JLabel("Peminjaman Aktif: " + aktif));

        JButton closeButton = new JButton("Tutup");
        closeButton.addActionListener(e -> dispose());

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(infoPanel, BorderLayout.WEST);
        southPanel.add(closeButton, BorderLayout.EAST);
        add(southPanel, BorderLayout.SOUTH);
    }
}
