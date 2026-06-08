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

public class barangAset extends Barang {

    private String instansi;
    private Date tanggalPinjam;
    private Date rencanaPengembalian;

    public barangAset() {
        super();
    }

    public void tampilInfo() {
        System.out.println("Nama : " + nama);
        System.out.println("Kategori : " + kategori);
        System.out.println("Stock : " + stock);
        System.out.println("Kondisi : " + kondisi);
        System.out.println("Instansi : " + instansi);
    }
}