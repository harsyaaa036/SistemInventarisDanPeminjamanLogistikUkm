/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import Barang.Barang;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User implements ILogin, ICrudBarang {
    private static ArrayList<Barang> daftarBarang = new ArrayList<>();
    private Scanner scanner;
    private int nim;
    private String jabatan;

    public Admin(String username, String password, int nim, String jabatan) {
        super(username, password);
        this.nim = nim;
        this.jabatan = jabatan;
        this.scanner = new Scanner(System.in);
        initData();
    }

    private void initData() {
        if (daftarBarang.isEmpty()) {
            daftarBarang.add(new Barang("Meja Lipat", 20, 150000));
            daftarBarang.add(new Barang("Kursi Plastik", 50, 25000));
            daftarBarang.add(new Barang("Tenda", 10, 500000));
        }
    }

    @Override
    public void showMenu() {
        int pilihan;
        do {
            System.out.println("\n=== Dashboard Admin ===");
            System.out.println("1. Manajemen Barang (CRUD)");
            System.out.println("2. Laporan Transaksi");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> menuCrud();
                case 2 -> daftarLaporanPeminjaman();
                case 0 -> logout();
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    private void menuCrud() {
        int pilihan;
        do {
            System.out.println("\n--- Manajemen Barang ---");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Lihat Semua Barang");
            System.out.println("3. Edit Barang");
            System.out.println("4. Hapus Barang");
            System.out.println("5. Cari Barang");
            System.out.println("0. Kembali");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> tambahBarang();
                case 2 -> lihatBarang();
                case 3 -> editBarang();
                case 4 -> hapusBarang();
                case 5 -> cariBarang();
                case 0 -> System.out.println("Kembali ke dashboard.");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    @Override
    public void tambahBarang() {
        System.out.println("\n--- Tambah Barang ---");
        System.out.print("Nama barang: ");
        String nama = scanner.nextLine();
        System.out.print("Stok: ");
        int stok = scanner.nextInt();
        System.out.print("Harga: ");
        double harga = scanner.nextDouble();
        scanner.nextLine();

        Barang barang = new Barang(nama, stok, harga);
        daftarBarang.add(barang);
        System.out.println("Barang berhasil ditambahkan!");
        System.out.println(barang);
    }

    public void lihatBarang() {
        System.out.println("\n--- Daftar Barang ---");
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada barang.");
            return;
        }
        for (Barang b : daftarBarang) {
            System.out.println(b);
        }
    }

    @Override
    public void hapusBarang() {
        System.out.println("\n--- Hapus Barang ---");
        System.out.print("Masukkan ID barang yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean ditemukan = daftarBarang.removeIf(b -> b.getId() == id);
        if (ditemukan) {
            System.out.println("Barang dengan ID " + id + " berhasil dihapus.");
        } else {
            System.out.println("Barang dengan ID " + id + " tidak ditemukan.");
        }
    }

    @Override
    public void editBarang() {
        System.out.println("\n--- Edit Barang ---");
        System.out.print("Masukkan ID barang yang akan diedit: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Barang b : daftarBarang) {
            if (b.getId() == id) {
                System.out.print("Nama baru (" + b.getNama() + "): ");
                String nama = scanner.nextLine();
                System.out.print("Stok baru (" + b.getStok() + "): ");
                int stok = scanner.nextInt();
                System.out.print("Harga baru (" + b.getHarga() + "): ");
                double harga = scanner.nextDouble();
                scanner.nextLine();

                if (!nama.isEmpty()) b.setNama(nama);
                b.setStok(stok);
                b.setHarga(harga);
                System.out.println("Barang berhasil diperbarui!");
                System.out.println(b);
                return;
            }
        }
        System.out.println("Barang dengan ID " + id + " tidak ditemukan.");
    }

    @Override
    public void cariBarang() {
        System.out.println("\n--- Cari Barang ---");
        System.out.print("Masukkan nama barang: ");
        String keyword = scanner.nextLine().toLowerCase();

        boolean ditemukan = false;
        for (Barang b : daftarBarang) {
            if (b.getNama().toLowerCase().contains(keyword)) {
                System.out.println(b);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    @Override
    public void login() { System.out.println("Admin login berhasil."); }
    @Override
    public void logout() { System.out.println("Admin logout. Sampai jumpa!"); }

    public void daftarLaporanPeminjaman() {
        System.out.println("\n--- Laporan Transaksi ---");
        System.out.println("Fitur laporan masih dalam pengembangan.");
    }
}