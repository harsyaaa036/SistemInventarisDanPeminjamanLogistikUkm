package Transaksi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
}
