/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Transaksi;

/**
 *
 * @author Hype AMD
 */
import java.time.LocalDate;
public class Transaksi {
    private int idTransaksi;
    private String status;
    private LocalDate tanggal = LocalDate.now();

    public Transaksi(int idTransaksi, String status) {
        this.idTransaksi = idTransaksi;
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
    
    public void MembuatTransaksi(){
        System.out.println("Transaksi Berhasil Dibuat \n"
                + "ID Transaksi: " + this.idTransaksi + "\n" +
                "Waktu Pembuatan Transaksi: " + this.tanggal);
    }
}
