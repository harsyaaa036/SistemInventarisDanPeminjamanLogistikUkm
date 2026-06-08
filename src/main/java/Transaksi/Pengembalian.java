package Transaksi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Pengembalian extends Transaksi implements AddOnTransaksi {
    private int idPeminjaman;
    private LocalDate tanggalPengembalian;
    private String kondisiAkhir;
    private long denda;
    private static final long DENDA_PER_HARI = 5000;

    public Pengembalian(int idPeminjaman, LocalDate tanggalRencanaKembali) {
        super("Selesai");
        this.idPeminjaman = idPeminjaman;
        this.tanggalPengembalian = LocalDate.now();
        this.kondisiAkhir = "Baik";
        this.denda = hitungDenda(tanggalRencanaKembali);
    }

    public long hitungDenda(LocalDate tanggalRencanaKembali) {
        long keterlambatan = ChronoUnit.DAYS.between(tanggalRencanaKembali, tanggalPengembalian);
        if (keterlambatan > 0) {
            long totalDenda = keterlambatan * DENDA_PER_HARI;
            System.out.println("Terlambat " + keterlambatan + " hari. Denda: Rp" + totalDenda);
            return totalDenda;
        }
        System.out.println("Tidak ada keterlambatan. Denda: Rp0");
        return 0;
    }

    public String cekKondisiBarang() {
        System.out.print("Masukkan kondisi barang (Baik/Rusak/Hilang): ");
        return kondisiAkhir;
    }

    public void setKondisiAkhir(String kondisiAkhir) {
        this.kondisiAkhir = kondisiAkhir;
    }

    public long getDenda() {
        return denda;
    }

    @Override
    public void hitungDurasi() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        long lamaPinjam = ChronoUnit.DAYS.between(tanggalPengembalian.minusDays(7), tanggalPengembalian);
        System.out.println("Lama Peminjaman: " + lamaPinjam + " hari");
        System.out.println("Tanggal Kembali: " + tanggalPengembalian.format(fmt));
    }

    @Override
    public void cetakStruk() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("===================================");
        System.out.println("       STRUK PENGEMBALIAN");
        System.out.println("===================================");
        System.out.println("ID Pengembalian: " + getIdTransaksi());
        System.out.println("ID Peminjaman  : " + idPeminjaman);
        System.out.println("Tanggal Kembali: " + tanggalPengembalian.format(fmt));
        System.out.println("Kondisi Barang : " + kondisiAkhir);
        System.out.println("Denda          : Rp" + denda);
        System.out.println("Status         : " + getStatus());
        System.out.println("===================================");
    }
}
