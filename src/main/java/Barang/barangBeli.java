/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Barang;

/**
 *
 * @author mfauz
 */
import java.util.Date;

public class barangBeli extends Barang {

    private String instansi;
    private int jumlahBeli;
    private Date tanggalPembelian;

    public barangBeli() {
        super();
    }

    public void tambahDaftarBeli() {
        System.out.println("Barang ditambahkan");
    }
}