package com.harsya.ukm.login;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginMenu {
    private ArrayList<User> daftarUser;
    private Scanner scanner;

    public LoginMenu() {
        this.daftarUser = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        initData();
    }

    private void initData() {
        daftarUser.add(new Admin("admin", "admin123", 12345, "Ketua UKM"));
        daftarUser.add(new Anggota("anggota", "anggota123", "Universitas A", "08123456789", "Gedung Serbaguna"));
    }

    public void start() {
        try {
            System.out.println("=== SISTEM INVENTARIS & PEMINJAMAN LOGISTIK UKM ===");
            int pilihan;
            do {
                System.out.println("1. Login");
                System.out.println("2. Registrasi");
                System.out.println("0. Keluar");
                System.out.print("Pilih menu: ");
                pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1 -> {
                        User user = prosesLogin();
                        if (user != null) {
                            user.showMenu();
                        }
                    }
                    case 2 -> registrasi();
                    case 0 -> System.out.println("Terima kasih!");
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } while (pilihan != 0);
        } finally {
            System.out.println("Program selesai. Resource dibersihkan.");
            scanner.close();
        }
    }

    private User prosesLogin() {
        int percobaan = 0;
        int maksPercobaan = 3;

        while (percobaan < maksPercobaan) {
            System.out.print("\nUsername: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            for (User u : daftarUser) {
                if (u.getUsername().equals(username) && u.verifyPassword(password)) {
                    System.out.println("Login berhasil! Selamat datang, " + username);
                    return u;
                }
            }

            percobaan++;
            int sisa = maksPercobaan - percobaan;
            if (sisa > 0) {
                System.out.println("Username/password salah. Sisa percobaan: " + sisa);
            } else {
                System.out.println("Percobaan habis. Program berhenti.");
            }
        }
        return null;
    }

    private void registrasi() {
        System.out.println("\n=== Registrasi User Baru ===");
        System.out.println("1. Registrasi sebagai Admin");
        System.out.println("2. Registrasi sebagai Anggota");
        System.out.println("0. Batal");
        System.out.print("Pilih: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        switch (pilihan) {
            case 1 -> registrasiAdmin();
            case 2 -> registrasiAnggota();
            case 0 -> System.out.println("Registrasi dibatalkan.");
            default -> System.out.println("Pilihan tidak valid.");
        }
    }

    private void registrasiAdmin() {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        for (User u : daftarUser) {
            if (u.getUsername().equals(username)) {
                System.out.println("Username sudah terdaftar!");
                return;
            }
        }

        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("NIM: ");
        int nim = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Jabatan: ");
        String jabatan = scanner.nextLine();

        daftarUser.add(new Admin(username, password, nim, jabatan));
        System.out.println("Registrasi Admin berhasil! Silakan login.");
    }

    private void registrasiAnggota() {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        for (User u : daftarUser) {
            if (u.getUsername().equals(username)) {
                System.out.println("Username sudah terdaftar!");
                return;
            }
        }

        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Instansi: ");
        String instansi = scanner.nextLine();
        System.out.print("No. HP: ");
        String noHp = scanner.nextLine();
        System.out.print("Lokasi Acara: ");
        String lokasiAcara = scanner.nextLine();

        daftarUser.add(new Anggota(username, password, instansi, noHp, lokasiAcara));
        System.out.println("Registrasi Anggota berhasil! Silakan login.");
    }
}
