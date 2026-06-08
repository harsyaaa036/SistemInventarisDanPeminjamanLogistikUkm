package Login;

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
        daftarUser.add(new Anggota("anggota", "anggota123", "Universitas A", "08123456789"));
    }

    public void start() {
        System.out.println("=== SISTEM INVENTARIS & PEMINJAMAN LOGISTIK UKM ===");
        User user = prosesLogin();
        if (user != null) {
            user.showMenu();
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
                if (u.getUsername().equals(username) && u.password.equals(password)) {
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
}
