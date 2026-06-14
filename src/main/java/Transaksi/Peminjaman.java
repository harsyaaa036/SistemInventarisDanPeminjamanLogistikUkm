package Transaksi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Peminjaman extends Transaksi implements AddOnTransaksi {
    private String namaBarang;
    private int jumlah;
    private LocalDate tanggalPinjam;
    private LocalDate rencanaPengembalian;
    private long durasi;

    public Peminjaman(String namaBarang, int jumlah, LocalDate tanggalPinjam, LocalDate rencanaPengembalian) {
        super("Diproses");
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.tanggalPinjam = tanggalPinjam;
        this.rencanaPengembalian = rencanaPengembalian;
        this.durasi = ChronoUnit.DAYS.between(tanggalPinjam, rencanaPengembalian);
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }

    public LocalDate getRencanaPengembalian() {
        return rencanaPengembalian;
    }

    public long getDurasi() {
        return durasi;
    }

    public void hitungDurasiPeminjaman() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Durasi Peminjaman: " + durasi + " hari");
        System.out.println("Tanggal Pinjam : " + tanggalPinjam.format(fmt));
        System.out.println("Rencana Kembali: " + rencanaPengembalian.format(fmt));
    }

    public boolean validasiStok(int stokTersedia) {
        if (jumlah <= 0) {
            System.out.println("Jumlah barang tidak valid.");
            return false;
        }
        if (jumlah > stokTersedia) {
            System.out.println("Stok tidak mencukupi! Tersedia: " + stokTersedia);
            return false;
        }
        System.out.println("Stok tersedia: " + stokTersedia + ", diminta: " + jumlah + " => Valid");
        return true;
    }

    @Override
    public void hitungDurasi() {
        hitungDurasiPeminjaman();
    }

    @Override
    public void cetakStruk() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("===================================");
        System.out.println("        STRUK PEMINJAMAN");
        System.out.println("===================================");
        System.out.println("ID Transaksi   : " + getIdTransaksi());
        System.out.println("Barang         : " + namaBarang);
        System.out.println("Jumlah         : " + jumlah);
        System.out.println("Tanggal Pinjam : " + tanggalPinjam.format(fmt));
        System.out.println("Rencana Kembali: " + rencanaPengembalian.format(fmt));
        System.out.println("Durasi         : " + durasi + " hari");
        System.out.println("Status         : " + getStatus());
        System.out.println("===================================");
    }
}
