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

public class barangPengembalian extends Barang {

    private Date tanggalPengembalian;
    private String kondisiAkhir;
    private int denda;

    public barangPengembalian() {
        super();
    }

    public void cekKondisi() {
        System.out.println("Kondisi akhir : " + kondisiAkhir);
    }

    public int hitungDenda() {
        return denda;
    }
}