/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

/**
 *
 * @author masag
 */

    public class Anggota extends User implements ILogin {
    private String instansi;
    private String noHp;
    private String lokasiAcara;

    public Anggota(String username, String password, String instansi, String noHp) {
        super(username, password);
        this.instansi = instansi;
        this.noHp = noHp;
    }

    @Override
    public void showMenu() {
        System.out.println("=== Dashboard Anggota ===");
        System.out.println("1. Ajukan Peminjaman");
        System.out.println("2. Proses Pengembalian");
        System.out.println("3. Beli Barang");
    }

    public void login() { System.out.println("Anggota login berhasil."); }
    public void logout() { System.out.println("Anggota logout."); }

    public void ajukanPeminjaman() { /* Logika pengajuan */ }
    public void prosesPengembalian() { /* Logika pengembalian */ }
    public void lihatStatusPeminjaman() { /* Logika cek status */ }
    public void cancelTransaksi() { /* Logika pembatalan */ }
}
    