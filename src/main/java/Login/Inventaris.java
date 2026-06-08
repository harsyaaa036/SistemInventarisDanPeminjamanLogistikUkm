package Login;

import Barang.Barang;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventaris {
    private List<Barang> listBarang;
    private Scanner scanner;

    public Inventaris() {
        this.listBarang = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        initData();
    }

    private void initData() {
        if (listBarang.isEmpty()) {
            listBarang.add(new Barang("Meja Lipat", 20, 150000));
            listBarang.add(new Barang("Kursi Plastik", 50, 25000));
            listBarang.add(new Barang("Tenda", 10, 500000));
            listBarang.add(new Barang("Sound System", 5, 2000000));
            listBarang.add(new Barang("Proyektor", 3, 3500000));
        }
    }

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
        listBarang.add(barang);
        System.out.println("Barang berhasil ditambahkan!");
        System.out.println(barang);
    }

    public void lihatBarang() {
        System.out.println("\n--- Daftar Barang ---");
        if (listBarang.isEmpty()) {
            System.out.println("Belum ada barang.");
            return;
        }
        for (int i = 0; i < listBarang.size(); i++) {
            System.out.println((i + 1) + ". " + listBarang.get(i));
        }
    }

    public void hapusBarang(int index) {
        int idx = index - 1;
        if (idx >= 0 && idx < listBarang.size()) {
            Barang removed = listBarang.remove(idx);
            System.out.println("Barang \"" + removed.getNama() + "\" berhasil dihapus.");
        } else {
            System.out.println("Indeks tidak valid.");
        }
    }

    public void cariBarang() {
        System.out.println("\n--- Cari Barang ---");
        System.out.print("Masukkan nama barang: ");
        String keyword = scanner.nextLine().toLowerCase();

        boolean ditemukan = false;
        for (Barang b : listBarang) {
            if (b.getNama().toLowerCase().contains(keyword)) {
                System.out.println(b);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    public int getJumlahBarang() {
        return listBarang.size();
    }

    public Barang getBarang(int index) {
        int idx = index - 1;
        if (idx >= 0 && idx < listBarang.size()) {
            return listBarang.get(idx);
        }
        return null;
    }
}
