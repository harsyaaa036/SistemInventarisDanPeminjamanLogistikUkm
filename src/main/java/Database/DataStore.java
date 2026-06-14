package Database;

import Barang.Barang;
import Login.Admin;
import Login.Anggota;
import Login.User;
import Transaksi.Peminjaman;
import Transaksi.Pengembalian;
import java.util.ArrayList;
import java.util.List;

public class DataStore {
    public static List<Barang> daftarBarang = new ArrayList<>();
    public static List<User> daftarUser = new ArrayList<>();
    public static List<Peminjaman> daftarPeminjaman = new ArrayList<>();
    public static List<Pengembalian> daftarPengembalian = new ArrayList<>();

    static {
        initData();
    }

    public static void initData() {
        if (daftarUser.isEmpty()) {
            daftarUser.add(new Admin("admin", "admin123", 12345, "Ketua UKM"));
            daftarUser.add(new Anggota("anggota", "anggota123", "Universitas A", "08123456789", "Gedung Serbaguna"));
        }
        if (daftarBarang.isEmpty()) {
            daftarBarang.add(new Barang("Meja Lipat", 20, 150000));
            daftarBarang.add(new Barang("Kursi Plastik", 50, 25000));
            daftarBarang.add(new Barang("Tenda", 10, 500000));
            daftarBarang.add(new Barang("Sound System", 5, 2000000));
            daftarBarang.add(new Barang("Proyektor", 3, 3500000));
        }
    }

    public static User findUser(String username) {
        for (User u : daftarUser) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public static Barang findBarang(int id) {
        for (Barang b : daftarBarang) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public static Barang findBarangByName(String nama) {
        for (Barang b : daftarBarang) {
            if (b.getNama().equalsIgnoreCase(nama)) {
                return b;
            }
        }
        return null;
    }
}
