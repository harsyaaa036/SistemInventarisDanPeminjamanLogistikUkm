package Login;

import java.util.Scanner;

public class Anggota extends User implements ILogin {
    private String instansi;
    private String noHp;
    private String lokasiAcara;
    private Scanner scanner;

    public Anggota(String username, String password, String instansi, String noHp, String lokasiAcara) {
        super(username, password);
        this.instansi = instansi;
        this.noHp = noHp;
        this.lokasiAcara = lokasiAcara;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void showMenu() {
        int pilihan;
        do {
            System.out.println("\n=== Dashboard Anggota ===");
            System.out.println("1. Ajukan Peminjaman");
            System.out.println("2. Proses Pengembalian");
            System.out.println("3. Lihat Status Peminjaman");
            System.out.println("4. Cancel Transaksi");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> ajukanPeminjaman();
                case 2 -> prosesPengembalian();
                case 3 -> lihatStatusPeminjaman();
                case 4 -> cancelTransaksi();
                case 0 -> logout();
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    @Override
    public void login() {
        System.out.println("Anggota login berhasil. Selamat datang, " + getUsername());
    }

    @Override
    public void logout() {
        System.out.println("Anggota logout. Sampai jumpa!");
    }

    public void ajukanPeminjaman() {
        System.out.println("\n--- Ajukan Peminjaman ---");
        System.out.print("Nama barang yang ingin dipinjam: ");
        String barang = scanner.nextLine();
        System.out.print("Jumlah: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Peminjaman " + barang + " (" + jumlah + " unit) berhasil diajukan.");
        System.out.println("Menunggu verifikasi admin.");
    }

    public void prosesPengembalian() {
        System.out.println("\n--- Proses Pengembalian ---");
        System.out.print("Masukkan ID transaksi: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Kondisi barang (baik/rusak): ");
        String kondisi = scanner.nextLine();
        System.out.println("Pengembalian transaksi ID " + id + " diproses. Kondisi: " + kondisi);
    }

    public void lihatStatusPeminjaman() {
        System.out.println("\n--- Status Peminjaman ---");
        System.out.println("Anda memiliki " + 0 + " peminjaman aktif.");
        System.out.println("Fitur detail status masih dalam pengembangan.");
    }

    public void cancelTransaksi() {
        System.out.println("\n--- Cancel Transaksi ---");
        System.out.print("Masukkan ID transaksi yang akan dibatalkan: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Transaksi ID " + id + " berhasil dibatalkan.");
    }
}