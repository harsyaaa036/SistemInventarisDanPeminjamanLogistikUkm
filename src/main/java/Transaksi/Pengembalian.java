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
public class Pengembalian extends Transaksi implements AddOnTransaksi{
    private LocalDate tanggalPengembalian = LocalDate.now();
    private String kondisiAkhir;
    private long denda;

    public Pengembalian(int idTransaksi, String status) {
        super(idTransaksi, status);
    }
    
    public long hitungDenda(){
        return 0;
    }
    
    public String cekKondisiBarang(){   
        return null;
    }

    @Override
    public void hitungDurasi() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cetakStruk() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
