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
public class Pengembalian extends Transaksi{
    protected LocalDate tanggalPengembalian = LocalDate.now();
    protected String kondisiAkhir;
    protected int denda;

    public Pengembalian(int idTransaksi, String status) {
        super(idTransaksi, status);
    }
    
    public void ProsesPengembalian(){
    
    }
    
    public void HitungDenda(){
    
    }
    
    public void CekKondisiBarang(){
    
    }
}
