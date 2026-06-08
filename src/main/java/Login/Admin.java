/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

/**
 *
 * @author masag
 */
public class Admin extends User implements ILogin, ICrudBarang {
    private int nim;
    private String jabatan;

    public Admin(String username, String password, int nim, String jabatan) {
        super(username, password);
        this.nim = nim;
        this.jabatan = jabatan;
    }

    @Override
    public void showMenu() {
        System.out.println("=== Dashboard Admin ===");
        System.out.println("1. Manajemen Pengguna");
        System.out.println("2. Manajemen Barang (CRUD)");
        System.out.println("3. Laporan Transaksi");
    }

    @Override
    public void tambahBarang() { System.out.println("Admin menambah barang..."); }
    @Override
    public void hapusBarang() { System.out.println("Admin menghapus barang..."); }
    @Override
    public void editBarang() { System.out.println("Admin mengedit barang..."); }
    @Override
    public void cariBarang() { System.out.println("Admin mencari barang..."); }

    @Override
    public void login() { System.out.println("Admin login berhasil."); }
    @Override
    public void logout() { System.out.println("Admin logout."); }

    public void daftarLaporanPeminjaman() { /* Logika laporan */ }
    public void verifikasiPeminjaman() { /* Logika verifikasi */ }
}