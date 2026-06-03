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
public class Peminjaman extends Transaksi implements AddOnTransaksi{
    private LocalDate tanggalPinjam = LocalDate.now();
    private LocalDate rencanaPengembalian = LocalDate.now();

    public Peminjaman(int idTransaksi, String status) {
        super(idTransaksi, status);
    }
    
    public void hitungDurasiPeminjaman(){
        
    }
    
    public boolean validasiStok(){
        return true;
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

