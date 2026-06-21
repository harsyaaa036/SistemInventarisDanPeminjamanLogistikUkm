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

public class barangPengembalian extends Barang {

    private Date tanggalPengembalian;
    private String kondisiAkhir;
    private int denda;

    public barangPengembalian(String nama, int stok, double harga) {
        super(nama, stok, harga);
    }

    public void cekKondisi() {
        System.out.println("Kondisi akhir : " + kondisiAkhir);
    }

    public int hitungDenda() {
        return denda;
    }
}