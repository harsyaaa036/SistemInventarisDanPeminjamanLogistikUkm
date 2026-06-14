package com.harsya.ukm.transaksi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Transaksi {
    private static int counter = 1000;
    private int idTransaksi;
    private String status;
    private LocalDate tanggal;

    public Transaksi(String status) {
        this.idTransaksi = counter++;
        this.status = status;
        this.tanggal = LocalDate.now();
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void buatTransaksi() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Transaksi Berhasil Dibuat");
        System.out.println("ID Transaksi: " + idTransaksi);
        System.out.println("Tanggal: " + tanggal.format(fmt));
        System.out.println("Status: " + status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaksi transaksi = (Transaksi) o;
        return idTransaksi == transaksi.idTransaksi;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransaksi);
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "Transaksi #" + idTransaksi + " | Tgl: " + tanggal.format(fmt) + " | Status: " + status;
    }
}
