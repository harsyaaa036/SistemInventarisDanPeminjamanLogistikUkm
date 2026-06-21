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

public class barangAset extends Barang {

    private String instansi;
    private Date tanggalPinjam;
    private Date rencanaPengembalian;
    private String kategori;
    private String stock;
    private String kondisi;

    public barangAset(String nama, int stok, double harga) {
        super(nama, stok, harga);
    }

 

    public void tampilInfo() {
        System.out.println("Nama : " + nama);
        System.out.println("Kategori : " + kategori);
        System.out.println("Stock : " + stock);
        System.out.println("Kondisi : " + kondisi);
        System.out.println("Instansi : " + instansi);
    }
}