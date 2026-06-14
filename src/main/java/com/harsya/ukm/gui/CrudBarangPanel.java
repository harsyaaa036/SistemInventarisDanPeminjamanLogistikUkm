package com.harsya.ukm.gui;

import com.harsya.ukm.barang.Barang;
import com.harsya.ukm.database.DataStore;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CrudBarangPanel extends JPanel {
    private AdminDashboard adminDashboard;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public CrudBarangPanel(AdminDashboard adminDashboard) {
        this.adminDashboard = adminDashboard;
        setLayout(new BorderLayout(10, 10));
        initComponents();
    }

    private void initComponents() {
        JLabel title = new JLabel("Manajemen Barang");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        String[] columns = {"ID", "Nama Barang", "Stok", "Harga (Rp)"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JButton btnTambah = new JButton("Tambah");
        JButton btnEdit = new JButton("Edit");
        JButton btnHapus = new JButton("Hapus");
        btnHapus.setForeground(Color.RED);
        JButton btnKembali = new JButton("Kembali");

        bottomPanel.add(btnTambah);
        bottomPanel.add(btnEdit);
        bottomPanel.add(btnHapus);
        bottomPanel.add(btnKembali);

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(15);
        JButton btnCari = new JButton("Cari");
        JButton btnRefresh = new JButton("Refresh");
        searchPanel.add(new JLabel("Cari:"));
        searchPanel.add(searchField);
        searchPanel.add(btnCari);
        searchPanel.add(btnRefresh);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(searchPanel, BorderLayout.NORTH);
        southPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);

        btnTambah.addActionListener(e -> tambahBarang());
        btnEdit.addActionListener(e -> editBarang());
        btnHapus.addActionListener(e -> hapusBarang());
        btnCari.addActionListener(e -> cariBarang());
        btnRefresh.addActionListener(e -> refreshTable());
        btnKembali.addActionListener(e -> adminDashboard.showMenu());

        searchField.addActionListener(e -> cariBarang());

        refreshTable();
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        for (Barang b : DataStore.daftarBarang) {
            tableModel.addRow(new Object[]{
                b.getId(), b.getNama(), b.getStok(),
                String.format("%,d", (int) b.getHarga())
            });
        }
    }

    private void tambahBarang() {
        JTextField namaField = new JTextField(15);
        JTextField stokField = new JTextField(10);
        JTextField hargaField = new JTextField(15);

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("Nama Barang:"));
        panel.add(namaField);
        panel.add(new JLabel("Stok:"));
        panel.add(stokField);
        panel.add(new JLabel("Harga:"));
        panel.add(hargaField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Tambah Barang",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.OK_OPTION) return;

        try {
            String nama = namaField.getText().trim();
            int stok = Integer.parseInt(stokField.getText().trim());
            double harga = Double.parseDouble(hargaField.getText().trim());
            if (nama.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama barang tidak boleh kosong!");
                return;
            }
            if (stok < 0 || harga < 0) {
                JOptionPane.showMessageDialog(this, "Stok dan harga tidak boleh negatif!");
                return;
            }
            DataStore.daftarBarang.add(new Barang(nama, stok, harga));
            refreshTable();
            JOptionPane.showMessageDialog(this, "Barang berhasil ditambahkan!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Stok dan harga harus angka valid!");
        }
    }

    private void editBarang() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih barang yang akan diedit!");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        Barang barang = DataStore.findBarang(id);
        if (barang == null) return;

        JTextField namaField = new JTextField(barang.getNama(), 15);
        JTextField stokField = new JTextField(String.valueOf(barang.getStok()), 10);
        JTextField hargaField = new JTextField(String.valueOf((int) barang.getHarga()), 15);

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("Nama Barang:"));
        panel.add(namaField);
        panel.add(new JLabel("Stok:"));
        panel.add(stokField);
        panel.add(new JLabel("Harga:"));
        panel.add(hargaField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Barang",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.OK_OPTION) return;

        try {
            String nama = namaField.getText().trim();
            int stok = Integer.parseInt(stokField.getText().trim());
            double harga = Double.parseDouble(hargaField.getText().trim());
            if (nama.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama barang tidak boleh kosong!");
                return;
            }
            if (stok < 0 || harga < 0) {
                JOptionPane.showMessageDialog(this, "Stok dan harga tidak boleh negatif!");
                return;
            }
            barang.setNama(nama);
            barang.setStok(stok);
            barang.setHarga(harga);
            refreshTable();
            JOptionPane.showMessageDialog(this, "Barang berhasil diupdate!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Stok dan harga harus angka valid!");
        }
    }

    private void hapusBarang() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih barang yang akan dihapus!");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String nama = (String) tableModel.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Yakin hapus barang \"" + nama + "\"?", "Hapus Barang",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) return;

        DataStore.daftarBarang.removeIf(b -> b.getId() == id);
        refreshTable();
        JOptionPane.showMessageDialog(this, "Barang berhasil dihapus!");
    }

    private void cariBarang() {
        String keyword = searchField.getText().trim().toLowerCase();
        tableModel.setRowCount(0);

        for (Barang b : DataStore.daftarBarang) {
            if (b.getNama().toLowerCase().contains(keyword)) {
                tableModel.addRow(new Object[]{
                    b.getId(), b.getNama(), b.getStok(),
                    String.format("%,d", (int) b.getHarga())
                });
            }
        }

        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Barang tidak ditemukan!");
            refreshTable();
        }
    }
}
