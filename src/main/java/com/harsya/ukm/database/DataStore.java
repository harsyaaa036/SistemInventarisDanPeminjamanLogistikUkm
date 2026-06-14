package com.harsya.ukm.database;

import com.harsya.ukm.barang.Barang;
import com.harsya.ukm.exception.DataTidakDitemukanException;
import com.harsya.ukm.login.Admin;
import com.harsya.ukm.login.Anggota;
import com.harsya.ukm.login.User;
import com.harsya.ukm.transaksi.Peminjaman;
import com.harsya.ukm.transaksi.Pengembalian;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore {
    public static List<Barang> daftarBarang = new ArrayList<>();
    public static List<User> daftarUser = new ArrayList<>();
    public static List<Peminjaman> daftarPeminjaman = new ArrayList<>();
    public static List<Pengembalian> daftarPengembalian = new ArrayList<>();

    public static Map<String, User> userMap = new HashMap<>();
    public static Map<Integer, Barang> barangMap = new HashMap<>();

    static {
        initData();
    }

    public static void initData() {
        if (daftarUser.isEmpty()) {
            Admin admin = new Admin("admin", "admin123", 12345, "Ketua UKM");
            Anggota anggota = new Anggota("anggota", "anggota123", "Universitas A", "08123456789", "Gedung Serbaguna");
            daftarUser.add(admin);
            daftarUser.add(anggota);
            userMap.put(admin.getUsername(), admin);
            userMap.put(anggota.getUsername(), anggota);
        }
        if (daftarBarang.isEmpty()) {
            daftarBarang.add(new Barang("Meja Lipat", 20, 150000));
            daftarBarang.add(new Barang("Kursi Plastik", 50, 25000));
            daftarBarang.add(new Barang("Tenda", 10, 500000));
            daftarBarang.add(new Barang("Sound System", 5, 2000000));
            daftarBarang.add(new Barang("Proyektor", 3, 3500000));
            for (Barang b : daftarBarang) {
                barangMap.put(b.getId(), b);
            }
        }
    }

    public static User findUser(String username) {
        return userMap.get(username);
    }

    public static User findUserByIterator(String username) {
        for (User u : daftarUser) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public static User findUserOrThrow(String username) throws DataTidakDitemukanException {
        User user = userMap.get(username);
        if (user == null) {
            throw new DataTidakDitemukanException("User", username);
        }
        return user;
    }

    public static Barang findBarang(int id) {
        return barangMap.get(id);
    }

    public static Barang findBarangByIterator(int id) {
        for (Barang b : daftarBarang) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public static Barang findBarangOrThrow(int id) throws DataTidakDitemukanException {
        Barang barang = barangMap.get(id);
        if (barang == null) {
            throw new DataTidakDitemukanException("Barang", id);
        }
        return barang;
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
