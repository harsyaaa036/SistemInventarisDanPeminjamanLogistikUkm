/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.harsya.ukm.barang;

/**
 *
 * @author mfauz
 */
import java.util.Date;

public class barangBeli extends Barang {

    private String instansi;
    private int jumlahBeli;
    private Date tanggalPembelian;

    public barangBeli(String nama, int stok, double harga) {
        super(nama, stok, harga);
    }


    public void tambahDaftarBeli() {
        System.out.println("Barang ditambahkan");
    }
}