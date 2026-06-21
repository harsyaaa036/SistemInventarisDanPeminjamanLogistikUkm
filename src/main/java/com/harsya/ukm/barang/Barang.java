package com.harsya.ukm.barang;

import java.util.Objects;

public class Barang {
    private static int counter = 1;
    private int id;
    String nama;
    private int stok;
    private double harga;

    public Barang(String nama, int stok, double harga) {
        this.id = counter++;
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
    }

    public Barang(int id, String nama, int stok, double harga) {
        this.id = id;
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
        if (id >= counter) {
            counter = id + 1;
        }
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

    public void setHarga(int harga) {
        this.harga = harga;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barang barang = (Barang) o;
        return id == barang.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + nama + " | Stok: " + stok + " | Harga: Rp" + harga;
    }
}
