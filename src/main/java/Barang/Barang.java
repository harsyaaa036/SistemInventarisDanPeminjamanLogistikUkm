package Barang;

public class Barang {
    private static int counter = 1;
    private int id;
    private String nama;
    private int stok;
    private double harga;

    public Barang(String nama, int stok, double harga) {
        this.id = counter++;
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + nama + " | Stok: " + stok + " | Harga: Rp" + harga;
    }
}
