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
public class Peminjaman extends Transaksi{
    protected LocalDate tanggalPinjam = LocalDate.now();
    protected LocalDate rencanaPengembalian = LocalDate.now();

    public Peminjaman(int idTransaksi, String status) {
        super(idTransaksi, status);
    }
    
    public void AjukanPeminjaman(){
    
    }
    
    public void DurasiPeminjaman(){
    
    }
    
    public void ValidasiStok(){
    
    }
    
}

