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
public class Transaksi {
    protected int idTransaksi;
    protected LocalDate tanggal = LocalDate.now();
    protected String status;

    public Transaksi(int idTransaksi, String status) {
        this.idTransaksi = idTransaksi;
        this.status = status;
    }
    
    public void CreateTransaksi(){
    
    }
    
    public void UpdateStatus(){
    
    }
    
    public void CancelTransaksi(){
    
    }
}
